/**
 * Runtime: 376 ms, faster than 24.92% of Java online submissions for Cut Off Trees for Golf Event.
 * Memory Usage: 46.3 MB, less than 75.57% of Java online submissions for Cut Off Trees for Golf Event.
 */
class Solution {
    
    public int cutOffTree(List<List<Integer>> forest) {
        // Map size
        int m, n;
        if((m = forest.size()) == 0 || (n = forest.get(0).size()) == 0) {
            return 0;
        } 
        
        // Get tree order.
        PriorityQueue<Integer> q = new PriorityQueue<Integer>();
        
        for(List<Integer> row: forest) {
            for(Integer tree: row) {
                if(tree > 1) {
                    q.add(tree);    
                }
            }
        }
        
        // BFS
        int ans = 0;
        int[] curPos = {0, 0};
        while(!q.isEmpty()) {
            int curTree = q.poll();
            int curStep = BFS(forest, m, n, curTree, curPos);
            if(curStep == -1) {
                return -1;
            }
            ans += curStep;
            
            //System.out.println("curTree: " + curTree + " curPos: " + Arrays.toString(curPos));
        }
        
        return ans;
    }
    
    private static int[][] moves = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    
    private int BFS(List<List<Integer>> forest, int m, int n, int tree, int[] startPos) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        int[][] steps = new int[m][n];
        
        q.add(startPos);
        visited[startPos[0]][startPos[1]] = true;
        
        while(!q.isEmpty()) {
            int[] pos = q.poll();
            int step = steps[pos[0]][pos[1]];
            
            // Check if find target tree
            if(forest.get(pos[0]).get(pos[1]) == tree) {
            // Remember to update startPos!
                startPos[0] = pos[0];
                startPos[1] = pos[1];
                        
                return step;
            }
            
            for(int[] move: moves) {
                int[] nextPos = {pos[0] + move[0], pos[1] + move[1]};
                if(nextPos[0] >= 0 && nextPos[0] < m && nextPos[1] >= 0 && nextPos[1] < n
                   && visited[nextPos[0]][nextPos[1]] == false 
                   && forest.get(nextPos[0]).get(nextPos[1]) != 0) {
                    // Add into queue
                    q.add(nextPos);
                    visited[nextPos[0]][nextPos[1]] = true;
                    steps[nextPos[0]][nextPos[1]] = step + 1;
                }
            }
        }
        
        return -1;
    }
}