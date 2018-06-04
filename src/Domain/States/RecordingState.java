package Domain.States;

import Domain.Connection;
import Domain.ConnectionState;
import Entities.Message;

public class RecordingState implements ConnectionState {
	

	
	Connection connection;
	
	public RecordingState(Connection connection) {
		this.connection = connection;
		connection.phone.speak(connection.getMailSystem().getGreeting());
	}
	
	private void login(String key) {
      if (key.equals(HASH_VALUE)) {
         if (connection.getMailSystem().checkPasscode(connection.getAccumulatedKeys())) {
            connection.changeConnectionState(new MailboxMenuState(connection));
            
         } else {
        	 connection.phone.speak("Incorrect passcode. Try again!");
         }
         connection.resetAccumulatedKeys();
      } else {
    	  connection.changeAccumulatedKeys(key);
      }
   }

	@Override
	public void dial(String key) {
		login(key);
	}

	@Override
	public void record(String voice) {
		connection.changeCurrentRecording(voice);
	}

	@Override
	public void hangUp() {
		connection.getMailSystem().addMessage(new Message(connection.getCurrentRecording()));
	}

}
