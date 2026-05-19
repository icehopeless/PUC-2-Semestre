`include "flipflopJK.v"

module ram1x4 (output [3:0] q,input  [3:0] d,input we,input clk);

wire j0, k0;
wire j1, k1;
wire j2, k2;
wire j3, k3;

// bit 0
and (j0, we,  d[0]);

wire nd0;
not (nd0, d[0]);
and (k0, we, nd0);


// bit 1
and (j1, we,  d[1]);

wire nd1;
not (nd1, d[1]);
and (k1, we, nd1);


// bit 2


and (j2, we,  d[2]);

wire nd2;
not (nd2, d[2]);
and (k2, we, nd2);

// bit 3

and (j3, we,  d[3]);

wire nd3;
not (nd3, d[3]);
and (k3, we, nd3);



flipflopJK ff0 (q[0], j0, k0, clk);
flipflopJK ff1 (q[1], j1, k1, clk);
flipflopJK ff2 (q[2], j2, k2, clk);
flipflopJK ff3 (q[3], j3, k3, clk);

endmodule