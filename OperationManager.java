package Calc;

public class OperationManager {
    private String currentOperand;
    private String previousOperand;
    private String operation;
    private final DisplayManager displayManager;

    public OperationManager(DisplayManager displayManager) {
        this.displayManager = displayManager;
        clear();
    }

    public void clear() {
        currentOperand = "";
        previousOperand = "";
        operation = "";
        displayManager.update(currentOperand, previousOperand, operation);
    }

    public void appendNumber(String number) {
        currentOperand = OperandManager.appendNumber(currentOperand, number);
        displayManager.update(currentOperand, previousOperand, operation);
    }

    public void chooseOperation(String operation) {
        if (currentOperand.isEmpty()) return;
        if (!previousOperand.isEmpty()) {
            compute();
        }
        this.operation = operation;
        previousOperand = currentOperand;
        currentOperand = "";
        displayManager.update(currentOperand, previousOperand, operation);
    }

    public void compute() {
        if (previousOperand.isEmpty() || currentOperand.isEmpty() || operation.isEmpty()) {
            return; 
        }

        try {
            float curr = Float.parseFloat(currentOperand);
            float prev = Float.parseFloat(previousOperand);
           
            OperationFactory opfactory = new OperationFactory();
            Operation op = opfactory.getOperation(operation);
            currentOperand = op.execute(prev, curr);

        } catch (Exception e) {
            currentOperand = "Error";
        }

        previousOperand = "";
        operation = "";
        displayManager.update(currentOperand, previousOperand, operation);
    }

    public void delete() {
        if (!currentOperand.isEmpty()) {
            currentOperand = currentOperand.substring(0, currentOperand.length() - 1);
            displayManager.update(currentOperand, previousOperand, operation);
        }
    }

  
    public String getCurrentOperand() {
        return currentOperand;
    }

  
    public String getPreviousOperand() {
        return previousOperand;
    }

   
    public String getOperation() {
        return operation;
    }
}
