JMP MAIN

F_IDIV:
        MVI D,00        

LOOP:
        MOV A,B        
        CMP C           

        JC FIM          

        SUB C           
        MOV B,A        

        INR D           

        JMP LOOP

FIM:
        MOV A,D        
        RET

MAIN:
        LXI H,0050

        MOV B,M         

        INX H

        MOV C,M       

C_IDIV: CALL F_IDIV

        STA 0052

        HLT

END