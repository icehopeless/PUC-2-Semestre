module Guia_1105 (
    input clock,
    input reset,
    input x,
    output reg y
);

reg [2:0] state;

parameter S0   = 3'b000,
          S1   = 3'b001,
          S2   = 3'b010,
          S3   = 3'b011,
          STOP = 3'b100;

always @(posedge clock or posedge reset)
begin
    if(reset)
    begin
        state <= S0;
        y <= 0;
    end
    else
    begin
        case(state)

            S0:
            begin
                y <= 0;

                if(x)
                    state <= S1;
                else
                    state <= S0;
            end

            S1:
            begin
                y <= 0;

                if(x)
                    state <= S1;
                else
                    state <= S2;
            end

            S2:
            begin
                y <= 0;

                if(x)
                    state <= S1;
                else
                    state <= S3;
            end

            S3:
            begin
                if(x)
                begin
                    y <= 1;
                    state <= STOP;
                end
                else
                begin
                    y <= 0;
                    state <= S0;
                end
            end

            STOP:
            begin
                y <= 0;
                state <= STOP;
            end

            default:
            begin
                y <= 0;
                state <= S0;
            end

        endcase
    end
end

endmodule

`timescale 1ns/1ps

module Guia_1105_teste;

reg clock;
reg reset;
reg x;
wire y;

Guia_1105 uut (
    .clock(clock),
    .reset(reset),
    .x(x),
    .y(y)
);


always #5 clock = ~clock;

initial
begin

    clock = 0;
    reset = 1;
    x = 0;

    #10;
    reset = 0;

    // Sequência: 100101010101

    x = 1; #10;
    x = 0; #10;
    x = 0; #10;
    x = 1; #10;

    x = 0; #10;
    x = 1; #10;
    x = 0; #10;
    x = 1; #10;
    x = 0; #10;
    x = 1; #10;
    x = 0; #10;
    x = 1; #10;

    #20;

    $finish;

end

initial
begin
    $monitor("Tempo=%0t   x=%b   y=%b   estado=%b",
              $time, x, y, uut.state);
end

endmodule