LittleImageRecognition
======================

An old assignment to build a little image recognition algorithm 

Introduction 

You get a matrix 10×10 size, which contains an image of one of the following symbols: C, O, L, I, T, X. You have 
to recognize that symbol. 

Symbols definition 

I – filled rectangle. 

O – filled rectangle with an unfilled area inside. The borders of the unfilled area should not overlap with filled 
area and/or merge with the outer unfilled area. 

C – filled rectangle with an unfilled area inside. The right border of the unfilled area breaks the border of the 
right border of the filled area. 

L – two rectangles lying on top of each other. Their left border is the same. The right edge of the bottom 
rectangle advances more to the right than the right edge of the top rectangle. 

T – two rectangles, one on top of another one. The left border of the rectangle on the top spans more to the 
left than the left border of the rectangle on the bottom. The right border of the rectangle on the top spans 
more to the right than the right border of the rectangle on the bottom. 

X – any other configuration. 

Input 

As an input a program gets 10 sequences of 10 symbols “0” or “1”, which represent one symbol starting from 
the top most line. 

Output 

One of the symbols I, O, C, L, T, X. 

Examples 

"file_O.txt":

0000000000 
0111111100 
0111111100 
0111111100 
0110111100 
0111111100 
0111111100 
0111111100 
0111111100 
0111111100 


Assessment outputs 

1. Source code of Java program that demonstrates algorithmical skills and a knowledge of programming 
language. 
