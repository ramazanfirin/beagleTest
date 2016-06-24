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
import PLCComMB.ModbusMaster;
import PLCComMB.OperationResult;
import PLCComMB.PortStateEventHandler;
import PLCComMB.ReadRequest;
import PLCComMB.ReadResult;
import PLCComMB.ReadValue;
import PLCComMB.RequestBuilder;
import PLCComMB.WriteRequest;
import PLCComMB.WriteResult;
import PLCComMB.iConnectionStateChangeEvent;
import PLCComMB.Enums.eBaudrate;
import PLCComMB.Enums.eByteOrder;
import PLCComMB.Enums.eConnectionState;
import PLCComMB.Enums.eDataBits;
import PLCComMB.Enums.eDataType;
import PLCComMB.Enums.eFlowControl;
import PLCComMB.Enums.eParity;
import PLCComMB.Enums.eReadFunction;
import PLCComMB.Enums.eRegisterMode;
import PLCComMB.Enums.eStopBits;
import PLCComMB.Enums.eWriteFunction;

@ManagedBean(name="dataControllerNew")
@ApplicationScoped
public class DataControllerNew implements iConnectionStateChangeEvent{

	int slaveAddress  = 33;
	//String comportName="/dev/ttyAMA0";
	String comportName="COM3";
	
	public int[] modbusValues= new int[150]; 
	private ScheduledExecutorService scheduler; 
	//ModbusRtuClientJssc mc =null;
	//ModbusTcpClient mc =null;
	
	//private String hostName="localhost";
	private String hostName="ec2-52-33-113-242.us-west-2.compute.amazonaws.com";
	
	private ModbusMaster Device = null;
	private PortStateEventHandler portStateEventHandler = new PortStateEventHandler(
			this);
	
	@PostConstruct
	public void init() {
		try {
			String[] portNames = SerialPortList.getPortNames();
		    for(int i = 0; i < portNames.length; i++){
		        System.out.println(portNames[i]);
		    }
		    
		    Device = new ModbusMaster();
			
			Device.setUser("firin");
			Device.setSerial("85484-28144-113256-2267267");

			Device.setConnector_RTU(comportName, eBaudrate.b9600,  eDataBits.DataBits8, eParity.None, eStopBits.One, eFlowControl.None);
			Device.getConnector().setMaxIdleTime(5000);
			Device.setRegisterMode(eRegisterMode._16Bit);
			Device.getConnector().addConnectionStateEventHandler(
					portStateEventHandler);
			
			
//			updateModbusData(0,63);
//			updateModbusData(63,63);
//			startTask();
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
	
	public void updateModbusData(int startaddress,int count) throws IOException{
	
    ReadRequest myReadRequest = RequestBuilder.ReadRequestBuilder.create(33, //Slave ID
			eReadFunction.F03_Read_Holding_Registers,//modbus function
			startaddress,//Read start adress
			eDataType.SHORT,//Target Datatype
			count); //quantity of objects to be read

	//set eventual byte order, standard = eByteOrder.AB_CD;
	myReadRequest.setByteOrder(eByteOrder.AB_CD);

//	System.out.println(myReadRequest.toString());

	//read from device
	ReadResult res = Device.read(myReadRequest);

	
	if (res.getQuality().equals(OperationResult.eQuality.GOOD)) {
		StringBuilder sb = new StringBuilder();
		
		for (ReadValue item : res.fetchValues()) {
			
			int address = item.getAddress();
			short value = (short)item.getValue();
			modbusValues[address] = value;
			System.out.println(address+"->"+value);
		}
	
	}else
		System.out.println("errro = "+res.getQuality());
	
    
    
    
    
   
		
	} 
	

	
	
	public void readModbus(){
		
	}
	
	public void saveModbusMultiple(int startAddress,int[] values) throws IOException{
		destroyTask();
		String s="";
		WriteRequest myWriteRequest = RequestBuilder.WriteRequestBuilder
				.create(slaveAddress, // Slave ID
						eWriteFunction.F16_Write_Multiple_Registers,// modbus
																	// function
						startAddress);// write start adress

		// set eventual byte order, standard = eByteOrder.AB_CD;
		myWriteRequest.setByteOrder(eByteOrder.AB_CD);

		// write 4 holding register at adress 100
		// add writable data to request
		myWriteRequest.addIntegerRange(values);;

		try {
			WriteResult res = Device.write(myWriteRequest);
			res.getMessage();
			for (int i = 0; i < values.length; i++) {
				modbusValues[startAddress+1]=values[i];
				s=s+","+values[i];
			}
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Successful,"+startAddress +"="+ s) );

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("error,"+startAddress +"="+ s) );
		} finally {

			// System.out.println("Modbus yazildi");
		}
		startTask();
	}
	
	
	public void saveModbus(int address,int value) throws IOException{
		destroyTask();
		
		WriteRequest myWriteRequest = RequestBuilder.WriteRequestBuilder
				.create(slaveAddress, //Slave ID
						eWriteFunction.F16_Write_Multiple_Registers,//modbus function
						address);//write start adress

		//set eventual byte order, standard = eByteOrder.AB_CD;
		myWriteRequest.setByteOrder(eByteOrder.AB_CD);

		//write 4 holding register at adress 100
		//add writable data to request
		myWriteRequest.addShortRange(new short[] { (short)value });
		
		
		
		try {
			WriteResult res = Device.write(myWriteRequest);
			res.getMessage();
				modbusValues[address]=value;
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(null, new FacesMessage("Successful,"+address +"="+ value) );
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("error,"+address +"="+ value) );
		}finally{
			
			//System.out.println("Modbus yazildi");
		}
		startTask();
	}
	
	public class SomeDailyJob implements Runnable {

	    @Override
	    public void run() {
	    	try {
	    		updateModbusData(0,63);
				updateModbusData(63,63);
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

	@Override
	public void On_ConnectionStateChange(eConnectionState arg0) {
		System.out.println("Change PortState ==> " + arg0.toString());
		
	}
	
	public static void main(String[] args) throws IOException {
		DataControllerNew controller = new DataControllerNew();
		controller.init();
		//controller.updateModbusData();
		System.out.println("test");
	}
}
