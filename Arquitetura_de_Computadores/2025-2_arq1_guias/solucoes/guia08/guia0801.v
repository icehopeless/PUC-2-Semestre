// Guia_0801 - full adder
// Nome: Gabriel Agostinho da Silva
// Matricula: 903104
// ------------------------- 

// ------------------------- 
//  half adder 
// ------------------------- 
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

module AU (output [5:0] soma, output carryOut, input  [5:0] x, input  [5:0] y);

wire [5:0] carry;

fullAdder FA0 (carry[0], soma[0], x[0], y[0], 1'b0);


fullAdder FA1 (carry[1], soma[1], x[1], y[1], carry[0]);
fullAdder FA2 (carry[2], soma[2], x[2], y[2], carry[1]);
fullAdder FA3 (carry[3], soma[3], x[3], y[3], carry[2]);
fullAdder FA4 (carry[4], soma[4], x[4], y[4], carry[3]);

fullAdder FA5 (carry[5], soma[5], x[5], y[5], carry[4]);

//assign carryOut = carry[5];
buf (carryOut, carry[5]);

endmodule

module test_AU;

reg  [5:0] x, y;
wire [5:0] soma;
wire carryOut;

AU uut (soma, carryOut, x, y);

initial begin
    $display("x       y     | soma    carry");

    x = 6'b000000; y = 6'b000000; #1;
    $display("%b %b | %b   %b", x, y, soma, carryOut);

    x = 6'b000101; y = 6'b000011; #1;
    $display("%b %b | %b   %b", x, y, soma, carryOut);

    x = 6'b001111; y = 6'b000001; #1;
    $display("%b %b | %b   %b", x, y, soma, carryOut);

    x = 6'b100001; y = 6'b000001; #1;
    $display("%b %b | %b   %b", x, y, soma, carryOut);

end

endmodule

/*
module test_fullAdder; 

reg   [3:0] x; 
reg   [3:0] y; 
reg   ci;
wire  carry; 
wire  soma; 

fullAdder FA0 (carry, soma, x[0], y[0], ci); 

initial begin 
    $display("a b ci | soma carry");

    ci=0; x=0; y=0; #1;
    $display("%b %b  %b  |   %b     %b", x[0], y[0], ci, soma, carry);

    ci=0; x=1; y=0; #1;
    $display("%b %b  %b  |   %b     %b", x[0], y[0], ci, soma, carry);

    ci=0; x=1; y=1; #1;
    $display("%b %b  %b  |   %b     %b", x[0], y[0], ci, soma, carry);

    ci=1; x=1; y=1; #1;
    $display("%b %b  %b  |   %b     %b", x[0], y[0], ci, soma, carry);

end 

endmodule

*/