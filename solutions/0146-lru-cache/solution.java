class LRUCache {

    class Node {
        int key, val;
        Node prev, next;

        Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    HashMap<Integer, Node> map = new HashMap<>();

    int capacity;
    int occupancy;
    Node head;

    public LRUCache(int _capacity) {
        capacity = _capacity;
        occupancy = 0;
        head = new Node(-1, -1);
        head.next = head;
        head.prev = head;
    }

    public void ll_insert_front(Node node, boolean mapping) {
        Node next = head.next;
        head.next = node;
        node.prev = head;
        node.next = next;
        next.prev = node;
        if (mapping)
            map.put(node.key, node);
        occupancy++;
    }

    // do not use on empty ll!!
    public void ll_remove_stitch(Node node, boolean mapping) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
        if (mapping)
            map.remove(node.key);
        occupancy--;
    }

    public void ll_print() {
        Node iter = head.next;
        int i = 0;
        while (iter != head) {
            System.out.printf("node%d: {%d, %d},  ", i++, iter.val, -1);
            iter = iter.next;
        }
        System.out.printf("\n");
    }

    public int get(int key) {
        if (occupancy == 0)
            return -1;
        Node n = map.getOrDefault(key, null);
        if (n == null)
            return -1;

        // move up the node
        ll_remove_stitch(n, false);
        ll_insert_front(n, false);

        return n.val;
    }

    public void put(int key, int value) {

        Node n = map.get(key);
        if (n != null) {
            n.val = value;
            ll_remove_stitch(n, false);
            ll_insert_front(n, false);
        } else {
            if (occupancy >= capacity) {
                Node lru = head.prev;
                ll_remove_stitch(lru, true);
            }
            Node nn = new Node(key, value);
            ll_insert_front(nn, true);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
