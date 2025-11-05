package Calc;

public class OperandManager{
   
public static String appendNumber(String currentOperand, String number) {
   
    if (currentOperand.equals("0") && number.equals("0")) {
        return currentOperand;
    }

  
    if (number.equals(".") && currentOperand.contains(".")) {
        return currentOperand;
    }

    
    if (currentOperand.isEmpty() && number.equals(".")) {
        return "0.";
    }

   
    if (currentOperand.equals("0") && !number.equals("0") && !number.equals(".")) {
        currentOperand = "";
    }

   
    currentOperand += number;
    return currentOperand;
}
}