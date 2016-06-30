package main.view;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;






public class FxmlMatchGameController {
	
	@FXML Label helpersPlayer;
	@FXML private ImageView emporio;
	@FXML  ImageView map;
	@FXML  Circle button;
	
	// pulsanti per vedere gli empori
	@FXML  Button buttonShopA;
	@FXML  Button buttonShopB;
	@FXML  Button buttonShopC;
	@FXML  Button buttonShopD;
	@FXML  Button buttonShopE;
	@FXML  Button buttonShopF;
	@FXML  Button buttonShopG;
	@FXML  Button buttonShopH;
	@FXML  Button buttonShopI;
	@FXML  Button buttonShopJ;
	@FXML  Button buttonShopK;
	@FXML  Button buttonShopL;
	@FXML  Button buttonShopM;
	@FXML  Button buttonShopN;
	@FXML  Button buttonShopO;
	
	//pulsanti per vedere i bonus dei villaggi
	@FXML  Button arkonBonus;
	@FXML  Button burgenBonus;
	@FXML  Button castrumBonus;
	@FXML  Button dorfulBonus;
	@FXML  Button estiBonus;
	@FXML  Button framekBonus;
	@FXML  Button gradenBonus;
	@FXML  Button hellarBonus;
	@FXML  Button indurBonus;
	@FXML  Button kultosBonus;
	@FXML  Button lyramBonus;
	@FXML  Button merkatimBonus;
	@FXML  Button narisBonus;
	@FXML  Button osiumBonus;
	
	// Pulsanti bonus carte regione
	@FXML  Button region0Card0;
	@FXML  Button region0Card1;
	@FXML  Button region1Card0;
	@FXML  Button region1Card1;
	@FXML  Button region2Card0;
	@FXML  Button region2Card1;
	
	// Pulsanti bonus carte regione azioni principali
	@FXML  Button regionCard0;
	@FXML  Button regionCard1;
	
	//Pulsanti bonus carte regione azioni speciali
	@FXML  Button regionCardSpecialAction0;
	@FXML  Button regionCardSpecialAction1;
	
	// bonus carte gamer dati gioco
	@FXML  Button bonusPermitCardGamer;
	
	//bonus carte giocatore azione speciale
	
	@FXML  Button bonusUnusedPermitCardGamer;
	@FXML  Button bonusUsedPermitCardGamer;
	
	 // label carte permesso regione della board
	@FXML  Label villagesRegion0Card0;
	@FXML  Label villagesRegion0Card1;
	@FXML  Label villagesRegion1Card0;
	@FXML  Label villagesRegion1Card1;
	@FXML  Label villagesRegion2Card0;
	@FXML  Label villagesRegion2Card1;
	
	// label carte permesso regione delle azioni principali
	@FXML  Label villagesRegionCard0;
	@FXML  Label villagesRegionCard1;
	
	
	// label carte permesso regione delle azioni speciali
	@FXML  Label villagesRegionCardSpecialAction0;
	@FXML  Label villagesRegionCardSpecialAction1;
	
	// nobil of board
	@FXML  ImageView nobil00;
	@FXML  ImageView nobil01;
	@FXML  ImageView nobil02;
	@FXML  ImageView nobil03;
	@FXML  ImageView nobil10;
	@FXML  ImageView nobil11;
	@FXML  ImageView nobil12;
	@FXML  ImageView nobil13;
	@FXML  ImageView nobil20;
	@FXML  ImageView nobil21;
	@FXML  ImageView nobil22;
	@FXML  ImageView nobil23;
	
	//nobil of King
	@FXML  ImageView nobilKing0;
	@FXML  ImageView nobilKing1;
	@FXML  ImageView nobilKing2;
	@FXML  ImageView nobilKing3;
	
	//ImageView nobil of principal action
	@FXML  ImageView council1nobil0;
	@FXML  ImageView council1nobil1;
	@FXML  ImageView council1nobil2;
	@FXML  ImageView council1nobil3;
	@FXML  ImageView council2nobil0;
	@FXML  ImageView council2nobil1;
	@FXML  ImageView council2nobil2;
	@FXML  ImageView council2nobil3;
	@FXML  ImageView councilKingNobil0;
	@FXML  ImageView councilKingNobil1;
	@FXML  ImageView councilKingNobil2;
	@FXML  ImageView councilKingNobil3;
	
	//ImageView dei nobili: azioni veloci
	
	@FXML  ImageView councilElectFastNobil0;
	@FXML  ImageView councilElectFastNobil1;
	@FXML  ImageView councilElectFastNobil2;
	@FXML  ImageView councilElectFastNobil3;
	
	// Immagini bonus di regione
	
	@FXML  ImageView bonusRegion0;
	@FXML  ImageView bonusRegion1;
	@FXML  ImageView bonusRegion2;
	
	// Immagini bonus di colore villaggio
	
	@FXML  ImageView bonusGiallo;
	@FXML  ImageView bonusArancio;
	@FXML  ImageView bonusAzzurro;
	@FXML  ImageView bonusGrigio;
	
	// Immagini bonus re
	
	@FXML  ImageView bonusRe0;
	@FXML  ImageView bonusRe1;
	@FXML  ImageView bonusRe2;
	@FXML  ImageView bonusRe3;	
	@FXML  ImageView bonusRe4;

	
	
	
	
	

	
	// scelta colore consigliere azione principale
	
	@FXML  RadioButton nobilWhite;
	@FXML  RadioButton nobilBlack;
	@FXML  RadioButton nobilCyan;
	@FXML  RadioButton nobilOrange;
	@FXML  RadioButton nobilMagenta;
	@FXML  RadioButton nobilPink;
	
	
	// scelta colore consigliere azione veloce
	
	@FXML  RadioButton fastNobilWhite;
	@FXML  RadioButton fastNobilBlack;
	@FXML  RadioButton fastNobilCyan;
	@FXML  RadioButton fastNobilOrange;
	@FXML  RadioButton fastNobilMagenta;
	@FXML  RadioButton fastNobilPink;
	
	// set TabPlayer
	
	@FXML  Label coinsPlayer0;
	@FXML  Label coinsPlayer1;
	@FXML  Label coinsPlayer2;
	@FXML  Label coinsPlayer3;
	@FXML  Label coinsPlayer4;
	@FXML  Label coinsPlayer5;
	@FXML  Label coinsPlayer6;
	@FXML  Label nobilityPathPlayer0;
	@FXML  Label nobilityPathPlayer1;
	@FXML  Label nobilityPathPlayer2;
	@FXML  Label nobilityPathPlayer3;
	@FXML  Label nobilityPathPlayer4;
	@FXML  Label nobilityPathPlayer5;
	@FXML  Label nobilityPathPlayer6;
	@FXML  Label pointsPlayer0;
	@FXML  Label pointsPlayer1;
	@FXML  Label pointsPlayer2;
	@FXML  Label pointsPlayer3;
	@FXML  Label pointsPlayer4;
	@FXML  Label pointsPlayer5;
	@FXML  Label pointsPlayer6;
	@FXML  Label usernamePlayer0;
	@FXML  Label usernamePlayer1;
	@FXML  Label usernamePlayer2;
	@FXML  Label usernamePlayer3;
	@FXML  Label usernamePlayer4;
	@FXML  Label usernamePlayer5;
	@FXML  Label usernamePlayer6;
	
	
	
	// Label per mostrare le citt� delle carte permesso del giocatore
	@FXML  Label villagePermitCardGamerLabel;
	@FXML  Label villagePermitCardBuildLabel;
	
	//Label per mostrare le citt� delle carte permesso del giocatore per azione speciale
	@FXML  Label villageUnusedPermitCardGamerLabel;
	@FXML  Label villageUsedPermitCardGamerLabel;
	
	// Seleziona consiglio per comprare carta permesso
	
	@FXML  Button selectCouncilBuyPermitCard;
	
	// seleziona consiglio per azione eleggi nobile azione principale e azione eleggi nobile azione veloce
	@FXML  Button selectCouncilElectNobil;
	@FXML  Button selectCouncilElectFastNobil;
	
	// seleziona oggetti di interesse per azione aiuto del re
	@FXML  Button selectCouncilKing;
	@FXML  Button selectPoliticalCardOk;
	@FXML  Button selectPoliticalCardOkKing;
	
	
	//seleziona regione per acquisire una nuova carta permesso(azione speciale)
	@FXML  Button buttonSelectRegionSpecialAction;
	
	//seleziona carte permesso da comprare
	@FXML  Button selectPermitCardBuy0;
	@FXML  Button selectPermitCardBuy1;
	
	// seleziona carta permesso per costruire emporio
	@FXML  Button selectPermitCardBuild;
	
	//seleziona carta permesso per acquisire nuovamente il bonus (azioni speciali)
	@FXML  Button acquireVillageSingleBonus;
	
	//seleziona carta permesso per acquisire nuovamente il bonus di due villaggi(azioni speciali)
	@FXML  Button acquireVillageDoubleBonus;
	
	//seleziona carta permesso per acquisire bonus tra quelle usate e non (azioni speciali)
	@FXML  Button selectUnusedPermitCardBonus;
	@FXML  Button selectUsedPermitCardBonus;
	
	
	//seleziona carta permesso senza pagare (azioni speciali) 
	@FXML  Button selectPermitCardTake0;
	@FXML  Button selectPermitCardTake1;
	
	// pulsanti seleziona villaggio per il re
	
	@FXML  Button villageA;
	@FXML  Button villageB;
	@FXML  Button villageC;
	@FXML  Button villageD;
	@FXML  Button villageE;
	@FXML  Button villageF;
	@FXML  Button villageG;
	@FXML  Button villageH;
	@FXML  Button villageI;
	@FXML  Button villageJ;
	@FXML  Button villageK;
	@FXML  Button villageL;
	@FXML  Button villageM;
	@FXML  Button villageN;
	@FXML  Button villageO;
	
	//pulsanti seleziona citta per ottenere il bonus singolo
	
	
	@FXML  Button villageA1;
	@FXML  Button villageB1;
	@FXML  Button villageC1;
	@FXML  Button villageD1;
	@FXML  Button villageE1;
	@FXML  Button villageF1;
	@FXML  Button villageG1;
	@FXML  Button villageH1;
	@FXML  Button villageI1;
	@FXML  Button villageJ1;
	@FXML  Button villageK1;
	@FXML  Button villageL1;
	@FXML  Button villageM1;
	@FXML  Button villageN1;
	@FXML  Button villageO1;
	
	//pulsanti seleziona villagi per bonus doppio (azione speciale)
	
	@FXML  Button villageA11;
	@FXML  Button villageB11;
	@FXML  Button villageC11;
	@FXML  Button villageD11;
	@FXML  Button villageE11;
	@FXML  Button villageF11;
	@FXML  Button villageG11;
	@FXML  Button villageH11;
	@FXML  Button villageI11;
	@FXML  Button villageJ11;
	@FXML  Button villageK11;
	@FXML  Button villageL11;
	@FXML  Button villageM11;
	@FXML  Button villageN11;
	@FXML  Button villageO11;
	
	//pulsanti seleziona villagi per costruisci emporio(azione princiaple)
	
	@FXML  Button villageA12;
	@FXML  Button villageB12;
	@FXML  Button villageC12;
	@FXML  Button villageD12;
	@FXML  Button villageE12;
	@FXML  Button villageF12;
	@FXML  Button villageG12;
	@FXML  Button villageH12;
	@FXML  Button villageI12;
	@FXML  Button villageJ12;
	@FXML  Button villageK12;
	@FXML  Button villageL12;
	@FXML  Button villageM12;
	@FXML  Button villageN12;
	@FXML  Button villageO12;
	
	// pulsanti bonus nobilityPath
	
	@FXML Button pathBonus0;
	@FXML Button pathBonus1;
	@FXML Button pathBonus2;
	@FXML Button pathBonus3;
	@FXML Button pathBonus4;
	@FXML Button pathBonus5;
	@FXML Button pathBonus6;
	@FXML Button pathBonus7;
	@FXML Button pathBonus8;
	@FXML Button pathBonus9;
	@FXML Button pathBonus10;
	@FXML Button pathBonus11;
	@FXML Button pathBonus12;
	@FXML Button pathBonus13;
	@FXML Button pathBonus14;
	@FXML Button pathBonus15;
	@FXML Button pathBonus16;
	@FXML Button pathBonus17;
	@FXML Button pathBonus18;
	@FXML Button pathBonus19;
	@FXML Button pathBonus20;
	
	
	// pulsanti azione veloce cambia carte permesso
	
	@FXML  Button region1;
	@FXML  Button region2;
	@FXML  Button region3;
	@FXML  Button buttonChangePermitCards;
	
	//pulsanti azioni speciali scegli regione e prendi carta permesso
	
	@FXML  Button regionSpecialAction0;
	@FXML  Button regionSpecialAction1;
	@FXML  Button regionSpecialAction2;
	
	
	
	// pulsanti per switch
	@FXML  Button prevPermitCardBuild;
	@FXML  Button nextPermitCardBuild;
	
	@FXML  Button prevPermitCardGamer;
	@FXML  Button nextPermitCardGamer;
	
	@FXML  Button nextCouncil1;
	@FXML  Button prevCouncil1;
	
	@FXML  Button nextCouncil2;
	@FXML  Button prevCouncil2;
	
	@FXML  Button nextCouncilElectFastNobil;
	@FXML  Button prevCouncilElectFastNobil;
	
	//azione speciale
	@FXML  Button prevUnusedPermitCardGamer;
	@FXML  Button nextUnusedPermitCardGamer;

	@FXML  Button prevUsedPermitCardGamer;
	@FXML  Button nextUsedPermitCardGamer;
	
	// Panello dati e azioni
	
	@FXML TitledPane gameDataTitledPane;
	@FXML TitledPane mainActionTitledPane;
	@FXML TitledPane fastActionTitledPane;
	@FXML TitledPane specialActionTitledPane;
        
	
	@FXML Accordion  accordion;
	@FXML AnchorPane anchorSelectNobil;
	@FXML AnchorPane anchorSelectFastNobil;
	@FXML AnchorPane anchorSelectVillage;
	@FXML AnchorPane anchorSelectPoliticalCard;
	@FXML AnchorPane anchorSelectPoliticalCardKing;
	@FXML AnchorPane anchorSelectVillageBuildShop;
	@FXML AnchorPane anchorSelectPermitCard1;
	@FXML AnchorPane anchorSelectKingVillage;
	@FXML  AnchorPane anchorProprietaGiocatore;
	@FXML  AnchorPane anchorTakePermitCard;

	//set label region number
	@FXML  Label regionNumber1;
	@FXML  Label regionNumber2;
	@FXML  Label regionNumberElectFast;
	@FXML  Label regionOfKing;
	
	@FXML  Label kingLocation1;
	@FXML  Label kingLocation2;
	@FXML  Label villagePathKing;
	@FXML  Button cancelVillagePathKing;
	
	
	// azioni
	@FXML Button buildShop;
	@FXML Button electNobil;
	@FXML Button electFastNobil;
	@FXML Button clickAcquirePermitCard;
	@FXML Button clickAcquireBonusPermitCard;
        @FXML Button buildWithKing;
	
	
	
	@FXML Tab kingHelp;
	@FXML Tab bonusSingleTab;

	
	
	// Immagini emporio villaggi
	
	@FXML ImageView shopA;
	@FXML ImageView shopB;
	@FXML ImageView shopC;
	@FXML ImageView shopD;
	@FXML ImageView shopE;
	
	@FXML Label see;

	
	// vedi scelta villaggi bonus singolo (azione speciale)
	@FXML Label see1;
	
	// vedi scelta villaggi bonus doppio (azione speciale)
	@FXML Label see11;
	
	// vedi scelta villaggi bonus doppio (azione speciale)
	@FXML Label seeVillageBuildShopBuild;
	
        @FXML Button buttonMarket;
	 
 }	 

