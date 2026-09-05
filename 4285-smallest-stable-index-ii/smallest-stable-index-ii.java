class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        int maxFar = Integer.MIN_VALUE;
        int maxAns = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            maxFar = Math.max(maxFar, nums[i]);
            if (i == ans) {
                maxAns = Math.max(maxAns, nums[i]);
            }
            if (nums[i] < maxAns - k) {
                ans = i + 1;
                maxAns = maxFar;
            }
        }
        return ans < n ? ans : -1;
    }
}