package repository;

import java.io.File;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import credentials.RestLogin;
import metadataResources.MetadataResource;

@Path("callheroku")
public class CallMetadata {

	MetadataResource metadataResource = new MetadataResource();

	@GET
	@Produces({ MediaType.TEXT_PLAIN })
	public Response getdata(@QueryParam("metadata[]") List<String> classnames,
			@QueryParam("sfdcuserid") String sfdcuserid_usrname, @QueryParam("startdate") String startdate,
			@QueryParam("enddate") String enddate, @QueryParam("logintoken[]") List<String> logintoken)
			throws Exception {
		System.out.println("calling callheroku");
		String sfdcuserid = sfdcuserid_usrname.split("##")[0];
		String sfdcusername = sfdcuserid_usrname.split("##")[1];
		JSONObject loginobject = new JSONObject();
		if (logintoken.size() != 0) {
			loginobject.put("instance_url", logintoken.get(0));
			loginobject.put("access_token", logintoken.get(1) + logintoken.get(2));
		} else {
			loginobject = RestLogin.GetLoginObject();// remove it once connected to lightning
		}
		StringBuffer sdate = new StringBuffer();
		sdate.append(startdate);
		sdate.append("T00:00:00.000Z");

		StringBuffer edate = new StringBuffer();
		edate.append(enddate);
		edate.append("T23:59:59.000Z");
		for (int i = 0; i < classnames.size(); i++) {
			switch (Integer.parseInt(classnames.get(i))) {
			case 101:
				metadataResource.getApexClasses(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			case 103:
				metadataResource.getApexPages(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			case 102:
				metadataResource.getApexComponents(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			case 104:
				metadataResource.getApexTriggers(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			case 105:
				metadataResource.getAssignmentRule(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			case 106:
				metadataResource.getAuraDefinitionBundle(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			case 107:
				metadataResource.getAutoResponse(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			case 108:
				metadataResource.getBusinessProcess(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			case 109:
				metadataResource.getCompactLayout(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			case 110:
				metadataResource.getConnectedApplication(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			case 111:
				metadataResource.getCustomApplication(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			case 112:
				metadataResource.getCustomField(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			case 113:
				metadataResource.getCustomObject(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			case 114:
				metadataResource.getCustomTab(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			case 115:
				metadataResource.getDashboard(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			case 116:
				metadataResource.getEmailTemplate(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			case 117:
				metadataResource.getFieldSet(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			/*
			 * case 118: metadataResource.getFlexiPage(loginobject, sfdcuserid,
			 * sdate.toString(), edate.toString()); break;
			 */
			case 119:
				metadataResource.getFlow(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			case 120:
				metadataResource.getGlobalValueSet(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			case 121:
				metadataResource.getHomePageLayout(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			case 122:
				metadataResource.getLayout(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			case 123:
				metadataResource.getPermission(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			case 124:
				metadataResource.getProfile(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			case 125:
				metadataResource.getRecordType(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			case 126:
				metadataResource.getReport(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			case 127:
				metadataResource.getStaticResources(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			case 128:
				metadataResource.getUser(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			case 129:
				metadataResource.getValidationRule(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			case 130:
				metadataResource.getWebLink(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			case 131:
				metadataResource.getWorkFlowAlert(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			case 132:
				metadataResource.getWorkFlowFieldUpdate(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			case 133:
				metadataResource.getWorkFlowRule(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			case 134:
				metadataResource.getWorkFlowTask(loginobject, sfdcuserid, sdate.toString(), edate.toString());
				break;
			}
		}
		File file = metadataResource.saveXml();
		if (file != null) {
			@SuppressWarnings("deprecation")
			String xml = FileUtils.readFileToString(new File(file.getPath()));
			JSONObject jsonmetadata = org.json.XML.toJSONObject(xml);
			if (PsqlDataHouse.setMetadataObjtoDB(sfdcusername, jsonmetadata) != 0) {
				System.out.println("JSON - created");
				return Response.status(200).entity("200").build();// Metadata process complete,now you can download xml file
			} else {
				return Response.status(200).entity("204").build();
			}
		} else {
			System.out.println("file null");
			PsqlDataHouse.delteDBEntry(sfdcusername);
			return Response.status(200).entity("204").build();// Metadata not found
		}
	}
}
