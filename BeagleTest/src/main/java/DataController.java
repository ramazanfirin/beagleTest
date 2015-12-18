import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import jssc.SerialPort;
import ua.com.certa.modbus.AModbusClient;
import ua.com.certa.modbus.ModbusRtuClientJssc;
import constants.Constants;

@ManagedBean(name="dataController")
@ApplicationScoped
public class DataController {

	public int[] modbusValues= new int[146]; 
	private ScheduledExecutorService scheduler; 
	ModbusRtuClientJssc mc =null;

	@PostConstruct
	public void init() {
	scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(new SomeDailyJob(), 0, 5, TimeUnit.SECONDS);
	
		mc = new ModbusRtuClientJssc("COM5", 9600, 8, SerialPort.PARITY_EVEN, SerialPort.STOPBITS_1, 1000, 5);
	}

	@PreDestroy
	public void destroy() {
		scheduler.shutdownNow();
	}
	
	public class SomeDailyJob implements Runnable {

	    @Override
	    public void run() {
	        //if(mc==null)
	        	mc = new ModbusRtuClientJssc("COM5", 9600, 8, SerialPort.PARITY_EVEN, SerialPort.STOPBITS_1, 1000, 5);
	        
	        mc.InitReadHoldingsRequest(1, 0, 16);
			try {
				mc.execRequest();
				if (mc.getResult() == AModbusClient.RESULT_OK){
					for (int i = 0; i < mc.getResponseCount(); i++)
						modbusValues[i]= mc.getResponseRegister(mc.getResponseAddress() + i);
				}else
					System.out.println("error");
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				mc.close();
			}
			
	    }

	}

	public int[] getModbusValues() {
		return modbusValues;
	}

	public void setModbusValues(int[] modbusValues) {
		this.modbusValues = modbusValues;
	}
}
