
typedef struct stack_entry {
    int val;
    int nextmin;
} stack_entry_t;

#define MIN_STACK_INIT_CAPACITY 16

typedef struct {
    int capacity;
    int nelem;
    int minval;

    int topptr;
    int minptr;
    stack_entry_t* stack;
} MinStack;

MinStack* minStackCreate() {
    MinStack* s = malloc(sizeof(MinStack));

    s->capacity = MIN_STACK_INIT_CAPACITY;
    s->nelem = 0;
    s->minval = INT_MAX;

    s->stack = calloc(MIN_STACK_INIT_CAPACITY, sizeof(stack_entry_t));
    s->topptr = -1;
    s->minptr = 0;

    return s;
}

void minStackPush(MinStack* obj, int val) {
    obj->nelem++;
    if (obj->nelem > obj->capacity) {
        obj->capacity *= 2;
        obj->stack = realloc(obj->stack, sizeof(stack_entry_t) * obj->capacity);
        // printf("%d\n", obj->capacity);
    }

    // printf("val: %d\n", val);

    obj->topptr++;
    obj->stack[obj->topptr].val = val;

    if (obj->nelem == 1) {
        obj->minptr = obj->topptr;
        obj->minval = val;
        return;
    }

    if (val < obj->minval) {
        obj->minval = val;
        obj->stack[obj->topptr].nextmin = obj->minptr;
        obj->minptr = obj->topptr;
    }
}

void minStackPop(MinStack* obj) {
    obj->nelem--;

    if (obj->nelem != 0 && obj->topptr == obj->minptr) {
        obj->minptr = obj->stack[obj->topptr].nextmin;
        obj->minval = obj->stack[obj->minptr].val;
    }

    obj->topptr--;
}

int minStackTop(MinStack* obj) { return obj->stack[obj->topptr].val; }

int minStackGetMin(MinStack* obj) { return obj->stack[obj->minptr].val; }

void minStackFree(MinStack* obj) {
    free(obj->stack);
    free(obj);
}

/**
 * Your MinStack struct will be instantiated and called as such:
 * MinStack* obj = minStackCreate();
 * minStackPush(obj, val);

 * minStackPop(obj);

 * int param_3 = minStackTop(obj);

 * int param_4 = minStackGetMin(obj);

 * minStackFree(obj);
*/
