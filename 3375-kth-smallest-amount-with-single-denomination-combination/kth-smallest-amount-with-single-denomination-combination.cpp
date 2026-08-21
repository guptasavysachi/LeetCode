class Solution {
public:
    long long findKthSmallest(vector<int>& coins, int k) {
        int n = coins.size();
        sort(coins.begin(), coins.end());
        vector<int> a;
        for (int x : coins) {
            bool redundant = false;
            for (int y : a) {
                if (x % y == 0) {
                    redundant = true;
                    break;
                }
            }
            if (!redundant) {
                a.push_back(x);
            }
        }
        coins = a;
        n = coins.size();
        auto gcdll = [](long long a, long long b) {
            while (b) {
                long long temp = a % b;
                a = b;
                b = temp;
            }
            return a;
        };
        auto count = [&](long long x) -> long long {
            long long total = 0;
            for (int mask = 1; mask < (1 << n); mask++) {
                long long lcm = 1;
                int bits = 0;
                bool valid = true;
                for (int i = 0; i < n; i++) {
                    if (mask & (1 << i)) {
                        bits++;
                        long long g = gcdll(lcm, coins[i]);
                        if (lcm > x / (coins[i] / g)) {
                            valid = false;
                            break;
                        }
                        lcm = lcm / g * coins[i];
                    }
                }
                if (!valid || lcm > x)
                    continue;
                if (bits % 2 == 1)
                    total += x / lcm;
                else
                    total -= x / lcm;
            }
            return total;
        };
        long long low = 1;
        long long high = 1LL * coins[0] * k;
        while (low < high) {
            long long mid = low + (high - low) / 2;
            if (count(mid) >= k)
                high = mid;
            else
                low = mid + 1;
        }
        return low;
    }
};