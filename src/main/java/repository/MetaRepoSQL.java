package repository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("sfdcmetadataPSQL")
public class MetaRepoSQL {
	
	/*@GET
	@Produces({ MediaType.TEXT_PLAIN })
	public Response getdata(@QueryParam("sfdcuserid") String sfdcuserid_usrname) throws Exception {*/
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getdata(String sfdcuserid_usrname) throws Exception {
		System.out.println("calling metadataPSQL-- ");
		try {
			sfdcuserid_usrname = sfdcuserid_usrname.replaceAll("^\"|\"$", "");
			String sfdcusername = sfdcuserid_usrname.split("##")[1];
			
			PsqlDataHouse.delAlldata(sfdcusername);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			int row = PsqlDataHouse.setUserKeytoDB(sfdcusername, dateFormat.format(date));
			if (row > 0) {
				return Response.status(200).entity("200").build();//Processing metadata
			} else {
				return Response.status(200).entity("422").build();//Invalid entry for database
			}
		} catch (Exception e) {
			System.out.println("error - " + e);
			return Response.status(400).entity("400").build();//Something going wrong
		}
	}
}
