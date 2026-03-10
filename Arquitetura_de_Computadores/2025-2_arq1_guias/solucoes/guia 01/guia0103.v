/* 
Guia_0103.v 
903104 - Gabriel Agostinho da Silva
*/ 

module guia0103;
integer x = 13;
reg [7:0] b = 0;

initial 
    begin : main
    $display ( "Guia_0103 - Tests" ); 
    $display ( "x = %d"  , x ); 
    $display ( "b = %8b", b ); 
    b = x; 
    $display ( "b = %B (2) = %o (8) = %x (16) = %X (16)", b, b, b, b );
    end
endmodule