package MyCalculatorValidationByClientServer;

public class ServerProtocol {
	
    public String processRequest(String theInput) {
    	
        String theOutput;
        System.out.println("Received message from client: " + theInput);

        int num1;
        int num2;
        String operation;
        int result;

        //Input split
        String[] splited = theInput.split("\\s+");
        num1 = Integer.parseInt(splited[0]);
        operation = splited[1];
        num2 = Integer.parseInt(splited[2]);


        //Processing of input
        if (operation.equals("+")){
            result = (num1 + num2);
            theOutput = Integer.toString(result);
        }
        else if (operation.equals("-")){
            result = (num1 - num2);
            theOutput = Integer.toString(result);
        }
        else if (operation.equals("/")){
            result = (num1 / num2);
            theOutput = Integer.toString(result);
        }
        else if (operation.equals("*")){
            result = (num1 * num2);
            theOutput = Integer.toString(result);
        }
        else {
            theOutput = "bad_input";
        }

        System.out.println("Send message to client: " + theOutput);
        
        return theOutput;
    }
}