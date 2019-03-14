# 564 Find the Closest Palindrome
Hard

## My thoughts
现在有一个简单的想法：two pointers, 左边一个left，右边一个right，往中间搜索。
如果相等的话，跳过不管；如果不相等的话，保留左边的值，改变右边的值。

1299554
1299921

15
11

好蠢哦，反正很多的case都没有考虑到

比如19的回答是22，而不是11。10的回答是9，而不是11。