; Mark Weiman
; COSC 221
; Lab 10
;
; TO BE USED WITH LC-3

        .ORIG x3000

        LD R1, SAMPLE       ; LOAD WORD
        JSR THING           ; CHECK WORD
        LD R2, FE           ; LOAD 48 TO R2
        ADD R0, R0, R2      ; ADD 48 TO RESULT
        OUT                 ; OUTPUT
        HALT                ; HALT

        ; THING
        ; COUNTS NUMBER OF ACTIVE BITS IN WORD
        ; RETURNS R0, WORD TO CHECK IN R1
THING   ST R2, TMP1         ; BACKUP R2
        ST R3, TMP3         ; BACKUP R3
        ST R4, TMP4         ; BACKUP R4
        ST R5, TMP2         ; BACKUP R5
        AND R4, R4, x0000   ; ZERO R4
        AND R0, R0, x0000   ; ZERO R0
        AND R2, R2, x0000   ; ZERO R2
        ADD R2, R2, x0001   ; INC R2

ASDF    AND R3, R1, R2      ; CHECK BIT
        BRnp ADDO           ; BRANCH IF ACTIVE
        ADD R2, R2, R2      ; SHIFT R2 (LEFT)
        ADD R4, R4, x0001   ; ADD ONE TO R4
        LD R5, SIXT         ; LOAD -16
        ADD R5, R5, R4      ; ADD R5 AND R4
        BRz RETU            ; IF NEG, RETURN
        LEA R5, ASDF        ; LOAD ASDF ADDRESS
        JMP R5              ; JUMP TO ASDF
ADDO    ADD R0, R0, x0001   ; ADD ONE TO R0
        ADD R2, R2, R2      ; SHIFT R2 (LEFT
        ADD R4, R4, x0001   ; ADD ONE TO R4
        LD R5, SIXT         ; LOAD -16
        ADD R5, R5, R4      ; ADD R5 AND R4
        LEA R5, ASDF        ; LOAD ASDF ADDR
        JMP R5              ; JUMP TO ASDF

RETU    LD R2, TMP1         ; RESTORE R2
        LD R3, TMP3         ; RESTORE R3
        LD R4, TMP4         ; RESTORE R4
        LD R5, TMP2         ; RESTORE R5
        RET                 ; RETURN
	
        ; DATA
FE      .FILL x0030
SIXT    .FILL xFFF0
SAMPLE  .FILL x8021
TMP1    .BLKW x0001
TMP2    .BLKW x0001
TMP3    .BLKW x0001
TMP4    .BLKW x0001

        .END
