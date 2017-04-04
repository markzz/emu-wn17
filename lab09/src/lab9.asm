; Mark Weiman
; COSC 221
; Lab 9
;
; TO BE USED WITH LC-3
        .ORIG x3000         ; begin at x3000

        LEA R0, PROMPT      ; load prompt
        PUTS                ; display prompt

        GETC                ; get char
        OUT                 ; output char
        AND R1, R1, #0      ; clear R1
        ADD R1, R1, R0      ; copy R0 to R1

        GETC                ; get char
        OUT                 ; output char
        AND R2, R2, #0      ; clear R2
        ADD R2, R2, R0      ; copy R0 to R2

        LD R0, LF           ; load line feed (x30)
        OUT                 ; output line feed

        JSR LARGER          ; run subroutine to check values

        OUT                 ; output largest number
        LEA R0, OUTPUT      ; load ending of line
        PUTS                ; display

        HALT                ; halt


        ; LARGER
        ; params, values to check in R1 and R2
        ; returns largest value in R0
LARGER  AND R3, R3, #0      ; clear R3
        NOT R3, R2          ; not R2 and store to R3
        ADD R3, R3, #1      ; add 1 to R3

        ADD R0, R1, R3      ; add R1 and R3 to store in R3
        BRn LESS            ; if negative, branch to LESS

        AND R0, R0, #0      ; clear R0
        ADD R0, R0, R1      ; copy R1 to R0
        RET                 ; return

LESS    AND R0, R0, #0      ; clear R0
        ADD R0, R0, R2      ; copy R2 to R0
        RET                 ; return

PROMPT  .STRINGZ "Enter two digits: "
OUTPUT  .STRINGZ " is larger."
LF      .FILL x000A

        .END
