package com.wuwii.leetcode;

/**
 * http://blog.csdn.net/kerryfish/article/details/24043099
 * https://www.cnblogs.com/winorgohome/p/6028309.html
 *
 * @author KronChan
 * @version 1.0
 * @since <pre>2017/12/22 21:48</pre>
 */
public class LinkedListSummary {

    /**
     * 获取链表存储元素的数量
     *
     * @param node 链表
     * @return int
     */
    public static int size(Node node) {
        int i = 0;
        for (; node != null; ) {
            i++;
            node = node.next;
        }
        return i;
    }

    public static Node reversal(Node node) {
        Node indirection = node;
        for (; indirection != null; ) {

        }
        return null;
    }

    class Node {
        int val;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {

    }


}
