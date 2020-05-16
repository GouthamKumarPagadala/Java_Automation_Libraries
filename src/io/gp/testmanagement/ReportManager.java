package io.gp.testmanagement;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Date;

import org.apache.http.ParseException;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;
import com.thed.zephyr.cloud.rest.ZFJCloudRestClient;
import io.github.manager.RestAssuredManager;
import io.gp.utilities.ReadProperties;
import io.restassured.response.Response;

public class ReportManager {

	String FileLocation;

	public ReportManager(String FileLocation) {
		this.FileLocation = FileLocation;
	}

	public Response CreateCycleFolder(String cycleName, String cycleDescription)
			throws JSONException, IllegalStateException, URISyntaxException, IOException {
		String zephyrBaseUrl = ReadProperties.getProperties(FileLocation).getProperty("zephyrBaseUrl");
		String accessKey = ReadProperties.getProperties(FileLocation).getProperty("accessKey");
		String secretKey = ReadProperties.getProperties(FileLocation).getProperty("secretKey");
		String accountId = ReadProperties.getProperties(FileLocation).getProperty("accountId");
		String projectId = ReadProperties.getProperties(FileLocation).getProperty("projectId");
		String versionId = ReadProperties.getProperties(FileLocation).getProperty("versionId");
		ZFJCloudRestClient client = ZFJCloudRestClient.restBuilder(zephyrBaseUrl, accessKey, secretKey, accountId)
				.build();
		String createCycleUri = zephyrBaseUrl + "/public/rest/api/1.0/cycle";

		JSONObject createCycleObj = new JSONObject();
		createCycleObj.put("name", cycleName);
		createCycleObj.put("description", cycleDescription);
		createCycleObj.put("projectId", projectId);
		createCycleObj.put("versionId", versionId);

		StringEntity cycleJSON = null;
		try {
			cycleJSON = new StringEntity(createCycleObj.toString());

		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		// HttpManager HTTP = new HttpManager();
		// String cycleID = HTTP.Post(createCycleUri, client, accessKey, cycleJSON);
		// System.out.println("Cycle Created with Cycle Id :" + cycleID);

		RestAssuredManager rest = new RestAssuredManager();
		return rest.POST(createCycleUri, client, accessKey, cycleJSON);

	}

	public Response AddTestsToFolder(String TestKey)
			throws JSONException, IllegalStateException, URISyntaxException, IOException {
		String zephyrBaseUrl = ReadProperties.getProperties(FileLocation).getProperty("zephyrBaseUrl");
		String accessKey = ReadProperties.getProperties(FileLocation).getProperty("accessKey");
		String secretKey = ReadProperties.getProperties(FileLocation).getProperty("secretKey");
		String accountId = ReadProperties.getProperties(FileLocation).getProperty("accountId");
		String projectId = ReadProperties.getProperties(FileLocation).getProperty("projectId");
		String versionId = ReadProperties.getProperties(FileLocation).getProperty("versionId");
		String cycleId = ReadProperties.getProperties(FileLocation).getProperty("cycleId");
		String folderId = ReadProperties.getProperties(FileLocation).getProperty("folderId");

		String addTestsUri = zephyrBaseUrl + "/public/rest/api/1.0/executions/add/folder/" + folderId;

		ZFJCloudRestClient client = ZFJCloudRestClient.restBuilder(zephyrBaseUrl, accessKey, secretKey, accountId)
				.build();

		String[] issueIds = { TestKey };

		JSONObject addTestsObj = new JSONObject();
		addTestsObj.put("issues", issueIds);
		addTestsObj.put("method", "1");
		addTestsObj.put("projectId", projectId);
		addTestsObj.put("versionId", versionId);
		addTestsObj.put("cycleId", cycleId);

		StringEntity addTestsJSON = null;
		try {
			addTestsJSON = new StringEntity(addTestsObj.toString());
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		RestAssuredManager rest = new RestAssuredManager();
		return rest.POST(addTestsUri, client, accessKey, addTestsJSON);


	}

	public Response AddTests_ToCycle(String TestKey)
			throws JSONException, IllegalStateException, URISyntaxException, IOException {
		String zephyrBaseUrl = ReadProperties.getProperties(FileLocation).getProperty("zephyrBaseUrl");
		String accessKey = ReadProperties.getProperties(FileLocation).getProperty("accessKey");
		String secretKey = ReadProperties.getProperties(FileLocation).getProperty("secretKey");
		String accountId = ReadProperties.getProperties(FileLocation).getProperty("accountId");
		String projectId = ReadProperties.getProperties(FileLocation).getProperty("projectId");
		String versionId = ReadProperties.getProperties(FileLocation).getProperty("versionId");
		String cycleId = ReadProperties.getProperties(FileLocation).getProperty("cycleId");
		String addTestsUri = zephyrBaseUrl + "/public/rest/api/1.0/executions/add/cycle/" + cycleId;
		ZFJCloudRestClient client = ZFJCloudRestClient.restBuilder(zephyrBaseUrl, accessKey, secretKey, accountId)
				.build();

		String[] issueIds = { TestKey };

		JSONObject addTestsObj = new JSONObject();
		addTestsObj.put("issues", issueIds);
		addTestsObj.put("method", "1");
		addTestsObj.put("projectId", projectId);
		addTestsObj.put("versionId", versionId);
		addTestsObj.put("cycleId", cycleId);

		StringEntity addTestsJSON = null;
		try {
			addTestsJSON = new StringEntity(addTestsObj.toString());
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		RestAssuredManager rest = new RestAssuredManager();
		return rest.POST(addTestsUri, client, accessKey, addTestsJSON);


	}

	public Response GetExecutionId_ByFolderName(String IssueId)
			throws JSONException, IllegalStateException, URISyntaxException, IOException {
		String zephyrBaseUrl = ReadProperties.getProperties(FileLocation).getProperty("zephyrBaseUrl");
		String accessKey = ReadProperties.getProperties(FileLocation).getProperty("accessKey");
		String secretKey = ReadProperties.getProperties(FileLocation).getProperty("secretKey");
		String accountId = ReadProperties.getProperties(FileLocation).getProperty("accountId");
		String projectId = ReadProperties.getProperties(FileLocation).getProperty("projectId");
		String addTestsUri = zephyrBaseUrl + "/public/rest/api/1.0/executions?issueId=" + IssueId + "&projectId="
				+ projectId + "";
		ZFJCloudRestClient client = ZFJCloudRestClient.restBuilder(zephyrBaseUrl, accessKey, secretKey, accountId)
				.build();

		RestAssuredManager rest = new RestAssuredManager();
		return rest.GET(addTestsUri, client, accessKey);


	}

	public Response CreateExecution(String testKeyId)
			throws JSONException, IllegalStateException, URISyntaxException, IOException {
		String zephyrBaseUrl = ReadProperties.getProperties(FileLocation).getProperty("zephyrBaseUrl");
		String accessKey = ReadProperties.getProperties(FileLocation).getProperty("accessKey");
		String secretKey = ReadProperties.getProperties(FileLocation).getProperty("secretKey");
		String accountId = ReadProperties.getProperties(FileLocation).getProperty("accountId");
		String projectId = ReadProperties.getProperties(FileLocation).getProperty("projectId");
		String versionId = ReadProperties.getProperties(FileLocation).getProperty("versionId");
		String cycleId = ReadProperties.getProperties(FileLocation).getProperty("cycleId");
		String addTestsUri = zephyrBaseUrl + "/public/rest/api/1.0/execution";

		ZFJCloudRestClient client = ZFJCloudRestClient.restBuilder(zephyrBaseUrl, accessKey, secretKey, accountId)
				.build();

		JSONObject addTestsObj = new JSONObject();

		JSONObject status = new JSONObject();
		status.put("id", 1);

		addTestsObj.put("status", status);
		addTestsObj.put("projectId", projectId);
		addTestsObj.put("issueId", testKeyId);

		addTestsObj.put("cycleId", cycleId);
		addTestsObj.put("versionId", versionId);

		StringEntity addTestsJSON = null;
		try {
			addTestsJSON = new StringEntity(addTestsObj.toString());

		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		RestAssuredManager rest = new RestAssuredManager();
		return rest.POST(addTestsUri, client, accessKey, addTestsJSON);

	}

	public Response UpdateExecutionStatus(String id, String IssueId, String Status)
			throws JSONException, IllegalStateException, URISyntaxException, IOException {
		String zephyrBaseUrl = ReadProperties.getProperties(FileLocation).getProperty("zephyrBaseUrl");
		String accessKey = ReadProperties.getProperties(FileLocation).getProperty("accessKey");
		String secretKey = ReadProperties.getProperties(FileLocation).getProperty("secretKey");
		String accountId = ReadProperties.getProperties(FileLocation).getProperty("accountId");
		String projectId = ReadProperties.getProperties(FileLocation).getProperty("projectId");
		String versionId = ReadProperties.getProperties(FileLocation).getProperty("versionId");
		String cycleId = ReadProperties.getProperties(FileLocation).getProperty("cycleId");
		String addTestsUri = zephyrBaseUrl + "/public/rest/api/1.0/execution/" + id;

		ZFJCloudRestClient client = ZFJCloudRestClient.restBuilder(zephyrBaseUrl, accessKey, secretKey, accountId)
				.build();

		// String[] issueIds = { "DEF-1658"}; // Issue Id's to be added to Test Cycle,
		// add more issueKeys separated

		JSONObject addTestsObj = new JSONObject();
		JSONObject status = new JSONObject();
		if (Status.contentEquals("PASS")) {

			status.put("id", 1);
		} else if (Status.contentEquals("FAIL")) {

			status.put("id", 2);
		}

		addTestsObj.put("status", status);
		addTestsObj.put("projectId", projectId);
		addTestsObj.put("issueId", IssueId);
		addTestsObj.put("id", id);
		addTestsObj.put("cycleId", cycleId);
		addTestsObj.put("versionId", versionId);
		addTestsObj.put("comment", "Updated by Automation");

		StringEntity addTestsJSON = null;
		try {
			addTestsJSON = new StringEntity(addTestsObj.toString());
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		System.out.println(addTestsObj.toString());
		RestAssuredManager rest = new RestAssuredManager();
		return rest.PUT(addTestsUri, client, accessKey, addTestsJSON);

	}

	public void UpdateStatus(String TestKey, String TestKeyId,String Status,String fileLocation) throws JSONException, IllegalStateException, URISyntaxException, IOException {
		ReportManager Report = new ReportManager(System.getProperty("user.dir") + "/zapi.properties");
		// Report.CreateCycleFolder("Sample Automation", "This folder has Automation
        Report.AddTests_ToCycle(TestKey);
		// Report.CreateCycleFolder("Sample RestAssured", "Description");
		// Report.AddTestsToFolder("DEF-1658");
		Response result=Report.CreateExecution(TestKeyId);
		String id = result.getBody().jsonPath().getString("execution.id").toString();
		Report.UpdateExecutionStatus(id,TestKeyId,Status);
		Report.UploadAttachment(id, TestKeyId, fileLocation);
		
	}

	public Response UploadAttachment(String id,String IssueId,String FilePath) throws ParseException, URISyntaxException, IOException {

		String zephyrBaseUrl = ReadProperties.getProperties(FileLocation).getProperty("zephyrBaseUrl");
		String accessKey = ReadProperties.getProperties(FileLocation).getProperty("accessKey");
		String secretKey = ReadProperties.getProperties(FileLocation).getProperty("secretKey");
		String accountId = ReadProperties.getProperties(FileLocation).getProperty("accountId");
		String projectId = ReadProperties.getProperties(FileLocation).getProperty("projectId");
		String versionId = ReadProperties.getProperties(FileLocation).getProperty("versionId");
		String cycleId = ReadProperties.getProperties(FileLocation).getProperty("cycleId");
		String entityName = "execution";
		String comment = "Test_Automation_Uploaded_File_on_"+new Date().toString().replace(" ", "_").replace(":", "");
		String addTestsUri = zephyrBaseUrl + "/public/rest/api/1.0/attachment?issueId="+IssueId+"&versionId="+versionId+"&entityName="+entityName+"&cycleId="+cycleId+"&entityId="+id+"&comment="+comment+"&projectId="+projectId;

		ZFJCloudRestClient client = ZFJCloudRestClient.restBuilder(zephyrBaseUrl, accessKey, secretKey, accountId)
				.build();
		
		RestAssuredManager rest = new RestAssuredManager();
		return rest.MultiPartPOST(addTestsUri, client, accessKey, FilePath);

	}

	
}
