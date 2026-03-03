/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     struct TreeNode *left;
 *     struct TreeNode *right;
 * };
 */
/**
 * Return an array of arrays of size *returnSize.
 * The sizes of the arrays are returned as *returnColumnSizes array.
 * Note: Both returned array and *columnSizes array must be malloced, assume
 * caller calls free().
 */

typedef struct node {
    struct node* r;
    struct node* l;
    struct TreeNode* tn;
} node_t;

void enq(node_t* head, node_t* n) {
    node_t* next = head->r;

    n->r = next;
    n->l = head;
    head->r = n;
    next->l = n;
}

node_t* deq(node_t* head) {
    node_t* d = head->l;
    node_t* prev = d->l;

    prev->r = head;
    head->l = prev;

    return d;
}

int** levelOrder(struct TreeNode* root, int* returnSize,
                 int** returnColumnSizes) {

    node_t* head = malloc(sizeof(node_t));
    head->r = head;
    head->l = head;

    if (root == 0) {
        *returnSize = 0;
        *returnColumnSizes = 0;
        return 0;
    }

    node_t* rootnode = malloc(sizeof(node_t));
    rootnode->tn = root;

    int return_array_sz = 1;
    int** return_array = malloc(return_array_sz * sizeof(int*));
    *returnColumnSizes = malloc(return_array_sz * sizeof(int));

    int nnode = 1;
    int nlevel = 0;
    enq(head, rootnode);

    while (nnode > 0) {
        int nodes_on_current_level = nnode;

        int* level_values = malloc(nodes_on_current_level * sizeof(int));
        int* level_values_it = level_values;

        for (int i = 0; i < nodes_on_current_level; i++) {
            node_t* curnode = deq(head);
            nnode--;

            *level_values_it = curnode->tn->val;
            level_values_it++;

            node_t* new;
            if (curnode->tn->left != 0) {
                new = malloc(sizeof(node_t));
                new->tn = curnode->tn->left;
                enq(head, new);
                nnode++;
            }
            if (curnode->tn->right != 0) {
                new = malloc(sizeof(node_t));
                new->tn = curnode->tn->right;
                enq(head, new);
                nnode++;
            }

            free(curnode);
        }

        nlevel++;
        if (nlevel > return_array_sz){
            return_array_sz *= 2;
            return_array = realloc(return_array, return_array_sz * sizeof(int*));
            *returnColumnSizes = realloc(*returnColumnSizes, return_array_sz * sizeof(int));
        }
        return_array[nlevel-1] = level_values;
        (*returnColumnSizes)[nlevel-1] = nodes_on_current_level;
    }

    *returnSize = nlevel; 
    return return_array;
}
