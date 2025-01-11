char roman[] = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};
int values[] = {1, 5, 10, 50, 100, 500, 1000};

char* intToRoman(int num) {
    char* ret = (char*)malloc(20*sizeof(char));

    int div = 1000;
    int retptr = 0;
    int valptr = 6;

    while (div != 0) {
        int d = num / div;
        if (d == 4 || d == 9) {
            if (d == 4 && div == 1) {
                ret[retptr++] = 'I';
                ret[retptr++] = 'V';
                num -= 4;
                valptr--;
            } else if (d == 9 && div == 1) {
                ret[retptr++] = 'I';
                ret[retptr++] = 'X';
                num -= 9;
            } else if (d == 4 && div == 10) {
                ret[retptr++] = 'X';
                ret[retptr++] = 'L';
                num -= 40;
            } else if (d == 9 && div == 10) {
                ret[retptr++] = 'X';
                ret[retptr++] = 'C';
                num -= 90;
            } else if (d == 4 && div == 100) {
                ret[retptr++] = 'C';
                ret[retptr++] = 'D';
                num -= 400;
            } else if (d == 9 && div == 100) {
                ret[retptr++] = 'C';
                ret[retptr++] = 'M';
                num -= 900;
            }
        } else {
            if (num - values[valptr] >= 0) {
                ret[retptr++] = roman[valptr];
                num -= values[valptr];
            } else {
                valptr--;
            }
        }

        if (d == 0){
            div /= 10;
        }
    }

    ret[retptr] = '\0';
    return ret;
}
