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
module f7 ( output s, input  a, input  b, input select, select2); 
    wire out_or;
    wire out_nor;

    wire out_xor;
    wire out_xnor;

    wire g1, g2;

    or OR1(out_or, a, b);
    nor NOR1(out_nor, a, b);

    xnor XNOR1(out_xnor, a, b);
    xor XOR1(out_xor, a, b);

    mux MUX1(g1, out_xor, out_xnor, select);
    mux MUX2(g2, out_nor, out_or, select);

    mux MUX3(s, g1, g2, select2);
    
endmodule 
 

module test_f7;

    reg x, y;
    reg s1, s0;
    wire w;

    f7 modulo ( w, x, y, s1, s0 );

    initial begin
        $display("Guia_0704 - Gabriel Agostinho - 903104");
        $display("   x    y    s1    s0    w");

        x = 0; y = 1;
        s1 = 0; s0 = 0;

        #1 $monitor("%4b %4b %4b %4b %4b", x, y, s1, s0, w);

        #1 s0 = 1;
        #1 s1 = 1; s0 = 0;
        #1 s0 = 1;
    end

endmodule