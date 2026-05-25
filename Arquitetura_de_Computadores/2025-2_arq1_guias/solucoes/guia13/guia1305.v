`include "flipflopT.v"

module guia1305(output q0,output q1,output q2,input clk);

wire q0n;
wire q1n;
wire q2n;

wire t0;
wire t1;
wire t2;

assign t0 = ~(q2 & q1);

assign t1 = q0;

assign t2 = (q1 & q0) | (q2 & q1);

flipflopT ff0 (
    .q(q0),
    .qn(q0n),
    .t(t0),
    .clk(clk)
);

flipflopT ff1 (
    .q(q1),
    .qn(q1n),
    .t(t1),
    .clk(clk)
);

flipflopT ff2 (
    .q(q2),
    .qn(q2n),
    .t(t2),
    .clk(clk)
);

endmodule