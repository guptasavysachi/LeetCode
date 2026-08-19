class Solution{
    public int maxNumberOfFamilies(int n, int[][] reservedSeats){
        HashMap<Integer,boolean[]> map = new HashMap<>();
        for (int[] seat : reservedSeats){
            int row = seat[0];
            int col = seat[1];
            map.putIfAbsent(row,new boolean[11]);
            map.get(row)[col] = true;
        }
        int ans = (n-map.size())*2;
        for (boolean[] seat : map.values()) {
            boolean left = true;
            boolean middle = true;
            boolean right = true;
            for (int i = 2; i <= 5; i++)
                if (seat[i])
                    left = false;
            for (int i = 4; i <= 7; i++)
                if (seat[i])
                    middle = false;
            for (int i = 6; i <= 9; i++)
                if (seat[i])
                    right = false;
            if (left && right)
                ans += 2;
            else if (left || middle || right)
                ans += 1;
        }
        return ans;
    }
}