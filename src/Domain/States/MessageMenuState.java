package Domain.States;

import Domain.Connection;
import Domain.ConnectionState;
import Entities.Message;

public class MessageMenuState  implements ConnectionState {
	
	Connection connection;
	
   private static final String MESSAGE_MENU_TEXT = 
	         "Enter 1 to listen to the current message\n"
	         + "Enter 2 to save the current message\n"
	         + "Enter 3 to delete the current message\n"
	         + "Enter 4 to return to the main menu";

	public MessageMenuState(Connection connection) {
		this.connection = connection;
        connection.phone.speak(MESSAGE_MENU_TEXT);
	}
	
	private void messageMenu(String key) {
      if (key.equals("1")) {
         String output = "";
         Message message = connection.getMailSystem().getCurrentMessage();
         if (message == null) {
        	 output += "No messages." + "\n";
         } else {
        	 output += message.getMessageText() + "\n";
         }
         output += MESSAGE_MENU_TEXT;
         connection.phone.speak(output);
      }
      else {
    	  if (key.equals("2")) {
	         connection.getMailSystem().saveCurrentMessage();
	         connection.phone.speak(MESSAGE_MENU_TEXT);
	      } else {
	    	  if (key.equals("3")) {
	    		 connection.getMailSystem().removeCurrentMessage();
		         connection.phone.speak(MESSAGE_MENU_TEXT);
		      } else {
		    	  if (key.equals("4")) { 
		    		  connection.changeConnectionState(new MailboxMenuState(connection));
		    	  }
		      }
	      }
      }
   }

	@Override
	public void dial(String key) {
		messageMenu(key);
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
