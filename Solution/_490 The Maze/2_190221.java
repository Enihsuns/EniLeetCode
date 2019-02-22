/**
 * 19-2-21
 * Runtime: 8 ms, faster than 71.81% of Java online submissions for The Maze.
 * Memory Usage: 48.2 MB, less than 5.33% of Java online submissions for The Maze.
 */
class Solution {
    
    private int[][] moves = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        // Check maze size
        if(maze.length == 0 || maze[0].length == 0) {
            return false;
        }
        
        // Get the maze range
        int M = maze.length, N = maze[0].length;
        
        // BFS
        boolean[][] visited = new boolean[M][N];
        Queue<int[]> q = new LinkedList<>();
        
        q.add(start);
        visited[start[0]][start[1]] = true;
        
        while(!q.isEmpty()) {
            int[] curPos = q.poll();
            
            // Iterate moves
            for(int[] move: moves) {
                // Find where it stops
                int[] nextPos = new int[] { curPos[0] + move[0], curPos[1] + move[1] };
                while(nextPos[0] < M && nextPos[1] < N && nextPos[0] >= 0 && nextPos[1] >= 0
                     && maze[nextPos[0]][nextPos[1]] == 0) {
                    nextPos[0] += move[0];
                    nextPos[1] += move[1];
                }                 
                
                // Go back
                nextPos[0] -= move[0];
                nextPos[1] -= move[1];
                
                // Check whether reach the destination
                if(nextPos[0] == destination[0] && nextPos[1] == destination[1]) {
                    return true;
                }
                
                // Check whether visited
                if(visited[nextPos[0]][nextPos[1]] == true) {
                    continue;
                }
                
                visited[nextPos[0]][nextPos[1]] = true;
                
                // Add into queue
                q.add(nextPos);
            }
        }
        return false;
    }
}