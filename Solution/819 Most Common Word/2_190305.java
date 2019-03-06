/**
 * Runtime: 17 ms, faster than 71.48% of Java online submissions for Most Common Word.
 * Memory Usage: 37.8 MB, less than 42.42% of Java online submissions for Most Common Word.
 */
class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        // Set banned words set
        Set<String> bannedWords = new HashSet<String>();
        
        for(String word: banned) {
            bannedWords.add(word);
        }
        
        // Use map to save words and their count
        Map<String, Integer> wordCnt = new HashMap<>();
        
        // Transfer paragraph to lower case
        paragraph = paragraph.toLowerCase();
        
        // Parse paragraph
        String[] words = paragraph.split("[^a-z]+");
        
        int maxCnt = 0;
        String ans = "";
        for(String word: words) {
            // Check if in banned words set
            if(bannedWords.contains(word)) {
                continue;
            }
            
            // Put into wordCnt map
            if(wordCnt.containsKey(word)) {
                wordCnt.put(word, wordCnt.get(word) + 1);
            }
            else {
                wordCnt.put(word, 1);
            }
            
            if(wordCnt.get(word) > maxCnt) {
                maxCnt = wordCnt.get(word);
                ans = word;
            }
        }
        
        return ans;
        
    }
}