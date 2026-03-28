#define STOP_MAX INT_MAX / 10
#define STOP_MIN INT_MIN / 10

int reverse(int x) {
    int n, i, rev = 0;
    for (; x != 0; x /= 10) {
        int rem = x % 10;
        if ((rev > (STOP_MAX)) || (rev == INT_MAX / 10 && rem > 7))
            return 0;
        if ((rev < (STOP_MIN)) || (rev == INT_MIN / 10 && rem < -8))
            return 0;
        rev = rev * 10 + rem;
    }
    return rev;
}
