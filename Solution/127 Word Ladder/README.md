# 127 Word Ladder
Medium
Breadth-first Search

## 我的想法
知道应该用图来做，但是不知道如何构建图（如何有效率地构建两个word之间的边）。同时也没有想到建好图后要用BFS来搜索。

## 190221_1
参考了LeetCode上面的Solution。它主要有两个点：
1. 使用intermediate state来构建边。例如dog可被解读为\*og, d\*g和do\*。建立一个Map，以intermediate state为key，该state对应的存在于wordList中的词所构成的list为value。这样的话，判断两个word之间是否存在边，就应当先求得一个word的所有intermediate state，再遍历所有state，找Map中与之对应的所有List。
2. 使用Bidrectional BFS。同时搜索beginWord和endWord，这样的话可以缩小搜索空间。

## 190221_2
提交了以后，对比了时间10ms的代码，发现：
1. 没有用intermediate state的Map，而是直接暴力搜索：双层循环，第一层循环i = 0 to word.length(), 第二层从a到z。貌似这样做时间复杂度并没有变高多少。
2. 他们写的bidirectional BFS更好看。递归写法。附在下面：
```Java
private int search(Set<String> beginSet, Set<String> endSet, Set<String> dict, int cnt){
    if(beginSet.isEmpty() || endSet.isEmpty()) return 0;
    cnt++;		\\ 表示当前搜索到第几轮了
    dict.removeAll(beginSet);	\\ 已经出现在beginSet当中的word不应当被重复搜，所以从word list中移走
    Set<String> nextSet = new HashSet<>();
    for(String str : beginSet){
        char[] chs = str.toCharArray();
        for(int i = 0; i < chs.length; i++){
            char c = chs[i];	\\ 为了之后把chs[i]还原回去
            for(char j = 'a'; j <= 'z'; j++){
                chs[i] = j;
                String tmp = new String(chs);
                if(!dict.contains(tmp)) continue;
                if(endSet.contains(tmp)) return cnt;
                nextSet.add(tmp);
            }
            chs[i] = c;			\\ 还原chs[i]
        }
    }
    \\ 选择size更小的作为beginSet，这样更节省搜索空间
    return nextSet.size() > endSet.size() ? search(endSet, nextSet, dict, cnt) : search(nextSet, endSet, dict, cnt);
}
```
- 为什么可以写dict.removeAll(beginSet)呢？
考虑题目给的例子 beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]

搜索顺序应该是这样的：		
1) *hit* -> hot		
2) *cog* -> log, dog		
3) *hot* -> dot, lot		
4) *log, dog* -> dot, lot		

可以看到beginSet中的词搜了一次，之后就再也没有用了，所以应该从dict中被拿掉。比如hit完成了找到hot的使命之后，它就没用了。假设在这之后反方向存在一个词可以到达hit，那么这说明此前hit也应该可以到达那个词。这是一个悖论。所以可以安全地从dict中remove所有的beginSet，这样也可以进一步缩小搜索空间。