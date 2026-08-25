class Solution {
public:
    int maxProfit(vector<int>& prices) {
        int fb=INT_MAX;
        int sb=INT_MAX;
        int fs=0, ss=0;
        for(int price:prices){
            fb=min(price,fb);
            fs=max(fs,price-fb);
            sb=min(price-fs,sb);
            ss=max(ss,price-sb);
        }
        return ss;
    }
};