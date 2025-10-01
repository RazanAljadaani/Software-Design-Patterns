
package Calc;
public class CalculatorOperations {
     public static String  compute(String operation , String currentOperand,String previousOperand) {
        float computation;
        if (currentOperand.equals("") || previousOperand.equals("")) {
            return "";
        }

        float curr = Float.parseFloat(currentOperand);
        float prev = Float.parseFloat(previousOperand);
        if (Float.isNaN(curr) || Float.isNaN(prev)) {
            return  "";
        }

        switch (operation) {
            case "+" ->
                computation = prev + curr;
            case "-" ->
                computation = prev - curr;
            case "×" ->
                computation = prev * curr;
            case "÷" -> {
                if (curr == 0) return "Error";
                
                computation = prev / curr;
            }
            default -> {
                return "";
            }
        }

         return  (computation - (int) computation) != 0 ? Float.toString(computation) : Integer.toString((int) computation);
        
    }

}
