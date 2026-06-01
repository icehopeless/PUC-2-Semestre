module flipflopD (output q, output qnot, input  d, input  clk, input  preset, input  clear);
    reg q, qnot;

    always @(posedge clk)
    begin
        if (clear)
        begin
            q <= 0;
            qnot <= 1;
        end
        else if (preset)
        begin
            q <= 1;
            qnot <= 0;
        end
        else
        begin
            q <= d;
            qnot <= ~d;
        end
    end

endmodule