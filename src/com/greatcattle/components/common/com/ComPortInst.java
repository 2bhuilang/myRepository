package com.greatcattle.components.common.com;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TooManyListenersException;

import com.greatcattle.components.common.com.listener.ListenerCallBack;
import com.greatcattle.components.utils.HexadecimalUtil;
import com.greatcattle.components.utils.RXTXUtil;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

/**
 * 串口实例
 * @author zhang.xian
 *
 */
public class ComPortInst {
	
	private SerialPort serialPort;
	private ComConfig comConfig = null ;
	private String comPortName = "";
	
	private InputStream inputStream = null ;
	
	public  void init(ComConfig comConfig) {
		this.comConfig = comConfig;
	}
	 
	/**
	 * 反送串口指令
	 * @param command
	 * @return
	 */
	public ComCommandResult sendCommand(ComCommand command) {
		
		  //16进制表示的字符串转换为字节数组
        byte[] data =HexadecimalUtil.hexStringToByteArray(command.getCommandHex());
        OutputStream os = null;
        try {
            os = serialPort.getOutputStream();//获得串口的输出流
            os.write(data);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            //关闭流操作
            try {
                if (os != null) {
                    os.close();
                    os = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
		return null;
	}
	
	
	
	/**
	 * 设置监听
	 * @param listener
	 */
	public void setListenerCallBack(ListenerCallBack callBack) {
		
			SerialPortEventListener listener =  new SerialPortEventListener(){
	            @Override
	            public void serialEvent(SerialPortEvent serialPortEvent) {
	                if(serialPortEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {//数据通知
	                    String str=readData();
	                    callBack.callBack(str); 
	                }
	            }
	        };
		 
		  try {
			  	//移除之前的监听
			  	serialPort.removeEventListener();
	            //给串口添加事件监听
	            serialPort.addEventListener(listener); 
	        } catch (TooManyListenersException e) {
	            e.printStackTrace();
	        }
	        serialPort.notifyOnDataAvailable(true);//串口有数据监听
	        serialPort.notifyOnBreakInterrupt(true);//中断事件监听
	}
	
	
	/**
	 * 读取串口下位数据
	 * @return
	 */
	 public  String  readData() {
	        //保存串口返回信息
	        StringBuffer res=new StringBuffer(40);
	        InputStream is = null;
	        byte[] bytes = null;
	        try {
	            is = serialPort.getInputStream();//获得串口的输入流
	            int bufflenth = is.available();//获得数据长度
	            while (bufflenth != 0) {
	                bytes = new byte[bufflenth];//初始化byte数组
	                is.read(bytes);
	                bufflenth = is.available();
	            }
	            if(bytes!=null) {
	                for (int i = 0; i < bytes.length; i++) {
	                    //转换成16进制数（FF）
	                    res.append(HexadecimalUtil.get16NumAdd0((bytes[i]&0xff)+"",2));
	                }
	            }
	            System.out.println("res: "+res.toString());
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (is != null) {
	                    is.close();
	                    is = null;
	                }
	            } catch(IOException e) {
	                e.printStackTrace();
	            }
	        } 
	        return res.toString();
	    }
	
	
	
	/**
	 * 打开串口端口
	 * @return
	 * @throws Exception 
	 */
	public boolean openComPort() throws Exception {
		
		RXTXUtil.getSystemPort();
		
		CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(this.comConfig.getPortName());
		// 打开端口，（自定义名字，打开超时时间）
		CommPort commPort = portIdentifier.open(this.comConfig.getPortName(), 5000);
		// 判断是不是串口
		if (commPort instanceof SerialPort) {
			serialPort = (SerialPort) commPort;
			// 设置串口参数（波特率，数据位8，停止位1，校验位无） 
			serialPort.setSerialPortParams(comConfig.getBitRate(), comConfig.getDatabits(), comConfig.getStopbits(),
					comConfig.getParity()); 
			//inputStream = serialPort.getInputStream(); 
			 
		} else {
			// 是其他类型的端口
			throw new NoSuchPortException();
		}
		
		return false;
		
	}
	
	
	
	/**
	 * 关闭串口
	 * @return
	 */
	public boolean closeComPort() {
		if(serialPort!=null) {
			serialPort.close();
			return true;
		}
		return false;
	}



	public String getComPortName() {
		return comPortName;
	}
 
	public void setComPortName(String comPortName) {
		this.comPortName = comPortName;
	} 
	
}
