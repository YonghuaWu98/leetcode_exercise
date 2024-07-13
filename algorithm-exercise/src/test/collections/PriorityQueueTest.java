package test.collections;

import java.util.PriorityQueue;

/**
 * @author 吴勇华
 * @description: TODO
 */
public class PriorityQueueTest {

    public static void main(String[] args) {
        // 测试 PriorityQueue 的默认为大根堆还是小根堆
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(3);
        pq.offer(5);
        pq.offer(7);
        pq.offer(1);
        pq.offer(2);
        while (!pq.isEmpty()) {
            System.out.print(pq.peek() + " ");
            pq.poll();
        }
        // 结论： PriorityQueue 默认为小根堆
        // 自定义为大根堆
        PriorityQueue<Integer> pq1 = new PriorityQueue<>((a, b) -> (b - a));

        pq1.offer(3);
        pq1.offer(5);
        pq1.offer(7);
        pq1.offer(1);
        pq1.offer(2);
        System.out.print(pq1.peek() + " ");
        pq1.remove(7); // 指定元素删除
        System.out.print(pq1.peek() + " ");

    }
}
