/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.view;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.ClientLogic;
import main.view.custom.ImageViewPosition;
import model.basics.Gamer;
import model.basics.PermitCard;
import model.basics.PoliticalCard;
import model.market.Agent;
import model.market.HelpersItem;
import model.market.PermitCardItem;
import model.market.PoliticalCardItem;


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
    @FXML Label  priceLabelMP3;
    @FXML Label  playerLabelMP;
    @FXML Label  playerLabelMP2;
    @FXML Label  playerLabelMP3;
    @FXML Label  nameAgent; 
    @FXML Label  villagePermitCardMP;
    @FXML SplitPane splitPaneMP;
    @FXML AnchorPane anchorPoliticalCardMP;
    
    private ArrayList<Agent> arrayAgent = new ArrayList<Agent>();
    private ArrayList<PermitCardItem> permitCardItemArray = new ArrayList<PermitCardItem> ();
    private ArrayList<PoliticalCardItem> politicalCardItemAgentArray = new ArrayList<PoliticalCardItem> ();
    private ArrayList<HelpersItem> helperItemArray = new ArrayList<HelpersItem>();
    private ArrayList<Integer> positionPoliticalCards;
    private ArrayList<ImageView> imgsPoliticalCards;
    private Agent agent;
    private int indexHelpers = -1;
    private int indexPermitCard = -1;
    private int indexAgent = -1;
    private HelpersItem selectedHelpers;
    private String usernameSeller;
    private HBox hbPoliticalCardsStock;
    private int selectedPoliticalCard = -1;
    private int maxSelectionPoliticalCard = 0;
     
     
    //private Market market;
  
//    
//    private void initialize(Market market) {
//            this.market = market;             
//    arrayAgent = market.getArrayAgent;
//	}
//  
    @FXML
    public void initializee(Agent agent){
        
       // arrayAgent = ClientLogic.getInstance().getMarket().getAgents();
        arrayAgent.add(agent);
        splitPaneMP.setDisable(true);
       
       
    
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
			
	if (indexAgent < (arrayAgent.size())-1 && arrayAgent.size()>0 ) {
                            
            if(indexAgent == -1){
                indexAgent = 0;
            }
            
            else{
                indexAgent++;
            }    	
	}
          
          
       agent = arrayAgent.get(indexAgent);
       if(agent.getPoliticalCardStock().size()>0){
            showPoliticalCardMP();
        }
          
        System.out.println("" + agent.getGamer().getUsername());
        setTextLabelAgent();
    }
   
   public void prevAgentMP(){
       
       
       if (indexAgent > 0  && arrayAgent.size()>0) {                  
            indexAgent--; 
	}
        agent = arrayAgent.get(indexAgent);      
    }




    
    public void nextHelpersMP(){
	
        if( agent.getHelpersStock().size()>0){
            if (indexHelpers < (agent.getHelpersStock().size())-1) {
                            
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
    }
   
   public void prevHelpersMP(){
       if(agent.getHelpersStock().size()>0){
            if (indexHelpers >0){                  
                indexHelpers--; 
            }
        
            setTextLabelHelpers(agent, indexHelpers);
            selectedHelpers = null; // = indexHelpers; 
       }
    }
   
   
   public void setTextLabelHelpers(Agent agent, int indexHelpers){
       
        helpersMPLabel.setText("" + agent.getHelpersStock().get(indexHelpers).getHelpers());
        priceLabelMP1.setText("" + agent.getHelpersStock().get(indexHelpers).getPrice());
        playerLabelMP.setText("" + agent.getGamer().getUsername());
   
   }
   
  
    
    public void nextPermitCardMP(){
        
	if (indexPermitCard < (agent.getPermitCardStock().size())-1) {
                            
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
       
        villagePermitCardMP.setText("" + agent.getPermitCardStock().get(indexPermitCard).getPermitCard().getVillages());
        priceLabelMP2.setText("" + agent.getPermitCardStock().get(indexPermitCard).getPrice());
        playerLabelMP2.setText("" + agent.getGamer().getUsername());
    }
        
    public void setTextLabelPoliticalCard(Agent agent, int indexPoliticalCard){
      
        priceLabelMP3.setText("" + agent.getPoliticalCardStock().get(indexPoliticalCard).getPrice());
        playerLabelMP3.setText("" + agent.getGamer().getUsername());
   
   }
   
      public void setTextLabelAgent(){
      
       nameAgent.setText("" + agent.getGamer().getUsername());
   
   }
      
    private void showPoliticalCardMP() {
		
                positionPoliticalCards = new ArrayList<Integer>();
		imgsPoliticalCards = new ArrayList<ImageView>();
                selectPoliticalCard();

		hbPoliticalCardsStock = new HBox();
		hbPoliticalCardsStock.setPadding(new Insets(0, 100, 10, 10));
		hbPoliticalCardsStock.setSpacing(5);

		hbPoliticalCardsStock.getChildren().addAll(imgsPoliticalCards);

		anchorPoliticalCardMP.getChildren().addAll(hbPoliticalCardsStock);

		AnchorPane.setBottomAnchor(hbPoliticalCardsStock, 0.0);
		AnchorPane.setLeftAnchor(hbPoliticalCardsStock, 1.0);
                selectionPoliticalCard();
	}  
    public void selectionPoliticalCard() {
                
                
		for (int i = 0; i < imgsPoliticalCards.size(); i++) {

			int j = i;
                        
			imgsPoliticalCards.get(j).setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					//if (maxSelectionPoliticalCard < imgsPoliticalCards.size()) {
                                                setEffectImageNull();
                                                GaussianBlur g = new GaussianBlur(5);
						imgsPoliticalCards.get(j).setEffect(g);
						selectedPoliticalCard =  positionPoliticalCards.get(j);
                                                setTextLabelPoliticalCard(agent, j);
                                                System.out.println("ho salvato selectpoli:  " + selectedPoliticalCard);
                                                //        = politicalCardGamer.get(j);
						maxSelectionPoliticalCard++;
					//}
				}
			});
		}
	}
    
    public void setEffectImageNull(){
            for (ImageView image: imgsPoliticalCards) {     
                image.setEffect(null);
            }
    
    
    
    }
    
    
    private void selectPoliticalCard(){

            politicalCardItemAgentArray = agent.getPoliticalCardStock();
            
            if(politicalCardItemAgentArray.size() != 0){
		for (PoliticalCardItem card : politicalCardItemAgentArray) {

			if (card.getPoliticalCard().getColor().equals(java.awt.Color.CYAN)) {
				Image image = new Image("main/view/image/cartaPoliticaCiano.png");
				ImageView politicalCard = new ImageView(image);
                                imgsPoliticalCards.add(politicalCard);
                                positionPoliticalCards.add(card.getPosition());
                              
			}

			if (card.getPoliticalCard().getColor().equals(java.awt.Color.BLACK)) {
				Image image = new Image("main/view/image/cartaPoliticaNera.png");
				ImageView politicalCard = new ImageView(image);
                                System.out.println("cardBlackPosition" + card.getPosition());
                                imgsPoliticalCards.add(politicalCard);
                                 positionPoliticalCards.add(card.getPosition());
			}

			if (card.getPoliticalCard().getColor().equals(java.awt.Color.PINK)) {
				Image image = new Image("main/view/image/cartaPoliticaRosa.png");
				ImageView politicalCard = new ImageView(image);
                                imgsPoliticalCards.add(politicalCard);
                                positionPoliticalCards.add(card.getPosition());
			}

			if (card.getPoliticalCard().getColor().equals(java.awt.Color.ORANGE)) {
				Image image = new Image("main/view/image/cartaPoliticaArancio.png");
				ImageView politicalCard = new ImageView(image);
                                imgsPoliticalCards.add(politicalCard);
                                positionPoliticalCards.add(card.getPosition());
			}

			if (card.getPoliticalCard().getColor().equals(java.awt.Color.WHITE)) {
				Image image = new Image("main/view/image/cartaPoliticaBianca.png");
				ImageView politicalCard = new ImageView(image);
                                imgsPoliticalCards.add(politicalCard);
                                 positionPoliticalCards.add(card.getPosition());
			}

			if (card.getPoliticalCard().getColor().equals(java.awt.Color.MAGENTA)) {
				Image image = new Image("main/view/image/cartaPoliticaMagenta.png");
				ImageView politicalCard = new ImageView(image);
                                imgsPoliticalCards.add(politicalCard);
                                 positionPoliticalCards.add(card.getPosition());
			}

			if (card.getPoliticalCard().getJolly() == true) {
				Image image = new Image("main/view/image/cartaPoliticaJolly.png");
				ImageView politicalCard = new ImageView(image);
                                imgsPoliticalCards.add(politicalCard);
                                positionPoliticalCards.add(card.getPosition());
			}
		}
            }
	} 
   
   
     @FXML
    public void selectSeller(ActionEvent e){
        
        if((e.getSource() == selectSellerButton) == true){
            if(agent != null){
           splitPaneMP.setDisable(false);
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
