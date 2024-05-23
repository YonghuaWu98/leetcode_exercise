package linkedList;/*
 *@title TurnoverLinkedList
 *@author 86156
 *@description
 *@create 2023/4/13 20:54
 */

/**
 * @ClassName:TurnoverLinkedList
 * @Description TODO 力扣第206题  反转链表
 * @Author 86156
 * @Date 2023/4/13 20:54
 * @Version 1.0
 **/
public class TurnoverLinkedList {
    public static void main(String[] args) {

    }
    /**带头结点的双指针写法**/
    public static ListNode1 reverseList1(ListNode1 head) {
        ListNode1 dummyHead = new ListNode1(0);
        dummyHead.next = head;
        ListNode1 pre = dummyHead;
        ListNode1 cur = head;
        if (head == null) {return null;}
        while (cur != null) {
            ListNode1 temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        head.next = null;
        head = pre;
        return head;
    }
    /**不带头节点的双指针写法**/
    public static  ListNode1 reverseList2(ListNode1 head) {
        ListNode1 cur = head;
        ListNode1 pre = null;
        while (cur != null) {
            ListNode1 temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        head = pre;
        return head;
    }
    /**递归写法**/
    private ListNode1 reverse(ListNode1 pre, ListNode1 cur) {
        if (cur == null) {
            return pre;
        }

        ListNode1 temp = cur.next;
        cur.next = pre;
        return reverse(cur,temp);
    }
    public ListNode1 reverseList3(ListNode1 head) {
        return reverse(null,head);

    }
}

