
class Solution {
    public int largestRectangleArea(int[] heights) {

        if (heights.length == 1) {
            return heights[0];
        }

        Stack<Integer> st = new Stack<>();

        int[] aux = heights.clone(); // new int[heights.length];

        for (int i = 1; i < heights.length; i++) {
            if (heights[i - 1] > heights[i]) {
                while (!st.empty() && heights[i] < heights[st.peek()]) {
                    int idx = st.pop();
                    aux[idx] += ((i - idx) - 1) * heights[idx];
                }
            } else {
                st.push(i - 1);
            }
        }

        while (!st.empty()) {
            int idx = st.pop();
            aux[idx] += ((heights.length - idx) - 1) * heights[idx];
        }

        for (int i = heights.length - 2; i >= 0; i--) {
            if (heights[i + 1] > heights[i]) {
                while (!st.empty() && heights[i] < heights[st.peek()]) {
                    int idx = st.pop();
                    aux[idx] += ((idx - i) - 1) * heights[idx];
                }
            } else {
                st.push(i + 1);
            }
        }

        while (!st.empty()) {
            int idx = st.pop();
            aux[idx] += ((idx + 1) - 1) * heights[idx];
        }

        int max = aux[0];
        for (int i = 1; i < aux.length; i++) {
            if (aux[i] > max)
                max = aux[i];
        }

        // System.gc();

        return max;
    }
}
