JMP MAIN

F_IMOD:
LOOP:
        MOV A,B         // A = dividendo atual

        CMP C          

        JC FIM          // se A < C termina

        SUB C           // A = A - C

        MOV B,A         

        JMP LOOP

FIM:
        MOV A,B         
        RET

MAIN:
        LXI H,0050

        MOV B,M        

        INX H

        MOV C,M         

C_IMOD: CALL F_IMOD

        STA 0052

        HLT

END