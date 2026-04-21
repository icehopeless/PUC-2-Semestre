    // -------------------------
    // Guia_0804 - comparador de desiigualdade
    // Nome: Gabriel Agostinho da Silva
    // Matricula: 903104
    // -------------------------

    module LU (
        output s,
        input  [5:0] x,
        input  [5:0] y
    );

    wire [5:0] eq;

    //comparadno com xor
    xor (eq[0], x[0], y[0]);
    xor (eq[1], x[1], y[1]);
    xor (eq[2], x[2], y[2]);
    xor (eq[3], x[3], y[3]);
    xor (eq[4], x[4], y[4]);
    xor (eq[5], x[5], y[5]);

    wire w1, w2, w3, w4;
    //diferentemente da igualdade, precisamos que apenas um seja diferente para dar true.
    or (w1, eq[0], eq[1]);
    or (w2, eq[2], eq[3]);
    or (w3, eq[4], eq[5]);
    or (w4, w1, w2);
    or (s, w4, w3);

    endmodule

    module test_LU;

    reg [5:0] x, y;
    wire s;

    LU uut (s, x, y);

    initial begin
        $display("x       y     | not igual");

        x = 6'b000000; y = 6'b000000; #1;
        $display("%b %b |   %b", x, y, s);

        x = 6'b000101; y = 6'b000101; #1;
        $display("%b %b |   %b", x, y, s);

        x = 6'b000101; y = 6'b000100; #1;
        $display("%b %b |   %b", x, y, s);

        x = 6'b111111; y = 6'b111111; #1;
        $display("%b %b |   %b", x, y, s);

        x = 6'b100001; y = 6'b100000; #1;
        $display("%b %b |   %b", x, y, s);

    end

    endmodule