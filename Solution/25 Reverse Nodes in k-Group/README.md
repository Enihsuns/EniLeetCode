# 25 Reverse Nodes in k-Group
Hard

## 1_190307
感觉不是很难？主要是理清思路？

外面一层大循环：用一个tmpHead,它的next保存当前正在visit的node。tmpHead->next用来作为内部循环的开头头的。判断有没有走到尽头就看tmpHead->next是不是空

循环内部：
1. 走k个node，记录这k个node(0, 1, ..., k-1)的开头(-1)和结尾(k)。这里面要考虑一些问题：
- 没有开头：不能让这种情况发生。我们应该在最开始的时候自己建立一个tmpHead之类的。
- 没有结尾：结尾就是null。
- 在走完k个node之前就结束了：要注意这里的判断，不要让世界报错，及时结束。
2. 把tmpEnd保存在preNode里。
3. for k个node的小循环：终止条件是p==tmpEnd
p的next保存在next里；让p指向pre；pre = p; p=next; 

e.g. 1->2->3->4->5
- tmpHead->1(p)->2(next)->3->4->5->tmpEnd(pre)
- tmpHead->1(pre)->tmpEnd 2(p)->3(next)->4->5->tmpEnd
- 2(pre)->1->tmpEnd 3(p)->4(next)->5->tmpEnd
- 3(pre)->2->1->tmpEnd 4(p)->5(next)->tmpEnd
- 4(pre)->3->2->1->tmpEnd 5(p)->tmpEnd(next)
- 5(pre)->4->3->2->1->tmpEnd tmpEnd(p)

4. tmpHead的next设为pre。
5. 为了回到外面的大循环，把tmpEnd的上一位！？？也就是tmpHead？但是tmpHead的next已经惨遭赋值了？赋值给tmpHead。

完成大循环以后，返回myHead.next。

Corner cases:
1. 给的head=null或者head->next=null：直接返回，快快乐乐。

？写错了

Testcase 1:
[1,2,3,4,5]
2

Output: [2,3,4,5,1]

Expected: [2,1,4,3,5]

错误原因：仔细检查了一下，就是上面步骤里的第5步写错啦。这种题还是要仔细一点。没有别的办法。