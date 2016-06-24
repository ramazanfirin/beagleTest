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

import jssc.SerialPort;
import jssc.SerialPortList;
import ua.com.certa.modbus.AModbusClient;
import ua.com.certa.modbus.ModbusRtuClientJssc;
import ua.com.certa.modbus.ModbusTcpClient;

@ManagedBean(name="dataController")
@ApplicationScoped
public class DataController {

	int slaveAddress  = 33;
	//String comportName="/dev/ttyAMA0";
	String comportName="COM1";
	
	public int[] modbusValues= new int[150]; 
	private ScheduledExecutorService scheduler; 
	//ModbusRtuClientJssc mc =null;
	//ModbusTcpClient mc =null;
	
	private String hostName="localhost";
	//private String hostName="ec2-52-33-113-242.us-west-2.compute.amazonaws.com";
	
	@PostConstruct
	public void init() {
		try {
			String[] portNames = SerialPortList.getPortNames();
		    for(int i = 0; i < portNames.length; i++){
		        System.out.println(portNames[i]);
		    }
			updateModbusData();
			startTask();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//mc = new ModbusRtuClientJssc("COM5", 9600, 8, SerialPort.PARITY_EVEN, SerialPort.STOPBITS_1, 1000, 5);
	}
	
	public AModbusClient getModbusClient(){
		//ModbusRtuClientJssc	mc = new ModbusRtuClientJssc(comportName, 9600, 8, SerialPort.PARITY_NONE, SerialPort.STOPBITS_1, 1000, 5);
		ModbusTcpClient mc = new ModbusTcpClient(hostName, 502, null, 0, 1000, 300, true);
		return mc;
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
		AModbusClient mc = getModbusClient();
    mc.InitReadHoldingsRequest(slaveAddress, 0, 125);
	try {
		mc.execRequest();
		if (mc.getResult() == AModbusClient.RESULT_OK){
			for (int i = 0; i < mc.getResponseCount(); i++)
				modbusValues[i]= mc.getResponseRegister(mc.getResponseAddress() + i);
		}else{
			System.out.println("error="+mc.getResult());
			if (mc.getResult() == AModbusClient.RESULT_EXCEPTION){
				System.out.println("exception cod="+mc.getExceptionCode());
				System.out.println(mc.getResultAsString());
			}
			
		}
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		mc.close();
		System.out.println("Modbus Okundu");
	}
	

	}
	
	public void readModbus(){
		
	}
	
	public void saveModbusMultiple(int startAddress,int[] values) throws IOException{
destroyTask();
		String s="";
		AModbusClient mc = getModbusClient();
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
		
		AModbusClient mc = getModbusClient();
		mc.InitWriteRegisterRequest(slaveAddress, address, value);
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
