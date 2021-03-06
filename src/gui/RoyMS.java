package gui;

import client.LoginCrypto;
import client.MapleCharacter;
import client.inventory.Equip;
import client.inventory.ItemFlag;
import client.inventory.MapleInventoryType;
import constants.GameConstants;
import constants.ServerConstants;
import database.DatabaseConnection;
import handling.RecvPacketOpcode;
import handling.SendPacketOpcode;
import handling.channel.ChannelServer;
import handling.login.handler.AutoRegister;
import handling.world.World;
import java.awt.Canvas;
import java.awt.Checkbox;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ScheduledFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import scripting.PortalScriptManager;
import scripting.ReactorScriptManager;
import server.CashItemFactory;
import server.MapleInventoryManipulator;
import server.MapleItemInformationProvider;
import server.MapleShopFactory;
import server.ShutdownServer;
import server.Start;
import server.Timer;
import server.life.MapleMonsterInformationProvider;
import server.quest.MapleQuest;
import tools.MaplePacketCreator;

public class RoyMS extends JFrame
{
    private static RoyMS instance;
    private static ScheduledFuture<?> ts;
    private int minutesLeft;
    private static Thread t;
    private Canvas canvas1;
    private JTextPane chatLog;
    private Checkbox checkbox1;
    private JButton jButton1;
    private JButton jButton10;
    private JButton jButton11;
    private JButton jButton12;
    private JButton jButton13;
    private JButton jButton14;
    private JButton jButton15;
    private JButton jButton16;
    private JButton jButton17;
    private JButton jButton18;
    private JButton jButton19;
    private JButton jButton2;
    private JButton jButton20;
    private JButton jButton21;
    private JButton jButton22;
    private JButton jButton23;
    private JButton jButton3;
    private JButton jButton4;
    private JButton jButton5;
    private JButton jButton6;
    private JButton jButton7;
    private JButton jButton8;
    private JButton jButton9;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JPanel jPanel7;
    private JPanel jPanel8;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTabbedPane jTabbedPane1;
    private JTabbedPane jTabbedPane2;
    private JTextArea jTextArea1;
    private JTextField jTextField1;
    private JTextField jTextField10;
    private JTextField jTextField11;
    private JTextField jTextField12;
    private JTextField jTextField13;
    private JTextField jTextField14;
    private JTextField jTextField15;
    private JTextField jTextField16;
    private JTextField jTextField17;
    private JTextField jTextField18;
    private JTextField jTextField19;
    private JTextField jTextField2;
    private JTextField jTextField20;
    private JTextField jTextField21;
    private JTextField jTextField22;
    private JTextField jTextField23;
    private JTextField jTextField24;
    private JTextField jTextField25;
    private JTextField jTextField26;
    private JTextField jTextField3;
    private JTextField jTextField4;
    private JTextField jTextField5;
    private JTextField jTextField6;
    private JTextField jTextField7;
    private JTextField jTextField8;
    private JTextField jTextField9;
    
    public static final RoyMS getInstance() {
        return RoyMS.instance;
    }
    
    public RoyMS() {
        this.minutesLeft = 0;
//        final ImageIcon icon = new ImageIcon(this.getClass().getClassLoader().getResource("gui/Icon.png"));
//        this.setIconImage(icon.getImage());
        switch (GameConstants.game) {
            case 0: {
                this.setTitle("?????????-079V6?????????");
                break;
            }
            default: {
                this.setTitle("?????????-?????????");
                break;
            }
        }
        this.initComponents();
    }
    
    private void initComponents() {
        this.canvas1 = new Canvas();
        this.jScrollPane1 = new JScrollPane();
        this.chatLog = new JTextPane();
        this.jTabbedPane2 = new JTabbedPane();
        this.jPanel5 = new JPanel();
        this.jButton10 = new JButton();
        this.jTextField22 = new JTextField();
        this.jButton16 = new JButton();
        this.jButton22 = new JButton();
        this.jButton23 = new JButton();
        this.jScrollPane2 = new JScrollPane();
        this.jTextArea1 = new JTextArea();
        this.jPanel7 = new JPanel();
        this.jButton7 = new JButton();
        this.jButton8 = new JButton();
        this.jLabel2 = new JLabel();
        this.jPanel6 = new JPanel();
        this.jButton9 = new JButton();
        this.jButton1 = new JButton();
        this.jButton5 = new JButton();
        this.jButton4 = new JButton();
        this.jButton3 = new JButton();
        this.jButton2 = new JButton();
        this.jLabel1 = new JLabel();
        this.jButton6 = new JButton();
        this.jButton12 = new JButton();
        this.jButton19 = new JButton();
        this.jPanel8 = new JPanel();
        this.jButton11 = new JButton();
        this.jTextField1 = new JTextField();
        this.jTextField23 = new JTextField();
        this.jButton17 = new JButton();
        this.jPanel1 = new JPanel();
        this.jTextField2 = new JTextField();
        this.jButton13 = new JButton();
        this.jTextField3 = new JTextField();
        this.jTextField4 = new JTextField();
        this.jButton14 = new JButton();
        this.jTextField5 = new JTextField();
        this.jTextField6 = new JTextField();
        this.jTextField7 = new JTextField();
        this.jTextField8 = new JTextField();
        this.jTextField9 = new JTextField();
        this.jTextField10 = new JTextField();
        this.jTextField11 = new JTextField();
        this.jTextField12 = new JTextField();
        this.jTextField13 = new JTextField();
        this.jTextField14 = new JTextField();
        this.jTextField15 = new JTextField();
        this.jTextField16 = new JTextField();
        this.jTextField17 = new JTextField();
        this.jTextField18 = new JTextField();
        this.jTextField19 = new JTextField();
        this.jPanel2 = new JPanel();
        this.jTextField20 = new JTextField();
        this.jTextField21 = new JTextField();
        this.jButton15 = new JButton();
        this.jPanel3 = new JPanel();
        this.jTextField24 = new JTextField();
        this.jTextField25 = new JTextField();
        this.jButton18 = new JButton();
        this.jTextField26 = new JTextField();
        this.checkbox1 = new Checkbox();
        this.jButton20 = new JButton();
        this.jButton21 = new JButton();
        this.jTabbedPane1 = new JTabbedPane();
        this.jLabel3 = new JLabel();
        this.jLabel4 = new JLabel();
        this.jLabel5 = new JLabel();
        this.jLabel6 = new JLabel();
        this.jLabel7 = new JLabel();
        this.setDefaultCloseOperation(3);
        this.jScrollPane1.setViewportView(this.chatLog);
        this.jButton10.setText("???????????????");
        this.jButton10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RoyMS.this.jButton10ActionPerformed(evt);
            }
        });
        this.jTextField22.setText("???????????????????????????");
        this.jTextField22.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RoyMS.this.jTextField22ActionPerformed(evt);
            }
        });
        this.jButton16.setText("???????????????");
        this.jButton16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RoyMS.this.jButton16ActionPerformed(evt);
            }
        });
        this.jButton22.setText("????????????????????????");
        this.jButton22.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RoyMS.this.jButton22ActionPerformed(evt);
            }
        });
        this.jButton23.setText("??????????????????");
        this.jButton23.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RoyMS.this.jButton23ActionPerformed(evt);
            }
        });
        this.jTextArea1.setColumns(20);
        this.jTextArea1.setRows(5);
        this.jTextArea1.setText("???????????????079???????????? ");
        this.jScrollPane2.setViewportView(this.jTextArea1);
        final GroupLayout jPanel5Layout = new GroupLayout(this.jPanel5);
        this.jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel5Layout.createSequentialGroup().addGap(23, 23, 23).addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel5Layout.createSequentialGroup().addComponent(this.jButton22, -2, 134, -2).addGap(18, 18, 18).addComponent(this.jTextField22, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 26, 32767).addComponent(this.jButton23, -2, 134, -2)).addGroup(jPanel5Layout.createSequentialGroup().addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jButton16, -2, 134, -2).addComponent(this.jButton10, -2, 134, -2)).addGap(18, 18, 18).addComponent(this.jScrollPane2))).addGap(24, 24, 24)));
        jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel5Layout.createSequentialGroup().addContainerGap(-1, 32767).addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup().addComponent(this.jButton10, -2, 49, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton16, -2, 49, -2)).addComponent(this.jScrollPane2, GroupLayout.Alignment.TRAILING, -2, -1, -2)).addGap(18, 18, 18).addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton22).addComponent(this.jTextField22, -2, -1, -2).addComponent(this.jButton23)).addGap(8, 8, 8)));
        this.jTabbedPane2.addTab("???????????????", this.jPanel5);
        this.jButton7.setText("????????????");
        this.jButton7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RoyMS.this.jButton7ActionPerformed(evt);
            }
        });
        this.jButton8.setText("????????????");
        this.jButton8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RoyMS.this.jButton8ActionPerformed(evt);
            }
        });
        this.jLabel2.setText("???????????????");
        final GroupLayout jPanel7Layout = new GroupLayout(this.jPanel7);
        this.jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel7Layout.createSequentialGroup().addContainerGap().addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel2).addGroup(jPanel7Layout.createSequentialGroup().addComponent(this.jButton7).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton8))).addContainerGap(295, 32767)));
        jPanel7Layout.setVerticalGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel7Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel7Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton7).addComponent(this.jButton8)).addContainerGap(125, 32767)));
        this.jTabbedPane2.addTab("????????????", this.jPanel7);
        this.jButton9.setText("????????????");
        this.jButton9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RoyMS.this.jButton9ActionPerformed(evt);
            }
        });
        this.jButton1.setText("????????????");
        this.jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RoyMS.this.jButton1ActionPerformed(evt);
            }
        });
        this.jButton5.setText("????????????");
        this.jButton5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RoyMS.this.jButton5ActionPerformed(evt);
            }
        });
        this.jButton4.setText("????????????");
        this.jButton4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RoyMS.this.jButton4ActionPerformed(evt);
            }
        });
        this.jButton3.setText("???????????????");
        this.jButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RoyMS.this.jButton3ActionPerformed(evt);
            }
        });
        this.jButton2.setText("???????????????");
        this.jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RoyMS.this.jButton2ActionPerformed(evt);
            }
        });
        this.jLabel1.setText("???????????????");
        this.jButton6.setText("????????????");
        this.jButton6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RoyMS.this.jButton6ActionPerformed(evt);
            }
        });
        this.jButton12.setText("????????????");
        this.jButton12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RoyMS.this.jButton12ActionPerformed(evt);
            }
        });
        this.jButton19.setText("??????Sql??????");
        this.jButton19.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RoyMS.this.jButton19ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel6Layout = new GroupLayout(this.jPanel6);
        this.jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel6Layout.createSequentialGroup().addContainerGap().addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel1).addGroup(jPanel6Layout.createSequentialGroup().addComponent(this.jButton6).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton12)).addGroup(jPanel6Layout.createSequentialGroup().addComponent(this.jButton1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton5).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton2).addGap(12, 12, 12).addComponent(this.jButton3)).addGroup(jPanel6Layout.createSequentialGroup().addComponent(this.jButton9).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton4)).addComponent(this.jButton19)).addContainerGap(91, 32767)));
        jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel6Layout.createSequentialGroup().addContainerGap().addComponent(this.jLabel1).addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton3).addComponent(this.jButton2)).addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton1).addComponent(this.jButton5))).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton9).addComponent(this.jButton4)).addGap(10, 10, 10).addGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton6).addComponent(this.jButton12)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jButton19).addContainerGap(32, 32767)));
        this.jTabbedPane2.addTab("????????????", this.jPanel6);
        this.jButton11.setText("????????????");
        this.jButton11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RoyMS.this.jButton11ActionPerformed(evt);
            }
        });
        this.jTextField1.setText("??????????????????");
        this.jTextField1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RoyMS.this.jTextField1ActionPerformed(evt);
            }
        });
        this.jTextField23.setText("????????????");
        this.jTextField23.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RoyMS.this.jTextField23ActionPerformed(evt);
            }
        });
        this.jButton17.setText("????????????");
        this.jButton17.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RoyMS.this.jButton17ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel8Layout = new GroupLayout(this.jPanel8);
        this.jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel8Layout.createSequentialGroup().addContainerGap().addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel8Layout.createSequentialGroup().addComponent(this.jTextField1, -2, 124, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton11)).addGroup(jPanel8Layout.createSequentialGroup().addComponent(this.jTextField23, -2, 124, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton17))).addContainerGap(252, 32767)));
        jPanel8Layout.setVerticalGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel8Layout.createSequentialGroup().addContainerGap().addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jTextField1, -2, -1, -2).addComponent(this.jButton11)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel8Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jTextField23, -2, -1, -2).addComponent(this.jButton17)).addContainerGap(117, 32767)));
        this.jTabbedPane2.addTab("????????????", this.jPanel8);
        this.jTextField2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RoyMS.this.jTextField2ActionPerformed(evt);
            }
        });
        this.jButton13.setText("????????????");
        this.jButton13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RoyMS.this.jButton13ActionPerformed(evt);
            }
        });
        this.jTextField3.setText("????????????");
        this.jTextField4.setText("??????ID");
        this.jButton14.setText("????????????");
        this.jButton14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RoyMS.this.jButton14ActionPerformed(evt);
            }
        });
        this.jTextField5.setText("??????");
        this.jTextField6.setText("??????");
        this.jTextField7.setText("??????");
        this.jTextField8.setText("??????");
        this.jTextField9.setText("??????");
        this.jTextField10.setText("HP??????");
        this.jTextField11.setText("MP??????");
        this.jTextField12.setText("????????????");
        this.jTextField13.setText("?????????");
        this.jTextField14.setText("??????????????????");
        this.jTextField15.setText("????????????");
        this.jTextField16.setText("?????????");
        this.jTextField17.setText("?????????");
        this.jTextField18.setText("????????????");
        this.jTextField19.setText("????????????");
        final GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jTextField2, -1, 354, 32767).addGap(18, 18, 18).addComponent(this.jButton13)).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jTextField3, -2, 92, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jTextField4, -2, 77, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jTextField5, -2, 52, -2)).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jTextField9, -2, 58, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jTextField13)).addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.jTextField8).addComponent(this.jTextField7)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jTextField11, -2, 79, -2).addComponent(this.jTextField12, -2, 79, -2))).addGroup(jPanel1Layout.createSequentialGroup().addComponent(this.jTextField6, -2, 58, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jTextField10, -2, 79, -2))).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jTextField16).addComponent(this.jTextField15).addComponent(this.jTextField14).addComponent(this.jTextField17)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jButton14, -1, -1, 32767).addComponent(this.jTextField18).addComponent(this.jTextField19)))).addContainerGap()));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jTextField2, -2, -1, -2).addComponent(this.jButton13)).addGap(18, 18, 18).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jTextField3, -2, -1, -2).addComponent(this.jTextField4, -2, -1, -2).addComponent(this.jTextField5, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jTextField6, -2, -1, -2).addComponent(this.jTextField10, -2, -1, -2).addComponent(this.jTextField14, -2, -1, -2).addComponent(this.jTextField18, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jTextField7, -2, -1, -2).addComponent(this.jTextField11, -2, -1, -2).addComponent(this.jTextField15, -2, -1, -2).addComponent(this.jTextField19, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jTextField8, -2, -1, -2).addComponent(this.jTextField12, -2, -1, -2).addComponent(this.jTextField16, -2, -1, -2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jTextField9, -2, -1, -2).addComponent(this.jTextField13, -2, -1, -2).addComponent(this.jTextField17, -2, -1, -2).addComponent(this.jButton14)).addContainerGap(-1, 32767)));
        this.jTabbedPane2.addTab("??????/??????", this.jPanel1);
        this.jTextField20.setText("????????????");
        this.jTextField20.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RoyMS.this.jTextField20ActionPerformed(evt);
            }
        });
        this.jTextField21.setText("1??????/2??????/3??????/4??????");
        this.jButton15.setText("??????????????????/?????????/??????/??????");
        this.jButton15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RoyMS.this.jButton15ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
        this.jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addComponent(this.jTextField20, -2, 88, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jTextField21, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jButton15).addContainerGap(-1, 32767)));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jTextField20, -2, -1, -2).addComponent(this.jTextField21, -2, -1, -2).addComponent(this.jButton15)).addContainerGap(146, 32767)));
        this.jTabbedPane2.addTab("????????????", this.jPanel2);
        this.jTextField24.setText("??????");
        this.jTextField24.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RoyMS.this.jTextField24ActionPerformed(evt);
            }
        });
        this.jTextField25.setText("?????????");
        this.jTextField25.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RoyMS.this.jTextField25ActionPerformed(evt);
            }
        });
        this.jButton18.setText("????????????");
        this.jButton18.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RoyMS.this.jButton18ActionPerformed(evt);
            }
        });
        this.jTextField26.setText("????????????");
        this.jTextField26.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RoyMS.this.jTextField26ActionPerformed(evt);
            }
        });
        this.checkbox1.setCursor(new Cursor(0));
        this.checkbox1.setName("123");
        this.checkbox1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent evt) {
                RoyMS.this.checkbox1MouseClicked(evt);
            }
        });
        this.jButton20.setText("?????????????????????");
        this.jButton20.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RoyMS.this.jButton20ActionPerformed(evt);
            }
        });
        this.jButton21.setText("???????????????????????????");
        this.jButton21.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                RoyMS.this.jButton21ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
        this.jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addComponent(this.jTextField24, -2, 88, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jTextField25, -2, 88, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jTextField26, -2, 88, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.checkbox1, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.jButton18).addGap(181, 181, 181)).addGroup(jPanel3Layout.createSequentialGroup().addComponent(this.jButton20).addGap(18, 18, 18).addComponent(this.jButton21).addContainerGap(-1, 32767)))));
        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jTextField24, -2, -1, -2).addComponent(this.jTextField25, -2, -1, -2).addComponent(this.jTextField26, -2, -1, -2).addComponent(this.jButton18)).addComponent(this.checkbox1, -2, -1, -2)).addGap(18, 18, 18).addGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jButton20).addComponent(this.jButton21)).addContainerGap(105, 32767)));
        this.jTabbedPane2.addTab("????????????", this.jPanel3);
        this.jLabel3.setFont(new Font("??????", 1, 12));
        this.jLabel3.setText("   ???????????????????????? ?????????????????? ?????????????????? ?????????????????????????????????");
        this.jTabbedPane1.addTab("????????????", this.jLabel3);
        this.jLabel4.setFont(new Font("??????", 1, 12));
        this.jLabel4.setText("       ????????????V079_MAX?????????  ??????  ???????????????QQ??? ");
        this.jTabbedPane1.addTab("????????????", this.jLabel4);
        this.jLabel5.setFont(new Font("??????", 1, 12));
        this.jLabel5.setText("      ????????????BUG ??????????????? ????????? ???BOSS ??????????????? ??????????????? ");
        this.jTabbedPane1.addTab("????????????A", this.jLabel5);
        this.jLabel6.setFont(new Font("??????", 1, 12));
        this.jLabel6.setText("      ????????????    ????????????    ????????????     ????????????    ????????????");
        this.jTabbedPane1.addTab("????????????B", this.jLabel6);
        this.jLabel7.setFont(new Font("??????", 1, 12));
        this.jLabel7.setText("      ???????????????   ????????????   ???????????????????????????   ??????????????????");
        this.jTabbedPane1.addTab("????????????C", this.jLabel7);
        this.jTabbedPane2.addTab("????????????", this.jTabbedPane1);
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane1).addGroup(layout.createSequentialGroup().addContainerGap(478, 32767).addComponent(this.canvas1, -2, -1, -2)).addComponent(this.jTabbedPane2, -2, 0, 32767));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jTabbedPane2).addGap(5, 5, 5).addComponent(this.canvas1, -2, -1, -2).addGap(20, 20, 20).addComponent(this.jScrollPane1, -2, 93, -2).addContainerGap()));
        this.pack();
    }
    
    private void jButton1ActionPerformed(final ActionEvent evt) {
        for (final ChannelServer instance1 : ChannelServer.getAllInstances()) {
            if (instance1 != null) {
                instance1.reloadEvents();
            }
        }
        final String ?????? = "[????????????] ?????????????????????";
        JOptionPane.showMessageDialog(null, "?????????????????????");
        this.printChatLog(??????);
    }
    
    private void jButton5ActionPerformed(final ActionEvent evt) {
        MapleMonsterInformationProvider.getInstance().clearDrops();
        final String ?????? = "[????????????] ?????????????????????";
        JOptionPane.showMessageDialog(null, "?????????????????????");
        this.printChatLog(??????);
    }
    
    private void jButton6ActionPerformed(final ActionEvent evt) {
        SendPacketOpcode.reloadValues();
        RecvPacketOpcode.reloadValues();
        final String ?????? = "[????????????] ?????????????????????";
        JOptionPane.showMessageDialog(null, "?????????????????????");
        this.printChatLog(??????);
    }
    
    private void jButton3ActionPerformed(final ActionEvent evt) {
        PortalScriptManager.getInstance().clearScripts();
        final String ?????? = "[????????????] ????????????????????????";
        JOptionPane.showMessageDialog(null, "????????????????????????");
        this.printChatLog(??????);
    }
    
    private void jButton4ActionPerformed(final ActionEvent evt) {
        MapleShopFactory.getInstance().clear();
        final String ?????? = "[????????????] ?????????????????????";
        JOptionPane.showMessageDialog(null, "?????????????????????");
        this.printChatLog(??????);
    }
    
    private void jButton2ActionPerformed(final ActionEvent evt) {
        ReactorScriptManager.getInstance().clearDrops();
        final String ?????? = "[????????????] ????????????????????????";
        JOptionPane.showMessageDialog(null, "????????????????????????");
        this.printChatLog(??????);
    }
    
    private void jButton9ActionPerformed(final ActionEvent evt) {
        MapleQuest.clearQuests();
        final String ?????? = "[????????????] ?????????????????????";
        JOptionPane.showMessageDialog(null, "?????????????????????");
        this.printChatLog(??????);
    }
    
    private void jButton8ActionPerformed(final ActionEvent evt) {
        int p = 0;
        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
            ++p;
            cserv.closeAllMerchant();
        }
        final String ?????? = "[????????????????????????] ??????????????????" + p + "??????????????????";
        JOptionPane.showMessageDialog(null, "??????????????????" + p + "??????????????????");
        this.printChatLog(??????);
    }
    
    private void jButton7ActionPerformed(final ActionEvent evt) {
        int p = 0;
        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (final MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                ++p;
                chr.saveToDB(true, true);
            }
        }
        final String ?????? = "[??????????????????] ??????" + p + "????????????";
        JOptionPane.showMessageDialog(null, ??????);
        this.printChatLog(??????);
    }
    
    private void jButton10ActionPerformed(final ActionEvent evt) {
        try {
            if (Start.Check) {
                Start.instance.startServer();
                final String ?????? = "[?????????] ????????????????????????";
                this.printChatLog(??????);
            }
            else {
                JOptionPane.showMessageDialog(null, "[?????????] ?????????????????????");
            }
        }
        catch (InterruptedException ex) {
            Logger.getLogger(RoyMS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void jTextField1ActionPerformed(final ActionEvent evt) {
    }
    
    private void jButton11ActionPerformed(final ActionEvent evt) {
        this.sendNotice(0);
    }
    
    private void jButton12ActionPerformed(final ActionEvent evt) {
        CashItemFactory.getInstance().clearCashShop();
        final String out = "[????????????] ?????????????????????";
        JOptionPane.showMessageDialog(null, "?????????????????????");
        this.printChatLog(out);
    }
    
    private void jTextField2ActionPerformed(final ActionEvent evt) {
    }
    
    private void jButton13ActionPerformed(final ActionEvent evt) {
        this.sendNoticeGG();
    }
    
    private void jButton14ActionPerformed(final ActionEvent evt) {
        this.?????????();
    }
    
    private void jTextField20ActionPerformed(final ActionEvent evt) {
    }
    
    private void jButton15ActionPerformed(final ActionEvent evt) {
        this.???????????????();
    }
    
    private void jButton16ActionPerformed(final ActionEvent evt) {
        this.restart();
    }
    
    private void jTextField22ActionPerformed(final ActionEvent evt) {
    }
    
    private void jTextField23ActionPerformed(final ActionEvent evt) {
    }
    
    private void jButton17ActionPerformed(final ActionEvent evt) {
        this.FixAcLogged();
    }
    
    private void jTextField24ActionPerformed(final ActionEvent evt) {
    }
    
    private void jTextField25ActionPerformed(final ActionEvent evt) {
    }
    
    private void jButton18ActionPerformed(final ActionEvent evt) {
        this.ChangePassWord();
    }
    
    private void jButton19ActionPerformed(final ActionEvent evt) {
        DatabaseConnection.closeTimeout();
    }
    
    private void jTextField26ActionPerformed(final ActionEvent evt) {
    }
    
    private void checkbox1MouseClicked(final MouseEvent evt) {
        final boolean status = this.checkbox1.getState();
        if (!(ServerConstants.Super_password = status)) {
            ServerConstants.superpw = "";
        }
        else {
            ServerConstants.superpw = this.jTextField26.getText();
        }
    }
    
    private void jButton20ActionPerformed(final ActionEvent evt) {
        this.??????????????????();
    }
    
    private void jButton21ActionPerformed(final ActionEvent evt) {
        this.?????????????????????();
    }
    
    private void jButton22ActionPerformed(final ActionEvent evt) {
        int p = 0;
        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
            for (final MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                if (chr != null) {
                    ++p;
                }
            }
        }
        JOptionPane.showMessageDialog(this, "?????????????????????" + p + "???");
    }
    
    private void jButton23ActionPerformed(final ActionEvent evt) {
        for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
            cserv.getPlayerStorage().disconnectAll(true);
        }
        JOptionPane.showMessageDialog(null, "???????????????????????????");
    }
    
    private void ?????????????????????() {
        final String account = this.jTextField24.getText();
        if (!AutoRegister.getAccountExists(account)) {
            JOptionPane.showMessageDialog(null, "???????????????");
            return;
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("Update accounts set handsome = ? Where name = ?");
            ps.setString(1, "1");
            ps.setString(2, account);
            ps.execute();
            ps.close();
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "??????!\r\n" + ex);
        }
        JOptionPane.showMessageDialog(null, "???????????????????????????");
        this.printChatLog("????????????: " + account + " .????????????????????????????????????");
    }
    
    private void ??????????????????() {
        final String account = this.jTextField24.getText();
        if (!AutoRegister.getAccountExists(account)) {
            JOptionPane.showMessageDialog(null, "???????????????");
            return;
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("Update accounts set handsome = ? Where name = ?");
            ps.setString(1, "0");
            ps.setString(2, account);
            ps.execute();
            ps.close();
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "??????!\r\n" + ex);
        }
        JOptionPane.showMessageDialog(null, "??????????????????????????????");
        this.printChatLog("????????????: " + account + " .?????????????????????????????????.");
    }
    
    private void ChangePassWord() {
        final String account = this.jTextField24.getText();
        final String password = this.jTextField25.getText();
        if (password.length() > 12) {
            JOptionPane.showMessageDialog(null, "????????????");
            return;
        }
        if (!AutoRegister.getAccountExists(account)) {
            JOptionPane.showMessageDialog(null, "???????????????");
            return;
        }
        try {
            final Connection con = DatabaseConnection.getConnection();
            final PreparedStatement ps = con.prepareStatement("Update accounts set password = ? Where name = ?");
            ps.setString(1, LoginCrypto.hexSha1(password));
            ps.setString(2, account);
            ps.execute();
            ps.close();
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "??????!\r\n" + ex);
        }
        this.printChatLog("????????????: " + account + "???????????? " + password);
    }
    
    private void restart() {
        try {
            final String out = "???????????????????????????";
            this.minutesLeft = Integer.parseInt(this.jTextField22.getText());
            if (RoyMS.ts == null && (RoyMS.t == null || !RoyMS.t.isAlive())) {
                RoyMS.t = new Thread(ShutdownServer.getInstance());
                RoyMS.ts = Timer.EventTimer.getInstance().register(new Runnable() {
                    @Override
                    public void run() {
                        if (RoyMS.this.minutesLeft == 0) {
                            ShutdownServer.getInstance();
                            RoyMS.t.start();
                            RoyMS.ts.cancel(false);
                            return;
                        }
                        World.Broadcast.broadcastMessage(MaplePacketCreator.serverNotice(0, "??????????????? " + RoyMS.this.minutesLeft + "???????????????. ???????????????????????????????????????.").getBytes());
                        System.out.println("??????????????? " + RoyMS.this.minutesLeft + "???????????????.");
                        RoyMS.this.minutesLeft--;
                    }
                }, 60000L);
            }
            this.jTextField22.setText("???????????????????????????");
            this.printChatLog(out);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "??????!\r\n" + e);
        }
    }
    
    private void ???????????????() {
        try {
            int ??????;
            if ("????????????".equals(this.jTextField20.getText())) {
                ?????? = 0;
            }
            else {
                ?????? = Integer.parseInt(this.jTextField20.getText());
            }
            int ??????;
            if ("1??????/2??????/3??????/4??????".equals(this.jTextField21.getText())) {
                ?????? = 0;
            }
            else {
                ?????? = Integer.parseInt(this.jTextField21.getText());
            }
            if (?????? <= 0 || ?????? <= 0) {
                return;
            }
            String ?????? = "";
            int ret = 0;
            if (?????? == 1 || ?????? == 2) {
                for (final ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                    for (final MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                        mch.modifyCSPoints(??????, ??????);
                        String cash = null;
                        if (?????? == 1) {
                            cash = "??????";
                        }
                        else if (?????? == 2) {
                            cash = "?????????";
                        }
                        mch.startMapEffect("???????????????" + ?????? + cash + "???????????????????????????????????????????????????", 5121009);
                        ++ret;
                    }
                }
            }
            else if (?????? == 3) {
                for (final ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                    for (final MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                        mch.gainMeso(??????, true);
                        mch.startMapEffect("???????????????" + ?????? + "????????????????????????????????????????????????????????????", 5121009);
                        ++ret;
                    }
                }
            }
            else if (?????? == 4) {
                for (final ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                    for (final MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                        mch.gainExp(??????, true, false, true);
                        mch.startMapEffect("???????????????" + ?????? + "?????????????????????????????????????????????????????????", 5121009);
                        ++ret;
                    }
                }
            }
            String ??????A = "";
            if (?????? == 1) {
                ??????A = "??????";
            }
            else if (?????? == 2) {
                ??????A = "?????????";
            }
            else if (?????? == 3) {
                ??????A = "??????";
            }
            else if (?????? == 4) {
                ??????A = "??????";
            }
            ?????? = "????????????[" + ?????? * ret + "]." + ??????A + "!??????????????????" + ret + "??????";
            this.jTextField20.setText("????????????");
            this.jTextField21.setText("1??????/2??????/3??????/4??????");
            this.printChatLog(??????);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "??????!\r\n" + e);
        }
    }
    
    private void ?????????() {
        try {
            String ??????;
            if ("????????????".equals(this.jTextField3.getText())) {
                ?????? = "";
            }
            else {
                ?????? = this.jTextField3.getText();
            }
            int ??????ID;
            if ("??????ID".equals(this.jTextField4.getText())) {
                ??????ID = 0;
            }
            else {
                ??????ID = Integer.parseInt(this.jTextField4.getText());
            }
            int ??????;
            if ("??????".equals(this.jTextField5.getText())) {
                ?????? = 0;
            }
            else {
                ?????? = Integer.parseInt(this.jTextField5.getText());
            }
            int ??????;
            if ("??????".equals(this.jTextField6.getText())) {
                ?????? = 0;
            }
            else {
                ?????? = Integer.parseInt(this.jTextField6.getText());
            }
            int ??????;
            if ("??????".equals(this.jTextField7.getText())) {
                ?????? = 0;
            }
            else {
                ?????? = Integer.parseInt(this.jTextField7.getText());
            }
            int ??????;
            if ("??????".equals(this.jTextField8.getText())) {
                ?????? = 0;
            }
            else {
                ?????? = Integer.parseInt(this.jTextField8.getText());
            }
            int ??????;
            if ("??????".equals(this.jTextField9.getText())) {
                ?????? = 0;
            }
            else {
                ?????? = Integer.parseInt(this.jTextField9.getText());
            }
            int HP;
            if ("HP??????".equals(this.jTextField10.getText())) {
                HP = 0;
            }
            else {
                HP = Integer.parseInt(this.jTextField10.getText());
            }
            int MP;
            if ("MP??????".equals(this.jTextField11.getText())) {
                MP = 0;
            }
            else {
                MP = Integer.parseInt(this.jTextField11.getText());
            }
            int ???????????????;
            if ("????????????".equals(this.jTextField12.getText())) {
                ??????????????? = 0;
            }
            else {
                ??????????????? = Integer.parseInt(this.jTextField12.getText());
            }
            String ???????????????;
            if ("?????????".equals(this.jTextField13.getText())) {
                ??????????????? = "";
            }
            else {
                ??????????????? = this.jTextField13.getText();
            }
            int ????????????;
            if ("??????????????????".equals(this.jTextField14.getText())) {
                ???????????? = 0;
            }
            else {
                ???????????? = Integer.parseInt(this.jTextField14.getText());
            }
            final String ?????????????????? = this.jTextField15.getText();
            int ?????????;
            if ("?????????".equals(this.jTextField16.getText())) {
                ????????? = 0;
            }
            else {
                ????????? = Integer.parseInt(this.jTextField16.getText());
            }
            int ?????????;
            if ("?????????".equals(this.jTextField17.getText())) {
                ????????? = 0;
            }
            else {
                ????????? = Integer.parseInt(this.jTextField17.getText());
            }
            int ????????????;
            if ("????????????".equals(this.jTextField18.getText())) {
                ???????????? = 0;
            }
            else {
                ???????????? = Integer.parseInt(this.jTextField18.getText());
            }
            int ????????????;
            if ("????????????".equals(this.jTextField19.getText())) {
                ???????????? = 0;
            }
            else {
                ???????????? = Integer.parseInt(this.jTextField19.getText());
            }
            final MapleItemInformationProvider ii = MapleItemInformationProvider.getInstance();
            final MapleInventoryType type = GameConstants.getInventoryType(??????ID);
            String ??????A = "";
            final String ?????? = "???????????????" + ?????? + " ??????ID???" + ??????ID + " ?????????" + ?????? + " ??????:" + ?????? + " ??????:" + ?????? + " ??????:" + ?????? + " ??????:" + ?????? + " HP:" + HP + " MP:" + MP + " ???????????????:" + ??????????????? + " ???????????????:" + ??????????????? + " ????????????:" + ???????????? + " ??????????????????:" + ?????????????????? + " ?????????:" + ????????? + " ?????????:" + ????????? + " ????????????:" + ???????????? + " ????????????:" + ???????????? + "\r\n";
            for (final ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                for (final MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                    if (mch.getName().equals(??????)) {
                        if (?????? >= 0) {
                            if (!MapleInventoryManipulator.checkSpace(mch.getClient(), ??????ID, ??????, "")) {
                                return;
                            }
                            if ((type.equals(MapleInventoryType.EQUIP) && !GameConstants.isThrowingStar(??????ID) && !GameConstants.isBullet(??????ID)) || (type.equals(MapleInventoryType.CASH) && ??????ID >= 5000000 && ??????ID <= 5000100)) {
                                final Equip item = (Equip)ii.getEquipById(??????ID);
                                if (ii.isCash(??????ID)) {
                                    item.setUniqueId(1);
                                }
                                if (?????? > 0 && ?????? <= 32767) {
                                    item.setStr((short)??????);
                                }
                                if (?????? > 0 && ?????? <= 32767) {
                                    item.setDex((short)??????);
                                }
                                if (?????? > 0 && ?????? <= 32767) {
                                    item.setInt((short)??????);
                                }
                                if (?????? > 0 && ?????? <= 32767) {
                                    item.setLuk((short)??????);
                                }
                                if (????????? > 0 && ????????? <= 32767) {
                                    item.setWatk((short)?????????);
                                }
                                if (????????? > 0 && ????????? <= 32767) {
                                    item.setMatk((short)?????????);
                                }
                                if (???????????? > 0 && ???????????? <= 32767) {
                                    item.setWdef((short)????????????);
                                }
                                if (???????????? > 0 && ???????????? <= 32767) {
                                    item.setMdef((short)????????????);
                                }
                                if (HP > 0 && HP <= 30000) {
                                    item.setHp((short)HP);
                                }
                                if (MP > 0 && MP <= 30000) {
                                    item.setMp((short)MP);
                                }
                                if ("????????????".equals(??????????????????)) {
                                    byte flag = item.getFlag();
                                    if (item.getType() == MapleInventoryType.EQUIP.getType()) {
                                        flag |= (byte)ItemFlag.KARMA_EQ.getValue();
                                    }
                                    else {
                                        flag |= (byte)ItemFlag.KARMA_USE.getValue();
                                    }
                                    item.setFlag(flag);
                                }
                                if (???????????? > 0) {
                                    item.setExpiration(System.currentTimeMillis() + ???????????? * 24 * 60 * 60 * 1000);
                                }
                                if (??????????????? > 0) {
                                    item.setUpgradeSlots((byte)???????????????);
                                }
                                if (??????????????? != null) {
                                    item.setOwner(???????????????);
                                }
                                final String name = ii.getName(??????ID);
                                if (??????ID / 10000 == 114 && name != null && name.length() > 0) {
                                    final String msg = "?????????????????? <" + name + ">";
                                    mch.getClient().getPlayer().dropMessage(5, msg);
                                    mch.getClient().getPlayer().dropMessage(5, msg);
                                }
                                MapleInventoryManipulator.addbyItem(mch.getClient(), item.copy());
                            }
                            else {
                                MapleInventoryManipulator.addById(mch.getClient(), ??????ID, (short)??????, "", null, ????????????, (byte)0);
                            }
                        }
                        else {
                            MapleInventoryManipulator.removeById(mch.getClient(), GameConstants.getInventoryType(??????ID), ??????ID, -??????, true, false);
                        }
                        mch.getClient().getSession().write((Object)MaplePacketCreator.getShowItemGain(??????ID, (short)??????, true));
                        ??????A = "[?????????]:" + ??????;
                    }
                }
            }
            this.jTextField3.setText("????????????");
            this.jTextField4.setText("??????ID");
            this.jTextField5.setText("??????");
            this.jTextField6.setText("??????");
            this.jTextField7.setText("??????");
            this.jTextField8.setText("??????");
            this.jTextField9.setText("??????");
            this.jTextField10.setText("HP??????");
            this.jTextField11.setText("MP??????");
            this.jTextField12.setText("????????????");
            this.jTextField13.setText("?????????");
            this.jTextField14.setText("??????????????????");
            this.jTextField15.setText("????????????");
            this.jTextField16.setText("?????????");
            this.jTextField17.setText("?????????");
            this.jTextField18.setText("????????????");
            this.jTextField19.setText("????????????");
            this.printChatLog(??????A);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "??????!\r\n" + e);
        }
    }
    
    private void printChatLog(final String str) {
        this.chatLog.setText(this.chatLog.getText() + str + "\r\n");
    }
    
    private void sendNoticeGG() {
        try {
            final String str = this.jTextField2.getText();
            String ?????? = "";
            for (final ChannelServer cserv1 : ChannelServer.getAllInstances()) {
                for (final MapleCharacter mch : cserv1.getPlayerStorage().getAllCharacters()) {
                    mch.startMapEffect(str, 5121009);
                    ?????? = "[??????]:" + str;
                }
            }
            this.jTextField2.setText("");
            this.printChatLog(??????);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "??????!\r\n" + e);
        }
    }
    
    private void FixAcLogged() {
        try {
            final com.mysql.jdbc.Connection dcon = (com.mysql.jdbc.Connection)DatabaseConnection.getConnection();
            try (final com.mysql.jdbc.PreparedStatement ps = (com.mysql.jdbc.PreparedStatement)dcon.prepareStatement("UPDATE accounts SET loggedin = 0 WHERE name = " + this.jTextField23.getText())) {
                ps.executeUpdate();
            }
            this.printChatLog("???????????????" + this.jTextField23.getText());
            this.jTextField23.setText("");
        }
        catch (SQLException ex) {}
    }
    
    private void sendNotice(final int type) {
        try {
            final String str = this.jTextField1.getText();
            final byte[] p = null;
            String ?????? = "";
            if (type == 0) {
                for (final ChannelServer cserv : ChannelServer.getAllInstances()) {
                    for (final MapleCharacter chr : cserv.getPlayerStorage().getAllCharacters()) {
                        try {
                            ChannelServer.forceRemovePlayerByCharName(str);
                            if (chr.getName().equals(str) && chr.getMapId() != 0) {
                                chr.getClient().getSession().close(true);
                                chr.getClient().disconnect(true, false);
                                ?????? = "[????????????] ????????????" + str + "?????????";
                            }
                            else {
                                ?????? = "[????????????] ??????????????????????????????????????????????????????";
                            }
                        }
                        catch (Exception ex) {}
                    }
                }
            }
            this.jTextField1.setText("");
            this.printChatLog(??????);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "??????!\r\n" + e);
        }
    }
    
    public static void main(final String[] args) {
        try {
            for (final UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(RoyMS.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex2) {
            Logger.getLogger(RoyMS.class.getName()).log(Level.SEVERE, null, ex2);
        }
        catch (IllegalAccessException ex3) {
            Logger.getLogger(RoyMS.class.getName()).log(Level.SEVERE, null, ex3);
        }
        catch (UnsupportedLookAndFeelException ex4) {
            Logger.getLogger(RoyMS.class.getName()).log(Level.SEVERE, null, ex4);
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RoyMS().setVisible(true);
            }
        });
    }
    
    static {
        RoyMS.instance = new RoyMS();
        RoyMS.ts = null;
        RoyMS.t = null;
    }
}
