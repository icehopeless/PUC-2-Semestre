// ------------------------- 
// Guia_0704 - GATES 
// Nome: Gabriel Agostinho da Silva
// Matricula: 903104
// ------------------------- 
 

// ------------------------- 
//  multiplexer 
// ------------------------- 
module mux ( output s, input  a, input  b, input  select ); 

// definir dados locais 
   wire not_select; 
   wire sa; 
   wire sb; 
 
// descrever por portas 
   not NOT1 ( not_select, select ); 
 
   and AND1 ( sa, a, not_select ); 
   and AND2 ( sb, b, select); 
 
   or  OR1     ( s , sa, sb ); 
endmodule 


// ------------------------- 
//  f7_gate 
// ------------------------- 
module f7 ( output s, input a, input b, input select, input select2, input select3 );

wire out_not;
wire out_and;
wire out_nand;
wire out_or;
wire out_nor;
wire out_xor;
wire out_xnor;

wire g1, g2, g3, g4, g5;


not   NOT1  (out_not, a);
and   AND1  (out_and, a, b);
nand  NAND1 (out_nand, a, b);
or    OR1   (out_or, a, b);
nor   NOR1  (out_nor, a, b);
xor   XOR1  (out_xor, a, b);
xnor  XNOR1 (out_xnor, a, b);


mux MUX1 (g1, out_or, out_nor, select);
mux MUX2 (g2, out_xor, out_xnor, select);
mux MUX3 (g3, out_and, out_nand, select);

mux MUX4 (g4, g1, g2, select2);
mux MUX5 (g5, out_not, g3, select2);

mux MUX6 (s, g4, g5, select3);

endmodule
 

module test_f7;

reg x, y;
reg s0, s1, s2;
wire w;

f7 modulo (w, x, y, s0, s1, s2);

initial begin
    $display("Guia_0705 - Gabriel Agostinho - 903104");
    $display("    x    y    s0    s1    s2    w");

    x = 0; y = 1;

    $monitor("%4b %4b %4b %4b %4b %4b", x, y, s0, s1, s2, w);

    s0 = 0; s1 = 0; s2 = 0; #1;
    s0 = 1; s1 = 0; s2 = 0; #1;
    s0 = 0; s1 = 1; s2 = 0; #1;
    s0 = 1; s1 = 1; s2 = 0; #1;
    s0 = 0; s1 = 0; s2 = 1; #1;
    s0 = 1; s1 = 0; s2 = 1; #1;
    s0 = 0; s1 = 1; s2 = 1; #1;
    s0 = 1; s1 = 1; s2 = 1; #1;
end

endmodule