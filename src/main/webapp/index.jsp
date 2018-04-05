<%@page import="dataContainer.DataWarehouse"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@page import="credentials.RestLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="UTF-8">
<title>Salesforce Metadata with Heroku</title>
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
 <script src="https://cdn.rawgit.com/eligrey/FileSaver.js/e9d941381475b5df8b7d7691013401e171014e89/FileSaver.min.js">
</script>
 </head>
<body>
	<div class="container">
		<h4>Salesforce Metadata Web Application!</h4>
		<div class="row">
			<div class="col s12 m6 l6">
				<select class="browser-default" name="sfdcuserid" id="sfdcuserid">
					<option value="" disabled selected>Select User</option>
					<%
						JSONObject loginObject = RestLogin.GetLoginObject();
						JSONArray UserArray = DataWarehouse.getUserCred(loginObject);
					%>
					<option value="">--none--</option>
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
					<label> <input type="checkbox" name="metadata" value="102" />
						<span>Apex Component </span>
					</label>
				</p>
				<p>
					<label> <input type="checkbox" name="metadata" value="103" />
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
				<p>
					<label> <input type="checkbox" name="metadata" value="118" />
						<span>Flexi Page</span>
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
				<p>
					<label> <input type="checkbox" name="metadata" value="134" />
						<span>Workflow Task</span>
					</label>
				</p>
			</div>
		</div>
		<button onclick="senddata();">Send data</button>
		<button id="getdatabutton" onclick="getfinaldata();" disabled>Fetch Data</button>
	</div>
	<script type="text/javascript">
		$(document).ready(
				function() {

					var yesterday = new Date((new Date()).valueOf() - 1000 * 60
							* 60 * 24);

					$("#startdate").click(function() {

						$('.datepicker').pickadate({
							format : 'yyyy-mm-dd',
						/* disable : [ {
							from : [ 0, 0, 0 ],
							to : yesterday
						} ] */
						});
					});

					$("#enddate").click(
							function() {
								var afterday = new Date((new Date($(
										"#tour_sdate").val())).valueOf()
										- 1000 * 60 * 60 * 24);
								$('.datepicker1').pickadate({
									format : 'yyyy-mm-dd',
								/* disable : [ {
									from : [ 0, 0, 0 ],
									to : afterday
								} ] */
								});
							});
				});
		$(document).ready(function() {
			$('select').formSelect();
		});
	</script>

	<br>
	<br>
	<script type="text/javascript">
		
		function senddata() {
			var userid = document.getElementById("sfdcuserid").value;
			var sdate = document.getElementById("startdate").value;
			var edate = document.getElementById("enddate").value;
			var logintoken = [];
			var metaobj = [];
			$.each($("input[name='metadata']:checked"), function() {
				metaobj.push($(this).val());
			});
			alert(userid+" "+sdate+" "+edate+" "+metaobj);
			$.ajax({
				method : "GET",
				url : "metadataresources/sfdcmetadataPSQL/",

				data : ({
					sfdcuserid : userid,
					startdate : sdate,
					enddate : edate,
					metadata : metaobj,
					logintoken : logintoken,

				}),

				async : true,
				cache : true,
				beforeSend : function() {

				},
				complete : function(xhr, status) {
					if (status == "success")
						{
							callmeatdata(userid,sdate,edate,metaobj,logintoken);
							$("#getdatabutton").prop('disabled', false);
						}
				},
				success : function(result, status, xhr) {
					alert("db entry response - " + result + " status - " + status);

				}
			});
		}
		function callmeatdata(userid,sdate,edate,metaobj,logintoken) {
			$.ajax({
				method : "GET",
				url : "metadataresources/callheroku/",

				data : ({
					sfdcuserid : userid,
					startdate : sdate,
					enddate : edate,
					metadata : metaobj,
					logintoken : logintoken,

				}),

				async : true,
				cache : true,
				beforeSend : function() {

				},
				complete : function(xhr, status) {
					if (status == "success")
						alert("metadata response status - " + status);
				},
				success : function(result, status, xhr) {
					//alert("metadata response - " + result + " status - " + status);

				}
			});

		}
		
		function getfinaldata() {
			var userid = document.getElementById("sfdcuserid").value;
			$.ajax({
				method : "GET",
				url : "metadataresources/herokuDB/getfinaldata/",

				data : ({
					sfdcuserid : userid,
				}),

				async : false,
				cache : true,
				beforeSend : function() {

				},
				complete : function(xhr, status) {
					if (status == "success")
						{
						//	alert("metadata response - " + xhr.responseText + " status - " + status);
							var blob = new Blob([xhr.responseText],{type:"application/xml;charset=utf-8"});
							saveAs(blob,"metadata.xml");
						}
				},
				success : function(result, status, xhr) {
					//alert("metadata response - " + result + " status - " + status);

				}
			});

		}
	</script>
</body>

</html>
<!-- pradeep1jangid1 0057F000001zU1GQAU
	pradeep jangid   0057F000000by3AQAQ
  -->