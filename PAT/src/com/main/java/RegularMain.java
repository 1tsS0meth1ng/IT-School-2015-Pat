package com.main.java;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RegularMain extends Application {
    
    
    @Override
    public void start(Stage primaryStage) 
    {       
       primaryStage.setTitle("Time Manager");
       
       BorderPane brdrPane = new BorderPane();
       
       Scene scene = new Scene(brdrPane, 1600, 1200);
       primaryStage.setScene(scene);
        
        MenuBar menuBar = new MenuBar();
           
            Menu menuFile = new Menu("file");
                MenuItem fileSave = new Menu("Save");
                MenuItem fileOpen = new Menu("Open");         
            menuFile.getItems().addAll(fileSave,fileOpen);
            
        menuBar.getMenus().addAll(menuFile);
        brdrPane.setTop(menuBar);
       
        scene.getStylesheets().add("com/main/resources/Css/MainSkin.css");
        
        primaryStage.show();       
    }
    public static void main(String[] args) 
    {
        
        launch(args);
    }
    
}
