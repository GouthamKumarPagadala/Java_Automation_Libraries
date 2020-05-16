package io.github.manager;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


import org.apache.http.ParseException;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import com.thed.zephyr.cloud.rest.ZFJCloudRestClient;
import com.thed.zephyr.cloud.rest.client.JwtGenerator;
import io.restassured.response.Response;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RestAssuredManager {
	public Response POST(String uriStr, ZFJCloudRestClient client, String accessKey, StringEntity cycleJSON)
			throws URISyntaxException, ParseException, IOException {

		URI uri = new URI(uriStr);
		int expirationInSec = 360;
		JwtGenerator jwtGenerator = client.getJwtGenerator();
		String jwt = jwtGenerator.generateJWT("POST", uri, expirationInSec);
		System.out.println(uri.toString());
		System.out.println(jwt);

		Response response = given().header("Content-Type", "application/json").header("Authorization", jwt)
				.header("zapiAccessKey", accessKey).body(EntityUtils.toString(cycleJSON)).post(uri);

		System.out.println(response.asString());
		return response;

	}
	public Response GET(String uriStr, ZFJCloudRestClient client, String accessKey)
			throws URISyntaxException, ParseException, IOException {

		URI uri = new URI(uriStr);
		int expirationInSec = 360;
		JwtGenerator jwtGenerator = client.getJwtGenerator();
		String jwt = jwtGenerator.generateJWT("POST", uri, expirationInSec);
		System.out.println(uri.toString());
		System.out.println(jwt);

		Response response = given().header("Content-Type", "application/json").header("Authorization", jwt)
				.header("zapiAccessKey", accessKey).get(uri);

		System.out.println(response.asString());
		return response;

	}
	
	public Response MultiPartPOST(String uriStr, ZFJCloudRestClient client, String accessKey, String filePath)
			throws URISyntaxException, ParseException, IOException {
		Response response1=null;
		URI uri = new URI(uriStr);
		int expirationInSec = 360;
		JwtGenerator jwtGenerator = client.getJwtGenerator();
		String jwt = jwtGenerator.generateJWT("POST", uri, expirationInSec);
		System.out.println(uri.toString());
		System.out.println(jwt);

		OkHttpClient client1 = new OkHttpClient().newBuilder()
				  .build();
				RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
				  .addFormDataPart("file",filePath,
				    RequestBody.create(MediaType.parse("application/octet-stream"),
				    new File(filePath)))
				  .build();
				Request request = new Request.Builder()
				  .url(uriStr)
				  .method("POST", body)
				  .addHeader("zapiAccessKey", accessKey)
				  .addHeader("Authorization", jwt)
				  .build();
				okhttp3.Response response = client1.newCall(request).execute();
		System.out.println(response);
		return response1;

	}

	public Response PUT(String uriStr, ZFJCloudRestClient client, String accessKey, StringEntity cycleJSON)
			throws URISyntaxException, ParseException, IOException {

		URI uri = new URI(uriStr);
		int expirationInSec = 360;
		JwtGenerator jwtGenerator = client.getJwtGenerator();
		String jwt = jwtGenerator.generateJWT("PUT", uri, expirationInSec);
		System.out.println(uri.toString());
		System.out.println(jwt);

		Response response = given().header("Content-Type", "application/json").header("Authorization", jwt)
				.header("zapiAccessKey", accessKey).body(EntityUtils.toString(cycleJSON)).put(uri);

		System.out.println(response.asString());
		return response;

	}

}
