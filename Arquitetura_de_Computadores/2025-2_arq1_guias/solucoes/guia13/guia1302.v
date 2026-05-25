`include "flipflopJK.v"

module guia1302(output q0,output q1,output q2,output q3,output q4,input clk);

wire q0n;
wire q1n;
wire q2n;
wire q3n;
wire q4n;
wire q5n;

flipflopJK ff0 (
    .q(q0),
    .qn(q0n),
    .j(1'b1),
    .k(1'b1),
    .clk(clk)
);

flipflopJK ff1 (
    .q(q1),
    .qn(q1n),
    .j(1'b1),
    .k(1'b1),
    .clk(q0)
);

flipflopJK ff2 (
    .q(q2),
    .qn(q2n),
    .j(1'b1),
    .k(1'b1),
    .clk(q1)
);

flipflopJK ff3 (
    .q(q3),
    .qn(q3n),
    .j(1'b1),
    .k(1'b1),
    .clk(q2)
);

flipflopJK ff4 (
    .q(q4),
    .qn(q4n),
    .j(1'b1),
    .k(1'b1),
    .clk(q3)
);

flipflopJK ff5 (
    .q(q5),
    .qn(q5n),
    .j(1'b1),
    .k(1'b1),
    .clk(q4)
);



endmodule