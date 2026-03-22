// ------------------------- 
// Guia_0502.v - GATES 
// Nome: Gabriel Agostinho da Silva
// Matricula: 903104
// ------------------------- 
 

//~(~a | b) apenas com NAND
//(a . ~b)
module f5a ( output s, input a, input b ); 

    wire not_b;
    wire nand_ab;

    nand NAND1(not_b, b, b);
    
    //and com nand
    nand NAND2(nand_ab, a, not_b);
    nand NAND3 (s, nand_ab , nand_ab); //s e a saida output


endmodule

module test_f5; 
// ------------------------- definir dados 
       reg  x; 
       reg  y; 
       wire a;
 
       f5a moduloA ( a, x, y ); 
 
// ------------------------- parte principal 
 
   initial 
   begin : main 
          $display("Guia_0502 - Gabriel Agostinho da Silva - 903104"); 
          $display("Test module"); 
          $display("   x     y    a   "); 
 
       // projetar testes do modulo 
         $monitor("%4b %4b %4b", x, y, a); 
            x = 1'b0;  y = 1'b0; 
   #1       x = 1'b0;  y = 1'b1; 
   #1       x = 1'b1;  y = 1'b0; 
   #1       x = 1'b1;  y = 1'b1; 
 
   end 
 
endmodule





