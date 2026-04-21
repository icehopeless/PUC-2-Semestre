/* 
Guia_0102.v 
903104 - Gabriel Agostinho da Silva
*/ 

module guia0102;

integer x = 0;
reg [7:0] b =  8'b0001101;

initial
    begin:main
    $display("guia0102 - Tests");
    $display("x = %d", x);
    $display("b = %8b", b);

    x = b;
    $display("b = %d", x);
    end
endmodule



