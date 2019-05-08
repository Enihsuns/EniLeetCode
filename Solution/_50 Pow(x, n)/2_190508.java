/**
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Pow(x, n).
 * Memory Usage: 32.9 MB, less than 100.00% of Java online submissions for Pow(x, n).
 */
class Solution {
    public double myPow(double x, int n) {
        if(n < 0) {
            return 1/x * myPow(1/x, -(n + 1));
        }
        else if (n == 0) {
            return 1;
        }
        
        double half = myPow(x, n/2);
        if(n % 2 == 1) {    // odd
            return half * half * x;
        }
        else {      // even
            return half * half;
        }
    }
}