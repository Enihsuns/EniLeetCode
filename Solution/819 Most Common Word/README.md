# 819 Most Common Word
Easy

## 1_190305
把banned words存在Set里面，其他的words存在Map<String, Integer>里面，其中Integer表示对应word的count。最后从Map里面选count最高的String。

把paragraph split成words: [^a-z]
完善一下: [^a-z]+ **一定要加加号！！！！**

考虑corner cases：
1. 不存在解，答案不唯一：题目禁止了这些情况

注意看清楚：Paragraph有可能是**大写的**！所以要把它们全部搞成小写。

## 2_190305
参考了一下6ms的算法。
没必要最后才从map中扫一遍，选择count最高的string。在更新Map的过程中就可以保存max。
虽然这样写完还是17ms。
看到更优的解法，有用trim的。共同的特点是没有像我一样用split，而是一个字符一个字符地读paragraph。因为懒惰所以不想写。