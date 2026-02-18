import java.util.*;

class Solution {
    class Node {
        int row, col, val;
        Node(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<Node> nodes = new ArrayList<>();
        dfs(root, 0, 0, nodes);

        // Sort by column, then row, then value
        Collections.sort(nodes, (a, b) -> {
            if (a.col != b.col) return a.col - b.col;
            if (a.row != b.row) return a.row - b.row;
            return a.val - b.val;
        });

        List<List<Integer>> result = new ArrayList<>();
        int prevCol = Integer.MIN_VALUE;
        List<Integer> colList = new ArrayList<>();

        for (Node n : nodes) {
            if (n.col != prevCol) {
                if (!colList.isEmpty()) result.add(colList);
                colList = new ArrayList<>();
                prevCol = n.col;
            }
            colList.add(n.val);
        }
        result.add(colList);

        return result;
    }

    private void dfs(TreeNode node, int row, int col, List<Node> nodes) {
        if (node == null) return;
        nodes.add(new Node(row, col, node.val));
        dfs(node.left, row + 1, col - 1, nodes);
        dfs(node.right, row + 1, col + 1, nodes);
    }
}