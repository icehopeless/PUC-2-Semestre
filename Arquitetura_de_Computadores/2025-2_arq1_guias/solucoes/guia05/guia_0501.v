// ------------------------- 
// Guia_0501.v - GATES 
// Nome: Gabriel Agostinho da Silva
// Matricula: 903104
// ------------------------- 
 

 // ------------------------- 
// Guia_0501.v 
// Nome: Gabriel Agostinho da Silva
// ------------------------- 

//exemplos

/*
module f5a ( output s, input  a, input  b ); 
// definir dado local 
   wire not_a; 
// descrever por portas 
   not NOT1  ( not_a, a ); 
   and AND1 ( s, not_a, b ); 
endmodule 
 


module f5b ( output s, input  a, input  b ); 
// descrever por expressao 
   assign s = ~a & b; 
endmodule 


//TESTE
module test_f5; 
// ------------------------- definir dados 
       reg  x; 
       reg  y; 
       wire a, b; 
 
       f5a moduloA ( a, x, y ); 
       f5b moduloB ( b, x, y ); 
 
// ------------------------- parte principal 
 
   initial 
   begin : main 
          $display("Guia_0500 - xxx yyy zzz - 999999"); 
          $display("Test module"); 
          $display("   x     y    a    b"); 
 
       // projetar testes do modulo 
         $monitor("%4b %4b %4b %4b", x, y, a, b); 
            x = 1'b0;  y = 1'b0; 
   #1       x = 1'b0;  y = 1'b1; 
   #1       x = 1'b1;  y = 1'b0; 
   #1       x = 1'b1;  y = 1'b1; 
 
   end 
 
endmodule

*/


//(~a & ~b)
module f5a ( output s, input a, input b ); 
    wire not_a;
    wire not_b;
    wire nand_ab;

    //primeiro, negamos a e b com nand
    //sabemos que nanda(a,a) = not(a)
    //o modelo do verilog PORTA (saida, entrada, entrada)
    nand NAND1 (not_a, a, a);
    nand NAND2 (not_b, b, b);

    //agora, precisamos fazer o and entre os dois com o nand
    //sabemos que nand(nand(a,b)) = and (a,b)
    //entao, fazemos o nand de a,b e depois o nand do resultado

    nand NAND3 (nand_ab, not_a, not_b);
    nand NAND4 (s, nand_ab, nand_ab); //s e a saida output


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
          $display("Guia_0501 - Gabriel Agostinho da Silva - 903104"); 
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





