// ------------------------- 
// Guia_0505.v - GATES 
// Nome: Gabriel Agostinho da Silva
// Matricula: 903104
// ------------------------- 
 

// (~(a ^ b) = a xnor b) apenas com NAND
// ^ = XOR
// XOR negado = XNOR(a,b)
//XOR = (a & ~b) | (~a & b);
module f5a ( output s, input a, input b ); 

    wire not_a;
    wire not_b;
    wire nand_ab1;
    wire nand_ab2;
    wire and_nand1;
    wire and_nand2;
    wire or_nand1;
    wire or_nand2;
    wire or_nand_final;
    wire xnor_f;

    //not a, not b
    nand NANDA1(not_a, a, a);
    nand NANDA2(not_b, b, b);

    //1 and com nand 
    nand NAND3(nand_ab1, a, not_b);
    nand NAND4(and_nand1, nand_ab1, nand_ab1);

    //2 and com nand
    nand NAND5(nand_ab2, not_a, b);
    nand NAND6(and_nand2, nand_ab2, nand_ab2);

    //OR COM NAND
    nand NAND7(or_nand1, and_nand1, and_nand1);
    nand NAND8(or_nand2, and_nand2, and_nand2);
    nand NAND9(or_nand_final, or_nand1, or_nand2);

    //negando o XOR = (a & ~b) | (~a & b);
    nand NAND10(s, or_nand_final, or_nand_final);

    //nao e a forma mais otimizada de fazer uma porta XNOR, mas seguindo o sentido litera, esta correto.
  
endmodule

module test_f5; 

       reg  x; 
       reg  y; 
       wire a;
 
       f5a moduloA ( a, x, y ); 
 
 
   initial 
   begin : main 
          $display("Guia_0505 - Gabriel Agostinho da Silva - 903104"); 
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





