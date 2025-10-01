package Calc;


class AddOperation implements Operation {
    @Override
    public String execute(float prev, float curr) {
        float v = prev + curr;
        return (v % 1 == 0) ? Integer.toString((int) v) : Float.toString(v);
    }
}

class SubtractOperation implements Operation {
    @Override
    public String execute(float prev, float curr) {
        float v = prev - curr;
        return (v % 1 == 0) ? Integer.toString((int) v) : Float.toString(v);
    }
}

class MultiplyOperation implements Operation {
    @Override
    public String execute(float prev, float curr) {
        float v = prev * curr;
        return (v % 1 == 0) ? Integer.toString((int) v) : Float.toString(v);
    }
}

class DivideOperation implements Operation {
    @Override
    public String execute(float prev, float curr) {
        if (curr == 0) return "Error";
        float v = prev / curr;
        return (v % 1 == 0) ? Integer.toString((int) v) : Float.toString(v);
    }
}
