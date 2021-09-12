package MyCalculatorAskClientServerSerializable;

import java.io.*;
import java.util.*;

public class Request implements Serializable {

   private String opcode;
   private int num1, num2;
   
   Request(int n1, String op, int n2) {
      opcode = op;
      num1 = n1;
      num2 = n2;
   }

   public String getOpcode() {
      return opcode;
   }

   public int getFirst() {
      return num1;
   }

   public int getSecond() {
      return num2;
   }
}

