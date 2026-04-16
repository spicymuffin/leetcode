class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            List<Integer> l = map.getOrDefault(nums[i], null);
            if (l == null) {
                l = new ArrayList<Integer>();
                map.put(nums[i], l);
            }
            l.add(i);
            // System.out.println(l.toString());
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i];
            int key = nums[idx];

            List<Integer> list = map.get(key);

            if (list.size() == 1) {
                ans.add(-1);
                continue;
            }

            int l = 0;
            int r = list.size() - 1;

            while (l < r) {
                int mid = l + (r - l) / 2;

                if (idx > list.get(mid)) {
                    l = mid + 1;
                } else if (idx < list.get(mid)) {
                    r = mid - 1;
                } else {
                    l = mid;
                    break;
                }
            }

            int dstl, dstr;

            if (l == list.size() - 1) {
                dstr = nums.length - 1 - list.get(l) + list.get(0) + 1;
                dstl = list.get(l) - list.get(l - 1);
            } else if (l == 0) {
                dstr = list.get(l + 1) - list.get(l);
                dstl = nums.length - 1 - list.get(list.size() - 1) + list.get(0) + 1;
            } else {
                dstr = list.get(l + 1) - list.get(l);
                dstl = list.get(l) - list.get(l - 1);
            }

            ans.add(Math.min(dstl, dstr));
        }

        return ans;
    }
}
