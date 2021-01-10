class Solution {
    public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Set<Integer>> pre = new HashMap<>();
        int[] indegree = new int[n];
        
        for(int i = 0; i < n; i++){
            graph.put(i, new HashSet<>());
            pre.put(i, new HashSet<>());
        }
        
        for(int[] prev : prerequisites){
            indegree[prev[1]]++;    
            graph.get(prev[0]).add(prev[1]);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < n; i++){
            if(indegree[i] == 0){
                queue.offer(i);
            }
        }
        
        while(!queue.isEmpty()){
            int node = queue.poll();
            for(Integer next : graph.get(node)){
             
                pre.get(next).add(node);
                pre.get(next).addAll(pre.get(node));
                indegree[next]--;
                
                if(indegree[next] == 0){
                    queue.offer(next);
                }
            }
        }
        
        // System.out.println(pre);
        List<Boolean> resList = new ArrayList<>();
        for(int[] query : queries){
            if(pre.get(query[1]).contains(query[0])){
                resList.add(true);
            }else{
                resList.add(false);
            }
        }
        
        return resList;
        
        
        
    }
}
