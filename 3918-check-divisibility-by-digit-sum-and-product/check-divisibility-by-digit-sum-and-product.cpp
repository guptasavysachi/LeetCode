class Solution {
public:
    bool checkDivisibility(int n) {
        return n%(digitSum(n)+prod(n))==0;
    }
int digitSum(int num){
        int res=0;
        while(num>0){
            res+=num%10;
            num/=10;
        }
        return res;
    }
    int prod(int num){
        int product=1;
        while(num>0){
            product*=num%10;
            num/=10;
        }
        return product;
    }

};