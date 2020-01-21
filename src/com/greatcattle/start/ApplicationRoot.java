package com.greatcattle.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 应用程序入口
 * @author zhang.xian
 *
 */
public class ApplicationRoot  extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception {
																									// 和
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("com/greatcattle/view/main/mainframe.fxml"));// getClass().getClassLoader().getResource() // getClass().getResource()的区别，在不在根目录下
		primaryStage.setTitle("窗口测试--zx");
		Scene scene = new Scene(root, 1000, 600);
		primaryStage.setScene(scene);
		//scene.getStylesheets().add(getClass().getClassLoader().getResource("com/greatcattle/view/css/base/app.css").toExternalForm());  
		primaryStage.setResizable(true);// 窗体缩放（默认为true）   sdfgsdsdfgsdgf33
		primaryStage.show();  
	}
}
