package Domain.States;

import Domain.Connection;
import Domain.ConnectionState;

public class ConnectedState  implements ConnectionState {
	
	Connection connection;

   
   private static final String INITIAL_PROMPT = "Enter mailbox number followed by #";
	
	public ConnectedState(Connection connection) {
		this.connection = connection;
		connection.phone.speak(INITIAL_PROMPT);
	}
	
	private void connect(String key) {
      if (key.equals(HASH_VALUE)) {
         connection.getMailSystem().findMailbox(connection.getAccumulatedKeys());
         if (connection.getMailSystem().haveAMailbox()) {
            connection.changeConnectionState(new RecordingState(connection));
         } else {
        	 connection.phone.speak("Incorrect mailbox number. Try again!");
         }
         connection.resetAccumulatedKeys();
      } else {
    	  connection.changeAccumulatedKeys(key);
      }
   }

	@Override
	public void dial(String key) {
		connect(key);
	}

	@Override
	public void record(String voice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hangUp() {
		// TODO Auto-generated method stub
		
	}

}
