class Solution {
    public long gridGame(int[][] grid) {
        int n = grid[0].length;
        long firstRowSum = 0;
        long SecondRowSum = 0;
        for (int i = 0; i < n; i++) {
            firstRowSum += grid[0][i];
        }
        long minimumSum = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            firstRowSum -= grid[0][i];                 
            minimumSum = Math.min(minimumSum, Math.max(firstRowSum, SecondRowSum));
            SecondRowSum += grid[1][i];              
        }
        return minimumSum;
    }
}