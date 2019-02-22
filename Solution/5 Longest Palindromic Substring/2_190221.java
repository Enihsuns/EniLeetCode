/**
 * Runtime: 23 ms, faster than 60.25% of Java online submissions for Longest Palindromic Substring.
 * Memory Usage: 37.6 MB, less than 89.79% of Java online submissions for Longest Palindromic Substring.
 */
class Solution {
    
    public String longestPalindrome(String s) {
        // Check if s is an empty string.
        if(s.length() <= 0) return s;
        
        // Record the answer index
        int ansBegin = 0, ansLen = 0;
        
        // Iterate the possible center of palindromic string
        for(int i = 0; i < s.length(); i++) {
            // center s[i]
            int l = i, r = i;
            while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
                l--; r++;
            }
            if(r - l - 1 > ansLen) {
                ansBegin = l + 1;
                ansLen = r - l - 1;
            }
            
            // center between s[i] and s[i + 1]
            l = i;
            r = i + 1;
            if(r >= s.length()) continue;   // Check boundary
            while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
                l--; r++;
            }
            if(r - l - 1 > ansLen) {
                ansBegin = l + 1;
                ansLen = r - l - 1;
            }
        }
        
        String ans = s.substring(ansBegin, ansBegin + ansLen);
        return ans;
    }
}