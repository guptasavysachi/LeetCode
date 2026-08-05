class Solution {
    public int lengthOfLastWord(String s) {
        s=s.trim();
        int k=0;
        for(int i=s.length()-1;i>=0;k++,i--)
            if(s.charAt(i)==' ')
                break;
        return k;
    }
}