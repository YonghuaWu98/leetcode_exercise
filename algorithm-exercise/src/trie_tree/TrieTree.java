package trie_tree;


public class TrieTree {
    private TrieTree[] children;
    private boolean isEnd;

    public TrieTree() {
        children = new TrieTree[26];
        isEnd = false;
    }
    // 插入
    public void insert(String word) {
        TrieTree node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieTree();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }
    // 查找
    public boolean search(String word) {
        TrieTree node = searchPrefix(word);
        return node != null && node.isEnd;
    }
    // 判断该单词是否为插入单词的前缀
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private TrieTree searchPrefix(String prefix) {
        TrieTree node = this;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node;
    }
}
