/**
 * Runtime: 1 ms, faster than 93.24% of Java online submissions for Copy List with Random Pointer.
 * Memory Usage: 34.4 MB, less than 100.00% of Java online submissions for Copy List with Random Pointer.
 */
/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
*/
class Solution {
    Map<Node, Node> map;
    
    public Node copyRandomList(Node head) {
        map = new HashMap<>();
        
        if(head == null) return null;
        
        return DFS(head);
    }
    
    private Node DFS(Node refNode) {
        // Check in map
        Node node = map.getOrDefault(refNode, null);
        if(node != null) return node;
        
        // Make new node
        node = new Node(refNode.val, null, null);
        map.put(refNode, node);
        
        // Next
        if(refNode.next != null) {
            node.next = DFS(refNode.next);
        }
        
        // Random
        if(refNode.random != null) {
            node.random = DFS(refNode.random);
        }
        
        return node;
    }
}