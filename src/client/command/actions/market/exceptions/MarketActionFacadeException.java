package client.command.actions.market.exceptions;

/**
 * Classe che gestisce funzioni di gestione 
 * @author Luca Lagni
 *
 */
public class MarketActionFacadeException extends Exception {
	private static final long serialVersionUID = 1L;

	public MarketActionFacadeException(String exceptionCode){ super(exceptionCode); }
}
