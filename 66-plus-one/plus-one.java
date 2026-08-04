class Solution {
    public int[] plusOne(int[] digits) {
        int n=digits.length-1;
        while(n>=0){
            if(digits[n]<9){
                digits[n]++;
                return digits;
            }
            digits[n]=0;
            n--;
        }
        int[] num=new int[digits.length+1];
        num[0]=1;
        return num;
    }
}