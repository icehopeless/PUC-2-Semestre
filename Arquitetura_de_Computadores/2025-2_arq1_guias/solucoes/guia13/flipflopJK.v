//Tabela verdade do Flip Flop JK

//J   K   Q   Q'
//-----------------
//0   0   Q   Q' -> Hold
//0   1   0   1 ->  Reset
//1   0   1   0 ->  Set
//1   1   Q'  Q ->  Toggle


module flipflopJK (output reg q,output reg qn,input j,input k,input clk);

initial begin
    q  = 0;
    qn = 1;
end

always @(posedge clk) begin

    // hold
    if (j == 0 && k == 0) begin
        q  <= q;
        qn <= qn;
    end

    // reset
    else if (j == 0 && k == 1) begin
        q  <= 0;
        qn <= 1;
    end

    // set
    else if (j == 1 && k == 0) begin
        q  <= 1;
        qn <= 0;
    end

    // toggle
    else if (j == 1 && k == 1) begin
        q  <= ~q;
        qn <= ~qn;
    end

end
endmodule