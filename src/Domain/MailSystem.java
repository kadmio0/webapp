package Domain;

import Entities.Mailbox;
import Entities.Message;

public class MailSystem {

   private MailboxService mailboxService;
   private Mailbox currentMailbox;
   
   public MailSystem(MailboxService mailboxService) {
      this.mailboxService = mailboxService;
   }

   public void findMailbox(String numberOfMailbox) {
      currentMailbox = mailboxService.findMailbox(numberOfMailbox);
   }
   
   public void removeCurrentMessage() {
	   mailboxService.removeMessage(currentMailbox);
   }
   
   public void saveCurrentMessage() {
	   mailboxService.saveMessage(currentMailbox);
   }
   
   public Message getCurrentMessage() {
	   return mailboxService.getMessage(currentMailbox);
   }
   
   public void addMessage(Message message) {
	   mailboxService.addNewMessage(message, currentMailbox);
   }
   
   public boolean haveAMailbox() {
	   return currentMailbox != null;
   }
   
   public String getGreeting() {
	   return currentMailbox.getGreeting();
   }
   
   public boolean checkPasscode(String passcode) {
	   return currentMailbox.checkPasscode(passcode);
   }
   
   public void changePasscode(String newPasscode) {
	   mailboxService.changePasscode(currentMailbox, newPasscode);
   }
   
   public void changeGreeting(String newGreeting) {
	   mailboxService.changeGreeting(currentMailbox, newGreeting);
   }


}