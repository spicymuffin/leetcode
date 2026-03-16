class Solution {
    public int maxProfit(int[] prices) {
        int buy_price = prices[0];
        int profit = 0;
        for (int p : prices) {
            if (p < buy_price) {
                buy_price = p;
            }
            profit = Math.max(profit, p-buy_price);
        }

        return profit;
    }
}
