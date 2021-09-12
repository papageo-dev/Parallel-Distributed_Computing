package MyCalculatorAskClientServerSerializable;

import java.net.*;
import java.io.*;

public class ServerProtocol {
	
	private String opcode;
	private int num1, num2;
	private int tmp;
	private Reply res = new Reply();
	
	public Reply processRequest(Request theInput) {

		opcode = theInput.getOpcode();
     		num1 = theInput.getFirst();
		num2 = theInput.getSecond();
		
		if (opcode.equals("+")) {res.setOpcode("OK"); tmp = num1+num2; res.setValue(tmp); 
		} else if (opcode.equals("-")) {res.setOpcode("OK"); tmp = num1-num2; res.setValue(tmp); 
		} else if (opcode.equals("*")) {res.setOpcode("OK"); tmp = num1*num2; res.setValue(tmp); 
		} else if (opcode.equals("/")) {res.setOpcode("OK"); tmp = num1/num2; res.setValue(tmp); 
		} else { res.setOpcode("ERR"); res.setValue(-9999); };
		return res;
		
	}
}