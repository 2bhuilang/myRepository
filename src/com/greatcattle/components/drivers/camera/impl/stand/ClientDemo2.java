/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ClientDemo.java
 *
 * Created on 2009-9-14, 19:31:34
 */
/**
 *
 * @author Xubinfeng
 */

package com.greatcattle.components.drivers.camera.impl.stand;
 
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.examples.win32.W32API.HWND;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.NativeLongByReference;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JPopupMenu; 

/*****************************************************************************
 *主类 ：ClientDemo
 *用途 ：用户注册，预览，参数配置菜单
 *容器：Jframe
 ****************************************************************************/
public class ClientDemo2 extends javax.swing.JFrame
{
    /*************************************************
    函数:      主类构造函数
    函数描述:	初始化成员
     *************************************************/
    public ClientDemo2()
    {
        JPopupMenu.setDefaultLightWeightPopupEnabled(false);//防止被播放窗口(AWT组件)覆盖
        initComponents();
        initParams();
    }
    
    private void initParams(){
        lUserID = new NativeLong(-1);
        lPreviewHandle = new NativeLong(-1);  
        g_lVoiceHandle = new NativeLong(-1);
        m_lPort = new NativeLongByReference(new NativeLong(-1)); 
    }

    static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE; 

    public static NativeLong g_lVoiceHandle;//全局的语音对讲句柄 
    HCNetSDK.NET_DVR_DEVICEINFO_V30 m_strDeviceInfo;//设备信息
    HCNetSDK.NET_DVR_IPPARACFG_V40  m_strIpparaCfg;//IP参数
    HCNetSDK.NET_DVR_CLIENTINFO m_strClientInfo;//用户参数
  
    NativeLong lUserID;//用户句柄
    NativeLong lPreviewHandle;//预览句柄
    NativeLongByReference m_lPort;//回调预览时播放库端口指针
      
     
  

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonLogin = new javax.swing.JButton();
        panelRealplay = new java.awt.Panel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ClientDemo");
        setFont(new java.awt.Font("宋体", 0, 10)); // NOI18N

        jButtonLogin.setText("注册");
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRealplayLayout = new javax.swing.GroupLayout(panelRealplay);
        panelRealplay.setLayout(panelRealplayLayout);
        panelRealplayLayout.setHorizontalGroup(
            panelRealplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 777, Short.MAX_VALUE)
        );
        panelRealplayLayout.setVerticalGroup(
            panelRealplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 365, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(jButtonLogin)
                .addContainerGap(975, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(174, 174, 174)
                    .addComponent(panelRealplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(175, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(322, Short.MAX_VALUE)
                .addComponent(jButtonLogin)
                .addGap(75, 75, 75))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(29, 29, 29)
                    .addComponent(panelRealplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(30, 30, 30)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 
    /*************************************************
    函数:      "注册"  按钮单击响应函数
    函数描述:	注册登录设备
     *************************************************/
    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionPerformed
        
            play2();
         
    }//GEN-LAST:event_jButtonLoginActionPerformed
 
    
     /*************************************************
    函数:      "预览"  按钮单击响应函数
    函数描述:	获取通道号,打开播放窗口,开始此通道的预览
     *************************************************/
    private void play2()                                                
    {    
        
        
        m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30();
        //        lUserID = hCNetSDK.NET_DVR_Login_V30(m_sDeviceIP,
            //                (short) iPort, jTextFieldUserName.getText(), new String(jPasswordFieldPassword.getPassword()), m_strDeviceInfo);
        
       // lUserID = hCNetSDK.NET_DVR_Login_V30(sDVRIP, wDVRPort, sUserName, sPassword, lpDeviceInfo)
        int userId = hCNetSDK.NET_DVR_Login_V30("192.168.0.103", 
            Short.parseShort("8000")
            , "admin", new String("a12345678"), m_strDeviceInfo);
        lUserID = new NativeLong(userId);

        long userID = lUserID.longValue();
        if (userID == -1)
        { 
            System.out.println("zhuceshibai");
        }
        
        System.out.println(panelRealplay.getWidth());
        System.out.println(panelRealplay.getHeight());
        if (lUserID.intValue() == -1)
        {
            JOptionPane.showMessageDialog(this, "请先注册");
            return;
        } 
        //获取窗口句柄
        HWND hwnd = new HWND(Native.getComponentPointer(panelRealplay)); 
        //获取通道号
        int iChannelNum = 1;//通道号 

        m_strClientInfo = new HCNetSDK.NET_DVR_CLIENTINFO();
        m_strClientInfo.lChannel = iChannelNum ;//new NativeLong(iChannelNum);

        //在此判断是否回调预览,0,不回调 1 回调

        m_strClientInfo.hPlayWnd = hwnd;
		/*
		 * lPreviewHandle = hCNetSDK.NET_DVR_RealPlay_V30( (int)lUserID.intValue(),
		 * m_strClientInfo, null, null, true);
		 */
        long previewSucValue = lPreviewHandle.longValue();

        //预览失败时:
        if (previewSucValue == -1)
        {
            JOptionPane.showMessageDialog(this, "预览失败");
            return;
        }
  
}           
    
    
     
  

  /*************************************************
    函数:       主函数
    函数描述:新建ClientDemo窗体并调用接口初始化SDK
     *************************************************/
    public static void main(String args[])
    {
        try
        {
          javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(new Runnable()
        {

            public void run()
            {
                boolean initSuc = hCNetSDK.NET_DVR_Init();
                if (initSuc != true)
                {
                    JOptionPane.showMessageDialog(null, "初始化失败");
                }
                ClientDemo2 Demo = new ClientDemo2();
             
                Demo.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JPanel jPanel1;
    private java.awt.Panel panelRealplay;
    // End of variables declaration//GEN-END:variables

  
 
     
        
}

