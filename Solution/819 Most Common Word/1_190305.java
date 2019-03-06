/**
 * Runtime: 19 ms, faster than 68.77% of Java online submissions for Most Common Word.
 * Memory Usage: 37.8 MB, less than 42.23% of Java online submissions for Most Common Word.
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
        }
        
        // Iterate all entries in wordCnt to find the most common word
        int maxCnt = 0;
        String ans = "";
        for(Map.Entry<String, Integer> entry: wordCnt.entrySet()) {
            if(entry.getValue() > maxCnt) {
                maxCnt = entry.getValue();
                ans = entry.getKey();
            }
        }
        
        return ans;
    }
}