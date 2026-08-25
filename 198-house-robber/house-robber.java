class Solution {
    public int rob(int[] nums) {
        int prev2 = 0, prev1 = 0;
        for(int num:nums){
            int take=num+prev2;
            int not_take=prev1;
            int current=Math.max(take,not_take);
            prev2=prev1;
            prev1=current;
        }
        return prev1;
    }
}