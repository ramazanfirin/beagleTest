import java.io.IOException;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.bulldog.beagleboneblack.BBBNames;
import org.bulldog.core.io.serial.SerialDataEventArgs;
import org.bulldog.core.io.serial.SerialDataListener;
import org.bulldog.core.io.serial.SerialPort;
import org.bulldog.core.platform.Board;
import org.bulldog.core.platform.Platform;
import org.bulldog.core.util.BulldogUtil;

@ManagedBean
@ApplicationScoped
public class Controller1 {
String name="ramazan";

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public void testSerial() throws IOException{
	System.out.println("test basliyor"); 
	final Board board = Platform.createBoard();
	SerialPort serial2 = board.getSerialPort(BBBNames.UART2);
    serial2.setBaudRate(9600);
    serial2.setBlocking(false);
    serial2.open();
     
    //Add a listener... This will print the data once it is
    //available for read on the port
    serial2.addListener(new SerialDataListener() {

        @Override
        public void onSerialDataAvailable(SerialDataEventArgs args) {
            System.out.print(args.getDataAsString());
        }
         
    });;
    
	
	
	
	
	
	
	
	
	
	
	
	
		
     SerialPort serial1 = board.getSerialPort(BBBNames.UART1);
     serial1.setBaudRate(9600);
     serial1.setBlocking(false);
     serial1.open();
     serial1.addListener(new SerialDataListener() {

         @Override
         public void onSerialDataAvailable(SerialDataEventArgs args) {
             System.out.print(args.getDataAsString());
         }
          
     });;
     
     
     
     serial2.writeString("aaaaaaaaaHello Serial1 - Greetings from Serial2\n");
     BulldogUtil.sleepMs(500);
     
     //System.out.print(serial1.readString());
     serial1.writeString("aaaaaaaaaaHello Serial2 - Greetings from Serial1\n");
     BulldogUtil.sleepMs(1000);
     
     //System.out.print(serial2.readString());
     System.out.println("test bitti");
     
     
     
     
     serial1.close();
     serial2.close();
}
}

