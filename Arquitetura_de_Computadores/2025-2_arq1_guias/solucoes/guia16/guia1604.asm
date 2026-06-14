            JMP MAIN

MAIN:       LXI H,0050
            MOV A,M
            INX H
            MVI B,04

LOOP:       MOV C,M
            CMP C
            JC NO_UPDATE
            MOV A,C

NO_UPDATE:  INX H
            DCR B
            JNZ LOOP

STA 0055
HLT