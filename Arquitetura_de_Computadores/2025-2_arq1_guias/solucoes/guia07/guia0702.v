// ------------------------- 
// Guia_0702 - GATES 
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
module f7 ( output s, input  a, input  b, input select); 
    wire out_or;
    wire out_nor;


    or OR1(out_or, a, b);
    nor NOR1(out_nor, a, b);


    mux MUX1(s, out_or, out_nor, select);
    
endmodule 
 

module test_f7;

    reg  x;
    reg  y;
    reg  s;  
    wire w;   

    f7 modulo ( w, x, y, s); 

    initial 
    begin : main 
        $display("Guia_0702 - Gabriel Agostinho - 903104"); 
        $display("Test LU's module"); 
        $display("   x     y    s    w"); 

        x = 1'b0;  
        y = 1'b1;  
        s = 1'b0; 

        #1 $monitor("%4b %4b %4b %4b", x, y, s, w); 

        #1 s = 1'b1; 
    end 

endmodule