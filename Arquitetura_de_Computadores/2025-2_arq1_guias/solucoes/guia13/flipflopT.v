//Flip Flop T
//T	Q	Q'
//-----------
//0	Q	Q
//1	Q	Q'


module flipflopT (output reg q,output reg qn,input t,input clk);

initial begin
    q  = 0;
    qn = 1;
end

always @(posedge clk) begin

    // Hold
    if(t == 0) begin
        q  <= q;
        qn <= qn;
    end

    // Toggle
    else begin
        q  <= ~q;
        qn <= ~qn;
    end

end

endmodule