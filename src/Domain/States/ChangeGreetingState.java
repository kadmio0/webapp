package Domain.States;

import Domain.Connection;
import Domain.ConnectionState;

public class ChangeGreetingState implements ConnectionState {
	
	Connection connection;
	
   private static final String MAILBOX_MENU_TEXT = 
	         "Enter 1 to listen to your messages\n"
	         + "Enter 2 to change your passcode\n"
	         + "Enter 3 to change your greeting";
	
	public ChangeGreetingState(Connection connection) {
		this.connection = connection;
		connection.phone.speak("Record your greeting, then press the # key");
	}
	
	private void changeGreeting(String key) {
      if (key.equals(HASH_VALUE)) {
    	 connection.getMailSystem().changeGreeting(connection.getCurrentRecording());
         connection.resetCurrentRecording();
         connection.changeConnectionState(new MailboxMenuState(connection));
         connection.phone.speak(MAILBOX_MENU_TEXT);
      }
   }

	@Override
	public void dial(String key) {
		changeGreeting(key);
	}

	@Override
	public void record(String voice) {
		connection.changeCurrentRecording(voice);
	}

	@Override
	public void hangUp() {
		// TODO Auto-generated method stub
		
	}

}
