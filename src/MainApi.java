import java.util.Scanner;

import Api.MailboxController;
import Api.VoiceMailService;
import Database.DatabaseMailboxService;
import UI.Telephone;
import UtilDatabase.Database;
import UtilDatabase.VoiceMailLocalDatabase;
import Domain.Connection;
import Domain.MailSystem;
import Domain.MailboxService;
import Memory.MemoryMailboxService;

public class MainApi {

   public static void main(String[] args) {
	  
	  String stringConnection = "jdbc:derby:VoiceMailDB;create=true";
	  String userName = "usuario";
	  String password = "derby";
	  
	  Database database = new VoiceMailLocalDatabase(stringConnection,userName,password);
	 
	  database.initializeConnection(); 
	  MailboxService mailboxService = new DatabaseMailboxService(database);
       
      new MailboxController(new VoiceMailService(mailboxService));  
   }
}
