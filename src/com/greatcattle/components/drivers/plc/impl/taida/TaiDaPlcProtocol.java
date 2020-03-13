package com.greatcattle.components.drivers.plc.impl.taida;

/**
 * 台达协议
 * @author zhang.xian
 *
 */
public class TaiDaPlcProtocol {
	
	//升起前栏杆
	public static final String UP_PRE_GATE_COMMAND ="3A30313035303530303030303046350D0A";
	//关闭前栏杆
	public static final String DOWN_PRE_GATE_COMMAND ="3A30313035303530313030303046340D0A";
	
	//升起后栏杆
	public static final String UP_SUFFIX_GATE_COMMAND ="3A30313035303530323030303046330D0A";
	//关闭后栏杆
	public static final String DOWN_SUFFIX_GATE_COMMAND ="3A30313035303530333030303046320D0A";
	 
	
}
