/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.view.custom;

import java.util.ArrayList;
import javafx.scene.control.Label;
import model.basics.PermitCard;

/**
 *
 * @author Antonietta
 */
public class Operation{
    
    
    public int nextPermitCard(int indexPermitCard,ArrayList<PermitCard> arrayPermitCard, Label villagePermitCardLabel){
        
        int selectedPermitCard = -1;
          if (indexPermitCard < (arrayPermitCard.size())-1) {
			
			if(indexPermitCard== -1){
                           indexPermitCard = 0;
                        }
                        
			else{
                            indexPermitCard++;
                            }
                    }
			
                    setTextPermitCardVillage(indexPermitCard, arrayPermitCard, villagePermitCardLabel);
                    selectedPermitCard = indexPermitCard;
                    return selectedPermitCard;
		}
        
    public int prevPermitCard(int indexPermitCard, ArrayList<PermitCard> arrayPermitCard, Label villagePermitCardLabel){
        
        int  selectedPermitCard = -1;
        
        if (indexPermitCard > 0) { 
             indexPermitCard--; 
        }
        
        setTextPermitCardVillage(indexPermitCard, arrayPermitCard, villagePermitCardLabel);
        selectedPermitCard = indexPermitCard;
                
        return selectedPermitCard;
    }        
              
    	public void setTextPermitCardVillage(int selectPermitCard, ArrayList<PermitCard> arrayPermitCards,Label villagePermitCard) {

		if (selectPermitCard < arrayPermitCards.size()) {
			villagePermitCard.setText("" + arrayPermitCards.get(selectPermitCard).getVillages());
                }
	}
        
    
    /**
     * 
     * @param numberOfCouncil      numero di consigli da considerare, sottraendolo di uno
     * @param indexCouncilRegion   indice di selezione 
     * @param regionNumberLabel    Label per settare il numero di regione
     * @return 
     */
        
        public int nextCouncil(int numberOfCouncilLessOne, int indexCouncilRegion, Label regionNumberLabel){
            
            int selectedRegion = -1;
        
            
            if(indexCouncilRegion<numberOfCouncilLessOne){
                if (indexCouncilRegion == -1) {
                    indexCouncilRegion = 0;
                }
                        
                else{
                    indexCouncilRegion++;
                }
            }
            
            setTextCouncilRegion(indexCouncilRegion, regionNumberLabel);
            selectedRegion  =  indexCouncilRegion;
            
            return selectedRegion;
        }
        
        public int prevCouncil(int indexCouncilRegion, Label regionNumberLabel){
            int selectedRegion = -1;
              
            if (indexCouncilRegion > 0) {
                indexCouncilRegion--;
            }
        
            setTextCouncilRegion(indexCouncilRegion, regionNumberLabel);
            selectedRegion  =  indexCouncilRegion;
            return selectedRegion;
        }
        
        
        public void setTextCouncilRegion(int indexCouncilRegion, Label regionNumberLabel){
        
            if (indexCouncilRegion == 0) {
				regionNumberLabel.setText("Regione 1");
			}
			if (indexCouncilRegion == 1) {
				regionNumberLabel.setText("Regione 2");
			}
			if (indexCouncilRegion == 2) {
				regionNumberLabel.setText("Regione 3");
			}
			
			if (indexCouncilRegion == 3) {
				regionNumberLabel.setText("Consiglio del re");}
        
        
        }
        
        
        
    /**
    * Setta le label dei villaggi delle carte regione dell'azione principale acquista carta politica
    * @param i si riferisce alla regione scelta 
    */
	public void setTextPermitCardVillageAction(int index, PermitCard[][] regionPermitCard, Label villagesRegionCard0, Label villagesRegionCard1) {
            
		villagesRegionCard0.setText("" + regionPermitCard[index][0].getVillages());
		villagesRegionCard1.setText("" + regionPermitCard[index][1].getVillages());

	}
        
        
        
        
        
    }       

