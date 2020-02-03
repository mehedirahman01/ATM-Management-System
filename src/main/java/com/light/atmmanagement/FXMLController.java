package com.light.atmmanagement;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class FXMLController implements Initializable {
    
    @FXML
    private Button loginButton;
    
    @FXML 
    private TextField accountNo;
    
    @FXML
    private PasswordField pin;
    
    @FXML
    private Button retypeLogin;
    
    @FXML
    private Button withdrawButton;
  
    @FXML
    private Button pinChangeButton;
    
    @FXML
    private Button transferButton;
    
    @FXML
    private Button balanceButton;
    
    @FXML
    private Button logoutButton;
    
    @FXML
    private TextField withdrawAmount;
    
    @FXML
    private PasswordField withdrawPin;
    
    @FXML
    private Button confirmWithdrawButton;
    
    @FXML
    private Button retypeWithdrawButton;
    
    @FXML
    private TextField toAccount;
    
    @FXML
    private TextField transferAmount;
    
    @FXML
    private PasswordField transferPin;
    
    @FXML
    private Button confirmTransferButton;
    
    @FXML
    private Button retypeTransferButton;
    
    @FXML
    private Button confirmPinButton;
    
    @FXML
    private TextField oldPin;
    
    @FXML
    private TextField newPin;
    
    @FXML
    private TextField confirmNewPin;
   
    @FXML
    public TextField balance;
    
    @FXML
    private Button backButton;
   
    
    


    static String tempAccount;
    static String toTempAccount;
    static int tempBalance;
    private accountsInfo ob;
    private int index;
    
    
    
    
    
    
                                                        /*LOGIN ACTION*/        
    //Login Function
    @FXML
    void loginAction(ActionEvent event) throws Exception {
        
        boolean isFound=authenticationLogin();
        
        if(isFound== true ){
        Parent home_root = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
        Scene home_scene = new Scene(home_root);
        home_scene.getStylesheets().add("/styles/Styles.css");
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setResizable(false);
        app_stage.setTitle("ATM SIMULATOR");
        app_stage.setScene(home_scene);
        app_stage.show();
        manageAccounts mga = new manageAccounts();
        accountsInfo[] obj = mga.accountInitialization();
        
        tempAccount=accountNo.getText();
        
        }
        
        else {
            AlertBox.infoBox("Wrong Username or Password", "Error");
            accountNo.setText("");
            pin.setText("");
            
        }
        
        }
        
    
    
    
    
    //Login Function For Enter Button 
    @FXML
     public void onEnter(ActionEvent ae) throws IOException{
         
        boolean isFound=authenticationLogin();
        
        if(isFound== true ){
        Parent home_root = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
        Scene home_scene = new Scene(home_root);
        home_scene.getStylesheets().add("/styles/Styles.css");
        Stage app_stage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
        app_stage.setTitle("ATM SIMULATOR");
        app_stage.setScene(home_scene);
        app_stage.show();
        tempAccount=accountNo.getText();
        }
        
        else {
            AlertBox.infoBox("Wrong Username or Password", "Error");
            accountNo.setText("");
            pin.setText("");
        }
        
        }
    
    
    
    
    //Checking Account No & Pin   
    public boolean authenticationLogin(){
            
        try{
            File f=new File("userInfo.txt");
                
            Scanner input=new Scanner(f);
                
            String i=accountNo.getText();
            String p=pin.getText();
                
            while(input.hasNextLine()==true){
                if((i.equals(input.nextLine())== true)&& (p.equals(input.nextLine())== true))
                    return true;
            }
                
            input.close();
        }
           
        catch(Exception e){
            System.out.println("File Error");
        }  
            
         return false;   
            
        }
    
   
       
    //Retype Button Funtion in Login 
    @FXML
    public void retypeLoginAction(ActionEvent ae) throws Exception{
            accountNo.setText("");
            pin.setText("");
    }
        
        
                                                    /*END OF LOGIN ACTION*/
     
                                                        

                                                           
    

                                                            /*MENU*/ 
    
    //Withdraw Button
    @FXML
    void withdrawMenu(ActionEvent event) throws Exception { 
        Parent home_root = FXMLLoader.load(getClass().getResource("/fxml/Withdraw.fxml"));
        Scene withdraw_scene = new Scene(home_root);
        withdraw_scene.getStylesheets().add("/styles/Styles.css");
        Stage withdraw_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        withdraw_stage.setTitle("ATM SIMULATOR");
        withdraw_stage.setScene(withdraw_scene);
        withdraw_stage.show();
    } 
       
       
    
    
    
    
    
    
    
    //Changing Pin button
    @FXML
    void changePinMenu(ActionEvent event) throws Exception { 
        Parent home_root = FXMLLoader.load(getClass().getResource("/fxml/PinChange.fxml"));
        Scene withdraw_scene = new Scene(home_root);
        withdraw_scene.getStylesheets().add("/styles/Styles.css");
        Stage withdraw_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        withdraw_stage.setTitle("ATM SIMULATOR");
        withdraw_stage.setScene(withdraw_scene);
        withdraw_stage.show();
    } 
    
    
    
    
    
    
    
     //Balance Button
    @FXML
    void showBalance(ActionEvent event) throws Exception {
        manageAccounts mga = new manageAccounts();
        accountsInfo[] obj = mga.accountInitialization();
        
        try{
            for(int i =0;i<obj.length;i++)
                {
		if(tempAccount.equals(obj[i].getAccountNo())){
                    AlertBox.infoBox("Your Balance is "+obj[i].getDeposit(),"Balance");
                
                }    
        }
        }
        
        catch(Exception s){
            System.out.println("Error");
	}
				
    }
            
        
    
    
    
    
       
       //Transfer Button
       @FXML
       void transferMenu(ActionEvent event) throws Exception { 
        Parent home_root = FXMLLoader.load(getClass().getResource("/fxml/Transfer.fxml"));
        Scene withdraw_scene = new Scene(home_root);
        withdraw_scene.getStylesheets().add("/styles/Styles.css");
        Stage withdraw_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        withdraw_stage.setTitle("ATM SIMULATOR");
        withdraw_stage.setScene(withdraw_scene);
        withdraw_stage.show();
       }
       
       
       
       
                                                    /*END OF MENU*/
       
      
       
       
       
                                               /*WITHDRAW ACTION*/
       
       
        //Withdraw Function
        @FXML
        void confirmWithdraw(ActionEvent event) throws Exception{
            manageAccounts mga = new manageAccounts();
            accountsInfo[] obj = mga.accountInitialization();
            accountsInfo ob=new accountsInfo();
            float oldBalance=0;
             
           boolean isFound=authenticationWithdraw();
        
            if(isFound== true ){
            try{
            for(int i=0;i<obj.length;i++)
                {
		if(tempAccount.equals(obj[i].getAccountNo())){
                    String wit=obj[i].getDeposit();
                    oldBalance=Float.parseFloat(wit);
                }    
        }
        }
                catch(Exception s){
		    System.out.println("Error");
		}
				
            float witBalance=Float.parseFloat(withdrawAmount.getText());
            
            if(witBalance>oldBalance){
                AlertBox.infoBox("You have not enough balance", "Error");
                withdrawAmount.setText("");
                withdrawPin.setText("");
            }
            
            else if(witBalance<0){
                AlertBox.infoBox("Please Enter Valid Amount", "Error");
                withdrawAmount.setText("");
                withdrawPin.setText("");
            }
            
            else{
                float newBalance=oldBalance-witBalance;
                String NewBalance = Float.toString(newBalance);
                String OldBalance = Float.toString(oldBalance);
		ob.setDeposit(NewBalance);
                fileModify("clientInfo.txt",OldBalance,NewBalance);
                AlertBox.infoBox("Your new account balance is "+ newBalance, "Operation Successful");
                withdrawAmount.setText("");
                withdrawPin.setText("");
            }
            
            }
        
            
        else {
            AlertBox.infoBox("Wrong Password", "Error");
              withdrawPin.setText("");
        }
            }

        
        
               
        //Checking Pin for Withdraw
        public boolean authenticationWithdraw(){
                
            try{
                File f=new File("userInfo.txt");
                
                Scanner input=new Scanner(f);
                
                String p=withdrawPin.getText();
                
                while(input.hasNextLine()==true){
                    if((tempAccount.equals(input.nextLine())==true && p.equals(input.nextLine())== true))
                        return true;
                }
                
                input.close();
        }
           
          catch(Exception e){
              System.out.println("File Error");
          }  
            
         return false;   

        }
    
        
        
        //Modifying FIle after Withdraw
        
         public void fileModify(String filePath,String oldString,String newString)
	{
		File f=new File(filePath);
                String oldContent="";
                BufferedReader reader=null;
                FileWriter writer=null;
                
            try{
                reader=new BufferedReader(new FileReader(f));
                String line=reader.readLine();
                while(line!=null){
                    oldContent=oldContent + line + System.lineSeparator();
                    line=reader.readLine();
                }
                
                String newContent = oldContent.replaceAll(oldString, newString);
                writer = new FileWriter(f);
             
                writer.write(newContent);
                writer.close();
        }
            catch (Exception e)
            {
            System.out.println(e.toString());
            }
        
        
    }
		
	
        
    //Retype Button in Withdraw
    @FXML
    public void retypeWithdraw(ActionEvent ae) throws Exception{
        withdrawAmount.setText("");
        withdrawPin.setText("");
    }
           
           
           
           
                                    /*END OF WITHDRAW ACTION*/
    
    
    
                                    /*PIN CHANGE ACTION*/
    
    //Pin Change Function   
    @FXML
    void confirmPinChange(ActionEvent event) throws Exception{
        boolean isFound=authenticationPinChange();
        if(isFound== true ){
            try{
                String n0=oldPin.getText();
                String n1=newPin.getText();
                String n2=confirmNewPin.getText();
                
                if(n1.equals(n2)){
                pinModify("clientInfo.txt",n0,n1);
                pinModify("userInfo.txt",n0,n1);
                oldPin.setText("");
                newPin.setText("");
                confirmNewPin.setText("");
                AlertBox.infoBox("Your Pin Changed","Operation Successful");
                }
                
                else{
                    oldPin.setText("");
                    newPin.setText("");
                    confirmNewPin.setText("");
                    AlertBox.infoBox("Pins doesn't match","Error");
                }
            
            
            }
            
            catch(Exception e){
              System.out.println("File Error");
          }  
            
            }
           
           
       }
       
       
    
    //Checking Pin for Pin Change
    public boolean authenticationPinChange(){
            try{
                File f=new File("userInfo.txt");
                
                Scanner input=new Scanner(f);
                
                String p=oldPin.getText();
                
                while(input.hasNextLine()==true){
                    if((tempAccount.equals(input.nextLine())==true && p.equals(input.nextLine())== true))
                        return true;
                }
                
                input.close();
        }
           
          catch(Exception e){
              System.out.println("File Error");
          }  
            
         return false;   
       }
    
    
    
    
    
    //Modifying File after Pin Change    
    public void pinModify(String filePath,String oldString,String newString)
	{
		File f=new File(filePath);
                String oldContent="";
                BufferedReader reader=null;
                FileWriter writer=null;
                
                try{
                reader=new BufferedReader(new FileReader(f));
                String line=reader.readLine();
                while(line!=null){
                    oldContent=oldContent + line + System.lineSeparator();
                    line=reader.readLine();
                }
                
                 String newContent = oldContent.replaceAll(oldString, newString);
                  writer = new FileWriter(f);
             
            writer.write(newContent);
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        
    }
	
       
       
       
                                   /*END OF PIN CHANGE ACTION*/
       
       
       
       
       
                                       /*TRANSFER ACTION*/
       
       //Transfer Function
       @FXML
        void confirmTransfer(ActionEvent event) throws Exception { 
                manageAccounts mga = new manageAccounts();
                accountsInfo[] obj = mga.accountInitialization();
                accountsInfo ob=new accountsInfo();
                float oldBalance=0;
                float oldBalancetoB=0;
                float newBalancetoB;
                float newBalancefromB;
                
               boolean isiFound=authenticationTransfer(); 
               boolean isFound=toUserAuthentication();
               if(isFound==false){
                   AlertBox.infoBox("Account Not Found", "Error");
                   transferPin.setText("");
                   toAccount.setText("");
                   transferAmount.setText("");
               }
               
               else if(isiFound==false){
                   AlertBox.infoBox("Pin not matched", "Error");
                   transferPin.setText("");
               }
               
               else if(isFound== true && isiFound==true ){
            try{
                 for(int i=0;i<obj.length;i++)
                {
		if(tempAccount.equals(obj[i].getAccountNo())){
                    String wit=obj[i].getDeposit();
                    oldBalance=Float.parseFloat(wit);
                }    
        }
            
            }
		
            
             catch(Exception e){
              System.out.println("File Error");
          } 
            
            float witBalance=Float.parseFloat(transferAmount.getText());
            
            if(witBalance>oldBalance){
                AlertBox.infoBox("You hsve not enough balance", "Error");
                transferAmount.setText("");
                transferPin.setText("");
            }
            
            else if(witBalance<0){
                AlertBox.infoBox("Please Enter Valid Amount", "Error");
                transferAmount.setText("");
                transferPin.setText("");
            }
            
            else{
              
        
                
                try{
                 for(int i=0;i<obj.length;i++)
                {
		if(toTempAccount.equals(obj[i].getAccountNo())){
                    String toB=obj[i].getDeposit();
                    oldBalancetoB=Float.parseFloat(toB);
                    
                }    
        }
            
            }
		
            
             catch(Exception e){
              System.out.println("File Error");
          } 
                
                
             newBalancetoB=oldBalancetoB+witBalance;
             newBalancefromB=oldBalance-witBalance;
            
             String newBalancetB = Float.toString( newBalancetoB);
             String newBalancefrmB = Float.toString( newBalancefromB);
             String OldBalance = Float.toString(oldBalance);
             String OldBalancetoB = Float.toString(oldBalancetoB);
             fileModify("clientInfo.txt",OldBalance,newBalancefrmB);
             fileModify("clientInfo.txt",OldBalancetoB,newBalancetB);
             AlertBox.infoBox("Transfer Completed. Your new balance is "+newBalancefromB, "Operation Successful");
             transferPin.setText("");
             toAccount.setText("");
             transferAmount.setText("");
             
            }
                
                
                
                
            }
            
            
            else{
                AlertBox.infoBox("Error Occured", "Error");
            }
       }
        
       
       //Check To Account 
       public boolean toUserAuthentication(){
           try{
                File f=new File("userInfo.txt");
                
                Scanner input=new Scanner(f);
                
                
                toTempAccount=toAccount.getText();
                
                if((toTempAccount.equals(tempAccount))==true){
                return false;
                }
                else{
                while(input.hasNextLine()==true){
                    if((toTempAccount.equals(input.nextLine())== true))
                        return true;
                }
                }
                
                input.close();
        }
           
          catch(Exception e){
              System.out.println(e.toString());
          }  
            
         return false;   
       }
       
       
       
       
        //Checking Pin for Transfer
        public boolean authenticationTransfer(){
                
            try{
                File f=new File("userInfo.txt");
                
                Scanner input=new Scanner(f);
                
                String p=transferPin.getText();
                
                while(input.hasNextLine()==true){
                    if((tempAccount.equals(input.nextLine())==true && p.equals(input.nextLine())== true))
                        return true;
                }
                
                input.close();
        }
           
          catch(Exception e){
              System.out.println(e.toString());
          }  
            
         return false;   

        }
       
    
        
    //Retype Button in Transfer    
    @FXML
    public void retypeTransfer(ActionEvent ae) throws Exception{
        toAccount.setText("");
        transferPin.setText("");
        transferAmount.setText("");
        
    }     
    
     
    
    
                                            /*END OF TRANSFER ACTION*/
    
    
    
    
    //Back Button Function
    @FXML
    void backButtonAction(ActionEvent event) throws Exception {
        Parent back_root = FXMLLoader.load(getClass().getResource("/fxml/Menu.fxml"));
        Scene back_scene = new Scene(back_root);
        back_scene.getStylesheets().add("/styles/Styles.css");
        Stage back_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        back_stage.setTitle("ATM SIMULATOR");
        back_stage.setScene(back_scene);
        back_stage.show();
        }    
         
         
   
    
    //LOGOUT Funtion
     @FXML
      void logoutAction(ActionEvent event) throws Exception {
        tempAccount="";
        Parent log_root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Scene scene = new Scene(log_root);
        scene.getStylesheets().add("/styles/Styles.css");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("ATM SIMULATOR");
        stage.setScene(scene);
        stage.show();
        }
    
      
      
      
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

  //  private PreparedStatement setString(int i, String tempAccount) {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
}
