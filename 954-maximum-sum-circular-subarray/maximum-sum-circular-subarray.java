class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int total = 0;
        int maxSum = nums[0], curMax = 0;
        int minSum = nums[0], curMin = 0;

        for (int n : nums) {
            // Kadane max
            curMax = Math.max(curMax + n, n);
            maxSum = Math.max(maxSum, curMax);

            // Kadane min
            curMin = Math.min(curMin + n, n);
            minSum = Math.min(minSum, curMin);

            total += n;
        }

        // if all numbers are negative
        if (maxSum < 0) return maxSum;

        return Math.max(maxSum, total - minSum);
    }
}