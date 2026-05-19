`include "ram1x4.v"

module ram2x4 (output [3:0] q,input  [3:0] d,input addr,input we,input clk);

wire [3:0] q0;
wire [3:0] q1;

wire we0;
wire we1;

wire not_addr;

not (not_addr, addr);

and (we0, we, not_addr);
and (we1, we, addr);

ram1x4 mem0 (q0,d,we0,clk);

ram1x4 mem1 (q1,d,we1,clk);

/*wire not_addr;

not (not_addr, addr);

and (q[0], q0[0], not_addr);
and (q[0], q1[0], addr);

and (q[1], q0[1], not_addr);
and (q[1], q1[1], addr);

and (q[2], q0[2], not_addr);
and (q[2], q1[2], addr);

and (q[3], q0[3], not_addr);
and (q[3], q1[3], addr);
*/
assign q[0] = (addr == 0) ? q0[0] : q1[0];
assign q[1] = (addr == 0) ? q0[1] : q1[1];
assign q[2] = (addr == 0) ? q0[2] : q1[2];
assign q[3] = (addr == 0) ? q0[3] : q1[3];

endmodule