class Solution {
    public long maxMatrixSum(int[][] matrix) {
        long totalSum = 0;
        int minAbsVal = Integer.MAX_VALUE;
        int negCount = 0;
        for (int[] row : matrix) {
            for (int value : row) {
                totalSum += Math.abs(value);
                if(value < 0) {
                    negCount++;
                }
                minAbsVal = Math.min(minAbsVal, Math.abs(value));
            }
        }
        if (negCount % 2 != 0) {
            totalSum -= 2 * minAbsVal;
        }
        return totalSum;
    }
}