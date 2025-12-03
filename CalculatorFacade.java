package Calc;

import javax.swing.JButton;
import javax.swing.JTextField;

public class CalculatorFacade {
    private final OperationManager operationManager;
    private final DisplayManager displayManager;
    private final ButtonManager buttonManager;

    public CalculatorFacade(JTextField current, JTextField previous, Calculator calculator) {
        // initialize display manager
        this.displayManager = new DisplayManager(current, previous);
        // initialize operation manager
        this.operationManager = new OperationManager(displayManager);
        // initialize button manager
        this.buttonManager = new ButtonManager(calculator, operationManager);
    }

    // Facade methods
    public void appendNumber(String number) {
        operationManager.appendNumber(number);
    }

    public void chooseOperation(String op) {
        operationManager.chooseOperation(op);
    }

    public void compute() {
        operationManager.compute();
    }

    public void clear() {
        operationManager.clear();
    }

    public void delete() {
        operationManager.delete();
    }

    public void toggleSign() {
        operationManager.toggleSign();
    }

    public void update(String currentOperand, String previousOperand, String operation) {
        displayManager.update(currentOperand, previousOperand, operation);
    }

    public void setupButtons(JButton[] numbers, JButton[] allButtons,
                             JButton btnDiv, JButton btnEqual, JButton btnDel,
                             JButton btnMult, JButton btnSub, JButton btnPlus, JButton btnClear,
                             JButton btnDot, JButton btnPlusSub) {

        buttonManager.addEvents(
                numbers,
                allButtons,
                btnDiv, btnEqual, btnDel,
                btnMult, btnSub, btnPlus, btnClear,
                btnDot, btnPlusSub
        );
    }
}
