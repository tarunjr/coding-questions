import unittest
import max_area
class TestMaxArea(unittest.TestCase):

    def test_empty(self):
        self.assertEqual(max_area.Solution().maxArea([]),0)

    def test_two(self):
        self.assertEqual(max_area.Solution().maxArea([1,1]),1)
        self.assertEqual(max_area.Solution().maxArea([3,7]),3)

    def test_leetcode(self):
        self.assertEqual(max_area.Solution().maxArea([1,8,6,2,5,4,8,3,7]),49)
    
if __name__ == '__main__':
    unittest.main()
