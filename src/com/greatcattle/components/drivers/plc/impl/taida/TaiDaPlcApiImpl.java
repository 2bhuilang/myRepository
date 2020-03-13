package com.greatcattle.components.drivers.plc.impl.taida;

import com.greatcattle.components.common.com.ComCommand;
import com.greatcattle.components.common.com.ComConfig;
import com.greatcattle.components.common.com.ComPortFactory;
import com.greatcattle.components.common.com.ComPortInst;
import com.greatcattle.components.drivers.plc.PLCApi;
import com.greatcattle.framework.utils.SpringUtils;

/**
 * 
 * @author zhang.xian
 *
 */
public class TaiDaPlcApiImpl implements PLCApi{

	private ComPortInst  comPortInst = null ;
	private ComConfig comConfig  = null ;
	
	
	@Override
	public void connect() {
		ComConfig comConfig  = SpringUtils.getBean("plcComConfig");
		comPortInst = ComPortFactory.getComPortFactory().loadComPortInst(comConfig);
		
	}

	@Override
	public void disConnect() {
		comPortInst.closeComPort(); 
	}

	@Override
	public void reConnect() {
		ComConfig comConfig  = SpringUtils.getBean("plcComConfig");
		comPortInst = ComPortFactory.getComPortFactory().reloadComPortInst(comConfig);
		
	}

	@Override
	public void upPreGate() {
		ComCommand  command = new ComCommand();
		command.setCommandHex(TaiDaPlcProtocol.UP_PRE_GATE_COMMAND );
		comPortInst.sendCommand(command); 
		
	}

	@Override
	public void downPreGate() {
		
		ComCommand  command = new ComCommand();
		command.setCommandHex(TaiDaPlcProtocol.DOWN_PRE_GATE_COMMAND );
		comPortInst.sendCommand(command); 
	}

	@Override
	public void upSuffixGate() {
		
		ComCommand  command = new ComCommand();
		command.setCommandHex(TaiDaPlcProtocol.UP_SUFFIX_GATE_COMMAND );
		comPortInst.sendCommand(command); 
		
	}

	@Override
	public void downSuffixGate() {
		
		ComCommand  command = new ComCommand();
		command.setCommandHex(TaiDaPlcProtocol.UP_SUFFIX_GATE_COMMAND );
		comPortInst.sendCommand(command);
		
	}

	@Override
	public void loadConfig() {
		
		comConfig  = SpringUtils.getBean("plcComConfig");
		
	}
	 
	 

}
