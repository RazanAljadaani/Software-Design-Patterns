/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calc;

/**
 *
 * @author LENOVO
 */

class InputState implements OperationState {

    @Override
    public void appendNumber(OperationManager op, String digit) {
        op.currentOperand = OperandManager.appendNumber(op.currentOperand, digit);
        op.updateDisplay();
    }

   

       @Override
public void chooseOperation(OperationManager op, String opSymbol) {
    if (!op.currentOperand.isEmpty()) {
         
        op.expressionParts.add(new Number(Float.parseFloat(op.currentOperand)));
        op.expressionOperators.add(opSymbol);
        
        op.expressionDisplay += op.currentOperand + " " + opSymbol + " ";
        op.currentOperand = "";
    } else if (!op.expressionOperators.isEmpty()) {
        
        op.expressionOperators.set(op.expressionOperators.size() - 1, opSymbol);
        op.expressionDisplay = op.expressionDisplay.replaceAll("[+\\-×÷]$", opSymbol);
    }

    op.operation = opSymbol;
    op.updateDisplay();
}


    @Override
    public void compute(OperationManager op) {
        boolean ok = op.evaluateExpression();
        op.setState(new ResultState());
    }

    @Override
    public void clear(OperationManager op) {
        op.resetAll();
    }
}

class ResultState implements OperationState {

    @Override
    public void clear(OperationManager op) {
        op.resetAll();
        op.setState(new InputState());
    }

    @Override
    public void appendNumber(OperationManager op, String digit) {
        // Start a new expression after result or error
        op.resetAll();
        op.currentOperand = OperandManager.appendNumber("", digit);
        op.updateDisplay();
        op.setState(new InputState());
    }

   
       @Override
public void chooseOperation(OperationManager op, String opSymbol) {
    if (op.currentOperand == null || op.currentOperand.isEmpty() || op.currentOperand.equals("Error")) {
        return;
    }

      
    op.expressionParts.clear();
    op.expressionOperators.clear();

    op.expressionParts.add(new Number(Float.parseFloat(op.currentOperand)));
    op.expressionOperators.add(opSymbol);

    op.expressionDisplay = op.currentOperand + " " + opSymbol + " ";
    op.currentOperand = "";
    op.operation = opSymbol;
    op.updateDisplay();

    op.setState(new InputState());
}

    @Override
    public void compute(OperationManager op) {
    }
}
