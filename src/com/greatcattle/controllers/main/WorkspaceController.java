package com.greatcattle.controllers.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * zhu kong
 * 
 * @author Administrator
 *
 */
public class WorkspaceController implements Initializable {
	
	
	@FXML
    private StackPane containerPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		AnchorPane pane = null;
		try {
			pane = FXMLLoader.load(getClass().getClassLoader().getResource("com/greatcattle/view/main/mainframe.fxml")); 
		} catch (IOException e) { 
		}

		containerPane.getChildren().addAll(pane);

	}
}
