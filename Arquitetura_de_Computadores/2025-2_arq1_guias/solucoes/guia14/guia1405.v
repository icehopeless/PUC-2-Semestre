`include "flipflopD.v"

module guia1405(input d0,input d01,input d02,input d03,input d04,input load,input clear,input clock,
output q0,output q01,output q02,output q03,output q04
);

wire lixo0, lixo1, lixo2, lixo3, lixo4;
wire or0, or1, or2, or3;
wire and0, and1, and2, and3, and4;

and AND0(and0, d0,  load);
and AND1(and1, d01, load);
and AND2(and2, d02, load);
and AND3(and3, d03, load);
and AND4(and4, d04, load);

flipflopD ff0(
    .q(q0),
    .qnot(lixo0),
    .d(and0),
    .clk(clock),
    .preset(1'b0),
    .clear(clear)
);

or OR0(or0, and1, q0);

flipflopD ff1(
    .q(q01),
    .qnot(lixo1),
    .d(or0),
    .clk(clock),
    .preset(1'b0),
    .clear(clear)
);

or OR1(or1, and2, q01);

flipflopD ff2(
    .q(q02),
    .qnot(lixo2),
    .d(or1),
    .clk(clock),
    .preset(1'b0),
    .clear(clear)
);

or OR2(or2, and3, q02);

flipflopD ff3(
    .q(q03),
    .qnot(lixo3),
    .d(or2),
    .clk(clock),
    .preset(1'b0),
    .clear(clear)
);

or OR3(or3, and4, q03);

flipflopD ff4(
    .q(q04),
    .qnot(lixo4),
    .d(or3),
    .clk(clock),
    .preset(1'b0),
    .clear(clear)
);

endmodule

module testbench;

    reg d0;
    reg d01;
    reg d02;
    reg d03;
    reg d04;

    reg load;
    reg clear;
    reg clock;

    wire q0;
    wire q01;
    wire q02;
    wire q03;
    wire q04;

    guia1405 uut (
        .d0(d0),
        .d01(d01),
        .d02(d02),
        .d03(d03),
        .d04(d04),
        .load(load),
        .clear(clear),
        .clock(clock),
        .q0(q0),
        .q01(q01),
        .q02(q02),
        .q03(q03),
        .q04(q04)
    );

    always #5 clock = ~clock;

    initial
    begin
        $display("clk load clr | d04 d03 d02 d01 d0 | q04 q03 q02 q01 q0");
        $monitor("%b    %b    %b  |  %b   %b   %b   %b   %b |  %b   %b   %b   %b   %b",
                 clock, load, clear,
                 d04, d03, d02, d01, d0,
                 q04, q03, q02, q01, q0);

        clock = 0;
        clear = 1;
        load  = 0;

        d0  = 0;
        d01 = 1;
        d02 = 1;
        d03 = 0;
        d04 = 1;

        #10;
        clear = 0;

        #10;
        load = 1;

        #10;
        load = 0;

        #100;

        $finish;
    end

endmodule