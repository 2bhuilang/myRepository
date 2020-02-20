package com.greatcattle.components.drivers.camera.impl.stand;
  
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.examples.win32.W32API;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.NativeLongByReference;

/**
 * 标准摄像头 协议 
 * @author zhang.xian
 *
 */
public class StandCameraProtocol {
    
    public StandCameraProtocol(java.awt.Panel panelRealplay ){
        
        boolean initSuc = hCNetSDK.NET_DVR_Init();
        this.panelRealplay = panelRealplay;
        
        lUserID = new NativeLong(-1);
        lPreviewHandle = new NativeLong(-1);
        lAlarmHandle = new NativeLong(-1);
        lListenHandle = new NativeLong(-1);
        g_lVoiceHandle = new NativeLong(-1);
        m_lPort = new NativeLongByReference(new NativeLong(-1));
    }
    
    private java.awt.Panel panelRealplay;
     
    static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
    static PlayCtrl playControl = PlayCtrl.INSTANCE;
    
    public static NativeLong g_lVoiceHandle;//全局的语音对讲句柄
        
    HCNetSDK.NET_DVR_DEVICEINFO_V30 m_strDeviceInfo;//设备信息
    HCNetSDK.NET_DVR_IPPARACFG_V40  m_strIpparaCfg;//IP参数
    HCNetSDK.NET_DVR_CLIENTINFO m_strClientInfo;//用户参数

    boolean bRealPlay;//是否在预览.
    String m_sDeviceIP ="192.168.0.103";//已登录设备的IP地址

    NativeLong lUserID;//用户句柄
    NativeLong lPreviewHandle;//预览句柄
    NativeLongByReference m_lPort;//回调预览时播放库端口指针
    
    NativeLong lAlarmHandle;//报警布防句柄
    NativeLong lListenHandle;//报警监听句柄
     
    FRealDataCallBack2 fRealDataCallBack;//预览回调函数实现
     
    
    
    
    public long reg(){
        m_sDeviceIP = "192.168.0.103";//设备ip地址 
          hCNetSDK.NET_DVR_Logout_V30(new NativeLong(0));
         m_strDeviceInfo = new HCNetSDK.NET_DVR_DEVICEINFO_V30(); 
        lUserID = hCNetSDK.NET_DVR_Login_V30("192.168.0.103",
            (short) Integer.parseInt("8000"), "admin", new String("a12345678"), m_strDeviceInfo);

        long userID = lUserID.longValue();
        if (userID == -1)  {
             System.out.println("注册失败");
             return -1l;
        } 
        
        IntByReference ibrBytesReturned = new IntByReference(0);//获取IP接入配置参数
        boolean bRet = false;

        m_strIpparaCfg = new HCNetSDK.NET_DVR_IPPARACFG_V40();
        m_strIpparaCfg.write();
        Pointer lpIpParaConfig = m_strIpparaCfg.getPointer();
        bRet = hCNetSDK.NET_DVR_GetDVRConfig(lUserID, HCNetSDK.NET_DVR_GET_IPPARACFG_V40, new NativeLong(0), lpIpParaConfig, m_strIpparaCfg.size(), ibrBytesReturned);
        m_strIpparaCfg.read();
        return userID;
        
    }
    
    public void playView(){
         //获取窗口句柄
            W32API.HWND hwnd = new W32API.HWND(Native.getComponentPointer(panelRealplay));

            //获取通道号
            int iChannelNum = 1;//通道号
            if(iChannelNum == -1)  {
                 
                return;
            }

            m_strClientInfo = new HCNetSDK.NET_DVR_CLIENTINFO();
            m_strClientInfo.lChannel = new NativeLong(iChannelNum);

            //在此判断是否回调预览,0,不回调 1 回调 
            m_strClientInfo.hPlayWnd = hwnd;
            lPreviewHandle = hCNetSDK.NET_DVR_RealPlay_V30(lUserID,
                    m_strClientInfo, fRealDataCallBack, null, true);
            
    }
    
    
    
    
     
     /******************************************************************************
     *内部类:   FRealDataCallBack
     *          实现预览回调数据
     ******************************************************************************/
     class FRealDataCallBack2 implements HCNetSDK.FRealDataCallBack_V30{ 
         
         
        //预览回调
        public void invoke(NativeLong lRealHandle, int dwDataType, ByteByReference pBuffer, int dwBufSize, Pointer pUser)
        {
            W32API.HWND hwnd = new W32API.HWND(Native.getComponentPointer(panelRealplay));
            switch (dwDataType)
            {
                case HCNetSDK.NET_DVR_SYSHEAD: //系统头
                    
                    if (!playControl.PlayM4_GetPort(m_lPort)) //获取播放库未使用的通道号
                    {
                        break;
                    }

                    if (dwBufSize > 0)
                    {
                        if (!playControl.PlayM4_SetStreamOpenMode(m_lPort.getValue(), PlayCtrl.STREAME_REALTIME))  //设置实时流播放模式
                        {
                            break;
                        }

                        if (!playControl.PlayM4_OpenStream(m_lPort.getValue(), pBuffer, dwBufSize, 1024 * 1024)) //打开流接口
                        {
                            break;
                        }

                        if (!playControl.PlayM4_Play(m_lPort.getValue(), hwnd)) //播放开始
                        {
                            break;
                        }
                    }
                case HCNetSDK.NET_DVR_STREAMDATA:   //码流数据
                    if ((dwBufSize > 0) && (m_lPort.getValue().intValue() != -1))
                    {
                        if (!playControl.PlayM4_InputData(m_lPort.getValue(), pBuffer, dwBufSize))  //输入流数据
                        {
                            break;
                        }
                    }
            }
        }
    }
    
}
