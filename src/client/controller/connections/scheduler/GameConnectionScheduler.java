package client.controller.connections.scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Classe che esgue in polling delle richieste fino al verificarsi di una certa condizione
 * 
 * @author Luca Lagni
 *
 */
public class GameConnectionScheduler {
	public static final long DELAY = 0;    					//Indica dopo quanto far partire lo scheduler
	public static final long PERIOD = 1;   					//Indica dopo quanto far ripartire lo scheduler
	public static final long STOP = 20;    					//indica dopo quanti secondi fermare lo scheduler
	public static final int NUM_THREADS = 1;       					//Indica quanti thread far partire
	public static final boolean DONT_INTERRUPT_IF_RUNNING = false ; //Indica che il thread può essere bloccato anche se esso non ha concluso la sua esecuzione
	
	private static ScheduledExecutorService scheduler;
	
	public GameConnectionScheduler(){
		scheduler = Executors.newScheduledThreadPool(NUM_THREADS);
	}
	
	/**
	 * Metodo che attiva lo scheduler per il polling
	 */
	public void activateAddMe(){
		Runnable addMeTask = new AddMeTask();
		ScheduledFuture<?> requireAddMe = scheduler.scheduleWithFixedDelay(addMeTask, DELAY, PERIOD, TimeUnit.SECONDS);
		Runnable stopAddMeTask = new StopAddMeTask(requireAddMe);
		
		scheduler.schedule(stopAddMeTask, STOP, TimeUnit.SECONDS);
		
	}
	
	private static final class AddMeTask implements Runnable{

		@Override
		public void run() {
			System.out.println("\nrequire Add me");
		}
		
	}
	
	private static final class StopAddMeTask implements Runnable {
		private ScheduledFuture<?> futureScheduled ;
		
	    StopAddMeTask(ScheduledFuture<?> futureScheduled){ this.futureScheduled = futureScheduled; }
		
		@Override
		public void run() {
			System.out.println("\nStop require add me");
			futureScheduled.cancel(DONT_INTERRUPT_IF_RUNNING);
			scheduler.shutdown();
		}
		
	}
}
