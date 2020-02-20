package com.greatcattle.components.drivers.camera.impl.stand;

/**
 * 标准摄像头配置
 * @author zhang.xian
 *
 */
public class StandCameraConfig {
	
	
	private String ip ="";
	private String port = "";
        private String userName = "";
        private String password = "";
	 
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        } 
}
