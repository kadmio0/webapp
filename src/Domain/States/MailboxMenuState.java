package Domain.States;

import Domain.Connection;
import Domain.ConnectionState;

public class MailboxMenuState  implements ConnectionState {
	

   
   private static final String MAILBOX_MENU_TEXT = 
	         "Enter 1 to listen to your messages\n"
	         + "Enter 2 to change your passcode\n"
	         + "Enter 3 to change your greeting";
	
	Connection connection;
	
	public MailboxMenuState(Connection connection) {
		this.connection = connection;
		connection.phone.speak(MAILBOX_MENU_TEXT);
	}
	
	private void mailboxMenu(String key) {
      if (key.equals("1")) {
    	 connection.changeConnectionState(new MessageMenuState(connection));

      } else { 
    	  if (key.equals("2")) {
    		  connection.changeConnectionState(new ChangePasscodeState(connection));
	         
	      } else {
	    	  if (key.equals("3")) {
	    		  connection.changeConnectionState(new ChangeGreetingState(connection));
		      }
	      }
      }
   }

	@Override
	public void dial(String key) {
		mailboxMenu(key);
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
