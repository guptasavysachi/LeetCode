class Solution {
    public int mySqrt(int x) {
        if(x<2)
            return x;
        int left=1;
        int right=x/2;
        int result=0;
        while(left<=right){
            int middle=left+(right-left)/2;
            long square=(long)middle*middle;
            if(square==x)
                return middle;
            if(square<x) {
                result=middle;
                left=middle+1;
            } 
            else
                right=middle-1;
        }
        return result;
    }
}