package command.basic.actions.exceptions.codes;

public enum MainActionCommandExceptionCode {
	GAMER_HAS_TOO_FEAW_COINS("MainActionExceptionCode: gamer has too feaw coins"),
	TOO_FEAW_HELPERS_SELECTED("MainActionExceptionCode: too feaw helpers selected"),
	TOO_FEAW_HELPERS_AVAILABLES("MainActionExceptionCode: too feaw helpers available"),
	VILLAGE_NOT_FOUND("MainActionExceptionCode: village not found"),
	INSISTENCE_BETWEEN_PERMIT_CARD_AND_VILLAGE("incosistence between card and village"),
	GAMER_DOES_NOT_HAS_THAT_PERMIT_CARD("gamer does not has that permit card"),
	GAMER_SHOP_ALREADY_PLACED_IN_THAT_VILLAGE("gamer's shop already placed in that village"),
	GAMER_HAS_NO_MORE_AVAILABLE_SHOPS("gamer has no more available shops"),
	INVALID_REGION_NUMBER("MainActionCommandException: invalid region number"),
	INVALID_PERMIT_CARD_NUMBER("MainActionCommandException: invalid permit card number"),
	INVALID_POLITICAL_CARDS_NUMBER("MainActionCommandException: invalid political cards number"),
	INCONCISTENCE_BEETWEEN_POLITICAL_CARDS_AND_NOBLES("MainActionCommandException: inconcistence between political cards and nobles"),
	INVALID_VILLAGE_PATH("MainActionCommandException: invalid village path"),
	INVALID_NOBLE_COLOR("MainActionCommandException: invalid noble color"),
	OPERATION_NOT_VALID("MainActionCommandException: operation not valid");
	
	private String exceptionCode;
	MainActionCommandExceptionCode(String exceptionCode){ this.setExceptionCode(exceptionCode);}
	private void setExceptionCode(String exceptionCode){ this.exceptionCode = exceptionCode; }
	public String getExceptionCode(){ return this.exceptionCode; }
}
