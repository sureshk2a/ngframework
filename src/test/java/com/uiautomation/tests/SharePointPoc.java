package com.uiautomation.tests;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.core5.http.io.entity.FileEntity;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import com.uiautomation.constants.FrameworkConstants;

public class SharePointPoc {

	@SuppressWarnings({ "resource", "deprecation" })
	private static void executeRequest(HttpPost httpPost) {
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			HttpResponse response = (HttpResponse) client.execute((HttpUriRequest) httpPost);
			// System.out.println("Response Code: " +
			// response.getStatusLine().getStatusCode());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getSharepointToken() throws IOException {
		/**
		 * This function helps to get SharePoint Access Token. SharePoint Access Token
		 * is required to authenticate SharePoint REST service while performing
		 * Read/Write events. SharePoint REST-URL to get access token is as:
		 * https://accounts.accesscontrol.windows.net/ /tokens/OAuth/2
		 *
		 * Input required related to SharePoint are as: 1. shp_clientId 2. shp_tenantId
		 * 3. shp_clientSecret
		 */
		String accessToken = "";
		try {

			// AccessToken url

			String wsURL = "https://accounts.accesscontrol.windows.net/92e84ceb-fbfd-47ab-be52-080c6b87953f/tokens/OAuth/2";

			URL url = new URL(wsURL);
			URLConnection connection = url.openConnection();
			HttpURLConnection httpConn = (HttpURLConnection) connection;

			// Set header
			httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			httpConn.setRequestMethod("POST");

			String jsonParam = "grant_type=client_credentials" + "&client_id=<shp_clientId>@<shp_tenandId>"
					+ "&client_secret=<shp_clientSecret>"
					+ "&resource=00000003-0000-0ff1-ce00-000000000000/<your organization here>.sharepoint.com@<shp_tenantId>";

			// Send Request
			DataOutputStream wr = new DataOutputStream(httpConn.getOutputStream());
			wr.writeBytes(jsonParam);
			wr.flush();
			wr.close();

			// Read the response.
			InputStreamReader isr = null;
			if (httpConn.getResponseCode() == 200) {
				isr = new InputStreamReader(httpConn.getInputStream());
			} else {
				isr = new InputStreamReader(httpConn.getErrorStream());
			}

			BufferedReader in = new BufferedReader(isr);
			String responseString = "";
			String outputString = "";

			// Write response to a String.
			while ((responseString = in.readLine()) != null) {
				outputString = outputString + responseString;
			}
			// Extracting accessToken from string, here response
			// (outputString)is a Json format string
			if (outputString.indexOf("access_token\":\"") > -1) {
				int i1 = outputString.indexOf("access_token\":\"");
				String str1 = outputString.substring(i1 + 15);
				int i2 = str1.indexOf("\"}");
				String str2 = str1.substring(0, i2);
				accessToken = str2;
				// System.out.println("accessToken.........." + accessToken);
			}
		} catch (Exception e) {
			accessToken = "Error: " + e.getMessage();
		}
		return accessToken;
	}

	private static HttpPost addHeader(HttpPost httpPost) throws IOException {
		String accessTokenInt = getSharepointToken();
		httpPost.addHeader("Accept", "application/json;odata=verbose");
		httpPost.setHeader("Authorization", "Bearer " + accessTokenInt);
		// System.out.println("httpPost…….." + httpPost);
		return httpPost;
	}

	public static void executeMultiPartRequest(String urlString, File file) throws IOException {
		HttpPost postRequest = new HttpPost(urlString);
		postRequest = addHeader(postRequest);
		try {
			postRequest.setEntity(new FileEntity(file, null));
			// System.out.println("urlString :"+urlString);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		executeRequest(postRequest);
	}

	public static void main(String[] args) {

		try {
			String accessTokenInt = getSharepointToken();
			String siteURL = "https://gotobo.sharepoint.com/sites/";
			// e.g. gotobo.sharepoint.com/sites/mysite
			String folderUrl = "Shared%20Documents" + "/test"; // ===============================================//
			
			// Sharepoint API to create Path
			// ===============================================//
			String url1w = siteURL + "/_api/web/GetFolderByServerRelativeUrl('" + folderUrl + "')";
			System.out.println("The complete path is : " + url1w);
			URL urlcw = new URL(url1w);
			URLConnection con1 = urlcw.openConnection();
			HttpURLConnection httpCon1 = (HttpURLConnection) con1;
			httpCon1.setDoOutput(true);
			httpCon1.setDoInput(true);
			httpCon1.setRequestMethod("POST");
			httpCon1.setRequestProperty("Authorization", "Bearer " + accessTokenInt);
			DataOutputStream wr1 = new DataOutputStream(httpCon1.getOutputStream());
			wr1.flush();
			wr1.close(); // Read the response.
			String respStr1 = "";
			if (httpCon1.getResponseCode() == 200) {
				respStr1 = "Path has been found/created successfully. ResponseCode : " + httpCon1.getResponseCode();
			} else {
				respStr1 += "Error while writing file, ResponseCode : " + httpCon1.getResponseCode() + " "
						+ httpCon1.getResponseMessage();
			}
			System.out.println(respStr1);
			
			
			// ===============================================//
			// Sharepoint API to upload file to Path
			// ===============================================//
			
			File filePath = null;
			try {
				filePath = new File(FrameworkConstants.getExtentReportPath());
			} catch (Exception nre) {
				System.out.println("User Cancelled the operation");
			}
			
			String filename = filePath.getName();
			filename = filename.replaceAll("\\", "%20");
			System.out.println(filename + " Selected for upload ");
			String uploadlink = "";
			uploadlink = siteURL + "/_api/web/GetFolderByServerRelativeUrl(\'" + folderUrl + "/";
			uploadlink = uploadlink + "\')/Files/add(url='" + filename + "',overwrite=true)";
			executeMultiPartRequest(uploadlink, filePath);
			System.out.println("File uploaded : " + uploadlink);
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("The file could not be uploaded OR Token error");
		}

	}

}
