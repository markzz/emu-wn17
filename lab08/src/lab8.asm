; Mark Weiman
; COSC-221
; Lab 8

        .ORIG x3000         ; start at x3000

        LEA R0, PROMPT      ; load prompt to R0
        PUTS                ; display prompt

        LEA R1, INSTOR      ; load address of storage
        AND R2, R2, x0000   ; clear R2

INPUT   GETC                ; get char
        OUT                 ; output char
        STR R0, R1, x0000   ; store R0 to R1
        ADD R1, R1, x0001   ; increment R1
        ADD R2, R2, x0001   ; Increment R@ 
        
        ADD R0, R0, xFFF3   ; add xFFF3 to R0
        BRz ESCAPE          ; if zero, branch to ESCAPE
        
        JSR INPUT           ; jump back to INPUT

ESCAPE  LEA R0, FORWD       ; load informative string to R0
        PUTS                ; display string in R0
        LEA R0, INSTOR      ; load INSTOR to R0
        PUTS                ; display string

        LEA R0, BAKWD       ; Load informative string to R0
        PUTS                ; display string in R0

        LEA R5, INSTOR      ; load INSTOR to R5
        LEA R1, INSTOR      ; load INSTOR to R1
        NOT R1, R1          ; not R1
        ADD R1, R1, x0001   ; add 1 to R1
        ADD R2, R2, xFFFF   ; add -1 to R2
        ADD R5, R5, R2      ; add R2 to R5
        
OUTLOOP ADD R5, R5, xFFFF   ; Add -1 to R5
        LDR R0, R5, x0000   ; load value in R5's value's address to R0
        OUT                 ; output char
        ADD R3, R5, R1      ; add R5 and R1 and store in R3
        BRp OUTLOOP         ; if positive, branch to OUTLOOP

        AND R0, R0, x0000   ; zero out R0
        ADD R0, R0, x000A   ; add 10 to R0
        OUT                 ; output char (\n)

STRLENO LEA R0, STRLEN      ; load informative text to R0
        PUTS                ; display text

        AND R0, R0, x0000   ; zero out R0
        ADD R0, R0, R2      ; add R2 to R0
        LD R1, FE           ; load FE to R1
        ADD R0, R0, R1      ; add R1 to R0
        OUT                 ; output char

        HALT                ; halt
        

PROMPT  .STRINGZ "Please enter a string (at most 9 characters): "
FORWD   .STRINGZ "Forward string:  "
BAKWD   .STRINGZ "Backward string: "
STRLEN  .STRINGZ "String length:   "
INSTOR  .BLKW x000A
FE      .FILL x0030

        .END
