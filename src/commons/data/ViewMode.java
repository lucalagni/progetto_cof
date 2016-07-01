package commons.data;

/**
 * Enumerazione che descrive i possibili tipi di visualizzazione dei dati
 * 
 * CLI = Visualizzazione a linea di comando
 * GUI = Visualizzazione ad interfaccia grafica
 * 
 * @author Luca Lagni
 *
 */
public enum ViewMode {
	CLI("CLI"),
	GUI("GUI");
	
	private String viewMode ;
	ViewMode(String viewMode){ this.setViewMode(viewMode); }
	private void setViewMode(String viewMode){ this.viewMode = viewMode; }
	public String getViewMode(){ return this.viewMode; }
}
