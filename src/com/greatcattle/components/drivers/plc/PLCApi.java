package com.greatcattle.components.drivers.plc;

public interface PLCApi {
	 
	  public void loadConfig();
	  
	  public void connect();
	  
	  public void disConnect();
	  
	  public void reConnect();
	  
	  public void upPreGate();
	  
	  public void downPreGate();
	  
	  public void upSuffixGate();
	  
	  public void downSuffixGate();
	
}
