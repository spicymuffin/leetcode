class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 1) {
            return 0;
        }

        int buyptr = 0;
        int sellptr = 0;
        int scanptr = 1;

        int minptr = 0;

        while (scanptr < prices.length) {
            if (prices[scanptr] < prices[minptr]) {
                minptr = scanptr;
            } else if (scanptr > minptr && prices[scanptr] > prices[minptr]
                    && (prices[scanptr] - prices[minptr] > prices[sellptr] - prices[buyptr])) {
                buyptr = minptr;
                sellptr = scanptr;
            }

            // System.out.printf("%d - %d\n", buyptr, sellptr);

            scanptr++;
        }

        // System.out.printf("%d - %d\n", buyptr, sellptr);

        return prices[sellptr] - prices[buyptr];
    }
}
