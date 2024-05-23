package linkedList;


/**
 * @ClassName:CircularLinkedList
 * @Description TODO Leetcode第142题  环形链表II
 * @Author 86156
 * @Date 2023/4/15 22:14
 * @Version 1.0
 **/

import java.util.HashMap;


public class CircularLinkedList {
    /**哈希表**/
    public ListNode detectCycle(ListNode head) {
        HashMap<ListNode, Integer> map = new HashMap<ListNode, Integer>(); // 初始化一个map，结点地址作为key，出现的次数作为value
        ListNode cur = head;
        while (cur != null) {
            map.put(cur,map.getOrDefault(cur,0)+1);  // 如果map中没有cur，则添加key为cur，value为默认值加上1的键值对，记录出现的次数。
            if (map.get(cur) == 2) { // 判断该结点是不是出现两次
                return cur;    // 返回第一次出现两次的结点，即为环的开始结点。
            }
            cur = cur.next; // cur遍历链表
        }
        return null; // 没找到返回空
    }
    /**双指针**/
//    public ListNode detectCycleDual(ListNode head) {
//
//    }



}


