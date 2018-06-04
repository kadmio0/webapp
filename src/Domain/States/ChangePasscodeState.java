package Domain.States;

import Domain.Connection;
import Domain.ConnectionState;

public class ChangePasscodeState  implements ConnectionState {
	
	Connection connection;
	
   private static final String MAILBOX_MENU_TEXT = 
	         "Enter 1 to listen to your messages\n"
	         + "Enter 2 to change your passcode\n"
	         + "Enter 3 to change your greeting";
	
	public ChangePasscodeState(Connection connection) {
		this.connection = connection;
		connection.phone.speak("Enter new passcode followed by the # key");
	}
	
	private void changePasscode(String key) {
      if (key.equals(HASH_VALUE)) {
         connection.getMailSystem().changePasscode(connection.getAccumulatedKeys());
         connection.changeConnectionState(new MailboxMenuState(connection));
         connection.phone.speak(MAILBOX_MENU_TEXT);
         connection.resetAccumulatedKeys();
      }
      else {
         connection.changeAccumulatedKeys(key);
      }
   }

	@Override
	public void dial(String key) {
		changePasscode(key);
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
