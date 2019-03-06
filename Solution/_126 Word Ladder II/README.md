# 126 Word Ladder II
Medium

## 1_190306
和127一样的，只是要返回路径。

双向BFS：分别从beginWord和endWord出发，找有没有路径。

Everytime doing BFS: We will start from a beginSet. We will try to transform every word in the beginSet to a new word. If this new word exist in a valid set, this new word can be added into a nextSet. Next time, we will consider nextSet or targetSet as beginSet and do BFS again.

E.g. 
Iter 1:
beginSet = {hit}
targetSet = {cog}
validSet = {hot, dot, dog, lot, log, cog}
(Remove those in beginSet from validSet)
Do transformation to hit: ait, bit, ..., hat, hbt, ..., hia, hib, ...
nextSet = {hot}

Iter 2:
beginSet = {hot}
targetSet = {cog}
validSet = {dot, dog, lot, log, cog}
Do transformation to hot
nextSet = {dot, lot}

Iter 3:
beginSet = {cog}
targetSet = {dot, lot}
validSet = {dot, dog, lot, log}
Transformation
nextSet = {dog, log}

Iter 4:
beginSet = {dog, log}
targetSet = {dot, lot}
validSet = {dot, lot}
Transformation
Save all valid answers

How to output path?
Use HashMap<String, String> Key: the new word Value: the word that is transformed from

When output, 
e.g. dot -> hot -> hit During recursion, save all these words into a list. If the final word is beginWord, we should reverse the list.
Add two list together.

Corner cases:
1. No valid answer: return empty list
2. 没有考虑的：**targetWord不在validSet里面！！！**

Test case 1:
"magic"
"pearl"
["flail","halon","lexus","joint","pears","slabs","lorie","lapse","wroth","yalow","swear","cavil","piety","yogis","dhaka","laxer","tatum","provo","truss","tends","deana","dried","hutch","basho","flyby","miler","fries","floes","lingo","wider","scary","marks","perry","igloo","melts","lanny","satan","foamy","perks","denim","plugs","cloak","cyril","women","issue","rocky","marry","trash","merry","topic","hicks","dicky","prado","casio","lapel","diane","serer","paige","parry","elope","balds","dated","copra","earth","marty","slake","balms","daryl","loves","civet","sweat","daley","touch","maria","dacca","muggy","chore","felix","ogled","acids","terse","cults","darla","snubs","boats","recta","cohan","purse","joist","grosz","sheri","steam","manic","luisa","gluts","spits","boxer","abner","cooke","scowl","kenya","hasps","roger","edwin","black","terns","folks","demur","dingo","party","brian","numbs","forgo","gunny","waled","bucks","titan","ruffs","pizza","ravel","poole","suits","stoic","segre","white","lemur","belts","scums","parks","gusts","ozark","umped","heard","lorna","emile","orbit","onset","cruet","amiss","fumed","gelds","italy","rakes","loxed","kilts","mania","tombs","gaped","merge","molar","smith","tangs","misty","wefts","yawns","smile","scuff","width","paris","coded","sodom","shits","benny","pudgy","mayer","peary","curve","tulsa","ramos","thick","dogie","gourd","strop","ahmad","clove","tract","calyx","maris","wants","lipid","pearl","maybe","banjo","south","blend","diana","lanai","waged","shari","magic","duchy","decca","wried","maine","nutty","turns","satyr","holds","finks","twits","peaks","teems","peace","melon","czars","robby","tabby","shove","minty","marta","dregs","lacks","casts","aruba","stall","nurse","jewry","knuth"]

My output:
[["magic","manic","mania","maria","maris","marks","parks","perks","peaks","pears","pearl"],
["magic","manic","mania","maria","marta","marty","marry","parry","perry","peary","pearl"],
["magic","manic","mania","maria","marta","marty","marry","merry","perry","peary","pearl"],
["magic","manic","mania","maria","marta","marty","party","parry","perry","peary","pearl"]]

Expected:
[["magic","manic","mania","maria","marta","marty","marry","merry","perry","peary","pearl"],
["magic","manic","mania","maria","marta","marty","party","parry","perry","peary","pearl"],
["magic","manic","mania","maria","marta","marty","marry","parry","perry","peary","pearl"],
["magic","manic","mania","maria","maris","paris","parks","perks","peaks","pears","pearl"],
["magic","manic","mania","maria","maris","marks","parks","perks","peaks","pears","pearl"]]

错误原因：漏了一个paris为啥呢<br>
通过分析，发现当会出现paris和marks都可以推出parks的现象。而transMap只记下了一种可能QAQ。

需要更改transMap的结构。目前的想法是，transMap对应一个修改结构为Map<String, List<String>>。

**注意需要使用DFS返回路径**

Test case 2:

错误原因：Runtime error

发现是因为一个数组size的比较写错了：错误的写法：p1.get(0).get(p1.size() - 1) 正确的写法：p1.get(0).get(p1.get(0).size() - 1) 
