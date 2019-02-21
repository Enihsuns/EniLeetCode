/**
 * 19-2-21 14:40
 * Runtime: 15 ms, faster than 98.89% of Java online submissions for Word Ladder.
 * Memory Usage: 38.8 MB, less than 89.60% of Java online submissions for Word Ladder.
 */
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Check if beginWord equals to endWord
        if(beginWord.equals(endWord)) return 1;
        
        // Generate begin & end set
        Set<String> beginSet = new HashSet<>(), endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        
        // Generate valid word list
        Set<String> validSet = new HashSet<>(wordList);
        validSet.add(beginWord);
        
        // Check if endWord in valid word list
        if(!validSet.contains(endWord)) {
            return 0;
        }
        return BFS(beginSet, endSet, validSet, 2);
    }
    
    private int BFS(Set<String> beginSet, Set<String> endSet, Set<String> validSet, int cnt) {
        // Check if beginSet is empty
        if(beginSet.isEmpty()) return 0;
        
        validSet.removeAll(beginSet);
        
        // Visit all the word in beginSet
        Set<String> nextSet = new HashSet<>();
        for(String word: beginSet) {
            StringBuilder wordBuilder = new StringBuilder(word);
            for(int i = 0; i < word.length(); i++) {
                char originCh = wordBuilder.charAt(i);
                for(char c = 'a'; c <= 'z'; c++) {
                    if(originCh == c) continue;
                    wordBuilder.setCharAt(i, c);
                    String neighborWord = wordBuilder.toString();
                    if(!validSet.contains(neighborWord)) continue;
                    if(endSet.contains(neighborWord)) return cnt;
                    nextSet.add(neighborWord);
                }
                wordBuilder.setCharAt(i, originCh);
            }
        }
        return nextSet.size() > endSet.size()? 
            BFS(endSet, nextSet, validSet, cnt + 1) : BFS(nextSet, endSet, validSet, cnt + 1);
    }
}