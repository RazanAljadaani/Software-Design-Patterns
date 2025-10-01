package Calc;

public class OperationFactory {
    public Operation getOperation(String symbol) {
        return switch (symbol) {
            case "+" -> new AddOperation();
            case "-" -> new SubtractOperation();
            case "×" -> new MultiplyOperation();
            case "÷" -> new DivideOperation();
            default -> throw new IllegalArgumentException("Unknown operation: " + symbol);
        };
    }
}

