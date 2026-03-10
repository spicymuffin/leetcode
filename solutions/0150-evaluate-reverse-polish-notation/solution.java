class Solution {

    interface IntOperation {
        int operate(int a, int b);
    }

    IntOperation add = (a, b) -> a + b;
    IntOperation sub = (a, b) -> a - b;
    IntOperation mul = (a, b) -> a * b;
    IntOperation div = (a, b) -> a / b;

    private IntOperation isOperator(String input) {
        if (input.length() != 1)
            return null;

        if (input.charAt(0) == '+') {
            return add;
        } else if (input.charAt(0) == '-') {
            return sub;
        } else if (input.charAt(0) == '*') {
            return mul;
        } else if (input.charAt(0) == '/') {
            return div;
        } else {
            return null;
        }
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            IntOperation operator = isOperator(tokens[i]);

            if (operator != null) {
                int operand2 = st.pop();
                int operand1 = st.pop();

                st.push(operator.operate(operand1, operand2));
            } else {
                st.push(Integer.valueOf(tokens[i]));
            }
        }

        return st.pop();
    }
}
