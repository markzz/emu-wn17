; Mark Weiman
; COSC 221
; Lab 6
;
; TO BE USED WITH LC-3
; Also, the lack of a mov instruction sucks...

         .ORIG x3000           ; Start at 0x3000

         LD R2, THRTWO         ; Load value 0xFFE0

         LEA R0, INPROMPT      ; Load prompt's beginning addr to R0
         LEA R1, CHARSTOR      ; Load address of CHARSTOR to R0
         PUTS                  ; Print prompt in R0

         GETC                  ; Get character from keyboard
         OUT                   ; Output character in R0
         STR R0, R1, #0        ; Store value in R0 to *(R1)
         GETC                  ; Get character from keyboard
         OUT                   ; Output character in R0
         STR R0, R1, #1        ; Store value in R0 to *(R1 + 1)
         GETC                  ; Get character from keyboard
         OUT                   ; Output character in R0
         STR R0, R1, #2        ; Store value in R0 to *(R1 + 2)

         JSR PRINTLF           ; Print line feed (\n)

         LDR R0, R1, #0        ; Load value in *(R1) to R0
         ADD R0, R0, R2        ; Add R0 and R2 to R0
         OUT                   ; Output character in R0
         JSR PRINTLF           ; Print line feed (\n)

         LDR R0, R1, #1        ; Load value in *(R1 + 1) to R0
         ADD R0, R0, R2        ; Add R0 and R2 to R0
         OUT                   ; Output character in R0
         JSR PRINTLF           ; Print line feed (\n)

         LDR R0, R1, #2        ; Load value in *(R1 + 2) to R0
         ADD R0, R0, R2        ; Add R0 and R2 to R0
         OUT                   ; Output character in R0

         HALT                  ; HALT

PRINTLF  LD R0, LF
         STI R7, TMPSTOR 
         OUT
         LDI R7, TMPSTOR
         JMP R7

         ; Data
INPROMPT .STRINGZ "Enter initials: "
LF       .FILL x000A
THRTWO   .FILL xFFE0
TMPSTOR  .BLKW #1
CHARSTOR .BLKW #3
         
         .END                  ; END
