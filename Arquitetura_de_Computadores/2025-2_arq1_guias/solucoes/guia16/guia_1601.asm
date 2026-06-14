JMP MAIN

F_IMUL:
        MVI A,00      

LOOP:
        MOV D,C       
        MOV E,D
        MOV D,A

        MOV A,E
        ORA A
        JZ FIM

        MOV A,D
        ADD B       // resultado += dado01

        DCR C         // contador--

        JMP LOOP

FIM:
        RET

MAIN:
        LXI H,0050

        MOV B,M       // dado01

        INX H

        MOV C,M       // dado02

C_IMUL: CALL F_IMUL

        STA 0052

        HLT

END