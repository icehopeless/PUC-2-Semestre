//0_0 = a'.c'
//_10 = b . c'
//1_1 = a.c
//a'c + bc'+ ac
module q04 (
    input  a,
    input  b,
    input  c,
    output s
);

assign s = (~a & ~c) |
           ( b & ~c) |
           ( a &  c);

endmodule

module test;

    reg a, b, c;
    wire s;

    q04 uut (
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