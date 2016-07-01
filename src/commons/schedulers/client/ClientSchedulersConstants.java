package commons.schedulers.client;

import java.util.concurrent.TimeUnit;

/**
 * Classe per la definizione delle costanti relative agli scheduler lato client
 * 
 * @author Luca Lagni
 *
 */
public final class ClientSchedulersConstants {
	
	//Quanti di tempo relativi ai parametri di connessione
	public static final TimeUnit CLIENT_SCHEDULER_CONNECTIONS_TIME_UNIT = TimeUnit.SECONDS;
	public static final long 	 CLIENT_SCHEDULER_CONNECTIONS_DELAY = 0;
	public static final long 	 CLIENT_SCHEDULER_CONNECTIONS_PERIOD = 1;
	public static final long 	 CLIENT_SCHEDULER_CONNECTIONS_TIMEOUT = 10;
	public static final long     CLIENT_SCHEDULER_CONNECTIONS_AWAIT = 20;
}
