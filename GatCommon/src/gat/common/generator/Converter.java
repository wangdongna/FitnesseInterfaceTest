package gat.common.generator;

public class Converter {
	
	public static String convert(String input)
	{
		String tmp = input;
		if(input.equals("null"))
	       tmp = null;
		else if (input.equals("blank"))
	       tmp = "";
		
		return tmp;
	}
}
