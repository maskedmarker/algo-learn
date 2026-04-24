package org.example.learn.algo.biz.cache;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {

    private final int capacity;
    private int size;
    private int minFreq;

    private final Map<Integer, Node> keyMap;
    private final Map<Integer, DoublyLinkedList> freqMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.minFreq = 0;
        this.keyMap = new HashMap<>();
        this.freqMap = new HashMap<>();
    }

    public int get(int key) {
        Node node = keyMap.get(key);
        if (node == null) return -1;

        increaseFreq(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (keyMap.containsKey(key)) {
            Node node = keyMap.get(key);
            node.value = value;
            increaseFreq(node);
            return;
        }

        // 容量满了，淘汰
        if (size == capacity) {
            DoublyLinkedList minList = freqMap.get(minFreq);
            Node toRemove = minList.removeLast();
            keyMap.remove(toRemove.key);
            size--;
        }

        // 插入新节点
        Node newNode = new Node(key, value);
        keyMap.put(key, newNode);

        DoublyLinkedList list = freqMap.getOrDefault(1, new DoublyLinkedList());
        list.addFirst(newNode);
        freqMap.put(1, list);

        minFreq = 1;
        size++;
    }

    // 核心：频率提升
    private void increaseFreq(Node node) {
        int freq = node.freq;
        DoublyLinkedList oldList = freqMap.get(freq);

        oldList.remove(node);

        // 如果当前链表空了，并且是最小频率，更新 minFreq
        if (freq == minFreq && oldList.isEmpty()) {
            minFreq++;
        }

        node.freq++;

        DoublyLinkedList newList = freqMap.getOrDefault(node.freq, new DoublyLinkedList());
        newList.addFirst(node);
        freqMap.put(node.freq, newList);
    }

    // ================= 数据结构 =================

    static class Node {
        int key;
        int value;
        int freq = 1;

        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    static class DoublyLinkedList {
        Node head;
        Node tail;

        DoublyLinkedList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        void addFirst(Node node) {
            node.next = head.next;
            node.prev = head;

            head.next.prev = node;
            head.next = node;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        Node removeLast() {
            if (isEmpty()) return null;
            Node last = tail.prev;
            remove(last);
            return last;
        }

        boolean isEmpty() {
            return head.next == tail;
        }
    }
}