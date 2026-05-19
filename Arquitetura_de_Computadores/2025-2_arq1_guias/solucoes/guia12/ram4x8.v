`include "ram2x4.v"

module ram4x8 (output [7:0] q, input  [7:0] d, input  [1:0] addr, input we, input clk);

wire [7:0] q0;
wire [7:0] q1;

wire not_a1;

wire we0;
wire we1;

not (not_a1, addr[1]);

and (we0, we, not_a1);
and (we1, we, addr[1]);


//0
ram2x4 low0 (q0[3:0],d[3:0],addr[0],we0,clk);

ram2x4 high0 (q0[7:4],d[7:4],addr[0],we0,clk);


//bloco 1

ram2x4 low1 (q1[3:0],d[3:0],addr[0],we1,clk);

ram2x4 high1 (q1[7:4],d[7:4],addr[0],we1,clk);
/*always @(addr or q0 or q1)
begin
    if(addr[1] == 0)
        q = q0;
    else
        q = q1;
end*/

assign q = (addr[1] == 0) ? q0 : q1;

endmodule