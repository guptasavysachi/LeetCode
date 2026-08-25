class Solution {
public:
    int missingMultiple(vector<int>& nums, int k) {
        set<int> st(nums.begin(), nums.end());
        int multiple=k;
        while(st.contains(multiple))
            multiple+=k;
        return multiple;
    }
};