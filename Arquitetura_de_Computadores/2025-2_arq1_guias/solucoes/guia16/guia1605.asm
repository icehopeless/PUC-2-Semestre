JMP   MAIN        
                                  
F_BCD:     POP   H           
                                  
               POP   B           
               MOV   A,C        
               ANI   0F          
               MOV   D,A        

               MOV   A,C        
               ANI   F0          
               RAR               
               RAR               
               RAR               
               RAR               
                                  

               MOV   E,A        
               MVI   B,0A      

LOOP:      DCR   B           
               JZ    MULT        
               ADD   E           
               JMP   LOOP       

MULT:      ADD   D           
               PUSH  H           
               RET               
                                  
                         
MAIN:      LXI   H,0050     
               MVI   B,00       
               MOV   C,M       
               PUSH  B          
               CALL  F_BCD    
               STA   0052       
               HLT              
END:

