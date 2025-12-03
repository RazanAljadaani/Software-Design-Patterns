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
public interface OperationState {
    void appendNumber(OperationManager op, String digit);
    void chooseOperation(OperationManager op, String opSymbol);
    void compute(OperationManager op);
    void clear(OperationManager op);
}

