/**
 * Runtime: 10 ms, faster than 77.01% of Java online submissions for Prison Cells After N Days.
 * Memory Usage: 40.3 MB, less than 8.91% of Java online submissions for Prison Cells After N Days.
 */
class Solution {
    public int[] prisonAfterNDays(int[] cells, int N) {
        Map<String, Integer> cellsMap = new HashMap<>();
        
        int cellsLen = cells.length;
        
        while(N > 0) {
            String str = Arrays.toString(cells);
            System.out.println(" N: " + N +"\tCells: " + Arrays.toString(cells));
            cellsMap.put(str, N--);
            
            int[] nextCells = new int[cellsLen];
            for(int j = 1; j < cellsLen - 1; j++) {
                nextCells[j] = cells[j - 1] == cells[j + 1]? 1 : 0;
            }
            
            cells = nextCells;
            String nextStr = Arrays.toString(cells);
            if(cellsMap.containsKey(nextStr)) {
                N %= cellsMap.get(nextStr) - N;
            }
        }
        
        return cells;
    }
}