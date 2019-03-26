# 42 Trapping Rain Water
Hard

## 1_190314
很久之前做过这道题。回忆一下。

Two pointers。left和right。Trap的water取决于更低的墙。移动pointer的策略是：

1. 移动过程中，以left为例，始终记录到目前为止，left遇到的最高的wall。同时记录以最高的wall减去当前的wall的高度，表示当前可能的积水数量。
2. 移动过程当中会有三种trap的可能：出现在Left区域内的trap，出现在right区域内的trap和由left和right共同达成的trap。
	1. Left区域内的trap：如果Left移动的过程当中，最高的wall被更新了或者被达到了（大于**等于**），这也就意味着出现了下图这种情况：
```
  .
. .
...
```
说明有一个trap出现了，左边低，右边高。这个时候，我们把当前区域内记录的所有的水量加起来。加入ans。
	2. Right区域内，同理。
	3. Left和Right共同构成的：牵涉到一个重要的策略，就是移动Left还是right。我们应当移动更小的那个Pointer。这样的话，在最后的这个由Left和right共同构成的区域内，我们只需要判断Left什么时候与right相等，再把当前记录的水量加起来就可以啦。