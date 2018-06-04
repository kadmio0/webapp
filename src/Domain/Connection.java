package Domain;

import Domain.States.ConnectedState;
import UI.Telephone;

public class Connection {
	

   private static final String EMPTY_VALUE = "";
   private MailSystem mailSystem;
   private String currentRecording;
   private String accumulatedKeys;
   private ConnectionState connectionState;
   public Telephone phone;

   public Connection(MailSystem mailSystem, Telephone phone) {
      this.mailSystem = mailSystem;
      this.phone = phone;
      resetConnection();
   }

   public void dial(String key) {
      connectionState.dial(key);
   }

   public void record(String voice) {
	   connectionState.record(voice);
   }

   public void hangUp() {
      connectionState.hangUp();
      resetConnection();
   }

   private void resetConnection() {
      currentRecording = EMPTY_VALUE;
      accumulatedKeys = EMPTY_VALUE;
      changeConnectionState(new ConnectedState(this));
   }

   public MailSystem getMailSystem() {
	   return this.mailSystem;
   }
   
   public String getAccumulatedKeys() {
	   return accumulatedKeys;
   }
   
   public String getCurrentRecording() {
	   return currentRecording;
   }
   
   public void changeConnectionState(ConnectionState connectionState) {
	   this.connectionState = connectionState;
   }
   
   public void resetAccumulatedKeys() {
	   accumulatedKeys = EMPTY_VALUE;
   }
   
   public void resetCurrentRecording() {
	   currentRecording = EMPTY_VALUE;
   }
   
   public void changeAccumulatedKeys(String value) {
	   accumulatedKeys += value;
   }
   
   public void changeCurrentRecording(String value) {
	   currentRecording += value;
   }

}











