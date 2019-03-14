1. String系列：
- String toCharArray()
```Java
String s = "ppqq";
char[] arr = s.toCharArray();
```
- String.start**s**With方法：返回boolean
```Java
word.startsWith("abc")
```
- 如何除去一个String的prefix：
```Java
word.substring("abc".length())
```

2. 排序系列：
- 对Array排序：
```Java
String[] words = ...;

// Sort words by their length
Arrays.sort(words, 0, words.length, (o1, o2) -> (o1.length() - o2.length()));
```
只要按照o1-o2的顺序写，排序就会是从小到大。
