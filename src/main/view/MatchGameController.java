package main.view;

import main.Client;
import main.ClientLogic;
import model.basics.Board;
import model.basics.Match;
import model.basics.PermitCard;
import model.basics.PoliticalCard;
import model.basics.Region;
import model.basics.builders.exceptions.BuilderException;
import model.basics.exceptions.GameMapException;
import mud.model.basic.interfaces.MatchRequest;

import java.util.Collection;
import java.util.LinkedList;

import examples.example1.BoardExample;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

public class MatchGameController {

	
	@FXML private Circle button;
	@FXML private Button buttonShopPlayers;
	@FXML private Button arkonBonus;
	@FXML private Button burgenBonus;
	@FXML private Button castrumBonus;
	@FXML private Button dorfulBonus;
	@FXML private Button estiBonus;
	@FXML private Button framekBonus;
	@FXML private Button gradenBonus;
	@FXML private Button hellarBonus;
	@FXML private Button indurBonus;
	@FXML private Button kultosBonus;
	@FXML private Button lyramBonus;
	@FXML private Button merkatimBonus;
	@FXML private Button narisBonus;
	@FXML private Button osiumBonus;
	@FXML private Button region0Card0;
	@FXML private Button region0Card1;
	@FXML private Button region1Card0;
	@FXML private Button region1Card1;
	@FXML private Button region2Card0;
	@FXML private Button region2Card1;
	@FXML private Label villagesRegion0Card0;
	@FXML private Label villagesRegion0Card1;
	@FXML private Label villagesRegion1Card0;
	@FXML private Label villagesRegion1Card1;
	@FXML private Label villagesRegion2Card0;
	@FXML private Label villagesRegion2Card1;
	@FXML private ImageView nobil00;
	@FXML private ImageView nobil01;
	@FXML private ImageView nobil02;
	@FXML private ImageView nobil03;
	@FXML private AnchorPane proprietaGiocatore;
	@FXML private RadioButton nobilWhite;
	@FXML private RadioButton nobilBlack;
	@FXML private RadioButton nobilCyan;
	private Board be;
	Collection<ImageView> imgsPoliticalCards;
	
	@FXML
	private ImageView emporio;
	
	@FXML
	private ImageView maps;
	
	String selectColorNobil;
	int selectCouncil;
	
	//private HBox hbPoliticalCard;
	//private HBox hbPermitCard;
	
	@FXML
    private void initialize() throws BuilderException, GameMapException {
		
//		imgsPoliticalCards = new LinkedList<ImageView>();
//		
		initComponentsView();
//		
		this.setVillagesCard();
	}
	
	protected void initComponentsView() throws BuilderException, GameMapException
	{
		be = new BoardExample().getBoard();
		ClientLogic.getInstance().setBoard(be);
		
		initializeCouncilRegion();
		
//		// initialize Hbox political card
//		hbPoliticalCard = new HBox();
//	    hbPoliticalCard.setPadding(new Insets(0, 10, 10, 10));
//	    hbPoliticalCard.setSpacing(5);
	
		// initialize Hbox permit card
//		hbPermitCard = new HBox();
//    	hbPermitCard.setPadding(new Insets(0, 10, 10, 10));
//   	hbPermitCard.setSpacing(5);
//	    
//	    // inizializzo altra roba ...
//	    
//	    
//	    
	}
	
	public void setTextVillagesCard(String s, int regioncard){
		
		if(regioncard == 00){
			villagesRegion0Card0.setText(s);
		}
	
		if(regioncard == 01){
			villagesRegion0Card1.setText(s);
		}
		
		if(regioncard == 10){
			villagesRegion1Card0.setText(s);
		}
		
		if(regioncard == 11){
			villagesRegion1Card1.setText(s);
		}
		
		if(regioncard == 20){
			villagesRegion2Card0.setText(s);
		}
		
		if(regioncard == 21){
			villagesRegion2Card1.setText(s);
		}
	}
	
	public void setVillagesCard(){
		
		PermitCard pc00 = be.getRegions()[0].getPermitCardsDeck().getAvailableCardsList().get(0);
		PermitCard pc01 =  be.getRegions()[0].getPermitCardsDeck().getAvailableCardsList().get(1);
		PermitCard pc10 =  be.getRegions()[1].getPermitCardsDeck().getAvailableCardsList().get(0);
		PermitCard pc11 =  be.getRegions()[1].getPermitCardsDeck().getAvailableCardsList().get(1);
		PermitCard pc20 =  be.getRegions()[2].getPermitCardsDeck().getAvailableCardsList().get(0);
		PermitCard pc21 =  be.getRegions()[2].getPermitCardsDeck().getAvailableCardsList().get(1);
		
		setTextVillagesCard("" + pc00.getVillages(), 00);
		setTextVillagesCard("" + pc01.getVillages(),01);
		setTextVillagesCard("" + pc10.getVillages(),10);
		setTextVillagesCard("" + pc11.getVillages(),11);
		setTextVillagesCard("" + pc20.getVillages(),20);
		setTextVillagesCard("" + pc21.getVillages(),21);
		

	}
	
	@FXML
	private void click(){
		
		Image image = new Image("main/view/image/cartapoliticaVerticale.png");

		
		
		ImageView politicalCarde = new ImageView(image);
		ImageView politicalCard2 = new ImageView(image);

		HBox hb = new HBox();
	    hb.setPadding(new Insets(0, 10, 10, 10));
	    hb.setSpacing(5);
	    hb.getChildren().addAll(politicalCarde, politicalCard2);
	
	    
	    proprietaGiocatore.getChildren().add(hb);
	
	
	    AnchorPane.setBottomAnchor(hb, 0.0);
	    AnchorPane.setLeftAnchor(hb, 5.0);

		//button.setFill(Color.RED);
	}

	
	
//	public void updatePoliticalCard()
//	{

//		
//		// rimuove la lista
//	    hbPoliticalCard.getChildren().removeAll(imgsPoliticalCards);
//		
//		// ricarica la lista
//		PoliticalCard[] mano = new PoliticalCard[10]; // prelevate dal gamer
//		imgsPoliticalCards.clear();
//		for( PoliticalCard c:mano ){
//			Image image;
//			if( c.getColor()==java.awt.Color.CYAN ){
//				image = new Image("main/view/image/cartapoliticaVerticale.png");
//			}
//			else if( c.getColor()==java.awt.Color.CYAN ){
//				image = new Image("main/view/image/cartapoliticaVerticale.png");
//			}
//			else {
//				// caso farlocco
//				continue;
//			}
//			
//			ImageView politicalCarde = new ImageView(image);
//			imgsPoliticalCards.add(politicalCarde);
//		}
//		
//		// per il refresh
//	    hbPoliticalCard.getChildren().addAll(imgsPoliticalCards);
//		
//	}
	
	/*@FXML
	
	private void click2(){
		
		button.setFill(Color.BLUE);
	}*/
	
	@FXML
	
	private void clickShopPlayers(){
		
		Client.showShopPlayers();
		
	}
	
	
	@FXML
	private void click2(){  // devi implementarlo per quando fa l'azione
	
		double x = button.getTranslateX();
		double y = button.getTranslateY();
		muoviPedina(button,x,y);
		
		
		}
	

	public void muoviPedina(Circle buttone, double x, double y){
		
		
		Path path = new Path();
	    path.getElements().add(new MoveTo(x,y));
	    if(x<50.0){
	    path.getElements().add(new LineTo(x+26,y));}
	    
	    else path.getElements().add(new LineTo(x,y+26));
	  
	    PathTransition ptr = new PathTransition();

	    ptr.setDuration(Duration.seconds(3));
	    ptr.setDelay(Duration.seconds(1));
	    ptr.setPath(path);
	    ptr.setNode(buttone);
	   // ptr.setCycleCount(2)
	    
	    ptr.play();
		
	    
		}    
	
	@FXML
	public void showBonusVillage(ActionEvent e){
	
		if((e.getSource()== arkonBonus) == true){
			new Client().showBonus(0);
		}
		
		if((e.getSource()== burgenBonus) == true){
			new Client().showBonus(1);	
		}
		
		if((e.getSource()== castrumBonus)== true){
			new Client().showBonus(2);
		}
		
		if((e.getSource()== dorfulBonus)== true){
			new Client().showBonus(3);
		}
		
		if((e.getSource()== estiBonus)== true){
			new Client().showBonus(4);
		}
		
		if((e.getSource()== framekBonus)== true){
			new Client().showBonus(5);
		}

		if((e.getSource()== gradenBonus)== true){
			new Client().showBonus(6);
		}
		
		if((e.getSource()== hellarBonus)== true){
			new Client().showBonus(7);
			
		}
		
		if((e.getSource()== indurBonus)== true){
			new Client().showBonus(8);
		}
		
		if((e.getSource()== kultosBonus)== true){
			new Client().showBonus(10);
		}
		
		if((e.getSource()== lyramBonus)== true){
			new Client().showBonus(11);
		}
		
		if((e.getSource()== merkatimBonus)== true){
			new Client().showBonus(12);
		}
		
		if((e.getSource()== narisBonus)== true){
			new Client().showBonus(13);
		}
		
		if((e.getSource()== osiumBonus)== true){
			new Client().showBonus(14);
		}
	}
	
	@FXML
	public void showBonusPermitCard(ActionEvent e){
		
		if((e.getSource()== region0Card0)){
			new Client().showBonus(0,0);
		}
		
		if((e.getSource()== region0Card1)){
			new Client().showBonus(0,1);
		}
		
		if((e.getSource()== region1Card0)){
			new Client().showBonus(1,0);
		}
		
		if((e.getSource()== region1Card1)){
			new Client().showBonus(1,1);
		}
		
		if((e.getSource()== region2Card0)){
			new Client().showBonus(2,0);
		}
		
		if((e.getSource()== region2Card1)){
			new Client().showBonus(2,1);
		}	
	}
	
	@FXML
	public void nobilSelection(ActionEvent e){
		
		if(e.getSource() == nobilWhite) {
		selectColorNobil = "WHITE";
		}
	}
	
	@FXML
	public void clickEleggiConsigliere(){
		
		
		
	}
	
	@FXML
	public void clickSelectCouncil(){
		
		selectCouncil = 0;
		
	}
	
	public void initializeCouncilRegion(){
		
		
		try 
		{
			// esempio
//			MatchRequest req = null;
//			req.getMatch("pippo");
			
		
			
//			// poi ...
//			Match m = null; // prendo il match dal server
//			Client.getInstance().setMatch(m);
		
			
			Region region = be.getRegions()[0];
			java.awt.Color[] color = region.getCouncil().getNobles();
			for(int i = 0; i<3; i++){
				if(color[i].equals(java.awt.Color.CYAN)){
					Image image = new Image("main/view/image/consBlu.png");
					if(i==0){
						nobil00.setImage(image);}
					if(i==1){
						nobil01.setImage(image);}
					if(i==2){
						nobil02.setImage(image);}
					if(i==3){
						nobil02.setImage(image);}
					} 
				if(color[i] == java.awt.Color.BLACK){
					Image image = new Image("main/view/image/consNero.png");
					if(i==0){
						nobil00.setImage(image);}
					if(i==1){
						nobil01.setImage(image);}
					if(i==2){
						nobil02.setImage(image);}
					if(i==3){
						nobil02.setImage(image);}
				} 
			}
		}
		catch (BuilderException ge) {
			// TODO Auto-generated catch block
			ge.printStackTrace();		
		}
		
	}
		
		
	
}
