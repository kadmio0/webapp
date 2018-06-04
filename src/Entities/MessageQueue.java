package Entities;
import java.util.ArrayList;

public class MessageQueue {
	
   private static final int SIZE_ZERO = 0;
   private ArrayList<Message> messageQueue;
   
   public MessageQueue() {
      messageQueue = new ArrayList<Message>();
   }

   public Message removeMessage() {
	  if (isMessageQueueEmpty()) {
		  return null;
	  } else {
		  return messageQueue.remove(0);
	  }
      
   }

   public void addMessage(Message newMessage) {
      messageQueue.add(newMessage);
   }

   public int getSizeOfMessageQueue() {
      return messageQueue.size();
   }

   public Message peekMessage() {
      if (isMessageQueueEmpty()) {
    	  return null;
      } else {
    	  return messageQueue.get(0);
      }
   }

   private boolean isMessageQueueEmpty() {
	   return getSizeOfMessageQueue() == SIZE_ZERO;
   }

}
