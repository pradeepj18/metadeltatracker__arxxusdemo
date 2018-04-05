package repository;

import java.net.URISyntaxException;
import java.sql.SQLException;

import org.json.JSONObject;

import credentials.DBManager;

public class PsqlDataHouse {

	public static int setUserKeytoDB(String sfdcusername,String datetime)
	{
		try {
			DBManager.loadDriver();
			int row = DBManager.CreUpDel("insert into sfdcmetadata(datakey,entrydate,status) values('"+sfdcusername+"','"+datetime+"','false')");
			if(row>0)
				return row;
			
		} catch (URISyntaxException | SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int setMetadataObjtoDB(String sfdcusername,JSONObject jsonobject)
	{
		try {
			DBManager.loadDriver();
			int row = DBManager.CreUpDel("update sfdcmetadata set metadata ='"+jsonobject+"',status='true' where datakey='"+sfdcusername+"'");
			if(row>0)
				return row;
			
		} catch (URISyntaxException | SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public static int delteDBEntry(String sfdcusername)
	{
		try {
			DBManager.loadDriver();
			int row = DBManager.CreUpDel("delete from sfdcmetadata where datakey='"+sfdcusername+"'");
			if(row>0)
				return row;
			
		} catch (URISyntaxException | SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
