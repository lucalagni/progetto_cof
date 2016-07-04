
package server.dao.basics;
import model.basics.Bonus;
import model.basics.builders.BonusBuilder;
import model.basics.builders.exceptions.BuilderException;
import org.json.simple.JSONObject;
import server.dao.basics.fields.DAOFields;
/**
 *
 * @author Antonietta
 */
public class BonusDAO {
    
    
   public Bonus readData(JSONObject jsonBonusRoot) throws BuilderException{
		JSONObject jsonBonus = (JSONObject)jsonBonusRoot.get(DAOFields.BONUS);
                
                Bonus b = new BonusBuilder().setCoins(((Long)jsonBonus.get(DAOFields.COINS)).intValue())
								 	.setHelpers(((Long)jsonBonus.get(DAOFields.HELPERS)).intValue())
								 	.setPoliticalCards(((Long)jsonBonus.get(DAOFields.POLITICAL_CARDS)).intValue())
								 	.setPoints(((Long)jsonBonus.get(DAOFields.POINTS)).intValue())
								 	.setShifts(((Long)jsonBonus.get(DAOFields.SHIFTS)).intValue())
								 	.setNewMainAction((boolean) jsonBonus.get(DAOFields.NEW_MAIN_ACTION))
								 	.setReusePermitBonus((boolean) jsonBonus.get(DAOFields.REUSE_PERMIT_BONUS))
								 	.setAcquirePermitCard((boolean) jsonBonus.get(DAOFields.ACQUIRE_PERMIT_CARD))
								 	.setAcquireSingleVillageBonus((boolean) jsonBonus.get(DAOFields.ACQUIRE_SINGLE_VILLAGE_BONUS))
								 	.setAcquireDoubleVillageBonus((boolean) jsonBonus.get(DAOFields.ACQUIRE_DOUBLE_VILLAGE_BONUS))
								 	.build();
		return b;     
   }


@SuppressWarnings("unchecked")   
   public JSONObject writeData(Bonus bonus,boolean indipendent){
		JSONObject jObj = new JSONObject();
		
		jObj.put(DAOFields.COINS, bonus.getCoins());
		jObj.put(DAOFields.HELPERS, bonus.getHelpers());
		jObj.put(DAOFields.POLITICAL_CARDS, bonus.getPoliticalCards());
		jObj.put(DAOFields.POINTS, bonus.getPoints());
		jObj.put(DAOFields.SHIFTS, bonus.getShifts());
		jObj.put(DAOFields.NEW_MAIN_ACTION, bonus.getNewMainAction());
		jObj.put(DAOFields.REUSE_PERMIT_BONUS, bonus.getReusePermitBonus());
		jObj.put(DAOFields.ACQUIRE_PERMIT_CARD, bonus.getAcquirePermitCard());
		jObj.put(DAOFields.ACQUIRE_SINGLE_VILLAGE_BONUS, bonus.getAcquireSingleVillageBonus());
		jObj.put(DAOFields.ACQUIRE_DOUBLE_VILLAGE_BONUS, bonus.getAcquireDoubleVillageBonus());
		
		if(indipendent == true){
			JSONObject root = new JSONObject();
			root.put(DAOFields.BONUS, jObj);
			return root;
		}
		else return jObj;
	}
    
}
