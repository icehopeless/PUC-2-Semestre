// ------------------------- 
// Guia_0506.v - GATES 
// Nome: Gabriel Agostinho da Silva
// Matricula: 903104
// ------------------------- 
 

//((a ^ b) = a xor b) com NOR
// ^ = XOR
//XOR = (a & ~b) | (~a & b);
module f5a ( output s, input a, input b ); 

    wire not_a;
    wire not_b;
    wire and1;
    wire and2;
    wire or_temp;

    //negando a e b
    nor NOR1(not_a, a, a);
    nor NOR2(not_b, b, b);

    // (a & ~b)
    nor NOR3(and1, not_a, b);

    // (~a & b)
    nor NOR4(and2, a, not_b);

    // OR entre os dois
    nor NOR5(or_temp, and1, and2);
    nor NOR6(s, or_temp, or_temp);
  
endmodule

module test_f5; 

       reg  x; 
       reg  y; 
       wire a;
 
       f5a moduloA ( a, x, y ); 
 
 
   initial 
   begin : main 
          $display("Guia_0506 - Gabriel Agostinho da Silva - 903104"); 
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





