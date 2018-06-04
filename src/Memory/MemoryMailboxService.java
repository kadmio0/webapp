package Memory;

import java.util.ArrayList;

import Entities.Mailbox;
import Entities.Message;
import Domain.MailboxService;

public class MemoryMailboxService implements MailboxService {
	
	private static final int REAL_POSITION_OF_ARRAY = 1;
	private static final int LIMIT_OF_MAILBOX = 1;
	private static final String PLEASE_LEAVE_A_MESSAGE_TEXT = ". \nPlease leave a message now.";
	private static final String REACHED_MAILBOX_TEXT = "You have reached mailbox ";
	private ArrayList<Mailbox> mailboxes;
	   
	 public MemoryMailboxService(int mailboxCount) {
	    mailboxes = new ArrayList<Mailbox>();
	    for (int numberOfMailbox = 1; numberOfMailbox <= mailboxCount; numberOfMailbox++)
	    {
	       String passcode = transformNumberToString(numberOfMailbox);
	       String greeting = getDefaultGreeting(numberOfMailbox);
	       mailboxes.add(new Mailbox(passcode, greeting, transformNumberToString(numberOfMailbox)));
	    }
	 }

	private String transformNumberToString(int numberOfMailbox) {
		return String.valueOf(numberOfMailbox);
	}

	private String getDefaultGreeting(int numberOfMailbox) {
		return REACHED_MAILBOX_TEXT + numberOfMailbox + PLEASE_LEAVE_A_MESSAGE_TEXT;
	}

    public Mailbox findMailbox(String numberOfMailbox) {
    	  Mailbox obtainedMailbox = null;
	      int number = transformStringToNumber(numberOfMailbox);
	      if (isAValidMailbox(number)) {
	    	  obtainedMailbox = mailboxes.get(getPositionOfMailbox(number));
	      }
	      return obtainedMailbox;
    }

	private int getPositionOfMailbox(int number) {
		return number - REAL_POSITION_OF_ARRAY;
	}

	private boolean isAValidMailbox(int number) {
		return isGreaterThanFirstMailbox(number) && isLessThanLimitOfMailboxes(number);
	}

	private boolean isGreaterThanFirstMailbox(int number) {
		return number >= LIMIT_OF_MAILBOX;
	}

	private boolean isLessThanLimitOfMailboxes(int number) {
		return number <= getQuantityOfMailboxes();
	}

	private int getQuantityOfMailboxes() {
		return mailboxes.size();
	}

	private int transformStringToNumber(String numberOfMailbox) {
		return Integer.parseInt(numberOfMailbox);
	}

	@Override
	public void removeMessage(Mailbox mailbox) {
		mailbox.removeCurrentMessage();
	}

	@Override
	public void addNewMessage(Message message, Mailbox mailbox) {
		mailbox.addMessage(message);
	}

	@Override
	public void saveMessage(Mailbox mailbox) {
		mailbox.saveCurrentMessage();
	}

	@Override
	public Message getMessage(Mailbox mailbox) {
		return mailbox.getCurrentMessage();
	}

	@Override
	public void changePasscode(Mailbox mailbox, String passcode) {
		mailbox.changePasscode(passcode);
	}

	@Override
	public void changeGreeting(Mailbox mailbox, String greeting) {
		mailbox.changeGreeting(greeting);
	}
	
	@Override
	public ArrayList<Mailbox> getMailboxes() {
		return mailboxes;
	}
	
}
