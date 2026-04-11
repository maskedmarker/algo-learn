

## LRU

```text
LRU = 哈希表 + 双向链表

典型实现：
HashMap<K, Node>
        +
Double Linked List

索引结构（HashMap） + 排序结构（LinkedList）
```

## LFU

```text
HashMap + 双层双向链表（频率链表 + 节点链表）


1️⃣ 核心结构
keyMap: Map<key, Node>
freqMap: Map<freq, DoublyLinkedList>
minFreq: 当前最小频率

2️⃣ Node 结构
Node {
    key
    value
    freq
    prev
    next
}
```