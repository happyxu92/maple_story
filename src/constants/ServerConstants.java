package constants;

import server.ServerProperties;
public class ServerConstants
{
    public static boolean PollEnabled;
    public static String Poll_Question;
    public static String[] Poll_Answers;
    public static MapleType MAPLE_TYPE;
    public static short MAPLE_VERSION;
    public static String MAPLE_PATCH;
    public static boolean Use_Fixed_IV;
    public static int MIN_MTS;
    public static int MTS_BASE;
    public static int MTS_TAX;
    public static int MTS_MESO;
    public static int CHANNEL_COUNT;
    public static boolean PACKET_DISPLAY;
    public static boolean DEBUG_OUTPUT_PACKER;
    public static boolean AUTO_REGISTER;
    public static boolean PACKET_ERROR_OFF;
    public static boolean Super_password;
    public static boolean clientAutoDisconnect;
    public static String superpw;
    public static String PACKET_ERROR;
    public static int Channel;
    public static int removePlayerFromMap;
    public static boolean loadop;
    
    public void setPACKET_ERROR(final String ERROR) {
        ServerConstants.PACKET_ERROR = ERROR;
    }
    
    public String getPACKET_ERROR() {
        return ServerConstants.PACKET_ERROR;
    }
    
    public void setChannel(final int ERROR) {
        ServerConstants.Channel = ERROR;
    }
    
    public int getChannel() {
        return ServerConstants.Channel;
    }
    
    public void setRemovePlayerFromMap(final int ERROR) {
        ServerConstants.removePlayerFromMap = ERROR;
    }
    
    public int getRemovePlayerFromMap() {
        return ServerConstants.removePlayerFromMap;
    }
    
    public static boolean getAutoReg() {
        return ServerConstants.AUTO_REGISTER;
    }
    
    public static String ChangeAutoReg() {
        ServerConstants.AUTO_REGISTER = !getAutoReg();
        return ServerConstants.AUTO_REGISTER ? "开启" : "关闭";
    }
    
    public static byte Class_Bonus_EXP(final int job) {
        switch (job) {
            case 3000:
            case 3200:
            case 3210:
            case 3211:
            case 3212:
            case 3300:
            case 3310:
            case 3311:
            case 3312:
            case 3500:
            case 3510:
            case 3511:
            case 3512: {
                return 10;
            }
            default: {
                return 0;
            }
        }
    }
    
    static {
        ServerConstants.PollEnabled = false;
        ServerConstants.Poll_Question = "Are you mudkiz?";
        ServerConstants.Poll_Answers = new String[] { "test1", "test2", "test3" };
        ServerConstants.MAPLE_TYPE = MapleType.CHINA;
        ServerConstants.MAPLE_VERSION = 79;
        ServerConstants.MAPLE_PATCH = "1";
        ServerConstants.Use_Fixed_IV = false;
        ServerConstants.MIN_MTS = 110;
        ServerConstants.MTS_BASE = 100;
        ServerConstants.MTS_TAX = 10;
        ServerConstants.MTS_MESO = 5000;
        ServerConstants.CHANNEL_COUNT = 200;
        ServerConstants.PACKET_DISPLAY = Boolean.parseBoolean(ServerProperties.getProperty("RoyMS.PACKER_DISPLAY", "false"));
        ServerConstants.DEBUG_OUTPUT_PACKER = Boolean.parseBoolean(ServerProperties.getProperty("RoyMS.DEBUG_OUTPUT_PACKET", "false"));
        ServerConstants.AUTO_REGISTER = Boolean.parseBoolean(ServerProperties.getProperty("RoyMS.AutoRegister", "false"));
        ServerConstants.PACKET_ERROR_OFF = Boolean.parseBoolean(ServerProperties.getProperty("RoyMS.PACKET_ERROR_OFF", "false"));
        ServerConstants.Super_password = false;
        ServerConstants.clientAutoDisconnect = true;
        ServerConstants.superpw = "";
        ServerConstants.PACKET_ERROR = "";
        ServerConstants.Channel = 0;
        ServerConstants.removePlayerFromMap = 0;
        ServerConstants.loadop = true;
    }
    
    public enum PlayerGMRank
    {
        NORMAL('@', 0), 
        INTERN('!', 1), 
        GM('!', 2), 
        ADMIN('!', 3);
        
        private final char commandPrefix;
        private final int level;
        
        private PlayerGMRank(final char ch, final int level) {
            this.commandPrefix = ch;
            this.level = level;
        }
        
        public char getCommandPrefix() {
            return this.commandPrefix;
        }
        
        public int getLevel() {
            return this.level;
        }
    }
    
    public enum CommandType
    {
        NORMAL(0), 
        TRADE(1);
        
        private final int level;
        
        private CommandType(final int level) {
            this.level = level;
        }
        
        public int getType() {
            return this.level;
        }
    }
    
    public enum MapleType
    {
        CHINA(4, "GB18030");
        
        final byte type;
        final String ascii;
        
        private MapleType(final int type, final String ascii) {
            this.type = (byte)type;
            this.ascii = ascii;
        }
        
        public String getAscii() {
            return this.ascii;
        }
        
        public byte getType() {
            return this.type;
        }
        
        public static MapleType getByType(final byte type) {
            for (final MapleType l : values()) {
                if (l.getType() == type) {
                    return l;
                }
            }
            return MapleType.CHINA;
        }
    }
}
