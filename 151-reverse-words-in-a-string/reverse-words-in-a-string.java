class Solution {
    public String reverseWords(String s){
        // Step 1: Remove extra spaces
        StringBuilder sb=new StringBuilder();
        int i=0,n=s.length();
        while (i<n){
            while (i<n && s.charAt(i)==' ')
                i++;
            while (i<n && s.charAt(i)!=' ') {
                sb.append(s.charAt(i));
                i++;
            }
            while (i<n && s.charAt(i)==' ')
                i++;
            if (i<n)
                sb.append(' ');
        }
        // Step 2: Reverse whole string
        reverse(sb,0,sb.length()-1);
        // Step 3: Reverse each word
        int start=0;
        for (i=0;i<=sb.length();i++){
            if (i==sb.length() || sb.charAt(i)==' ') {
                reverse(sb,start,i-1);
                start=i+1;
            }
        }
        return sb.toString();
    }

    void reverse(StringBuilder sb,int l,int r){
        while (l<r){
            char temp=sb.charAt(l);
            sb.setCharAt(l,sb.charAt(r));
            sb.setCharAt(r,temp);
            l++;
            r--;
        }
    }
}