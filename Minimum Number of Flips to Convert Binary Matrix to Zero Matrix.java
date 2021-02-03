class Solution {
    int[][] dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int m;
    int n;
    public int minFlips(int[][] mat) {
        m = mat.length;
        n = mat[0].length;
        Queue<Integer> queue = new LinkedList<>();
        int init = createBitVec(mat);
        queue.offer(init);
        Set<Integer> visited = new HashSet<>();
        visited.add(init);
        int distance = 0;
        
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int curBoard = queue.poll();
                if(curBoard == 0){
                    return distance;
                }
                for(int x = 0; x < m; x++){
                    for(int y = 0; y < n; y++){
                        int nextBoard = flip(curBoard, x, y);
                        if(!visited.contains(nextBoard)){
                            visited.add(nextBoard);
                            queue.offer(nextBoard);
                        }
                    }
                }
            }
            distance++;
        }
        
        return -1;
        
    }
    
    private int flip(int mat, int x, int y){
        int pos = 1 << (x * n + y) ;
        mat ^= pos;
        for(int[] dir : dirs){
            int nextx = x + dir[0];
            int nexty = y + dir[1];
            if(nextx < 0 || nexty < 0 || nextx >= m || nexty >= n){
                continue;
            }
            mat ^= (1 << (nextx * n + nexty));
        }
        return mat;
        
    }
    
    private int createBitVec(int[][] mat){
     
        int num = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                num |= mat[i][j] << (i * n + j);
            }
        }
        
        return num;
    }
}
