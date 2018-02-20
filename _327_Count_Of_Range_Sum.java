package leetcode;

import java.util.Map;
import java.util.TreeMap;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : CountOfRangeSum
 * Creator : Edward
 * Description : 327. Count of Range Sum
 */

public class _327_Count_Of_Range_Sum {
    /**
     Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
     Range sum S(i, j) is defined as the sum of the elements in nums
     between indices i and j (i ≤ j), inclusive.

     Note:
     A naive algorithm of O(n2) is trivial. You MUST do better than that.

     Example:
     Given nums = [-2, 5, -1], lower = -2, upper = 2,
     Return 3.

     题意：
        给一个数组，如果该数组的一个子数组，元素之和大于等于给定的一个参数值（lower），小于等于一个给定的参数值（upper），
        那么这为一组解，求总共有几组解。

     方法一（TreeMap）:

     思路：
         1. 使用TreeMap来保存每个前缀和的计数，key为前n项和，value为前n项和为key的情况的个数
         2. 先在treeMap中put一个情况：key = 0, value = 1，这是sub-array为空时的情况，一定存在
         3. 在for循环中，我们记录了两个值: from和to，其中from = sum - upper, to = sum - lower
         4. 我们可以把这个式子变换一下：upper = sum - from，lower = sum - to，可以发现from对应的index到to对应的index
         之间的sub-array就是我们要找的子数组(元素和在lower和upper之间)
         5. 从而我们使用TreeMap自带的method subMap即可找到相应的sub-array

     复杂度：
         time: O(n^2)
         space: O(n)
     */
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) return 0;
        TreeMap<Long, Long> treeMap = new TreeMap<>();
        treeMap.put((long)0, (long)1);
        long sum = 0;
        long count = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            long from = sum - upper;
            long to = sum - lower;
            Map<Long, Long> sub = treeMap.subMap(from, true, to, true);
            for (Long value : sub.values()) {
                count += value;
            }
            treeMap.put(sum, treeMap.getOrDefault(sum, (long)0) + 1);
        }
        return (int)count;
    }

    /**
     方法二(分治法)：

     思路：
     1. rangeEnd是第一个满足 sums[rangeEnd] - sums[i] > upper 的下标
     2. rangeStart是第一个满足 sums[rangeStart] - sums[i] >= lower 的下标
     3. [lower, upper]之间的区间的个数是rangeEnd - rangeStart
     4. 遍历前半段 匹配后半段

     复杂度：
     time: O(nlogn)
     space: O(n)
     */
    public int countRangeSum2(int[] nums, int lower, int upper) {
        long[] sum = new long[nums.length + 1];
        for(int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i-1] + nums[i-1];
        }
        return helper(sum, new long[sum.length], 0, sum.length - 1, lower, upper);
    }

    private int helper(long[] sum, long[] helper, int low, int high, long lower, long upper) {
        if (low >= high) {
            return 0;
        }

        int mid = (high + 1 - low) / 2 + low;
        int count = helper(sum, helper, low, mid - 1, lower, upper)
                + helper(sum, helper, mid, high, lower, upper);

        int rangeStart = mid, rangeEnd = mid;
        for(int i = low; i < mid; i++) {
            while(rangeStart <= high && sum[rangeStart] - sum[i] < lower)
                rangeStart++;
            while(rangeEnd <= high && sum[rangeEnd] - sum[i] <= upper)
                rangeEnd++;

            count += rangeEnd - rangeStart;
        }

        merge(sum, helper, low, mid, high);
        return count;
    }

    private void merge(long[] sum, long[] helper, int low, int mid, int high) {
        int left = low, right = mid, idx = low;

        while(left < mid && right <= high) {
            if (sum[left] <= sum[right])
                helper[idx++] = sum[left++];
            else
                helper[idx++] = sum[right++];
        }

        while(left < mid)
            helper[idx++] = sum[left++];
        while(right <= high)
            helper[idx++] = sum[right++];

        System.arraycopy(helper, low, sum, low, high + 1 - low);
    }
}
