/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.greatcattle.components.drivers.camera.impl.stand;

import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.examples.win32.W32API;
import com.sun.jna.ptr.ByteByReference;
import com.sun.jna.ptr.NativeLongByReference;
import com.sun.jna.win32.StdCallLibrary;

/**
 *
 * @author Administrator
 */
//播放库函数声明,PlayCtrl.dll
public  interface PlayCtrl extends StdCallLibrary {
	PlayCtrl INSTANCE = (PlayCtrl) Native.loadLibrary("E:\\e\\rb\\dev64lib\\PlayCtrl.dll", PlayCtrl.class);

	// 如果SDK库加载不成功，也可以使用绝对路径测试看看，如下所示
	// PlayCtrl INSTANCE = (PlayCtrl)
	// Native.loadLibrary("D:\\lib\\PlayCtrl.dll",PlayCtrl.class);

	public static final int STREAME_REALTIME = 0;
	public static final int STREAME_FILE = 1;

	boolean PlayM4_GetPort(NativeLongByReference nPort);

	boolean PlayM4_OpenStream(NativeLong nPort, ByteByReference pFileHeadBuf, int nSize, int nBufPoolSize);

	boolean PlayM4_InputData(NativeLong nPort, ByteByReference pBuf, int nSize);

	boolean PlayM4_CloseStream(NativeLong nPort);

	boolean PlayM4_SetStreamOpenMode(NativeLong nPort, int nMode);

	boolean PlayM4_Play(NativeLong nPort, W32API.HWND hWnd);

	boolean PlayM4_Stop(NativeLong nPort);

	boolean PlayM4_SetSecretKey(NativeLong nPort, NativeLong lKeyType, String pSecretKey, NativeLong lKeyLen);
}
