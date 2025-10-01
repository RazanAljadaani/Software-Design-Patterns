package Calc;

import javax.swing.JTextField;

public class DisplayManager {
    private JTextField current;
    private JTextField previous;

    public DisplayManager(JTextField current, JTextField previous) {
        this.current = current;
        this.previous = previous;
    }

    public void update(String currentOperand, String previousOperand, String operation) {
        current.setText(currentOperand);
        previous.setText(previousOperand + " " + operation);
    }
}
