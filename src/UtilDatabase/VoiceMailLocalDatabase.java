package UtilDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VoiceMailLocalDatabase implements Database {
	private String url; 
	private String userName; 
	private String password;
	Connection connection;
	
	public VoiceMailLocalDatabase(String url,String userName,String password) { 
		this.url = url;
		this.userName = userName;
		this.password = password; 
	}

	@Override
	public ResultSet querySelector(String query) {
		Statement statement;
		try {
			statement = connection.createStatement(); 
			return statement.executeQuery(query); 
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void queryUpdate(String query) {
		Statement statement;
		try {
			statement = connection.createStatement(); 
			statement.executeUpdate(query) ; 
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		
	}

	@Override
	public void initializeConnection() {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance(); 
			connection = DriverManager.getConnection(url, userName,password);
			
			Statement initialStatement = connection.createStatement();
			
			if(!connection.getMetaData().getSchemas(null, "USUARIO").next()) {
				initialStatement.execute("CREATE SCHEMA USUARIO");
			}
			
			if (!connection.getMetaData().getTables(null, "USUARIO", "%", null).next()) {
				initialStatement.addBatch("CREATE TABLE MailBox ( id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), passcode varchar(500) DEFAULT '0', greeting varchar(500) DEFAULT '0', PRIMARY KEY (id))");
				initialStatement.addBatch(getQueryCreateMailboxes());
				initialStatement.addBatch("CREATE TABLE message (id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),idMailBox int NOT NULL,text varchar(300)DEFAULT '0',state int DEFAULT 0, PRIMARY KEY (id),FOREIGN KEY (idMailBox) REFERENCES MailBox (id))");
				initialStatement.executeBatch();
				initialStatement.closeOnCompletion();
			}
		} catch (Exception e) {
		    throw new IllegalStateException("Cannot connect the database!", e);
		}
	}
	
	public String getQueryCreateMailboxes(){
		return "INSERT INTO MailBox (passcode, greeting) VALUES \r\n" + 
				"('1','You have reached mailbox 1. Please leave a message now.'), \r\n" + 
				"('2', 'You have reached mailbox 2. Please leave a message now.'), \r\n" + 
				"('3', 'You have reached mailbox 3. Please leave a message now.'),\r\n" + 
				"('4', 'You have reached mailbox 4. Please leave a message now.'),\r\n" + 
				"('5', 'You have reached mailbox 5. Please leave a message now.'),\r\n" + 
				"('6', 'You have reached mailbox 6. Please leave a message now.'),\r\n" + 
				"('7', 'You have reached mailbox 7. Please leave a message now.'),\r\n" + 
				"('8', 'You have reached mailbox 8. Please leave a message now.'),\r\n" + 
				"('9', 'You have reached mailbox 9. Please leave a message now.')";
	}
}
