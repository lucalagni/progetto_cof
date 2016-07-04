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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.Client;
import main.ClientLogic;
import main.view.custom.NumberTextField;
import main.view.custom.Operation;
import model.basics.Gamer;
import model.basics.PermitCard;
import model.basics.PoliticalCard;
import model.market.Agent;
import model.market.HelpersItem;
import model.market.PermitCardItem;
import model.market.PoliticalCardItem;

/**
 *
 * @author Antonietta
 */
public class MarketController {

    private Stage seeMarketStage;
    private Gamer gamer;
    private HelpersItem helpersItem;
    private PermitCardItem  permitCardItem;
    private PoliticalCardItem politicalCardItem;
 
   
    
    @FXML Label  helpersGamerLabel;
    @FXML Label  villagePermitCardGamerLabelM;
    @FXML Button plusHelpers;
    @FXML Button minusHelpers;
    @FXML Button selectPermitCardButton;
    @FXML Button selectHelpersButton;
    @FXML Button nextPermitCardGamerMButton;
    @FXML Button prevPermitCardGamerMButton;
    @FXML Button selectPoliticalCardButton;
    @FXML Button endSelectionToSell;
    @FXML Button seeSelection;
    @FXML NumberTextField priceHelpersTextField;
    @FXML NumberTextField pricePermitCardTextField;
    @FXML NumberTextField pricePoliticalCardTextField;
    @FXML AnchorPane anchorPoliticalCardM;

    private int selectedHelpers = 0;
   
    private int helpersGamer = 0;
    //int priceHelpers;
    private int pricePermitCard = 0;
    private int indexHelpers = 0;
    private int indexPermitCardGamer = -1;
    private int selectedPermitCard = -1;
    private int selectedPoliticalCard = -1;
    private int maxSelectionPoliticalCard = 0;
    private ArrayList<PermitCard> unusedPermitCardsGamer = new ArrayList<PermitCard> ();
    private ArrayList<PoliticalCard> politicalCardGamer = new ArrayList<PoliticalCard> ();
    private ArrayList<ImageView> imgsPoliticalCards;
    private HBox hbPoliticalCardGamer;
    private Agent agentGamer;
    private Operation op = new Operation();
  
     
    public void setGamer(Gamer gamer){
  
        //CHIEDI DANTE SE QUESTO COPIA IL RIFERIMENTO O SOLO IL VALORE 
        this.gamer = gamer;
        helpersGamer = gamer.getHelpers(); 
        unusedPermitCardsGamer = gamer.getUnusedPermitCards();
        agentGamer = new Agent(gamer);
        showPoliticalCardGamer();
       
        
    }
     
       
    @FXML 
    public void operationHelpers(ActionEvent e){
     
        
        if((e.getSource() == plusHelpers) == true){
            plusHelpersOp();
        }
        
        if((e.getSource() == minusHelpers) == true){
            minusHelpersOp();  
        }
    }
    
    
   
    public void plusHelpersOp() {
        
        
        if(indexHelpers>=0 && indexHelpers < helpersGamer){
           indexHelpers++;
        }
        
         helpersGamerLabel.setText("" + indexHelpers);
         selectedHelpers = indexHelpers;
           System.out.println("" + indexHelpers);
    }
    
    public void minusHelpersOp(){

         if(indexHelpers > 0 && indexHelpers <= helpersGamer){
           --indexHelpers;     
        }
         
          selectedHelpers = indexHelpers;
          helpersGamerLabel.setText("" + indexHelpers);
          System.out.println("" + indexHelpers);
    }
   
    
   
//   public void setTextUnusedPermitCardVillageGamer(int selectPermitCard, Label villagePermitCardGamer) {
//
//	if (selectPermitCard <unusedPermitCardsGamer.size()) {
//            villagePermitCardGamer.setText("" + unusedPermitCardsGamer.get(selectPermitCard).getVillages());
//            } 
//        
//        else selectPermitCard = unusedPermitCardsGamer.size();
//        
//}
   
  
    @FXML
    public void changePermitCard(ActionEvent e){
        
        if((e.getSource() == nextPermitCardGamerMButton) == true){
            selectedPermitCard = op.nextPermitCard(selectedPermitCard,unusedPermitCardsGamer, villagePermitCardGamerLabelM);
        }
        
        if((e.getSource() == prevPermitCardGamerMButton) == true){
            selectedPermitCard = op.prevPermitCard(selectedPermitCard, unusedPermitCardsGamer, villagePermitCardGamerLabelM);    
        }

    }
    
    @FXML
   public void selectedItem(ActionEvent e){
       // se preme il pulsante per selezionare gli aiutanti da vendere
       if((e.getSource() == selectHelpersButton) == true){
          // manda un messaggio di informazione che non è stato inserito un prezzo o  il numero di aiutanti diverso da zero
          //int priceHelpers = Integer.parseInt(priceHelpersTextField.getText());
          
          if(priceHelpersTextField.getText().trim().isEmpty() || selectedHelpers == 0 ){ 
              alertStageNoCorrectInput(0);  
            }
          // se il campo prezzo non è vuoto e il numero di aiutanti è maggiore di 0 allora
          
          if((priceHelpersTextField.getText().trim().isEmpty())== false  && helpersGamer>0){
              
              //aggiunge all'itemHelpers il numero di aiutanti selezionati e il relativo prezzo 
              
              helpersItem = new HelpersItem(selectedHelpers, Integer.parseInt(priceHelpersTextField.getText()));
              
              //li aggiunge nell'array di itemHelpers dell'agente
              agentGamer.addHelpersItem(helpersItem);
              
              // decrementa il numero totale di aiutanti in base a quelli selezionati     
              helpersGamer = helpersGamer - selectedHelpers;
              
               //resetta gli indici a zero e resetta la label che mostra il numero di aiutanti
              selectedHelpers = 0;
              indexHelpers = 0;
              helpersGamerLabel.setText("" + indexHelpers);
            }    
        }
       
       
          if((e.getSource() == selectPermitCardButton) == true){
           
                if(pricePermitCardTextField.getText().trim().isEmpty() || selectedPermitCard == -1){
                        alertStageNoCorrectInput(1);  
                }
             
       
                if((pricePermitCardTextField.getText().trim().isEmpty()) == false  && selectedPermitCard>=0){
                    
                    if(isPermitCardSelected(unusedPermitCardsGamer.get(selectedPermitCard)) == true){
                        alertStageNoCorrectInput(2);
                    
                    }
                }
            
                    
                if((isPermitCardSelected(unusedPermitCardsGamer.get(selectedPermitCard))) == false){
                        
                    System.out.println("" + selectedPermitCard);
                        
                    //aggiunge all'itemHelpers il numero di aiutanti selezionati e il relativo prezzo 
                    permitCardItem = new PermitCardItem(unusedPermitCardsGamer.get(selectedPermitCard),
                                                             Integer.parseInt(pricePermitCardTextField.getText()),
                                                             selectedPermitCard);
                       
                    //li aggiunge nell'array di itemHelpers dell'agente
                    agentGamer.addPermitCardItem(permitCardItem);
              
                    //resetta gli indici a zero e resetta la label che mostra il numero di aiutanti
                    selectedPermitCard = -1;
                    villagePermitCardGamerLabelM.setText("Click next");
                }
            }      
    
        
          if((e.getSource() == selectPoliticalCardButton) == true){
            
              if(pricePoliticalCardTextField.getText().trim().isEmpty() || selectedPoliticalCard == -1){
                  alertStageNoCorrectInput(3);
              }
              
             if((pricePoliticalCardTextField.getText().trim().isEmpty()) == false  && selectedPoliticalCard>=0){
                 
                    System.out.println("ho fatto select  " + selectedPoliticalCard);
                    politicalCardItem = new PoliticalCardItem(gamer.getPoliticalCards().get(selectedPoliticalCard), 
                                                            Integer.parseInt(pricePoliticalCardTextField.getText()),
                                                            selectedPoliticalCard);
                    //aggiungi ad agente la PoliticalCardItem
                    agentGamer.addPoliticalCardItem(politicalCardItem);
                    //hbPoliticalCardGamer.getChildren().removeAll(imgsPoliticalCards);
                    maxSelectionPoliticalCard = 0;
                    selectedPoliticalCard = -1;
                    selectionPoliticalCard();
                    
                }   
           }
        }
       
    
   
   public boolean isPermitCardSelected(PermitCard permitCard){
       boolean control = false;
       ArrayList<PermitCardItem> arrayPermitCard = agentGamer.getPermitCardStock();
       
       for(PermitCardItem item: arrayPermitCard){
           if((item.getPermitCard().equals(permitCard)) == true){
                control = true; 
           }
       }
       
       return control;
   }
  
   public void showPoliticalCardGamer() {
		
		imgsPoliticalCards = new ArrayList<ImageView>();
		selectPoliticalCard();

		hbPoliticalCardGamer = new HBox();
		hbPoliticalCardGamer.setPadding(new Insets(0, 100, 10, 10));
		hbPoliticalCardGamer.setSpacing(5);

		hbPoliticalCardGamer.getChildren().addAll(imgsPoliticalCards);

		anchorPoliticalCardM.getChildren().addAll(hbPoliticalCardGamer);

		AnchorPane.setBottomAnchor(hbPoliticalCardGamer, 0.0);
		AnchorPane.setLeftAnchor(hbPoliticalCardGamer, 1.0);
                selectionPoliticalCard();
	}
   
   	public void selectionPoliticalCard() {

		for (int i = 0; i < imgsPoliticalCards.size(); i++) {

			int j = i;
			imgsPoliticalCards.get(j).setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					if (maxSelectionPoliticalCard < 1) {
						imgsPoliticalCards.get(j).setImage(null);
						selectedPoliticalCard = j;
                                                System.out.println("ho salvato selectpoli:  " + selectedPoliticalCard);
                                                //        = politicalCardGamer.get(j);
						maxSelectionPoliticalCard++;
					}
				}
			});
		}
	}
   
  private void selectPoliticalCard() {

		politicalCardGamer = gamer.getPoliticalCards();

		for (PoliticalCard card : politicalCardGamer) {

			if (card.getColor().equals(java.awt.Color.CYAN)) {
				Image image = new Image("main/view/image/cartaPoliticaCiano.png");
				ImageView politicalCard = new ImageView(image);
				imgsPoliticalCards.add(politicalCard);
			}

			if (card.getColor().equals(java.awt.Color.BLACK)) {
				Image image = new Image("main/view/image/cartaPoliticaNera.png");
				ImageView politicalCard = new ImageView(image);
				imgsPoliticalCards.add(politicalCard);
			}

			if (card.getColor().equals(java.awt.Color.PINK)) {
				Image image = new Image("main/view/image/cartaPoliticaRosa.png");
				ImageView politicalCard = new ImageView(image);
				imgsPoliticalCards.add(politicalCard);
			}

			if (card.getColor().equals(java.awt.Color.ORANGE)) {
				Image image = new Image("main/view/image/cartaPoliticaArancio.png");
				ImageView politicalCard = new ImageView(image);
				imgsPoliticalCards.add(politicalCard);
			}

			if (card.getColor().equals(java.awt.Color.WHITE)) {
				Image image = new Image("main/view/image/cartaPoliticaBianca.png");
				ImageView politicalCard = new ImageView(image);
				imgsPoliticalCards.add(politicalCard);
			}

			if (card.getColor().equals(java.awt.Color.MAGENTA)) {
				Image image = new Image("main/view/image/cartaPoliticaMagenta.png");
				ImageView politicalCard = new ImageView(image);
				imgsPoliticalCards.add(politicalCard);
			}

			if (card.getJolly() == true) {
				Image image = new Image("main/view/image/cartaPoliticaJolly.png");
				ImageView politicalCard = new ImageView(image);
				imgsPoliticalCards.add(politicalCard);
			}
		}
	} 
   
   
  @FXML 
  public void optionMarketSelect(ActionEvent e){
      if((e.getSource() == endSelectionToSell)){

          new Client().showMarketPlace(agentGamer);
          seeMarketStage.close();
          // invia al server l'agente (agentGamer)
      
      }
  }
   
   
   public void alertStageNoCorrectInput(int numberAlert){
       //non hai inserito il prezzo e aiutanti
       if(numberAlert == 0){
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.initOwner(Client.getPrimaryStage());
              alert.setTitle("failure");
              alert.setHeaderText("Non hai inserito il prezzo o non hai selezionato gli aiutanti da vendere");
              alert.setContentText("Per favore, prima di selezionare inserisci gli un prezzo e gli aiutanti");

              alert.showAndWait();
       }
       
       //non hai inserito prezzo e non hai selezionato nessuna carta
       
        if(numberAlert == 1){
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.initOwner(Client.getPrimaryStage());
              alert.setTitle("failure");
              alert.setHeaderText("Non hai inserito il prezzo o non hai selezionato nessuna carta");
              alert.setContentText("Per favore, prima di selezionare inserisci gli un prezzo e premi next per selezionare una carta");

              alert.showAndWait();
       }
        
       if(numberAlert == 2){
              Alert alert = new Alert(Alert.AlertType.WARNING);
              alert.initOwner(Client.getPrimaryStage());
              alert.setTitle("failure");
              alert.setHeaderText("Carta già selezionata");
              alert.setContentText("Seleziona una carta differente");

              alert.showAndWait();
       }
       
       if(numberAlert == 3){
              Alert alert = new Alert(Alert.AlertType.WARNING);
              alert.initOwner(Client.getPrimaryStage());
              alert.setTitle("failure");
              alert.setHeaderText("Nessuna carta politica selezionata o nessun prezzo inserito");
              alert.setContentText("Seleziona una carta o inserisci il prezzo");

              alert.showAndWait();
       }
   }
   
    @FXML
    public void setSeeMarketStage(Stage seeMarketStage) {
	this.seeMarketStage = seeMarketStage;
	
    }
    
    
    
    
    
    
}
