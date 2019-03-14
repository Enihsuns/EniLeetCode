# 16 3Sum Closest
Medium

## 1_190313
一个简单粗暴的想法：先求出世界上所有的sum的可能，把这些sum建成一棵binary search tree，然后搜索。其实直接搜也是一样的吧。。。反正肯定是O(n^3)。

或者还是用3Sum的思想，先排序。一个循环，遍历element。在fix了一个element的情况下，对右边的elements进行O(n)的2Sum，就是循环里面要执行的操作不是对比等不等于target，而是判断和target之间的差。