package UtilDatabase;

import java.sql.ResultSet;

public interface Database {
	ResultSet querySelector(String query);
	void queryUpdate(String query);
	void initializeConnection();
}
