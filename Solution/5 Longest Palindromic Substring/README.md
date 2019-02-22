# 5 Longest Palindromic Substring

## 1_190221
首先可以暴力搜索。对于每一对i, j， 判断s[i...j]是不是palindromic。记录最长的长度和对应的字符串。这样的话时间复杂度是$O(n^3)$。<br>

回忆起来以前用C++写的时候的解法。遍历每一个可能的回文字符串的中间。从这个中间往两边发散，在两边不相等的时候停下来。记录最长的会问字符串。这样的时间复杂度是$O(n^2)$。

## 2_190221
仿佛看到了一个节约时间的方案：可以不用存substring两端的ansBegin和ansEnd，而是存ansBegin和maxLen，这样的话可以节省ansEnd-ansBegin的时间。<br>
需要注意的一点是，我的第1个实现里面在while之后，又把l++,r--了一遍，是为了回到正确的substring的位置。可以不用这样做，而是**小心地**计算substring的长度r-l-1。同时ansBegin也不等于l，而是**等于l+1**呀。<br>
貌似怎么样也提不到更少的时间了。就这样吧。