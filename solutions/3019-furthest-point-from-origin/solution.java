class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        int[] lr = new int[2];
        int wildcards = 0;
        for (int i = 0; i < moves.length(); i++) {
            if (moves.charAt(i) == 'L') {
                lr[0]++;
            } else if (moves.charAt(i) == 'R') {
                lr[1]++;
            } else {
                wildcards++;
            }
        }

        int delta = -lr[0] + lr[1];
        if (delta < 0) {
            return -delta + wildcards;
        } else {
            return delta + wildcards;
        }
    }
}
