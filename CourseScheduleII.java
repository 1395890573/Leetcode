class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        HashMap<Integer, Integer> inDegree = new HashMap<>();
        Queue<Integer> sources = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            graph.put(i, new ArrayList<>());
            inDegree.put(i, 0);
        }
        for(int[] pre : prerequisites){
            graph.get(pre[1]).add(pre[0]);
            inDegree.put(pre[0], inDegree.get(pre[0]) + 1);
        }
        for(int i = 0; i < numCourses; i++){
            if(inDegree.get(i) == 0){ sources.add(i);}
        }
        List<Integer> sorted = new ArrayList<>();
        while(!sources.isEmpty()){
            int node = sources.poll();
            sorted.add(node);
            for(int child : graph.get(node)){
                inDegree.put(child, inDegree.get(child) - 1);
                if(inDegree.get(child) == 0){sources.add(child);}
            }
        }
        if(sorted.size() != numCourses){
            return new int[0];
        }
        int[] res = new int[numCourses];
        for(int i = 0; i < numCourses; i++){
            res[i] = sorted.get(i);
        }
        return res;     
    }
}
