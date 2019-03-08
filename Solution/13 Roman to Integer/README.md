# 13 Roman to Integer
Easy

## 1_190307
和12题一样，就是反过来？还是写一个helper来处理。

corner cases:
1. 注意remove all white chars。

## 2_190307
跑出来惊喜地发现只超过了35.49%。决定先自己改进一下：不用StringBuffer来保存，这样的话也可以不用每次都delete char。用最简单的char数组就可以了。Helper多加一个参数，记录当前的Index。

然后我发现这样提升了3ms。真棒。

借鉴一番优秀的top1%代码！发现他们是这样写的：

首先，可以用一个Map来保存字母和其对应数字之间的关系。（或者用switch写也可以的呀）

然后，遍历string。如果发现当前位置的char比下个位置的char小，这就说明这里出现了一个subtraction。如果没有subtraction，ans就直接加上之前在Map里保存的对应数字。如果有subtraction，ans减去之前保存的对应数字。
比方说 MCM
1. 读了M：ans += 1000
2. 读了C：发现有subtraction，ans -= 100
3. 读了M：ans += 1000

这样1000-100就正好是900呀。

机智！学习了