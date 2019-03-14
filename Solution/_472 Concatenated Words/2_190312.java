/**
 * Runtime: 52 ms, faster than 86.78% of Java online submissions for Concatenated Words.
 * Memory Usage: 53.7 MB, less than 32.50% of Java online submissions for Concatenated Words.
 */
class Solution {
	private class TrieNode {		
		boolean isEnd;		
		TrieNode[] children;

		public TrieNode(boolean isEnd) {
			this.isEnd = isEnd;
			children = new TrieNode[26];
		}
	}
    
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode(false);
        
        for(String word: words) {
            // Check if word has enough length
            if(word.length() == 0) continue;
            
            // Insert word into Trie
            TrieNode curNode = root;
            for(int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                if(curNode.children[index] == null) {
                    curNode.children[index] = new TrieNode(false);
                }
                
                curNode = curNode.children[index];
            }
            curNode.isEnd = true;
        }
        
        return root;
    }
    
    public boolean DFS(String word, int curIdx, int count, TrieNode root) {
        if(curIdx == word.length()) return count > 1;
        
        TrieNode curNode = root;
        for(int level = curIdx; level < word.length(); level++) {
            int index = word.charAt(level) - 'a';
            
            if(curNode.children[index] == null) {
                return false;
            }
            
            if(curNode.children[index].isEnd && DFS(word, level + 1, count + 1, root)) return true;
            
            curNode = curNode.children[index];
        }
        return false;
    }
    
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> ans = new ArrayList<>();
        
        if(words.length == 0) return ans;
        
        // Build Trie
        TrieNode root = buildTrie(words);
        
        for(String word: words) {
            if(DFS(word, 0, 0, root)) {
                ans.add(word);
            }
        }
        
        return ans;
    }
}