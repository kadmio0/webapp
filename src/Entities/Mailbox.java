package Entities;

public class Mailbox {
	
	private static final int SIZE_ZERO = 0;
	private String numberOfMailbox;
	private String passcode;
    private String greeting;
    private MessageQueue newMessages;
    private MessageQueue keptMessages;

    public Mailbox(String passcode, String greeting, String numberOfMailbox) {
    	this.passcode = passcode;
    	this.greeting = greeting;
    	this.numberOfMailbox = numberOfMailbox;
    	newMessages = new MessageQueue();
    	keptMessages = new MessageQueue();
    }

   public boolean checkPasscode(String introducedPasscode) {
      return introducedPasscode.equals(passcode);
   }

   public void addMessage(Message newMessage) {
      newMessages.addMessage(newMessage);
   }

   public Message getCurrentMessage() {
      if (haveMessages(newMessages)) {
    	  return newMessages.peekMessage();
      }
      else {
    	  if (haveMessages(keptMessages)) {
    		  return keptMessages.peekMessage(); 
    	  } else {
    		  return null;
    	  }
      }
   }

   public Message removeCurrentMessage() {
      if (haveMessages(newMessages)) {
    	  return newMessages.removeMessage();
      }
      else {
    	  if (haveMessages(keptMessages)) {
    		  return keptMessages.removeMessage(); 
    	  } else {
    		  return null;
    	  }
      }
   }
   
   public boolean haveMessages(MessageQueue messageQueue) {
	   return messageQueue.getSizeOfMessageQueue() > SIZE_ZERO;
   }

   public void saveCurrentMessage() {
      Message message = removeCurrentMessage();
      if (isNotEmptyMessage(message)) {
    	  keptMessages.addMessage(message);
      }
   }

	private boolean isNotEmptyMessage(Message message) {
		return message != null;
	}

   public void changeGreeting(String newGreeting) {
      greeting = newGreeting;
   }

   public void changePasscode(String newPasscode) {
      passcode = newPasscode;
   }

   public String getGreeting() {
      return greeting;
   }
   
   public void setKeptMessages(MessageQueue newKeptMessages) {
	   keptMessages = newKeptMessages;
   }
   
   public void setNewMessages(MessageQueue newNewMessages) {
	   newMessages = newNewMessages;
   }
   
   public String getNumberOfMailbox() {
	   return numberOfMailbox;
   }

}
