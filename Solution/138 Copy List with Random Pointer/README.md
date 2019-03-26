# 138 Copy List with Random Pointer
Medium

## 1_190314
简单粗暴的想法：用一个Map<Node, Node>保存原先的Node和新Node的对应关系。使用DFS直接加新的Node。

啊 出现了多年未曾出现过的问题，stackoverflow。真棒真开心~

改变一下策略：先用next全部走一遍，然后再用Map帮助赋值random。

喵喵喵？然后发现我忘记add进Map里了？加上了以后就好啦！