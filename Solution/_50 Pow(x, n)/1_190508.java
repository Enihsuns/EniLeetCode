/**
 * Runtime: 1 ms, faster than 72.35% of Java online submissions for Pow(x, n).
 * Memory Usage: 32.8 MB, less than 100.00% of Java online submissions for Pow(x, n).
 */
class Solution {
    public double myPow(double x, int n) {
        long N = n;
        if(N < 0) {
            x = 1/x;
            N = -N;
        }
        return fastPow(x, N);
    }
    
    public double fastPow(double x, long N) {
        if(N == 0) {
            return 1;
        }
        double half = fastPow(x, N/2);
        if(N % 2 == 1) {    // odd
            return half * half * x;
        }
        else {      // even
            return half * half;
        }
    }
}