
package commands;

import client.GameClient;

public class ClientGameLost extends Command<GameClient>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 66783472132893423L;
	
	public ClientGameLost(){
	}
	
	@Override
	public void execute(GameClient executeOn) {
		executeOn.notifyLevelWasLost();
	}

}