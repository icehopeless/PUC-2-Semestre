`include "ram1x4.v"

module ram1x8 (output [7:0] q, input[7:0] d, input we, input clk);

ram1x4 parte0 (q[3:0], d[3:0], we, clk);

ram1x4 parte1 (q[7:4], d[7:4], we, clk);

endmodule