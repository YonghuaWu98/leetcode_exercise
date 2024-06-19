package tree.forest.and_search_the_collection;

/**
 * @Description TODO 并查集(java版最终版）
 **/
public class UF {
    // 记录节点元素和该元素所在分组的标识
    private int[] eleAndGroups;
    // 记录并查集中的分组个数
    private int count;
    // 记录每个组中的元素个数
    private int[] size;


    // 初始化并查集
    public UF(int N) {
        // 初始化分组的数量，默认情况下，由N个分组
        this.count = N;
        // 初始化eleAndGroup数组
        this.eleAndGroups = new int[N];
        // 初始化eleAndGroup中的元素及其所在的组的标识符，让eleAndGroup数组的索引作为并查集的每个结点的元素，并且让每个索引处的值
        // 作为元素所属集合的标识
        for (int i = 0; i < eleAndGroups.length; i++) {
            eleAndGroups[i] = i;
        }
        // 初始化size大小
        int[] size = new int[N];
        // 默认每个组的元素个数为1
        for (int i = 0; i < size.length; i++) {
            size[i] = 1;
        }
    }

    // 获取当前并查集中的数据有多少分组
    public int count() {
        return count;
    }

    // 元素p所在分组的标识符
    public int find(int p) {
        if (eleAndGroups[p] == p) return p;
        return find(eleAndGroups[p]);
    }


    // 判断并查集中元素p和元素q是否在同一分组中
    public boolean connected(int p, int q) {

        return find(p) == find(q);
    }


    // 把p元素所在分组和q元素所在分组合并
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        // 判断元素q和p是否已经在同一分组中，如果已经在同一分组中，则结束方法
        if (pRoot == qRoot) return;
        // 根据树（集合）的大小来合并
        if (size[p] < size[q]) {
            eleAndGroups[pRoot] = qRoot;
            size[q] += size[p];
        }else {
            eleAndGroups[qRoot] = pRoot;
            size[p] += size[q];
        }
        // 分组数减一
        this.count--;
    }
}
