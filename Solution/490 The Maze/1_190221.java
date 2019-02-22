class Solution {
    class Point {
        int x;
        int y;
        
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
        
        @Override
        public boolean equals(Object o) {
            if(!(o instanceof Point)) return false;
            return ((Point)o).x == this.x && ((Point)o).y == this.y;
        }
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    private int[][] moves = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        // Check maze size
        if(maze.length == 0 || maze[0].length == 0) {
            return false;
        }
        
        // Get the maze range
        int M = maze.length, N = maze[0].length;
        
        // BFS
        Set<Point> visited = new HashSet<>();
        Queue<Point> q = new LinkedList<>();
        
        q.add(new Point(start[0], start[1]));
        visited.add(new Point(start[0], start[1]));
        while(!q.isEmpty()) {
            Point curPos = q.poll();
            
            // Iterate moves
            for(int i = 0; i < 4; i++) {
                // Find where it stops
                Point nextPos = new Point(curPos.x + moves[i][0], curPos.y + moves[i][1]);
                while(nextPos.x < M && nextPos.y < N && nextPos.x >= 0 && nextPos.y >= 0
                      && maze[nextPos.x][nextPos.y] == 0) {
                    nextPos.x += moves[i][0];
                    nextPos.y += moves[i][1];
                }
                
                // Go back
                nextPos.x -= moves[i][0];
                nextPos.y -= moves[i][1];
                
                // Check whether reach the destination
                if(nextPos.x == destination[0] && nextPos.y == destination[1]) {
                    return true;
                }
                
                // Check whether visited
                if(visited.contains(nextPos)) {
                    continue;
                }
                
                visited.add(nextPos);
                
                // Add into queue
                q.add(nextPos);
            }
        }
        return false;
    }
}