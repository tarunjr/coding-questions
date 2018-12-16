import unittest

class Solution:
    def maxArea(self, height):
        """
        :type height: List[int]
        :rtype: int
        """
        i, j = 0, len(height)-1
        maxSoFar = 0
        max_i, max_j = i, j
        while i < j:
            max = min(height[i], height[j]) * (j-i)
            if max > maxSoFar:
                maxSoFar, max_i, max_j = max, i, j
            if height[i] > height[j]:
                j-= 1
            else:
                i+= 1
        return maxSoFar

if __name__ == "__main__":
    s = Solution()
    print(s.maxArea([5,4,3,2,1]))
