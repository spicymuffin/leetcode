using namespace std;

class LRUCache {
public:
    typedef struct ll_node {
        struct ll_node* next;
        struct ll_node* prev;
        int value;
        int key;
    } ll_node_t;

    ll_node_t head;
    ll_node_t* cache;

    int cache_write_ptr = 0;

    unordered_map<int, ll_node_t*> addr_lookup_tbl;
    int cache_sz;

    inline void ll_insert_after(ll_node_t* insert_after, ll_node_t* node) {
        ll_node_t* next = insert_after->next;

        insert_after->next = node;
        node->prev = insert_after;

        node->next = next;
        next->prev = node;
    }

    inline void ll_delete(ll_node_t* node) {
        ll_node_t* prev = node->prev;
        ll_node_t* next = node->next;

        prev->next = next;
        next->prev = prev;
    }

    LRUCache(int capacity) {
        cache_sz = capacity;
        cache = new ll_node_t[capacity];
        head.next = &head;
        head.prev = &head;
        head.key = 6969;
        head.value = 0;
    }

    ~LRUCache() { delete[] cache; }

    int get(int key) {
        if (addr_lookup_tbl.count(key)) {
            // if get happened, set as LRU and return value
            ll_node_t* addr = addr_lookup_tbl[key];

            ll_delete(addr);
            ll_insert_after(&head, addr);
            return addr->value;
        } else {
            return -1;
        }
    }

    void put(int key, int value) {
        if (addr_lookup_tbl.count(key)) {
            // if key in cache o/w value
            ll_node_t* addr = addr_lookup_tbl[key];
            addr->value = value;

            // set as LRU
            ll_delete(addr);
            ll_insert_after(&head, addr);
        } else {
            if (cache_write_ptr < cache_sz) {
                // if LRU not full just make new entry and link (set as LRU)
                cache[cache_write_ptr].value = value;
                cache[cache_write_ptr].key = key;

                ll_insert_after(&head, &cache[cache_write_ptr]);

                // add to addr_lookup_tbl
                addr_lookup_tbl[key] = &cache[cache_write_ptr];

                // prepare for next write
                cache_write_ptr++;
            } else {
                // if LRU full evict
                ll_node_t* addr = head.prev;

                // evict key from addr table
                addr_lookup_tbl.erase(addr->key);
                // insert new key to addr table
                addr_lookup_tbl[key] = addr;

                // overwrite value at evicted node
                addr->value = value;
                addr->key = key;

                // set evicted node as LRU
                ll_delete(addr);
                ll_insert_after(&head, addr);
            }
        }
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache* obj = new LRUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */
