package Calc;


class AppendNumCommand implements OperationCommand {
    private final OperationManager opManager;
    private final String number;

    public AppendNumCommand(OperationManager operationManager, String number) {
        this.opManager = operationManager;
        this.number = number;
    }

    @Override
    public void execute() {
        opManager.appendNumber(number);
    }
}

class ChooseOperationCommand implements OperationCommand {
    private final OperationManager opManager;
    private final String operation;

    public ChooseOperationCommand(OperationManager operationManager, String operation) {
        this.opManager = operationManager;
        this.operation = operation;
    }

    @Override
    public void execute() {
        opManager.chooseOperation(operation);
    }
}

class CmputeCommand implements OperationCommand { 
    private final OperationManager opManager;

    public CmputeCommand(OperationManager operationManager) {
        this.opManager = operationManager;
    }

    @Override
    public void execute() {
        opManager.compute();
    }
}

class ClearCommand implements OperationCommand {
    private final OperationManager opManager;

    public ClearCommand(OperationManager operationManager) {
        this.opManager = operationManager;
    }

    @Override
    public void execute() {
        opManager.clear();
    }
}

class DeleteCommand implements OperationCommand {
    private final OperationManager opManager;

    public DeleteCommand(OperationManager operationManager) {
        this.opManager = operationManager;
    }

    @Override
    public void execute() {
        opManager.delete();
    }
}


class ToggleSignCommand implements OperationCommand {
    private final OperationManager opManager;

    public ToggleSignCommand(OperationManager operationManager) {
        this.opManager = operationManager;
    }

    @Override
    public void execute() {
        opManager.toggleSign();
    }
}
