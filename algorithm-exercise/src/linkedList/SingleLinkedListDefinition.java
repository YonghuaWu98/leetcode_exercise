package linkedList;/*


/**
 * @ClassName:SingleLinkedListDefinition
 * @Description TODO
 * @Author 86156
 * @Date 2023/4/13 20:42
 * @Version 1.0
 **/

public class SingleLinkedListDefinition {
    public static void main(String[] args) {
        ListNode1 node1 = new ListNode1(1);
        ListNode1 node2 = new ListNode1(2);
        ListNode1 node3 = new ListNode1(3);
        ListNode1 node4 = new ListNode1(4);
        ListNode1 node5 = new ListNode1(3);
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.add(node1);
        myLinkedList.add(node2);
        myLinkedList.add(node3);
        myLinkedList.add(node4);
        myLinkedList.add(node5);
//        myLinkedList.showList();
        ListNode1 head = myLinkedList.get();
        System.out.println(head.next);

        ListNode1 listNode1 = myLinkedList.removeElements(head, 3);

        System.out.println(listNode1);

    }
}
class MyLinkedList {
    private ListNode1 head = new ListNode1(0);
    public void add(ListNode1 lisNode) {
        // 由于头节点head不能动，因此需要一个辅助temp
        ListNode1 temp = head;
        // 遍历链表，找到最后
        while (true) {
            if (temp.next == null) {
                break;
            }
            // 如果没有找到最后，将temp后移
            temp = temp.next;
        }
        // 当退出while循环后，temp就指向了链表的尾部
        temp.next = lisNode;
    }
    public void showList() {
        // 判断链表是否为空
        if (head.next == null) {
            return;
        }
        // 设置辅助变量
        ListNode1 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            // 输出结点信息
            System.out.println(temp);
            // 输出一次，后移一次
            temp = temp.next;
        }
    }
    public ListNode1 get() {
        return head;
    }
    public  ListNode1 removeElements(ListNode1 head, int val) {
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

class ListNode1 {
    public int val;
    public ListNode1 next;
    public ListNode1(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val + next+"}";
    }
}