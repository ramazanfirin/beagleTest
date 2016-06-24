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
	
	public static String convertToBinary(int value){
		String alarmBits = Integer.toBinaryString(value);
		String temp="";
		for(int i=alarmBits.length();i<16;i++)
			temp=temp+"0";
		
		String result = temp+alarmBits;
		return result;
	}
	
	public static String getBit(String value,int bit){

		String result = value.substring(bit, bit+1);
		return result;
	}

	public static int updateBit(String value,int index,int newValue){
		//String alarmBits = Integer.toBinaryString(value);
		StringBuilder myName = new StringBuilder(value);
		
		String aaa = String.valueOf(newValue);
		char c = aaa.charAt(0);
		myName.setCharAt(index, c);
		Integer i = Integer.parseInt(myName.toString(),2);
		return i.intValue();
	}
}
