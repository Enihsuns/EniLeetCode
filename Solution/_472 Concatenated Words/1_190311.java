/**
 * Runtime: 3873 ms, faster than 5.05% of Java online submissions for Concatenated Words.
 * Memory Usage: 49.8 MB, less than 87.50% of Java online submissions for Concatenated Words.
 */
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Set<String> existWords = new HashSet<>();
        
        // Sort words by their length
        Arrays.sort(words, 0, words.length, (o1, o2) -> (o1.length() - o2.length()));
        
        List<String> ans = new ArrayList<String>();
        for(String word: words) {
            if(word.length() == 0) continue;
            
            boolean flag = DFS(word, existWords);
            if(flag) {
                ans.add(word);
            }
            else {
                existWords.add(word);
            }
        }
        
        return ans;
    }
    
    private boolean DFS(String word, Set<String> existWords) {
        if(word.length() == 0) return true;
        
        for(String existWord: existWords) {
            if(word.startsWith(existWord)) {
                boolean flag = DFS(word.substring(existWord.length()), existWords);
                if(flag) return true;
            }
        }
            
        return false;
    }
}