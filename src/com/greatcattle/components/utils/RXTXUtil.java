package com.greatcattle.components.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TooManyListenersException;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;




/**
 * 使用rxtx连接串口工具类
 */
public class RXTXUtil {
    
    private static final String DEMONAME = "串口测试";

    /**
     * 检测系统中可用的端口
     */
    private CommPortIdentifier portId;
    /**
     * 获得系统可用的端口名称列表
     */
    private static Enumeration<CommPortIdentifier> portList;
    /**
     * 输入流
     */
    private static InputStream inputStream;
    /**
     * RS-232的串行口
     */
    private static SerialPort serialPort;
    /**
     * 返回结果
     */
    private static String res=null;
    /**
     * 串口命令执行
     * @param order 命令
     * @param portName 端口名
     * @param baudRate 波特率
     * @return
     * @throws UnsupportedEncodingException
     */
    public synchronized  static String executeOrder(String order,String portName,int baudRate)  {
        String str="";
        if (serialPort==null) {
            openSerialPort(portName, baudRate);
        }
        //发送消息
        sendData(order);
        //代替监听
        /*for (int i = 0; i <1000; i++) {
            str=readData();
            
            if (str!=null){
                return str;
            }else {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }*/
        //设置监听
        setListenerToSerialPort( new SerialPortEventListener(){
            @Override
            public void serialEvent(SerialPortEvent serialPortEvent) {
                if(serialPortEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {//数据通知
                    String str=readData();
                    //res=returnCheck(order,str);
                }
            }
        });

        //监听是异步请求，如果要获取监听里的内容做返回值，
        // 可以通过循环去延迟来获取返回值，
        // 设置最大延迟防止死循环
        long startTime = System.currentTimeMillis();
        while (res==null){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long endTime = System.currentTimeMillis();
            if ((endTime-startTime)/1000.0>20){//最长20s返回
                return res;
            }
        }
        return res;
    }

    


    /**
     * 获得系统可用的端口名称列表
     * @return 可用端口名称列表
     */
    @SuppressWarnings("unchecked")
    public static void getSystemPort(){
        List<String> systemPorts = new ArrayList<>();
        //获得系统可用的端口
        portList = CommPortIdentifier.getPortIdentifiers();
        while(portList.hasMoreElements()) {
            String portName = portList.nextElement().getName();//获得端口的名字
            systemPorts.add(portName);
        }

    }

    /**
     * 开启串口
     * @param serialPortName 串口名称
     * @param baudRate 波特率
     * @return 串口对象
     */
    public static void openSerialPort(String serialPortName,int baudRate) {
        try {
            //通过端口名称得到端口
            CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(serialPortName);
            //打开端口，（自定义名字，打开超时时间）
            CommPort commPort = portIdentifier.open(serialPortName, 5000);
            //判断是不是串口
            if (commPort instanceof SerialPort) {
                serialPort = (SerialPort) commPort;
                //设置串口参数（波特率，数据位8，停止位1，校验位无）
                serialPort.setSerialPortParams(baudRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                //    System.out.println("开启串口成功，串口名称："+serialPortName);
            }else {
                //是其他类型的端口
                throw new NoSuchPortException();
            }
        } catch (NoSuchPortException e) {
            e.printStackTrace();
        } catch (PortInUseException e) {
            e.printStackTrace();
        } catch (UnsupportedCommOperationException e) {
            e.printStackTrace();
        }

    }

    /**
     * 向串口发送数据
     * @param order 发送的命令
     */
    public static void sendData( String order) {
        //16进制表示的字符串转换为字节数组
        byte[] data =HexadecimalUtil.hexStringToByteArray(order);
        OutputStream os = null;
        try {
            os = serialPort.getOutputStream();//获得串口的输出流
            os.write(data);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
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
    }

    /**
     * 从串口读取数据
     * @return 读取的数据
     */
    public static String  readData() {
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
     * 关闭串口
     *
     */
    public static void closeSerialPort() {
        if(serialPort != null) {
            serialPort.close();
            //System.out.println("关闭了串口："+serialPort.getName());
            serialPort = null;
        }
    }

    /**
     * 给串口设置监听
     * @param listener
     */
    public static void setListenerToSerialPort( SerialPortEventListener listener) {
        try {
            //给串口添加事件监听
            serialPort.addEventListener(listener);
        } catch (TooManyListenersException e) {
            e.printStackTrace();
        }
        serialPort.notifyOnDataAvailable(true);//串口有数据监听
        serialPort.notifyOnBreakInterrupt(true);//中断事件监听

    }
    
	public static void main(String[] args) throws Exception { // 通过端口名称得到端口
		String serialPortName = "COM3";

		try {
			
			getSystemPort();
			CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(serialPortName);
			// 打开端口，（自定义名字，打开超时时间）
			CommPort commPort = portIdentifier.open(serialPortName, 5000);
			// 判断是不是串口
			if (commPort instanceof SerialPort) {
				serialPort = (SerialPort) commPort;
				// 设置串口参数（波特率，数据位8，停止位1，校验位无）
				serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
						SerialPort.PARITY_NONE);
				// System.out.println("开启串口成功，串口名称："+serialPortName);
				inputStream = serialPort.getInputStream();

				System.out.println();
			} else {
				// 是其他类型的端口
				throw new NoSuchPortException();
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (serialPort != null) {
				serialPort.close();
			}
		}}
}