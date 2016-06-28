package view;

import view.client.setup.ViewGameModeSetup;
import view.client.setup.ViewSetupUsername;

public class View {
	public View(){ }
	
	public boolean viewGameModeSetup(){
		return new ViewGameModeSetup().show();
	}
	
	public String viewSetupUsername(){
		return new ViewSetupUsername().show();
	}
}
