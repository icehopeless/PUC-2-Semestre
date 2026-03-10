/* 
Guia_0101.v 
903104- Gabriel Agostinho da Silva
*/ 

module guia0101; 
integer   x = 13; 
reg [7:0] b =  0;  


initial 
begin : main 
$display ( "guia0101 - Tests" ); 
$display ( "x = %d"  , x ); 
$display ( "b = %8b", b ); 
b = x; 
$display ( "b = %8b", b ); 
end  
endmodule 
