<%@page import="org.apache.commons.codec.binary.Base64"%>
<%@page import="dataContainer.DataWarehouse"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@page import="credentials.RestLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Salesforce Metadata</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0,user-scalable=no" />
<link href="http://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/css/materialize.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/js/materialize.min.js"></script>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<link type="text/css" rel="stylesheet"
	href="materialize/css/materialize.min.css" media="screen,projection" />
<script type="text/javascript" src="materialize/js/materialize.min.js"></script>
<script
	src="https://cdn.rawgit.com/eligrey/FileSaver.js/e9d941381475b5df8b7d7691013401e171014e89/FileSaver.min.js">
	
</script>
<link type="text/css" rel="stylesheet" href="materialize/css/custom.css"
	media="screen,projection" />

</head>
<body>
	<div class="container" id="noscript">
		<h4>Salesforce Metadata Application!</h4>
		<div class="row">
			<div class="input-field col s12 m6 l6" style="margin-bottom: -30px;">
				<select name="sfdcuserid" id="sfdcuserid" required>

					<%
						JSONObject loginObject = RestLogin.GetLoginObject();
					/* JSONObject loginObject = new JSONObject();
					loginObject.put("instance_url", "https://playful-raccoon-370173-dev-ed.my.salesforce.com");
					byte [] decode = Base64.decodeBase64("MEQxTjAwMDAwMWhkQ00hQVEwQVFENV9wQlMxbkdGTFR3UDJuX19jbTcxeHdIWlpFWUFJcmlXRmhfM2RUTmJSWlBuQW4yN0Y3UGU4WHhpa0dYWmxTdXlSVUY4YXIwNHp3VkpMS1JoWlZxWDJzSktp");
					String access_token = new String (decode);
					System.out.println("decode  - "+access_token);
					loginObject.put("access_token", access_token);
					 */
					JSONArray UserArray = DataWarehouse.getUserCred(loginObject);
					%>
					<option value="##info.mca">---All Users---</option>
					<%
						for (int i = 0; i < UserArray.length(); i++) {
					%>
					<option
						value="<%=UserArray.getJSONObject(i).getString("Id")%>##<%=UserArray.getJSONObject(i).getString("Username")%>"><%=UserArray.getJSONObject(i).getString("Name")%></option>
					<%
						}
					%>

				</select>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12 m12 l6">
				<label for="startdate">Start Date</label> <input id="startdate"
					type="text" class="datepicker" name="startdate" required>
			</div>
			<div class="input-field col s12 m12 l6">
				<label for="enddate">End Date</label> <input id="enddate"
					type="text" class="datepicker1" name="enddate" required>
			</div>

		</div>
		<div class="row">
			<div class="col s12 m4 l3">
				<p>
					<label> <input type="checkbox" name="metadata" value="101" />
						<span>Apex Class</span>
					</label>
				</p>
				<p>
					<label> <input type="checkbox" name="metadata" value="103" />
						<span>Apex Component </span>
					</label>
				</p>
				<p>
					<label> <input type="checkbox" name="metadata" value="102" />
						<span>Apex Page</span>
					</label>
				</p>
				<p>
					<label> <input type="checkbox" name="metadata" value="104" />
						<span>Apex Trigger</span>
					</label>
				</p>
			</div>
			<div class="col s12 m4 l3">
				<p>
					<label> <input type="checkbox" name="metadata" value="105" />
						<span>Assignment Rule</span>
					</label>
				</p>
				<p>
					<label> <input type="checkbox" name="metadata" value="106" />
						<span>AuraDefinition Rule</span>
					</label>
				</p>
				<p>
					<label> <input type="checkbox" name="metadata" value="107" />
						<span>Auto Response</span>
					</label>
				</p>
				<p>
					<label> <input type="checkbox" name="metadata" value="108" />
						<span>Business Process</span>
					</label>
				</p>
			</div>
			<div class="col s12 m4 l3">

				<p>
					<label> <input type="checkbox" name="metadata" value="109" />
						<span>Compact Layout</span>
					</label>
				</p>
				<p>
					<label> <input type="checkbox" name="metadata" value="110" />
						<span>Connected Application</span>
					</label>
				</p>
				<p>
					<label> <input type="checkbox" name="metadata" value="111" />
						<span>Custom Application</span>
					</label>
				</p>
				<p>
					<label> <input type="checkbox" name="metadata" value="112" />
						<span>Custom Filed</span>
					</label>
				</p>
			</div>
			<div class="col s12 m4 l3">

				<p>
					<label> <input type="checkbox" name="metadata" value="113" />
						<span>Custom Object</span>
					</label>
				</p>
				<p>
					<label> <input type="checkbox" name="metadata" value="114" />
						<span>Custom Tab</span>
					</label>
				</p>
				<p>
					<label> <input type="checkbox" name="metadata" value="115" />
						<span>Dashboard</span>
					</label>
				</p>
				<p>
					<label> <input type="checkbox" name="metadata" value="116" />
						<span>Email Template</span>
					</label>
				</p>
			</div>
			<div class="col s12 m4 l3">

				<p>
					<label> <input type="checkbox" name="metadata" value="117" />
						<span>Field Set</span>
					</label>
				</p>
				<!-- <p>
					<label> <input type="checkbox" name="metadata" value="118" />
						<span>Flexi Page</span>
					</label>
				</p> -->
				<p>
					<label> <input type="checkbox" name="metadata" value="134" />
						<span>Workflow Task</span>
					</label>
				</p>
				<p>
					<label> <input type="checkbox" name="metadata" value="119" />
						<span>Flow</span>
					</label>
				</p>
				<p>
					<label> <input type="checkbox" name="metadata" value="120" />
						<span>Global Value Set</span>
					</label>
				</p>
			</div>
			<div class="col s12 m4 l3">

				<p>
					<label> <input type="checkbox" name="metadata" value="121" />
						<span>Home Page Layout</span>
					</label>
				</p>
				<p>
					<label> <input type="checkbox" name="metadata" value="122" />
						<span>Layout</span>
					</label>
				</p>
				<p>
					<label> <input type="checkbox" name="metadata" value="123" />
						<span>Permission</span>
					</label>
				</p>
				<p>
					<label> <input type="checkbox" name="metadata" value="124" />
						<span>Profile</span>
					</label>
				</p>
			</div>
			<div class="col s12 m4 l3">

				<p>
					<label> <input type="checkbox" name="metadata" value="125" />
						<span>Record Type</span>
					</label>
				</p>
				<p>
					<label> <input type="checkbox" name="metadata" value="126" />
						<span>Report</span>
					</label>
				</p>
				<p>
					<label> <input type="checkbox" name="metadata" value="127" />
						<span>Static Resources</span>
					</label>
				</p>
				<p>
					<label> <input type="checkbox" name="metadata" value="128" />
						<span>User</span>
					</label>
				</p>
			</div>
			<div class="col s12 m4 l3">

				<p>
					<label> <input type="checkbox" name="metadata" value="129" />
						<span>Validation Rule</span>
					</label>
				</p>
				<p>
					<label> <input type="checkbox" name="metadata" value="130" />
						<span>WebLink</span>
					</label>
				</p>
				<p>
					<label> <input type="checkbox" name="metadata" value="131" />
						<span>WorkFlowAlert</span>
					</label>
				</p>
				<p>
					<label> <input type="checkbox" name="metadata" value="132" />
						<span>WorkFlowFieldUpdate</span>
					</label>
				</p>
			</div>
			<div class="col s12 m4 l3">

				<p>
					<label> <input type="checkbox" name="metadata" value="133" />
						<span>WorkflowRulle</span>
					</label>
				</p>

			</div>
		</div>
		<div class="row">
			<button class="btn waves-effect red lighten-2" onclick="senddata();"
				id="senddatabutton">
				Fetch Metadata <i class="material-icons right">send</i>
			</button>
			&nbsp;&nbsp;
			<div style="display: none" class="my-pre-loader" id="loader"
				title="Processing metadata"></div>
			<div class="red-text" id="procmsg">Processing Metadata</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#startdate").click(function() {
				$('.datepicker').pickadate({
					today : 'Today',
					clear : 'Clear',
					close : 'Ok',
					closeOnSelect : false,
					format : 'yyyy-mm-dd',
					max : new Date(),
				});
				//	$('.datepicker1').pickadate(clear);
			});

			$("#enddate").click(function() {
				$('.datepicker1').pickadate({
					today : 'Today',
					clear : 'Clear',
					close : 'Ok',
					closeOnSelect : false,
					format : 'yyyy-mm-dd',
					//min : new Date($("#startdate").val()),
					max : new Date(),
				});
			});
		});
		$(document).ready(function() {
			$('select').material_select();
		});
	</script>

	<br>
	<br>
	<script type="text/javascript">
		function senddata() {
			window.temptimeout;
			var userid = document.getElementById("sfdcuserid").value;
			var sdate = document.getElementById("startdate").value;
			var edate = document.getElementById("enddate").value;
			var logintoken = [];
			var metaobj = [];
			$.each($("input[name='metadata']:checked"), function() {
				metaobj.push($(this).val());
			});
			if (sdate.length == 0 || edate.length == 0) {
				alert("Enter valid Date");
				return;
			}
			if (metaobj.length === 0) {
				alert("Select atleast one Object");
				return;
			}
			//window.proxy = 'https://cors-anywhere.herokuapp.com/';
			$
					.ajax({
						method : "POST",
						url : "metadataresources/sfdcmetadataPSQL/",
						data : (
							JSON.stringify(userid)
						),
						traditional: true,
						dataType : 'json',
						contentType: 'application/json',
						async : true,
						cache : true,
						beforeSend : function() {
							document.getElementById("loader").style.display = "inline-block";
							document.getElementById("procmsg").style.display = "inline-block";
							$("#senddatabutton").prop('disabled', true);
						},
						complete : function(xhr, status) {
							if (parseInt(xhr.responseText) === 200) {
								temptimeout = setInterval(function() {
									getfinaldata(userid)
								}, 10000);
								callmeatdata(userid, sdate, edate, metaobj,logintoken);
							} else if (parseInt(xhr.responseText) === 422) { // remove it after testing
								document.getElementById("loader").style.display = "none";
								document.getElementById("procmsg").style.display = "none";
								$("#senddatabutton").prop('disabled', false);
								alert("Invalid entry try again");
							} else if (parseInt(xhr.responseText) === 400) {
								document.getElementById("loader").style.display = "none";
								document.getElementById("procmsg").style.display = "none";
								$("#senddatabutton").prop('disabled', false);
								//alert("Something going wrong");
							}
						},
						success : function(result, status, xhr) {
						}
					});
		}
		function callmeatdata(userid, sdate, edate, metaobj, logintoken) {
			 var log = [];
             log.push('https://playful-raccoon-370173-dev-ed.my.salesforce.com');
             log.push('MEQxTjAwMDAwMWhkQ00hQVEwQVFENV9wQlMxbkdGTFR3UDJuX19jbTcxeHdIWlpFWUFJcmlXRmhfM2RUTmJSWlBuQW4yN0Y3UGU4WHhpa0dYWmxTdXlSVUY4YXIwNHp3VkpMS1JoWlZxWDJzSktp');
			$
					.ajax({
						method : "POST",
						url : "metadataresources/callheroku/",
						data : (JSON.stringify({
							sfdcuserid : userid,
							startdate : sdate,
							enddate : edate,
							metadata : metaobj,
							logintoken : log})
						),
						traditional: true,
						dataType : 'json',
						contentType: 'application/json',
						async : true,
						cache : true,
						beforeSend : function() {

						},
						complete : function(xhr, status) {
							if (parseInt(xhr.responseText) === 204) {
								document.getElementById("loader").style.display = "none";
								document.getElementById("procmsg").style.display = "none";
								$("#senddatabutton").prop('disabled', false);
								alert("Metadata not found");
								clearInterval(temptimeout);
							}
						},
						success : function(result, status, xhr) {
						}
					});
		}

		function getfinaldata(userid) {
			$
					.ajax({
						method : "GET",
						url :"metadataresources/herokuDB/getfinaldata/",
						data : ({
							sfdcuserid : userid,
						}),
						async : false,
						cache : true,
						beforeSend : function() {
						},
						complete : function(xhr, status) {
							if (parseInt(xhr.responseText) != 204) {
								document.getElementById("loader").style.display = "none";
								document.getElementById("procmsg").style.display = "none";
								var blob = new Blob([ xhr.responseText ], {
									type : "application/xml;charset=utf-8"
								});
								saveAs(blob, "metadata.xml");
								$("#senddatabutton").prop('disabled', false);
								clearInterval(temptimeout);
							}
						},
						success : function(result, status, xhr) {
						}
					});
		}
	</script>
</body>

</html>
<!-- pradeep1jangid1 0057F000001zU1GQAU
	pradeep jangid   0057F000000by3AQAQ
  -->