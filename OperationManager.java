package Calc;

import java.util.ArrayList;
import java.util.List;

public class OperationManager {

    String currentOperand = "";
    String operation = "";
    private final DisplayManager displayManager;
    private OperationState state;

    List<Operations> expressionParts = new ArrayList<>();
    List<String> expressionOperators = new ArrayList<>();
    String expressionDisplay = "";
    
    
    // Factory methods
  public Operations getOperation(String symbol, Operations left, Operations right) {
        return switch (symbol) {
            case "+" -> new AddOperation(left, right);
            case "-" -> new SubtractOperation(left, right);
            case "×" -> new MultiplyOperation(left, right);
            case "÷" -> new DivideOperation(left, right);
            default -> null;
        };
    }
  
  
 //المتحكم الرئيسي الي يستخدم ال state صارت المثود ما تتنفذ من الاوبريشين مانجر صارت تتنفذ من ال state
    public OperationManager(DisplayManager displayManager) {
        this.displayManager = displayManager;
        this.state = new InputState();
        resetAll();
    }

    public void clear() {
        state.clear(this);
    }

    public void appendNumber(String number) {
        state.appendNumber(this, number);
    }

    public void chooseOperation(String operation) {
        state.chooseOperation(this, operation);
    }

    public void compute() {
        state.compute(this);
    }   
    //نهاية الستيت

    void resetAll() {
        currentOperand = "";
        operation = "";
        expressionDisplay = "";
        expressionParts.clear();
        expressionOperators.clear();
        updateDisplay();
    }

    void updateDisplay() {
        displayManager.update(currentOperand, expressionDisplay, operation);
    }

    boolean evaluateExpression() {
        try {
            if (!currentOperand.isEmpty()) {
                expressionParts.add(new Number(Float.parseFloat(currentOperand)));
            }

            if (expressionParts.isEmpty()) {
                // nothing to compute
                return true;
            }

            // First handle × and ÷
           int index = 0;
           while (index < expressionOperators.size()) {
           String opSymbol = expressionOperators.get(index);
            if (opSymbol.equals("×") || opSymbol.equals("÷")) {
              Operations left = expressionParts.get(index); Operations right = expressionParts.get(index + 1);
              Operations merged = getOperation(opSymbol, left, right); expressionParts.set(index, merged); 
              expressionParts.remove(index + 1); expressionOperators.remove(index); } else { index++; } }
    
           

            // Then handle + and -
            Operations resultOp = expressionParts.get(0);
            for (int i = 0; i < expressionOperators.size(); i++) {
                String opSymbol = expressionOperators.get(i);
                Operations right = expressionParts.get(i + 1);
                resultOp = getOperation(opSymbol, resultOp, right);
            }

            float result = resultOp.evaluate();
            currentOperand = Float.toString(result);

            expressionParts.clear();
            expressionOperators.clear();
            expressionDisplay = "";
            operation = "";
            updateDisplay();
            return true;

        } catch (Exception e) {
            currentOperand = "Error";
            expressionParts.clear();
            expressionOperators.clear();
            expressionDisplay = "";
            updateDisplay();
            return false;
        }
    }

    
    public void setState(OperationState newState) {
        this.state = newState;
    }

    public OperationState getState() {
        return state;
    }
    public void delete() {
        if (!currentOperand.isEmpty()) {
            currentOperand = currentOperand.substring(0, currentOperand.length() - 1);
        } 
         else if (!expressionOperators.isEmpty() && currentOperand.isEmpty()) {
            expressionOperators.remove(expressionOperators.size() - 1);
             expressionDisplay = expressionDisplay.replaceAll(".$", "").trim();
             operation = "";

        if (!expressionDisplay.isEmpty()) {
            
            expressionDisplay = expressionDisplay.trim();
            
            expressionDisplay = expressionDisplay.replaceAll(".$", "").trim();
        }

        operation = "";
    }
        
        
        else if (!expressionParts.isEmpty()) {
            Operations lastExpression = expressionParts.get(expressionParts.size() - 1);

            if (lastExpression instanceof CompositeOperation composite) {
                Operations lastChild = composite.getChild(composite.children.size() - 1);
                composite.remove(lastChild);
            } else {
                expressionParts.remove(expressionParts.size() - 1);
                if (!expressionOperators.isEmpty()) {
                    expressionOperators.remove(expressionOperators.size() - 1);
                }
                
            }
            
        }

        displayManager.update(currentOperand, expressionDisplay, operation);
    }

    public void toggleSign() {
        if (!this.currentOperand.isBlank()) {
            float tmp = -Float.parseFloat(this.currentOperand);
            this.currentOperand = (tmp - (int) tmp) != 0
                    ? Float.toString(tmp)
                    : Integer.toString((int) tmp);
            operation="";
            this.displayManager.update(currentOperand, expressionDisplay, operation);
        }
    }
    
    
}
