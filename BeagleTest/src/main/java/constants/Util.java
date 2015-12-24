package constants;

public class Util {
	public static Boolean translate(String a){
		if(a.equals("0"))
			return true;
		else
			return false;
	}
	
	public static int translate(Boolean b){
		if(b)
			return 0;
		else
			return 1;
	}
}
