class Solution(object):
    def searchInsert(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: int
        """
        pos = 0
        while pos < len(nums):
            if target > nums[pos]:
                pos+= 1
            else:
                break

        return pos
