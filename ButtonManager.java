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
            JButton btnMult, JButton btnSub, JButton btnPlus, JButton btnClear,
            JButton btnDot, JButton btnPlusSub) {

     
        for (JButton number : numbers) {
            number.addActionListener((ActionEvent e) -> {
                String value = ((JButton) e.getSource()).getText();
                new AppendNumCommand(operationManager, value).execute();
            });
        }

        btnPlus.addActionListener(e ->
                new ChooseOperationCommand(operationManager, "+").execute());

        btnSub.addActionListener(e ->
                new ChooseOperationCommand(operationManager, "-").execute());

        btnMult.addActionListener(e ->
                new ChooseOperationCommand(operationManager, "×").execute());

        btnDiv.addActionListener(e ->
                new ChooseOperationCommand(operationManager, "÷").execute());

        // ===== Equals, Clear, Delete =====
        btnEqual.addActionListener(e ->
                new CmputeCommand(operationManager).execute());

        btnClear.addActionListener(e ->
                new ClearCommand(operationManager).execute());

        btnDel.addActionListener(e ->
                new DeleteCommand(operationManager).execute());

     
        btnDot.addActionListener(e ->
                new AppendNumCommand(operationManager, ".").execute());

  
        btnPlusSub.addActionListener(e ->
                new ToggleSignCommand(operationManager).execute());

      
        for (JButton btn : allButtons) {
            btn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    ((JButton) e.getSource()).setBackground(new Color(73, 69, 78));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    Object b = e.getSource();
                    if (b == btnDiv || b == btnEqual || b == btnDel
                            || b == btnMult || b == btnSub || b == btnPlus || b == btnClear) {
                        ((JButton) b).setBackground(new Color(41, 39, 44));
                    } else {
                        ((JButton) b).setBackground(new Color(21, 20, 22));
                    }
                }
            });
        }
    }
}
