class Solution {
    public int search(int[] nums, int target) {
         int pivot=findPivot(nums);
        if(pivot==-1)
            return binarySearch(nums,target,0,nums.length-1);
        if(nums[pivot]==target)
            return pivot;
        if(nums[pivot]>target && nums[0]<=target)
            return binarySearch(nums,target,0,pivot);
        else
            return binarySearch(nums,target,pivot+1,nums.length-1);
    }

    public int binarySearch(int[] nums, int target, int left, int right){
        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target)
                return mid;
            else if (nums[mid]<target)
                left=mid+1;
            else
                right=mid-1;
        }
        return -1;
    }

    public int findPivot(int[] arr){
        int left=0;
        int right=arr.length-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            // 4 cases;
            if(mid < right && arr[mid]>arr[mid+1])
                return mid;
            else if (mid > left && arr[mid]<arr[mid-1])
                return mid-1;
            else if (arr[mid]<=arr[left])
                right=mid-1;
            else
                left=mid+1;
        }
        return -1;
    }
}