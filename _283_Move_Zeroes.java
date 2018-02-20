package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MoveZeroes
 * Creator : Edward
 * Description : 283. Move Zeroes
 */

public class _283_Move_Zeroes {
    /**
     Given an array nums, write a function to move all 0's to the end of it
     while maintaining the relative order of the non-zero elements.
     For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

     Note:
     1. You must do this in-place without making a copy of the array.
     2. Minimize the total number of operations.


     time : O(n);
     space : O(1);
     */
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

    // num of operation : 2 * num of non-zero
    // lots of zeros
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
