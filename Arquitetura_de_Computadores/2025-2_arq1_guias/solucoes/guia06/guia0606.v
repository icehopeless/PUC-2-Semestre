module f5a(output S, input x,y,z,w);
    assign S = ~(~x | ~y | w) & ~(y & x & ~w) | ~((y & w & z) | ~x);
endmodule

module run;
    reg x,y,z,w;
    wire a;
    f5a f(a,x,y,z,w);
    initial begin:start
        x=1'bx; y=1'bx; z=1'bx; w=1'bx;
    end
    initial begin:main
        $display("VALORES INICIAIS");
        $display("X = %b    Y = %b  z = %b W = %b",x,y,z,w);
        $display("|-------------------------------------------------------------|");
        $monitor("| X=%b | Y=%b | z=%b | w=%b | (x'+y'+w)'(yxw')' + ((ywz)+x')' = %b |",x,y,z,w,a);
        #2 x=0; y=0; z=0; w=0;
        #1 x=0; y=0; z=0; w=1;
        #1 x=0; y=0; z=1; w=0;
        #1 x=0; y=0; z=1; w=1;
        #1 x=0; y=1; z=0; w=0;
        #1 x=0; y=1; z=0; w=1;
        #1 x=0; y=1; z=1; w=0;
        #1 x=0; y=1; z=1; w=1;
        #1 x=1; y=0; z=0; w=0;
        #1 x=1; y=0; z=0; w=1;
        #1 x=1; y=0; z=1; w=0;
        #1 x=1; y=0; z=1; w=1;
        #1 x=1; y=1; z=0; w=0;
        #1 x=1; y=1; z=0; w=1;
        #1 x=1; y=1; z=1; w=0;
        #1 x=1; y=1; z=1; w=1;
        #1;
        $display("|-------------------------------------------------------------|");
    end
endmodule