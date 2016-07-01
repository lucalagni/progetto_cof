package commons.schedulers.server;

import java.util.concurrent.TimeUnit;

/**
 * Classe che contiene le costanti per la gestione della schedulazione dei processi
 * lato server
 * @author lucal
 *
 */
public final class ServerSchedulersConstants {
	//Quanti di tempo relativi ai parametri di connessione
		public static final TimeUnit SERVER_SCHEDULER_CONNECTIONS_TIME_UNIT = TimeUnit.SECONDS;
		public static final long 	 SERVER_SCHEDULER_CONNECTIONS_DELAY = 0;
		public static final long 	 SERVER_SCHEDULER_CONNECTIONS_PERIOD = 1;
		public static final long 	 SERVER_SCHEDULER_CONNECTIONS_TIMEOUT = 20;
		
	//Parametri temporali inerenti alle richieste degli utenti di poter essere aggiunti al match
		public static final TimeUnit SERVER_SCHEDULER_MATCH_MANAGER_TIME_UNIT = TimeUnit.SECONDS;
		public static final long	 SERVER_SCHEDULER_MATCH_MANAGER_DELAY = 0;
		public static final long	 SERVER_SCHEDULER_MATCH_MANAGER_SINGLE_GAMER_PERIOD = 30;
		public static final long	 SERVER_SCHEDULER_MATCH_MANAGER_SINGLE_GAMER_TIMEOUT = 50;
		public static final long	 SERVER_SCHEDULER_MATCH_MANAGER_SINGLE_GAMER_AWAIT = 55;
}
