package gat.common.jsonhelper;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.type.TypeFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import gat.entity.user.User;

import java.io.IOException;  
import java.io.StringWriter;
import java.io.Writer;
import java.text.ParseException;  
import java.util.List;
import java.util.Map;


public class jsonservice {
	

public static void main(String[] args) throws ParseException, IOException{
		
		User user = new User();
		@SuppressWarnings("unused")
		ObjectMapper mapper = new ObjectMapper();
		String json = "{\"Error\":\"0\",\"Message\":[\"\"],\"RequestId\":null,\"Result\":{\"Id\":301236,\"RealName\":\"StableUser\",\"UserType\":301985,\"UserTypeName\":\"�ͻ�����Ա\",\"Password\":\"6373DDF3790C9B95E095583CA5C76C8C\",\"Customers\":[{\"CustomerId\":319252,\"CustomerName\":\"GatewayCustomer\",\"CustomerCode\":\"gatcustomer1\",\"CustomerImageId\":\"img-logo-300790\"},{\"CustomerId\":319398,\"CustomerName\":\"StableCustomerInfo\",\"CustomerCode\":\"StableCustomerInfo-GAT\",\"CustomerImageId\":\"img-logo-300795\"}],\"Title\":\"��Դ����ʦ\",\"Telephone\":\"18911094525\",\"Email\":\"157621331@qq.com\",\"Comment\":\"For Hierarchy related get, ticket,maintainence, etc\",\"Name\":\"StableUser\",\"DemoStatus\":0,\"SpId\":299,\"Status\":1,\"SpStatus\":0,\"SpDomain\":null,\"FedLoginUrl\":null,\"Version\":9048002,\"HasWholeCustomer\":false,\"PrivilegeCodes\":[\"1104\",\"1205\"],\"SpFullName\":\"ʩ�͵µ����й���Ϣ�������޹�˾\"}}";
		//String json = "{\"Error\":\"0\",\"Message\":[\"\"],\"RequestId\":null,\"Result\":{\"Id\":301236,\"RealName\":\"StableUser\",\"UserType\":301985,\"UserTypeName\":\"�ͻ�����Ա\",\"Password\":\"6373DDF3790C9B95E095583CA5C76C8C\",\"Title\":\"��Դ����ʦ\",\"Telephone\":\"18911094525\",\"Email\":\"157621331@qq.com\",\"Comment\":\"For Hierarchy related get, ticket,maintainence, etc\",\"Name\":\"StableUser\",\"DemoStatus\":0,\"SpId\":299,\"Status\":1,\"SpStatus\":0,\"SpDomain\":null,\"FedLoginUrl\":null,\"Version\":9048002,\"HasWholeCustomer\":false,\"PrivilegeCodes\":[\"1104\",\"1205\"],\"SpFullName\":\"ʩ�͵µ����й���Ϣ�������޹�˾\"}}";

		user = deserialize(json, User.class);		
        System.out.println(user.Result.Email);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T deserialize(String json, Class<T> clazz) {  
		ObjectMapper mapper = new ObjectMapper(); 
		Object object = null;
        try {  
            object = mapper.readValue(json, TypeFactory.rawClass(clazz));  
        } catch (JsonParseException e) {  
            
        } catch (JsonMappingException e) {  
           
        } catch (IOException e) {  
            
        }  
        return (T) object;  
    }   
	
	 public static String serialize(Object object) {
		 ObjectMapper mapper = new ObjectMapper(); 
	     Writer write = new StringWriter();  
	     try {  
	        mapper.writeValue(write, object);  
	     } catch (JsonGenerationException e) {  
	             
	     } catch (JsonMappingException e) {  
	             
	        } catch (IOException e) {  
	            
	        }  
	        return write.toString();  
	    }  
	
	 //Json�ַ���ת����Map����
	 public static Map<String, Map<String, Object>> readJson2Map(String sourceJson) {
		 ObjectMapper objectMapper = new ObjectMapper(); 
		 
		    try {	    	
		        @SuppressWarnings("unchecked")
				Map<String, Map<String, Object>> maps = objectMapper.readValue(sourceJson, Map.class);
		        return maps;
		    } catch (JsonParseException e) {
		        e.printStackTrace();
		        return null;
		    } catch (JsonMappingException e) {
		        e.printStackTrace();
		        return null;
		    } catch (IOException e) {
		        e.printStackTrace();
		        return null;
		    }
		}

	//��Map����ת����Json�ַ���
	public static  String writeMapJSON(Object map) {
		    try {
		    	ObjectMapper objectMapper = new ObjectMapper();
		    	Writer write = new StringWriter(); 
		    	
		        objectMapper.writeValue(write, map);
		        return write.toString();

		    } catch (IOException e) {
		        e.printStackTrace();
		        return null;
		    }
		}
	
	//��list����ת����json�ַ���
	public static String writeListJSON(Object list) {
	    try {
	    	ObjectMapper objectMapper = new ObjectMapper();

	        //��objectMapperֱ�ӷ���listת���ɵ�JSON�ַ���
	        return objectMapper.writeValueAsString(list);

	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	//��json�ַ���ת����List<Map>����
	public static List<Map<String, Object>> readJson2List(String sourceJson) {
	    try {
	    	ObjectMapper objectMapper = new ObjectMapper();
	    	@SuppressWarnings("unchecked")
			List<Map<String, Object>> list = objectMapper.readValue(sourceJson, List.class);

	        //��objectMapperֱ�ӷ���listת���ɵ�JSON�ַ���
	        return list;

	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
  //��json�ַ���ת����List<Map>����
    public static List<Map<String, Object>> readMap2List(Object source) {
	    try {
		    ObjectMapper objectMapper = new ObjectMapper();
		    @SuppressWarnings("unchecked")
		    List<Map<String, Object>> list = objectMapper.readValue(writeMapJSON(source), List.class);

		    //��objectMapperֱ�ӷ���listת���ɵ�JSON�ַ���
		    return list;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
}	
}