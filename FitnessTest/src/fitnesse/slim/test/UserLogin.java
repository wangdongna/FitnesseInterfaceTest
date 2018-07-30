package fitnesse.slim.test;
import gat.common.generator.Converter;
import gat.http.helper.httpService;


public class UserLogin {
	private String username;
	private String password;

	public void setUsername(String username){
		 this.username = Converter.convert(username);
	}
 

	public void setPassword(String password){
		 this.password = Converter.convert(password);
	}
 

	public String responsebody(){
		httpService newHttp = new httpService(username, password);
		String responseStr = newHttp.getLoginResult();
		return responseStr;
	}
}
