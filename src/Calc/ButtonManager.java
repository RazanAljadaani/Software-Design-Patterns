
package Calc;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.JButton;

public class ButtonManager {
    private final Calculator calculator;
    private final OperationManager operationManager;

    public ButtonManager(Calculator calculator, OperationManager operationManager) {
        this.calculator = calculator;
        this.operationManager = operationManager;
    }

    public void addEvents(
            JButton[] numbers, 
            JButton[] allButtons, 
            JButton btnDiv, JButton btnEqual, JButton btnDel, 
            JButton btnMult, JButton btnSub, JButton btnPlus, JButton btnClear) {

       
        for (JButton number : numbers) {
            number.addActionListener((ActionEvent e) -> {
                operationManager.appendNumber(((JButton) e.getSource()).getText());
            });
        }

       
        btnPlus.addActionListener((ActionEvent e) -> operationManager.chooseOperation("+"));
        btnSub.addActionListener((ActionEvent e) -> operationManager.chooseOperation("-"));
        btnMult.addActionListener((ActionEvent e) -> operationManager.chooseOperation("×"));
        btnDiv.addActionListener((ActionEvent e) -> operationManager.chooseOperation("÷"));

       
        btnEqual.addActionListener((ActionEvent e) -> operationManager.compute());

       
        btnClear.addActionListener((ActionEvent e) -> operationManager.clear());

        
        btnDel.addActionListener((ActionEvent e) -> operationManager.delete());

        for (JButton btn : allButtons) {
            btn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    ((JButton) e.getSource()).setBackground(new Color(73, 69, 78));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    Object b = e.getSource();
                    if (b == btnDiv || b == btnEqual || b == btnDel || 
                        b == btnMult || b == btnSub || b == btnPlus || b == btnClear) {
                        ((JButton) b).setBackground(new Color(41, 39, 44));
                    } else {
                        ((JButton) b).setBackground(new Color(21, 20, 22));
                    }
                }
            });
        }
    }
}
