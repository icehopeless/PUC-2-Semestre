// ------------------------- 
// Guia_0502.v - GATES 
// Nome: Gabriel Agostinho da Silva
// Matricula: 903104
// ------------------------- 
 

//(~a | ~b)
module f5a ( output s, input a, input b ); 
    wire not_a;
    wire not_b;
    wire nor_ab;

    //primeiro, negamos a e b com nor
    nor NOR1(not_a, a, a);
    nor NOR2(not_b, b, b);

    //agora, fazemos o nor entre not_a e not_b
    //or com nor = nor(a,b) nor nor(a,b)

    nor NOR3 (nor_ab, not_a,not_b);
    nor NOR4 (s, nor_ab, nor_ab); //s e a saida output


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





