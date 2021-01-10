class Solution {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        
        for(List<Integer> seq : seqs){
            if(seq.size() == 1){
                map.putIfAbsent(seq.get(0), new HashSet<>());
                indegree.putIfAbsent(seq.get(0), 0);
            }else{
                for(int i = 0; i + 1 < seq.size(); i++){
                    map.putIfAbsent(seq.get(i), new HashSet<>());
                    map.putIfAbsent(seq.get(i + 1), new HashSet<>());
                    indegree.putIfAbsent(seq.get(i), 0);
                    indegree.putIfAbsent(seq.get(i + 1), 0);
                    if(!map.get(seq.get(i)).contains(seq.get(i + 1))){
                         indegree.put(seq.get(i + 1), indegree.get(seq.get(i + 1)) + 1);
                    }
                    
                    map.get(seq.get(i)).add(seq.get(i + 1));
                }
            }
        }
        
        Queue<Integer> queue = new LinkedList<>();
        for(Integer num : map.keySet()){
            if(indegree.get(num) == 0){
                queue.offer(num);
            }
        }
        
        int index = 0;
        while(!queue.isEmpty()){
            // Only one sequence
            if(queue.size() > 1){return false;}
            int num = queue.poll();
            if(index >= org.length){return false;}
            if(org[index++] != num){return false;}
            Set<Integer> set = map.get(num);
            if(index > map.keySet().size()){return false;}
            for(Integer next : set){
                indegree.put(next, indegree.get(next) - 1);
                if(indegree.get(next) == 0){
                    queue.offer(next);
                }
            }
        }
        
        // System.out.println(index);
//         System.out.println(map);
//         System.out.println(indegree);
        
        for(Integer num : indegree.keySet()){
            if(indegree.get(num) != 0){
                return false;
            }
        }
        
        return index == map.keySet().size() && index == org.length;
    }
}
