package server;

import client.MapleCharacter;
import client.MapleClient;
import client.SkillFactory;
import client.inventory.Equip;
import client.inventory.MapleInventoryType;
import constants.GameConstants;
import constants.OtherSettings;
import constants.ServerConstants;
import database.DatabaseConnection;
import gui.RoyMS;
import handling.MapleServerHandler;
import handling.cashshop.CashShopServer;
import handling.channel.ChannelServer;
import handling.channel.MapleGuildRanking;
import handling.login.LoginInformationProvider;
import handling.login.LoginServer;
import handling.world.World;
import handling.world.family.MapleFamilyBuff;
import handling.world.guild.MapleGuild;
import java.io.IOException;
import java.net.ServerSocket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import provider.MapleData;
import server.custom.auction.AuctionManager;
import server.events.MapleOxQuizFactory;
import server.life.MapleLifeFactory;
import server.life.MapleMonsterInformationProvider;
import server.life.MobSkillFactory;
import server.maps.MapleMapFactory;
import server.quest.MapleQuest;
import tools.FileoutputUtil;
import tools.Pair;
import tools.StringUtil;
import tools.Triple;

public class Start
{
    public static boolean Check;
    private static RoyMS CashGui;
    public static Start instance;
    private static int maxUsers;
    private static ServerSocket srvSocket;
    private static final int srvPort = 6350;
    private MapleClient c;

    public static void main(final String[] args) throws InterruptedException {
        String homePath = System.getProperty("homePath");
        String scriptsPath = System.getProperty("scriptsPath");
        String wzPath = System.getProperty("wzPath");
        System.setProperty("server_property_file_path", homePath + "server.properties");
        System.setProperty("server_property_db_path", homePath + "db.properties");
        System.setProperty("server_property_shop_path", homePath + "shop.properties");
        System.setProperty("server_property_fish_path", homePath + "fish.properties");
        System.setProperty("wzPath", wzPath);
        System.setProperty("scripts_path", scriptsPath);
        System.setProperty("server_name", "Happy");
        OtherSettings.getInstance();
        Start.instance.run();
//        getInfoToSql();
    }
    
    public void run() throws InterruptedException {
        final long start = System.currentTimeMillis();
        checkSingleInstance();
        if (Boolean.parseBoolean(ServerProperties.getProperty("RoyMS.Admin"))) {
            printSection("Admin login only!");
        }
        if (Boolean.parseBoolean(ServerProperties.getProperty("RoyMS.AutoRegister"))) {
            System.out.println("Load auto register...");
        }
        try {
            try (final PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE accounts SET loggedin = 0")) {
                ps.executeUpdate();
            }
            try (final PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE accounts SET lastGainHM = 0")) {
                ps.executeUpdate();
            }
        }
        catch (SQLException ex) {
            throw new RuntimeException("[Database Exception] can not connect to MySQL!");
        }
        System.out.println("Server start init... version:079H3");
        System.out.println("Current operation system: " + System.getProperty("sun.desktop"));
        System.out.println("ServerIp: " + ServerProperties.getProperty("RoyMs.IP") + ":" + LoginServer.PORT);
        System.out.println("Game version: " + ServerConstants.MAPLE_TYPE + " v." +
                ServerConstants.MAPLE_VERSION + "." + ServerConstants.MAPLE_PATCH);
        World.init();
        runThread();
        loadData();
        System.out.println("Loading login service");
        LoginServer.run_startup_configurations();
        System.out.println("Loading channel...");
        ChannelServer.startChannel_Main();
        System.out.println("Loading channel completed!\r\n");
        System.out.print("Loading cash shop...");
        CashShopServer.run_startup_configurations();
        printSection("Starting Respawn Thread");
        World.registerRespawn();
        Timer.CheatTimer.getInstance().register(AutobanManager.getInstance(), 60000L);
        onlineTime(1);
        memoryRecical(10);
        MapleServerHandler.registerMBean();
        LoginServer.setOn();
        System.out.println("\r\nExpRate:" + Integer.parseInt(ServerProperties.getProperty("RoyMS.Exp"))
                + "  QuestExpRate:" + Integer.parseInt(ServerProperties.getProperty("RoyMS.QuestExp"))
                + "  Drop Rate:" + Integer.parseInt(ServerProperties.getProperty("RoyMS.Drop"))
                + "  Meso Rate:" + Integer.parseInt(ServerProperties.getProperty("RoyMS.Meso"))
                + "  Boss Rate" + Integer.parseInt(ServerProperties.getProperty("RoyMS.BDrop")));
        if (Boolean.parseBoolean(ServerProperties.getProperty("RoyMS.checkRepeatEqu", "false"))) {
            checkCopyItemFromSql();
        }
        if (Boolean.parseBoolean(ServerProperties.getProperty("RoyMS.chekCharacterConnection", "false"))) {
            System.out.println("Check all power");
            startCheck();
        }
        final long now = System.currentTimeMillis() - start;
        final long seconds = now / 1000L;
        final long ms = now % 1000L;
        System.out.println("Loading completed, cost:" + seconds + "s, " + ms + "ms\r\n");
        boolean loadGui = Boolean.parseBoolean(ServerProperties.getProperty("RoyMS.loadGui", "false"));
        if(loadGui){
            System.out.println("Loading GUI tool");
            CashGui();
        }
    }
    
    public static void runThread() {
        System.out.println("\r\nStart loading thread");
        Timer.WorldTimer.getInstance().start();
        Timer.EtcTimer.getInstance().start();
        Timer.MapTimer.getInstance().start();
        Timer.MobTimer.getInstance().start();
        Timer.CloneTimer.getInstance().start();
        Timer.CheatTimer.getInstance().start();
        System.out.print("...");
        Timer.EventTimer.getInstance().start();
        Timer.BuffTimer.getInstance().start();
        Timer.TimerManager.getInstance().start();
        Timer.PingTimer.getInstance().start();
        Timer.PGTimer.getInstance().start();
        System.out.println("Completed!\r\n");
    }
    
    public static void loadData() {
        System.out.println("Loading data...be patient...");
        System.out.println("Loading exp data...");
        GameConstants.LoadExp();
        System.out.println("Loading rank data...");
        MapleGuildRanking.getInstance().RankingUpdate();
        System.out.println("Loading Guild, remove not exist guild...");
        MapleGuild.loadAll();
        System.out.println("Loading Quests...");
        MapleQuest.initQuests();
        MapleLifeFactory.loadQuestCounts();
        System.out.println("Loading retrieve global...");
        MapleMonsterInformationProvider.getInstance().retrieveGlobal();
        System.out.println("Loading language test system...");
        LoginInformationProvider.getInstance();
        System.out.println("Loading item data...");
        ItemMakerFactory.getInstance();
        MapleItemInformationProvider.getInstance().load();
        System.out.println("Loading skill data...");
        SkillFactory.getSkill(99999999);
        MobSkillFactory.getInstance();
        MapleFamilyBuff.getBuffEntry();
        System.out.println("Loading SpeedRunner");
        Runtime.getRuntime().addShutdownHook(new Thread(new Shutdown()));
        try {
            SpeedRunner.getInstance().loadSpeedRuns();
        }
        catch (SQLException e) {
            System.out.println("SpeedRunner Exception=" + e);
        }
        System.out.println("Loading random reward system...");
        RandomRewards.getInstance();
        System.out.println("Loading ox quiz data...");
        MapleOxQuizFactory.getInstance().initialize();
        System.out.println("Loading Carnival data...");
        MapleCarnivalFactory.getInstance();
        System.out.println("Loading customLife");
        MapleMapFactory.loadCustomLife();
        System.out.println("Loading cash item data...");
        CashItemFactory.getInstance().initialize();
    }
    
    public static void ????????????(final int time) {
        System.out.println("???????????????????????????." + time + "??????????????????????????????.");
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                int ppl = 0;
                try {
                    for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                        for (final MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                            if (chr == null) {
                                continue;
                            }
                            ++ppl;
                            chr.saveToDB(false, false);
                        }
                    }
                }
                catch (Exception ex) {}
            }
        }, 60000 * time);
    }

    //????????????
    public static void onlineTime(final int time) {
        System.out.println("Record characters' online time once " + time + " minute!");
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                try {
                    for (final ChannelServer chan : ChannelServer.getAllInstances()) {
                        for (final MapleCharacter chr : chan.getPlayerStorage().getAllCharacters()) {
                            if (chr == null) {
                                continue;
                            }
                            chr.gainGamePoints(1);
                            if (chr.getGamePoints() >= 5) {
                                continue;
                            }
                            chr.resetFBRW();
                            chr.resetFBRWA();
                            chr.resetSBOSSRW();
                            chr.resetSBOSSRWA();
                            chr.resetSGRW();
                            chr.resetSGRWA();
                            chr.resetSJRW();
                            chr.resetlb();
                            chr.setmrsjrw(0);
                            chr.setmrfbrw(0);
                            chr.setmrsgrw(0);
                            chr.setmrsbossrw(0);
                            chr.setmrfbrwa(0);
                            chr.setmrsgrwa(0);
                            chr.setmrsbossrwa(0);
                            chr.setmrfbrwas(0);
                            chr.setmrsgrwas(0);
                            chr.setmrsbossrwas(0);
                            chr.setmrfbrws(0);
                            chr.setmrsgrws(0);
                            chr.setmrsbossrws(0);
                            chr.resetGamePointsPS();
                            chr.resetGamePointsPD();
                        }
                    }
                }
                catch (Exception ex) {}
            }
        }, 60000 * time);
    }
    
    protected static void checkSingleInstance() {
        try {
            Start.srvSocket = new ServerSocket(srvPort);
        }
        catch (IOException ex) {
            if (ex.getMessage().indexOf("Address already in use: JVM_Bind") >= 0) {
                System.out.println("????????????????????????????????????????????????(Only one instance allowed)???");
            }
            System.exit(0);
        }
    }
    
    protected static void checkCopyItemFromSql() {
        System.out.println("??????????????? ????????????????????????????????????.????????????????????????");
        final List<Integer> equipOnlyIds = new ArrayList<Integer>();
        final Map<Integer, Integer> checkItems = new HashMap<Integer, Integer>();
        try {
            final Connection con = DatabaseConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM inventoryitems WHERE equipOnlyId > 0");
            final ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                final int itemId = rs.getInt("itemId");
                final int equipOnlyId = rs.getInt("equipOnlyId");
                if (equipOnlyId > 0) {
                    if (checkItems.containsKey(equipOnlyId)) {
                        if (checkItems.get(equipOnlyId) != itemId) {
                            continue;
                        }
                        equipOnlyIds.add(equipOnlyId);
                    }
                    else {
                        checkItems.put(equipOnlyId, itemId);
                    }
                }
            }
            rs.close();
            ps.close();
            Collections.sort(equipOnlyIds);
            for (final int i : equipOnlyIds) {
                ps = con.prepareStatement("DELETE FROM inventoryitems WHERE equipOnlyId = ?");
                ps.setInt(1, i);
                ps.executeUpdate();
                ps.close();
                System.out.println("?????????????????? ??????????????????ID: " + i + " ?????????????????????..");
                FileoutputUtil.log("????????????.txt", "?????????????????? ??????????????????ID: " + i + " ?????????????????????..");
            }
        }
        catch (SQLException ex) {
            System.out.println("[EXCEPTION] ??????????????????????????????." + ex);
        }
    }
    
    public void startServer() throws InterruptedException {
        final long start = System.currentTimeMillis();
        checkSingleInstance();
        System.out.println("======================================");
        System.out.println(ServerProperties.getProperty("RoyMS.Admin"));
        System.out.println("========================");
        if (Boolean.parseBoolean(ServerProperties.getProperty("RoyMS.Admin"))) {
            printSection("[!!! ???????????????????????????????????? !!!]");
        }
        if (Boolean.parseBoolean(ServerProperties.getProperty("RoyMS.AutoRegister"))) {
            System.out.println("?????? ?????????????????? :::");
        }
        try (final PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("UPDATE accounts SET loggedin = 0")) {
            ps.executeUpdate();
        }
        catch (SQLException ex) {
            throw new RuntimeException("[???????????????] ????????????????????????????????????????????????MySQL?????????.");
        }
        System.out.println("????????? ????????????...");
        System.out.println("??????????????????: " + System.getProperty("sun.desktop"));
        System.out.println("???????????????: " + ServerProperties.getProperty("RoyMS.IP") + ":" + LoginServer.PORT);
        System.out.println("????????????: " + ServerConstants.MAPLE_TYPE + " v." + ServerConstants.MAPLE_VERSION + "." + ServerConstants.MAPLE_PATCH);
        System.out.println("??????Roy: ??????????????????????????????????????????????????????");
        World.init();
        runThread();
        loadData();
        System.out.print("??????\"??????\"??????...");
        LoginServer.run_startup_configurations();
        System.out.println("??????????????????...");
        ChannelServer.startChannel_Main();
        System.out.println("??????????????????!\r\n");
        System.out.print("??????????????????...");
        CashShopServer.run_startup_configurations();
        printSection("????????????");
        World.registerRespawn();
        Timer.CheatTimer.getInstance().register(AutobanManager.getInstance(), 60000L);
        onlineTime(1);
        memoryRecical(360);
        MapleServerHandler.registerMBean();
        LoginServer.setOn();
        System.out.println("\r\n???????????????" + Integer.parseInt(ServerProperties.getProperty("RoyMS.Exp")) + "  ???????????????" + Integer.parseInt(ServerProperties.getProperty("RoyMS.Drop")) + "  ???????????????" + Integer.parseInt(ServerProperties.getProperty("RoyMS.Meso")) + "  BOSS?????????" + Integer.parseInt(ServerProperties.getProperty("RoyMS.BDrop")));
        if (Boolean.parseBoolean(ServerProperties.getProperty("RoyMS.checkRepeatEqu", "false"))) {
            checkCopyItemFromSql();
        }
        if (Boolean.parseBoolean(ServerProperties.getProperty("RoyMS.???????????????", "false"))) {
            System.out.println("?????????????????????");
            startCheck();
        }
        final long now = System.currentTimeMillis() - start;
        final long seconds = now / 1000L;
        final long ms = now % 1000L;
        System.out.println("????????????, ??????: " + seconds + "???" + ms + "??????\r\n");
        System.out.println("????????????????????????????????????????????????");
    }
    
    public static void CashGui() {
        if (Start.CashGui != null) {
            Start.CashGui.dispose();
        }
        (Start.CashGui = new RoyMS()).setVisible(true);
    }

    //????????????
    public static void onlineStatistics(final int time) {
        System.out.println("???????????????????????????." + time + "???????????????????????????????????????.");
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                final Map<Integer, Integer> connected = World.getConnected();
                final StringBuilder conStr = new StringBuilder(FileoutputUtil.CurrentReadable_Time() + " ????????????: ");
                for (final int i : connected.keySet()) {
                    if (i == 0) {
                        final int users = connected.get(i);
                        conStr.append(StringUtil.getRightPaddedStr(String.valueOf(users), ' ', 3));
                        if (users > Start.maxUsers) {
                            Start.maxUsers = users;
                        }
                        conStr.append(" ????????????: ");
                        conStr.append(Start.maxUsers);
                        break;
                    }
                }
                System.out.println(conStr.toString());
                if (Start.maxUsers > 0) {
                    FileoutputUtil.log("log/????????????.log", conStr.toString());
                }
            }
        }, 60000 * time);
    }
    
    public static void printSection(String s) {
        for (s = "-[ " + s + " ]"; s.getBytes().length < 79; s = "=" + s) {}
        System.out.println(s);
    }
    
    public static void startCheck() {
        System.out.println("Check character connection once 30 seconds!");
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                for (final ChannelServer cserv_ : ChannelServer.getAllInstances()) {
                    for (final MapleCharacter chr : cserv_.getPlayerStorage().getAllCharacters()) {
                        if (chr != null) {
                            chr.startCheck();
                        }
                    }
                }
            }
        }, 30000L);
    }

    //????????????
    public static void memoryRecical(final int time) {
        Timer.WorldTimer.getInstance().register(new Runnable() {
            @Override
            public void run() {
                System.gc();
            }
        }, 60000 * time);
    }
    
    static {
        Start.Check = true;
        Start.instance = new Start();
        Start.maxUsers = 0;
        Start.srvSocket = null;
    }
    
    public static class Shutdown implements Runnable
    {
        @Override
        public void run() {
            new Thread(ShutdownServer.getInstance()).start();
        }
    }

    private static void getInfoToSql() {
        getMobInfo();
        getItemInfo();
        System.out.println("Get info completed!");
    }

    private static void getMobInfo() {
        final Connection con = DatabaseConnection.getConnection();
        try {
            con.setAutoCommit(true);
            PreparedStatement ps = con.prepareStatement("Truncate Table mob_info");;
            ps.executeUpdate();
            ps.close();
            for (MapleData mobData: MapleLifeFactory.getMobStringData().getChildren()) {
                int mobId = Integer.parseInt(mobData.getName());
                String name = mobData.getChildByPath("name").getData().toString();
                ps = con.prepareStatement("INSERT INTO mob_info (`mobid`, `name`) VALUES (?, ?)");
                ps.setInt(1, mobId);
                ps.setString(2, name);
                ps.executeUpdate();
                ps.close();
            }
        } catch (SQLException e3) {
            FileoutputUtil.outputFileError(FileoutputUtil.PacketEx_Log, e3);
        }
    }

    private static void getItemInfo() {
        List<Triple<Integer, String, String>> allItems = MapleItemInformationProvider.getInstance().getAllItemInfo();
        final Connection con = DatabaseConnection.getConnection();
        try {
            con.setAutoCommit(true);
            PreparedStatement ps2 = con.prepareStatement("Truncate Table item_info");;
            ps2.executeUpdate();
            ps2.close();
            for (Triple<Integer, String, String> triple: allItems) {
                ps2 = con.prepareStatement("INSERT INTO item_info (`itemid`, `name`, `desc`, `level`, `str`, `dex`, " +
                        "`luk`, `hp`, `mp`, `watk`, `matk`, `wdef`, `mdef`, `acc`, `avoid`, `hands`, `speed`, `jump`, " +
                        "`ViciousHammer`, `potential1`, `potential2`, `potential3`, `hpR`, `mpR`, `itemLevel`" +
                        ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                ps2.setInt(1, triple.getLeft());
                ps2.setString(2, triple.getMid());
                ps2.setString(3, triple.getRight());
                setEquInfo(ps2, triple.getLeft());
                ps2.executeUpdate();
                ps2.close();
            }
        } catch (SQLException e3) {
            System.out.println("Sql error:" + e3);
        }
    }

    private static void setEquInfo(PreparedStatement ps, Integer equipId) throws SQLException {
        if (MapleInventoryType.EQUIP.equals(AuctionManager.getInstance().getItemTypeByItemId(equipId))) {
            Equip equip = (Equip) MapleItemInformationProvider.getInstance().getEquipById(equipId);
            ps.setInt(4, equip.getLevel());
            ps.setInt(5, equip.getStr());
            ps.setInt(6, equip.getDex());
            ps.setInt(7, equip.getLuk());
            ps.setInt(8, equip.getHp());
            ps.setInt(9, equip.getMp());
            ps.setInt(10, equip.getWatk());
            ps.setInt(11, equip.getMatk());
            ps.setInt(12, equip.getWdef());
            ps.setInt(13, equip.getMdef());
            ps.setInt(14, equip.getAcc());
            ps.setInt(15, equip.getAvoid());
            ps.setInt(16, equip.getHands());
            ps.setInt(17, equip.getSpeed());
            ps.setInt(18, equip.getJump());
            ps.setInt(19, equip.getViciousHammer());
            ps.setInt(20, equip.getPotential1());
            ps.setInt(21, equip.getPotential2());
            ps.setInt(22, equip.getPotential3());
            ps.setInt(23, equip.getHpR());
            ps.setInt(24, equip.getMpR());
            ps.setInt(25, equip.getEquipLevel());
        } else {
            ps.setInt(4, 0);
            ps.setInt(5, 0);
            ps.setInt(6, 0);
            ps.setInt(7, 0);
            ps.setInt(8, 0);
            ps.setInt(9, 0);
            ps.setInt(10, 0);
            ps.setInt(11, 0);
            ps.setInt(12, 0);
            ps.setInt(13, 0);
            ps.setInt(14, 0);
            ps.setInt(15, 0);
            ps.setInt(16, 0);
            ps.setInt(17, 0);
            ps.setInt(18, 0);
            ps.setInt(19, 0);
            ps.setInt(20, 0);
            ps.setInt(21, 0);
            ps.setInt(22, 0);
            ps.setInt(23, 0);
            ps.setInt(24, 0);
            ps.setInt(25, 0);
        }
    }
}
