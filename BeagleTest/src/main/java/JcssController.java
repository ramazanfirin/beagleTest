import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortList;

import com.pi4j.io.serial.SerialPortException;


@ManagedBean
@ApplicationScoped
public class JcssController {

	static  SerialPort outputPort = new SerialPort("/dev/ttyAMA0");
	static  SerialPort inputPort = new SerialPort("COM5");
	
	
	
	
	
	public JcssController() throws jssc.SerialPortException {
		super();
//		//serialPort4 = new SerialPort("COM1"); 
//	        try {
//	        	inputPort.openPort();//Open port
//	        	inputPort.setParams(9600, 8, 1, 0);//Set params
//	            int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;//Prepare mask
//	            inputPort.setEventsMask(mask);//Set mask
//	            inputPort.addEventListener(new SerialPortReader());//Add SerialPortEventListener
//	        }
//	        catch (SerialPortException ex) {
//	            System.out.println(ex);
//	        }
	}

public void listenPort() throws jssc.SerialPortException{
	 try {
     	inputPort.openPort();//Open port
     	inputPort.setParams(9600, 8, 1, 0);//Set params
         int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;//Prepare mask
         inputPort.setEventsMask(mask);//Set mask
         inputPort.addEventListener(new SerialPortReader());//Add SerialPortEventListener
         System.out.println("port dinleniyor");
     }
     catch (SerialPortException ex) {
         System.out.println(ex);
     }
}
	
	
	
	public void listPorts(){
	String[] portNames = SerialPortList.getPortNames();
    for(int i = 0; i < portNames.length; i++){
        System.out.println(portNames[i]);
    }
}
	

	public void send() throws Exception{
	
		//SerialPort serialPort = new SerialPort("COM1");
        try {
        	outputPort.openPort();//Open serial port
        	outputPort.setParams(SerialPort.BAUDRATE_9600, 
                                 SerialPort.DATABITS_8,
                                 SerialPort.STOPBITS_1,
                                 SerialPort.PARITY_NONE);//Set params. Also you can set params by this string: serialPort.setParams(9600, 8, 1, 0);
        	outputPort.writeBytes("This is a test string".getBytes());//Write data to port
        	outputPort.closePort();//Close serial port
        }
        catch (SerialPortException ex) {
            System.out.println(ex);
        }
		
        System.out.println("data gönderildi");
	}



	static class SerialPortReader implements SerialPortEventListener {

        public void serialEvent(SerialPortEvent event) {
            if(event.isRXCHAR()){//If data is available
                //if(event.getEventValue() == 10){//Check bytes count in the input buffer
                    //Read data, if 10 bytes available 
                    try {
                        byte buffer[] = inputPort.readBytes();
                        System.out.println(new String(buffer));
                    }
                    catch (SerialPortException ex) {
                        System.out.println(ex);
                    } catch (jssc.SerialPortException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
               // }
            }
            else if(event.isCTS()){//If CTS line has changed state
                if(event.getEventValue() == 1){//If line is ON
                    System.out.println("CTS - ON");
                }
                else {
                    System.out.println("CTS - OFF");
                }
            }
            else if(event.isDSR()){///If DSR line has changed state
                if(event.getEventValue() == 1){//If line is ON
                    System.out.println("DSR - ON");
                }
                else {
                    System.out.println("DSR - OFF");
                }
            }
        }
    }
	
}
	

