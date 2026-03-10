/* 
Guia_0104.v 
903104 - Gabriel Agostinho da Silva
*/ 

module guia0104; 

integer   x = 13; 
reg [7:0] b =  0; 


initial 
begin : main 
$display ( "guia0104 - tests" ); 
$display ( "x = %d"  , x ); 
$display ( "b = %8b", b ); 
b = x; 
$display ( "b = [%4b] [%4b] = %x %x", b[7:4], b[3:0], b[7:4], b[3:0] );
end 
endmodule 