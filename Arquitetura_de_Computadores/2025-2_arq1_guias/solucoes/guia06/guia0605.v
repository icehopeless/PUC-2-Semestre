// ------------------------- 
// Guia_0605.v - GATES 
// Nome: Gabriel Agostinho da Silva
// Matricula: 903104
// ------------------------- 
 

//((x' + y')' . (x . y)') + ((y . z) + x')'
module f5a ( output s, input x, input y, input z); 
   /*wire not_x;
   wire not_y;
   wire first;
   wire second;
   wire final;
    
   not NOT1(not_x, x);
   not NOT2(not_y, y);


   //primeira parte da expressao
   wire aux1;
   wire aux2;
   and AND1(aux1, not_x, not_y);
   not NOT3(aux2, aux1);

   //segunda parte da expressao
   wire aux3;
   wire aux4;
   and AND2(aux3, x, y);
   not NOT4(aux4, aux3);

   //and das duas primeiras expressoes
   and AND3(first, aux2, aux4);

   //terceira parte da expressao
   wire aux5;
   wire aux6;
   and AND4(aux5, y, z);
   or OR1(aux6, aux5, not_x);
   not NOT5(second, aux6);


   //or entre as duas partes
   or OR2(final, first ,second);

   assign s = final;
   */
   assign s = ~(~x | ~y) & ~(x & y) | ~((y & z) | ~x);

endmodule

module run;
    reg x,y,z;
    wire a;
    f5a f(a,x,y,z);
    initial begin:start
        x=1'bx;y=1'bx;z=1'bx;
    end
    initial begin:main
        $display("VALORES INICIAIS");
        $display("X = %b    Y = %b  z = %b",x,y,z);
        $display("|------------------------------------------------------|");
        $monitor("| X=%b | Y=%b | z=%b |  (x'+y')'(xy)' + ((yz)+x')' = %b |",x,y,z,a);
        #2 x=0; y=0; z=0;
        #1 x=0; y=0; z=1;
        #1 x=0; y=1; z=0;
        #1 x=0; y=1; z=1;
        #1 x=1; y=0; z=0;
        #1 x=1; y=0; z=1;
        #1 x=1; y=1; z=0;
        #1 x=1; y=1; z=1;
        #1;
        $display("|----------------------------------------------------|");
    end
endmodule




