import unittest
import search_insert

class TestSearchInsert(unittest.TestCase):

    def test_empty(self):
        self.assertEqual(search_insert.Solution().searchInsert([],5),0)

    def test_last(self):
        self.assertEqual(search_insert.Solution().searchInsert([1],1),0)
        self.assertEqual(search_insert.Solution().searchInsert([3,3],3),0)

    def test_leetcode(self):
        self.assertEqual(search_insert.Solution().searchInsert([1,3,5,6], 7),4)


if __name__ == '__main__':
    unittest.main()
