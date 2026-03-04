class Solution {

    private boolean dfs(boolean[] visited, boolean[] instack, List<List<Integer>> graph, Deque<Integer> answer,
            int node) {
        visited[node] = true;
        instack[node] = true;
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                if (!dfs(visited, instack, graph, answer, neighbor)) {
                    return false;
                }
            } else if (instack[neighbor]) {
                return false;
            }
        }
        answer.push(node);
        instack[node] = false;
        return true;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>(numCourses);

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < prerequisites.length; i++) {
            graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        boolean[] visited = new boolean[numCourses];
        boolean[] instack = new boolean[numCourses];
        Deque<Integer> answer = new ArrayDeque<>();

        for (int i = 0; i < graph.size(); i++) {
            if (!visited[i]) {
                if (!dfs(visited, instack, graph, answer, i)) {
                    return new int[] {};
                }
            }
        }

        int[] answer_arr = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            answer_arr[i] = answer.pop();
        }

        return answer_arr;
    }
}
