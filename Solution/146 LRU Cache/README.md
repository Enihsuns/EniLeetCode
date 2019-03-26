# 146 LRU Cache
Hard

## 1_190314
用一个map<Integer, Integer>保存key到index的关系。用int[]保存values。用head, tail保存指向values的index，模拟队列。

写了很多！高兴地发现问的是Least used items也就是说get也会更新顺序。请好好读题好吗。谢谢。

想用Linked list+Map了。

使用了双向链表+Map<Integer, Node>。需要小心的地方：
1. 检查Key是否已经出现在Map中：如果出现了，更新Node的值，也要更新位置。
2. 每次get都要更新位置。