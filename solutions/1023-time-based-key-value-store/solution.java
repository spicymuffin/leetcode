class TimeMap {
    class Entry {
        public String s;
        public int i;

        public Entry(String _s, int _i) {
            s = _s;
            i = _i;
        }
    }

    Map<String, List<Entry>> map;

    private int searchTimestamp(List<Entry> list, int ts) {
        if (list.size() == 0) {
            return -1;
        }

        int l = 0;
        int r = list.size() - 1;

        if (ts < list.get(0).i) {
            return -1;
        }

        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if (ts >= list.get(mid).i) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        // System.out.printf("l:%d, 0:%d\n", list.get(l).i, list.get(0).i);

        return l;
    }

    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (map.containsKey(key)) {
            map.get(key).add(new Entry(value, timestamp));
        } else {
            map.put(key, new ArrayList<>(Collections.singletonList(new Entry(value, timestamp))));
        }
    }

    public String get(String key, int timestamp) {
        List<Entry> list = map.getOrDefault(key, null);
        if (list == null)
            return "";

        int idx = searchTimestamp(list, timestamp);
        return idx == -1 ? "" : list.get(idx).s;
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
