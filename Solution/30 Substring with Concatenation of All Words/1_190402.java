/**
 * Runtime: 41 ms, faster than 70.20% of Java online submissions for Substring with Concatenation of All Words.
 * Memory Usage: 37.7 MB, less than 83.53% of Java online submissions for Substring with Concatenation of All Words.
 */
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        if(s.length() == 0 || words.length == 0) {
            return ans;
        }
        
        // Build word map
        Map<String, Integer> wordMap = new HashMap<>();
        for(String word: words) {
            wordMap.merge(word, 1, (oldVal, newVal) -> oldVal + 1);
        }
        
        // Get word length
        int wordLen = words[0].length();
        
        // Iterate all possible "start" position
        for(int i = 0; i < wordLen; i++) {
            Map<String, Integer> curMap = new HashMap<>();
            
            // Sliding window
            int start = i, end = i, wordCnt = words.length;
            while(end + wordLen <= s.length()) {
                // Judge whether end is a word
                String curWord = s.substring(end, end + wordLen);
                int requiredCnt = wordMap.getOrDefault(curWord, 0);
                
                //System.out.println("i: " + i + " end: " + end + " wordCnt: " + wordCnt + " curWord: " + curWord + " requiredCnt: " + requiredCnt);
                
                // Does not contain curWord.
                if(requiredCnt == 0) {
                    start = end = end + wordLen;
                    wordCnt = words.length;
                    curMap.clear();
                    continue;
                }
                
                // Add curWord into map
                curMap.merge(curWord, 1, (oldVal, newVal)->oldVal + 1);
                wordCnt--;
                
                // Too many curWord
                if(curMap.get(curWord) > requiredCnt) {
                    // Move start until curWord's count is reduced.
                    while(start < s.length()) {
                        String rmvWord = s.substring(start, start + wordLen);
                        curMap.merge(rmvWord, 0, (oldVal, newVal) -> oldVal - 1);
                        start += wordLen;
                        wordCnt++;
                        
                        if(rmvWord.equals(curWord)) {
                            break;
                        }
                    }
                }
                
                if(wordCnt == 0) {
                    ans.add(start);
                }
                
                end += wordLen;
            }
        }
        return ans;
    }
}