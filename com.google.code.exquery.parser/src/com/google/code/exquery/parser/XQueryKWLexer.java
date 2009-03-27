
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

import lpg.runtime.*;

public class XQueryKWLexer extends XQueryKWLexerprs
{
    private char[] inputChars;
    private final int keywordKind[] = new int[96 + 1];

    public int[] getKeywordKinds() { return keywordKind; }

    public int lexer(int curtok, int lasttok)
    {
        int current_kind = getKind(inputChars[curtok]),
            act;

        for (act = tAction(START_STATE, current_kind);
             act > NUM_RULES && act < ACCEPT_ACTION;
             act = tAction(act, current_kind))
        {
            curtok++;
            current_kind = (curtok > lasttok
                                   ? XQueryKWLexersym.Char_EOF
                                   : getKind(inputChars[curtok]));
        }

        if (act > ERROR_ACTION)
        {
            curtok++;
            act -= ERROR_ACTION;
        }

        return keywordKind[act == ERROR_ACTION  || curtok <= lasttok ? 0 : act];
    }

    public void setInputChars(char[] inputChars) { this.inputChars = inputChars; }


    final static int tokenKind[] = new int[128];
    static
    {
        tokenKind['$'] = XQueryKWLexersym.Char_DollarSign;
        tokenKind['%'] = XQueryKWLexersym.Char_Percent;
        tokenKind['-'] = XQueryKWLexersym.Char_Minus;
        
        tokenKind['a'] = XQueryKWLexersym.Char_a;
        tokenKind['b'] = XQueryKWLexersym.Char_b;
        tokenKind['c'] = XQueryKWLexersym.Char_c;
        tokenKind['d'] = XQueryKWLexersym.Char_d;
        tokenKind['e'] = XQueryKWLexersym.Char_e;
        tokenKind['f'] = XQueryKWLexersym.Char_f;
        tokenKind['g'] = XQueryKWLexersym.Char_g;
        tokenKind['h'] = XQueryKWLexersym.Char_h;
        tokenKind['i'] = XQueryKWLexersym.Char_i;
        tokenKind['j'] = XQueryKWLexersym.Char_j;
        tokenKind['k'] = XQueryKWLexersym.Char_k;
        tokenKind['l'] = XQueryKWLexersym.Char_l;
        tokenKind['m'] = XQueryKWLexersym.Char_m;
        tokenKind['n'] = XQueryKWLexersym.Char_n;
        tokenKind['o'] = XQueryKWLexersym.Char_o;
        tokenKind['p'] = XQueryKWLexersym.Char_p;
        tokenKind['q'] = XQueryKWLexersym.Char_q;
        tokenKind['r'] = XQueryKWLexersym.Char_r;
        tokenKind['s'] = XQueryKWLexersym.Char_s;
        tokenKind['t'] = XQueryKWLexersym.Char_t;
        tokenKind['u'] = XQueryKWLexersym.Char_u;
        tokenKind['v'] = XQueryKWLexersym.Char_v;
        tokenKind['w'] = XQueryKWLexersym.Char_w;
        tokenKind['x'] = XQueryKWLexersym.Char_x;
        tokenKind['y'] = XQueryKWLexersym.Char_y;
        tokenKind['z'] = XQueryKWLexersym.Char_z;
    };

    final int getKind(int c)
    {
        return ((c & 0xFFFFFF80) == 0 /* 0 <= c < 128? */ ? tokenKind[c] : 0);
    }


    public XQueryKWLexer(char[] inputChars, int identifierKind)
    {
        this.inputChars = inputChars;
        keywordKind[0] = identifierKind;

        //
        // Rule 1:  Keyword ::= x q u e r y
        //
        
        keywordKind[1] = (XQueryParsersym.TK_xquery);
      
    
        //
        // Rule 2:  Keyword ::= v e r s i o n
        //
        
        keywordKind[2] = (XQueryParsersym.TK_version);
      
    
        //
        // Rule 3:  Keyword ::= e n c o d i n g
        //
        
        keywordKind[3] = (XQueryParsersym.TK_encoding);
      
    
        //
        // Rule 4:  Keyword ::= m o d u l e
        //
        
        keywordKind[4] = (XQueryParsersym.TK_module);
      
    
        //
        // Rule 5:  Keyword ::= n a m e s p a c e
        //
        
        keywordKind[5] = (XQueryParsersym.TK_namespace);
      
    
        //
        // Rule 6:  Keyword ::= d e c l a r e
        //
        
        keywordKind[6] = (XQueryParsersym.TK_declare);
      
    
        //
        // Rule 7:  Keyword ::= b o u n d a r y - s p a c e
        //
        
        keywordKind[7] = (XQueryParsersym.TK_boundary_space);
      
    
        //
        // Rule 8:  Keyword ::= p r e s e r v e
        //
        
        keywordKind[8] = (XQueryParsersym.TK_preserve);
      
    
        //
        // Rule 9:  Keyword ::= s t r i p
        //
        
        keywordKind[9] = (XQueryParsersym.TK_strip);
      
    
        //
        // Rule 10:  Keyword ::= d e f a u l t
        //
        
        keywordKind[10] = (XQueryParsersym.TK_default);
      
    
        //
        // Rule 11:  Keyword ::= f u n c t i o n
        //
        
        keywordKind[11] = (XQueryParsersym.TK_function);
      
    
        //
        // Rule 12:  Keyword ::= o p t i o n
        //
        
        keywordKind[12] = (XQueryParsersym.TK_option);
      
    
        //
        // Rule 13:  Keyword ::= o r d e r i n g
        //
        
        keywordKind[13] = (XQueryParsersym.TK_ordering);
      
    
        //
        // Rule 14:  Keyword ::= o r d e r e d
        //
        
        keywordKind[14] = (XQueryParsersym.TK_ordered);
      
    
        //
        // Rule 15:  Keyword ::= o r d e r
        //
        
        keywordKind[15] = (XQueryParsersym.TK_order);
      
    
        //
        // Rule 16:  Keyword ::= e m p t y
        //
        
        keywordKind[16] = (XQueryParsersym.TK_empty);
      
    
        //
        // Rule 17:  Keyword ::= g r e a t e s t
        //
        
        keywordKind[17] = (XQueryParsersym.TK_greatest);
      
    
        //
        // Rule 18:  Keyword ::= l e a s t
        //
        
        keywordKind[18] = (XQueryParsersym.TK_least);
      
    
        //
        // Rule 19:  Keyword ::= c o p y - n a m e s p a c e s
        //
        
        keywordKind[19] = (XQueryParsersym.TK_copy_namespaces);
      
    
        //
        // Rule 20:  Keyword ::= n o - p r e s e r v e
        //
        
        keywordKind[20] = (XQueryParsersym.TK_no_preserve);
      
    
        //
        // Rule 21:  Keyword ::= i n h e r i t
        //
        
        keywordKind[21] = (XQueryParsersym.TK_inherit);
      
    
        //
        // Rule 22:  Keyword ::= n o - i n h e r i t
        //
        
        keywordKind[22] = (XQueryParsersym.TK_no_inherit);
      
    
        //
        // Rule 23:  Keyword ::= c o l l a t i o n
        //
        
        keywordKind[23] = (XQueryParsersym.TK_collation);
      
    
        //
        // Rule 24:  Keyword ::= b a s e - u r i
        //
        
        keywordKind[24] = (XQueryParsersym.TK_base_uri);
      
    
        //
        // Rule 25:  Keyword ::= a t
        //
        
        keywordKind[25] = (XQueryParsersym.TK_at);
      
    
        //
        // Rule 26:  Keyword ::= i m p o r t
        //
        
        keywordKind[26] = (XQueryParsersym.TK_import);
      
    
        //
        // Rule 27:  Keyword ::= s c h e m a
        //
        
        keywordKind[27] = (XQueryParsersym.TK_schema);
      
    
        //
        // Rule 28:  Keyword ::= v a r i a b l e
        //
        
        keywordKind[28] = (XQueryParsersym.TK_variable);
      
    
        //
        // Rule 29:  Keyword ::= c o n s t r u c t i o n
        //
        
        keywordKind[29] = (XQueryParsersym.TK_construction);
      
    
        //
        // Rule 30:  Keyword ::= a s
        //
        
        keywordKind[30] = (XQueryParsersym.TK_as);
      
    
        //
        // Rule 31:  Keyword ::= e x t e r n a l
        //
        
        keywordKind[31] = (XQueryParsersym.TK_external);
      
    
        //
        // Rule 32:  Keyword ::= r e t u r n
        //
        
        keywordKind[32] = (XQueryParsersym.TK_return);
      
    
        //
        // Rule 33:  Keyword ::= i n
        //
        
        keywordKind[33] = (XQueryParsersym.TK_in);
      
    
        //
        // Rule 34:  Keyword ::= f o r
        //
        
        keywordKind[34] = (XQueryParsersym.TK_for);
      
    
        //
        // Rule 35:  Keyword ::= l e t
        //
        
        keywordKind[35] = (XQueryParsersym.TK_let);
      
    
        //
        // Rule 36:  Keyword ::= w h e r e
        //
        
        keywordKind[36] = (XQueryParsersym.TK_where);
      
    
        //
        // Rule 37:  Keyword ::= b y
        //
        
        keywordKind[37] = (XQueryParsersym.TK_by);
      
    
        //
        // Rule 38:  Keyword ::= s t a b l e
        //
        
        keywordKind[38] = (XQueryParsersym.TK_stable);
      
    
        //
        // Rule 39:  Keyword ::= a s c e n d i n g
        //
        
        keywordKind[39] = (XQueryParsersym.TK_ascending);
      
    
        //
        // Rule 40:  Keyword ::= d e s c e n d i n g
        //
        
        keywordKind[40] = (XQueryParsersym.TK_descending);
      
    
        //
        // Rule 41:  Keyword ::= s o m e
        //
        
        keywordKind[41] = (XQueryParsersym.TK_some);
      
    
        //
        // Rule 42:  Keyword ::= e v e r y
        //
        
        keywordKind[42] = (XQueryParsersym.TK_every);
      
    
        //
        // Rule 43:  Keyword ::= s a t i s f i e s
        //
        
        keywordKind[43] = (XQueryParsersym.TK_satisfies);
      
    
        //
        // Rule 44:  Keyword ::= c a s e
        //
        
        keywordKind[44] = (XQueryParsersym.TK_case);
      
    
        //
        // Rule 45:  Keyword ::= t h e n
        //
        
        keywordKind[45] = (XQueryParsersym.TK_then);
      
    
        //
        // Rule 46:  Keyword ::= e l s e
        //
        
        keywordKind[46] = (XQueryParsersym.TK_else);
      
    
        //
        // Rule 47:  Keyword ::= o r
        //
        
        keywordKind[47] = (XQueryParsersym.TK_or);
      
    
        //
        // Rule 48:  Keyword ::= a n d
        //
        
        keywordKind[48] = (XQueryParsersym.TK_and);
      
    
        //
        // Rule 49:  Keyword ::= t o
        //
        
        keywordKind[49] = (XQueryParsersym.TK_to);
      
    
        //
        // Rule 50:  Keyword ::= u n i o n
        //
        
        keywordKind[50] = (XQueryParsersym.TK_union);
      
    
        //
        // Rule 51:  Keyword ::= i n t e r s e c t
        //
        
        keywordKind[51] = (XQueryParsersym.TK_intersect);
      
    
        //
        // Rule 52:  Keyword ::= e x c e p t
        //
        
        keywordKind[52] = (XQueryParsersym.TK_except);
      
    
        //
        // Rule 53:  Keyword ::= i n s t a n c e
        //
        
        keywordKind[53] = (XQueryParsersym.TK_instance);
      
    
        //
        // Rule 54:  Keyword ::= o f
        //
        
        keywordKind[54] = (XQueryParsersym.TK_of);
      
    
        //
        // Rule 55:  Keyword ::= t r e a t
        //
        
        keywordKind[55] = (XQueryParsersym.TK_treat);
      
    
        //
        // Rule 56:  Keyword ::= c a s t a b l e
        //
        
        keywordKind[56] = (XQueryParsersym.TK_castable);
      
    
        //
        // Rule 57:  Keyword ::= c a s t
        //
        
        keywordKind[57] = (XQueryParsersym.TK_cast);
      
    
        //
        // Rule 58:  Keyword ::= v a l i d a t e
        //
        
        keywordKind[58] = (XQueryParsersym.TK_validate);
      
    
        //
        // Rule 59:  Keyword ::= l a x
        //
        
        keywordKind[59] = (XQueryParsersym.TK_lax);
      
    
        //
        // Rule 60:  Keyword ::= s t r i c t
        //
        
        keywordKind[60] = (XQueryParsersym.TK_strict);
      
    
        //
        // Rule 61:  Keyword ::= c h i l d
        //
        
        keywordKind[61] = (XQueryParsersym.TK_child);
      
    
        //
        // Rule 62:  Keyword ::= d e s c e n d a n t
        //
        
        keywordKind[62] = (XQueryParsersym.TK_descendant);
      
    
        //
        // Rule 63:  Keyword ::= s e l f
        //
        
        keywordKind[63] = (XQueryParsersym.TK_self);
      
    
        //
        // Rule 64:  Keyword ::= d e s c e n d a n t - o r - s e l f
        //
        
        keywordKind[64] = (XQueryParsersym.TK_descendant_or_self);
      
    
        //
        // Rule 65:  Keyword ::= f o l l o w i n g - s i b l i n g
        //
        
        keywordKind[65] = (XQueryParsersym.TK_following_sibling);
      
    
        //
        // Rule 66:  Keyword ::= f o l l o w i n g
        //
        
        keywordKind[66] = (XQueryParsersym.TK_following);
      
    
        //
        // Rule 67:  Keyword ::= p a r e n t
        //
        
        keywordKind[67] = (XQueryParsersym.TK_parent);
      
    
        //
        // Rule 68:  Keyword ::= a n c e s t o r
        //
        
        keywordKind[68] = (XQueryParsersym.TK_ancestor);
      
    
        //
        // Rule 69:  Keyword ::= p r e c e d i n g - s i b l i n g
        //
        
        keywordKind[69] = (XQueryParsersym.TK_preceding_sibling);
      
    
        //
        // Rule 70:  Keyword ::= p r e c e d i n g
        //
        
        keywordKind[70] = (XQueryParsersym.TK_preceding);
      
    
        //
        // Rule 71:  Keyword ::= a n c e s t o r - o r - s e l f
        //
        
        keywordKind[71] = (XQueryParsersym.TK_ancestor_or_self);
      
    
        //
        // Rule 72:  Keyword ::= u n o r d e r e d
        //
        
        keywordKind[72] = (XQueryParsersym.TK_unordered);
      
    
        //
        // Rule 73:  Keyword ::= d o c u m e n t
        //
        
        keywordKind[73] = (XQueryParsersym.TK_document);
      
    
        //
        // Rule 74:  Keyword ::= d i v
        //
        
        keywordKind[74] = (XQueryParsersym.TK_div);
      
    
        //
        // Rule 75:  Keyword ::= i d i v
        //
        
        keywordKind[75] = (XQueryParsersym.TK_idiv);
      
    
        //
        // Rule 76:  Keyword ::= m o d
        //
        
        keywordKind[76] = (XQueryParsersym.TK_mod);
      
    
        //
        // Rule 77:  Keyword ::= e q
        //
        
        keywordKind[77] = (XQueryParsersym.TK_eq);
      
    
        //
        // Rule 78:  Keyword ::= n e
        //
        
        keywordKind[78] = (XQueryParsersym.TK_ne);
      
    
        //
        // Rule 79:  Keyword ::= l t
        //
        
        keywordKind[79] = (XQueryParsersym.TK_lt);
      
    
        //
        // Rule 80:  Keyword ::= l e
        //
        
        keywordKind[80] = (XQueryParsersym.TK_le);
      
    
        //
        // Rule 81:  Keyword ::= g t
        //
        
        keywordKind[81] = (XQueryParsersym.TK_gt);
      
    
        //
        // Rule 82:  Keyword ::= g e
        //
        
        keywordKind[82] = (XQueryParsersym.TK_ge);
      
    
        //
        // Rule 83:  Keyword ::= i s
        //
        
        keywordKind[83] = (XQueryParsersym.TK_is);
      
    
        //
        // Rule 84:  Keyword ::= a t t r i b u t e
        //
        
        keywordKind[84] = (XQueryParsersym.TK_attribute);
      
    
        //
        // Rule 85:  Keyword ::= c o m m e n t
        //
        
        keywordKind[85] = (XQueryParsersym.TK_comment);
      
    
        //
        // Rule 86:  Keyword ::= d o c u m e n t - n o d e
        //
        
        keywordKind[86] = (XQueryParsersym.TK_document_node);
      
    
        //
        // Rule 87:  Keyword ::= e l e m e n t
        //
        
        keywordKind[87] = (XQueryParsersym.TK_element);
      
    
        //
        // Rule 88:  Keyword ::= e m p t y - s e q u e n c e
        //
        
        keywordKind[88] = (XQueryParsersym.TK_empty_sequence);
      
    
        //
        // Rule 89:  Keyword ::= i f
        //
        
        keywordKind[89] = (XQueryParsersym.TK_if);
      
    
        //
        // Rule 90:  Keyword ::= i t e m
        //
        
        keywordKind[90] = (XQueryParsersym.TK_item);
      
    
        //
        // Rule 91:  Keyword ::= n o d e
        //
        
        keywordKind[91] = (XQueryParsersym.TK_node);
      
    
        //
        // Rule 92:  Keyword ::= p r o c e s s i n g - i n s t r u c t i o n
        //
        
        keywordKind[92] = (XQueryParsersym.TK_processing_instruction);
      
    
        //
        // Rule 93:  Keyword ::= s c h e m a - a t t r i b u t e
        //
        
        keywordKind[93] = (XQueryParsersym.TK_schema_attribute);
      
    
        //
        // Rule 94:  Keyword ::= s c h e m a - e l e m e n t
        //
        
        keywordKind[94] = (XQueryParsersym.TK_schema_element);
      
    
        //
        // Rule 95:  Keyword ::= t e x t
        //
        
        keywordKind[95] = (XQueryParsersym.TK_text);
      
    
        //
        // Rule 96:  Keyword ::= t y p e s w i t c h
        //
        
        keywordKind[96] = (XQueryParsersym.TK_typeswitch);
      
    
        for (int i = 0; i < keywordKind.length; i++)
        {
            if (keywordKind[i] == 0)
                keywordKind[i] = identifierKind;
        }
    }
}

