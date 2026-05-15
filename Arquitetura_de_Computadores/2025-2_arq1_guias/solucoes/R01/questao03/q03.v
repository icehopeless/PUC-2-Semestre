module q03 (
    input  a,
    input  b,
    input  c,
    output s
);

    wire na;
    wire nb;

    wire x1;
    wire x2;
    wire x3;

    // negando
    nand (na, a, a);
    nand (nb, b, b);

    //partes de dentro
    nand (x1, na, c);
    nand (x2, na, b);
    nand (x3, nb, c);
    //nand externo (junta tudo)
    nand (s, x1, x2, x3);

endmodule

module testbench;

    reg a;
    reg b;
    reg c;

    wire s;


    q03 q04 (
        .a(a),
        .b(b),
        .c(c),
        .s(s)
    );

    initial begin

        $display("a b c | s");
        $display("------------");

        a=0; b=0; c=0; #10;
        $display("%b %b %b | %b", a,b,c,s);

        a=0; b=0; c=1; #10;
        $display("%b %b %b | %b", a,b,c,s);

        a=0; b=1; c=0; #10;
        $display("%b %b %b | %b", a,b,c,s);

        a=0; b=1; c=1; #10;
        $display("%b %b %b | %b", a,b,c,s);

        a=1; b=0; c=0; #10;
        $display("%b %b %b | %b", a,b,c,s);

        a=1; b=0; c=1; #10;
        $display("%b %b %b | %b", a,b,c,s);

        a=1; b=1; c=0; #10;
        $display("%b %b %b | %b", a,b,c,s);

        a=1; b=1; c=1; #10;
        $display("%b %b %b | %b", a,b,c,s);

        $finish;
    end

endmodule