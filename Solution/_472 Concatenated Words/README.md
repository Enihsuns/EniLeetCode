# 472 Concatenated Words
Hard

## 1_190311
感觉可以用trie做，然而我不会写。
简单粗暴的方法就是用set把所有的input单词存起来，然后，遍历每个词，对每个词DFS。

剪枝：
1. 遍历每个词之前，把存词的list按照string长度排序；也不要一开始就建立set，可以考虑按遍历顺序建立set。
2. 如果发现某个词是由其他的词构成的，我们就不应该把这个词加入到set当中。

Corner Cases：
1. 长度为0的word是不对的。要跳过。

记得写DFS的时候要写终止的状态的返回值！

学习的Java写法：
1. 对Array排序：
```Java
String[] words = ...;

// Sort words by their length
Arrays.sort(words, 0, words.length, (o1, o2) -> (o1.length() - o2.length()));
```
只要按照o1-o2的顺序写，排序就会是从小到大。
2. String.start**s**With方法：返回boolean
```Java
word.startsWith("abc")
```
3. 如何除去一个String的prefix：
```Java
word.substring("abc".length())
```

## 2_190311
自己想的简单粗暴的方法果然非常惨淡。超过了5.05%。
欣赏一下排在最前面的代码。还是要使用Trie。这里先学习一下Trie。（放在了数据结构.md里）

Trie建好了。下一步是搜索。首先遍历每个词。对于每个词，跑DFS。然后发现写DFS的时候会有一个问题，就是不能单纯地用isEnd和当前DFS到达了句子末尾来判断是不是成功了，因为这个词语本身就在Trie里，这样的话DFS总是会返回true。

借鉴了一下别人的代码，他们的方法是在DFS的参数里面加一个int count，表示现在是第几层DFS。只有count>1的时候才能返回true。机智！

Testcase 1:

错误原因：在DFS的时候，没有判断是否走到了单词的末尾(isEnd == true)。

Testcase 2:
Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
Output: []
Expected: ["catsdogcats","dogcatsdog","ratcatdogcat"]

不知道发生了什么？？？

错误原因：在DFS里面写判断的时候，不是curNode.isEnd，而是curNode.children[index].isEnd。这是因为我们在循环里面写的是curNode.children[index] == null这种判断，而不是curNode == null之类的。这说明了当前index指向的字母不是在说当前的节点，而是在说当前节点的孩子，它的下一个结点是不是存在。

