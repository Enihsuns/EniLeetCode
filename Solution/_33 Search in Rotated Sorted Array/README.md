# 33 Search in Rotated Sorted Array
Medium

## My thoughts
肯定要用二分。但是怎么找头和尾呢。

[4,5,6,7,0,1,2]

先用二分找在哪里分开了，再用二分搜索。
1. 找在哪里分开了:left, right, mid
	1. 如果arr[mid]大于arr[left]且arr[mid + 1]小于arr[right]: 找到了，在mid和mid+1之间断开。
	2. 如果arr[mid]小于arr[left]:太右了，应该移动right到mid - 1
	3. 如果arr[mid + 1]大于arr[right]: 太左了，应该移动left到mid。

## 1_190331
参考Solution。使用二分法。
1. 先用二分找在哪里分开了，即找整个array中最小的数的index。left, right, mid
	1. arr[mid] > arr[mid + 1]：找到了，返回mid+1
	2. arr[mid] > arr[left]:太左了。left=mid+1。**在顺序的没有翻转过的数组中并不适用。**
	3. arr[mid] < arr[left]:太右了。right=mid。
2. 从这个index我们可以把数组分为两个部分。
3. 从其中的一个部分中找值。

Testcase 1:<br>
[1, 2, 3, 4, 5]<br>
5<br>
Expected: 4

Output: -1

错误原因：寻找在哪里分开的地方出现了错误。没有考虑顺序数组的情况！要相信二分可以走过每一个可能是答案的点，所以说return mid只能发生在循环内。循环外，只能return 0而不能return left。

然后，在第2步里面，需要正确地把数组分成两个部分。