; Mark Weiman
; COSC 221
; Lab 7
;
; TO BE USED WITH LC-3

        .ORIG x3000         ; Start at 0x3000
        
        LD R6, NEGFE        ; Load -48 to R6

        LEA R0, PROMPT      ; Load prompt
        LEA R1, INSTOR      ; Ready storage
        PUTS                ; Display prompt

        GETC                ; Get char
        OUT                 ; Output char
        ADD R0, R0, R6      ; Fix input
        STR R0, R1, #0      ; Store input

        GETC                ; Get char
        OUT                 ; Output char
        ADD R0, R0, R6      ; Fix input
        STR R0, R1, #1      ; Store input

        LDR R2, R1, #0      ; Load first digit
        LDR R3, R1, #1      ; Load second digit
        LD R4, NEGTEN       ; Load -10

        LD R0, TEN          ; Load 10
        OUT                 ; Output newline (10)

        ADD R5, R2, R3      ; Add both digits
        ADD R5, R5, R4      ; Subtract 10
        BRn ONEDIG          ; Branch if negative

        LD R0, FE           ; Load 48
        ADD R0, R0, #1      ; Add 1 to R0
        OUT                 ; Output 1
        LD R1, NEGONE       ; Load -1
        ADD R0, R0, R5      ; Add R5 to R0
        ADD R0, R0, R1      ; Subtract 1 from R0
        OUT                 ; Output second digit
        HALT                ; Halt program

ONEDIG  LD R1, TEN          ; Load 10
        ADD R0, R5, R1      ; Add 10 to R5 and store to R0
        LD R1, FE           ; Load 48
        ADD R0, R0, R1      ; Add 48 to R0
        OUT                 ; Output character
        HALT                ; Halt program

        ; data
PROMPT  .STRINGZ "Enter two integers: "
NEGFE   .FILL xFFD0
FE      .FILL x0030
NEGONE  .FILL xFFFF
NEGTEN  .FILL xFFF6
TEN     .FILL x000A
INSTOR  .BLKW #2

        .END
