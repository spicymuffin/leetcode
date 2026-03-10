class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                char p;
                if (stack.empty()) {
                    return false;
                } else {
                    p = stack.pop();
                }
                if (c == ')') {
                    if (p == '(')
                        continue;
                    else
                        return false;
                }
                if (c == '}') {
                    if (p == '{')
                        continue;
                    else
                        return false;
                }
                if (c == ']') {
                    if (p == '[')
                        continue;
                    else
                        return false;
                }

            }
        }

        return stack.empty();
    }
}
