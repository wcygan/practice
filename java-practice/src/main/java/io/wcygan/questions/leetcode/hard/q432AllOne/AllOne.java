package io.wcygan.questions.leetcode.hard.q432AllOne;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/problems/all-oone-data-structure/
class AllOne {

    public static final String EMPTY = "";

    static class Pool {
        int index;
        Set<String> members;
        Pool next;
        Pool prev;

        public Pool(int index) {
            this.index = index;
            members = new HashSet<>();
        }
    }

    Map<String, Pool> stringPools;
    Pool head;
    Pool tail;

    public AllOne() {
        head = new Pool(Integer.MIN_VALUE);
        tail = new Pool(Integer.MAX_VALUE);
        head.next = tail;
        tail.prev = head;
        stringPools = new HashMap<>();
    }

    public void inc(String key) {
        if (stringPools.containsKey(key)) {
            incExisting(key, stringPools.get(key));
        } else {
            incNonExisting(key);
        }
    }

    public void incExisting(String key, Pool curr) {
        // find the next index
        int nextIndex = curr.index + 1;

        // make sure the next pool exists
        if (curr.next.index != nextIndex) {
            Pool next = new Pool(nextIndex);
            next.next = curr.next;
            next.prev = curr;

            curr.next.prev = next;
            curr.next = next;
        }

        // remove the key from the current pool
        stringPools.remove(key);
        curr.members.remove(key);

        // add the key to the next pool
        stringPools.put(key, curr.next);
        curr.next.members.add(key);

        // remove the previous pool if needed
        if (curr.members.isEmpty()) {
            curr.next.prev = curr.prev;
            curr.prev.next = curr.next;
        }
    }

    public void incNonExisting(String key) {
        // add the pool for index 1 if necessary
        if (head.next.index != 1) {
            Pool curr = new Pool(1);

            curr.next = head.next;
            curr.prev = head;

            head.next.prev = curr;
            head.next = curr;
        }

        head.next.members.add(key);
        stringPools.put(key, head.next);
    }

    // guaranteed to exist
    public void dec(String key) {
        Pool curr = stringPools.get(key);
        int nextIndex = curr.index - 1;

        // remove key from current pool
        curr.members.remove(key);
        stringPools.remove(key);

        // make sure the next node exists
        if (nextIndex != 0 && nextIndex != curr.prev.index) {
            Pool next = new Pool(nextIndex);

            // align this node
            next.next = curr;
            next.prev = curr.prev;

            // align the other nodes
            curr.prev.next = next;
            curr.prev = next;
        }

        // add key to the next node only if nextIndex is not 0
        if (nextIndex != 0) {
            curr.prev.members.add(key);
            stringPools.put(key, curr.prev);
        }

        // remove key from the current node if needed
        if (curr.members.isEmpty()) {
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
        }
    }

    public String getMaxKey() {
        return tail.prev.members.stream().findFirst().orElse(EMPTY);
    }

    public String getMinKey() {
        return head.next.members.stream().findFirst().orElse(EMPTY);
    }
}