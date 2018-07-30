package gat.http.helper;

import org.apache.http.client.HttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.entity.StringEntity;
import java.util.List;
import java.util.ArrayList;

public class httpService {

	private String urlGet = "http://xxx";
	private String urlPost = "http://xxx";
	private String loginUrl = "http://xxx/api/user/login";
	private String username;
	private String password;
	private String loginResult;
	
	
	public String getLoginResult() {
		this.loginResult = httpPost(); 
		return loginResult;
	}

	public void setLoginResult(String loginResult) {
		this.loginResult = loginResult;
	}


	public httpService(String username, String password)
	{
		this.username = username;
		this.password = password;
	}	
	/*
	public static void main(String[] args) {
		String responseString = "$NULL";
    	CloseableHttpClient client = HttpClients.createDefault();
		//HttpClient client = new DefaultHttpClient();
    	
    	HttpPost httpPostTmp = new HttpPost("http://apiauto.fmauto.energymost.com/api/user/login");
	    List<NameValuePair> formParams = new ArrayList<NameValuePair>();
	    formParams.add(new BasicNameValuePair("username", "apiauto"));
	    formParams.add(new BasicNameValuePair("password", "123456Qq"));
	    try
	    {
	    UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
	    httpPostTmp.setEntity(postEntity);

	    // 执行post请求   	
	    HttpResponse httpResponseTmp = client.execute(httpPostTmp);
	    httpResponseTmp.getEntity();

	    String apiUrl = "http://apiauto.fmauto.energymost.com/api/user/delete";
	    HttpPost httpPost = new HttpPost(apiUrl); 
	    httpPost.setHeader("Accept", "application/json");
	    httpPost.setHeader("Content-Type", "application/json");
	    String requestBody1 = "{\"SpDomain\":null,\"Telephone\":\"12558325665\",\"UserType\":303262,\"SpName\":null,\"RealName\":\"deleteusera\",\"Name\":\"deleteusera\",\"FedLoginUrl\":null,\"SpStatus\":0,\"UserTypeName\":\"单项目能源经理(购买咨询服务)\",\"Status\":1,\"PrivilegeCodes\":[],\"Customers\":[],\"DemoStatus\":0,\"SpFullName\":null,\"SpId\":316,\"Version\":9744008,\"Title\":\"能源工程顾问\",\"Id\":301330,\"HasWholeCustomer\":false,\"Password\":\"53C1B0A46AA083D3588F3BDE269502FF\",\"Email\":\"dongna-emma.wang@schneider-electric.com\",\"Comment\":null}";
	    httpPost.setEntity(new StringEntity(requestBody1));      
	    // 执行post请求
	    HttpResponse httpResponse = client.execute(httpPost);
		HttpEntity entity = httpResponse.getEntity();
		if (entity != null) {
	  	      responseString = EntityUtils.toString(entity);
	  	    }
		client.close();
	    }
	    catch(Exception e)
	    {    	
	    	e.printStackTrace();
	    }
	    System.out.println(responseString);  	
	}
	*/
	private String httpPost()
    {
		String responseString = "$NULL";
    	CloseableHttpClient client = HttpClients.createDefault();
    	
    	HttpPost httpPostTmp = new HttpPost(loginUrl);
	    List<NameValuePair> formParams = new ArrayList<NameValuePair>();
	    formParams.add(new BasicNameValuePair("username", username));
	    formParams.add(new BasicNameValuePair("password", password));
	    try
	    {
	    UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
	    httpPostTmp.setEntity(postEntity);

	    // 执行post请求   	
	    HttpResponse httpResponseTmp = client.execute(httpPostTmp);
	    HttpEntity entityTmp = httpResponseTmp.getEntity();
	    if (entityTmp != null) {
		      responseString = EntityUtils.toString(entityTmp);
		    }
	    client.close();
	    }
		
    catch(Exception e)
    {    	
    	e.printStackTrace();
    }
		return responseString;		      
    }
	
	public String newHttpGet(String path)
    {
    	String responseString = "$NULL";
    	CloseableHttpClient client = HttpClients.createDefault();
    	
    	
    	HttpPost httpPostTmp = new HttpPost(loginUrl);
	    List<NameValuePair> formParams = new ArrayList<NameValuePair>();
	    formParams.add(new BasicNameValuePair("username", username));
	    formParams.add(new BasicNameValuePair("password", password));
	    try
	    {
	    UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
	    httpPostTmp.setEntity(postEntity);

	    // 执行post请求   	
	    HttpResponse httpResponseTmp = client.execute(httpPostTmp);
	    HttpEntity entityTmp = httpResponseTmp.getEntity();
	    if (entityTmp != null) {
		      responseString = EntityUtils.toString(entityTmp);
		    }
    	String apiUrl = urlGet + "/" + path;
	    HttpGet httpGet = new HttpGet(apiUrl);
		// 执行get请求
		HttpResponse httpResponse = client.execute(httpGet);
		HttpEntity entity = httpResponse.getEntity();
		
		if (entity != null) {
			responseString = EntityUtils.toString(entity);
  	    }
		client.close();
	    }
	    catch(Exception e)
	    {    	
	    	e.printStackTrace();
	    }
		return responseString;		      
    }
	
	 public String newHttpPost(String path, String requestBody)
	    {
		 	String responseString = "$NULL";
	    	CloseableHttpClient client = HttpClients.createDefault();
	    	
	    	HttpPost httpPostTmp = new HttpPost(loginUrl);
		    List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		    formParams.add(new BasicNameValuePair("username", username));
		    formParams.add(new BasicNameValuePair("password", password));
		    try
		    {
		    UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
			httpPostTmp.setEntity(postEntity);

			// 执行post请求   	
			HttpResponse httpResponseTmp = client.execute(httpPostTmp);
			httpResponseTmp.getEntity();
			
		    String apiUrl = urlPost + "/" + path;
		    HttpPost httpPost = new HttpPost(apiUrl); 
		    httpPost.setHeader("Accept", "application/json");
		    httpPost.setHeader("Content-Type", "application/json");

		    httpPost.setEntity(new StringEntity(requestBody));      
		    // 执行post请求
		    HttpResponse httpResponse = client.execute(httpPost);
			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
		  	      responseString = EntityUtils.toString(entity);
		  	    }
			client.close();
		    }
		    catch(Exception e)
		    {
		    	e.printStackTrace();
		    }
			return responseString;		      
	    }

}
