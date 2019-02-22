# 490 The Maze
Medium

## My thoughts
BFS
找到stop的点。

## Wrong Answer
Test case 1:		
[[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]]		
[4,3]		
[3,0]		

错误原因：
stop的点找错了。只在while里面判断了stop的条件，但是nextPos写成了maze[][]=1的那个点或者是边界的点。**一定要记得stop的点要退回来！！！**

Test case 2:	
[[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]]		
[0,4]		
[4,4]		
错误原因:
发现Point的hashCode写错了。

Test case 3: textcase3.txt
错误原因：
发现Point的equals写错了。

## 1_190221
自己写了一个Point类，然后runtime和memory十分惨淡。

## 2_190221
看了Solution以后意识到自己傻了。为什么一定要一个Set来存点呢。完全可以用一个boolean[][]来保存。唉。  
然后moves\[i\]\[0\]的写法累赘了。可以用for(int[] move: moves)呀。  
**一定要检查！重点检查move的时候发生的事情。**因为刚刚我又写错了。这次是while里面的判断写错了。把>=0写成了>=1。