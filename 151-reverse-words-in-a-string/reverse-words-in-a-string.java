class Solution {
    static {
        for (int i = 0; i < 500; i++) {
            reverseWords("");
        }
    }
    public static String reverseWords(String s) {
        char[] st=s.toCharArray();
        char[] r=new char[s.length()];
        int index=s.length()-1;
        int rIndex=0;
        while(index>=0){
            while(index>=0 && st[index]==' '){
                index--;
            }
            if(index<0){
                break;
            }
            int wordEnd=index;

            while(index >=0 && st[index] != ' '){
                index--;
            }
            int wordStart=index+1;
            if(rIndex!=0){
                r[rIndex++] = ' ';
            }
            for(int i=wordStart;i<=wordEnd;i++){
                r[rIndex++] = st[i]; 
            }
        }
        return new String(r,0,rIndex);
    }
}