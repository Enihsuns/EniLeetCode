/**
 * 19-2-21
 * Runtime: 28 ms, faster than 56.08% of Java online submissions for Longest Palindromic Substring.
 * Memory Usage: 37.8 MB, less than 84.49% of Java online submissions for Longest Palindromic Substring.
 */
class Solution {
    
    public String longestPalindrome(String s) {
        // Check if s is an empty string.
        if(s.length() == 0) return s;
        
        // Record the answer index
        int ansBegin = 0, ansEnd = -1;
        
        // Iterate the possible center of palindromic string
        for(int i = 0; i < s.length(); i++) {
            // center s[i]
            int l = i, r = i;
            while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
                l--; r++;
            }
            l++; r--;   // Go back
            if(r - l > ansEnd - ansBegin) {
                ansBegin = l;
                ansEnd = r;
            }
            
            // center between s[i] and s[i + 1]
            l = i;
            r = i + 1;
            if(r >= s.length()) continue;   // Check boundary
            while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
                l--; r++;
            }
            l++; r--;   // Go back
            if(r - l > ansEnd - ansBegin) {
                ansBegin = l;
                ansEnd = r;
            }
        }
        
        String ans = s.substring(ansBegin, ansEnd + 1);
        return ans;
    }
}