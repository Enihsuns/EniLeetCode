/**
 * 19-2-21 10:42
 * Runtime: 30 ms, faster than 92.08% of Java online submissions for Word Ladder.
 * Memory Usage: 47.4 MB, less than 11.59% of Java online submissions for Word Ladder.
 */
class Solution {
    Map<String, Set<String>> stateMap;
    
    private List<String> getIntermediateState(String word) {
        List<String> intermediateState = new ArrayList<>();
        for(int i = 0; i < word.length(); i++) {
            // Get intermediate state
            StringBuilder inWordBuilder = new StringBuilder(word);
            inWordBuilder.setCharAt(i, '*');
            intermediateState.add(inWordBuilder.toString());
        }
        return intermediateState;
    }
    
    private void addIntoMap(String word) {
        List<String> intermediateState = getIntermediateState(word);
        for(String inWord: intermediateState) {
            // Add into stateMap
            if(stateMap.containsKey(inWord)) {
                stateMap.get(inWord).add(word);
            }
            else {
                Set<String> inWordList = new HashSet<String>();
                inWordList.add(word);
                stateMap.put(inWord, inWordList);
            }
        }
    }
    
        
    private int BFS(Queue<String> q, Set<String> visited, Set<String> refVisited, Map<String, Integer> level) {
        String curWord = q.poll();
        
        // Get intermediate states
        List<String> inStates = getIntermediateState(curWord);
        for(String inState: inStates) {
            Set<String> neighborWords = stateMap.get(inState);
            for(String nWord: neighborWords) {
                // Check if nWord is in visited.
                if(visited.contains(nWord)) {
                    continue;
                }
                
                // Check if nWord is in refVisited.
                if(refVisited.contains(nWord)) {
                    System.out.println(level);
                    return level.get(nWord) + level.get(curWord);
                }
                
                // Add nWord into q, visited and level.
                visited.add(nWord);
                q.add(nWord);
                level.put(nWord, level.get(curWord) + 1);
            }
        }
        return -1;
    }
    
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        
        // Begin equals end
        if(beginWord.equals(endWord)) {
            return 1;
        }
        
        // End word is not in wordList
        boolean valid = false;
        for(String word: wordList) {
            if(word.equals(endWord)) {
                valid = true;
                break;
            }
        }
        if(!valid) {
            return 0;
        }
        
        // Build intermediate state map
        stateMap = new HashMap<>();
        
        addIntoMap(beginWord);
        for(String word: wordList) {
            addIntoMap(word);
        }
        
        // Bidirectional BFS
        Queue<String> beginQueue = new LinkedList<>(), endQueue = new LinkedList<>();
        Set<String> beginVisited = new HashSet<>(), endVisited = new HashSet<>();
        Map<String, Integer> level = new HashMap<>();
        
        beginQueue.add(beginWord);
        beginVisited.add(beginWord);
        endQueue.add(endWord);
        endVisited.add(endWord);
        level.put(beginWord, 1);
        level.put(endWord, 1);
        
        while(!beginQueue.isEmpty() && !endQueue.isEmpty()) {
            int ans = -1;
            // Begin word BFS
            ans = BFS(beginQueue, beginVisited, endVisited, level);
            if(ans != -1) {
                return ans;
            }
            
            // End word BFS
            ans = BFS(endQueue, endVisited, beginVisited, level);
            if(ans != -1) {
                return ans;
            }
        }
        return 0;
    }
}