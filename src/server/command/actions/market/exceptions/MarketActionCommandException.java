package server.command.actions.market.exceptions;

/**
 * Classe per la definizione di eccezzioni lato market
 * @author Luca Lagni
 *
 */
public class MarketActionCommandException extends Exception{
	private static final long serialVersionUID = 1L;

	public MarketActionCommandException(String exceptionCode){ super(exceptionCode); }
}
