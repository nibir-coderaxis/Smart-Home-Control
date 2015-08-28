/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplicationsmartroom;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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

/**
 *
 * @author Tazim
 */
public class JavaFXApplicationSmartRoom extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        final String user_name="user1";
        final String user_password="user1";
        primaryStage.setTitle("Smart Home");
        
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        
        Text scenetitle = new Text("Welcome To Smart Home");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        final TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        final PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);
        
        
        Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);
        
        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
          @Override
              public void handle(ActionEvent e) {
                  
                 if(userTextField.getText().equals(user_name) && pwBox.getText().equals(user_password))
                 {
                    actiontarget.setFill(Color.GREEN);
                    actiontarget.setText("Successfully Logged In!"); 
                    
                    Room1 r1 = new Room1();
                    r1.setVisible(true);
                    
                    WebUpdate w1 = new WebUpdate();
                    w1.setVisible(true);
                    
                    SendData sd = new SendData();
                    sd.setVisible(true);
                    
                    ((Node)(e.getSource())).getScene().getWindow().hide();
                    
                    //Window window = SwingUtilities.getWindowAncestor(this);
                    //window.setVisible( false );
                    // OR
                    //window.dispose();
                 }
                 else
                 {
                    if(!userTextField.getText().equals(user_name))
                    {
                       actiontarget.setFill(Color.RED);
                       actiontarget.setText("User Name Doesn't Exist!"); 
                    }
                    else
                    {
                       actiontarget.setFill(Color.RED);
                       actiontarget.setText("Wrong Password!"); 
                    }
                 }
                 
               
              }
        });
        
        
         Scene scene = new Scene(grid, 300, 275);
         primaryStage.setScene(scene);
        
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
