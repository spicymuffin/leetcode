class Robot {

    int direction = 0;

    int[] coords = new int[2];

    int pos;

    int w;
    int h;

    int[] stops = new int[4];
    static final String[] direction_labels = new String[] { "East", "North", "West", "South" };

    public Robot(int _w, int _h) {
        w = _w;
        h = _h;

        stops[0] = w - 1;
        stops[1] = w + h - 2;
        stops[2] = w + w + h - 3;
        stops[3] = w + w + h + h - 4;
    }

    public void step(int num) {
        if (num == 0)
            return;

        pos = (pos + num) % stops[3];

        if (pos == 0) {
            coords[0] = 0;
            coords[1] = 0;
            direction = 3;
        } else if (pos <= stops[0]) {
            coords[0] = pos;
            coords[1] = 0;
            direction = 0;
        } else if (pos <= stops[1]) {
            coords[0] = w - 1;
            coords[1] = pos - stops[0];
            direction = 1;
        } else if (pos <= stops[2]) {
            coords[0] = w - 1 - (pos - stops[1]);
            coords[1] = h - 1;
            direction = 2;
        } else {
            coords[0] = 0;
            coords[1] = h - 1 - (pos - stops[2]);
            direction = 3;
        }
    }

    public int[] getPos() {
        return coords;
    }

    public String getDir() {
        return direction_labels[direction];
    }
}

/**
 * Your Robot object will be instantiated and called as such:
 * Robot obj = new Robot(width, height);
 * obj.step(num);
 * int[] param_2 = obj.getPos();
 * String param_3 = obj.getDir();
 */
