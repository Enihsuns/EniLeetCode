# 34 Find First and Last Position of Element in Sorted Array
Medium

## 1_190331
想法就是两个二分。第一个二分找first，第二个找last。

第一个二分没有问题。但是第二个有问题。

我的解决方式是使用了向上取整：left + (int)Math.ceil((right - left)/2.0)。然后left = mid, right = mid - 1。