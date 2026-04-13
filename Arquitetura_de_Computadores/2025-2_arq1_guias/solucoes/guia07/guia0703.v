// ------------------------- 
// Guia_0703 - GATES 
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
module f7 ( output s, input  a, input  b, input select, input select2); 
    wire out_or;
    wire out_nor;
    wire out_nand;
    wire out_and;
    wire g1;
    wire g2;

    or OR1(out_or, a, b);
    nor NOR1(out_nor, a, b);

    and AND1(out_and, a, b);
    nand NAND1(out_nand, a, b);

    mux MUX1(g1, out_or, out_nor, select);
    mux MUX2(g2, out_and, out_nand, select);

    mux MUX3(s, g1, g2, select2);

endmodule 
 

module test_f7;

    reg  x;
    reg  y;
    reg  s;
    reg s2;  
    wire w;   

    f7 modulo ( w, x, y, s, s2); 

    initial 
    begin : main 
        $display("Guia_0703 - Gabriel Agostinho - 903104"); 
        $display("Test LU's module"); 
        $display("   x    y     s    s2   w"); 

        x = 1'b0;  
        y = 1'b1;  
        s = 1'b0; 
        s2 = 1'b0; 

        #1 $monitor("%4b %4b %4b %4b %4b", x, y, s, s2, w); 

        #1 s = 1'b1;
        #1 s2 = 1'b1;
        #1 s = 1'b0;

    end 

endmodule