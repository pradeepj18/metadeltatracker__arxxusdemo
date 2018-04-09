	package credentials;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

import org.apache.http.util.EntityUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class RestLogin {
	
	/* 
	 System.debug('instance url '+Url.getSalesforceBaseUrl().toExternalForm());
String orgid=(UserInfo.getOrganizationId()).SubString(0,15);
String access=UserInfo.getSessionId().SubString(15);
System.debug(orgid+'--'+access);
 */
	public static JSONObject GetLoginObject() {
		 final String username = "info.mca2016@gmail.com";
		final String password = "info.salesforce19929QMgm89HYa6ExqzuoGy1YDZq";
		final String loginurl = "https://login.salesforce.com";
		final String grantservice = "/services/oauth2/token?grant_type=password";
		final String cleienid = "3MVG9d8..z.hDcPLMwnhCIndM32aS.bjeX5yn0UsgvjXVxQu0KSkLUj3nVdcbKl8tMWk6Fs.Bz9HxeVk3wm6j";
		final String clientsecret = "8119906263693749634";

		HttpClient httpclient = HttpClientBuilder.create().build();
		
		
		String loginURL = loginurl + grantservice + "&client_id=" + cleienid + "&client_secret=" + clientsecret
				+ "&username=" + username + "&password=" + password;
		HttpPost httpPost = new HttpPost(loginURL);
		
		HttpResponse response = null;
		try {
			response = httpclient.execute(httpPost);
		} catch (ClientProtocolException cpException) {
			cpException.printStackTrace();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		final int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != HttpStatus.SC_OK) {
			System.out.println("Error authenticating to Force.com: " + response.getStatusLine());
			return null;
		}

		String getResult = null;
		try {
			getResult = EntityUtils.toString(response.getEntity());
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		JSONObject jsonObject = null;
		
		try {
			jsonObject = (JSONObject) new JSONTokener(getResult).nextValue();
		} catch (JSONException jsonException) {
			jsonException.printStackTrace();
		}
		httpPost.releaseConnection();
		return jsonObject;
	}

}
