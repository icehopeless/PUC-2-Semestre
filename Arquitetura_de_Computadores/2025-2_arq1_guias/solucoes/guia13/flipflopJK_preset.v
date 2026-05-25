module flipflopJK_reset (output reg q,output reg qn,input j,input k,input clk, input reset, input preset);

initial begin
    q  = 0;
    qn = 1;
end

always @(posedge clk or posedge reset or posedge preset) begin

    // reset 
    if(reset) begin
        q  <= 0;
        qn <= 1;
    end

    else if(preset) begin
        q  <= 1;
        qn <= 0;
    end

    // hold
    else if (j == 0 && k == 0) begin
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