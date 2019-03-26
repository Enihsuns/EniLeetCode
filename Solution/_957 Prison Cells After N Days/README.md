# 957 Prison Cells After N Days
Medium

## My thoughts
因为N可能会很大，所以应该不是直接算，而是一道找规律的题。

e.g. N = 7<br>
Day 0: [0, 1, 0, 1, 1, 0, 0, 1]<br>
Day 1: [0, 1, 1, 0, 0, 0, 0, 0]<br>
Day 2: [0, 0, 0, 0, 1, 1, 1, 0]<br>
Day 3: [0, 1, 1, 0, 0, 1, 0, 0]<br>
Day 4: [0, 0, 0, 0, 0, 1, 0, 0]<br>
Day 5: [0, 1, 1, 1, 0, 1, 0, 0]<br>
Day 6: [0, 0, 1, 0, 1, 1, 0, 0]<br>
Day 7: [0, 0, 1, 1, 0, 0, 0, 0]<br>

现在的想法是从两边向中间找。
1. 首先可以确定的是，最两端的cell一定一直都是0.
2. 现在考虑位于1和len-2的两个cell：??卡住。

## 1_190314
啊，看了solution，虽然大家告诉我，7天或者14天是一个轮回，但是还是不想按这个找规律写？？？

比较节省时间的方法！就是把array存在Map里，防止重复的计算。需要注意的是**存在map里的时候，需要把array转化成String因为直接存Array的话会用reference检查而不是content。**

**转化array变成String的时候怎么能用arr.toString()呢？？？**猪猪吧。要用**Arrays.toString()**。

啊，仔细地看了一下别人的代码，并不是构建了一个Map<String, int[]>来节省时间，而是构建Map<String, Integer>，其中的Integer表示的是在第几轮出现了这个组合的cells。这也就意味着，如果我们发现了重复的cells，那么它必定存在着一个轮回。我们只需要把N对这个轮回取余，然后再计算一番就可以惹。

太绕了。取余什么的。理一下。

正确的写法（借鉴Discuss，自己写的）：
```Java
public int[] prisonAfterNDays(int[] cells, int N) {
    Map<String, Integer> cellsMap = new HashMap<>();
    
    int cellsLen = cells.length;
    
    while(N > 0) {
        String str = Arrays.toString(cells);
        System.out.println(" N: " + N + " Cells: " + Arrays.toString(cells));
        cellsMap.put(str, N--);
        
        int[] nextCells = new int[cellsLen];
        for(int j = 1; j < cellsLen - 1; j++) {
            nextCells[j] = cells[j - 1] == cells[j + 1]? 1 : 0;
        }
        
        cells = nextCells;
        String nextStr = Arrays.toString(cells);
        if(cellsMap.containsKey(nextStr)) {
            N %= cellsMap.get(nextStr) - N;
        }
    }
    
    return cells;
}
```

正确的写法的输出是这样的：（注意这里的输出是被放进cellsMap的内容，也就是说，被放进cellsMap的是第N天的cells，而第N-1天的cells应该出现在下一行中。如果发生了N的减少，这说明下一行中的内容是重复出现的，因为我们在用nextCells做匹配而不是当前的cells）<br>
N: 50 Cells: [0, 1, 0, 1, 1, 0, 0, 1]<br>
**N: 49 Cells: [0, 1, 1, 0, 0, 0, 0, 0]**<br>
N: 48 Cells: [0, 0, 0, 0, 1, 1, 1, 0]<br>
N: 47 Cells: [0, 1, 1, 0, 0, 1, 0, 0]<br>
N: 46 Cells: [0, 0, 0, 0, 0, 1, 0, 0]<br>
N: 45 Cells: [0, 1, 1, 1, 0, 1, 0, 0]<br>
N: 44 Cells: [0, 0, 1, 0, 1, 1, 0, 0]<br>
N: 43 Cells: [0, 0, 1, 1, 0, 0, 0, 0]<br>
N: 42 Cells: [0, 0, 0, 0, 0, 1, 1, 0]<br>
N: 41 Cells: [0, 1, 1, 1, 0, 0, 0, 0]<br>
N: 40 Cells: [0, 0, 1, 0, 0, 1, 1, 0]<br>
N: 39 Cells: [0, 0, 1, 0, 0, 0, 0, 0]<br>
N: 38 Cells: [0, 0, 1, 0, 1, 1, 1, 0]<br>
N: 37 Cells: [0, 0, 1, 1, 0, 1, 0, 0]<br>
**N: 36** Cells: [0, 0, 0, 0, 1, 1, 0, 0]<br>
N: 7  **Cells: [0, 1, 1, 0, 0, 0, 0, 0]**<br>
N: 6  Cells: [0, 0, 0, 0, 1, 1, 1, 0]<br>
N: 5  Cells: [0, 1, 1, 0, 0, 1, 0, 0]<br>
N: 4  Cells: [0, 0, 0, 0, 0, 1, 0, 0]<br>
N: 3  Cells: [0, 1, 1, 1, 0, 1, 0, 0]<br>
N: 2  Cells: [0, 0, 1, 0, 1, 1, 0, 0]<br>
N: 1  Cells: [0, 0, 1, 1, 0, 0, 0, 0]<br>

怎么思考呢？写循环内的代码的顺序：
1. cellsMap怎么保存：第N天就应该对应第N天的cells。比方说，这里输入的N是50，对应世界上一共有50天。我们应该把保存进cellsMap的工作放在最开始做（写在循环的最开始）。这是为了保存好最开始的输入的cells。
2. N--。和当天的事情已经做完。
3. 下一步是计算明天的cells会变成什么样子。
4. 已经算好了明天的cells，我们应该比较一下明天的cells是不是已经出现过了。如果等到明天再去比较，再去对N进行操作就来不及啦。所以我们今天就要算好明天该进入第几天。N %= cellsMap.get(nextStr) - N的设计是怎么回事呢？e.g. 看上面的例子。首先可以注意到，N=7和N=47的时候，它们的cells是相同的。但根据我们前面说的，第7天的cells，其实是第36天的nextCells。也就是说，我们在第36天，完成了当天操作，N--（这时N已经等于35了），计算nextCells=[0, 1, 1, 0, 0, 0, 0, 0]之后，发现cellsMap中有重合的部分N=49。这时，我们用49-35=14，发现14天为一个轮回。也就是说，再过14天，N再减少14，世界又会回到现在的cells的状态。所以我们可以用N/14，来表示还剩下多少个14天。那么，N%14则是表示，在经过N/14个14天以后，还剩下多少天？这时候就正常计算就可以了，虽然代码里还在继续进行没有作用的取余。