
/**
* <copyright>
*
* Copyright (c) 2009 Jin Mingjian and others.
* All rights reserved.   This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*   Jin Mingjian - Initial API and implementation
*  
* </copyright>
* 
**/

package com.google.code.exquery.parser;

public interface XQueryKWLexersym {
    public final static int
      Char_DollarSign = 26,
      Char_Percent = 27,
      Char_Minus = 12,
      Char_a = 7,
      Char_b = 17,
      Char_c = 9,
      Char_d = 11,
      Char_e = 1,
      Char_f = 18,
      Char_g = 16,
      Char_h = 19,
      Char_i = 4,
      Char_j = 28,
      Char_k = 29,
      Char_l = 10,
      Char_m = 14,
      Char_n = 3,
      Char_o = 8,
      Char_p = 15,
      Char_q = 23,
      Char_r = 5,
      Char_s = 6,
      Char_t = 2,
      Char_u = 13,
      Char_v = 21,
      Char_w = 24,
      Char_x = 22,
      Char_y = 20,
      Char_z = 30,
      Char_EOF = 25;

    public final static String orderedTerminalSymbols[] = {
                 "",
                 "e",
                 "t",
                 "n",
                 "i",
                 "r",
                 "s",
                 "a",
                 "o",
                 "c",
                 "l",
                 "d",
                 "Minus",
                 "u",
                 "m",
                 "p",
                 "g",
                 "b",
                 "f",
                 "h",
                 "y",
                 "v",
                 "x",
                 "q",
                 "w",
                 "EOF",
                 "DollarSign",
                 "Percent",
                 "j",
                 "k",
                 "z"
             };

    public final static int numTokenKinds = orderedTerminalSymbols.length;
    public final static boolean isValidForParser = true;
}
