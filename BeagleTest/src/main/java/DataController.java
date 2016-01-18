import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import ua.com.certa.modbus.AModbusClient;
import ua.com.certa.modbus.ModbusTcpClient;

@ManagedBean(name="dataController")
@ApplicationScoped
public class DataController {

	public int[] modbusValues= new int[150]; 
	private ScheduledExecutorService scheduler; 
	//ModbusRtuClientJssc mc =null;
	//ModbusTcpClient mc =null;
	
	@PostConstruct
	public void init() {
		try {
			updateModbusData();
			startTask();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//mc = new ModbusRtuClientJssc("COM5", 9600, 8, SerialPort.PARITY_EVEN, SerialPort.STOPBITS_1, 1000, 5);
	}

	@PreDestroy
	public void destroy() {
		scheduler.shutdownNow();
	}
	
	public void startTask(){
		scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(new SomeDailyJob(), 0, 5, TimeUnit.SECONDS);
	}
	
	public void destroyTask(){
		scheduler.shutdownNow();
	}
	
	public void updateModbusData() throws IOException{
		 //if(mc==null)
	//ModbusRtuClientJssc	mc = new ModbusRtuClientJssc("COM5", 9600, 8, SerialPort.PARITY_EVEN, SerialPort.STOPBITS_1, 1000, 5);
	ModbusTcpClient mc = new ModbusTcpClient("localhost", 502, null, 0, 1000, 300, true);
    mc.InitReadHoldingsRequest(1, 0, 125);
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
		//System.out.println("Modbus Okundu");
	}
	

	}
	
	public void readModbus(){
		
	}
	
	public void saveModbusMultiple(int startAddress,int[] values) throws IOException{
destroyTask();
		String s="";
		//ModbusRtuClientJssc mc = new ModbusRtuClientJssc("COM5", 9600, 8, SerialPort.PARITY_EVEN, SerialPort.STOPBITS_1, 1000, 5);
		ModbusTcpClient mc = new ModbusTcpClient("localhost", 502, null, 0, 1000, 300, true);
		mc.InitWriteRegistersRequest(1, startAddress, values);
		try {
			mc.execRequest();
			if (mc.getResult() == AModbusClient.RESULT_OK){
				for (int i = 0; i < values.length; i++) {
					modbusValues[startAddress+1]=values[i];
					s=s+","+values[i];
				}
				
				
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Successful,"+startAddress +"="+ s) );
			}else
				System.out.println("error");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			mc.close();
			//System.out.println("Modbus yazildi");
		}
		startTask();
	}
	
	
	public void saveModbus(int address,int value) throws IOException{
		destroyTask();
		
		//ModbusRtuClientJssc mc = new ModbusRtuClientJssc("COM5", 9600, 8, SerialPort.PARITY_EVEN, SerialPort.STOPBITS_1, 1000, 5);
		ModbusTcpClient mc = new ModbusTcpClient("localhost", 502, null, 0, 1000, 300, true);
		mc.InitWriteRegisterRequest(1, address, value);
		try {
			mc.execRequest();
			if (mc.getResult() == AModbusClient.RESULT_OK){
				modbusValues[address]=value;
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Successful,"+address +"="+ value) );
			}else
				System.out.println("error");
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			mc.close();
			//System.out.println("Modbus yazildi");
		}
		startTask();
	}
	
	public class SomeDailyJob implements Runnable {

	    @Override
	    public void run() {
	    	try {
				updateModbusData();
				//System.out.println("Data update yapildi");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
