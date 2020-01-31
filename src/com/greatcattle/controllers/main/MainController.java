package com.greatcattle.controllers.main;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.greatcattle.controllers.utils.UiUtil;

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
import javafx.scene.layout.AnchorPane;
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
    private BorderPane workSpacePane;
    
    @FXML
    private AnchorPane workCenterPane;
 
    @Override
    public void initialize(URL location, ResourceBundle resources) { 
    	 
    	
        menuBar.prefWidthProperty().bind(pane.widthProperty());//��Ȱ�ΪPane��� ss
        workSpacePane.prefWidthProperty().bind( pane.widthProperty());  
        workSpacePane.prefHeightProperty().bind( pane.heightProperty());
        this.initWorkspace();
        
        
       
    }
    
    /**
     * 初始化工作区
     */
    private  void initWorkspace() {
    	
    	Pane viewRoot = (Pane)UiUtil.loadView("com/greatcattle/view/main/workspace.fxml");
    	workCenterPane.getChildren().add(viewRoot); 
		viewRoot.prefWidthProperty().bind( workCenterPane.widthProperty() );
		viewRoot.prefHeightProperty().bind( workCenterPane.heightProperty() );
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
    private void openWorkFlow(ActionEvent event){
		/*
		 * Stage stage=new Stage(); InnerDynContanner con =
		 * InnerViewFactory.createInnnerCon(stage); workCenterPane.getChildren().add(
		 * con.getRoot());
		 */
    	
		 
    	
		/*
		 * Stage dialog = new Stage() ; dialog.initModality(Modality.WINDOW_MODAL);
		 * 
		 * //dialog.initOwner(primaryStage); VBox dialogVbox = new VBox(20);
		 * dialogVbox.getChildren().add( new Text("This is a Dialog")); Scene
		 * dialogScene = new Scene(dialogVbox,300,200); dialog.setScene(dialogScene);
		 * dialog.show();
		 */
    	 
    }
    
    
    @FXML 
    private void closeAction(ActionEvent event){//
    	 System.out.println("invoke close menu action");
    	 Alert alert = new Alert(AlertType.CONFIRMATION);
    	 alert.setTitle("确认关闭"); 
    	 alert.setContentText("确认关闭"); 
    	 Optional<ButtonType> result = alert.showAndWait();
    	  
    	 System.out.println(result.get().getButtonData().getTypeCode());
    	 if("O".equals( result.get().getButtonData().getTypeCode())) {
    		 System.exit(0);
    	 } 
    } 
}
