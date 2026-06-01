`include "flipflopD.v"

module guia1402(output q0,output q01,output q02,output q03,output q04,input data,input clock,input clear,input LD);

wire lixo0, lixo1, lixo2, lixo3, lixo4;

flipflopD ff0(
    .q(q0),
    .qnot(lixo0),
    .d(q01),
    .clk(clock),
    .preset(LD),
    .clear(clear)
);

flipflopD ff1(
    .q(q01),
    .qnot(lixo1),
    .d(q02),
    .clk(clock),
    .preset(LD),
    .clear(clear)
);

flipflopD ff2(
    .q(q02),
    .qnot(lixo2),
    .d(q03),
    .clk(clock),
    .preset(LD),
    .clear(clear)
);

flipflopD ff3(
    .q(q03),
    .qnot(lixo3),
    .d(q04),
    .clk(clock),
    .preset(LD),
    .clear(clear)
);

flipflopD ff4(
    .q(q04),
    .qnot(lixo4),
    .d(data),
    .clk(clock),
    .preset(LD),
    .clear(clear)
);

endmodule

module testbench;

    reg data;
    reg clock;
    reg clear;
    reg LD;

    wire q0;
    wire q01;
    wire q02;
    wire q03;
    wire q04;

    guia1402 uut (
        .q0(q0),
        .q01(q01),
        .q02(q02),
        .q03(q03),
        .q04(q04),
        .data(data),
        .clock(clock),
        .clear(clear),
        .LD(LD)
    );

    always #5 clock = ~clock;

    initial
    begin
        $display("clk clr LD data | q0 q01 q02 q03 q04");
        $monitor("%b   %b   %b   %b   | %b  %b   %b   %b   %b",
                  clock, clear, LD, data,
                  q0, q01, q02, q03, q04);

        clock = 0;
        clear = 1;
        LD    = 0;
        data  = 0;

        #10;
        clear = 0;

        LD = 1;

        #10;
        LD = 0;

        #50;

        data = 1; #10;
        data = 0; #10;
        data = 1; #10;
        data = 1; #10;
        data = 0; #10;

        #50;
        $finish;
    end

endmodule