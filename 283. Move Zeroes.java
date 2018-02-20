题意：
	给一个未排序的数组，写一个函数，在保证其他非零元素的相对顺序不变的情况下，把数组中所有的0都放在数组末尾。
	注意：1. 不能开辟额外空间；2. 尽量减少总共的操作数

思路：
	这里有两种方法，分别适用于不同的数组情况：

	方法一（当数组中0的个数少时）：
	1. 引入一个指针start，将其初始化为0
	2. for循环nums数组，当发现非零元素时，就将值赋给start对应的元素，且start向后移动一位
	3. 当for循环结束时，就将从start到数组最后的元素都设为0

	方法二（当数组中0的个数较多时）：
	1. 引入快慢指针i(快指针)和j(慢指针)
	2. for循环便利数组，快指针每次循环加1，当快指针所指元素不为0时，和慢指针指向的元素交换
	3. 慢指针只有在和快指针交换元素后才加1

复杂度：
	方法一：
	time: O(n)
	space: O(1)
	operation: nums.length

	方法二：
	time: O(n)
	space: O(1)
	operation: (num of non-zero element) * 2

	public class Solution {
		
		方法一：
		public void moveZeroes1(int[] nums) {
	        if (nums == null || nums.length == 0) return;
	        int start = 0;
	        for (int i = 0; i < nums.length; i++) {
	            if (nums[i] != 0) {
	                nums[start++] = nums[i];
	            }
	        }
	        while (start < nums.length) {
	            nums[start++] = 0;
	        }
	    }

	 	方法二：
	    public void  moveZeroes2(int[] nums) {
	        if (nums == null || nums.length == 0) return;
	        for (int i = 0, j = 0; i < nums.length; i++) {
	            if (nums[i] != 0) {
	                int temp = nums[i];
	                nums[i] = nums[j];
	                nums[j++] = temp;
	            }
	        }
	    }
	}
