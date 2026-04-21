// Guia_0802 - full subtrator
// Nome: Gabriel Agostinho da Silva
// Matricula: 903104
// ------------------------- 

// ------------------------- 
//  half subtrator 
// ------------------------- 
module halfSubtractor (output s1, output s0, input a, input b); 
 
// descrever por portas 
xor  XOR1 ( s0, a, b );
wire not_a;
not (not_a, a);
and AND1 ( s1, not_a, b ); 

endmodule
 
// ------------------------- 
//  full subtrator
// -------------------------
// difference = a xor b
// borrow in = a' and b
//borrow out = (difference) xor (borrow in)
module fullSubtractor ( output s1, output s0,  input   a,  input   b,  input borrow_in); 
    wire diff;
    wire borrow0, borrow1;

    // a xor b (a-b)
    halfSubtractor HS1(borrow0, diff, a, b);

    //(a xor b) xor borrowIn
    halfSubtractor HS2(borrow1, s0, diff, borrow_in);

    //conjuncao dos borrows
    or (s1, borrow0, borrow1);
endmodule 


module AU (output [5:0] dif, output borrowOut, input  [5:0] x, input  [5:0] y);

wire [5:0] borrow;


fullSubtractor FS0 (borrow[0], dif[0], x[0], y[0], 1'b0);

// encadeamento
fullSubtractor FS1 (borrow[1], dif[1], x[1], y[1], borrow[0]);
fullSubtractor FS2 (borrow[2], dif[2], x[2], y[2], borrow[1]);
fullSubtractor FS3 (borrow[3], dif[3], x[3], y[3], borrow[2]);
fullSubtractor FS4 (borrow[4], dif[4], x[4], y[4], borrow[3]);
fullSubtractor FS5 (borrow[5], dif[5], x[5], y[5], borrow[4]);

buf (borrowOut, borrow[5]);

endmodule

module test_AU;

reg  [5:0] x, y;
wire [5:0] dif;
wire borrowOut;

AU uut (dif, borrowOut, x, y);

initial begin
    $display("x       y     | dif     borrow");

    x = 6'b000101; y = 6'b000011; #1; // 5 - 3 = 2
    $display("%b %b | %b   %b", x, y, dif, borrowOut);

    x = 6'b000011; y = 6'b000101; #1; // 3 - 5 (negativo)
    $display("%b %b | %b   %b", x, y, dif, borrowOut);

    x = 6'b001111; y = 6'b000001; #1; // 15 - 1 = 14
    $display("%b %b | %b   %b", x, y, dif, borrowOut);

    x = 6'b100001; y = 6'b000001; #1;
    $display("%b %b | %b   %b", x, y, dif, borrowOut);

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