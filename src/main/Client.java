package main;



import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.view.MarketController;
import main.view.MarketPlaceController;
import main.view.SeeBonusController;
import main.view.SeeShopsController;
import main.view.StartGameTimeController;
import main.view.ValidConnectionController;
import model.basics.Board;
import model.basics.Match;
import mud.model.basic.interfaces.MatchRequest;
import main.view.SeeBonusController;
import model.basics.Gamer;
import model.market.Agent;


public class Client extends Application {
    private static Stage primaryStage;
    private static BorderPane rootLayout;
  
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Council of four");

        initRootLayout();
        showPlayGame();
        
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Client.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setHeight(720);
            primaryStage.setWidth(1255);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    /**
     * Shows the person overview inside the root layout.
     */
    public void showPlayGame() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Client.class.getResource("view/PlayGame.fxml"));
            AnchorPane playGame = (AnchorPane) loader.load();
            

            // Set person overview into the center of root layout.
            rootLayout.setCenter(playGame);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

      public static void showSelectConnection() {
        try {
            // 
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Client.class.getResource("view/SelectConnection.fxml"));
            AnchorPane selection = (AnchorPane) loader.load();
            

            // Set person overview into the center of root layout.
           rootLayout.setCenter(selection);
            } catch (IOException e) {
            	e.printStackTrace();
               }
    }  

     
     public static void showMatchGame(){
    	 
             try {
              
           	FXMLLoader loader = new FXMLLoader();
                 loader.setLocation(Client.class.getResource("view/MatchGame.fxml"));
                 AnchorPane matchGame = (AnchorPane) loader.load();
                 

                 // Set person overview into the center of root layout.
                rootLayout.setCenter(matchGame);
                
          
                
                 } catch (IOException e) {
                 	e.printStackTrace();
                    }
         }
           
     public static void showMessageStartGameTime() {
     	
         try {
         	FXMLLoader loader = new FXMLLoader();
             loader.setLocation(Client.class.getResource("view/StartGameTime.fxml"));
             AnchorPane valid = (AnchorPane) loader.load();
             
             Stage startGameTimeStage = new Stage();
             startGameTimeStage.setTitle("Connessione");
             startGameTimeStage.initModality(Modality.WINDOW_MODAL);
             startGameTimeStage.initOwner(primaryStage);
             Scene scene = new Scene(valid);
             startGameTimeStage.setScene(scene);
           
             
             StartGameTimeController controller = loader.getController();  //new
             controller.setStartGameTimeStage(startGameTimeStage);					//new
             
             startGameTimeStage.showAndWait();
             
            } catch (IOException e) {
             e.printStackTrace();
         }             
  } 
     public static void showMessageConnection() {
        	
            try {
            	FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Client.class.getResource("view/ValidConnection.fxml"));
                AnchorPane valid = (AnchorPane) loader.load();
                
                Stage validDialogStage = new Stage();
                validDialogStage.setTitle("Connessione");
                validDialogStage.initModality(Modality.WINDOW_MODAL);
                validDialogStage.initOwner(primaryStage);
                Scene scene = new Scene(valid);
                validDialogStage.setScene(scene);
              
                
                ValidConnectionController controller = loader.getController();  //new
                controller.setValidDialogStage(validDialogStage);		
                
              
               
                //new
                
                validDialogStage.showAndWait();
                
               } catch (IOException e) {
                e.printStackTrace();
               }  
            }     
            
            public void showShopPlayers(String village) {
            	
                try {
                	FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(Client.class.getResource("view/SeeShops.fxml"));
                    AnchorPane valid = (AnchorPane) loader.load();
                    
                    Stage seeShopsStage = new Stage();
                    seeShopsStage.setTitle("Shops in Village");
                    seeShopsStage.initModality(Modality.WINDOW_MODAL);
                    seeShopsStage.initOwner(primaryStage);
                    Scene scene = new Scene(valid);
                    seeShopsStage.setScene(scene);
                  
                  
                    SeeShopsController controller = loader.getController();  
                    controller.setSeeShopsStage(seeShopsStage);					
                    
                    controller.setTextShopsPlayer(village);
                    
                    
                    seeShopsStage.showAndWait();
                    
                   } catch (IOException e) {
                    e.printStackTrace();
                }                
    	}
            
            public void showBonusVillage(int village) { 	
            	
             	
                try {
                	
                	FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(Client.class.getResource("view/SeeBonus.fxml"));
                    AnchorPane valid = (AnchorPane) loader.load();
                    
                    Stage seeBonusVillageStage = new Stage();
                    seeBonusVillageStage.setTitle("Bonus Village");
                    seeBonusVillageStage.initModality(Modality.WINDOW_MODAL);
                    seeBonusVillageStage.initOwner(primaryStage);
                    Scene scene = new Scene(valid);
                    seeBonusVillageStage.setScene(scene);
                  
                    // istanzio controller
                    
                    SeeBonusController controller = loader.getController();  //new
                    controller.setSeeBonusVillageStage(seeBonusVillageStage);					//new
            
                    controller.seeBonusVillage(village);
                    seeBonusVillageStage.showAndWait();
                    
                    
                    
                    
                    
                   } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
            
            public void showBonusPermitCard(int region,int card) {
             	
                try {
                	
                	FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(Client.class.getResource("view/SeeBonus.fxml"));
                    AnchorPane valid = (AnchorPane) loader.load();
                    
                    Stage seeBonusVillageStage = new Stage();
                    seeBonusVillageStage.setTitle("Bonus");
                    seeBonusVillageStage.initModality(Modality.WINDOW_MODAL);
                    seeBonusVillageStage.initOwner(primaryStage);
                    Scene scene = new Scene(valid);
                    seeBonusVillageStage.setScene(scene);
                  
                    // istanzio controller
                    
                    SeeBonusController controller = loader.getController();  //new
                    controller.setSeeBonusVillageStage(seeBonusVillageStage);					//new
                   
              
                    controller.seeBonusPermitCard(region,card);
   
                    seeBonusVillageStage.showAndWait();
                  
                   } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
            
            
            public void showBonusPermitCardGamer(int card) {
             	
                try {
                	
                	FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(Client.class.getResource("view/SeeBonus.fxml"));
                    AnchorPane valid = (AnchorPane) loader.load();
                    
                    Stage seeBonusVillageStage = new Stage();
                    seeBonusVillageStage.setTitle("Bonus");
                    seeBonusVillageStage.initModality(Modality.WINDOW_MODAL);
                    seeBonusVillageStage.initOwner(primaryStage);
                    Scene scene = new Scene(valid);
                    seeBonusVillageStage.setScene(scene);
                  
                    // istanzio controller
                    
                    SeeBonusController controller = loader.getController();  //new
                    controller.setSeeBonusVillageStage(seeBonusVillageStage);					//new
                   
              
                    controller.seeBonusPermitCardGamer(card);
   
                    seeBonusVillageStage.showAndWait();
                  
                   } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
   
            public void showBonusUsedPermitCardGamer(int card) {             	
                      try {
                      	
                      	FXMLLoader loader = new FXMLLoader();
                          loader.setLocation(Client.class.getResource("view/SeeBonus.fxml"));
                          AnchorPane valid = (AnchorPane) loader.load();
                          
                          Stage seeBonusVillageStage = new Stage();
                          seeBonusVillageStage.setTitle("Bonus Village");
                          seeBonusVillageStage.initModality(Modality.WINDOW_MODAL);
                          seeBonusVillageStage.initOwner(primaryStage);
                          Scene scene = new Scene(valid);
                          seeBonusVillageStage.setScene(scene);
                        
                          // istanzio controller
                          
                          SeeBonusController controller = loader.getController();  //new
                          controller.setSeeBonusVillageStage(seeBonusVillageStage);					//new
                  
                          controller.seeBonusUsedPermitCardGamer(card);
                          
                          seeBonusVillageStage.showAndWait();
                
                         } catch (IOException e) {
                          e.printStackTrace();
                      } 
                  }
            
            
            
            public void showBonusNobilityPath(int number) { 	
            	try {
                     	
            		FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(Client.class.getResource("view/SeeBonus.fxml"));
                    AnchorPane valid = (AnchorPane) loader.load();
                         
                    Stage seeBonusVillageStage = new Stage();
                    seeBonusVillageStage.setTitle("Bonus");
                    seeBonusVillageStage.initModality(Modality.WINDOW_MODAL);
                    seeBonusVillageStage.initOwner(primaryStage);
                    Scene scene = new Scene(valid);
                    seeBonusVillageStage.setScene(scene);
                       
                    // istanzio controller
                         
                    SeeBonusController controller = loader.getController();  //new
                    controller.setSeeBonusVillageStage(seeBonusVillageStage);					//new
                 
                    controller.seeBonusNobilityPath(number);
                    
                    seeBonusVillageStage.showAndWait();
               
                        } catch (IOException e) {
                         e.printStackTrace();
                     } 
                 }
            
            
    public void showSaleMarket(Gamer g) { 	
            	try {
                     	
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(Client.class.getResource("view/SaleMarket.fxml"));
                    AnchorPane valid = (AnchorPane) loader.load();
                         
                    Stage seeMarketStage = new Stage();
                    seeMarketStage.setTitle("SaleMarket");
                    seeMarketStage.initModality(Modality.WINDOW_MODAL);
                    seeMarketStage.initOwner(primaryStage);
                    Scene scene = new Scene(valid);
                    seeMarketStage.setScene(scene);
                       
                    // istanzio controller
                         
                    MarketController controller = loader.getController();  //new
                    controller.setSeeMarketStage(seeMarketStage);				//new
                 
                    controller.setGamer(g);
                    
                    seeMarketStage.showAndWait();
               
                        } catch (IOException e) {
                         e.printStackTrace();
                     } 
                 }
    
    
//     public void showMarketSelectionToSell(Agent agent) { 	
//            	try {
//                     	
//                    FXMLLoader loader = new FXMLLoader();
//                    loader.setLocation(Client.class.getResource("view/SeeMarketSelectionToSell.fxml"));
//                    AnchorPane valid = (AnchorPane) loader.load();
//                         
//                    Stage seeMarketSelectionToSellStage = new Stage();
//                    seeMarketSelectionToSellStage.setTitle("Selction to sell");
//                    seeMarketSelectionToSellStage.initModality(Modality.WINDOW_MODAL);
//                    seeMarketSelectionToSellStage.initOwner(primaryStage);
//                    Scene scene = new Scene(valid);
//                    seeMarketSelectionToSellStage.setScene(scene);
//                       
//                    // istanzio controller
//                         
//                    MarketController controller = loader.getController();  //new
//                    controller.setSeeMarketStage(primaryStage);				//new
//                 
//                    controller.setGamer(g);
//                    
//                    seeMarketSelectionToSellStage.showAndWait();
//               
//                        } catch (IOException e) {
//                         e.printStackTrace();
//                     } 
//                 }
//               
           
    
    
    
        public void showMarketPlace(Agent agent) { 	
            	try {
                     	
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(Client.class.getResource("view/MarketPlace.fxml"));
                    AnchorPane valid = (AnchorPane) loader.load();
                         
                    Stage seeMarketPlaceStage = new Stage();
                    seeMarketPlaceStage.setTitle("MarketPlace");
                    seeMarketPlaceStage.initModality(Modality.WINDOW_MODAL);
                    seeMarketPlaceStage.initOwner(primaryStage);
                    Scene scene = new Scene(valid);
                    seeMarketPlaceStage.setScene(scene);
                       
                    // istanzio controller
                         
                    MarketPlaceController controller = loader.getController();  //new
                    controller.setSeeMarketPlaceStage(seeMarketPlaceStage);				//new
                 
                    controller.initializee(agent);
                    
                    seeMarketPlaceStage.showAndWait();
               
                        } catch (IOException e) {
                         e.printStackTrace();
                     } 
                 }
    /**
     * Returns the main stage.
     * @return
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
	