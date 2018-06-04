package Domain;

import java.util.ArrayList;

import Entities.Mailbox;
import Entities.Message;

public interface MailboxService {
	public Mailbox findMailbox(String numberOfMailbox);
	public void removeMessage(Mailbox mailbox);
	public void addNewMessage(Message message, Mailbox mailbox);
	public void saveMessage(Mailbox mailbox);
	public Message getMessage(Mailbox mailbox);
	public void changePasscode(Mailbox mailbox, String passcode);
	public void changeGreeting(Mailbox mailbox, String greeting);
	ArrayList<Mailbox> getMailboxes();
}
