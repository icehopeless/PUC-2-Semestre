/* 
Guia_0305.v 
903104 - Gabriel Agostinho da Silva
*/ 

module Guia_0305; 

reg [2:0] a = 0; 
reg [3:0] b = 0; 
reg [4:0] c = 0; 
reg [4:0] d = 0; 
reg [6:0] e = 0; 

initial 
begin : main 
$display ( "Guia_0305 - Tests" ); 
a = 5 + 3;                       

b = 10 - 5 + 25 + 3 + 1; 
c = 2 - 35;                      

$display("\nOverflow"); 
$display("a = %d = %3b = %d", (5+3) , a, a); 
$display("b = %d = %4b = %d", (10 - 5 + 25 + 3 + 1), b, b); 
$display("c = %d = %5b = %d", (2-35), c, c); 
$display("\nComplements"); 
$display("0= %d = %3b = %3b",   ~1 , (1-1), ~(1*1) ); 
$display("1= %d = %3b = %3b",   ~0 , (2-1), ~(0*1) ); 
$display("2= %d = %3b = %3b", (1+1), (3-1), ~0+~0  ); 
end 
endmodule 