import java.util.Arrays;

class Solution {
    // Factors for digits 0..9 in terms of [2, 3, 5, 7]
    private static final int[][] DIGIT_FACTORS = {
        {0, 0, 0, 0}, // 0
        {0, 0, 0, 0}, // 1
        {1, 0, 0, 0}, // 2
        {0, 1, 0, 0}, // 3
        {2, 0, 0, 0}, // 4
        {0, 0, 1, 0}, // 5
        {1, 1, 0, 0}, // 6
        {0, 0, 0, 1}, // 7
        {3, 0, 0, 0}, // 8
        {0, 2, 0, 0}  // 9
    };

    public String smallestNumber(String num, long t) {
        // Step 1: Prime factorize t into 2, 3, 5, 7
        int[] required = new int[4]; // [count(2), count(3), count(5), count(7)]
        long tempT = t;
        int[] primes = {2, 3, 5, 7};
        
        for (int i = 0; i < 4; i++) {
            while (tempT % primes[i] == 0) {
                required[i]++;
                tempT /= primes[i];
            }
        }

        // If t has any prime factors other than 2, 3, 5, 7
        if (tempT > 1) {
            return "-1";
        }

        int n = num.length();

        // Step 2: Compute prefix sums of prime factors for num
        int[][] pref = new int[n + 1][4];
        int firstZeroIndex = n;

        for (int i = 0; i < n; i++) {
            char ch = num.charAt(i);
            if (ch == '0') {
                firstZeroIndex = i;
                break;
            }
            int d = ch - '0';
            for (int k = 0; k < 4; k++) {
                pref[i + 1][k] = pref[i][k] + DIGIT_FACTORS[d][k];
            }
        }

        // Step 3: Check if original num is zero-free and divisible by t
        if (firstZeroIndex == n) {
            int[] remReq = new int[4];
            for (int k = 0; k < 4; k++) {
                remReq[k] = required[k] - pref[n][k];
            }
            if (getMinDigitsCount(remReq) == 0) {
                return num;
            }
        }

        // Step 4: Backtrack from right to left to find smallest larger number of same length
        for (int i = Math.min(n - 1, firstZeroIndex); i >= 0; i--) {
            int currDigit = num.charAt(i) - '0';

            for (int d = currDigit + 1; d <= 9; d++) {
                int[] remReq = new int[4];
                for (int k = 0; k < 4; k++) {
                    remReq[k] = required[k] - pref[i][k] - DIGIT_FACTORS[d][k];
                }

                int minDigitsNeeded = getMinDigitsCount(remReq);
                int remLen = n - 1 - i;

                if (minDigitsNeeded <= remLen) {
                    // Valid configuration found! Construct the result
                    return constructResult(num.substring(0, i) + d, remReq, remLen);
                }
            }
        }

        // Step 5: Expand string length if same length is impossible
        int minDigitsNeeded = getMinDigitsCount(required);
        int targetLen = Math.max(n + 1, minDigitsNeeded);

        return constructResult("", required, targetLen);
    }

    /**
     * Calculates the minimum number of digits needed to fulfill required prime factor counts.
     */
    private int getMinDigitsCount(int[] req) {
        int c2 = Math.max(0, req[0]);
        int c3 = Math.max(0, req[1]);
        int c5 = Math.max(0, req[2]);
        int c7 = Math.max(0, req[3]);

        int count = c7 + c5 + (c3 / 2) + (c2 / 3);
        c3 %= 2;
        c2 %= 3;

        if (c2 == 2 && c3 == 1) count += 2;      // e.g., 2 and 6
        else if (c2 > 0 || c3 > 0) count += 1;  // e.g., 4, 6, 2, or 3

        return count;
    }

    /**
     * Helper to build the final lexicographically smallest valid suffix.
     */
    private String constructResult(String prefix, int[] req, int targetSuffixLen) {
        int c2 = Math.max(0, req[0]);
        int c3 = Math.max(0, req[1]);
        int c5 = Math.max(0, req[2]);
        int c7 = Math.max(0, req[3]);

        // Compress prime factors into largest single digits (greedy for minimal count)
        int num8 = c2 / 3; c2 %= 3;
        int num9 = c3 / 2; c3 %= 2;
        int num7 = c7;
        int num5 = c5;

        int num2 = 0, num3 = 0, num4 = 0, num6 = 0;

        if (c2 == 2 && c3 == 1) {
            num2 = 1;
            num6 = 1;
        } else if (c2 == 2 && c3 == 0) {
            num4 = 1;
        } else if (c2 == 1 && c3 == 1) {
            num6 = 1;
        } else if (c2 == 1 && c3 == 0) {
            num2 = 1;
        } else if (c2 == 0 && c3 == 1) {
            num3 = 1;
        }

        int totalNeeded = num2 + num3 + num4 + num5 + num6 + num7 + num8 + num9;
        int num1 = targetSuffixLen - totalNeeded;

        StringBuilder sb = new StringBuilder(prefix);
        sb.append("1".repeat(Math.max(0, num1)));
        sb.append("2".repeat(num2));
        sb.append("3".repeat(num3));
        sb.append("4".repeat(num4));
        sb.append("5".repeat(num5));
        sb.append("6".repeat(num6));
        sb.append("7".repeat(num7));
        sb.append("8".repeat(num8));
        sb.append("9".repeat(num9));

        return sb.toString();
    }
}