package fitnesse.slim.test;
import gat.common.generator.Converter;
import java.util.Map;
import gat.common.jsonhelper.jsonservice;
import gat.http.helper.httpService;

import gat.common.dbservice.Sqlserver;


public class UserLogout {
	private String path;
	private String username;
	private String password;
	private String requestbody;
	public void setPath(String path){
		 this.path = Converter.convert(path);
	}
 

	public void setUsername(String username){
		 this.username = Converter.convert(username);
	}
 

	public void setPassword(String password){
		 this.password = Converter.convert(password);
	}
 

	public void setRequestbody(String requestbody){
		 this.requestbody = Converter.convert(requestbody);
	}
 

	public String responsebody(){
		httpService newHttp = new httpService(username, password);
		String requestBody = this.requestbody;
		String responseStr = newHttp.newHttpPost(path, requestBody);
		return responseStr.replaceAll(",\"Version\":\\d{7}","");
	}
}
