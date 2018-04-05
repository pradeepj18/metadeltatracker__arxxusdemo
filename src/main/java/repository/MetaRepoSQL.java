package repository;

import java.io.File;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;

import org.json.JSONObject;

import credentials.RestLogin;
import metadataResources.MetadataResource;

@Path("sfdcmetadataPSQL")
public class MetaRepoSQL {

	MetadataResource metadataResource = new MetadataResource();

/*	@GET
	public void getdata(@Context UriInfo info)
	{
		System.out.println(info.getQueryParameters());
	}*/

	@GET 
	@Produces({MediaType.TEXT_PLAIN})
	public Response getdata(@QueryParam("metadata[]") List<String> classnames,
			@QueryParam("sfdcuserid") String sfdcuserid_usrname, @QueryParam("startdate") String startdate,
			@QueryParam("enddate") String enddate, @QueryParam("logintoken[]") List<String> logintoken)
			throws Exception {
	System.out.println("calling metadataPSQL");
		String sfdcuserid = sfdcuserid_usrname.split("##")[0];
		String sfdcusername = sfdcuserid_usrname.split("##")[1];
		JSONObject loginobject = new JSONObject();
		if (logintoken.size() != 0) {
			loginobject.put("instance_url", logintoken.get(0));
			loginobject.put("access_token", logintoken.get(1) + logintoken.get(2));
		} else {
			loginobject = RestLogin.GetLoginObject();
		}
		StringBuffer sdate = new StringBuffer();
		sdate.append(startdate);
		sdate.append("T00:00:00.000Z");

		StringBuffer edate = new StringBuffer();
		edate.append(enddate);
		edate.append("T23:59:59.000Z");

		/*System.out.println("sfdcuserid - " + sfdcuserid);
		System.out.println(sdate + "---" + edate);
		System.out.println("metadata - " + classnames);
		System.out.println("logintoken - " + loginobject);
		System.out.println("sfdcusername - " + sfdcusername);
*/
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		int row = PsqlDataHouse.setUserKeytoDB(sfdcusername, dateFormat.format(date));
		if (row > 0) {
			/*CallMetadata callMetadata = new CallMetadata();
			callMetadata.getdata(classnames, sfdcuserid, startdate, enddate, logintoken, sfdcusername);
		} else {
			PsqlDataHouse.delteDBEntry(sfdcusername);
		}*/
		return Response.status(202).entity("Processing metadata").build();
		}
		else
		
			return Response.status(404).entity("Not Processing metadata").build();
		
			
	}
}
