/**
 * Runtime: 24 ms, faster than 94.55% of Java online submissions for Word Ladder II.
 * Memory Usage: 42.3 MB, less than 89.75% of Java online submissions for Word Ladder II.
 */
class Solution {
    Map<String, List<String>> transMap;
    String beginWord;
    
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if(beginWord.length() == 0 || endWord.length() == 0 
           || beginWord.length() != endWord.length() || wordList.size() == 0) {
            return new ArrayList<>();
        }
        
        // Build beginSet, targetSet, validSet
        Set<String> beginSet = new HashSet<String>() {{ add(beginWord); }};
        Set<String> targetSet = new HashSet<String>() {{ add(endWord); }};
        Set<String> validSet = new HashSet<String>() {{ addAll(wordList); }};
        
        // Check if targetWord in validSet
        if(!validSet.contains(endWord)) {
            return new ArrayList<>();
        }
        
        // Set beginWord
        this.beginWord = beginWord;
        
        // Build transMap
        transMap = new HashMap<>();
        
        return BFS(beginSet, targetSet, validSet);
    }
    
    private void DFS(String word, List<String> curAns, List<List<String>> list) {
        if(!transMap.containsKey(word)) {
            list.add(new ArrayList<String> (curAns));
            return;
        }
        // Keep recursively visit the transMap until cannot find key
        for(String lstWord: transMap.get(word)) {
            curAns.add(lstWord);
            
            DFS(lstWord, curAns, list);
            
            curAns.remove(curAns.size() - 1);
        }
    }
    
    private List<List<String>> getPath(List<List<String>> p1, List<List<String>> p2) {
        // Reverse p1
        for(List<String> l: p1) {
            Collections.reverse(l);
        }
        
        // Generate ans
        List<List<String>> ans = new ArrayList<>();
        for(List<String> l1: p1) {
            for(List<String> l2: p2) {
                ans.add(new ArrayList<String>(l1){{ addAll(l2); }});
            }
        }
        return ans;
    }
    
    private List<List<String>> BFS(Set<String> beginSet, Set<String> targetSet, Set<String> validSet) {
        List<List<String>> ans = new ArrayList<>();
        // Check if beginSet/targetSet is empty
        if(beginSet.size() == 0 || targetSet.size() == 0) {
            return ans;
        }
        
        // Remove beginSet from validSet
        validSet.removeAll(beginSet);
        
        // Init nextSet
        Set<String> nextSet = new HashSet<>();
        
        // Do transformations
        for(String word: beginSet) {
            char[] chars = word.toCharArray();
            
            for(int i = 0; i < chars.length; i++) {
                char c = chars[i];
                for(char tmp = 'a'; tmp <= 'z'; tmp++) {
                    chars[i] = tmp;
                    String newWord = new String(chars);
                    
                    // Check if newWord in targetSet
                    if(targetSet.contains(newWord)) {
                        // Output the path
                        List<List<String>> p1 = new ArrayList<>();
                        List<List<String>> p2 = new ArrayList<>();
                        DFS(word, new ArrayList<String>() {{ add(word); }}, p1);
                        DFS(newWord, new ArrayList<String>() {{ add(newWord); }}, p2);
                        
                        // Decide which path need to be reversed
                        if(p1.get(0).get(p1.get(0).size() - 1).equals(beginWord)) {
                            ans.addAll(getPath(p1, p2));
                        }
                        else {
                            ans.addAll(getPath(p2, p1));
                        }
                        continue;
                    }
                    
                    // Check if newWord is valid and save into transMap and nextSet
                    if(validSet.contains(newWord)) {
                        if(transMap.containsKey(newWord)) {
                            transMap.get(newWord).add(word);
                        }
                        else {
                            transMap.put(newWord, new ArrayList<String>(){{ add(word); }});    
                        }
                        nextSet.add(newWord);
                    }
                }
                // Restore chars[i]
                chars[i] = c;
            }
        }
        
        //System.out.println("----Iteration----");
        //System.out.println("beginSet: " + beginSet);
        //System.out.println("targetSet: " + targetSet);
        //System.out.println("validSet: " + validSet);
        
        if(ans.size() > 0) {    // Find answer
            System.out.println(transMap);
            return ans;
        }
        else if(nextSet.size() > targetSet.size()) {
            return BFS(targetSet, nextSet, validSet);
        }
        else {
            return BFS(nextSet, targetSet, validSet);    
        }
    }
}