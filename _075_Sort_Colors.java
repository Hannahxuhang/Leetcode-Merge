package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SortColors
 * Creator : Edward
 * Description : 75. Sort Colors
 */
public class _075_Sort_Colors {
    /**
     Given an array with n objects colored red, white or blue,
     sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
     Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

     Note:
     You are not suppose to use the library's sort function for this problem.

     题意：
     给出一个有n个对象的数组，这n个元素分别被标为了红色，白色和蓝色。请将这些元素排序，使得相同颜色的元素相邻，并且颜色的顺序为红
     色，白色和蓝色。这里我们用整数0，1和2来分别代表红色，白色和蓝色。

     注意：请不要使用库里自带的排序方法来做这道题。

     思路：
     这道题用了Quick Select中挡板的思路：
     1. 这道题里有三种颜色，所以我们设置三个挡板：left, index和right
     2. 三个挡板，四个区域：
     1）红色区域：left左边（<left）都是0
     2）白色区域：left右边，index左边(>=left，<index)都是1
     3）未知区域：index右边，right左边(>=index, <=right)是未知区域（颜色不定）
     4) 蓝色区域：right右边（>right）都是2

     在while循环里，我们检测未知区域的元素，如：当发现元素为0时，判定它应该属于红色区域，就和left对应的元素交换，并且left++,
     index++，其他区域同理。
     3. 当未知区域为0（index > right）时，排序结束，跳出循环

     复杂度：
     time: O(n)
     space: O(1)
     */

    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int left = 0;
        int right = nums.length - 1;
        int index = 0;
        while (index <= right) {
            if (nums[index] == 0) {
                swap(nums, index++, left++);
            } else if (nums[index] == 1) {
                index++;
            } else {
                swap(nums, index, right--);
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
