import java.util.*;

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();
        
        // Step 1: Build graph
        for (List<String> acc : accounts) {
            String name = acc.get(0);
            String firstEmail = acc.get(1);
            
            graph.putIfAbsent(firstEmail, new ArrayList<>());
            
            for (int i = 1; i < acc.size(); i++) {
                String email = acc.get(i);
                
                graph.putIfAbsent(email, new ArrayList<>());
                graph.get(firstEmail).add(email);
                graph.get(email).add(firstEmail);
                
                emailToName.put(email, name);
            }
        }
        
        // Step 2: DFS
        Set<String> visited = new HashSet<>();
        List<List<String>> result = new ArrayList<>();
        
        for (String email : graph.keySet()) {
            if (!visited.contains(email)) {
                List<String> component = new ArrayList<>();
                dfs(email, graph, visited, component);
                
                Collections.sort(component);
                component.add(0, emailToName.get(email));
                result.add(component);
            }
        }
        
        return result;
    }
    
    private void dfs(String email, Map<String, List<String>> graph,
                     Set<String> visited, List<String> component) {
        visited.add(email);
        component.add(email);
        
        for (String nei : graph.get(email)) {
            if (!visited.contains(nei)) {
                dfs(nei, graph, visited, component);
            }
        }
    }
}