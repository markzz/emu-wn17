/*
 * "Now do it in C." --Andrew
 *
 * Okay... The goal of this one is to NOT use string.h.
 */

#include <stdio.h>   /* scanf and puts */
#include <stdlib.h>  /* malloc and free */

int show_prompt() {
    int ret;

    puts("1. Convert decimal to binary");
    puts("2. Convert binary to decimal");
    puts("3. Quit");
    printf("\nMake selection: ");

    scanf("%d", &ret);
    return ret;
}

char *to_binary(int i) {
    int j;
    char *ret = (char*)malloc(9);

    for (j = 7; j >= 0; j--) {
        *(ret+(7-j)) = i >= 1 << j ? 49 : 48;
        i -= i >= 1 << j ? 1 << j : 0;
    }
    *(ret+8) = '\0';

    return ret;
}

int to_dec(const char *s) {
    int ret = 0;
    int i;
    char c;

    for (i = 0; i < 8; i++) {
        ret += *(s + i) == 49 ? 1 << (7-i) : 0;
    }

    return ret;
}

int main(int argc, char *argv) {
    int selection = 0;
    int dec;
    char *bin = (char*)malloc(9);
    char *s;

    while (1) {
        selection = show_prompt();
        
        if (selection == 1) {
            printf("Enter decimal number: ");
            scanf("%d", &dec);
            s = to_binary(dec);
            printf("%d -> %s\n\n", dec, s);
            free(s);
        } else if (selection == 2) {
            printf("Enter binary number: ");
            scanf("%s", bin);
            printf("%s -> %d\n\n", bin, to_dec(bin));
        } else {
            puts("Good bye.");
            break;
        }
    }

    free(bin);
    return 0;
}
