/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.greatcattle.jui.main;

import com.greatcattle.business.consts.Msg;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.text.DateFormatter;

/**
 *
 * @author Administrator
 */
public class MainFrm extends javax.swing.JFrame {

    /**
     * Creates new form MainFrm
     */
    public MainFrm() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu7 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JDesktopPane();
        jPanel2 = new javax.swing.JPanel();
        leftStatus = new javax.swing.JLabel();
        midStatus = new javax.swing.JSeparator();
        rightStatus = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        jMenu7.setText("File");
        jMenuBar2.add(jMenu7);

        jMenu8.setText("Edit");
        jMenuBar2.add(jMenu8);

        jMenuItem12.setText("jMenuItem12");

        jMenuItem22.setText("jMenuItem22");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1119, 750));

        jPanel1.setPreferredSize(new java.awt.Dimension(800, 516));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jToolBar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jToolBar1.setRollover(true);
        jToolBar1.setPreferredSize(new java.awt.Dimension(100, 60));

        jPanel4.setLayout(new java.awt.GridLayout(1, 0));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/greatcattle/jui/img/exit(0).png"))); // NOI18N
        jButton1.setText("退出");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/greatcattle/jui/img/switchUser.png"))); // NOI18N
        jButton3.setText("切换用户");
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jButton3);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/greatcattle/jui/img/chen.png"))); // NOI18N
        jButton5.setText("称重");
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jButton5);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/greatcattle/jui/img/statitcs.png"))); // NOI18N
        jButton6.setText("查询统计");
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jButton6);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/greatcattle/jui/img/格式.png"))); // NOI18N
        jButton7.setText("磅单格式");
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jButton7);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/greatcattle/jui/img/computer.png"))); // NOI18N
        jButton4.setText("计算器");
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jButton4);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/greatcattle/jui/img/voice.png"))); // NOI18N
        jButton2.setText("语音播报");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jButton2);
        jPanel4.add(jPanel9);
        jPanel4.add(jPanel8);
        jPanel4.add(jPanel7);

        jPanel5.setPreferredSize(new java.awt.Dimension(100, 200));
        jPanel5.add(jPanel6);

        jPanel4.add(jPanel5);

        jToolBar1.add(jPanel4);

        jPanel1.add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        jPanel3.setBackground(new java.awt.Color(240, 240, 240));
        jPanel3.setLayout(new java.awt.BorderLayout());
        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(800, 20));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        leftStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        leftStatus.setText(" 操作员:张三");
        leftStatus.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        leftStatus.setMaximumSize(new java.awt.Dimension(42, 25));
        leftStatus.setPreferredSize(new java.awt.Dimension(390, 30));
        jPanel2.add(leftStatus);

        midStatus.setOrientation(javax.swing.SwingConstants.VERTICAL);
        midStatus.setPreferredSize(new java.awt.Dimension(2, 30));
        jPanel2.add(midStatus);

        rightStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rightStatus.setText(" 时间：2020-02-20 10:55:11");
        rightStatus.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        rightStatus.setPreferredSize(new java.awt.Dimension(399, 30));
        jPanel2.add(rightStatus);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_END);

        jMenuBar1.setPreferredSize(new java.awt.Dimension(800, 25));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/greatcattle/jui/img/systemA.png"))); // NOI18N
        jMenu1.setText("系统(S)");
        jMenu1.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N

        jMenuItem18.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK));
        jMenuItem18.setText("切换用户");
        jMenu1.add(jMenuItem18);

        jMenuItem24.setText("更改密码");
        jMenu1.add(jMenuItem24);
        jMenu1.add(jSeparator7);

        jMenuItem19.setText("退出系统");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem19);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/greatcattle/jui/img/operation.png"))); // NOI18N
        jMenu2.setText("操作(O)");
        jMenu2.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N

        jMenuItem15.setText("称重查询");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem15);

        jMenuItem17.setText("模拟称重");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem17);
        jMenu2.add(jSeparator6);

        jMenuItem16.setText("称重操作");
        jMenu2.add(jMenuItem16);

        jMenuItem20.setText("预置操作");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem20);

        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/greatcattle/jui/img/tongji.png"))); // NOI18N
        jMenu3.setText("统计(C)");
        jMenu3.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N
        jMenuBar1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/greatcattle/jui/img/config.png"))); // NOI18N
        jMenu4.setText("配置(T)");
        jMenu4.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N

        jMenuItem6.setText("看板设置");
        jMenu4.add(jMenuItem6);

        jMenuItem7.setText("仪表设置");
        jMenu4.add(jMenuItem7);
        jMenu4.add(jSeparator3);

        jMenuItem8.setText("轮轴设置");
        jMenu4.add(jMenuItem8);

        jMenuItem9.setText("I/O设置");
        jMenu4.add(jMenuItem9);

        jMenuItem14.setText("LED设置");
        jMenu4.add(jMenuItem14);

        jMenuBar1.add(jMenu4);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/greatcattle/jui/img/maintain.png"))); // NOI18N
        jMenu5.setText("维护(M)");
        jMenu5.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N

        jMenuItem2.setText("车辆信息");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem2);

        jMenuItem3.setText("发货单位");
        jMenu5.add(jMenuItem3);
        jMenu5.add(jSeparator2);

        jMenuItem4.setText("收货单位");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem4);

        jMenuItem5.setText("磅轴信息");
        jMenu5.add(jMenuItem5);
        jMenu5.add(jSeparator5);

        jMenuItem10.setText("用户信息");
        jMenu5.add(jMenuItem10);

        jMenuItem11.setText("权限信息");
        jMenu5.add(jMenuItem11);
        jMenu5.add(jSeparator4);

        jMenuItem13.setText("角色信息");
        jMenu5.add(jMenuItem13);

        jMenuBar1.add(jMenu5);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/greatcattle/jui/img/help.png"))); // NOI18N
        jMenu6.setText("帮助(H)");
        jMenu6.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N

        jMenuItem21.setText("计算器");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem21);

        jMenuItem23.setText("关于系统");
        jMenu6.add(jMenuItem23);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
         jPanel3.removeAll();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TruckFrm truckFrm= new TruckFrm();
                jPanel3.add(truckFrm);
                truckFrm.setVisible(true);
                jPanel3.updateUI();
                jPanel3.repaint();
            }
        });
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         dispose();//本窗口销毁,释放内存资源
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        jPanel3.removeAll();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SimulationFrm simulationFrm= new SimulationFrm();
                jPanel3.add(simulationFrm);
                simulationFrm.setVisible(true);
                jPanel3.updateUI();
                jPanel3.repaint();
            }
        });
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        dispose();//本窗口销毁,释放内存资源
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TruckPkgFrm truckPkgFrm= new TruckPkgFrm();
                jPanel3.add(truckPkgFrm);
                truckPkgFrm.setVisible(true);
            }
        });
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])throws Exception {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
       MainFrm mainFrm =  new MainFrm();
       mainFrm.setVisible(true);
       mainFrm.setLocationRelativeTo(null);
        
      TimerTask task = new TimerTask() {
			@Override
			public void run() {
				// task to run goes here
				// 执行的输出的内容
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
                            mainFrm.rightStatus.setText(Msg.Msg00002.getMessage()+df.format(System.currentTimeMillis()));
			}
		};
		Timer timer = new Timer();
		// 定义开始等待时间  --- 等待 5 秒
		// 1000ms = 1s
		long delay = 0;
		// 定义每次执行的间隔时间
		long intevalPeriod = 1;
		// schedules the task to be run in an interval
		// 安排任务在一段时间内运行
		timer.scheduleAtFixedRate(task, delay, intevalPeriod);
                
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JDesktopPane jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel leftStatus;
    private javax.swing.JSeparator midStatus;
    private javax.swing.JLabel rightStatus;
    // End of variables declaration//GEN-END:variables
}
