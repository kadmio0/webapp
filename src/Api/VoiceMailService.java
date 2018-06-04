package Api;

import java.util.ArrayList;

import Domain.MailboxService;
import Entities.Mailbox;
import Entities.Message;

public class VoiceMailService {
	private MailboxService mailboxService; 
	
	public VoiceMailService(MailboxService mailboxService){
		this.mailboxService=mailboxService; 
	}

	public ArrayList<Mailbox> getAllMailboxes() { 
		return mailboxService.getMailboxes();
	}

	public Mailbox getMailboxByNumber(String mailboxNumber) { 
		return mailboxService.findMailbox(mailboxNumber);
	}
	
	public Message getCurrentMessage(Mailbox mailbox) {
		return mailboxService.getMessage(mailbox);
	}

	public void recordMessage(Message message, Mailbox mailbox) {
		mailboxService.addNewMessage(message, mailbox);
	}
}
