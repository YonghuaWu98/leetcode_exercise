package linkedList;/*
 *@title RemoveElements
 *@author 86156
 *@description
 *@create 2023/4/13 15:42
 */

/**
 * @ClassName:RemoveElements
 * @Description TODO leetcode第203题 移除链表元素
 * @Author 86156
 * @Date 2023/4/13 15:42
 * @Version 1.0
 */
public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        public static ListNode1 removeElements(ListNode1 head, int val) {
            while (head != null && head.val == val) {
                head = head.next;
            }
            if (head == null) {
                return null;
            }

            ListNode1 cur = head.next;
            ListNode1 pre = head;
            while (cur != null) {
                if (cur.val == val) {
                    pre.next = null;
                    pre.next = cur.next;
                    cur = pre.next;

                }
                cur = cur.next;
                pre = pre.next;

            }
            return head;

        }
}
