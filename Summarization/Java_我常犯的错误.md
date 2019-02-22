1. Set<int[]> 不行。自己写Class，注意Override equals和hashCode。  
不要写错！equals一定要老老实实比较每一个东西; hashCode调用Objects.hash。
```Java
    class Point {
        int x;
        int y;
        
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
        
        @Override
        public boolean equals(Object o) {
            if(!(o instanceof Point)) return false;
            return ((Point)o).x == this.x && ((Point)o).y == this.y;
        }
    }
```
