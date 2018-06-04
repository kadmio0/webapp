package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Entities.Mailbox;
import Entities.Message;
import UtilDatabase.Database;
import Domain.MailboxService;

public class DatabaseMailboxService implements MailboxService {
	
	private Database database;
	
	public DatabaseMailboxService(Database database) {
	       this.database = database;
	    }

	@Override
	public Mailbox findMailbox(String numberOfMailbox) {
		Mailbox mailbox = null;
		try { 
			String query = "SELECT * FROM MailBox WHERE id="+ numberOfMailbox;
			ResultSet result = database.querySelector(query) ; 
			if(result.next()){
				mailbox = new Mailbox(result.getString("passcode"),result.getString("greeting"),result.getString("id"));
			}
		} catch (SQLException e) { 
			e.printStackTrace();
		}  
		return mailbox;
	}

	@Override
	public void removeMessage(Mailbox mailbox) {
		String id = mailbox.getNumberOfMailbox();
		String queryDelete;
		ResultSet sr = null;
		String query = "SELECT  * FROM Message WHERE idMailBox=" + id + " AND state = 0";
		sr = database.querySelector(query);
		try {
			if(sr.next()) {
				queryDelete = "DELETE FROM Message WHERE id = " + sr.getString("id");
		    	database.queryUpdate(queryDelete);
			} else {
				query = "SELECT * FROM Message WHERE idMailBox = "+ id +" AND state = 1"; 
	    	    sr = database.querySelector(query);  
    		    if(sr.next()){
    			    queryDelete = "DELETE FROM Message WHERE id = " + sr.getString("id");
    			    database.queryUpdate(queryDelete);
    		    }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveMessage(Mailbox mailbox) {
		String id = mailbox.getNumberOfMailbox();
		Message message = getMessage(mailbox);
		removeMessage(mailbox);
		if(isAValidMessage(message)){
			String query = "INSERT INTO Message (idMailBox,text,state) VALUES"+
							"("+id+",'"+message.getMessageText()+"',1)";
			database.queryUpdate(query);
		}
		
	}

	@Override
	public Message getMessage(Mailbox mailbox) {
		 String id = mailbox.getNumberOfMailbox();
		 Message currentMessage = null;
		 ResultSet sr = null;
		 String query = "SELECT  * FROM Message WHERE idMailBox="+id+" AND state = 0"; 
		 try{
			  sr = database.querySelector(query);
		      if (sr.next()) {  
		    	  currentMessage = new Message(sr.getString("text"));  
		      } else {
		    	  query = "SELECT * FROM Message WHERE idMailBox="+id+" AND state = 1"; 
		    	  sr = database.querySelector(query);  
	    		  if(sr.next()){
	    			  currentMessage =  new Message(sr.getString("text")); 
	    		  } else {
	    			  return null;
	    		  }
		      }
		 }catch(SQLException e){
			 e.printStackTrace();
		 } 
		 return currentMessage;
	}
	
	@Override
	public void addNewMessage(Message message, Mailbox mailbox) {
		String id = mailbox.getNumberOfMailbox();
		if(isAValidMessage(message)) { 
			String query = "INSERT INTO Message (idMailBox,text,state) VALUES("+id+",'"
							+ message.getMessageText() + "',0)";
			database.queryUpdate(query);
		}
	}

	@Override
	public void changePasscode(Mailbox mailbox, String passcode) {
		String id = mailbox.getNumberOfMailbox();
		String query = "UPDATE Mailbox SET passcode = '"+ passcode +"' WHERE id = "+ id;
		database.queryUpdate(query);
	}

	@Override
	public void changeGreeting(Mailbox mailbox, String greeting) {
		String id = mailbox.getNumberOfMailbox();
		String query = "UPDATE Mailbox SET greeting = '"+ greeting +"' WHERE id = "+ id;
		database.queryUpdate(query);
	}
	
	private boolean isAValidMessage(Message message) {
		return message != null && !message.getMessageText().isEmpty();
	}
	
	@Override
	public ArrayList<Mailbox> getMailboxes() {
		ArrayList<Mailbox> mailboxes = new ArrayList<Mailbox>();
		String query = "(SELECT passcode,greeting,id FROM MailBox)";
		ResultSet rs = database.querySelector(query) ; 
		try { 
			while(rs.next()){
				mailboxes.add(
						new Mailbox(rs.getString("passcode"),
									rs.getString("greeting"),rs.getString("id")));
			}
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return mailboxes;
	}
	
}
