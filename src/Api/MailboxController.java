package Api;

import static spark.Spark.*;

import java.util.ArrayList;
 
import spark.Request;
import spark.Response;
import com.google.gson.Gson;

import Entities.Mailbox;
import Entities.Message;
public class MailboxController { 
 
	static int getHerokuAssignedPort() {
	        ProcessBuilder processBuilder = new ProcessBuilder();
	        if (processBuilder.environment().get("PORT") != null) {
	            return Integer.parseInt(processBuilder.environment().get("PORT"));
	        }
	        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
	}
	
	public MailboxController(VoiceMailService voiceMailService){
		port(getHerokuAssignedPort()); 
	    get("/", (request, response) -> {
	    	response.type("application/json");
			ArrayList<Mailbox> list = voiceMailService.getAllMailboxes(); 
	    	return new Gson().toJson(list);
		});
	    
		get("/mailbox/:id", (request, response) ->{ 
			response.type("application/json");
	        Mailbox mailbox=voiceMailService.getMailboxByNumber(request.params(":id")); 
			return new Gson().toJson(mailbox);
		});
		
		get("/mailbox/:id/currentmessage",(request,response) -> {
			response.type("application/json"); 
	        Mailbox mailbox=voiceMailService.getMailboxByNumber(request.params(":id"));
			Message currentMessage=voiceMailService.getCurrentMessage(mailbox);
			return new Gson().toJson(currentMessage);
		});  
	} 
	
   
}

