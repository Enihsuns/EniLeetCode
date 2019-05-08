# 30 Substring with Concatenation of All Words
Hard

## My thoughts
好难呀。。。

注意到所有的words are of the same length！！！这样就不用考虑两个word之间可能某一个word是另一个的substring。

首先，因为要返回每个位置，所以肯定有一个大循环循环每个位置。

为了优化，我们可以用trie来保存所有的words。用和words大小一样的一个boolean[]来记录某个word有没有出现过（每个word在trie里对应一个标号）。还要有一个wordCnt之类的，记录一共出现了几个word。

在循环的过程中，先从trie出发，搜搜，搜到了，我们就去往下一站这样。直到wordCnt等于wordList的大小。

优化：有的时候不用重复地搜。比方说 s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]这个例子里面，我们在i=0的时候，可能已经搜过第4个位置了（我们知道位置4可以接一个词good）。等到i=4的时候，就没必要重新再搜了，可以直接跳过4个位置这样。为了这样做，我们应该使用一个save数组int[]，它的长度与given string一样长，value表示这个位置的后面对应了标号为几的word。这个数组可以初始化为-1，表示没有被查询过，如果某个位置被发现不对应一个word，我们把该位置标为-2。

Testcase 1:<br>
"aaa"<br>
["aa","aa"]<br>

错误原因：Runtime Error(java.lang.ArrayIndexOutOfBoundsException) 在search trie的时候没有考虑边界问题，只是给出了start和end, 而没有考虑end的大小是否超出了array.length。

Testcase 2:<br>
"wordgoodgoodgoodbestword"<br>
["word","good","best","good"]<br>

错误原因：原来一个词可以出现多次呀。。。怪我没有看清楚测试用例。感到心酸。

## 1_190402
参考Discuss最高票中。

滑动窗口。。
暂时hard to explain。

