//903104
//Gabriel Agostinho

// j        K       Q       oper
//---------------------------------
// 0        0       Q       hold
// 0        1       0       reset
// 1        0       1       set
// 1        1       Q'      toggle

module jk_ff (output reg q,input j,input k,input clk);

always @(posedge clk) begin

    if (j == 0 && k == 0)
        q <= q;
        
    else if (j == 0 && k == 1)
        q <= 0;

    else if (j == 1 && k == 0)
        q <= 1;

    else
        q <= ~q;

end

endmodule