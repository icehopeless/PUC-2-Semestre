/* 
Guia_0105.v 
903104 - Gabriel Agostinho da Silva
*/ 

module guia0105; 

   integer   x = 13; 
   reg [7:0] b        ; 
   reg [0:2][7:0] s = "PUC"; 
 

   initial 
    begin : main 
     $display ( "guia0105 - tests" ); 
     $display ( "x = %d"  , x ); 
     $display ( "b = %8b", b ); 
     $display ( "s = %s"  , s ); 
     b = x; 
     $display ( "b = [%4b] [%4b] = %h %h", b[7:4], b[3:0], b[7:4], b[3:0] ); 
     s[0] = "-"; 
     s[1] = 8'b01001101; 
     s[2] = 71;               
     $display ( "s = %s" , s ); 
    end 
 
endmodule 