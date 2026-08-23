class Solution {
    public boolean sumGame(String num) {
        int[] sum={0,0}, q={0,0};
        int n=num.length();
        for(int i=0;i<n;i++){
            int j=i/(n>>1);
            if(num.charAt(i)=='?')
                q[j]++;
            else
                sum[j]+=num.charAt(i)-'0';
        }
        return ((q[0]+q[1])%2==1) || ((sum[0]-sum[1])<<1) != (q[1]-q[0])*9;
    }
}