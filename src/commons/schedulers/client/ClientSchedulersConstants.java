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
	public static final long 	 CLIENT_SCHEDULER_CONNECTIONS_TIMEOUT = 30;
	public static final long     CLIENT_SCHEDULER_CONNECTIONS_AWAIT = 35;
	
	//Quanti di tempo relativi alla richiesta can I Play
	public static final TimeUnit CLIENT_SCHEDULER_CAN_I_PLAY_TIME_UNIT = TimeUnit.SECONDS;
	public static final long     CLIENT_SCHEDULER_CAN_I_PLAY_DELAY = 0 ;
	public static final long     CLIENT_SCHEDULER_CAN_I_PLAY_PERIOD = 5;
	public static final long 	 CLIENT_SCHEDULER_CAN_I_PLAY_TIMEOUT = 30;
	public static final long	 CLIENT_SCHEDULER_CAN_I_PLAY_AWAIT = 35;
	
	//Quanti di tempo relativi alla richiesta del turno giocatore
	public static final TimeUnit CLIENT_SCHEDULER_GAMER_TURN_TIME_UNIT = TimeUnit.MINUTES;
	public static final long	 CLIENT_SCHEDULER_GAMER_TURN_DELAY = 0;
	public static final long	 CLIENT_SCHEDULER_GAMER_TURN_PERIOD = 1;
	public static final long 	 CLIENT_SCHEDULER_GAMER_TURN_TIMEOUT = 20;
	public static final long	 CLIENT_SCHEDULER_GAMER_TURN_AWAIT = 4000;
}
