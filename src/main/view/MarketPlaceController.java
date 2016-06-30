/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.view;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.basics.Gamer;
import model.basics.PermitCard;
import model.basics.PoliticalCard;
import model.market.Agent;
import model.market.ItemHelpers;
import model.market.ItemPermitCard;
import model.market.ItemPoliticalCard;


public class MarketPlaceController {
    
    private Stage seeMarketPlaceStage;
        
    @FXML Button prevHelpersMP;
    @FXML Button nextHelpersMP;
    @FXML Button prevPermitCardMP;
    @FXML Button nextPermitCardMP;
    @FXML Button selectHelpersButtonMP;
    @FXML Button selectSellerButton;
    @FXML Button prevAgent;
    @FXML Button nextAgent;
    @FXML Label  helpersMPLabel;
    @FXML Label  priceLabelMP1;
    @FXML Label  priceLabelMP2;
    @FXML Label  playerLabelMP;
    @FXML Label  playerLabelMP2;
    @FXML Label  nameAgent; 
    @FXML Label  villagePermitCardMP;
    @FXML SplitPane splitPaneMP;
    
    private ArrayList<Agent> arrayAgent = new ArrayList<Agent>();
    private ArrayList<ItemPermitCard> permitCardItemArray = new ArrayList<ItemPermitCard> ();
    private ArrayList<ItemPoliticalCard> politicalCardItemAgentArray = new ArrayList<ItemPoliticalCard> ();
    private ArrayList<ItemHelpers> helperItemArray = new ArrayList<ItemHelpers>();
    private Agent agent;
    private int indexHelpers = -1;
    private int indexPermitCard = -1;
    private int indexAgent = -1;
    private ItemHelpers selectedHelpers;
    private String usernameSeller;
     
     
    //private Market market;
  
//    
//    private void initialize(Market market) {
//            this.market = market;             
//    arrayAgent = market.getArrayAgent;
//	}
//  
    @FXML
    public void initializee(Agent agent){
        arrayAgent.add(agent);
       // splitPaneMP.set
       
    
    }
    
    
//    public void addAgentStock(){
//        for(Agent a: arrayAgent){
//            helperItemArray.addAll(a.getArrayListItemHelpers());
//            permitCardItemArray.addAll(a.getArrayListItemPermitCard());
//            permitCardItemArray.addAll(a.getArrayListItemPermitCard());
//            politicalCardItemArray.addAll(a.getArrayListItemPoliticalCard());
//        }    
//    }
    
    
    @FXML 
    public void changeAgent(ActionEvent e){
        if((e.getSource() == nextAgent) == true){
            nextAgentMP();
        }
        
        if((e.getSource() == prevAgent) == true){
            prevAgentMP();
        }
        
        
        
    
    
    }
    
    @FXML 
    public void changeHelpersMP(ActionEvent e){
        
        if((e.getSource() == prevHelpersMP) == true){
            prevHelpersMP();
        
        }
        
        if((e.getSource() == nextHelpersMP) == true){
        
            nextHelpersMP();
        }
    
    
    }
    
        
    @FXML 
    public void changePermitCardMP(ActionEvent e){
        
        if((e.getSource() == prevPermitCardMP) == true){
            prevPermitCardMP();
        
        }
        
        if((e.getSource() == nextPermitCardMP) == true){
        
            nextPermitCardMP();
        }
    
    
    }
    
   public void nextAgentMP(){
			
	if (indexAgent < (arrayAgent.size())-1) {
                            
            if(indexAgent == -1){
                indexAgent = 0;
            }
            
            else{
                indexAgent++;
            }    	
	}
          
          
          agent = arrayAgent.get(indexAgent);
          System.out.println("" + agent.getGamer().getUsername());
          setTextLabelAgent();
    }
   
   public void prevAgentMP(){
       if (indexAgent > 0) {                  
            indexAgent--; 
	}
        agent = arrayAgent.get(indexAgent);      
    }




    
    public void nextHelpersMP(){
			
	if (indexHelpers < (agent.getArrayListItemHelpers().size())-1) {
                            
            if(indexHelpers == -1){
                indexHelpers = 0;
            }
            
            else{
                indexHelpers++;
            }    	
	}
        
          selectedHelpers = null;// = indexHelpers;
          setTextLabelHelpers(agent,indexHelpers);
    }
   
   public void prevHelpersMP(){
       if (indexHelpers > 0) {                  
          indexHelpers--; 
	}
        
        setTextLabelHelpers(agent, indexHelpers);
        selectedHelpers = null; // = indexHelpers;       
    }
   
   
   public void setTextLabelHelpers(Agent agent, int indexHelpers){
       
        helpersMPLabel.setText("" + agent.getArrayListItemHelpers().get(indexHelpers).getHelpers());
        priceLabelMP1.setText("" + agent.getArrayListItemHelpers().get(indexHelpers).getPrice());
        playerLabelMP.setText("" + agent.getGamer().getUsername());
   
   }
   
  
    
    public void nextPermitCardMP(){
			
	if (indexPermitCard < (agent.getArrayListItemPermitCard().size())-1) {
                            
            if(indexPermitCard == -1){
                indexPermitCard = 0;
            }
            
            else{
                indexPermitCard++;
            }    	
	}
        
         // selectedHelpers = null;// = indexHelpers;
          setTextLabelPermitCard(agent,indexPermitCard);
    }
   
   public void prevPermitCardMP(){
       if (indexPermitCard > 0) {                  
          indexPermitCard--; 
	}
        
        setTextLabelPermitCard(agent, indexPermitCard);
        //selectedHelpers = null; // = indexHelpers;       
    }
   
   
   public void setTextLabelPermitCard(Agent agent, int indexPermitCard){
       
        villagePermitCardMP.setText("" + agent.getArrayListItemPermitCard().get(indexPermitCard).getPermitCard().getVillages());
        priceLabelMP2.setText("" + agent.getArrayListItemPermitCard().get(indexPermitCard).getPrice());
        playerLabelMP2.setText("" + agent.getGamer().getUsername());
   
   }
   
      public void setTextLabelAgent(){
      
       nameAgent.setText("" + agent.getGamer().getUsername());
   
   }
      
      
    //LOOK
//    private void selectPoliticalCard( ArrayList<ImageView>  imgsPoliticalCards) {
//
//		politicalCardItemAgentArray = gamer.getPoliticalCards();
//
//		for (PoliticalCard card : politicalCardGamer) {
//
//			if (card.getColor().equals(java.awt.Color.CYAN)) {
//				Image image = new Image("main/view/image/cartaPoliticaCiano.png");
//				ImageView politicalCard = new ImageView(image);
//				imgsPoliticalCards.add(politicalCard);
//			}
//
//			if (card.getColor().equals(java.awt.Color.BLACK)) {
//				Image image = new Image("main/view/image/cartaPoliticaNera.png");
//				ImageView politicalCard = new ImageView(image);
//				imgsPoliticalCards.add(politicalCard);
//			}
//
//			if (card.getColor().equals(java.awt.Color.PINK)) {
//				Image image = new Image("main/view/image/cartaPoliticaRosa.png");
//				ImageView politicalCard = new ImageView(image);
//				imgsPoliticalCards.add(politicalCard);
//			}
//
//			if (card.getColor().equals(java.awt.Color.ORANGE)) {
//				Image image = new Image("main/view/image/cartaPoliticaArancio.png");
//				ImageView politicalCard = new ImageView(image);
//				imgsPoliticalCards.add(politicalCard);
//			}
//
//			if (card.getColor().equals(java.awt.Color.WHITE)) {
//				Image image = new Image("main/view/image/cartaPoliticaBianca.png");
//				ImageView politicalCard = new ImageView(image);
//				imgsPoliticalCards.add(politicalCard);
//			}
//
//			if (card.getColor().equals(java.awt.Color.MAGENTA)) {
//				Image image = new Image("main/view/image/cartaPoliticaMagenta.png");
//				ImageView politicalCard = new ImageView(image);
//				imgsPoliticalCards.add(politicalCard);
//			}
//
//			if (card.getJolly() == true) {
//				Image image = new Image("main/view/image/cartaPoliticaJolly.png");
//				ImageView politicalCard = new ImageView(image);
//				imgsPoliticalCards.add(politicalCard);
//			}
//		}
//	} 
//   
   
     @FXML
    public void selectSeller(ActionEvent e){
        
        if((e.getSource() == selectSellerButton) == true){
            if(agent != null){
           // splitPaneMP.setVisible(true);
            usernameSeller = agent.getGamer().getUsername();
            }
            
            else{   //errore
            
            }
        }
    
    
    }
    
    
    
//    @FXML
//    public void selectedItemMP(ActionEvent e){
//        
//        if((e.getSource() == selectHelpersButtonMP) == true){
//        
//        
//        }
//    
//    
//    }
//    
    
    @FXML
    public void setSeeMarketPlaceStage(Stage seeMarketPlaceStage) {
	this.seeMarketPlaceStage = seeMarketPlaceStage;
	
    }
    
    
}
