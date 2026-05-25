`include "flipflopJK_preset.v"

module guia1304(output q0,output q1,output q2,output q3,output q4,input clk);

wire q0n;
wire q1n;
wire q2n;
wire q3n;
wire q4n;
wire reset;
wire load;

assign load = ~q4 & ~q3 & ~q2 & ~q1 & ~q0;

assign reset = q3 & q1;

flipflopJK_preset ff0 (
    .q(q0),
    .qn(q0n),
    .j(1'b1),
    .k(1'b1),
    .clk(clk),
    .preset(load),
    .reset(1'b0)
    );

flipflopJK_preset ff1 (
    .q(q1),
    .qn(q1n),
    .j(1'b1),
    .k(1'b1),
    .clk(q0n),
    .preset(1'b0),
    .reset(load)
);

flipflopJK_preset ff2 (
    .q(q2),
    .qn(q2n),
    .j(1'b1),
    .k(1'b1),
    .clk(q1n),
    .preset(1'b0),
    .reset(load)
);

flipflopJK_preset ff3 (
    .q(q3),
    .qn(q3n),
    .j(1'b1),
    .k(1'b1),
    .clk(q2n),
    .preset(load),
    .reset(1'b0)
);

flipflopJK_preset ff4 (
    .q(q4),
    .qn(q4n),
    .j(1'b1),
    .k(1'b1),
    .clk(q3n),
    .preset(1'b0),
    .reset(load)
);

endmodule