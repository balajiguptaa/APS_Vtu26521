import java.util.*;

class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        // Step 1: assign unique group to -1 items
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = m++;
            }
        }

        // Graphs
        List<List<Integer>> itemGraph = new ArrayList<>();
        List<List<Integer>> groupGraph = new ArrayList<>();

        int[] itemIndegree = new int[n];
        int[] groupIndegree = new int[m];

        for (int i = 0; i < n; i++) itemGraph.add(new ArrayList<>());
        for (int i = 0; i < m; i++) groupGraph.add(new ArrayList<>());

        // Step 2: build graphs
        for (int i = 0; i < n; i++) {
            for (int prev : beforeItems.get(i)) {
                itemGraph.get(prev).add(i);
                itemIndegree[i]++;

                if (group[i] != group[prev]) {
                    groupGraph.get(group[prev]).add(group[i]);
                    groupIndegree[group[i]]++;
                }
            }
        }

        // Step 3: topo sort groups
        List<Integer> groupOrder = topoSort(groupGraph, groupIndegree, m);
        if (groupOrder.size() == 0) return new int[0];

        // Step 4: topo sort items
        List<Integer> itemOrder = topoSort(itemGraph, itemIndegree, n);
        if (itemOrder.size() == 0) return new int[0];

        // Step 5: group items
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int item : itemOrder) {
            map.computeIfAbsent(group[item], k -> new ArrayList<>()).add(item);
        }

        // Step 6: build result
        List<Integer> result = new ArrayList<>();
        for (int g : groupOrder) {
            if (map.containsKey(g)) {
                result.addAll(map.get(g));
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    private List<Integer> topoSort(List<List<Integer>> graph, int[] indegree, int n) {
        Queue<Integer> q = new LinkedList<>();
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) q.offer(i);
        }

        while (!q.isEmpty()) {
            int node = q.poll();
            res.add(node);

            for (int nei : graph.get(node)) {
                if (--indegree[nei] == 0) {
                    q.offer(nei);
                }
            }
        }

        return res.size() == n ? res : new ArrayList<>();
    }
}