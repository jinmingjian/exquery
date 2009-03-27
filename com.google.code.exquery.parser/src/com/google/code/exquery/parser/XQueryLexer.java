
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
import java.util.*;
import org.eclipse.imp.parser.ILexer;

public class XQueryLexer implements RuleAction, ILexer
{
    private XQueryLexerLpgLexStream lexStream;
    
    private static ParseTable prs = new XQueryLexerprs();
    public ParseTable getParseTable() { return prs; }

    private LexParser lexParser = new LexParser();
    public LexParser getParser() { return lexParser; }

    public int getToken(int i) { return lexParser.getToken(i); }
    public int getRhsFirstTokenIndex(int i) { return lexParser.getFirstToken(i); }
    public int getRhsLastTokenIndex(int i) { return lexParser.getLastToken(i); }

    public int getLeftSpan() { return lexParser.getToken(1); }
    public int getRightSpan() { return lexParser.getLastToken(); }

    public void resetKeywordLexer()
    {
        if (kwLexer == null)
              this.kwLexer = new XQueryKWLexer(lexStream.getInputChars(), XQueryParsersym.TK_NCName); //XXX
        else this.kwLexer.setInputChars(lexStream.getInputChars());
    }

    public void reset(String filename, int tab) throws java.io.IOException
    {
        lexStream = new XQueryLexerLpgLexStream(filename, tab);
        lexParser.reset((ILexStream) lexStream, prs, (RuleAction) this);
        resetKeywordLexer();
    }

    public void reset(char[] input_chars, String filename)
    {
        reset(input_chars, filename, 1);
    }
    
    public void reset(char[] input_chars, String filename, int tab)
    {
        lexStream = new XQueryLexerLpgLexStream(input_chars, filename, tab);
        lexParser.reset((ILexStream) lexStream, prs, (RuleAction) this);
        resetKeywordLexer();
    }
    
    public XQueryLexer(String filename, int tab) throws java.io.IOException 
    {
        reset(filename, tab);
    }

    public XQueryLexer(char[] input_chars, String filename, int tab)
    {
        reset(input_chars, filename, tab);
    }

    public XQueryLexer(char[] input_chars, String filename)
    {
        reset(input_chars, filename, 1);
    }

    public XQueryLexer() {}

    public ILexStream getILexStream() { return lexStream; }

    /**
     * @deprecated replaced by {@link #getILexStream()}
     */
    public ILexStream getLexStream() { return lexStream; }

    private void initializeLexer(IPrsStream prsStream, int start_offset, int end_offset)
    {
        if (lexStream.getInputChars() == null)
            throw new NullPointerException("LexStream was not initialized");
        lexStream.setPrsStream(prsStream);
        prsStream.makeToken(start_offset, end_offset, 0); // Token list must start with a bad token
    }

    private void addEOF(IPrsStream prsStream, int end_offset)
    {
        prsStream.makeToken(end_offset, end_offset, XQueryParsersym.TK_EOF_TOKEN); // and end with the end of file token
        prsStream.setStreamLength(prsStream.getSize());
    }

    public void lexer(IPrsStream prsStream)
    {
        lexer(null, prsStream);
    }
    
    public void lexer(Monitor monitor, IPrsStream prsStream)
    {
        initializeLexer(prsStream, 0, -1);
        lexParser.parseCharacters(monitor);  // Lex the input characters
        addEOF(prsStream, lexStream.getStreamIndex());
    }

    public void lexer(IPrsStream prsStream, int start_offset, int end_offset)
    {
        lexer(null, prsStream, start_offset, end_offset);
    }
    
    public void lexer(Monitor monitor, IPrsStream prsStream, int start_offset, int end_offset)
    {
        if (start_offset <= 1)
             initializeLexer(prsStream, 0, -1);
        else initializeLexer(prsStream, start_offset - 1, start_offset - 1);

        lexParser.parseCharacters(monitor, start_offset, end_offset);

        addEOF(prsStream, (end_offset >= lexStream.getStreamIndex() ? lexStream.getStreamIndex() : end_offset + 1));
    }

    /**
     * If a parse stream was not passed to this Lexical analyser then we
     * simply report a lexical error. Otherwise, we produce a bad token.
     */
    public void reportLexicalError(int startLoc, int endLoc) {
        IPrsStream prs_stream = lexStream.getPrsStream();
        if (prs_stream == null)
            lexStream.reportLexicalError(startLoc, endLoc);
        else {
            //
            // Remove any token that may have been processed that fall in the
            // range of the lexical error... then add one error token that spans
            // the error range.
            //
            for (int i = prs_stream.getSize() - 1; i > 0; i--) {
                if (prs_stream.getStartOffset(i) >= startLoc)
                     prs_stream.removeLastToken();
                else break;
            }
            prs_stream.makeToken(startLoc, endLoc, 0); // add an error token to the prsStream
        }        
    }

    //
    // The Lexer contains an array of characters as the input stream to be parsed.
    // There are methods to retrieve and classify characters.
    // The lexparser "token" is implemented simply as the index of the next character in the array.
    // The Lexer extends the abstract class LpgLexStream with an implementation of the abstract
    // method getKind.  The template defines the Lexer class and the lexer() method.
    // A driver creates the action class, "Lexer", passing an Option object to the constructor.
    //
    XQueryKWLexer kwLexer;
    boolean printTokens;
    private final static int ECLIPSE_TAB_VALUE = 4;

    public int [] getKeywordKinds() { return kwLexer.getKeywordKinds(); }

    public XQueryLexer(String filename) throws java.io.IOException
    {
        this(filename, ECLIPSE_TAB_VALUE);
        this.kwLexer = new XQueryKWLexer(lexStream.getInputChars(), XQueryParsersym.TK_NCName);
    }

    /**
     * @deprecated function replaced by {@link #reset(char [] content, String filename)}
     */
    public void initialize(char [] content, String filename)
    {
        reset(content, filename);
    }
    
    final void makeToken(int left_token, int right_token, int kind)
    {
        lexStream.makeToken(left_token, right_token, kind);
    }
    
    final void makeToken(int kind)
    {
        int startOffset = getLeftSpan(),
            endOffset = getRightSpan();
        lexStream.makeToken(startOffset, endOffset, kind);
        if (printTokens) printValue(startOffset, endOffset);
    }

    final void makeComment(int kind)
    {
        int startOffset = getLeftSpan(),
            endOffset = getRightSpan();
        lexStream.getIPrsStream().makeAdjunct(startOffset, endOffset, kind);
    }

    final void skipToken()
    {
        if (printTokens) printValue(getLeftSpan(), getRightSpan());
    }
    
    final void checkForKeyWord()
    {
        int startOffset = getLeftSpan(),
            endOffset = getRightSpan(),
            kwKind = kwLexer.lexer(startOffset, endOffset);
        lexStream.makeToken(startOffset, endOffset, kwKind);
        if (printTokens) printValue(startOffset, endOffset);
    }
    
    //
    // This flavor of checkForKeyWord is necessary when the default kind
    // (which is returned when the keyword filter doesn't match) is something
    // other than _IDENTIFIER.
    //
    final void checkForKeyWord(int defaultKind)
    {
        int startOffset = getLeftSpan(),
            endOffset = getRightSpan(),
            kwKind = kwLexer.lexer(startOffset, endOffset);
        if (kwKind == XQueryParsersym.TK_NCName)
            kwKind = defaultKind;
        lexStream.makeToken(startOffset, endOffset, kwKind);
        if (printTokens) printValue(startOffset, endOffset);
    }
    
    final void printValue(int startOffset, int endOffset)
    {
        String s = new String(lexStream.getInputChars(), startOffset, endOffset - startOffset + 1);
        System.out.print(s);
    }

    //
    //
    //
    static class XQueryLexerLpgLexStream extends LpgLexStream
    {
    public final static int tokenKind[] =
    {
        XQueryLexersym.Char_CtlCharNotWS,    // 000    0x00
        XQueryLexersym.Char_CtlCharNotWS,    // 001    0x01
        XQueryLexersym.Char_CtlCharNotWS,    // 002    0x02
        XQueryLexersym.Char_CtlCharNotWS,    // 003    0x03
        XQueryLexersym.Char_CtlCharNotWS,    // 004    0x04
        XQueryLexersym.Char_CtlCharNotWS,    // 005    0x05
        XQueryLexersym.Char_CtlCharNotWS,    // 006    0x06
        XQueryLexersym.Char_CtlCharNotWS,    // 007    0x07
        XQueryLexersym.Char_CtlCharNotWS,    // 008    0x08
        XQueryLexersym.Char_HT,              // 009    0x09
        XQueryLexersym.Char_LF,              // 010    0x0A
        XQueryLexersym.Char_CtlCharNotWS,    // 011    0x0B
        XQueryLexersym.Char_FF,              // 012    0x0C
        XQueryLexersym.Char_CR,              // 013    0x0D
        XQueryLexersym.Char_CtlCharNotWS,    // 014    0x0E
        XQueryLexersym.Char_CtlCharNotWS,    // 015    0x0F
        XQueryLexersym.Char_CtlCharNotWS,    // 016    0x10
        XQueryLexersym.Char_CtlCharNotWS,    // 017    0x11
        XQueryLexersym.Char_CtlCharNotWS,    // 018    0x12
        XQueryLexersym.Char_CtlCharNotWS,    // 019    0x13
        XQueryLexersym.Char_CtlCharNotWS,    // 020    0x14
        XQueryLexersym.Char_CtlCharNotWS,    // 021    0x15
        XQueryLexersym.Char_CtlCharNotWS,    // 022    0x16
        XQueryLexersym.Char_CtlCharNotWS,    // 023    0x17
        XQueryLexersym.Char_CtlCharNotWS,    // 024    0x18
        XQueryLexersym.Char_CtlCharNotWS,    // 025    0x19
        XQueryLexersym.Char_CtlCharNotWS,    // 026    0x1A
        XQueryLexersym.Char_CtlCharNotWS,    // 027    0x1B
        XQueryLexersym.Char_CtlCharNotWS,    // 028    0x1C
        XQueryLexersym.Char_CtlCharNotWS,    // 029    0x1D
        XQueryLexersym.Char_CtlCharNotWS,    // 030    0x1E
        XQueryLexersym.Char_CtlCharNotWS,    // 031    0x1F
        XQueryLexersym.Char_Space,           // 032    0x20
        XQueryLexersym.Char_Exclamation,     // 033    0x21
        XQueryLexersym.Char_DoubleQuote,     // 034    0x22
        XQueryLexersym.Char_Sharp,           // 035    0x23
        XQueryLexersym.Char_DollarSign,      // 036    0x24
        XQueryLexersym.Char_Percent,         // 037    0x25
        XQueryLexersym.Char_Ampersand,       // 038    0x26
        XQueryLexersym.Char_SingleQuote,     // 039    0x27
        XQueryLexersym.Char_LeftParen,       // 040    0x28
        XQueryLexersym.Char_RightParen,      // 041    0x29
        XQueryLexersym.Char_Star,            // 042    0x2A
        XQueryLexersym.Char_Plus,            // 043    0x2B
        XQueryLexersym.Char_Comma,           // 044    0x2C
        XQueryLexersym.Char_Minus,           // 045    0x2D
        XQueryLexersym.Char_Dot,             // 046    0x2E
        XQueryLexersym.Char_Slash,           // 047    0x2F
        XQueryLexersym.Char_0,               // 048    0x30
        XQueryLexersym.Char_1,               // 049    0x31
        XQueryLexersym.Char_2,               // 050    0x32
        XQueryLexersym.Char_3,               // 051    0x33
        XQueryLexersym.Char_4,               // 052    0x34
        XQueryLexersym.Char_5,               // 053    0x35
        XQueryLexersym.Char_6,               // 054    0x36
        XQueryLexersym.Char_7,               // 055    0x37
        XQueryLexersym.Char_8,               // 056    0x38
        XQueryLexersym.Char_9,               // 057    0x39
        XQueryLexersym.Char_Colon,           // 058    0x3A
        XQueryLexersym.Char_SemiColon,       // 059    0x3B
        XQueryLexersym.Char_LessThan,        // 060    0x3C
        XQueryLexersym.Char_Equal,           // 061    0x3D
        XQueryLexersym.Char_GreaterThan,     // 062    0x3E
        XQueryLexersym.Char_QuestionMark,    // 063    0x3F
        XQueryLexersym.Char_AtSign,          // 064    0x40
        XQueryLexersym.Char_A,               // 065    0x41
        XQueryLexersym.Char_B,               // 066    0x42
        XQueryLexersym.Char_C,               // 067    0x43
        XQueryLexersym.Char_D,               // 068    0x44
        XQueryLexersym.Char_E,               // 069    0x45
        XQueryLexersym.Char_F,               // 070    0x46
        XQueryLexersym.Char_G,               // 071    0x47
        XQueryLexersym.Char_H,               // 072    0x48
        XQueryLexersym.Char_I,               // 073    0x49
        XQueryLexersym.Char_J,               // 074    0x4A
        XQueryLexersym.Char_K,               // 075    0x4B
        XQueryLexersym.Char_L,               // 076    0x4C
        XQueryLexersym.Char_M,               // 077    0x4D
        XQueryLexersym.Char_N,               // 078    0x4E
        XQueryLexersym.Char_O,               // 079    0x4F
        XQueryLexersym.Char_P,               // 080    0x50
        XQueryLexersym.Char_Q,               // 081    0x51
        XQueryLexersym.Char_R,               // 082    0x52
        XQueryLexersym.Char_S,               // 083    0x53
        XQueryLexersym.Char_T,               // 084    0x54
        XQueryLexersym.Char_U,               // 085    0x55
        XQueryLexersym.Char_V,               // 086    0x56
        XQueryLexersym.Char_W,               // 087    0x57
        XQueryLexersym.Char_X,               // 088    0x58
        XQueryLexersym.Char_Y,               // 089    0x59
        XQueryLexersym.Char_Z,               // 090    0x5A
        XQueryLexersym.Char_LeftBracket,     // 091    0x5B
        XQueryLexersym.Char_BackSlash,       // 092    0x5C
        XQueryLexersym.Char_RightBracket,    // 093    0x5D
        XQueryLexersym.Char_Caret,           // 094    0x5E
        XQueryLexersym.Char__,               // 095    0x5F
        XQueryLexersym.Char_BackQuote,       // 096    0x60
        XQueryLexersym.Char_a,               // 097    0x61
        XQueryLexersym.Char_b,               // 098    0x62
        XQueryLexersym.Char_c,               // 099    0x63
        XQueryLexersym.Char_d,               // 100    0x64
        XQueryLexersym.Char_e,               // 101    0x65
        XQueryLexersym.Char_f,               // 102    0x66
        XQueryLexersym.Char_g,               // 103    0x67
        XQueryLexersym.Char_h,               // 104    0x68
        XQueryLexersym.Char_i,               // 105    0x69
        XQueryLexersym.Char_j,               // 106    0x6A
        XQueryLexersym.Char_k,               // 107    0x6B
        XQueryLexersym.Char_l,               // 108    0x6C
        XQueryLexersym.Char_m,               // 109    0x6D
        XQueryLexersym.Char_n,               // 110    0x6E
        XQueryLexersym.Char_o,               // 111    0x6F
        XQueryLexersym.Char_p,               // 112    0x70
        XQueryLexersym.Char_q,               // 113    0x71
        XQueryLexersym.Char_r,               // 114    0x72
        XQueryLexersym.Char_s,               // 115    0x73
        XQueryLexersym.Char_t,               // 116    0x74
        XQueryLexersym.Char_u,               // 117    0x75
        XQueryLexersym.Char_v,               // 118    0x76
        XQueryLexersym.Char_w,               // 119    0x77
        XQueryLexersym.Char_x,               // 120    0x78
        XQueryLexersym.Char_y,               // 121    0x79
        XQueryLexersym.Char_z,               // 122    0x7A
        XQueryLexersym.Char_LeftBrace,       // 123    0x7B
        XQueryLexersym.Char_VerticalBar,     // 124    0x7C
        XQueryLexersym.Char_RightBrace,      // 125    0x7D
        XQueryLexersym.Char_Tilde,           // 126    0x7E

        XQueryLexersym.Char_AfterASCII,      // for all chars in range 128..65534
        XQueryLexersym.Char_EOF              // for '\uffff' or 65535 
    };
            
    public final int getKind(int i)  // Classify character at ith location
    {
        int c = (i >= getStreamLength() ? '\uffff' : getCharValue(i));
        return (c < 128 // ASCII Character
                  ? tokenKind[c]
                  : c == '\uffff'
                       ? XQueryLexersym.Char_EOF
                       : XQueryLexersym.Char_AfterASCII);
    }

    public String[] orderedExportedSymbols() { return XQueryParsersym.orderedTerminalSymbols; }

    public XQueryLexerLpgLexStream(String filename, int tab) throws java.io.IOException
    {
        super(filename, tab);
    }

    public XQueryLexerLpgLexStream(char[] input_chars, String filename, int tab)
    {
        super(input_chars, filename, tab);
    }

    public XQueryLexerLpgLexStream(char[] input_chars, String filename)
    {
        super(input_chars, filename, 1);
    }
    }

    public void ruleAction(int ruleNumber)
    {
        switch(ruleNumber)
        {

            //
            // Rule 1:  Token ::= NCName
            //
            case 1: { 
            
                checkForKeyWord(); 
                  break;
            }
    
            //
            // Rule 2:  Token ::= WhiteSpaces
            //
            case 2: { 
            
                skipToken();
                  break;
            }
    
            //
            // Rule 3:  Token ::= ( : CMInside : )
            //
            case 3: { 
            
                makeComment(XQueryParsersym.TK_Comment);
                  break;
            }
    
            //
            // Rule 4:  Token ::= IntegerLiteral
            //
            case 4: { 
            
                makeToken(XQueryParsersym.TK_IntegerLiteral);
                  break;
            }
    
            //
            // Rule 5:  Token ::= DecimalLiteral
            //
            case 5: { 
            
                makeToken(XQueryParsersym.TK_DecimalLiteral);
                  break;
            }
    
            //
            // Rule 6:  Token ::= DoubleLiteral
            //
            case 6: { 
            
                makeToken(XQueryParsersym.TK_DoubleLiteral);
                  break;
            }
    
            //
            // Rule 7:  Token ::= PredefinedEntityRef
            //
            case 7: { 
            
                makeToken(XQueryParsersym.TK_PredefinedEntityRef);
                  break;
            }
    
            //
            // Rule 8:  Token ::= CharRef
            //
            case 8: { 
            
                makeToken(XQueryParsersym.TK_CharRef);
                  break;
            }
    
            //
            // Rule 9:  Token ::= StringLiteral
            //
            case 9: { 
            
                makeToken(XQueryParsersym.TK_StringLiteral);
                  break;
            }
    
            //
            // Rule 10:  Token ::= EscapeQuot
            //
            case 10: { 
            
                makeToken(XQueryParsersym.TK_EscapeQuot);
                  break;
            }
    
            //
            // Rule 11:  Token ::= EscapeApos
            //
            case 11: { 
            
                makeToken(XQueryParsersym.TK_EscapeApos);
                  break;
            }
    
            //
            // Rule 12:  Token ::= DirCommentConstructor
            //
            case 12: { 
            
                makeToken(XQueryParsersym.TK_DirCommentConstructor);
                  break;
            }
    
            //
            // Rule 13:  Token ::= CDataSection
            //
            case 13: { 
            
                makeToken(XQueryParsersym.TK_CDataSection);
                  break;
            }
    
            //
            // Rule 14:  Token ::= ,
            //
            case 14: { 
            
                makeToken(XQueryParsersym.TK_COMMA);
                  break;
            }
    
            //
            // Rule 15:  Token ::= ;
            //
            case 15: { 
            
                makeToken(XQueryParsersym.TK_SEMICOLON);
                  break;
            }
    
            //
            // Rule 16:  Token ::= :
            //
            case 16: { 
            
                makeToken(XQueryParsersym.TK_COLON);
                  break;
            }
    
            //
            // Rule 17:  Token ::= "
            //
            case 17: { 
            
                makeToken(XQueryParsersym.TK_DOUBLEQUOTE);
                  break;
            }
    
            //
            // Rule 18:  Token ::= '
            //
            case 18: { 
            
                makeToken(XQueryParsersym.TK_SINGLEQUOTE);
                  break;
            }
    
            //
            // Rule 19:  Token ::= +
            //
            case 19: { 
            
                makeToken(XQueryParsersym.TK_PLUS);
                  break;
            }
    
            //
            // Rule 20:  Token ::= -
            //
            case 20: { 
            
                makeToken(XQueryParsersym.TK_MINUS);
                  break;
            }
    
            //
            // Rule 21:  Token ::= (
            //
            case 21: { 
            
                makeToken(XQueryParsersym.TK_LEFTPAREN);
                  break;
            }
    
            //
            // Rule 22:  Token ::= )
            //
            case 22: { 
            
                makeToken(XQueryParsersym.TK_RIGHTPAREN);
                  break;
            }
    
            //
            // Rule 23:  Token ::= {
            //
            case 23: { 
            
                makeToken(XQueryParsersym.TK_LEFTBRACE);
                  break;
            }
    
            //
            // Rule 24:  Token ::= }
            //
            case 24: { 
            
                makeToken(XQueryParsersym.TK_RIGHTBRACE);
                  break;
            }
    
            //
            // Rule 25:  Token ::= [
            //
            case 25: { 
            
                makeToken(XQueryParsersym.TK_LEFTBRACKET);
                  break;
            }
    
            //
            // Rule 26:  Token ::= ]
            //
            case 26: { 
            
                makeToken(XQueryParsersym.TK_RIGHTBRACKET);
                  break;
            }
    
            //
            // Rule 27:  Token ::= /
            //
            case 27: { 
            
                makeToken(XQueryParsersym.TK_SLASH);
                  break;
            }
    
            //
            // Rule 28:  Token ::= / /
            //
            case 28: { 
            
                makeToken(XQueryParsersym.TK_DoubleSlash);
                  break;
            }
    
            //
            // Rule 29:  Token ::= < <
            //
            case 29: { 
            
                makeToken(XQueryParsersym.TK_NodeCompLeft);
                  break;
            }
    
            //
            // Rule 30:  Token ::= > >
            //
            case 30: { 
            
                makeToken(XQueryParsersym.TK_NodeCompRight);
                  break;
            }
    
            //
            // Rule 31:  Token ::= $
            //
            case 31: { 
            
                makeToken(XQueryParsersym.TK_DOLLAR);
                  break;
            }
    
            //
            // Rule 32:  Token ::= { {
            //
            case 32: { 
            
                makeToken(XQueryParsersym.TK_DoubleLEFTBRACE);
                  break;
            }
    
            //
            // Rule 33:  Token ::= } }
            //
            case 33: { 
            
                makeToken(XQueryParsersym.TK_DoubleRIGHTBRACE);
                  break;
            }
    
            //
            // Rule 34:  Token ::= ?
            //
            case 34: { 
            
                makeToken(XQueryParsersym.TK_QUESTION);
                  break;
            }
    
            //
            // Rule 35:  Token ::= *
            //
            case 35: { 
            
                makeToken(XQueryParsersym.TK_TIMES);
                  break;
            }
    
            //
            // Rule 36:  Token ::= |
            //
            case 36: { 
            
                makeToken(XQueryParsersym.TK_ORBar);
                  break;
            }
    
            //
            // Rule 37:  Token ::= =
            //
            case 37: { 
            
                makeToken(XQueryParsersym.TK_EQUAL);
                  break;
            }
    
            //
            // Rule 38:  Token ::= ! =
            //
            case 38: { 
            
                makeToken(XQueryParsersym.TK_NOTEQUAL);
                  break;
            }
    
            //
            // Rule 39:  Token ::= <
            //
            case 39: { 
            
                makeToken(XQueryParsersym.TK_LESS);
                  break;
            }
    
            //
            // Rule 40:  Token ::= < =
            //
            case 40: { 
            
                makeToken(XQueryParsersym.TK_LESSEQUAL);
                  break;
            }
    
            //
            // Rule 41:  Token ::= >
            //
            case 41: { 
            
                makeToken(XQueryParsersym.TK_GREATER);
                  break;
            }
    
            //
            // Rule 42:  Token ::= > =
            //
            case 42: { 
            
                makeToken(XQueryParsersym.TK_GREATEREQUAL);
                  break;
            }
    
            //
            // Rule 43:  Token ::= ( #
            //
            case 43: { 
            
                makeToken(XQueryParsersym.TK_PragmaST);
                  break;
            }
    
            //
            // Rule 44:  Token ::= # )
            //
            case 44: { 
            
                makeToken(XQueryParsersym.TK_PragmaET);
                  break;
            }
    
            //
            // Rule 45:  Token ::= : :
            //
            case 45: { 
            
                makeToken(XQueryParsersym.TK_DoubleCOLON);
                  break;
            }
    
            //
            // Rule 46:  Token ::= @
            //
            case 46: { 
            
                makeToken(XQueryParsersym.TK_ATSIGN);
                  break;
            }
    
            //
            // Rule 47:  Token ::= .
            //
            case 47: { 
            
                makeToken(XQueryParsersym.TK_DOT);
                  break;
            }
    
            //
            // Rule 48:  Token ::= . .
            //
            case 48: { 
            
                makeToken(XQueryParsersym.TK_DOTDOT);
                  break;
            }
    
            //
            // Rule 49:  Token ::= : =
            //
            case 49: { 
            
                makeToken(XQueryParsersym.TK_ASSIGN);
                  break;
            }
    
            //
            // Rule 50:  Token ::= < ?
            //
            case 50: { 
            
                makeToken(XQueryParsersym.TK_PIST);
                  break;
            }
    
            //
            // Rule 51:  Token ::= ? >
            //
            case 51: { 
            
                makeToken(XQueryParsersym.TK_PIET);
                  break;
            }
    
            //
            // Rule 52:  Token ::= / >
            //
            case 52: { 
            
                makeToken(XQueryParsersym.TK_STEndMark);
                  break;
            }
    
            //
            // Rule 53:  Token ::= < /
            //
            case 53: { 
            
                makeToken(XQueryParsersym.TK_ETStartMark);
                  break;
            }
    
    
            default:
                break;
        }
        return;
    }
}

