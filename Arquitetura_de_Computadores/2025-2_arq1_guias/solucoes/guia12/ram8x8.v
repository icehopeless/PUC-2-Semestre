`include "ram4x8.v"

module ram8x8 (output [7:0] q, input [7:0] d, input [2:0] addr, input we, input clk);

wire [7:0] q0;
wire [7:0] q1;

wire not_a2;

wire we0;
wire we1;

not (not_a2, addr[2]);

and (we0, we, not_a2);
and (we1, we, addr[2]);

ram4x8 mem0 (q0,d,addr[1:0],we0,clk);

ram4x8 mem1 (q1,d,addr[1:0],we1,clk);

assign q = (addr[2] == 0) ? q0 : q1;

endmodule