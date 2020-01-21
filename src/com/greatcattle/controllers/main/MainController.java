package com.greatcattle.controllers.main;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * zhu kong
 * @author Administrator
 *
 */
public class MainController  implements Initializable {
    @FXML
    private Pane pane;
    
    @FXML
    private MenuBar menuBar;
    
    @FXML
    private BorderPane quickShortPane;
 
    @Override
    public void initialize(URL location, ResourceBundle resources) { 
    	
        menuBar.prefWidthProperty().bind(pane.widthProperty());//宽度绑定为Pane宽度
        quickShortPane.prefWidthProperty().bind( pane.widthProperty());  
        quickShortPane.setPrefHeight(pane.getPrefHeight()-menuBar.getPrefHeight() );
       
    }
    
    @FXML 
    private void addMenu(ActionEvent event){
    	
    	MenuItem item = new MenuItem();
    	item.setText("test");
    	item.setMnemonicParsing(false); 
    	item.setOnAction(new EventHandler() { 
			@Override
			public void handle(Event event) {
				  System.out.println( ( (MenuItem)event.getSource()).getText());
			} 
    	});
    	this.menuBar.getMenus().get(1).getItems().add( item); 
    }
    
    
    @FXML 
    private void closeAction(ActionEvent event){//函数名必需与fxml中的一致 System.out.println("invoke close menu action");
    	 System.out.println("invoke close menu action");
    	 Alert alert = new Alert(AlertType.CONFIRMATION);
    	 alert.setTitle("是否确认");
    	 //alert.setHeaderText("是否确认");
    	 alert.setContentText("是否确认退出系统"); 
    	 Optional<ButtonType> result = alert.showAndWait();
    	  
    	 System.out.println(result.get().getButtonData().getTypeCode());
    	 if("O".equals( result.get().getButtonData().getTypeCode())) {
    		 System.exit(0);
    	 } 
    } 
}
