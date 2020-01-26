package com.greatcattle.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * @author zhang.xian
 *
 */
public class ApplicationRoot  extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
																							
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("com/greatcattle/view/main/mainframe.fxml"));// getClass().getClassLoader().getResource() // getClass().getResource()�������ڲ��ڸ�Ŀ¼��
		primaryStage.setTitle("窗口测试--zx");
		Scene scene = new Scene(root, 1000, 600);
		primaryStage.setScene(scene);
		
		//scene.getStylesheets().add(getClass().getClassLoader().getResource("com/greatcattle/view/css/base/app.css").toExternalForm());  
		primaryStage.setResizable(true);//
		primaryStage.show();  
	}
}
