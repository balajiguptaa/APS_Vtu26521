import java.util.*;

class Solution {

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(root, targetSum, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(TreeNode node, int target,
                           List<Integer> path,
                           List<List<Integer>> result) {

        if (node == null) return;

        path.add(node.val);
        target -= node.val;

        // Check leaf
        if (node.left == null && node.right == null && target == 0) {
            result.add(new ArrayList<>(path)); // copy path
        } else {
            backtrack(node.left, target, path, result);
            backtrack(node.right, target, path, result);
        }

        path.remove(path.size() - 1); // backtrack
    }
}
