class Solution {

    private int dst(int s, int d, int length) {
        if (s == d)
            return 0;
        int max = s > d ? s : d;
        int min = s < d ? s : d;

        return Math.min(max - min, length - max + min);
    }

    public int closestTarget(String[] words, String target, int startIndex) {
        int mindst = Integer.MAX_VALUE;
        
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(target)) {
                int d = dst(i, startIndex, words.length);
                if (d < mindst) {
                    mindst = d;
                }
            }
        }

        return mindst == Integer.MAX_VALUE ? -1 : mindst;
    }
}
