/**
 * Runtime: 65 ms, faster than 90.59% of Java online submissions for LRU Cache.
 * Memory Usage: 58.1 MB, less than 60.40% of Java online submissions for LRU Cache.
 */
class LRUCache {
    
    class Node {
        int key;
        int val;
        Node pre, next;
        
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
    
    Node head, tail;        // Add on tail; Remove on head
    int capacity, size;
    Map<Integer, Node> map;

    public LRUCache(int capacity) {
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.pre = head;
        
        this.capacity = capacity;
        this.size = 0;
        map = new HashMap<>();
    }
    
    public int get(int key) {
        Node node = map.get(key);
        if(node == null) return -1;
        
        updateNode(node);
        
        return node.val;
    }
    
    public void put(int key, int value) {
        // Check if key exist
        if(map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            
            updateNode(node);
                
            return;
        }

        if(size == capacity) {  // Full
            // Remove head.next
            int hnextKey = head.next.key;
            map.remove(hnextKey);
            head.next = head.next.next;
            head.next.pre = head;
        }
        else {
            size++;
        }
        
        // Insert on tail
        Node node = new Node(key, value);
        
        // Connect tail.pre with node
        tail.pre.next = node;
        node.pre = tail.pre;
        
        // Connect tail with node
        tail.pre = node;
        node.next = tail;
        
        // Add into map
        map.put(key, node);
    }
    
    private void updateNode(Node node) {
        if(node.next == tail) return;   // Already newest
        
        // Connect node.pre with node.next
        node.pre.next = node.next;
        node.next.pre = node.pre;
        
        // Connect tail.pre with node
        tail.pre.next = node;
        node.pre = tail.pre;
        
        // Connect tail with node
        tail.pre = node;
        node.next = tail;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */