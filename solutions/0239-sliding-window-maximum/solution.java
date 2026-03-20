class Solution {
    class Entry {
        public int idx;
        public int val;

        public Entry(int _idx, int _val) {
            idx = _idx;
            val = _val;
        }

        @Override
        public String toString() {
            return "(" + idx + ", " + val + ")";
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        Deque<Entry> d = new ArrayDeque<>();

        int max = -1;
        int maxidx = -1;
        for (int i = 0; i < k; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxidx = i;
            }
        }

        int target = max;
        for (int i = maxidx + 1; i < k; i++) {
            if (nums[i] <= target) {
                target = nums[i];
                d.addLast(new Entry(i, nums[i]));
            } else {
                while (!d.isEmpty() && nums[i] > d.getLast().val) {
                    // System.out.printf("%d > %d\n", nums[i], d.getLast().val);
                    // System.out.println("cleared: " + d.removeLast());
                    d.removeLast();
                }
                target = nums[i];
                d.addLast(new Entry(i, nums[i]));
            }
        }

        ans[0] = max;

        d.addFirst(new Entry(maxidx, max));

        for (int i = k; i < nums.length; i++) {
            int windowleft = i - k + 1;
            // System.out.printf("loopinit: %d<->%d, nums[i]=%d\n", windowleft, i, nums[i]);
            // System.out.println("d: " + d);

            while (!d.isEmpty() && d.getFirst().idx < windowleft) {
                d.removeFirst();
            }

            while (!d.isEmpty() && nums[i] > d.getLast().val) {
                // System.out.printf("%d > %d\n", nums[i], d.getLast().val);
                // System.out.println("cleared: " + d.removeLast());
                d.removeLast();
            }

            Entry e;
            if (d.isEmpty()) {
                e = new Entry(i, nums[i]);
                d.addLast(e);
            } else {
                e = d.getFirst();
                d.addLast(new Entry(i, nums[i]));
            }

            // System.out.printf("e.idx=%d, e.val=%d\n", e.idx, e.val);
            // System.out.println("----- loop end ------");

            ans[windowleft] = e.val;
        }

        return ans;
    }
}
