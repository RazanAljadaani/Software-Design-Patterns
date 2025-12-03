package Calc;

import java.util.ArrayList;
import java.util.List;

// Leaf
class Number implements Operations {
    private float value;

    public Number(float value) {
        this.value = value;
    }

    @Override
    public float evaluate() {
        return value;
    }
}

// Base Composite
abstract class CompositeOperation implements Operations {
    protected List<Operations> children = new ArrayList<>();

    @Override
    public void add(Operations component) {
        children.add(component);
    }

    @Override
    public void remove(Operations component) {
        children.remove(component);
    }

    @Override
    public Operations getChild(int index) {
        return children.get(index);
    }
}

// Addition Composite
class AddOperation extends CompositeOperation {
    public AddOperation(Operations left, Operations right) {
        children.add(left);
        children.add(right);
    }

    @Override
    public float evaluate() {
        float sum = 0;
        for (Operations op : children) sum += op.evaluate();
        return sum;
    }
}

// Subtraction Composite
class SubtractOperation extends CompositeOperation {
    public SubtractOperation(Operations left, Operations right) {
        children.add(left);
        children.add(right);
    }

    @Override
    public float evaluate() {
        float result = children.get(0).evaluate();
        for (int i = 1; i < children.size(); i++)
            result -= children.get(i).evaluate();
        return result;
    }
}

// Multiplication Composite
class MultiplyOperation extends CompositeOperation {
    public MultiplyOperation(Operations left, Operations right) {
        children.add(left);
        children.add(right);
    }

    @Override
    public float evaluate() {
        float result = 1;
        for (Operations op : children) result *= op.evaluate();
        return result;
    }
}

// Division Composite
class DivideOperation extends CompositeOperation {
    public DivideOperation(Operations left, Operations right) {
        children.add(left);
        children.add(right);
    }

    @Override
    public float evaluate() {
        float result = children.get(0).evaluate();
        for (int i = 1; i < children.size(); i++) {
            float divisor = children.get(i).evaluate();
            if (divisor == 0) return Float.NaN;
            result /= divisor;
        }
        return result;
    }
}
