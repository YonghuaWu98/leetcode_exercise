package linkedList;/*
 *@title ExchangeLinkedListNode
 *@author 86156
 *@description
 *@create 2023/4/14 17:44
 */

/**
 * @ClassName:ExchangeLinkedListNode
 * @Description TODO  leetcode第24题  两两交换链表中的节点
 * @Author 86156
 * @Date 2023/4/14 17:44
 * @Version 1.0
 **/

/**不带头结点（迭代法）**/
public class SwapLinkedListNode {  // 算法思想：分别处理两两结点，处理一次拼接拼接一次，判断最后一次处理是两个节点还是一个结点，分别处理

    public ListNode swapPairs(ListNode head) {
        // 链表为null或者只有一个节点，直接返回head。
        if (head == null || head.next == null) {
            return head;
        }
        ListNode l = head.next;  // 保存head的第二个结点的地址
        while (head != null && head.next != null) {
            ListNode newHead = head.next.next;  // 设置一个辅助量newHead，记录两个结点之后的第一个结点。
//            head.next.next = null; // 每两个结点就与后面的结点断开
            // 以下两步完成两个结点之间指向翻转
            head.next.next = head;
            head.next = null;
            // oldHead记录head改变之前的位子
            ListNode oldHead = head;
            // 将head移动，进行下一次处理
            head = newHead;
            // 判断下一次处理是两个结点还是一个结点
            if (head != null && head.next != null) {
                oldHead.next = head.next; // 两个结点
            }else {
                oldHead.next = head; // 一个结点
            }
        }

        return l;
    }
}

/**带头结点**/
