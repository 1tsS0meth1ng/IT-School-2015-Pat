package com.main.java;

import java.io.IOException;
import java.sql.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author User1
 */
public class Login extends Application 
{
    private Object event;
    @Override
    public void start(Stage primaryStage) 
    {
        primaryStage.setResizable(false);
        primaryStage.setTitle("JavaFX Welcome");
            
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,25,25,25));
        
        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        
        Text sceneTitle = new Text("Welcome");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        sceneTitle.setId("welcome-text");
        grid.add(sceneTitle, 0, 0, 2, 1);
        
        Label usrlbl = new Label("User ID:");
        grid.add(usrlbl, 0, 1);
        
        Label passlbl = new Label("Password");
        grid.add(passlbl, 0, 2);
        
        TextField idText = new TextField();
        grid.add(idText, 1, 1);
        
        PasswordField passField = new PasswordField();
        grid.add(passField, 1, 2);
        
        final Text actionTarget = new Text();
            grid.add(actionTarget, 1, 3);
        
        Button lginbtn = new Button("Login");
            lginbtn.setOnAction((ActionEvent e) -> {
                String loginuser = idText.getText();
                String pass = passField.getText();
                int access = compareLogin(loginuser, pass);
                if(access == 1)
                {
                    Parent root;
                    
                    try{
                        root = FXMLLoader.load(getClass().getClassLoader().getResource("Test.fxml"));
                        Stage stage = new Stage();
                        stage.setTitle("My New Stage Title");
                        stage.setScene(new Scene(root, 450, 450));
                        stage.show();
                        
                        ((Node)(e.getSource())).getScene().getWindow().hide();
                    }
                    catch(IOException e2)
                    {
                        System.out.println(e);
                    }
                    
                }
        });        
       
        HBox hbbtn = new HBox(10);
        hbbtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbbtn.getChildren().add(lginbtn);
        grid.add(hbbtn, 1, 4);
        
        scene.getStylesheets().add("com/main/resources/Css/LoginSkin.css");
        
        primaryStage.show();
    }
    	
    protected static Connection getSQLConnection()
    {
	Connection con = null;
	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost/pat";
		String user = "Brendan";
		String password ="02Ruffies";
		con = DriverManager.getConnection(url, user, password);
	} 
	catch(ClassNotFoundException e2)
	{
		e2.getException();
		System.out.println(e2.getMessage()+" failed");
		System.exit(0);
	}
	catch (SQLException e1) 
	{
		
		System.out.println(e1.getMessage());
		System.exit(0);
	}
	return con;
    }
	
    public static int compareLogin(String userlog, String userpass)
    {
	Connection con = getSQLConnection();
	boolean login = false;
	try
	{
		Statement s = con.createStatement();
		String select = "SELECT * FROM EMPLOYEES;";
		ResultSet rows;
		rows = s.executeQuery(select);
		while(rows.next() && login == false)
		{
			if(userlog.equalsIgnoreCase(null)|| userpass.equalsIgnoreCase(null))
                        {
                            return 0;   
                        }    
                        String EmployeeID = rows.getString("EmployeeID");
			if(EmployeeID.equalsIgnoreCase(userlog))
			{
				String Password =rows.getString("Password");
				if(Password.equals(userpass))
				{					
                                        String Status = rows.getString("Status");
                                        if(Status.equalsIgnoreCase("terminated"))
                                        {
                                            return 2;
                                        }
                                        login = true;
				}
			}
		}
	}
	catch(SQLException e)
	{
		System.out.println(e.getErrorCode());
		System.exit(0);
		return 3;
	}
	if(login == true)
	{
		return 1;
	}
	else
	{
		return 0;
	}
		
    }
    
   
            
    public static void main(String[] args) 
    {
        Login.launch(args);
        
    }
}
