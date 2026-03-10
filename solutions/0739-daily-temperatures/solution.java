class Solution {
    public int[] dailyTemperatures(int[] t) {

        if (t.length == 1) {
            return new int[] { 0 };
        }

        Stack<Integer> st = new Stack<>();

        int[] answer = new int[t.length];

        st.push(0);

        for (int i = 1; i < t.length; i++) {
            while (!st.empty() && t[i] > t[st.peek()]){
                int idx = st.pop();
                answer[idx] = i - idx;
            }
            st.push(i);
        }

        return answer;
    }
}
