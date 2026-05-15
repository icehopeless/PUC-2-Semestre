// w1 = c' + b
// s = a(w1)
//s = a(c'+ b) = ac' + ab

module f ( output s, input a, input b, input c );
wire w1, w2;

and AND_1 (s,a,w1);
not NOT_1 (w2,c);
or  OR__1 (w1,w2,b);

endmodule // s = f (a,b,c)

module f_b (
    output s,
    input a,
    input b,
    input c
);

wire nc;
wire x1;
wire x2;

nand (nc, c, c);

nand (x1, a, nc);
nand (x2, a, b);

nand (s, x1, x2);

endmodule

module f_c (
    output s,
    input a,
    input b,
    input c
);

wire nc;
wire soma;

not (nc, c);

or  (soma, b, nc);

and (s, a, soma);

endmodule

module mux (
    output s,
    input a,
    input b,
    input sel
);

assign s = (a & ~sel) | (b & sel);

endmodule

module f_d (
    output s,
    input a,
    input b,
    input c
);

wire na, nb, nc;

wire m1, m2;
wire m3, m4;

not (na, a);
not (nb, b);
not (nc, c);

mux M1 (m1, 0, a, b);
mux M2 (m2, 0, m1, c);

mux M3 (m3, 0, na, nb);
mux M4 (m4, 0, m3, nc);

mux M5 (s, m2, 1'b1, m4);

endmodule