class Solution {
    public int[] validSequence(String word1, String word2) {
        int n=word1.length();
        int m=word2.length();
        int[] suffix=new int[n+1];
        suffix[n]=0;
        for(int i=n-1;i>=0;i--){
            suffix[i]=suffix[i+1];
            if(suffix[i+1]<m && word1.charAt(i)==word2.charAt(m-1-suffix[i+1]))
                suffix[i]++;
        }
        int[] ans=new int[m];
        int pos=0;
        boolean changed=false;
        for (int i=0;i<n && pos<m;i++) {
            if(word1.charAt(i)==word2.charAt(pos))
                ans[pos++]=i;
            else if(!changed && pos<m && suffix[i+1]>=m-pos-1){
                ans[pos++]=i;
                changed=true;
            }
        }
        return pos==m ? ans : new int[0];
    }
}