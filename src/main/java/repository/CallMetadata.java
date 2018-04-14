package repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import credentials.DBManager;
import credentials.RestLogin;
import metadataResources.MetadataResource;

@Path("callheroku")
public class CallMetadata {

	MetadataResource metadataResource = new MetadataResource();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getdata(String inputarray)throws Exception {
		System.out.println("calling callheroku");
		
		List<String> classnames = new ArrayList<String>();
		List<String> logintoken = new ArrayList<String>();
		
		JSONObject jsonobject = new JSONObject(inputarray);
		
		String sfdcuserid = jsonobject.getString("sfdcuserid").split("##")[0];
		String sfdcusername = jsonobject.getString("sfdcuserid").split("##")[1];
		String startdate = jsonobject.getString("startdate");
		String enddate = jsonobject.getString("enddate");
		
		JSONArray metadataarray =  jsonobject.getJSONArray("metadata");
		for(int i = 0; i < metadataarray.length(); i++){
			classnames.add(metadataarray.getString(i));
		}
		
		JSONArray logintokenarray =  jsonobject.getJSONArray("logintoken");
		for(int i = 0; i < logintokenarray.length(); i++){
			logintoken.add(logintokenarray.getString(i));
		}
		
		
		System.out.println("sfdcuserid - "+sfdcuserid);
		System.out.println("sfdcusername - "+sfdcusername);
		System.out.println("sdate - "+startdate);
		System.out.println("edate - "+enddate);
		System.out.println("metaobj - "+classnames);
		System.out.println("logintoken - "+logintoken);
		
		JSONObject loginobject = new JSONObject();
		if (logintoken.size() != 0) {
			loginobject.put("instance_url", logintoken.get(0));
			byte [] decode = Base64.decodeBase64(logintoken.get(1));
			String access_token = new String (decode);
			System.out.println("decode  - "+access_token);
			loginobject.put("access_token", access_token);
			
		} 
		/*else
		{
			loginobject = RestLogin.GetLoginObject();
		}*/
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
		System.out.println("file---");
		File file = metadataResource.saveXml();
		if (file != null) {
			@SuppressWarnings("deprecation")
			String xml = FileUtils.readFileToString(new File(file.getPath()));
			JSONObject jsonmetadata = org.json.XML.toJSONObject(xml);
			if (PsqlDataHouse.setMetadataObjtoDB(sfdcusername, jsonmetadata) != 0) {
				System.out.println("JSON - created");
				DBManager.deleteFiles(file);
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
