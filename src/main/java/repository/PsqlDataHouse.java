package repository;

import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import credentials.DBManager;

@Path("herokuDB")
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
	
	@GET
	@Path("getfinaldata")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public static Response getJsonobjectfromHerokuDB(@QueryParam("sfdcuserid") String sfdcuserid_usrname)
	{
		String sfdcusername = sfdcuserid_usrname.split("##")[1];
		
		String msg="";
		try {
			DBManager.loadDriver();
			ResultSet result =  DBManager.fetchQuery("select * from sfdcmetadata where datakey='"+sfdcusername+"' and status=true");
			if(result.next())
			{
				org.json.JSONObject jsonFileObject = new org.json.JSONObject(result.getString("metadata"));
		       String xml = org.json.XML.toString(jsonFileObject);
		        return Response.status(200).entity(xml).build();
			}
			else
			{
				
				ResultSet error =  DBManager.fetchQuery("select * from sfdcmetadata where datakey='"+sfdcusername+"' and status=false");
				if(error.next())
				{
					return Response.status(200).entity("Metadata not found in database").build();
				}
				
			}
			
		} catch (URISyntaxException | SQLException e) {
			e.printStackTrace();
			msg=e.toString();
			
		}
		return Response.status(200).entity("Something going wrong - "+msg).build();
	}
}
