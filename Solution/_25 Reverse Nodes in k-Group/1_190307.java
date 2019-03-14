/**
 * Runtime: 3 ms, faster than 99.36% of Java online submissions for Reverse Nodes in k-Group.
 * Memory Usage: 39.1 MB, less than 32.36% of Java online submissions for Reverse Nodes in k-Group.
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null) {
            return head;
        }
        
        // Set myHead
        ListNode myHead = new ListNode(-1);
        myHead.next = head;
        
        // Loop
        ListNode tmpHead = myHead;
        while(tmpHead != null && tmpHead.next != null) {
            // Find if we have k followed nodes
            ListNode p = tmpHead;
            for(int i = 0; i < k; i++) {
                p = p.next;
                if(p == null) {
                    return myHead.next;
                }
            }
            ListNode tmpEnd = p.next;
            
            // Reverse
            p = tmpHead.next;
            ListNode pre = tmpEnd;
            while(p != tmpEnd) {
                ListNode next = p.next;
                p.next = pre;
                pre = p;
                p = next;
            }
            
            // Get the next node
            ListNode next = tmpHead.next;
            tmpHead.next = pre;
            tmpHead = next;
        }
        
        return myHead.next;
    }
}