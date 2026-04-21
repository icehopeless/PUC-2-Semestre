// Guia_0802 - CPL2 + Somador
// Nome: Gabriel Agostinho da Silva
// Matricula: 903104
// ------------------------- 

module complemento1 (output [5:0] y, input [5:0] x);

not (y[0], x[0]);
not (y[1], x[1]);
not (y[2], x[2]);
not (y[3], x[3]);
not (y[4], x[4]);
not (y[5], x[5]);

endmodule

module halfAdder (output s1, output s0, input a, input b); 
 
// descrever por portas 
xor  XOR1 ( s0, a, b ); //soma
and AND1 ( s1, a, b ); //carry

endmodule // halfAdder 
 
// ------------------------- 
//  full adder 
// -------------------------
// um full adder é dado por (a+b+carryIn) e ((a.b) + carryin.(a+b)) ou de forma mais simples = (A+B) + CARRYIN
module fullAdder ( output s1, output s0,  input   a,  input   b,  input carryIn); 
    wire soma0;
    wire carry0, carry1;

    //nesses dois halfs, vamos fazer A+B + carryIn
    halfAdder HA1(carry0, soma0, a, b);
    halfAdder HA2(carry1, s0, soma0, carryIn);

    //retorna true caso haja carry em alguma das operacoes
    or OR1(s1, carry0, carry1);

endmodule 

module test_CPL2;

reg  [5:0] x;
wire [5:0] cpl1;    
wire [5:0] cpl2;    
wire [5:0] carry;

//complemento de 1
complemento1 C1 (cpl1, x);

// complemento 2 - somar +1 com full adder
fullAdder FA0 (carry[0], cpl2[0], cpl1[0], 1'b1, 1'b0);


fullAdder FA1 (carry[1], cpl2[1], cpl1[1], 1'b0, carry[0]);
fullAdder FA2 (carry[2], cpl2[2], cpl1[2], 1'b0, carry[1]);
fullAdder FA3 (carry[3], cpl2[3], cpl1[3], 1'b0, carry[2]);
fullAdder FA4 (carry[4], cpl2[4], cpl1[4], 1'b0, carry[3]);
fullAdder FA5 (carry[5], cpl2[5], cpl1[5], 1'b0, carry[4]);

initial begin
    $display("x       | ~x    | cpl2(x)");

    x = 6'b000001; #1; 
    $display("%b | %b | %b", x, cpl1, cpl2);

    x = 6'b000010; #1; 
    $display("%b | %b | %b", x, cpl1, cpl2);

    x = 6'b000101; #1; 
    $display("%b | %b | %b", x, cpl1, cpl2);

    x = 6'b001000; #1; 
    $display("%b | %b | %b", x, cpl1, cpl2);

end

endmodule


