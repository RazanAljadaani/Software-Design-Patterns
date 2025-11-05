package Calc;

import java.util.ArrayList;
import java.util.List;

public class OperationManager {

    private String currentOperand;
    private String operation;
    private final DisplayManager displayManager;

    private List<Operations> expressionParts = new ArrayList<>();
    private List<String> expressionOperators = new ArrayList<>();
    private boolean resultJustComputed = false;
    private String expressionDisplay = "";
    
    // Factory method
  public Operations getOperation(String symbol, Operations left, Operations right) {
        return switch (symbol) {
            case "+" -> new AddOperation(left, right);
            case "-" -> new SubtractOperation(left, right);
            case "×" -> new MultiplyOperation(left, right);
            case "÷" -> new DivideOperation(left, right);
            default -> null;
        };
    }
    public OperationManager(DisplayManager displayManager) {
        this.displayManager = displayManager;
        clear();
    }

    public void clear() {
        currentOperand = "";
        operation = "";
        expressionParts.clear();
        expressionOperators.clear();
        expressionDisplay = "";
        displayManager.update(currentOperand, expressionDisplay, "");
    }

    public void appendNumber(String number) {
        if (resultJustComputed) {
             if (operation.isEmpty()) {
        currentOperand = "";
        expressionDisplay = "";
         expressionParts.clear();
        expressionOperators.clear();
        operation="";        
    }
             resultJustComputed = false;
             }
        currentOperand = OperandManager.appendNumber(currentOperand, number);
        displayManager.update(currentOperand, expressionDisplay, operation);
    }

    public void chooseOperation(String operation) {
        if (currentOperand.isEmpty()) {
            return;
        }

        expressionParts.add(new Number(Float.parseFloat(currentOperand)));
        expressionOperators.add(operation);

        this.operation = operation;
        expressionDisplay += currentOperand + " " + operation + " ";
        currentOperand = "";

        displayManager.update(currentOperand, expressionDisplay, operation);
    }

    public void compute() {
        try {
            
            if (!currentOperand.isEmpty()) {
                expressionParts.add(new Number(Float.parseFloat(currentOperand)));
            }

            
            if (expressionParts.isEmpty()) {
                return;
            }

            

            // First, we calculate multiplication and division because they have higher priority
            int index = 0;
            while (index < expressionOperators.size()) {
                String currentOperator = expressionOperators.get(index);

                // Check if the operator is multiply or divide
                if (currentOperator.equals("×") || currentOperator.equals("÷")) {

                    // Get the two numbers we want to calculate
                    Operations leftOperand = expressionParts.get(index);
                    Operations rightOperand = expressionParts.get(index + 1);

                    // Create one operation from them
                    Operations mergedOperation
                            = getOperation(currentOperator, leftOperand, rightOperand);

                    // Replace the two numbers with the new merged result
                    expressionParts.set(index, mergedOperation);
                    expressionParts.remove(index + 1);
                    expressionOperators.remove(index);
                } else {
                    
                    index++;
                }
            }

            // Now we calculate addition and subtraction from left to right
            Operations resultOperation = expressionParts.get(0);

            for (int operatorIndex = 0; operatorIndex < expressionOperators.size(); operatorIndex++) {
                String operatorSymbol = expressionOperators.get(operatorIndex);
                Operations nextOperand = expressionParts.get(operatorIndex + 1);

                // Update the result by applying the next operation
                resultOperation = getOperation(operatorSymbol, resultOperation, nextOperand);
            }

        
            currentOperand = String.valueOf(resultOperation.evaluate());

        } catch (Exception e) {
          
            currentOperand = "Error";
        }
            resultJustComputed = true;
    
        expressionParts.clear();
        expressionOperators.clear();
        expressionDisplay = "";

  
        displayManager.update(currentOperand, "", "");
    }

    public void delete() {
        if (!currentOperand.isEmpty()) {
            currentOperand = currentOperand.substring(0, currentOperand.length() - 1);
        } 
         else if (!expressionOperators.isEmpty() && currentOperand.isEmpty()) {
        expressionOperators.remove(expressionOperators.size() - 1);

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

            this.displayManager.update(currentOperand, expressionDisplay, operation);
        }
    }
}
