class Solution {
    public boolean uniformArray(int[] nums1) {
        boolean result = false;
        int element = nums1[0];
        for (int num : nums1) {
            if (element > num) {
                element=num;
            }
            if ((num & 1) == 1) {
                result = true;
            }
        }
        if ((element & 1) == 1) {
            return true;
        }
        return !result;
    }
}