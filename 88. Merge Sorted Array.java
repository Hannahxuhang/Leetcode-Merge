题意：
	给两个有序数组nums1和nums2，将nums2归并到nums1中，使得它们成为一个有序数组。
	注意：你可以认为nums1拥有足够的空间（nums1的长度大于等于m + n）来存储nums2的元素，nums1和nums2初始化的元素个数分别为m和n。

思路：
	这道题用Two Pointers来解决：
	1. 因为nums1和nums2都是分别有序的，所以我们设置两个指针i和j，让它们一开始分别指向nums1和nums2的末尾元素，比较哪个元素大，就
	把她放在nums1的最后位置，然后该指针前进一位
	2. 需要注意的是，在第一次while循环结束后，需要检验j是否大于等于0，如果是，则需要额外将剩余的nums2元素排到nums1中

复杂度：
	time: O(m+n)
	space: O(1)

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (i >= 0 && j >= 0) {
            nums1[k--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--]; 
        }
        
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }
}