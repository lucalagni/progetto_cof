package main.view;

import examples.example1.MatchExample;
import java.util.ArrayList;
import java.util.Iterator;
import main.Client;
import main.ClientLogic;
import model.basics.Board;
import model.basics.Gamer;
import model.basics.King;
import model.basics.Match;
import model.basics.PermitCard;
import model.basics.PoliticalCard;
import model.basics.Region;
import model.basics.Village;
import model.basics.builders.exceptions.BuilderException;
import model.basics.exceptions.GameMapException;
import model.basics.exceptions.MatchException;
import model.basics.exceptions.PoliticalCardsDeckException;



import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import main.view.custom.Operation;
import model.market.Agent;
import model.market.PoliticalCardItem;

public class MatchGameController extends FxmlMatchGameController {
    
    
        Operation op = new Operation();

	private Board be;
	private Match matchNew;
	private Gamer gamer;
	ArrayList<ImageView> imgsPoliticalCards;
	ArrayList<ImageView> imgsPoliticalCardsAction;
	ArrayList<ImageView> imgsPoliticalCardsKing;
	ArrayList<PoliticalCard> selectedPoliticalCards;
	ArrayList<PoliticalCard> selectedPoliticalCardsKing;
	ArrayList<PoliticalCard> politicalCardGamer;
	ArrayList<PermitCard> unusedPermitCardsGamer;
	ArrayList<PermitCard> usedPermitCardsGamer;
	ArrayList<String> villageKingLocation;
	PermitCard selectedPermitCardBuy;
	PermitCard permitCardBuild;
	PermitCard[][] regionPermitCard;
	PermitCard permitCardSpecialActionAcquire;
	PermitCard permitCardSpecialActionBonus;
	private HBox hbPoliticalCardGamer;
	private HBox hbPoliticalCardAction;
	private HBox hbPoliticalCardActionKing;
	java.awt.Color selectColorNobil;
	java.awt.Color selectColorElectFastNobil;
		
	private int selectRegionSpecialAction = 0;
	private int maxSelectionPoliticalCard = 0;
	private int maxSelectionPoliticalCardKing = 0;
	private int selectedRegionChangePermitCards = 3;
        
        //indici per next e prev
        private int selectedPermitCardGamer =  -1;
        private int selectedPermitCardBuild =  -1;
        private int selectedUnusedPermitCard = -1;
        private int selectedUsedPermitCard = -1;
        private int selectedRegionPermitCard1= -1;
	private int selectedRegionPermitCard2 = -1;
        private int selectedRegionElectFastNobil = -1;
        
	String selectedVillageBuildShop;
	String selectedVillageSingolBonus;
	String[] selectedVillageDoubleBonus;
	boolean clickSelectCouncilBoolean = false;
	boolean clickSelectCouncilKingBoolean = false;
	boolean clickSelectCouncilElectFastBoolean = false;
        
        
       
        
        // da togliere
    
     

	@FXML
	private void initialize() throws BuilderException, GameMapException {

		initComponentsView();
		

	}

/**
 * Inizializza diversi componenti
 */       
        
	protected void initComponentsView() {

		// inizializziamo il match
		try {
			matchNew = new MatchExample().getMatch();
			ClientLogic.getInstance().setMatch(matchNew);
			// istazio la board
			be = ClientLogic.getInstance().getMatch().getBoard();
		} catch (BuilderException | MatchException | GameMapException | PoliticalCardsDeckException e) {
			// pop-up con messaggio di errore (finestre di dialogo)
			e.printStackTrace();
		}

                // setta mappa
		String urlMappa = new String();
		// prendere da file
		urlMappa = "main/view/image/board.jpg";
		Image mapImage = new Image(urlMappa);
		map.setImage(mapImage);
                
                setCouncilRegionBoard();
		
		
		selectedVillageDoubleBonus = new String[2];
		selectedVillageDoubleBonus[0]= " ";
		selectedVillageDoubleBonus[1]= " ";
                // [3] = regioni, [2] carte
		regionPermitCard = new PermitCard[3][2];
                setVillagesPermitCardRegion();
                
		setTabPlayers();
		showPoliticalCardGamer();
		gamer = ClientLogic.getInstance().getGamer();
		helpersPlayer.setText("" + gamer.getHelpers());
		kingLocation1.setText("" + matchNew.getBoard().getKing().getPosition());
		kingLocation2.setText("" + matchNew.getBoard().getKing().getPosition());
		unusedPermitCardsGamer = gamer.getUnusedPermitCards();
		usedPermitCardsGamer = gamer.getUsedPermitCards();
	
			
		// nascondo finestre non utilizzabili momentaneamente
		anchorSelectNobil.setVisible(false);
                //anchorSelectVillage.setVisible(false);
		anchorSelectPoliticalCard.setVisible(false);
		anchorSelectPermitCard1.setVisible(false);
		anchorTakePermitCard.setVisible(false);
		anchorSelectVillageBuildShop.setDisable(true);
		clickAcquireBonusPermitCard.setVisible(false);
                actionBuyPermitCardButton.setDisable(true);
                accordion.setExpandedPane(gameDataTitledPane);
                
                //disabilita azioni
//               mainActionTitledPane.setDisable(true);
//               fastActionTitledPane.setDisable(true);
//               specialActionTitledPane.setDisable(true);
//                
	}
        
        public void upgradeBoard(Match match){
            
            ClientLogic.getInstance().setMatch(match);
            matchNew =  ClientLogic.getInstance().getMatch();
            be =  ClientLogic.getInstance().getMatch().getBoard();
            
            setVillagesPermitCardRegion();
            setTabPlayers();
            setCouncilRegionBoard();
            
            kingLocation1.setText("" + matchNew.getBoard().getKing().getPosition());
            kingLocation2.setText("" + matchNew.getBoard().getKing().getPosition());
            
//            if(isMyTurn == true){
//               mainActionTitledPane.setDisable(false);
//               fastActionTitledPane.setDisable(false);
//            }
//            
        }
        
        public void upgradeGamer(Gamer g){
            
            
            helpersPlayer.setText("" + g.getHelpers());
            selectedPermitCardGamer = -1;
            selectedPermitCardGamer =  -1;
            selectedPermitCardBuild =  -1;
            selectedUnusedPermitCard = -1;
            selectedUsedPermitCard = -1;
            selectedRegionPermitCard1= -1;
            selectedRegionPermitCard2 = -1;
            selectedRegionElectFastNobil = -1;
            
            showPoliticalCardGamer();
        }
        
        
        
        
        
        
        
        public void setCouncilRegionBoard(){
            for (int i = 0; i <= 3; i++) {
                    if (i == 0) {
			setCouncilRegion(i, nobil00, nobil01, nobil02, nobil03);
                    }
                    if (i == 1) {
			setCouncilRegion(i, nobil10, nobil11, nobil12, nobil13);
                    }
                    if (i == 2) {
			setCouncilRegion(i, nobil20, nobil21, nobil22, nobil23);
                    }
                    if (i == 3) {
			setCouncilRegion(i, nobilKing0, nobilKing1, nobilKing2, nobilKing3);
                    }
		}  
        }

/**
 * Setta il testo delle Label dei villaggi relativi alle carte permesso delle regioni della Board
 * @param s la stringa da far apparire nella Label
 * @param regioncard la regione selezionata
 */      
        
	public void setTextPermitCardVillageBoard(String s, int regioncard) {

		if (regioncard == 00) {
			villagesRegion0Card0.setText(s);
		}
		if (regioncard == 01) {
			villagesRegion0Card1.setText(s);
		}
		if (regioncard == 10) {
			villagesRegion1Card0.setText(s);
		}
		if (regioncard == 11) {
			villagesRegion1Card1.setText(s);
		}
		if (regioncard == 20) {
			villagesRegion2Card0.setText(s);
		}
		if (regioncard == 21) {
			villagesRegion2Card1.setText(s);
		}
	}

	
/**
 * Gestisce gli eventi dei pulsanti per spostare in avanti gli indici relativi alle carte permesso
 * @param e 
 */
	@FXML
	public void nextPermitCard(ActionEvent e) {

		if ((e.getSource() == nextPermitCardGamer) == true) {
      
                    selectedPermitCardGamer = op.nextPermitCard(selectedPermitCardGamer,unusedPermitCardsGamer, villagePermitCardGamerLabel);    

		}

		if ((e.getSource() == nextPermitCardBuild) == true) {

                   // selected permit card viene passato come indice per mostrare carte permesso successive
                   selectedPermitCardBuild = op.nextPermitCard(selectedPermitCardBuild,unusedPermitCardsGamer, villagePermitCardBuildLabel);
                   setButtonVillageAvailable();
                   anchorSelectVillageBuildShop.setDisable(true);
              
                }
		if ((e.getSource() == nextUnusedPermitCardGamer) == true) {

                   selectedUnusedPermitCard = op.nextPermitCard(selectedUnusedPermitCard,unusedPermitCardsGamer, villageUnusedPermitCardGamerLabel);  
                }
		
		if ((e.getSource() == nextUsedPermitCardGamer) == true) {
                    
                   selectedUsedPermitCard = op.nextPermitCard(selectedUsedPermitCard,usedPermitCardsGamer, villageUsedPermitCardGamerLabel);
                   System.out.println("next  " + selectedUsedPermitCard);
                   
		}



	}
        
/**
 * Gestisce i bottoni per spostare indietro gli indici relativi alle carte permesso
 * @param e 
 */      
        
	@FXML
	public void prevPermitCard(ActionEvent e) {

		if ((e.getSource() == prevPermitCardGamer) == true) {
                    
                    selectedPermitCardGamer = op.prevPermitCard(selectedPermitCardGamer, unusedPermitCardsGamer, villagePermitCardGamerLabel);            
                }


		if ((e.getSource() == prevPermitCardBuild) == true) {
                    
                     selectedPermitCardBuild = op.prevPermitCard(selectedPermitCardBuild, unusedPermitCardsGamer, villagePermitCardBuildLabel);
                    setButtonVillageAvailable();
                    anchorSelectVillageBuildShop.setDisable(true);
             
		}
		
		if ((e.getSource() == prevUnusedPermitCardGamer) == true) {

                    selectedUnusedPermitCard = op.prevPermitCard(selectedUnusedPermitCard, unusedPermitCardsGamer,villageUnusedPermitCardGamerLabel);
                  
		}
		
		if ((e.getSource() == prevUsedPermitCardGamer) == true) {
                    selectedUsedPermitCard = op.prevPermitCard(selectedUsedPermitCard, usedPermitCardsGamer,villageUsedPermitCardGamerLabel);
		}
	}

/**
 * Gestisce i bottoni per spostare in avanti gli indici relativi ai consigli
 * @param e 
 */
	@FXML
	public void nextCouncil(ActionEvent e) {

		if ((e.getSource() == nextCouncil1) == true) {
                    
                selectedRegionPermitCard1 = op.nextCouncil(3,selectedRegionPermitCard1, regionNumber1);
                setCouncilRegion(selectedRegionPermitCard1, council1nobil0, council1nobil1, council1nobil2, council1nobil3);
                
		}

		if ((e.getSource() == nextCouncil2) == true) {

                selectedRegionPermitCard2 = op.nextCouncil(2,selectedRegionPermitCard2, regionNumber2);
                setCouncilRegion(selectedRegionPermitCard2, council2nobil0, council2nobil1, council2nobil2, council2nobil3);
            }
		
		
		if ((e.getSource() == nextCouncilElectFastNobil) == true) {
                    selectedRegionElectFastNobil = op.nextCouncil(3,selectedRegionElectFastNobil, regionNumberElectFast);
                    setCouncilRegion( selectedRegionElectFastNobil, councilElectFastNobil0, councilElectFastNobil1, councilElectFastNobil2, councilElectFastNobil3);   
                }
	}
	
/**
 * Gestisce i bottoni per spostare indietro gli indici relativi ai consigli
 * @param e 
 */
	public void prevCouncil(ActionEvent e) {

		if ((e.getSource() == prevCouncil1) == true) {

                    selectedRegionPermitCard1 = op.prevCouncil(selectedRegionPermitCard1, regionNumber1);
                    setCouncilRegion(selectedRegionPermitCard1, council1nobil0, council1nobil1, council1nobil2, council1nobil3);
		}

		if ((e.getSource() == prevCouncil2) == true) {

                         selectedRegionPermitCard2 = op.prevCouncil(selectedRegionPermitCard2, regionNumber2);
                         setCouncilRegion(selectedRegionPermitCard2, council2nobil0, council2nobil1, council2nobil2, council2nobil3);
		}
		
		if ((e.getSource() == prevCouncilElectFastNobil) == true) {
                    
                    selectedRegionElectFastNobil = op.prevCouncil(selectedRegionElectFastNobil, regionNumberElectFast);
                    setCouncilRegion(selectedRegionElectFastNobil, councilElectFastNobil0, councilElectFastNobil1, councilElectFastNobil2, councilElectFastNobil3);
                    
		}

	}
        
/**
 * Inserisce all'interno di un array multiplo di PermitCard le carte regione scoperte relative alla board
 * può essere usato per fare l'update
 */
	public void setVillagesPermitCardRegion() {

		regionPermitCard[0][0] = be.getRegions()[0].getPermitCardsDeck().getUnhiddenCards()[0];
		regionPermitCard[0][1] = be.getRegions()[0].getPermitCardsDeck().getUnhiddenCards()[1];
		regionPermitCard[1][0] = be.getRegions()[1].getPermitCardsDeck().getUnhiddenCards()[0];
		regionPermitCard[1][1] = be.getRegions()[1].getPermitCardsDeck().getUnhiddenCards()[1];
		regionPermitCard[2][0] = be.getRegions()[2].getPermitCardsDeck().getUnhiddenCards()[0];
		regionPermitCard[2][1] = be.getRegions()[2].getPermitCardsDeck().getUnhiddenCards()[1];

		// PermitCard pc00 =
		// be.getRegions()[0].getPermitCardsDeck().getUnhiddenCards()[0];
		// PermitCard pc01 =
		// be.getRegions()[0].getPermitCardsDeck().getUnhiddenCards()[1];
		// PermitCard pc10 =
		// be.getRegions()[1].getPermitCardsDeck().getUnhiddenCards()[0];
		// PermitCard pc11 =
		// be.getRegions()[1].getPermitCardsDeck().getUnhiddenCards()[1];
		// PermitCard pc20 =
		// be.getRegions()[2].getPermitCardsDeck().getUnhiddenCards()[0];
		// PermitCard pc21 =
		// be.getRegions()[2].getPermitCardsDeck().getUnhiddenCards()[1];
		//

		setTextPermitCardVillageBoard("" + regionPermitCard[0][0].getVillages(), 00);
		setTextPermitCardVillageBoard("" + regionPermitCard[0][1].getVillages(), 01);
		setTextPermitCardVillageBoard("" + regionPermitCard[1][0].getVillages(), 10);
		setTextPermitCardVillageBoard("" + regionPermitCard[1][1].getVillages(), 11);
		setTextPermitCardVillageBoard("" + regionPermitCard[2][0].getVillages(), 20);
		setTextPermitCardVillageBoard("" + regionPermitCard[2][1].getVillages(), 21);

	}
/**
 * Salva in un array le immagini delle carte politiche del giocatore facendo un controllo tra i colori delle carte politiche del giocatore
 * e i colori stabiliti
 * @param imgsPoliticalCards ArrayList di ImageView dove salvare le immagini delle carte politiche del giocatore
 */        
	private void showPoliticalCard( ArrayList<ImageView>  imgsPoliticalCards) {

		
		Gamer gamer = matchNew.getGamers().get(1);
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

	// Image image = new Image("main/view/image/cartapoliticaVerticale.png");
	// ImageView politicalCard1 = new ImageView(image);
	// ImageView politicalCard2 = new ImageView(image);
	//
	// HBox hb = new HBox();
	// hb.setPadding(new Insets(0, 10, 10, 10));
	// hb.setSpacing(5);
	// hb.getChildren().addAll(politicalCard1, politicalCard2);
	//
	//
	// proprietaGiocatore.getChildren().add(hb);
	//
	//
	// AnchorPane.setBottomAnchor(hb, 0.0);
	// AnchorPane.setLeftAnchor(hb, 5.0);

	// public void updatePoliticalCard()
	// {

	//
	// // rimuove la lista
	// hbPoliticalCard.getChildren().removeAll(imgsPoliticalCards);
	//
	// // ricarica la lista
	// PoliticalCard[] mano = new PoliticalCard[10]; // prelevate dal gamer
	// imgsPoliticalCards.clear();
	// for( PoliticalCard c:mano ){
	// Image image;
	// if( c.getColor()==java.awt.Color.CYAN ){
	// image = new Image("main/view/image/cartapoliticaVerticale.png");
	// }
	// else if( c.getColor()==java.awt.Color.CYAN ){
	// image = new Image("main/view/image/cartapoliticaVerticale.png");
	// }
	// else {
	// // caso farlocco
	// continue;
	// }
	//
	// ImageView politicalCarde = new ImageView(image);
	// imgsPoliticalCards.add(politicalCarde);
	// }
	//
	// // per il refresh
	// hbPoliticalCard.getChildren().addAll(imgsPoliticalCards);
	//
	// }
/**
 * Crea una nuova HBox dove inserire le immagini delle carte politiche del giocatore per mostrarle nella tabella dati
 */
	public void showPoliticalCardGamer() {
		
		imgsPoliticalCards = new ArrayList<ImageView>();
		showPoliticalCard(imgsPoliticalCards);

		hbPoliticalCardGamer = new HBox();
		hbPoliticalCardGamer.setPadding(new Insets(0, 100, 10, 10));
		hbPoliticalCardGamer.setSpacing(5);

		hbPoliticalCardGamer.getChildren().addAll(imgsPoliticalCards);

		anchorProprietaGiocatore.getChildren().addAll(hbPoliticalCardGamer);

		AnchorPane.setBottomAnchor(hbPoliticalCardGamer, 0.0);
		AnchorPane.setLeftAnchor(hbPoliticalCardGamer, 1.0);

	}
        
/**
 * Crea una nuova HBox dove inserire le immagini delle carte politiche per l'azione principale costruisci emporio
 */

	public void showPoliticalCardAction() {
		
		imgsPoliticalCardsAction = new ArrayList<ImageView>();
		showPoliticalCard(imgsPoliticalCardsAction);

		hbPoliticalCardAction = new HBox();
		hbPoliticalCardAction.setPadding(new Insets(0, 100, 10, 10));
		hbPoliticalCardAction.setSpacing(5);

		hbPoliticalCardAction.getChildren().addAll(imgsPoliticalCardsAction);

		anchorSelectPoliticalCard.getChildren().addAll(hbPoliticalCardAction);

		AnchorPane.setBottomAnchor(hbPoliticalCardAction, 0.0);
		AnchorPane.setLeftAnchor(hbPoliticalCardAction, 1.0);
		selectPoliticalCardAction();

	}
	
/**
 * Crea una nuova HBox dove inserire le immagini delle carte politiche per l'azione aiuto del re
 */
	public void showPoliticalCardActionKing() {
		
		imgsPoliticalCardsKing = new ArrayList<ImageView>();
		showPoliticalCard(imgsPoliticalCardsKing);

		hbPoliticalCardActionKing = new HBox();
		hbPoliticalCardActionKing.setPadding(new Insets(0, 100, 10, 10));
		hbPoliticalCardActionKing.setSpacing(5);

		hbPoliticalCardActionKing.getChildren().addAll(imgsPoliticalCardsKing);

		anchorSelectPoliticalCardKing.getChildren().addAll(hbPoliticalCardActionKing);

		AnchorPane.setBottomAnchor(hbPoliticalCardActionKing, 0.0);
		AnchorPane.setLeftAnchor(hbPoliticalCardActionKing, 1.0);
		selectPoliticalCardActionKing();

	}
/**
 * Seleziona le carte politiche per le azioni (gestisce l'interazione grafica di selezione) le salva su un arrayList
 * da mandare al server
 */
	public void selectPoliticalCardAction() {

		selectedPoliticalCards = new ArrayList<PoliticalCard>();

		for (int i = 0; i < imgsPoliticalCardsAction.size(); i++) {

			int j = i;
			imgsPoliticalCardsAction.get(j).setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					if (maxSelectionPoliticalCard < 4) {
						imgsPoliticalCardsAction.get(j).setImage(null);
						selectedPoliticalCards.add(politicalCardGamer.get(j));
						maxSelectionPoliticalCard++;
					}
				}
			});
		}
	}
	
/**
 * Selziona carte politiche per azione aiuto del re (gestisce l'interazione grafica di selezione)
 */
        
	public void selectPoliticalCardActionKing() {

		selectedPoliticalCardsKing = new ArrayList<PoliticalCard>();

		for (int i = 0; i < imgsPoliticalCardsKing.size(); i++) {

			int j = i;
			imgsPoliticalCardsKing.get(j).setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					if (maxSelectionPoliticalCardKing < 4) {
						imgsPoliticalCardsKing.get(j).setImage(null);
						selectedPoliticalCardsKing.add(politicalCardGamer.get(j));
						maxSelectionPoliticalCardKing++;
					}
				}
			});
		}
	}

        /**
         * Metodo che gestisce i "mouse clicked" sui bottoni per vedere gli empori prensenti nei villaggi
         * @param e evento
         */
        
	@FXML
	private void clickShopPlayers(ActionEvent e) {

		if ((e.getSource() == buttonShopA) == true) {
			new Client().showShopPlayers("Arkon");
		}

		if ((e.getSource() == buttonShopB) == true) {
			new Client().showShopPlayers("Burgen");
		}

		if ((e.getSource() == buttonShopC) == true) {
			new Client().showShopPlayers("Castrum");
		}

		if ((e.getSource() == buttonShopD) == true) {
			new Client().showShopPlayers("Dorful");
		}

		if ((e.getSource() == buttonShopE) == true) {
			new Client().showShopPlayers("Esti");
		}

		if ((e.getSource() == buttonShopF) == true) {
			new Client().showShopPlayers("Framek");
		}

		if ((e.getSource() == buttonShopG) == true) {
			new Client().showShopPlayers("Graden");
		}
		
		if ((e.getSource() == buttonShopH) == true) {
			new Client().showShopPlayers("Hellar");
		}

		if ((e.getSource() == buttonShopI) == true) {
			new Client().showShopPlayers("Indur");
		}

		if ((e.getSource() == buttonShopJ) == true) {
			new Client().showShopPlayers("Juvelar");
		}

		if ((e.getSource() == buttonShopK) == true) {
			new Client().showShopPlayers("Kultos");
		}

		if ((e.getSource() == buttonShopL) == true) {
			new Client().showShopPlayers("Lyram");
		}

		if ((e.getSource() == buttonShopM) == true) {
			new Client().showShopPlayers("Merkatim");
		}

		if ((e.getSource() == buttonShopN) == true) {
			new Client().showShopPlayers("Naris");
		}

		if ((e.getSource() == buttonShopO) == true) {
			new Client().showShopPlayers("Osium");
		}

	}

	@FXML
	private void click2() { // devi implementarlo per quando fa l'azione

		double x = button.getTranslateX();
		double y = button.getTranslateY();
		muoviPedina(button, x, y);

	}

	public void muoviPedina(Circle buttone, double x, double y) {

		Path path = new Path();
		path.getElements().add(new MoveTo(x, y));
		if (x < 50.0) {
			path.getElements().add(new LineTo(x + 26, y));
		}

		else
			path.getElements().add(new LineTo(x, y + 26));

		PathTransition ptr = new PathTransition();

		ptr.setDuration(Duration.seconds(3));
		ptr.setDelay(Duration.seconds(1));
		ptr.setPath(path);
		ptr.setNode(buttone);
		// ptr.setCycleCount(2)

		ptr.play();

	}

/**
 * Mostra una nuova finestra con i bonus relativi al villaggio selezionato
 * @param e evento riferito al bottone selezionato
 */       
        
	@FXML
	public void showBonusVillage(ActionEvent e) {

		if ((e.getSource() == arkonBonus) == true) {
			new Client().showBonusVillage(0);
		}

		if ((e.getSource() == burgenBonus) == true) {
			new Client().showBonusVillage(1);
		}

		if ((e.getSource() == castrumBonus) == true) {
			new Client().showBonusVillage(2);
		}

		if ((e.getSource() == dorfulBonus) == true) {
			new Client().showBonusVillage(3);
		}

		if ((e.getSource() == estiBonus) == true) {
			new Client().showBonusVillage(4);
		}

		if ((e.getSource() == framekBonus) == true) {
			new Client().showBonusVillage(5);
		}

		if ((e.getSource() == gradenBonus) == true) {
			new Client().showBonusVillage(6);
		}

		if ((e.getSource() == hellarBonus) == true) {
			new Client().showBonusVillage(7);
		}

		if ((e.getSource() == indurBonus) == true) {
			new Client().showBonusVillage(8);
		}

		if ((e.getSource() == kultosBonus) == true) {
			new Client().showBonusVillage(10);
		}

		if ((e.getSource() == lyramBonus) == true) {
			new Client().showBonusVillage(11);
		}

		if ((e.getSource() == merkatimBonus) == true) {
			new Client().showBonusVillage(12);
		}

		if ((e.getSource() == narisBonus) == true) {
			new Client().showBonusVillage(13);
		}

		if ((e.getSource() == osiumBonus) == true) {
			new Client().showBonusVillage(14);
		}
	}

	@FXML
	public void showBonusPermitCard(ActionEvent e) {

		if ((e.getSource() == region0Card0)) {
			new Client().showBonusPermitCard(0, 0);
		}

		if ((e.getSource() == region0Card1)) {
			new Client().showBonusPermitCard(0, 1);
		}

		if ((e.getSource() == region1Card0)) {
			new Client().showBonusPermitCard(1, 0);
		}

		if ((e.getSource() == region1Card1)) {
			new Client().showBonusPermitCard(1, 1);
		}

		if ((e.getSource() == region2Card0)) {
			new Client().showBonusPermitCard(2, 0);
		}

		if ((e.getSource() == region2Card1)) {
			new Client().showBonusPermitCard(2, 1);
		}

		if ((e.getSource() == villagesRegionCard0)) {
			new Client().showBonusPermitCard(selectedRegionPermitCard2, 0);
		}

		if ((e.getSource() == villagesRegionCard1)) {
			new Client().showBonusPermitCard(selectedRegionPermitCard2, 1);
		}
		
		if ((e.getSource() == bonusPermitCardGamer)) {
			new Client().showBonusPermitCardGamer(selectedPermitCardGamer);
		}
		
		if ((e.getSource() == bonusUnusedPermitCardGamer)) {
			new Client().showBonusPermitCardGamer(selectedUnusedPermitCard);
		}
		
		if ((e.getSource() == bonusUsedPermitCardGamer)) {
			new Client().showBonusUsedPermitCardGamer(selectedUsedPermitCard);
		}
		
                if ((e.getSource() == buyRegionCard0)) {
			new Client().showBonusPermitCard(selectedRegionPermitCard2, 0);
		}
                
                 if ((e.getSource() == buyRegionCard1)) {
			new Client().showBonusPermitCard(selectedRegionPermitCard2, 1);
		}
                
                if ((e.getSource() == regionCardSpecialAction0)) {
			new Client().showBonusPermitCard(selectRegionSpecialAction, 0);
		}
                
                 if ((e.getSource() == regionCardSpecialAction1)) {
			new Client().showBonusPermitCard(selectRegionSpecialAction, 1);
		}
               
		
		
		

	}

	@FXML
	public void nobilSelection(ActionEvent e) {
		
		// per azione principale
		if (e.getSource() == nobilWhite) {
                    selectColorNobil = java.awt.Color.WHITE;
                }
		if (e.getSource() == nobilBlack) {
                    selectColorNobil = java.awt.Color.BLACK;
		}
		if (e.getSource() == nobilCyan) {
                    selectColorNobil = java.awt.Color.CYAN;
		}
		if (e.getSource() == nobilOrange) {
                    selectColorNobil = java.awt.Color.ORANGE;
		}
		if (e.getSource() == nobilMagenta) {
                    selectColorNobil = java.awt.Color.MAGENTA;
		}
		if (e.getSource() == nobilPink) {
                    selectColorNobil = java.awt.Color.PINK;
		}
		
		// per azione veloce
		
		
		if (e.getSource() == fastNobilWhite) {
			selectColorElectFastNobil = java.awt.Color.WHITE;
		}
		if (e.getSource() == fastNobilBlack) {
			selectColorElectFastNobil = java.awt.Color.BLACK;
		}
		if (e.getSource() == fastNobilCyan) {
			selectColorElectFastNobil = java.awt.Color.CYAN;
		}
		if (e.getSource() == fastNobilOrange) {
			selectColorElectFastNobil = java.awt.Color.ORANGE;
		}
		if (e.getSource() == fastNobilMagenta) {
			selectColorElectFastNobil = java.awt.Color.MAGENTA;
		}
		if (e.getSource() == fastNobilPink) {
			selectColorElectFastNobil = java.awt.Color.PINK;
		}

	}

	@FXML
	public void clickSelectCouncil(ActionEvent e) {

		if ((e.getSource() == selectCouncilBuyPermitCard) == true) {

			if (clickSelectCouncilBoolean == true) {
				hbPoliticalCardAction.getChildren().removeAll(imgsPoliticalCardsAction);
				anchorSelectPoliticalCard.setVisible(false);
				anchorSelectPermitCard1.setVisible(false);
			}
                        op.setTextPermitCardVillageAction(selectedRegionPermitCard2, regionPermitCard, villagesRegionCard0, villagesRegionCard1);
			maxSelectionPoliticalCard = 0;
			showPoliticalCardAction();
			clickSelectCouncilBoolean = true;
			// anchorSelectPoliticalCard.setVisible(true);
			anchorSelectPoliticalCard.setVisible(true);
		}

		if ((e.getSource() == selectCouncilElectNobil) == true) {

//			selectRegionPermitCard1
			anchorSelectNobil.setVisible(true);
		}
		
		if((e.getSource() == selectCouncilKing)== true){
			if (clickSelectCouncilKingBoolean == true) {
				hbPoliticalCardActionKing.getChildren().removeAll(imgsPoliticalCardsKing);
				anchorSelectPoliticalCardKing.setVisible(false);		
			}

			maxSelectionPoliticalCardKing = 0;


			showPoliticalCardActionKing();
			clickSelectCouncilKingBoolean = true;
			anchorSelectPoliticalCardKing.setVisible(true);
     
		}
		
		if((e.getSource() == selectCouncilElectFastNobil)== true){
			
			anchorSelectFastNobil.setVisible(true);
			if (clickSelectCouncilElectFastBoolean == true) {
				anchorSelectFastNobil.setVisible(false);
			}
			
				//selectRegionElectFastNobil;
				clickSelectCouncilElectFastBoolean = true;
		}
		
	}

	/**
	 * Carte politiche selezionate procedi a selezionare le carte permesso
	 */

	@FXML
	public void selectPoliticalCard(ActionEvent e) {
		
		if((e.getSource() == selectPoliticalCardOk) == true){
			if (maxSelectionPoliticalCard == 0) {

				// Nothing selected.
				Alert alert = new Alert(AlertType.WARNING);
				alert.initOwner(Client.getPrimaryStage());
				alert.setTitle("No Selection");
				alert.setHeaderText("No political card Selected");
				alert.setContentText("Please select a political card.");

				alert.showAndWait();

			}

			else
                            anchorSelectPermitCard1.setVisible(true);
                     
		}
		
		
		if((e.getSource() == selectPoliticalCardOkKing) == true){
			if (maxSelectionPoliticalCardKing == 0) {

				// Nothing selected.
				Alert alert = new Alert(AlertType.WARNING);
				alert.initOwner(Client.getPrimaryStage());
				alert.setTitle("No Selection");
				alert.setHeaderText("No political card Selected");
				alert.setContentText("Please select a political card.");

				alert.showAndWait();

			}

			else
				anchorSelectKingVillage.setVisible(true);
		}
		
		
		
		
	}

	/**
	 * Imposta i consiglieri nei balconcini in base alla regione passata
	 * 
	 * @param numberOfRegion
	 * @param nobil0
	 *            � il nobile in posizione 0 del balconcino
	 * @param nobil1
	 *            � il nobile in posizione 1 del balconcino
	 * @param nobil2
	 *            � il nobile in posizione 2 del balconcino
	 * @param nobil3
	 *            � il nobile in posizione 3 del balconcino
	 */
	public void setCouncilRegion(int numberOfRegion, ImageView nobil0, ImageView nobil1, ImageView nobil2,
			ImageView nobil3) {

		try {
			// esempio
			// MatchRequest req = null;
			// req.getMatch("pippo");

			// // poi ...
			// Match m = null; // prendo il match dal server
			// Client.getInstance().setMatch(m);

			if (numberOfRegion >= 0 && numberOfRegion <= 2) {
				Region region = be.getRegions()[numberOfRegion];
				if (numberOfRegion == 0) {
					java.awt.Color[] color = region.getCouncil().getNobles();
					setColorCouncil(nobil0, nobil1, nobil2, nobil3, color);
				}
				if (numberOfRegion == 1) {
					java.awt.Color[] color = region.getCouncil().getNobles();
					setColorCouncil(nobil0, nobil1, nobil2, nobil3, color);
				}
				if (numberOfRegion == 2) {
					java.awt.Color[] color = region.getCouncil().getNobles();
					setColorCouncil(nobil0, nobil1, nobil2, nobil3, color);
				}
			}

			// il numero "3" identifica il il consiglio del re
			if (numberOfRegion == 3) {
				King king = be.getKing();
				java.awt.Color[] color = king.getCouncil().getNobles();
				setColorCouncil(nobil0, nobil1, nobil2, nobil3, color);
			}
		}
		// }
		catch (BuilderException ge) {
			// TODO Auto-generated catch block
			ge.printStackTrace();
		}

	}

	public void setTabPlayers() {

		ArrayList<Gamer> gamers;
		gamers = matchNew.getGamers();

		int i = gamers.size();

		for (int j = 0; j < i; j++) {

			if (j == 0) {
				usernamePlayer0.setText("" + gamers.get(0).getUsername());
				coinsPlayer0.setText("" + gamers.get(0).getCoins());
				nobilityPathPlayer0.setText("" + gamers.get(0).getShifts());
				pointsPlayer0.setText("" + gamers.get(0).getPoints());
			}
			if (j == 1) {
				usernamePlayer1.setText("" + gamers.get(1).getUsername());
				coinsPlayer1.setText("" + gamers.get(1).getCoins());
				nobilityPathPlayer1.setText("" + gamers.get(1).getShifts());
				pointsPlayer1.setText("" + gamers.get(1).getPoints());
			}
			if (j == 2) {
				usernamePlayer2.setText("" + gamers.get(1).getUsername());
				coinsPlayer2.setText("" + gamers.get(1).getCoins());
				nobilityPathPlayer2.setText("" + gamers.get(1).getShifts());
				pointsPlayer2.setText("" + gamers.get(1).getPoints());
			}

			if (j == 3) {
				usernamePlayer3.setText("" + gamers.get(1).getUsername());
				coinsPlayer3.setText("" + gamers.get(1).getCoins());
				nobilityPathPlayer3.setText("" + gamers.get(1).getShifts());
				pointsPlayer3.setText("" + gamers.get(1).getPoints());
			}

			if (j == 4) {
				usernamePlayer4.setText("" + gamers.get(1).getUsername());
				coinsPlayer4.setText("" + gamers.get(1).getCoins());
				nobilityPathPlayer4.setText("" + gamers.get(1).getShifts());
				pointsPlayer4.setText("" + gamers.get(1).getPoints());
			}

			if (j == 5) {
				usernamePlayer5.setText("" + gamers.get(1).getUsername());
				coinsPlayer5.setText("" + gamers.get(1).getCoins());
				nobilityPathPlayer5.setText("" + gamers.get(1).getShifts());
				pointsPlayer5.setText("" + gamers.get(1).getPoints());
			}

			if (j == 6) {
				usernamePlayer6.setText("" + gamers.get(1).getUsername());
				coinsPlayer6.setText("" + gamers.get(1).getCoins());
				nobilityPathPlayer6.setText("" + gamers.get(1).getShifts());
				pointsPlayer6.setText("" + gamers.get(1).getPoints());
			}
		}
	}

	public void setColorCouncil(ImageView nobil0, ImageView nobil1, ImageView nobil2, ImageView nobil3,
			java.awt.Color[] color) {

		for (int i = 0; i < 4; i++) {
			if (color[i].equals(java.awt.Color.CYAN)) {
				Image image = new Image("main/view/image/consBlu.png");
				if (i == 0) {
					nobil0.setImage(image);
				}
				if (i == 1) {
					nobil1.setImage(image);
				}
				if (i == 2) {
					nobil2.setImage(image);
				}
				if (i == 3) {
					nobil3.setImage(image);
				}
			}
			if (color[i] == java.awt.Color.BLACK) {
				Image image = new Image("main/view/image/consNero.png");
				if (i == 0) {
					nobil0.setImage(image);
				}
				if (i == 1) {
					nobil1.setImage(image);
				}
				if (i == 2) {
					nobil2.setImage(image);
				}
				if (i == 3) {
					nobil3.setImage(image);
				}
			}

			if (color[i] == java.awt.Color.PINK) {
				Image image = new Image("main/view/image/consRosa.png");
				if (i == 0) {
					nobil0.setImage(image);
				}
				if (i == 1) {
					nobil1.setImage(image);
				}
				if (i == 2) {
					nobil2.setImage(image);
				}
				if (i == 3) {
					nobil3.setImage(image);
				}
			}

			if (color[i] == java.awt.Color.ORANGE) {
				Image image = new Image("main/view/image/consAranc.png");
				if (i == 0) {
					nobil0.setImage(image);
				}
				if (i == 1) {
					nobil1.setImage(image);
				}
				if (i == 2) {
					nobil2.setImage(image);
				}
				if (i == 3) {
					nobil3.setImage(image);
				}
			}

			if (color[i] == java.awt.Color.MAGENTA) {
				Image image = new Image("main/view/image/consMagenta.png");
				if (i == 0) {
					nobil0.setImage(image);
				}
				if (i == 1) {
					nobil1.setImage(image);
				}
				if (i == 2) {
					nobil2.setImage(image);
				}
				if (i == 3) {
					nobil3.setImage(image);
				}
			}

			if (color[i] == java.awt.Color.WHITE) {
				Image image = new Image("main/view/image/consBianco.png");
				if (i == 0) {
					nobil0.setImage(image);
				}
				if (i == 1) {
					nobil1.setImage(image);
				}
				if (i == 2) {
					nobil2.setImage(image);
				}
				if (i == 3) {
					nobil3.setImage(image);
				}
			}

		}
	}

	@FXML
	public void selectPermitCard(ActionEvent e) {

		if ((e.getSource() == selectPermitCardBuy0) == true) {
                    selectedPermitCardBuy = regionPermitCard[selectedRegionPermitCard2][0];
                    actionBuyPermitCardButton.setDisable(false);
                    selectPermitCardBuy1.setDisable(true);
		}

		if ((e.getSource() == selectPermitCardBuy1) == true) {
                    selectedPermitCardBuy = regionPermitCard[selectedRegionPermitCard2][1];
                    actionBuyPermitCardButton.setDisable(false);
                    selectPermitCardBuy0.setDisable(true);
		}
		
		if ((e.getSource() == selectPermitCardBuild) == true) {
                    
                    permitCardBuild = unusedPermitCardsGamer.get(selectedPermitCardBuild);
                    setVillageBuildShop(permitCardBuild);
                    anchorSelectVillageBuildShop.setDisable(false);
		}

		if ((e.getSource() == selectUnusedPermitCardBonus) == true) {
			permitCardSpecialActionBonus = unusedPermitCardsGamer.get(selectedUnusedPermitCard);
			clickAcquireBonusPermitCard.setVisible(true);
		}
		
		if ((e.getSource() == selectUsedPermitCardBonus) == true) {
			permitCardSpecialActionBonus = usedPermitCardsGamer.get(selectedUsedPermitCard);
			clickAcquireBonusPermitCard.setVisible(true);
		}	
	}
        
        public void setButtonVillageAvailable(){
            villageA12.setDisable(false);
            villageB12.setDisable(false);
            villageC12.setDisable(false);
            villageD12.setDisable(false);
            villageE12.setDisable(false);
            villageF12.setDisable(false);
            villageG12.setDisable(false);
            villageH12.setDisable(false);
            villageI12.setDisable(false);
            villageJ12.setDisable(false);
            villageK12.setDisable(false);
            villageL12.setDisable(false);
            villageM12.setDisable(false);
            villageN12.setDisable(false);
            villageO12.setDisable(false);
   
        }
        
        
	
	@FXML
	public void buyWithKingHelp() {
		villageKingLocation = new ArrayList<String>();
		villageKingLocation.add(matchNew.getBoard().getKing().getPosition());
		setCouncilRegion(3, councilKingNobil0, councilKingNobil1, councilKingNobil2, councilKingNobil3);
		anchorSelectKingVillage.setVisible(false);
		
//		showPoliticalCardActionKing();
	}
	

	@FXML
	public void selectVillageKing(ActionEvent e){
		
		if((e.getSource() == villageA) == true){
			
		villageKingLocation.add("A");
		villagePathKing.setText("" + villageKingLocation);}
		
		if((e.getSource() == villageB) == true){
			
			villageKingLocation.add("B");
			villagePathKing.setText("" + villageKingLocation);}
		
		if((e.getSource() == villageC) == true){
			
			villageKingLocation.add("C");
			villagePathKing.setText("" + villageKingLocation);}
		
		if((e.getSource() == villageD) == true){
			
			villageKingLocation.add("D");
			villagePathKing.setText("" + villageKingLocation);}
		
		if((e.getSource() == villageE) == true){
			
			villageKingLocation.add("E");
			villagePathKing.setText("" + villageKingLocation);}
		
		if((e.getSource() == villageF) == true){
			
			villageKingLocation.add("F");
			villagePathKing.setText("" + villageKingLocation);}

		if((e.getSource() == villageG) == true){
	
			villageKingLocation.add("G");
			villagePathKing.setText("" + villageKingLocation);}
		
		if((e.getSource() == villageH) == true){
			
			villageKingLocation.add("H");
			villagePathKing.setText("" + villageKingLocation);}
		
		if((e.getSource() == villageI) == true){
			
			villageKingLocation.add("I");
			villagePathKing.setText("" + villageKingLocation);}

		if((e.getSource() == villageJ) == true){
	
			villageKingLocation.add("J");
			villagePathKing.setText("" + villageKingLocation);}
		
		if((e.getSource() == villageK) == true){
			
			villageKingLocation.add("K");
			villagePathKing.setText("" + villageKingLocation);}
		
		if((e.getSource() == villageL) == true){
			
			villageKingLocation.add("L");
			villagePathKing.setText("" + villageKingLocation);}

		if((e.getSource() == villageM) == true){
	
			villageKingLocation.add("M");
			villagePathKing.setText("" + villageKingLocation);}
		
		if((e.getSource() == villageN) == true){
			
			villageKingLocation.add("N");
			villagePathKing.setText("" + villageKingLocation);}

		if((e.getSource() == villageO) == true){
	
			villageKingLocation.add("O");
			villagePathKing.setText("" + villageKingLocation);}
		
		if((e.getSource() == cancelVillagePathKing) == true){
			
			villageKingLocation.clear();
			villageKingLocation.add(matchNew.getBoard().getKing().getPosition());
			villagePathKing.setText("" + villageKingLocation);}
		
	}
	
	
	
	
	 @FXML
	 public void changePermitCards(ActionEvent e){
		 
		 if((e.getSource() == region1) == true){
			 selectedRegionChangePermitCards = 0;
		 }
		 
		 if((e.getSource() == region2) == true){
			 selectedRegionChangePermitCards = 1;
		 }
		 
		 if((e.getSource() == region3) == true){
			 selectedRegionChangePermitCards = 2;
		 }
		 
		 if((e.getSource() == buttonChangePermitCards) == true){
			 if( selectedRegionChangePermitCards<3){
				 //manda al server richiesta azione 	 
			 }
			 
			 else {
				 
				// Nothing selected.
					Alert alert = new Alert(AlertType.WARNING);
					alert.initOwner(Client.getPrimaryStage());
					alert.setTitle("No Selection");
					alert.setHeaderText("No region selected");
					alert.setContentText("Please select a region.");

					alert.showAndWait();
			 }
		 }
	}
	 
	 @FXML 
	 public void selectVillageBuildShop(ActionEvent e){
		boolean controlShopGamer = false;
		
		if((e.getSource() == villageA12)){ 
			selectedVillageBuildShop = "Arkon";
			controlShopGamer = controlShop("Arkon"); 
                        seeVillageBuildShop.setText("Arkon");
		 }
		 
		if((e.getSource() == villageB12) == true){
			selectedVillageBuildShop = "Burgen";
			controlShopGamer = controlShop("Burgen");
                        seeVillageBuildShop.setText("Burgen");
                }
			
		if((e.getSource() == villageC12) == true){
				
			selectedVillageBuildShop = "Castrum";
			controlShopGamer = controlShop("Castrum");
                        seeVillageBuildShop.setText("Castrum");
                }
			
		if((e.getSource() == villageD12) == true){
				
			selectedVillageBuildShop = "Dorful";
			controlShopGamer = controlShop("Dorful");
                        seeVillageBuildShop.setText("Dorful");
                }
			
			
		if((e.getSource() == villageE12) == true){
		
			selectedVillageBuildShop  = "Esti";
			controlShopGamer = controlShop("Esti");
                        seeVillageBuildShop.setText("Esti");
                }
			
		if((e.getSource() == villageF12) == true){
				
			selectedVillageBuildShop  = "Framek";
			controlShopGamer = controlShop("Framek");
                        seeVillageBuildShop.setText("Framek");
                }
		
		if((e.getSource() == villageG12) == true){
		
			selectedVillageBuildShop  = "Graden";
			controlShopGamer = controlShop("Graden");
                        seeVillageBuildShop.setText("Graden");
                }
			
		if((e.getSource() == villageH12) == true){
				
			selectedVillageBuildShop  = "Hellar";
			controlShopGamer = controlShop("Hellar");
                        seeVillageBuildShop.setText("Hellar");
                }
			
		if((e.getSource() == villageI12) == true){
				
			selectedVillageBuildShop = "Indur";
			controlShopGamer = controlShop("Indur");
                         seeVillageBuildShop.setText("Indur");
                }

		if((e.getSource() == villageJ12) == true){
		
			selectedVillageBuildShop = "Juvelar";
			controlShopGamer = controlShop("Juvelar");
                        seeVillageBuildShop.setText("Juvelar");
                }
			
		if((e.getSource() == villageK12) == true){
				
			selectedVillageBuildShop = "Kultos";
			controlShopGamer = controlShop("Kultos");
                        seeVillageBuildShop.setText("Kultos");
                }
			
		if((e.getSource() == villageL12) == true){
			
			selectedVillageBuildShop = "Lyram";
			controlShopGamer = controlShop("Lyram");
                        seeVillageBuildShop.setText("Lyram");
                }

		if((e.getSource() == villageM12) == true){
		
			selectedVillageBuildShop = "Merkatim";
			controlShopGamer = controlShop("Merkatim");
                        seeVillageBuildShop.setText("Merkatim");
                }
			
		if((e.getSource() == villageN12) == true){
				
			selectedVillageBuildShop = "Naris";
			controlShopGamer = controlShop("Naris");
                        seeVillageBuildShop.setText("Naris");
                }

		if((e.getSource() == villageO12) == true){
		
			selectedVillageBuildShop = "Osium";
			controlShopGamer = controlShop("Osium");
                        seeVillageBuildShop.setText("Osium");
                }
				 
		 
		 if(controlShopGamer == true){
			 // Show the error message.
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(Client.getPrimaryStage());
	            alert.setTitle("Invalid Fields");
	            alert.setHeaderText("Hai già un emporio in questo villaggio\n");
	            alert.setContentText("seleziona un'altro villaggio");

	            alert.showAndWait(); 
		 }
		 
	 }
	 
	 // CHIEDERE
	 
	 public boolean controlVillagePermitCard(String nameVillage, PermitCard permitCard){
		
		boolean control = false;
                boolean trovato = false ;
		Iterator<Character> it = permitCard.getVillages().iterator();	
		while(it.hasNext()){
                    String name = "" + it.next();
                    if(name.equals(nameVillage)) {
                        control = true ;
                        break;
                    }
                }
                
                if(control == false) ; //gestire il fatto che non è stato trovato

//		control = permitCard.verifyVillage(nameVillage);
		
			
		 return control;
	 }
	 
	 
	 
	 @FXML
	 public void actionBuildShop(ActionEvent e){
            
            if((e.getSource() == buildShop) == true){
             
		// passa al server i dati metodo(PermitCard permitCardBuild, String selectedVillageBuildShop) 
            }

	 }
	 
	 /**
	  * Controlla se il giocatore ha un emporio in quel villaggio
	  * @param nameVillage nome del villaggio in cui cercare
	  * @return default false, ritorna true se il giocatore ha un emporio in quel villaggio
	  */
	
	 public boolean controlShop(String nameVillage){
		 boolean control = false;
		 
		 Village[] villages1= new Village[15];
		 villages1 = be.getGameMap().getVillages(); 
		 String[] shops = new String[7];
			for(Village tmp : villages1){
				if(tmp.getName().equals(nameVillage)){
                                    
					shops = tmp.getShops();
                                        break;
				}
			}
// Controlla che il nome del giocatore sia all interno del villaggio			
		for(int i = 0; i<7; i++){
			if(shops[i].equals(ClientLogic.getInstance().getUsername())){
				control = true;	
			}
		}
		 
		return control;
	 }
	 
	 
	 
	 
	 public void setBonusSingleTab(){
		 if(controlShop("Arkon") == false){
			 villageA1.setDisable(true);		 
		 }
		 
		 if(controlShop("Burgen") == false){
			 villageB1.setDisable(true);		 
		 }
		 
		 if(controlShop("Castrum") == false){
			 villageC1.setDisable(true);		 
		 }
		 
		 if(controlShop("Dorful") == false){
			 villageD1.setDisable(true);		 
		 }
		 
		 if(controlShop("Esti") == false){
			 villageE1.setDisable(true);		 
		 }
		 
		 if(controlShop("Framek") == false){
			 villageF1.setDisable(true);		 
		 }
		 
		 if(controlShop("Graden") == false){
			 villageG1.setDisable(true);		 
		 }
		 
		 if(controlShop("Hellar") == false){
			 villageH1.setDisable(true);		 
		 }
		 
		 if(controlShop("Indur") == false){
			 villageI1.setDisable(true);		 
		 }
		 
		 if(controlShop("Juvelar") == false){
			 villageJ1.setDisable(true);		 
		 }
		 
		 if(controlShop("Kultos") == false){
			 villageK1.setDisable(true);		 
		 }
		 
		 if(controlShop("Lyram") == false){
			 villageL1.setDisable(true);		 
		 }
		 
		 if(controlShop("Merkatim") == false){
			 villageM1.setDisable(true);		 
		 }
		 
		 if(controlShop("Naris") == false){
			 villageN1.setDisable(true);		 
		 }
		 
		 if(controlShop("Osium") == false){
			 villageO1.setDisable(true);}
	 }
	 
 public void setVillageBuildShop(PermitCard permitCardBuild){
		 
		 if(controlVillagePermitCard("A", permitCardBuild) == false){
			 villageA12.setDisable(true);		 
		 }
		 
		 if(controlVillagePermitCard("B", permitCardBuild) == false){
			 villageB12.setDisable(true);		 
		 }
		 
		 if(controlVillagePermitCard("C", permitCardBuild) == false){
			 villageC12.setDisable(true);		 
		 }
		 
		 if(controlVillagePermitCard("D", permitCardBuild) == false){
			 villageD12.setDisable(true);		 
		 }
		 
		 if(controlVillagePermitCard("E", permitCardBuild) == false){
			 villageE12.setDisable(true);		 
		 }
		 
		 if(controlVillagePermitCard("F", permitCardBuild) == false){
			 villageF12.setDisable(true);		 
		 }
		 
		 if(controlVillagePermitCard("G", permitCardBuild) == false){
			 villageG12.setDisable(true);		 
		 }
		 
		 if(controlVillagePermitCard("H", permitCardBuild) == false){
			 villageH12.setDisable(true);		 
		 }
		 
		 if(controlVillagePermitCard("I", permitCardBuild) == false){
			 villageI12.setDisable(true);		 
		 }
		 
		 if(controlVillagePermitCard("J", permitCardBuild) == false){
			 villageJ12.setDisable(true);		 
		 }
		 
		 if(controlVillagePermitCard("K", permitCardBuild) == false){
			 villageK12.setDisable(true);		 
		 }
		 
		 if(controlVillagePermitCard("L", permitCardBuild) == false){
			 villageL12.setDisable(true);		 
		 }
		 
		 if(controlVillagePermitCard("M", permitCardBuild) == false){
			 villageM12.setDisable(true);		 
		 }
		 
		 if(controlVillagePermitCard("N", permitCardBuild) == false){
			 villageN12.setDisable(true);		 
		 }
		 
		 if(controlVillagePermitCard("O", permitCardBuild) == false){
			 villageO12.setDisable(true);		 
		 }
	 }
	 
	 
	 public void clickSpecialActionTitledPane(){
		 setBonusSingleTab();
	 }
	 
	 
	 
	 @FXML 
	 public void selectVillageSingleBonus(ActionEvent e){
		 
			   if((e.getSource() == villageA1) == true){
				
				selectedVillageSingolBonus = "Arkon";
				see1.setText("" + selectedVillageSingolBonus );}
				
				if((e.getSource() == villageB1) == true){
					
					selectedVillageSingolBonus = "Burgen";
					see1.setText("" + selectedVillageSingolBonus );}
				
				if((e.getSource() == villageC1) == true){
					
					selectedVillageSingolBonus = "Castrum";
					see1.setText("" + selectedVillageSingolBonus );}
				
				if((e.getSource() == villageD1) == true){
					
					selectedVillageSingolBonus = "Dorful";
					see1.setText("" + selectedVillageSingolBonus );}
				
				
				if((e.getSource() == villageE1) == true){
			
					selectedVillageSingolBonus = "Esti";
					see1.setText("" + selectedVillageSingolBonus );}
				
				if((e.getSource() == villageF1) == true){
					
					selectedVillageSingolBonus = "Framek";
					see1.setText("" + selectedVillageSingolBonus );}

				if((e.getSource() == villageG1) == true){
			
					selectedVillageSingolBonus = "Graden";
					see1.setText("" + selectedVillageSingolBonus );}
				
				if((e.getSource() == villageH1) == true){
					
					selectedVillageSingolBonus = "Hellar";
					see1.setText("" + selectedVillageSingolBonus );}
				
				if((e.getSource() == villageI1) == true){
					
					selectedVillageSingolBonus = "Indur";
					see1.setText("" + selectedVillageSingolBonus );}

				if((e.getSource() == villageJ1) == true){
			
					selectedVillageSingolBonus = "Juvelar";
					see1.setText("" + selectedVillageSingolBonus );}
				
				if((e.getSource() == villageK1) == true){
					
					selectedVillageSingolBonus = "Kultos";
					see1.setText("" + selectedVillageSingolBonus );}
				
				if((e.getSource() == villageL1) == true){
					
					selectedVillageSingolBonus = "Lyram";
					see1.setText("" + selectedVillageSingolBonus );}

				if((e.getSource() == villageM1) == true){
			
					selectedVillageSingolBonus = "Merkatim";
					see1.setText("" + selectedVillageSingolBonus );}
				
				if((e.getSource() == villageN1) == true){
					
					selectedVillageSingolBonus = "Naris";
					see1.setText("" + selectedVillageSingolBonus );}

				if((e.getSource() == villageO1) == true){
			
					selectedVillageSingolBonus = "Osium";
					see1.setText("" + selectedVillageSingolBonus );}
				
				if((e.getSource() == cancelVillagePathKing) == true){
					
					villageKingLocation.clear();
					villageKingLocation.add(matchNew.getBoard().getKing().getPosition());
					villagePathKing.setText("" + villageKingLocation);}
		 
	 }
		 
	 @FXML
	 public void selectRegionSpecialAction(ActionEvent e){
		 
		 if((e.getSource() == regionSpecialAction0) == true){
			 selectRegionSpecialAction = 0;
		 }
		 
		 if((e.getSource() == regionSpecialAction1) == true){
			 selectRegionSpecialAction = 1;
		 }
		 
		 if((e.getSource() == regionSpecialAction2) == true){
			 selectRegionSpecialAction = 2;
		 }
		 
		 if((e.getSource() == buttonSelectRegionSpecialAction) == true){
			 if(  selectRegionSpecialAction<3){
				 anchorTakePermitCard.setVisible(true);
                                 op.setTextPermitCardVillageAction(selectRegionSpecialAction, regionPermitCard, villagesRegionCardSpecialAction0, villagesRegionCardSpecialAction1);
				 //manda al server richiesta azione 	 
			 }
			 
			 else {
				 
				// Nothing selected.
					Alert alert = new Alert(AlertType.WARNING);
					alert.initOwner(Client.getPrimaryStage());
					alert.setTitle("No Selection");
					alert.setHeaderText("No region selected");
					alert.setContentText("Please select a region.");

					alert.showAndWait();
			 }
		 }
	}
	 
	@FXML
	public void acquirePermitCard(ActionEvent e){
		
		 if((e.getSource() == selectPermitCardTake0) == true){	 
			 permitCardSpecialActionAcquire = regionPermitCard[selectRegionSpecialAction][0] ;	 
		 }
		 
			
		 if((e.getSource() == selectPermitCardTake1) == true){	 
			 permitCardSpecialActionAcquire = regionPermitCard[selectRegionSpecialAction][1] ;	 
		 }
		 
		 if((e.getSource() == clickAcquirePermitCard) == true){	 
			 
			 // manda al server la permitCardSpecialAction

		 } 
		 
		 if((e.getSource() == clickAcquireBonusPermitCard) == true){	 
			 
			 // manda al server la permitCardSpecialActionBonus

		 } 
		 
	}
	
	
	@FXML 
	public void selectVillageDoubleBonus(ActionEvent e){
		
				String errorMessage = "";
			 
		if((e.getSource() == villageA11) == true){
		        	
		   if(selectedVillageDoubleBonus[1] == " "){
			   if(selectedVillageDoubleBonus[0] == " "){
		        	selectedVillageDoubleBonus[0] = "Arkon";
		        	villageA11.setDisable(true);}
			   else{ selectedVillageDoubleBonus[1] = "Arkon";	
		    }
		   }
		   
		   
		      	
		   else errorMessage += "Hai già selezionato due villaggi, fai la tua mossa\n";
		   see11.setText("" + selectedVillageDoubleBonus[0] + "," + selectedVillageDoubleBonus[1] );
		        	
		}
				
		if((e.getSource() == villageB11) == true){
					
			
			if(selectedVillageDoubleBonus[1] == " "){
				if(selectedVillageDoubleBonus[0] == " "){
		
		        	selectedVillageDoubleBonus[0] = "Burgen";
		        	villageB11.setDisable(true);
		        }	
			
				else selectedVillageDoubleBonus[1] = "Burgen";
			}
		
			else  errorMessage += "Hai già selezionato due villaggi, fai la tua mossa\n";
			
			see11.setText("" + selectedVillageDoubleBonus[0] + "," + selectedVillageDoubleBonus[1] );
					
		}
				
		if((e.getSource() == villageC11) == true){
			if(selectedVillageDoubleBonus[1] == " "){
				if(selectedVillageDoubleBonus[0] == " "){
		        		selectedVillageDoubleBonus[0] = "Castrum";
		        		villageC11.setDisable(true);
		        }		
				else selectedVillageDoubleBonus[1] = "Castrum";	
			}	
			
			else  errorMessage += "Hai già selezionato due villaggi, fai la tua mossa\n";
							
			see11.setText("" + selectedVillageDoubleBonus[0] + "," + selectedVillageDoubleBonus[1]);
					
		}
				
		if((e.getSource() == villageD11) == true){
			if(selectedVillageDoubleBonus[1] == " "){
				if(selectedVillageDoubleBonus[0] == " "){
					selectedVillageDoubleBonus[0] = "Dorful";
					villageD11.setDisable(true);		
				}
				else selectedVillageDoubleBonus[1] = "Dorful"; 
			}
			
			else  errorMessage += "Hai già selezionato due villaggi, fai la tua mossa\n";
					
			see11.setText("" + selectedVillageDoubleBonus[0] + "," + selectedVillageDoubleBonus[1] );
		}
				
				
		if((e.getSource() == villageE11) == true){
			if(selectedVillageDoubleBonus[1] == " "){
				if(selectedVillageDoubleBonus[0] == " "){
					selectedVillageDoubleBonus[0] =  "Esti";
					villageE11.setDisable(true);	
				}
				else	selectedVillageDoubleBonus[1] = "Esti"; 
			}
	
			else  errorMessage += "Hai gi�àselezionato due villaggi, fai la tua mossa\n";
					
			see11.setText("" + selectedVillageDoubleBonus[0] + "," + selectedVillageDoubleBonus[1] );
		}
				
		
		if((e.getSource() == villageF11) == true){
			
			if(selectedVillageDoubleBonus[1] == " "){	
				if(selectedVillageDoubleBonus[0] == " "){
					selectedVillageDoubleBonus[0] =  "Framek";
					villageF11.setDisable(true);	
					}
				else selectedVillageDoubleBonus[1] = "Framek"; 
			}
				
			else  errorMessage += "Hai già selezionato due villaggi, fai la tua mossa\n";
			
			see11.setText("" + selectedVillageDoubleBonus[0] + "," + selectedVillageDoubleBonus[1]);
		}

		
		if((e.getSource() == villageG11) == true){
			if(selectedVillageDoubleBonus[1] == " "){
				if(selectedVillageDoubleBonus[0] == " "){
					selectedVillageDoubleBonus[0] = "Graden";
					villageG11.setDisable(true);	
					}	
				else selectedVillageDoubleBonus[1] = "Graden"; 
			}
			
			else  errorMessage += "Hai già selezionato due villaggi, fai la tua mossa\n";
					
			see11.setText("" + selectedVillageDoubleBonus[0] + "," + selectedVillageDoubleBonus[1] );			
		}
				
		if((e.getSource() == villageH11) == true){
			if(selectedVillageDoubleBonus[1] == " "){		
				if(selectedVillageDoubleBonus[0] == " "){
					selectedVillageDoubleBonus[0] = "Hellar";
					villageH11.setDisable(true);	
				}
				else selectedVillageDoubleBonus[1] = "Hellar";	 
			}
			
			else  errorMessage += "Hai già selezionato due villaggi, fai la tua mossa\n";
					
			see11.setText("" + selectedVillageDoubleBonus[0] + "," + selectedVillageDoubleBonus[1]);		
		}
					
	
		if((e.getSource() == villageI11) == true){
			if(selectedVillageDoubleBonus[1] == " "){		
				if(selectedVillageDoubleBonus[0] == " "){
					selectedVillageDoubleBonus[0] = "Indur";
					villageI11.setDisable(true);	
				}
				else selectedVillageDoubleBonus[1] = "Indur";
			}
			
			else  errorMessage += "Hai già selezionato due villaggi, fai la tua mossa\n";
					
			see11.setText("" + selectedVillageDoubleBonus[0] + "," + selectedVillageDoubleBonus[1]);
		}

		if((e.getSource() == villageJ11) == true){
			if(selectedVillageDoubleBonus[1] == " "){
				if(selectedVillageDoubleBonus[0] == " "){
					selectedVillageDoubleBonus[0] = "Juvelar";
					villageJ11.setDisable(true);	
				}
				else selectedVillageDoubleBonus[1] = "Juvelar";
			}
		
			else  errorMessage += "Hai già selezionato due villaggi, fai la tua mossa\n";
					
			see11.setText("" + selectedVillageDoubleBonus[0] + "," + selectedVillageDoubleBonus[1]);
					
		}
				
		if((e.getSource() == villageK11) == true){
			if(selectedVillageDoubleBonus[1] == " "){	
				if(selectedVillageDoubleBonus[0] == " "){
					selectedVillageDoubleBonus[0] = "Kultos";
					villageK11.setDisable(true);	
					}
				else selectedVillageDoubleBonus[1] = "Kultos"; 
			}
			
			else  errorMessage += "Hai già selezionato due villaggi, fai la tua mossa\n";
					
			see11.setText("" + selectedVillageDoubleBonus[0] + "," + selectedVillageDoubleBonus[1]);
		}
				
		if((e.getSource() == villageL11) == true){
			if(selectedVillageDoubleBonus[1] == " "){		
				if(selectedVillageDoubleBonus[0] == " "){
					selectedVillageDoubleBonus[0] = "Lyram";
					villageL11.setDisable(true);	
					}
				else selectedVillageDoubleBonus[1] = "Lyram"; 
			}
			else  errorMessage += "Hai già selezionato due villaggi, fai la tua mossa\n";
					
			see11.setText("" + selectedVillageDoubleBonus[0] + "," + selectedVillageDoubleBonus[1]);
					
		}

		if((e.getSource() == villageM11) == true){
			if(selectedVillageDoubleBonus[1] == " "){
				if(selectedVillageDoubleBonus[0] == " "){
					selectedVillageDoubleBonus[0] = "Merkatim";
					villageM11.setDisable(true);	
					}
				else selectedVillageDoubleBonus[1] = "Merkatim"; 
			}
			
			else  errorMessage += "Hai già selezionato due villaggi, fai la tua mossa\n";
			
			see11.setText("" + selectedVillageDoubleBonus[0] + "," + selectedVillageDoubleBonus[1]);
		}
				
		if((e.getSource() == villageN11) == true){
			if(selectedVillageDoubleBonus[1] == " "){		
				if(selectedVillageDoubleBonus[0] == " "){
					selectedVillageDoubleBonus[0] = "Naris";
					villageN11.setDisable(true);	
				}	
				else selectedVillageDoubleBonus[1] = "Naris"; 
			}
			
			else  errorMessage += "Hai già selezionato due villaggi, fai la tua mossa\n";
					
			see11.setText("" + selectedVillageDoubleBonus[0] + "," + selectedVillageDoubleBonus[1]);
					
		}

		if((e.getSource() == villageO11) == true){
			if(selectedVillageDoubleBonus[1] == " "){
				if(selectedVillageDoubleBonus[0] == " "){
					selectedVillageDoubleBonus[0] = "Osium";
					villageO11.setDisable(true);	
				}
				else selectedVillageDoubleBonus[1] = "Osium";
			}
			
			else  errorMessage += "Hai già selezionato due villaggi, fai la tua mossa\n";
					
			see11.setText("" + selectedVillageDoubleBonus[0] + "," + selectedVillageDoubleBonus[1]);
					
			}
				
//				if((e.getSource() == cancelVillagePathKing) == true){
//					
//					villageKingLocation.clear();
//					villagePathKing.setText("" + villageKingLocation);}
		
				if (errorMessage.length() == 0) {
					
					
				}
				
				else{
		            // Show the error message.
		            Alert alert = new Alert(AlertType.ERROR);
		            alert.initOwner(Client.getPrimaryStage());
		            alert.setTitle("Invalid Fields");
		            alert.setHeaderText("Hai già selezionato due villaggi, fai la tua mossa\n");
		            alert.setContentText(errorMessage);

		            alert.showAndWait();
				}
				
		}
	
	
	@FXML
	public void showBonusNobility(ActionEvent e){
		
		if((e.getSource() == pathBonus0) == true){	
			new Client().showBonusNobilityPath(0);
		}
		
		if((e.getSource() == pathBonus1) == true){	
			new Client().showBonusNobilityPath(1);
		}
		
		if((e.getSource() == pathBonus2) == true){	
			new Client().showBonusNobilityPath(2);
		}
		
		if((e.getSource() == pathBonus3) == true){	
			new Client().showBonusNobilityPath(3);
		}
		
		if((e.getSource() == pathBonus4) == true){	
			new Client().showBonusNobilityPath(4);
		}
		
		if((e.getSource() == pathBonus5) == true){	
			new Client().showBonusNobilityPath(5);
		}
		
		if((e.getSource() == pathBonus6) == true){	
			new Client().showBonusNobilityPath(6);
		}
		
		if((e.getSource() == pathBonus7) == true){	
			new Client().showBonusNobilityPath(7);
		}
		
		if((e.getSource() == pathBonus8) == true){	
			new Client().showBonusNobilityPath(8);
		}
		
		if((e.getSource() == pathBonus9) == true){	
			new Client().showBonusNobilityPath(9);
		}
		
		if((e.getSource() == pathBonus10) == true){	
			new Client().showBonusNobilityPath(10);
		}
		
		if((e.getSource() == pathBonus11) == true){	
			new Client().showBonusNobilityPath(11);
		}
		
		if((e.getSource() == pathBonus12) == true){	
			new Client().showBonusNobilityPath(12);
		}
		
		if((e.getSource() == pathBonus13) == true){	
			new Client().showBonusNobilityPath(13);
		}
		
		
		if((e.getSource() == pathBonus14) == true){	
			new Client().showBonusNobilityPath(14);
		}
		
		if((e.getSource() == pathBonus15) == true){	
			new Client().showBonusNobilityPath(15);
		}
		
		if((e.getSource() == pathBonus16) == true){	
			new Client().showBonusNobilityPath(16);
		}
		
		if((e.getSource() == pathBonus17) == true){	
			new Client().showBonusNobilityPath(17);
		}
		
		if((e.getSource() == pathBonus18) == true){	
			new Client().showBonusNobilityPath(18);
		}
		
		if((e.getSource() == pathBonus19) == true){	
			new Client().showBonusNobilityPath(19);
		}
		
		if((e.getSource() == pathBonus20) == true){	
			new Client().showBonusNobilityPath(20);
		}	
	}
        
        
        //avviare il market 
        
        @FXML 
        public void showMarketStage(){
        
            new Client().showSaleMarket(gamer);
        }
        
        
        	// azioni da mandare al server
	@FXML
	public void clickActionElectCouncil(ActionEvent e) {

		if((e.getSource() == electNobil ) == true){ 
			if (selectColorNobil != null) {
				// metodoMandaDati(selectCouncilRegion1, selectColorNobil);
                                //controllare che le azioni principali sono finite
				//azioniPrincipali.setDisable(true);
                                
                                selectedRegionPermitCard1 = -1;
                                selectColorNobil = null;
                                setCouncilRegion(0, council1nobil0, council1nobil1, council1nobil2, council1nobil3);
                                // quando ricevo il match dal server
                                //upgradeBoard(Match match);
                                //upgradeGamer(ClientLogic.getInstance().getGamer());
                                //aggioranare dati del giocatore;
                                
				accordion.setExpandedPane(fastActionTitledPane);
			}

			if (selectColorNobil == null) {

				// dialog stage
			}
		
		}
		
		if((e.getSource() == electFastNobil) == true){ 
			if (selectColorElectFastNobil != null) {
				// metodoMandaDati(selectedRegionElectFastNobil, selectColorElectFastNobil);
			
			}

			if (selectColorNobil == null) {

				// dialog stage
			}
		
		}	
	}
        
        
        @FXML
        public void actionBuildWithKing(ActionEvent e){
        
            if((e.getSource() == buildWithKing)){
       
                    // manda a server metotdo(ArrayList villageKingLocation)             
            }
        }
	 
	
	@FXML
	public void actionAcquireDoubleBonus(ActionEvent e){
		
		if((e.getSource() == acquireVillageDoubleBonus) == true){
			
			// manda al server  selectedVillageDoubleBonus[]
			
		}
		
		
	}
        
        @FXML
        public void actionAcquireSingleBonus(){
		 
		 // manda segnale al server con selectedVillageActionBonus
		 
		 
	 }
        
        @FXML
        public void actionBuyPermitCard(ActionEvent e){
            
            if((e.getSource() == actionBuyPermitCardButton) == true){
                    selectPermitCardBuy0.setDisable(false);
                    selectPermitCardBuy1.setDisable(false);
                    // manda al server (int selectedRegionPermitCard2, ArrayList<PoliticalCard> selectedPoliticalCards,PermitCard selectedPermitCardBuy )
            }
         
        }
}
