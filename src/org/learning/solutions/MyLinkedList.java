package org.learning.solutions;

public class MyLinkedList {
	 public class ListNode {
		 int val;
		 ListNode next;
		 ListNode(int x) { val = x; }
	 } 
	public ListNode reverseBetween(ListNode head, int m, int n) {
	        if (head == null) {
	            return null;
	        }
	        if (m == n) {
	            return head;
	        }
	        m--;
	        n--;
	        
	        ListNode p =  head;
	        ListNode l = null;
	        
	        for (int i=0; i <= n; i++ ) {
	            if( i < m) {
	                l = p;
	                p = p.next;
	            } else if (i == m) {
	                ListNode sublistHead = null;
	                ListNode sublistTail = p;
	                ListNode q  = p;
	                while(i <= n ){
	                    q = p.next;
	                    p.next = sublistHead;
	                    sublistHead = p;
	                    p = q;
	                    i++;
	                }
	                if (l != null)
	                    l.next=sublistHead;
	                else 
	                    head = sublistHead; 
	                
	                sublistTail.next = q;
	            } 
	        } 
	        return head;
	    }
}
