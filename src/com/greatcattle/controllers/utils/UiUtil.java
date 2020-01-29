package com.greatcattle.controllers.utils;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

public class UiUtil {
	 
	
	public static Node loadView(String viewPath) {
		
		
		Parent root =null;
		try {
			root = FXMLLoader.load(UiUtil.class.getClassLoader().getResource(viewPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// getClass().getClassLoader().getResource() // getClass().getResource()�������ڲ��ڸ�Ŀ¼��
		
		return root;
	} 
	
}
