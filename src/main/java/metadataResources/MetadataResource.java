package metadataResources;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import credentials.RestLogin;
import dataContainer.DataWarehouse;
import metadataPOJO.ApexClass;
import metadataPOJO.ApexComponent;
import metadataPOJO.ApexPage;
import metadataPOJO.ApexTrigger;
import metadataPOJO.AssignmentRule;
import metadataPOJO.AuraDefinitionBundle;
import metadataPOJO.AutoResponse;
import metadataPOJO.BusinessProcess;
import metadataPOJO.CompactLayout;
import metadataPOJO.ConnectedApplication;
import metadataPOJO.CustomApplication;
import metadataPOJO.CustomField;
import metadataPOJO.CustomObject;
import metadataPOJO.CustomTab;
import metadataPOJO.Dashboard;
import metadataPOJO.EmailTemplate;
import metadataPOJO.FieldSet;
import metadataPOJO.FlexiPage;
import metadataPOJO.Flow;
import metadataPOJO.GlobalValueSet;
import metadataPOJO.HomePageLayout;
import metadataPOJO.Layout;
import metadataPOJO.Permission;
import metadataPOJO.Profile;
import metadataPOJO.RecordType;
import metadataPOJO.Report;
import metadataPOJO.StaticResources;
import metadataPOJO.User;
import metadataPOJO.ValidationRule;
import metadataPOJO.WebLink;
import metadataPOJO.WorkFlowAlert;
import metadataPOJO.WorkFlowFieldUpdate;
import metadataPOJO.WorkFlowRule;
import metadataPOJO.WorkFlowTask;

public class MetadataResource {

	/*JSONObject loginObject = RestLogin.GetLoginObject();
	String startdate = "2018-02-01T17:23:04.000Z";
	String enddate = "2018-03-01T17:23:04.000Z";*/
	Document doc;
	Element xmlroot;

	public MetadataResource() {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.newDocument();
			xmlroot = doc.createElement("Package");
			doc.appendChild(xmlroot);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public List<ApexClass> getApexClasses(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		JSONArray apexclassArray = DataWarehouse.getApexClassList(loginObject, startdate, enddate,sfdcuserid);
		List<ApexClass> apexClassList = new ArrayList<ApexClass>();
		if (apexclassArray != null) {
			if (apexclassArray.length() > 0) {
				Element xmlapexclasstype = doc.createElement("types");
				xmlroot.appendChild(xmlapexclasstype);

				try {

					for (int i = 0; i < apexclassArray.length(); i++) {
						Element xmlapexclassMembers = doc.createElement("members");
						xmlapexclassMembers
								.appendChild(doc.createTextNode(apexclassArray.getJSONObject(i).getString("Name")));
						xmlapexclasstype.appendChild(xmlapexclassMembers);
						
						ApexClass apexClass = new ApexClass();
						apexClass.setName(apexclassArray.getJSONObject(i).getString("Name"));
						apexClassList.add(apexClass);
					}
					Element xmlapexclassName = doc.createElement("name");
					xmlapexclassName.appendChild(doc.createTextNode("ApexClass"));
					xmlapexclasstype.appendChild(xmlapexclassName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return apexClassList;
	}

	public List<ApexTrigger> getApexTriggers(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		JSONArray apexTriggerArray = DataWarehouse.getApexTriggerList(loginObject, startdate, enddate,sfdcuserid);
		List<ApexTrigger> apexTriggerList = new ArrayList<ApexTrigger>();
		if (apexTriggerArray != null) {
			if (apexTriggerArray.length() > 0) {
				Element xmlapextriggertype = doc.createElement("types");
				xmlroot.appendChild(xmlapextriggertype);

				for (int i = 0; i < apexTriggerArray.length(); i++) {
					try {
						Element xmlapextriggerMembers = doc.createElement("members");
						xmlapextriggerMembers
								.appendChild(doc.createTextNode(apexTriggerArray.getJSONObject(i).getString("Name")));
						xmlapextriggertype.appendChild(xmlapextriggerMembers);
						ApexTrigger apexTrigger = new ApexTrigger();
						apexTrigger.setName(apexTriggerArray.getJSONObject(i).getString("Name"));
						apexTriggerList.add(apexTrigger);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlapextriggerName = doc.createElement("name");
				xmlapextriggerName.appendChild(doc.createTextNode("ApexTrigger"));
				xmlapextriggertype.appendChild(xmlapextriggerName);
			}
		}
		return apexTriggerList;
	}

	public List<ApexPage> getApexPages(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<ApexPage> apexPageList = new ArrayList<ApexPage>();
		JSONArray apexPageArray = DataWarehouse.getApexPageList(loginObject, startdate, enddate,sfdcuserid);
		if (apexPageArray != null) {
			if (apexPageArray.length() > 0) {
				Element xmlapexPagetype = doc.createElement("types");
				xmlroot.appendChild(xmlapexPagetype);
				for (int i = 0; i < apexPageArray.length(); i++) {
					try {
						Element xmlapexPageMembers = doc.createElement("members");
						xmlapexPageMembers
								.appendChild(doc.createTextNode(apexPageArray.getJSONObject(i).getString("Name")));
						xmlapexPagetype.appendChild(xmlapexPageMembers);
						ApexPage apexPage = new ApexPage();
						apexPage.setName(apexPageArray.getJSONObject(i).getString("Name"));
						apexPageList.add(apexPage);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlapexPageName = doc.createElement("name");
				xmlapexPageName.appendChild(doc.createTextNode("ApexPage"));
				xmlapexPagetype.appendChild(xmlapexPageName);
			}
		}
		return apexPageList;
	}

	public List<ApexComponent> getApexComponents(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<ApexComponent> ApexComponentList = new ArrayList<ApexComponent>();
		JSONArray apexComponentArray = DataWarehouse.getApexComponentList(loginObject, startdate, enddate,sfdcuserid);
		if (apexComponentArray != null) {
			if (apexComponentArray.length() > 0) {
				Element xmlapexComponenttype = doc.createElement("types");
				xmlroot.appendChild(xmlapexComponenttype);
				for (int i = 0; i < apexComponentArray.length(); i++) {
					try {
						Element xmlapexComponentMembers = doc.createElement("members");
						xmlapexComponentMembers
								.appendChild(doc.createTextNode(apexComponentArray.getJSONObject(i).getString("Name")));
						xmlapexComponenttype.appendChild(xmlapexComponentMembers);
						ApexComponent apexComponent = new ApexComponent();
						apexComponent.setName(apexComponentArray.getJSONObject(i).getString("Name"));
						ApexComponentList.add(apexComponent);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlapexComponentName = doc.createElement("name");
				xmlapexComponentName.appendChild(doc.createTextNode("ApexComponent"));
				xmlapexComponenttype.appendChild(xmlapexComponentName);
			}
		}
		return ApexComponentList;
	}

	public List<AssignmentRule> getAssignmentRule(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<AssignmentRule> AssignmentRuleList = new ArrayList<AssignmentRule>();
		JSONArray assignmentRuleArray = DataWarehouse.getAssignmentRuleList(loginObject, startdate, enddate,sfdcuserid);
		if (assignmentRuleArray != null) {
			if (assignmentRuleArray.length() > 0) {
				Element xmlassignmentRuletype = doc.createElement("types");
				xmlroot.appendChild(xmlassignmentRuletype);
				for (int i = 0; i < assignmentRuleArray.length(); i++) {
					try {
						Element xmlassignmentRuleMembers = doc.createElement("members");
						xmlassignmentRuleMembers.appendChild(
								doc.createTextNode(assignmentRuleArray.getJSONObject(i).getString("EntityDefinitionId")
										+ "." + assignmentRuleArray.getJSONObject(i).getString("Name")));
						xmlassignmentRuletype.appendChild(xmlassignmentRuleMembers);
						AssignmentRule assign = new AssignmentRule();
						assign.setEntityDefinitionId(
								assignmentRuleArray.getJSONObject(i).getString("EntityDefinitionId"));
						assign.setName(assignmentRuleArray.getJSONObject(i).getString("Name"));
						AssignmentRuleList.add(assign);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlassignmentRuleName = doc.createElement("name");
				xmlassignmentRuleName.appendChild(doc.createTextNode("AssignmentRule"));// Make it dynamic
				xmlassignmentRuletype.appendChild(xmlassignmentRuleName);
			}
		}
		return AssignmentRuleList;
	}

	public List<AuraDefinitionBundle> getAuraDefinitionBundle(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<AuraDefinitionBundle> AuraDefinitionBundleList = new ArrayList<AuraDefinitionBundle>();
		JSONArray auraDefinitionBundleArray = DataWarehouse.getAuraDefinitionBundleList(loginObject, startdate,enddate,sfdcuserid);
		if (auraDefinitionBundleArray != null) {
			if (auraDefinitionBundleArray.length() > 0) {
				Element xmlAuraDefinitionBundletype = doc.createElement("types");
				xmlroot.appendChild(xmlAuraDefinitionBundletype);
				try {
					for (int i = 0; i < auraDefinitionBundleArray.length(); i++) {
						Element xmlAuraDefinitionBundleMembers = doc.createElement("members");
						xmlAuraDefinitionBundleMembers.appendChild(doc
								.createTextNode(auraDefinitionBundleArray.getJSONObject(i).getString("DeveloperName")));
						xmlAuraDefinitionBundletype.appendChild(xmlAuraDefinitionBundleMembers);
						AuraDefinitionBundle aura = new AuraDefinitionBundle();
						aura.setDeveloperName(auraDefinitionBundleArray.getJSONObject(i).getString("DeveloperName"));
						AuraDefinitionBundleList.add(aura);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				Element xmlAuraDefinitionBundleName = doc.createElement("name");
				xmlAuraDefinitionBundleName.appendChild(doc.createTextNode("AuraDefinitionBundle"));
				xmlAuraDefinitionBundletype.appendChild(xmlAuraDefinitionBundleName);
			}
		}
		return AuraDefinitionBundleList;
	}

	public List<AutoResponse> getAutoResponse(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<AutoResponse> AutoResponseList = new ArrayList<AutoResponse>();
		JSONArray autoResponseArray = DataWarehouse.getAutoResponseList(loginObject, startdate, enddate,sfdcuserid);
		if (autoResponseArray != null) {
			if (autoResponseArray.length() > 0) {
				Element xmlautoResponseListtype = doc.createElement("types");
				xmlroot.appendChild(xmlautoResponseListtype);
				for (int i = 0; i < autoResponseArray.length(); i++) {
					try {
						Element xmlautoResponseListMembers = doc.createElement("members");
						xmlautoResponseListMembers
								.appendChild(doc.createTextNode(autoResponseArray.getJSONObject(i).getString("Name")));
						xmlautoResponseListtype.appendChild(xmlautoResponseListMembers);
						AutoResponse autores = new AutoResponse();
						autores.setName(autoResponseArray.getJSONObject(i).getString("Name"));
						AutoResponseList.add(autores);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				Element xmlautoResponseListName = doc.createElement("name");
				xmlautoResponseListName.appendChild(doc.createTextNode("ApexComponent"));// Make it dynamic
				xmlautoResponseListtype.appendChild(xmlautoResponseListName);
			}
		}
		return AutoResponseList;
	}

	public List<BusinessProcess> getBusinessProcess(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<BusinessProcess> BusinessProcessList = new ArrayList<BusinessProcess>();
		JSONArray businessProcessArray = DataWarehouse.getBusinessProcessList(loginObject, startdate, enddate,sfdcuserid);
		if (businessProcessArray != null) {
			if (businessProcessArray.length() > 0) {
				Element xmlbusinessProcesstype = doc.createElement("types");
				xmlroot.appendChild(xmlbusinessProcesstype);
				for (int i = 0; i < businessProcessArray.length(); i++) {
					try {
						Element xmlbusinessProcessMembers = doc.createElement("members");
						xmlbusinessProcessMembers.appendChild(
								doc.createTextNode(businessProcessArray.getJSONObject(i).getString("Name")));
						xmlbusinessProcesstype.appendChild(xmlbusinessProcessMembers);
						BusinessProcess bp = new BusinessProcess();
						bp.setName(businessProcessArray.getJSONObject(i).getString("Name"));
						BusinessProcessList.add(bp);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlbusinessProcessName = doc.createElement("name");
				xmlbusinessProcessName.appendChild(doc.createTextNode("BusinessProcess"));// Make it dynamic
				xmlbusinessProcesstype.appendChild(xmlbusinessProcessName);
			}
		}
		return BusinessProcessList;
	}

	public List<CompactLayout> getCompactLayout(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<CompactLayout> CompactLayoutList = new ArrayList<CompactLayout>();
		JSONArray compactLayoutArray = DataWarehouse.getCompactLayoutList(loginObject, startdate, enddate,sfdcuserid);
		if (compactLayoutArray != null) {
			if (compactLayoutArray.length() > 0) {
				Element xmlcompactLayouttype = doc.createElement("types");
				xmlroot.appendChild(xmlcompactLayouttype);

				for (int i = 0; i < compactLayoutArray.length(); i++) {
					try {
						Element xmlcompactLayoutMembers = doc.createElement("members");
						xmlcompactLayoutMembers.appendChild(
								doc.createTextNode(compactLayoutArray.getJSONObject(i).getString("SobjectType") + "."
										+ compactLayoutArray.getJSONObject(i).getString("DeveloperName")));
						xmlcompactLayouttype.appendChild(xmlcompactLayoutMembers);
						CompactLayout cl = new CompactLayout();
						cl.setSobjectType(compactLayoutArray.getJSONObject(i).getString("SobjectType"));
						cl.setDeveloperName(compactLayoutArray.getJSONObject(i).getString("DeveloperName"));
						CompactLayoutList.add(cl);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlcompactLayoutName = doc.createElement("name");
				xmlcompactLayoutName.appendChild(doc.createTextNode("CompactLayout"));// Make it dynamic
				xmlcompactLayouttype.appendChild(xmlcompactLayoutName);
			}
		}
		return CompactLayoutList;
	}

	public List<ConnectedApplication> getConnectedApplication(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<ConnectedApplication> ConnectedApplicationList = new ArrayList<ConnectedApplication>();
		JSONArray connectedApplicationArray = DataWarehouse.getConnectedApplicationList(loginObject, startdate,enddate,sfdcuserid);
		if (connectedApplicationArray != null) {
			if (connectedApplicationArray.length() > 0) {
				Element xmlconnectedApplicationtype = doc.createElement("types");
				xmlroot.appendChild(xmlconnectedApplicationtype);
				for (int i = 0; i < connectedApplicationArray.length(); i++) {
					try {
						Element xmlconnectedApplicationMembers = doc.createElement("members");
						xmlconnectedApplicationMembers.appendChild(
								doc.createTextNode(connectedApplicationArray.getJSONObject(i).getString("Name")));
						xmlconnectedApplicationtype.appendChild(xmlconnectedApplicationMembers);
						ConnectedApplication ca = new ConnectedApplication();
						ca.setName(connectedApplicationArray.getJSONObject(i).getString("Name"));
						ConnectedApplicationList.add(ca);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlconnectedApplicationName = doc.createElement("name");
				xmlconnectedApplicationName.appendChild(doc.createTextNode("ConnectedApplication"));// Make it dynamic
				xmlconnectedApplicationtype.appendChild(xmlconnectedApplicationName);
			}
		}
		return ConnectedApplicationList;
	}

	public List<CustomApplication> getCustomApplication(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<CustomApplication> CustomApplicationList = new ArrayList<CustomApplication>();
		JSONArray customApplicationArray = DataWarehouse.getCustomApplicationList(loginObject, startdate, enddate,sfdcuserid);
		if (customApplicationArray != null) {
			if (customApplicationArray.length() > 0) {
				Element xmlcustomApplicationtype = doc.createElement("types");
				xmlroot.appendChild(xmlcustomApplicationtype);
				for (int i = 0; i < customApplicationArray.length(); i++) {
					try {
						Element xmlcustomApplicationMembers = doc.createElement("members");
						xmlcustomApplicationMembers.appendChild(
								doc.createTextNode(customApplicationArray.getJSONObject(i).getString("DeveloperName")));
						xmlcustomApplicationtype.appendChild(xmlcustomApplicationMembers);
						CustomApplication capp = new CustomApplication();
						capp.setDeveloperName(customApplicationArray.getJSONObject(i).getString("DeveloperName"));
						CustomApplicationList.add(capp);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlcustomApplicationName = doc.createElement("name");
				xmlcustomApplicationName.appendChild(doc.createTextNode("CustomApplication"));// Make it dynamic
				xmlcustomApplicationtype.appendChild(xmlcustomApplicationName);
			}
		}
		return CustomApplicationList;
	}

	public List<CustomObject> getCustomObject(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<CustomObject> CustomObjectList = new ArrayList<CustomObject>();
		JSONArray customobjectArray = DataWarehouse.getCustomObjectList(loginObject, startdate, enddate,sfdcuserid);
		if (customobjectArray != null) {
			if (customobjectArray.length() > 0) {
				Element xmlcustomobjecttype = doc.createElement("types");
				xmlroot.appendChild(xmlcustomobjecttype);
				for (int i = 0; i < customobjectArray.length(); i++) {
					try {
						Element xmlcustomobjectMembers = doc.createElement("members");
						xmlcustomobjectMembers.appendChild(doc
								.createTextNode(customobjectArray.getJSONObject(i).getString("DeveloperName") + "__c"));
						xmlcustomobjecttype.appendChild(xmlcustomobjectMembers);
						CustomObject co = new CustomObject();
						co.setDeveloperName(customobjectArray.getJSONObject(i).getString("DeveloperName") + "__c");
						CustomObjectList.add(co);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				Element xmlcustomobjectName = doc.createElement("name");
				xmlcustomobjectName.appendChild(doc.createTextNode("CustomObject"));
				xmlcustomobjecttype.appendChild(xmlcustomobjectName);
			}
		}
		return CustomObjectList;
	}

	public List<CustomField> getCustomField(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<CustomField> CustomFieldList = new ArrayList<CustomField>();
		JSONArray customFieldArray = DataWarehouse.__getCustomFieldList(loginObject, startdate, enddate,sfdcuserid);
		if (customFieldArray != null) {
			if (customFieldArray.length() > 0) {
				Element xmlcustomfieldtype = doc.createElement("types");
				xmlroot.appendChild(xmlcustomfieldtype);

				String customObjectName = "";
				for (int j = 0; j < customFieldArray.length(); j++) {
					if (customFieldArray.getJSONObject(j).getString("TableEnumOrId").matches("^[A-Za-z]+[0-9]*"))
						customObjectName = customFieldArray.getJSONObject(j).getString("TableEnumOrId");
					else {
						customObjectName = DataWarehouse.getCustomObjectName(loginObject,
								customFieldArray.getJSONObject(j).getString("TableEnumOrId"));
						customObjectName += "__c";
					}
					Element xmlcustomfieldMembers = doc.createElement("members");
					xmlcustomfieldMembers.appendChild(doc.createTextNode(customObjectName + "."
							+ customFieldArray.getJSONObject(j).getString("DeveloperName") + "__c"));
					xmlcustomfieldtype.appendChild(xmlcustomfieldMembers);
					CustomField cf = new CustomField();
					cf.setDeveloperName(customFieldArray.getJSONObject(j).getString("DeveloperName") + "__c");
					cf.setObjectName(customObjectName);
					CustomFieldList.add(cf);
				}

				Element xmlcustomfieldName = doc.createElement("name");
				xmlcustomfieldName.appendChild(doc.createTextNode("CustomField"));
				xmlcustomfieldtype.appendChild(xmlcustomfieldName);
			}
		}
		return CustomFieldList;
	}

	public List<CustomTab> getCustomTab(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<CustomTab> CustomTabList = new ArrayList<CustomTab>();
		JSONArray customTabArray = DataWarehouse.getCustomTabList(loginObject, startdate, enddate,sfdcuserid);
		if (customTabArray != null) {
			if (customTabArray.length() > 0) {
				Element xmlcustomTabtype = doc.createElement("types");
				xmlroot.appendChild(xmlcustomTabtype);
				for (int i = 0; i < customTabArray.length(); i++) {
					try {
						JSONArray customTabFullname = DataWarehouse.getFullname("CustomTab",
								customTabArray.getJSONObject(i).getString("Id"), loginObject);
						Element xmlcustomTabMembers = doc.createElement("members");
						xmlcustomTabMembers.appendChild(
								doc.createTextNode(customTabFullname.getJSONObject(0).getString("FullName")));
						xmlcustomTabtype.appendChild(xmlcustomTabMembers);

						CustomTab ct = new CustomTab();
						ct.setFullName(customTabFullname.getJSONObject(0).getString("FullName"));
						CustomTabList.add(ct);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlcustomTabName = doc.createElement("name");
				xmlcustomTabName.appendChild(doc.createTextNode("CustomTab"));
				xmlcustomTabtype.appendChild(xmlcustomTabName);
			}
		}
		return CustomTabList;
	}

	public List<Dashboard> getDashboard(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<Dashboard> DashboardList = new ArrayList<Dashboard>();
		JSONArray dashboardArray = DataWarehouse.getDashboardList(loginObject, startdate, enddate,sfdcuserid);
		if (dashboardArray != null) {
			if (dashboardArray.length() > 0) {
				Element xmldashboardtype = doc.createElement("types");
				xmlroot.appendChild(xmldashboardtype);
				for (int i = 0; i < dashboardArray.length(); i++) {
					try {
						Element xmldashboardMembers = doc.createElement("members");
						xmldashboardMembers.appendChild(
								doc.createTextNode(dashboardArray.getJSONObject(i).getString("DeveloperName")));
						xmldashboardtype.appendChild(xmldashboardMembers);

						Dashboard d = new Dashboard();
						d.setDeveloperName(dashboardArray.getJSONObject(i).getString("DeveloperName"));
						DashboardList.add(d);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmldashboardName = doc.createElement("name");
				xmldashboardName.appendChild(doc.createTextNode("Dashboard"));
				xmldashboardtype.appendChild(xmldashboardName);
			}
		}
		return DashboardList;
	}

	public List<EmailTemplate> getEmailTemplate(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<EmailTemplate> EmailTemplateList = new ArrayList<EmailTemplate>();
		JSONArray emailTemplateArray = DataWarehouse.getEmailTemplateList(loginObject, startdate, enddate,sfdcuserid);
		if (emailTemplateArray != null) {
			if (emailTemplateArray.length() > 0) {
				Element xmlemailTemplateListtype = doc.createElement("types");
				xmlroot.appendChild(xmlemailTemplateListtype);
				for (int i = 0; i < emailTemplateArray.length(); i++) {
					try {
						Element xmlemailTemplateListMembers = doc.createElement("members");
						xmlemailTemplateListMembers
								.appendChild(doc.createTextNode(emailTemplateArray.getJSONObject(i).getString("Name")));
						xmlemailTemplateListtype.appendChild(xmlemailTemplateListMembers);
						EmailTemplate et = new EmailTemplate();
						et.setName(emailTemplateArray.getJSONObject(i).getString("Name"));
						EmailTemplateList.add(et);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlemailTemplateListName = doc.createElement("name");
				xmlemailTemplateListName.appendChild(doc.createTextNode("EmailTemplate"));
				xmlemailTemplateListtype.appendChild(xmlemailTemplateListName);
			}
		}
		return EmailTemplateList;

	}

	public List<FieldSet> getFieldSet(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<FieldSet> FieldSetList = new ArrayList<FieldSet>();
		JSONArray fieldSetArray = DataWarehouse.getFieldSetList(loginObject, startdate, enddate,sfdcuserid);
		if (fieldSetArray != null) {
			if (fieldSetArray.length() > 0) {
				Element xmlfieldSettype = doc.createElement("types");
				xmlroot.appendChild(xmlfieldSettype);
				for (int i = 0; i < fieldSetArray.length(); i++) {
					try {
						Element xmlfieldSetMembers = doc.createElement("members");
						xmlfieldSetMembers.appendChild(
								doc.createTextNode(fieldSetArray.getJSONObject(i).getString("DeveloperName")));
						xmlfieldSettype.appendChild(xmlfieldSetMembers);
						FieldSet fs = new FieldSet();
						fs.setDeveloperName(fieldSetArray.getJSONObject(i).getString("DeveloperName"));
						FieldSetList.add(fs);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlxmlfieldSetName = doc.createElement("name");
				xmlxmlfieldSetName.appendChild(doc.createTextNode("FieldSet"));
				xmlfieldSettype.appendChild(xmlxmlfieldSetName);
			}
		}
		return FieldSetList;

	}

	public List<FlexiPage> getFlexiPage (JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<FlexiPage> FlexiPageList = new ArrayList<FlexiPage>();
		JSONArray flexiPageArray = DataWarehouse.getFlexiPageList(loginObject, startdate, enddate,sfdcuserid);
		if (flexiPageArray != null) {
			if (flexiPageArray.length() > 0) {
				Element xmlflexiPageListtype = doc.createElement("types");
				xmlroot.appendChild(xmlflexiPageListtype);
				for (int i = 0; i < flexiPageArray.length(); i++) {
					try {
						Element xmlflexiPageListMembers = doc.createElement("members");
						xmlflexiPageListMembers.appendChild(
								doc.createTextNode(flexiPageArray.getJSONObject(i).getString("DeveloperName")));
						xmlflexiPageListtype.appendChild(xmlflexiPageListMembers);
						FlexiPage fp = new FlexiPage();
						fp.setDeveloperName(flexiPageArray.getJSONObject(i).getString("DeveloperName"));
						FlexiPageList.add(fp);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlflexiPageListName = doc.createElement("name");
				xmlflexiPageListName.appendChild(doc.createTextNode("FlexiPage"));
				xmlflexiPageListtype.appendChild(xmlflexiPageListName);
			}
		}
		return FlexiPageList;

	}

	public List<Flow> getFlow(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<Flow> FlowList = new ArrayList<Flow>();
		JSONArray flowArray = DataWarehouse.getFlowList(loginObject, startdate, enddate,sfdcuserid);
		if (flowArray != null) {
			if (flowArray.length() > 0) {
				Element xmlflowtype = doc.createElement("types");
				xmlroot.appendChild(xmlflowtype);
				for (int i = 0; i < flowArray.length(); i++) {
					try {
						JSONArray flowFullname = DataWarehouse.getFullname("Flow",
								flowArray.getJSONObject(i).getString("Id"), loginObject);
						Element xmlflowMembers = doc.createElement("members");
						xmlflowMembers
								.appendChild(doc.createTextNode(flowFullname.getJSONObject(0).getString("FullName")));
						xmlflowtype.appendChild(xmlflowMembers);
						Flow flow = new Flow();
						flow.setFullName(flowFullname.getJSONObject(0).getString("FullName"));
						FlowList.add(flow);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlflowName = doc.createElement("name");
				xmlflowName.appendChild(doc.createTextNode("Flow"));
				xmlflowtype.appendChild(xmlflowName);
			}
		}
		return FlowList;

	}

	public List<GlobalValueSet> getGlobalValueSet(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<GlobalValueSet> GlobalValueSetList = new ArrayList<GlobalValueSet>();
		JSONArray globalValueSetArray = DataWarehouse.getGlobalValueSetList(loginObject, startdate, enddate,sfdcuserid);
		if (globalValueSetArray != null) {
			if (globalValueSetArray.length() > 0) {
				Element xmlglobalValueSettype = doc.createElement("types");
				xmlroot.appendChild(xmlglobalValueSettype);
				for (int i = 0; i < globalValueSetArray.length(); i++) {
					try {
						Element xmlglobalValueSetMembers = doc.createElement("members");
						xmlglobalValueSetMembers.appendChild(
								doc.createTextNode(globalValueSetArray.getJSONObject(i).getString("DeveloperName")));
						xmlglobalValueSettype.appendChild(xmlglobalValueSetMembers);
						GlobalValueSet gvs = new GlobalValueSet();
						gvs.setDeveloperName(globalValueSetArray.getJSONObject(i).getString("DeveloperName"));
						GlobalValueSetList.add(gvs);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlglobalValueSetName = doc.createElement("name");
				xmlglobalValueSetName.appendChild(doc.createTextNode("GlobalValueSet"));
				xmlglobalValueSettype.appendChild(xmlglobalValueSetName);
			}
		}
		return GlobalValueSetList;

	}

	public List<HomePageLayout> getHomePageLayout(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<HomePageLayout> HomePageLayoutList = new ArrayList<HomePageLayout>();
		JSONArray homePageLayoutArray = DataWarehouse.getHomePageLayoutList(loginObject, startdate, enddate,sfdcuserid);
		if (homePageLayoutArray != null) {
			if (homePageLayoutArray.length() > 0) {
				Element xmlhomePageLayouttype = doc.createElement("types");
				xmlroot.appendChild(xmlhomePageLayouttype);
				for (int i = 0; i < homePageLayoutArray.length(); i++) {
					try {
						Element xmlhomePageLayoutMembers = doc.createElement("members");
						xmlhomePageLayoutMembers.appendChild(
								doc.createTextNode(homePageLayoutArray.getJSONObject(i).getString("Name")));
						xmlhomePageLayouttype.appendChild(xmlhomePageLayoutMembers);
						HomePageLayout hpl = new HomePageLayout();
						hpl.setName(homePageLayoutArray.getJSONObject(i).getString("Name"));
						HomePageLayoutList.add(hpl);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlhomePageLayoutName = doc.createElement("name");
				xmlhomePageLayoutName.appendChild(doc.createTextNode("HomePageLayout"));
				xmlhomePageLayouttype.appendChild(xmlhomePageLayoutName);
			}
		}
		return HomePageLayoutList;
	}

	public List<Layout> getLayout(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<Layout> LayoutList = new ArrayList<Layout>();
		JSONArray layoutArray = DataWarehouse.getLayoutList(loginObject, startdate, enddate,sfdcuserid);
		if (layoutArray != null) {
			if (layoutArray.length() > 0) {
				Element xmlcustomfieldtype = doc.createElement("types");
				xmlroot.appendChild(xmlcustomfieldtype);

				String customObjectName = "";
				for (int j = 0; j < layoutArray.length(); j++) {
					if (layoutArray.getJSONObject(j).getString("TableEnumOrId").matches("^[A-Za-z]+[0-9]*"))
						customObjectName = layoutArray.getJSONObject(j).getString("TableEnumOrId");
					else {
						customObjectName = DataWarehouse.getCustomObjectName(loginObject,
								layoutArray.getJSONObject(j).getString("TableEnumOrId"));
						customObjectName += "__c";
					}
					Element xmlcustomfieldMembers = doc.createElement("members");
					xmlcustomfieldMembers.appendChild(doc
							.createTextNode(customObjectName + "." + layoutArray.getJSONObject(j).getString("Name")));
					xmlcustomfieldtype.appendChild(xmlcustomfieldMembers);
					Layout layout = new Layout();
					layout.setObjectName(customObjectName);
					layout.setDeveloperName(layoutArray.getJSONObject(j).getString("Name"));
					LayoutList.add(layout);
				}

				Element xmlcustomfieldName = doc.createElement("name");
				xmlcustomfieldName.appendChild(doc.createTextNode("Layout"));// Make it dynamic
				xmlcustomfieldtype.appendChild(xmlcustomfieldName);
			}
		}
		return LayoutList;

	}

	public List<Permission> getPermission(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<Permission> PermissionList = new ArrayList<Permission>();
		JSONArray permissionSetArray = DataWarehouse.getPermissionSetList(loginObject, startdate, enddate,sfdcuserid);
		if (permissionSetArray != null) {
			if (permissionSetArray.length() > 0) {
				Element xmlpermissionSettype = doc.createElement("types");
				xmlroot.appendChild(xmlpermissionSettype);
				for (int i = 0; i < permissionSetArray.length(); i++) {
					try {
						Element xmlpermissionSetMembers = doc.createElement("members");
						xmlpermissionSetMembers
								.appendChild(doc.createTextNode(permissionSetArray.getJSONObject(i).getString("Name")));
						xmlpermissionSettype.appendChild(xmlpermissionSetMembers);

						Permission per = new Permission();
						per.setName(permissionSetArray.getJSONObject(i).getString("Name"));
						PermissionList.add(per);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlpermissionSetName = doc.createElement("name");
				xmlpermissionSetName.appendChild(doc.createTextNode("PermissionSet"));
				xmlpermissionSettype.appendChild(xmlpermissionSetName);
			}
		}
		return PermissionList;

	}

	public List<Profile> getProfile(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<Profile> ProfileList = new ArrayList<Profile>();
		JSONArray profileArray = DataWarehouse.getProfileList(loginObject, startdate, enddate,sfdcuserid);
		if (profileArray != null) {
			if (profileArray.length() > 0) {
				Element xmlprofiletype = doc.createElement("types");
				xmlroot.appendChild(xmlprofiletype);
				for (int i = 0; i < profileArray.length(); i++) {
					try {
						Element xmlprofileMembers = doc.createElement("members");
						xmlprofileMembers
								.appendChild(doc.createTextNode(profileArray.getJSONObject(i).getString("Name")));
						xmlprofiletype.appendChild(xmlprofileMembers);
						Profile pro = new Profile();
						pro.setName(profileArray.getJSONObject(i).getString("Name"));
						ProfileList.add(pro);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlprofileName = doc.createElement("name");
				xmlprofileName.appendChild(doc.createTextNode("Profile"));
				xmlprofiletype.appendChild(xmlprofileName);
			}
		}
		return ProfileList;

	}

	public List<RecordType> getRecordType(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<RecordType> RecordTypeList = new ArrayList<RecordType>();
		JSONArray recordTypeArray = DataWarehouse.getRecordTypeList(loginObject, startdate, enddate,sfdcuserid);
		if (recordTypeArray != null) {
			if (recordTypeArray.length() > 0) {
				Element xmlrecordTypetype = doc.createElement("types");
				xmlroot.appendChild(xmlrecordTypetype);
				for (int i = 0; i < recordTypeArray.length(); i++) {
					try {
						Element xmlrecordTypeMembers = doc.createElement("members");
						xmlrecordTypeMembers.appendChild(
								doc.createTextNode(recordTypeArray.getJSONObject(i).getString("Name") ));
						xmlrecordTypetype.appendChild(xmlrecordTypeMembers);

						RecordType rt = new RecordType();
						rt.setName(recordTypeArray.getJSONObject(i).getString("Name"));
						RecordTypeList.add(rt);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlrecordTypeName = doc.createElement("name");
				xmlrecordTypeName.appendChild(doc.createTextNode("RecordType"));
				xmlrecordTypetype.appendChild(xmlrecordTypeName);
			}
		}

		return RecordTypeList;

	}

	public List<Report> getReport(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<Report> ReportList = new ArrayList<Report>();
		JSONArray reportArray = DataWarehouse.getReportList(loginObject, startdate, enddate,sfdcuserid);
		if (reportArray != null) {
			if (reportArray.length() > 0) {
				Element xmlreporttype = doc.createElement("types");
				xmlroot.appendChild(xmlreporttype);
				for (int i = 0; i < reportArray.length(); i++) {
					try {
						Element xmlreportMembers = doc.createElement("members");
						xmlreportMembers.appendChild(
								doc.createTextNode(reportArray.getJSONObject(i).getString("DeveloperName")));
						xmlreporttype.appendChild(xmlreportMembers);
						Report report = new Report();
						report.setDeveloperName(reportArray.getJSONObject(i).getString("DeveloperName"));
						ReportList.add(report);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlreportName = doc.createElement("name");
				xmlreportName.appendChild(doc.createTextNode("Report"));
				xmlreporttype.appendChild(xmlreportName);
			}
		}
		return ReportList;

	}

	public List<StaticResources> getStaticResources(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<StaticResources> StaticResourcesList = new ArrayList<StaticResources>();
		JSONArray staticResourceArray = DataWarehouse.getStaticResourceList(loginObject, startdate, enddate,sfdcuserid);
		if (staticResourceArray != null) {
			if (staticResourceArray.length() > 0) {
				Element xmlstaticResourcetype = doc.createElement("types");
				xmlroot.appendChild(xmlstaticResourcetype);
				for (int i = 0; i < staticResourceArray.length(); i++) {
					try {
						Element xmlstaticResourceMembers = doc.createElement("members");
						xmlstaticResourceMembers.appendChild(
								doc.createTextNode(staticResourceArray.getJSONObject(i).getString("Name")));
						xmlstaticResourcetype.appendChild(xmlstaticResourceMembers);
						StaticResources sr = new StaticResources();
						sr.setName(staticResourceArray.getJSONObject(i).getString("Name"));
						StaticResourcesList.add(sr);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlstaticResourceName = doc.createElement("name");
				xmlstaticResourceName.appendChild(doc.createTextNode("StaticResource"));
				xmlstaticResourcetype.appendChild(xmlstaticResourceName);
			}
		}
		return StaticResourcesList;

	}

	public List<User> getUser(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<User> UserList = new ArrayList<User>();
		JSONArray UserArray = DataWarehouse.getUserList(loginObject, startdate, enddate,sfdcuserid);
		if (UserArray != null) {
			if (UserArray.length() > 0) {
				Element xmlUserType = doc.createElement("types");
				xmlroot.appendChild(xmlUserType);
				for (int i = 0; i < UserArray.length(); i++) {
					try {
						Element xmlUserMembers = doc.createElement("members");
						xmlUserMembers.appendChild(doc.createTextNode(UserArray.getJSONObject(i).getString("Name")));
						xmlUserType.appendChild(xmlUserMembers);
						User usr = new User();
						usr.setName(UserArray.getJSONObject(i).getString("Name"));
						UserList.add(usr);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlUserName = doc.createElement("name");
				xmlUserName.appendChild(doc.createTextNode("User"));
				xmlUserType.appendChild(xmlUserName);
			}
		}
		return UserList;

	}

	public List<ValidationRule> getValidationRule(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<ValidationRule> ValidationRuleList = new ArrayList<ValidationRule>();
		JSONArray validationRuleArray = DataWarehouse.getValidationRuleList(loginObject, startdate, enddate,sfdcuserid);
		if (validationRuleArray != null) {
			if (validationRuleArray.length() > 0) {
				Element xmlvalidationRuletype = doc.createElement("types");
				xmlroot.appendChild(xmlvalidationRuletype);
				for (int i = 0; i < validationRuleArray.length(); i++) {
					String objectname = DataWarehouse.getValidationRuleObjectName(loginObject,
							validationRuleArray.getJSONObject(i).getString("Id"));
					try {
						Element xmlvalidationRuleMembers = doc.createElement("members");
						xmlvalidationRuleMembers.appendChild(doc.createTextNode(objectname));
						xmlvalidationRuletype.appendChild(xmlvalidationRuleMembers);
						ValidationRule vr = new ValidationRule();
						vr.setName(objectname);
						ValidationRuleList.add(vr);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlvalidationRuleName = doc.createElement("name");
				xmlvalidationRuleName.appendChild(doc.createTextNode("ValidationRule"));
				xmlvalidationRuletype.appendChild(xmlvalidationRuleName);
			}
		}
		return ValidationRuleList;

	}

	public List<WebLink> getWebLink(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<WebLink> WebLinkList = new ArrayList<WebLink>();
		JSONArray WebLinArray = DataWarehouse.geWebLinkList(loginObject, startdate, enddate,sfdcuserid);
		if (WebLinArray != null) {
			if (WebLinArray.length() > 0) {
				Element xmlWebLinkType = doc.createElement("types");
				xmlroot.appendChild(xmlWebLinkType);
				for (int i = 0; i < WebLinArray.length(); i++) {
					try {
						Element xmlWebLinkMembers = doc.createElement("members");
						xmlWebLinkMembers
								.appendChild(doc.createTextNode(WebLinArray.getJSONObject(i).getString("Name")));
						xmlWebLinkType.appendChild(xmlWebLinkMembers);
						WebLink weblink = new WebLink();
						weblink.setName(WebLinArray.getJSONObject(i).getString("Name"));
						WebLinkList.add(weblink);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlWebLinkName = doc.createElement("name");
				xmlWebLinkName.appendChild(doc.createTextNode("WebLink"));
				xmlWebLinkType.appendChild(xmlWebLinkName);
			}
		}
		return WebLinkList;
	}

	public List<WorkFlowAlert> getWorkFlowAlert(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<WorkFlowAlert> WorkFlowAlertList = new ArrayList<WorkFlowAlert>();
		JSONArray objWorkFlowAlert = DataWarehouse.getWorkflowAlertList(loginObject, startdate, enddate,sfdcuserid);
		if (objWorkFlowAlert != null) {
			if (objWorkFlowAlert.length() > 0) {
				Element xmlobjWorkFlowAlerttype = doc.createElement("types");
				xmlroot.appendChild(xmlobjWorkFlowAlerttype);
				try {
					for (int k = 0; k < objWorkFlowAlert.length(); k++) {
						JSONArray jsonworkflowaler = DataWarehouse.getFullname("WorkFlowAlert",
								objWorkFlowAlert.getJSONObject(k).getString("Id"), loginObject);
						Element xmlobjworkFlowAlertMembers = doc.createElement("members");
						xmlobjworkFlowAlertMembers.appendChild(
								doc.createTextNode(jsonworkflowaler.getJSONObject(0).getString("FullName")));
						xmlobjWorkFlowAlerttype.appendChild(xmlobjworkFlowAlertMembers);
						WorkFlowAlert workflowalert = new WorkFlowAlert();
						workflowalert.setFullName(jsonworkflowaler.getJSONObject(0).getString("FullName"));
						WorkFlowAlertList.add(workflowalert);
					}
					Element xmlobjWorkFlowAlertName = doc.createElement("name");
					xmlobjWorkFlowAlertName.appendChild(doc.createTextNode("WorkFlowAlert"));
					xmlobjWorkFlowAlerttype.appendChild(xmlobjWorkFlowAlertName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return WorkFlowAlertList;
	}

	public List<WorkFlowFieldUpdate> getWorkFlowFieldUpdate(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<WorkFlowFieldUpdate> WorkFlowFieldUpdateList = new ArrayList<WorkFlowFieldUpdate>();
		JSONArray workflowFieldUpdateArray = DataWarehouse.getWorkflowFieldUpdateList(loginObject, startdate, enddate,sfdcuserid);
		if (workflowFieldUpdateArray != null) {
			if (workflowFieldUpdateArray.length() > 0) {
				Element xmlworkflowFieldUpdatetype = doc.createElement("types");
				xmlroot.appendChild(xmlworkflowFieldUpdatetype);
				for (int i = 0; i < workflowFieldUpdateArray.length(); i++) {
					try {
						Element xmlworkflowFieldUpdateMembers = doc.createElement("members");
						xmlworkflowFieldUpdateMembers.appendChild(doc.createTextNode(
								workflowFieldUpdateArray.getJSONObject(i).getString("SourceTableEnumOrId") + "."
										+ workflowFieldUpdateArray.getJSONObject(i).getString("Name")));
						xmlworkflowFieldUpdatetype.appendChild(xmlworkflowFieldUpdateMembers);
						WorkFlowFieldUpdate workFlowFieldUpdate = new WorkFlowFieldUpdate();
						workFlowFieldUpdate.setName(workflowFieldUpdateArray.getJSONObject(i).getString("Name"));
						workFlowFieldUpdate.setSourceTableEnumOrId(
								workflowFieldUpdateArray.getJSONObject(i).getString("SourceTableEnumOrId"));
						WorkFlowFieldUpdateList.add(workFlowFieldUpdate);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlworkflowFieldUpdateName = doc.createElement("name");
				xmlworkflowFieldUpdateName.appendChild(doc.createTextNode("WorkflowFieldUpdate"));
				xmlworkflowFieldUpdatetype.appendChild(xmlworkflowFieldUpdateName);
			}
		}
		return WorkFlowFieldUpdateList;
	}

	public List<WorkFlowRule> getWorkFlowRule(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<WorkFlowRule> WorkFlowRuleList = new ArrayList<WorkFlowRule>();
		JSONArray workflowRuleList = DataWarehouse.getWorkflowRuleList(loginObject, startdate, enddate,sfdcuserid);
		if (workflowRuleList != null) {
			if (workflowRuleList.length() > 0) {
				Element xmlworkflowRuletype = doc.createElement("types");
				xmlroot.appendChild(xmlworkflowRuletype);
				for (int i = 0; i < workflowRuleList.length(); i++) {
					try {
						Element xmlworkflowRuleMembers = doc.createElement("members");
						xmlworkflowRuleMembers.appendChild(
								doc.createTextNode(workflowRuleList.getJSONObject(i).getString("TableEnumOrId") + "."
										+ workflowRuleList.getJSONObject(i).getString("Name")));
						xmlworkflowRuletype.appendChild(xmlworkflowRuleMembers);
						WorkFlowRule wfr = new WorkFlowRule();
						wfr.setName(workflowRuleList.getJSONObject(i).getString("Name"));
						wfr.setTableEnumOrId(workflowRuleList.getJSONObject(i).getString("TableEnumOrId"));
						WorkFlowRuleList.add(wfr);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				Element xmlworkflowRuleName = doc.createElement("name");
				xmlworkflowRuleName.appendChild(doc.createTextNode("WorkflowRule"));
				xmlworkflowRuletype.appendChild(xmlworkflowRuleName);
			}
		}
		return WorkFlowRuleList;
	}

	public List<WorkFlowTask> getWorkFlowTask(JSONObject loginObject,String sfdcuserid,String startdate,String enddate) throws Exception {
		List<WorkFlowTask> WorkFlowTaskList = new ArrayList<WorkFlowTask>();
		JSONArray objWorkFlowTask = DataWarehouse.getWorkflowTaskList(loginObject, startdate, enddate,sfdcuserid);
		if (objWorkFlowTask != null) {
			if (objWorkFlowTask.length() > 0) {
				Element xmlobjWorkFlowTasktype = doc.createElement("types");
				xmlroot.appendChild(xmlobjWorkFlowTasktype);
				try {
					for (int k = 0; k < objWorkFlowTask.length(); k++) {
						JSONArray jsonworkflowaler = DataWarehouse.getFullname("WorkFlowTask",
								objWorkFlowTask.getJSONObject(k).getString("Id"), loginObject);
						Element xmlobjWorkFlowTaskMembers = doc.createElement("members");
						xmlobjWorkFlowTaskMembers.appendChild(
								doc.createTextNode(jsonworkflowaler.getJSONObject(0).getString("FullName")));
						xmlobjWorkFlowTasktype.appendChild(xmlobjWorkFlowTaskMembers);
						WorkFlowTask workflowtask = new WorkFlowTask();
						workflowtask.setFullName(jsonworkflowaler.getJSONObject(0).getString("FullName"));
						WorkFlowTaskList.add(workflowtask);
					}
					Element xmlobjWorkFlowTaskName = doc.createElement("name");
					xmlobjWorkFlowTaskName.appendChild(doc.createTextNode("WorkFlowTask"));
					xmlobjWorkFlowTasktype.appendChild(xmlobjWorkFlowTaskName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return WorkFlowTaskList;
	}

	public File saveXml() throws TransformerException {
		File file = XmlDocumetRes.xmlDocEnd(doc,xmlroot);
		return file;
	}
}