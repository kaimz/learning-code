package com.wuwii.leetcode;

/**
 * https://leetcode.com/problems/add-two-numbers/description/
 * <p>
 * medium
 * <p>
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Example
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 * <p>
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 *
 * @author KronChan
 * @version 1.0
 * @since <pre>2017/12/21 11:48</pre>
 */
public class AddTwoNumbers {
    class ListNode {
        public ListNode next;
        public int val;

        public ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        // 进位
        int hamal = 0;
        ListNode middleware = result;
        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + hamal;
            hamal = sum / 10;
            middleware.next = new ListNode(sum % 10);
            middleware = middleware.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        return result.next;
    }
}
