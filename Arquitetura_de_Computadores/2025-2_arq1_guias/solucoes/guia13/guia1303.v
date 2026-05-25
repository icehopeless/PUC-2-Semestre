`include "flipflopJK_reset.v"

module guia1303(output q0,output q1,output q2,output q3,output q4,input clk);

wire q0n;
wire q1n;
wire q2n;
wire q3n;
wire q4n;
wire reset;

assign reset = q3 & q1;

flipflopJK_reset ff0 (
    .q(q0),
    .qn(q0n),
    .j(1'b1),
    .k(1'b1),
    .clk(clk),
    .reset(reset)
    );

flipflopJK_reset ff1 (
    .q(q1),
    .qn(q1n),
    .j(1'b1),
    .k(1'b1),
    .clk(q0),
    .reset(reset)
);

flipflopJK_reset ff2 (
    .q(q2),
    .qn(q2n),
    .j(1'b1),
    .k(1'b1),
    .clk(q1),
    .reset(reset)
);

flipflopJK_reset ff3 (
    .q(q3),
    .qn(q3n),
    .j(1'b1),
    .k(1'b1),
    .clk(q2),
    .reset(reset)
);

flipflopJK_reset ff4 (
    .q(q4),
    .qn(q4n),
    .j(1'b1),
    .k(1'b1),
    .clk(q3),
    .reset(reset)
);

endmodule