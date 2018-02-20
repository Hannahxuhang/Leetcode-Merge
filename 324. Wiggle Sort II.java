题意：
	给一个无序数组，将它重新排序使得nums[0] < nums[1] > nums[2] < nums[3]....
	注意：你可以假设所有输入的数组都有可行的答案。

Follow Up: 
	你能够在O(n)的时间内而且/或者不使用额外空间解答吗？

思路：
	方法一：
	1. 将数组排序，找其中位数，相当于把有序数组从中间分成两份
	2. 从前半段的末尾取一个数放在新数组的第一位，从后半段的末尾取一个数放在新数组的第二位
	3. 接着从前半段取倒数第二个数放在第三位，从后半段取倒数第二个数放在第四位
	4. 依次类推，直至取完

	方法二：
	Link: https://leetcode.com/problems/wiggle-sort-ii/discuss/77677/O(n)+O(1)-after-median-Virtual-Indexing
	1. 使用Kth-largest-element的方法找到数组的中位数
	2. 进行virtual indexing re-wiring: 定义一个数组，使得A(i) = nums[(1+2*(i)) % (n|1)]
	3. 使用partition来quick select新的数组

复杂度：
	方法一：
	time: O(nlogn)
	space: O(n)

	方法二：
	time: O(n)
	space: O(1)

public class Solution {

	方法一：
	public void wiggleSort(int[] nums) {
	        Arrays.sort(nums);
	        int n = nums.length;
	        int mid = (n - 1) / 2;
	        int index = 0;
	        int[] temp = new int[n];
	        for (int i = 0; i <= mid; i++) {
	            temp[index] = nums[mid - i];
	            if (index + 1 < n) {
	                temp[index + 1] = nums[n - 1 - i];
	            }
	            index += 2;
	        }
	        System.arraycopy(temp, 0, nums, 0, n);
	    }

	方法二：
	public void wiggleSort2(int[] nums) {
        int median = findKthLargest(nums, (nums.length + 1) / 2);
        int n = nums.length;
        int left = 0, right = n - 1;
        int index = 0;
        while (index <= right) {
            if (nums[newIndex(index, n)] > median) {
                swap(nums, newIndex(left++, n), newIndex(index++, n));
            } else if (nums[newIndex(index, n)] < median) {
                swap(nums, newIndex(right--, n), newIndex(index, n));
            } else {
                index++;
            }
        }
    }

    private int newIndex(int index, int n) {
        return (1 + 2 * index) % (n | 1);
    }

    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0;
        int right = nums.length - 1;
        while (true) {
            int pos = partition(nums, left, right);
            if (pos + 1 == k) {
                return nums[pos];
            } else if (pos + 1 > k) {
                right = pos - 1;
            } else {
                left = pos + 1;
            }
        }
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        int l = left + 1;
        int r = right;
        while (l <= r) {
            if (nums[l] < pivot && nums[r] > pivot) {
                swap(nums, l++, r--);
            }
            if (nums[l] >= pivot) l++;
            if (nums[r] <= pivot) r--;
        }
        swap(nums, left, r);
        return r;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}