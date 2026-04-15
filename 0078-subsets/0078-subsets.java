import java.util.*;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, nums, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int[] nums, 
                           List<Integer> current, 
                           List<List<Integer>> result) {
        
        // add current subset
        result.add(new ArrayList<>(current));
        
        for (int i = start; i < nums.length; i++) {
            // choose
            current.add(nums[i]);
            
            // explore
            backtrack(i + 1, nums, current, result);
            
            // undo (backtrack)
            current.remove(current.size() - 1);
        }
    }
}