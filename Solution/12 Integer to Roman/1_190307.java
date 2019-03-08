/**
 * Runtime: 34 ms, faster than 94.57% of Java online submissions for Integer to Roman.
 * Memory Usage: 39.4 MB, less than 75.33% of Java online submissions for Integer to Roman.
 */
class Solution {
    public int helper(int num, int divisor, char symbol, char subSymbol, int subVal, StringBuffer ans) {
        int quotient = num/divisor;
        
        for(int i = 0; i < quotient; i++) {
            ans.append(symbol);
        }
        
        num = num%divisor;
        
        // Check if we should do subtraction
        if(num >= subVal) {
            num -= subVal;
            ans.append(subSymbol).append(symbol);
        }
        
        return num;
    }
    
    public String intToRoman(int num) {
        StringBuffer ans = new StringBuffer();
        if(num <= 0 || num > 3999) {
            return ans.toString();
        }
        
        num = helper(num, 1000, 'M', 'C', 900, ans);
        num = helper(num, 500,  'D', 'C', 400, ans);
        num = helper(num, 100,  'C', 'X', 90, ans);
        num = helper(num, 50,   'L', 'X', 40, ans);
        num = helper(num, 10,   'X', 'I', 9, ans);
        num = helper(num, 5,    'V', 'I', 4, ans);
        num = helper(num, 1,    'I', ' ', Integer.MAX_VALUE, ans);
        
        return ans.toString();
    }
}