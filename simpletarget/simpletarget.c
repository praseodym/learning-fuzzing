#include <stdio.h>

int main() {
    int n;
    scanf("%d", &n);
    switch (n) {
        case 1:
            printf("one!");
            break;
        case 42:
            printf("the answer");
            break;
        default:
            return 1;
    }
    return 0;
}