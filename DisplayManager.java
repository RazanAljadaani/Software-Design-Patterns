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
        String curr = currentOperand == null ? "" : currentOperand;
        String prev = previousOperand == null ? "" : previousOperand.trim();
        String op   = operation == null ? "" : operation.trim();

        // Update the current field (the number currently being typed)
        current.setText(curr);

        // If there is no operation, just show the previous operand as-is
        if (op.isEmpty()) {
            previous.setText(prev);
            return;
        }

        // Prevent duplication: if previous already ends with this operator, don’t add it again
        if (prev.endsWith(op)) {
            previous.setText(prev);
        } else if (prev.isEmpty()) {
            // Optional: if no previous operand, show only the operator
            previous.setText(op);
        } else {
            // Otherwise, append the operator neatly
            previous.setText(prev + " " + op);
        }
    }
}