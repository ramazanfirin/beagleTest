import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import PLCComMB.ModbusMaster;
import PLCComMB.OperationResult;
import PLCComMB.ReadRequest;
import PLCComMB.ReadResult;
import PLCComMB.ReadValue;
import PLCComMB.RequestBuilder;
import PLCComMB.Enums.eBaudrate;
import PLCComMB.Enums.eByteOrder;
import PLCComMB.Enums.eDataBits;
import PLCComMB.Enums.eDataType;
import PLCComMB.Enums.eFlowControl;
import PLCComMB.Enums.eParity;
import PLCComMB.Enums.eReadFunction;
import PLCComMB.Enums.eRegisterMode;
import PLCComMB.Enums.eStopBits;


@ManagedBean
@ApplicationScoped
public class ModbusController {
	private ModbusMaster Device = null;
	String name="aaa";
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ModbusController() throws IOException {
		super();
		// TODO Auto-generated constructor stub
		
	}
	
	@PostConstruct
	public void init() throws IOException{
//		testModbus();
	}



	public void testModbus() throws IOException{
		Device = new ModbusMaster();
		
		Device.setUser("f?r?n");
		Device.setSerial("82400-21182-771172-2967168");
		
		Device.setConnector_RTU("/dev/ttyAMA0",
	            eBaudrate.b9600,
	            eDataBits.DataBits8,
	            eParity.None,
	            eStopBits.One,
	            eFlowControl.None);
		
		Device.getConnector().setMaxIdleTime(5000);
		Device.setRegisterMode(eRegisterMode._16Bit);
		
		System.out.println("modbud master listen...");
	}
	
	
	public void readModbus() throws IOException {
		testModbus();
		System.out.println("start reading modbus");

		//declare a ReadRequest object
		//and set the request parameters >> read 10 holding register from adress 100
		ReadRequest myReadRequest = RequestBuilder.ReadRequestBuilder.create(1, //Slave ID
				eReadFunction.F03_Read_Holding_Registers,//modbus function
				0,//Read start adress
				eDataType.SHORT,//Target Datatype
				10); //quantity of objects to be read

		//set eventual byte order, standard = eByteOrder.AB_CD;
		myReadRequest.setByteOrder(eByteOrder.AB_CD);

		System.out.println(myReadRequest.toString());

		//read from device
		ReadResult res = Device.read(myReadRequest);

		//evaluate results
		System.out.println(DateFormat.getDateTimeInstance().format(
				Calendar.getInstance().getTime())
				+ ": " + res.getMessage());

		if (res.getQuality().equals(OperationResult.eQuality.GOOD)) {
			StringBuilder sb = new StringBuilder();
			for (ReadValue item : res.fetchValues()) {
				sb.append("Address " + String.valueOf(item.getAddress())
						+ " / Pos" + String.valueOf(item.getAddressPosition()));
				sb.append(" >> ");
				sb.append(item.toString());
				sb.append(System.getProperty("line.separator"));
			}
			System.out.println(sb.toString());
		}
		System.out.println("end reading modbus");
		System.out.println(System.getProperty("line.separator"));
	}

}
