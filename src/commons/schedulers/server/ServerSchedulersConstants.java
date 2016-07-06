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
		public static final long	 SERVER_SCHEDULER_MATCH_MANAGER_PERIOD = 5; 
		public static final long	 SERVER_SCHEDULER_MATCH_MANAGER_TIMEOUT = 20;
		public static final long	 SERVER_SCHEDULER_MATCH_MANAGER_AWAIT = 25;
		
	//Parametri temporali inerenti alla gestione dein turni
//		public static final TimeUnit SERVER_SCHEDULER_MATCH_SCHEDULER_TIME_UNIT = TimeUnit.MINUTES;
//		public static final long	 SERVER_SCHEDULER_MATCH_SCHEDULER_DELAY = 0; //Ritado prima dell'avvio dello scheduler (tempo del primo giocatore per fare la mossa)
//		public static final long	 SERVER_SCHEDULER_MATCH_SCHEDULER_PERIOD = 5; //Periodo che ha l'utente a disposizione per fare la mossa
//		public static final long     SERVER_SCHEDULER_MATCH_SCHEDULER_TIMEOUT = 120; //Perido che ha l'utente a disposizione prima di essere messo fuori uso
//		public static final long	 SERVER_SCHEDULER_MATCH_SCHEDULER_AWAIT = 400;
		public static final TimeUnit SERVER_SCHEDULER_MATCH_SCHEDULER_TIME_UNIT = TimeUnit.SECONDS;
		public static final long	 SERVER_SCHEDULER_MATCH_SCHEDULER_DELAY = 30; //Ritado prima dell'avvio dello scheduler (tempo del primo giocatore per fare la mossa)
		public static final long	 SERVER_SCHEDULER_MATCH_SCHEDULER_PERIOD = 30; //Periodo che ha l'utente a disposizione per fare la mossa
		public static final long     SERVER_SCHEDULER_MATCH_SCHEDULER_TIMEOUT = 120; //Perido che ha l'utente a disposizione prima di essere messo fuori uso
		public static final long	 SERVER_SCHEDULER_MATCH_SCHEDULER_AWAIT = 400;
		
}
