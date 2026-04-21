class Solution {
    // private int uf_root(int[] uf, int idx) {
    //     while (uf[idx] != idx) {
    //         idx = uf[idx];
    //     }
    //     return idx;
    // }

    private int uf_root(int[] uf, int idx) {
        if (uf[idx] != idx) {
            uf[idx] = uf_root(uf, uf[idx]);
        }
        return uf[idx];
    }

    private void uf_merge(int[] uf, int a, int b) {
        int ra = uf_root(uf, a);
        int rb = uf_root(uf, b);
        if (ra != rb) {
            uf[rb] = ra;
        }
    }

    public int minimumHammingDistance(int[] source, int[] target, int[][] swaps) {
        int n = source.length;
        int[] uf = new int[n];

        for (int i = 0; i < n; i++) {
            uf[i] = i;
        }

        for (int[] pair : swaps) {
            uf_merge(uf, pair[0], pair[1]);
            // System.out.println(Arrays.toString(uf));
        }

        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            Map<Integer, Integer> sub = map.computeIfAbsent(uf_root(uf, uf[i]), k -> new HashMap<>());
            Integer freq = sub.computeIfAbsent(source[i], k -> 0);
            sub.put(source[i], freq + 1);
            // System.out.printf("root=%d, val=%d, freq=%d\n", uf_root(uf, uf[i]), source[i], freq);
        }

        int hdst = n;

        for (int i = 0; i < n; i++) {
            Map<Integer, Integer> sub = map.get(uf_root(uf, uf[i]));
            // System.out.printf("scanning root=%d\n", uf_root(uf, uf[i]));
            Integer freq = sub.getOrDefault(target[i], null);
            // System.out.printf("freq=%d\n", freq == null ? -1 : freq);
            if (freq != null && freq > 0) {
                // System.out.printf("freq=%d, val=%d, hdst=%d\n", freq, target[i], hdst);
                sub.put(target[i], freq - 1);
                hdst--;
            }
        }

        return hdst;
    }
}
