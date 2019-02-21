/**
 * 19-2-21 15:02
 * Runtime: 41 ms, faster than 58.98% of Java online submissions for K Closest Points to Origin.
 * Memory Usage: 52.2 MB, less than 95.85% of Java online submissions for K Closest Points to Origin.
 */
class Solution {
    class PointComparator implements Comparator<int[]> {
        private double getDist(int[] p) {
            return Math.pow(p[0], 2) + Math.pow(p[1], 2);
        }
        
        @Override
        public int compare(int[] o1, int[] o2) {
            return Double.compare(getDist(o2), getDist(o1));
        }    
    }
    
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> topKPoints = new PriorityQueue<>(new PointComparator());
        
        for(int[] point: points) {
            
            topKPoints.add(point);
            
            if(topKPoints.size() > K) {
                topKPoints.poll();
            }
        }
        
        // Output
        int[][] ans = new int[K][2];
        
        int i = 0;
        while(!topKPoints.isEmpty()) {
            ans[i++] = topKPoints.poll();
        }
        return ans;
    }
}