package Entities;
public class Message {
	
   private String text;
   private String id;
   
   public Message(String messageText) {
      text = messageText;
   }

   public String getMessageText() {
      return text;
   }
   
   public void setId(String id) {
	   this.id = id;
   }
   
   public String getId() {
	   return id;
   }

}
