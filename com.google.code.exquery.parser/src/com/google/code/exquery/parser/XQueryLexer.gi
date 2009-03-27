--/**
-- * <copyright>
-- *
-- * Copyright (c) 2009 Jin Mingjian, and others.
-- * All rights reserved.   This program and the accompanying materials
-- * are made available under the terms of the Eclipse Public License v1.0
-- * which accompanies this distribution, and is available at
-- * http://www.eclipse.org/legal/epl-v10.html
-- *
-- * Contributors:
-- *   Jin Mingjian - Initial API and implementation
-- *
-- * </copyright>
-- */
--
-- The XQuery Lexer
--

%options package=com.google.code.exquery.parser
%options template=./lpg/LexerTemplateXQ.gi
%options filter=XQueryKWLexer.gi
%options la=6 

%Globals
    /.import java.util.*;
    import org.eclipse.imp.parser.ILexer;
    ./
%End

%Define
    $additional_interfaces /., ILexer./
    $kw_lexer_class /.$XQueryKWLexer./
%End

%Include
    ./lpg/LexerBasicMapXQ.gi
%End


%Notice
	/.
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
	./
%End



%Export
          
     COMMA                     --  ","
     SEMICOLON                 --  ";"
     COLON                     --  ":" 
     DOUBLEQUOTE               --  '"'
     SINGLEQUOTE               --  "'"
     PLUS                      --  "+"
     MINUS                     --  "-"
     LEFTPAREN                 --  '('
     RIGHTPAREN                --  ')'
     LEFTBRACE                 --  '{'
     RIGHTBRACE                --  '}'
     LEFTBRACKET               --  '['
     RIGHTBRACKET               --  ']'
     SLASH                     --  '/'
     DoubleSlash               --  "//" 
     NodeCompLeft              --  "<<"
     NodeCompRight             --   ">>"     
     DOLLAR                    --  "$"
     DoubleLEFTBRACE           --  "{{"
     DoubleRIGHTBRACE          --  "}}"
     QUESTION                  --  "?"
     TIMES                     --  "*" 
     ORBar                     --  "|"
     EQUAL                    --   "="
     NOTEQUAL                 --   "!="
     LESS                     --   "<"
     LESSEQUAL                --   "<="
     GREATER                  --   ">" 
     GREATEREQUAL             --   ">=" 
     PragmaST                 --   "(#"
     PragmaET                 --   "#)"
     DoubleCOLON              --   "::"
     ATSIGN                   --   "@"
     DOT                      --   '.'
     DOTDOT                   --   ".."
     ASSIGN                   --   ":="

     PIST                     --    "<?"
     PIET                     --    "?>" 
      
     STEndMark                --    "/>"
     ETStartMark              --    "</"  
       
        --
     NCName
     Comment  
     IntegerLiteral
     DecimalLiteral
     DoubleLiteral
     PredefinedEntityRef
     CharRef
     StringLiteral
     EscapeQuot
     EscapeApos
     DirCommentConstructor
     CDataSection
        
        --
        
         
%End

%Terminals
    CtlCharNotWS

    LF   CR   HT   FF

    a    b    c    d    e    f    g    h    i    j    k    l    m
    n    o    p    q    r    s    t    u    v    w    x    y    z
    _

    A    B    C    D    E    F    G    H    I    J    K    L    M
    N    O    P    Q    R    S    T    U    V    W    X    Y    Z

    0    1    2    3    4    5    6    7    8    9

    AfterASCII   ::= '\u0080..\ufffe'
    Space        ::= ' '
    LF           ::= NewLine
    CR           ::= Return
    HT           ::= HorizontalTab
    FF           ::= FormFeed
    DoubleQuote  ::= '"'
    SingleQuote  ::= "'"
    Percent      ::= '%'
    VerticalBar  ::= '|'
    Exclamation  ::= '!'
    AtSign       ::= '@'
    BackQuote    ::= '`'
    Tilde        ::= '~'
    Sharp        ::= '#'
    DollarSign   ::= '$'
    Ampersand    ::= '&'
    Caret        ::= '^'
    Colon        ::= ':'
    SemiColon    ::= ';'
    BackSlash    ::= '\'
    LeftBrace    ::= '{'
    RightBrace   ::= '}'
    LeftBracket  ::= '['
    RightBracket ::= ']'
    QuestionMark ::= '?'
    Comma        ::= ','
    Dot          ::= '.'
    LessThan     ::= '<'
    GreaterThan  ::= '>'
    Plus         ::= '+'
    Minus        ::= '-'
    Slash        ::= '/'
    Star         ::= '*'
    LeftParen    ::= '('
    RightParen   ::= ')'
    Equal        ::= '='
    
    --
    --NonAsciiBaseChar
    --Ideographic
    --CombiningChar
    --Extender
    --NonAsciiDigit
    --OtherValidChar

    --Unused

    --ExtSubsetMarker
    --ExtParserEntMarker
    
    
    
%End


%Start
    Token
%End


%Rules
 -- 
    Token ::= NCName
        /.$BeginJava
                    checkForKeyWord(); 
          $EndJava
        ./
    
    Token ::= WhiteSpaces
        /.$BeginJava
                    skipToken();
          $EndJava
        ./
        
    Token ::=  '(' ':' CMInside ':' ')'
        /.$BeginJava
                    makeComment($_Comment);
          $EndJava
        ./
        
    
 --    
     
    Token ::= IntegerLiteral
        /.$BeginJava
                    makeToken($_IntegerLiteral);
          $EndJava
        ./
    Token ::= DecimalLiteral
        /.$BeginJava
                    makeToken($_DecimalLiteral);
          $EndJava
        ./
    Token ::= DoubleLiteral
        /.$BeginJava
                    makeToken($_DoubleLiteral);
          $EndJava
        ./
    Token ::= PredefinedEntityRef
        /.$BeginJava
                    makeToken($_PredefinedEntityRef);
          $EndJava
        ./
      
    Token ::= CharRef
        /.$BeginJava
                    makeToken($_CharRef);
          $EndJava
        ./ 
    Token ::= StringLiteral
        /.$BeginJava
                    makeToken($_StringLiteral);
          $EndJava
        ./
    Token ::= EscapeQuot
        /.$BeginJava
                    makeToken($_EscapeQuot);
          $EndJava
        ./
    Token ::= EscapeApos
        /.$BeginJava
                    makeToken($_EscapeApos);
          $EndJava
        ./
    Token ::= DirCommentConstructor
        /.$BeginJava
                    makeToken($_DirCommentConstructor);
          $EndJava
        ./
   Token ::= CDataSection
        /.$BeginJava
                    makeToken($_CDataSection);
          $EndJava
        ./ 
 
    
        
 --       
    Token ::= ","
        /.$BeginJava
                    makeToken($_COMMA);
          $EndJava
        ./
    
    Token ::= ";"
        /.$BeginJava
                    makeToken($_SEMICOLON);
          $EndJava
        ./

    Token ::= ":"   
        /.$BeginJava
                    makeToken($_COLON);
          $EndJava
        ./
    
    Token ::= '"'   
        /.$BeginJava
                    makeToken($_DOUBLEQUOTE);
          $EndJava
        ./
    Token ::= "'"   
        /.$BeginJava
                    makeToken($_SINGLEQUOTE);
          $EndJava
        ./    

    Token ::= '+'
        /.$BeginJava
                    makeToken($_PLUS);
          $EndJava
        ./

    Token ::= '-'
        /.$BeginJava
                    makeToken($_MINUS);
          $EndJava
        ./

    Token ::= '('
        /.$BeginJava
                    makeToken($_LEFTPAREN);
          $EndJava
        ./

    Token ::= ')'
        /.$BeginJava
                    makeToken($_RIGHTPAREN);
          $EndJava
        ./

    Token ::= '{'
        /.$BeginJava
                    makeToken($_LEFTBRACE);
          $EndJava
        ./

    Token ::= '}'
        /.$BeginJava
                    makeToken($_RIGHTBRACE);
          $EndJava
        ./

    Token ::= '['
        /.$BeginJava
                    makeToken($_LEFTBRACKET);
          $EndJava
        ./

    Token ::= ']'
        /.$BeginJava
                    makeToken($_RIGHTBRACKET);
          $EndJava
        ./

    Token ::= '/'
        /.$BeginJava
                    makeToken($_SLASH);
          $EndJava
        ./

    Token ::= "/" "/"
        /.$BeginJava
                    makeToken($_DoubleSlash);
          $EndJava
        ./

    Token ::= "<" "<"
        /.$BeginJava
                    makeToken($_NodeCompLeft);
          $EndJava
        ./

    Token ::= ">" ">"
        /.$BeginJava
                    makeToken($_NodeCompRight);
          $EndJava
        ./

    Token ::= "$"
        /.$BeginJava
                    makeToken($_DOLLAR);
          $EndJava
        ./
    
    Token ::= "{" "{"
        /.$BeginJava
                    makeToken($_DoubleLEFTBRACE);
          $EndJava
        ./
    
    Token ::= "}" "}"
        /.$BeginJava
                    makeToken($_DoubleRIGHTBRACE);
          $EndJava
        ./
    
    Token ::= "?"
        /.$BeginJava
                    makeToken($_QUESTION);
          $EndJava
        ./
    Token ::= "*"
        /.$BeginJava
                    makeToken($_TIMES);
          $EndJava
        ./
    Token ::= "|"
        /.$BeginJava
                    makeToken($_ORBar);
          $EndJava
        ./
    Token ::= "="
        /.$BeginJava
                    makeToken($_EQUAL);
          $EndJava
        ./
    Token ::= '!' '='
        /.$BeginJava
                    makeToken($_NOTEQUAL);
          $EndJava
        ./
    Token ::= "<"
        /.$BeginJava
                    makeToken($_LESS);
          $EndJava
        ./
     Token ::= "<" "="
        /.$BeginJava
                    makeToken($_LESSEQUAL);
          $EndJava
        ./
    Token ::= ">"
        /.$BeginJava
                    makeToken($_GREATER);
          $EndJava
        ./
    Token ::= ">" "="
        /.$BeginJava
                    makeToken($_GREATEREQUAL);
          $EndJava
        ./
    Token ::= "(" "#"
        /.$BeginJava
                    makeToken($_PragmaST);
          $EndJava
        ./
   Token ::= "#" ")"
        /.$BeginJava
                    makeToken($_PragmaET);
          $EndJava
        ./ 
    
    Token ::= ":" ":"
        /.$BeginJava
                    makeToken($_DoubleCOLON);
          $EndJava
        ./
    
    Token ::= "@"
        /.$BeginJava
                    makeToken($_ATSIGN);
          $EndJava
        ./
   Token ::= '.'
        /.$BeginJava
                    makeToken($_DOT);
          $EndJava
        ./ 
    Token ::= "." "."
        /.$BeginJava
                    makeToken($_DOTDOT);
          $EndJava
        ./
   Token ::= ":" "="
        /.$BeginJava
                    makeToken($_ASSIGN);
          $EndJava
        ./ 
    
   Token ::= "<" "?"
        /.$BeginJava
                    makeToken($_PIST);
          $EndJava
        ./
   Token ::= "?" ">"
        /.$BeginJava
                    makeToken($_PIET);
          $EndJava
        ./     
        
        
    
    Token ::= "/" ">"
        /.$BeginJava
                    makeToken($_STEndMark);
          $EndJava
        ./
    Token ::= "<" "/"
        /.$BeginJava
                    makeToken($_ETStartMark);
          $EndJava
        ./      



    --
    
    WhiteSpaces ::= SpaceChar
                  | WhiteSpaces SpaceChar
    
    SpaceChar ::= Space | LF | CR | HT | FF


    IntegerLiteral     ::= Digit | IntegerLiteral Digit
             
    DecimalLiteral     ::=   "." IntegerLiteral |  IntegerLiteral "."  |  IntegerLiteral "." IntegerLiteral       

    DoubleLiteral ::=  DecimalLiteral Exponent
                    | IntegerLiteral Exponent
                    
    Exponent ::= LetterEe IntegerLiteral
               | LetterEe '-' IntegerLiteral
               | LetterEe '+' IntegerLiteral

    LetterEe ::= 'e' | 'E'

    
    PredefinedEntityRef   ::=    "&" "l" "t" ";" 
                               | "&" "g" "t" ";" 
                               | "&" "a" "m" "p" ";" 
                               | "&" "q" "u" "o" "t" ";" 
                               | "&" "a" "p" "o" "s" ";" 
    

    Digit ::= 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9

    aA ::= a | A
    bB ::= b | B
    cC ::= c | C
    dD ::= d | D
    eE ::= e | E
    fF ::= f | F
    gG ::= g | G
    hH ::= h | H
    iI ::= i | I
    jJ ::= j | J
    kK ::= k | K
    lL ::= l | L
    mM ::= m | M
    nN ::= n | N
    oO ::= o | O
    pP ::= p | P
    qQ ::= q | Q
    rR ::= r | R
    sS ::= s | S
    tT ::= t | T
    uU ::= u | U
    vV ::= v | V
    wW ::= w | W
    xX ::= x | X
    yY ::= y | Y
    zZ ::= z | Z

    Letter ::= aA | bB | cC | dD | eE | fF | gG | hH | iI | jJ | kK | lL | mM | nN | oO | pP | qQ | rR | sS | tT | uU | vV | wW | xX | yY | zZ

    -- any ::= letter | digit | special | white

    Special ::= '+' | '-' | '(' | ')' | '"' | '!' | '@' | '`' | '~' | '.' |
                '%' | '&' | '^' | ':' | ';' | "'" | '\' | '|' | '{' | '}' |
                '[' | ']' | '?' | ',' | '<' | '>' | '=' | '#' | '*' | '_' |
                '/' | '$'

    SpecialWithoutDoubleQuote ::= '+' | '-' | '(' | ')' |  '!' | '@' | '`' | '~' | '.' | '^' |
                                  '%' | ':' | ';' | "'" | '\'  | '|' | '{' | '}' |
                                  '[' | ']' | '?' | ',' | '<'  | '>' | '=' | '#' | '*' | '_' |
                                  '/' | '$'
                
    SpecialWithoutSingleQuote ::= '+' | '-' | '(' | ')' |  '!' | '@' | '`' | '~' | '.' | '^' |
                                  '%' | ':' | ';' | '"' | '\'  | '|' | '{' | '}' |
                                  '[' | ']' | '?' | ',' | '<'  | '>' | '=' | '#' | '*' | '_' |
                                  '/' | '$'
                                  
    SpecialForQuotAttr ::= '+' | '-' | '(' | ')' | '!' | '@'  | '`' | '~' | '.' | '^' |
                                  '%' | ':' | ';' | "'" | '\'  | '|' |  
                                  '[' | ']' | '?' | ',' |  '>' | '=' | '#' | '*' | '_' |
                                  '/' | '$'
                                 
    SpecialForAposAttr ::= '+' | '-' | '(' | ')' |  '!' | '@'  | '`' | '~' | '.' | '^' |
                                  '%' | ':' | ';' | '"' | '\'  | '|' |  
                                  '[' | ']' | '?' | ',' |  '>' | '=' | '#' | '*' | '_' |
                                  '/' | '$'
                                  
   SpecialNotLeftRightParenColon ::= '+' | '-' |  '"' | '!' | '@' | '`' | '~' | '.' |
                '%' | '&' | '^' | ';' | "'" | '\' | '|' | '{' | '}' |
                '[' | ']' | '?' | ',' | '<' | '>' | '=' | '#' | '*' | '_' |
                '/' | '$'                               
                                      
    SpecialNotDash ::= '+' | '(' | ')' | '"' | '!' | '@' | '`' | '~' | '.' |
                '%' | '&' | '^' | ':' | ';' | "'" | '\' | '|' | '{' | '}' |
                '[' | ']' | '?' | ',' | '<' | '>' | '=' | '#' | '*' | '_' |
                '/' | '$'
    
    SpecialNotQuestionGreaterThan ::= '+' | '-' | '(' | ')' | '"' | '!' | '@' | '`' | '~' | '.' |
                '%' | '&' | '^' | ':' | ';' | "'" | '\' | '|' | '{' | '}' |
                '[' | ']' |  ',' | '<' |   '=' | '#' | '*' | '_' |
                '/' | '$'
    
    
    
    SpecialNotRightBracketGreaterThan ::= '+' | '-' | '(' | ')' | '"' | '!' | '@' | '`' | '~' | '.' |
                '%' | '&' | '^' | ':' | ';' | "'" | '\' | '|' | '{' | '}' |
                '[' |   '?' | ',' | '<' |  '=' | '#' | '*' | '_' |
                '/' | '$'





    ---

    --Char -> Letter | Digit | Special | SpaceChar | AfterASCII
    --Chars ::= Char | Chars Char
    
    
    
    
    
    
    NCNameStartChar -> "_" | Letter | AfterASCII
    
    NCNameChar ->  NCNameStartChar "-" | "." | Digit
   
    NCNameChars ::= NCNameChar | NCNameChars NCNameChar
   
    NCName ::=   NCNameStartChar | NCNameStartChar NCNameChars            
         
    
 
 
    HexDigits -> HexDigit  |  HexDigits HexDigit
    atof -> a | b | c | d | e | f | A | B | C | D | E | F
    HexDigit ->  Digit | atof
    
    CharRef	 ::=   '&' '#' IntegerLiteral ';' | '&' '#' 'x' HexDigits ';'
 
    CharWithoutDoubleQuote ::=   Letter | Digit | SpecialWithoutDoubleQuote | SpaceChar |  AfterASCII | CharRef | PredefinedEntityRef 
    CharWithoutSingleQuote ::=   Letter | Digit | SpecialWithoutSingleQuote | SpaceChar |  AfterASCII | CharRef | PredefinedEntityRef
    CharWithoutDoubleQuotes -> CharWithoutDoubleQuote | CharWithoutDoubleQuotes CharWithoutDoubleQuote
    CharWithoutSingleQuotes -> CharWithoutSingleQuote | CharWithoutSingleQuotes CharWithoutSingleQuote 
 
    StringLiteral  ::= '"' CharWithoutDoubleQuotes '"' | "'" CharWithoutSingleQuotes  "'"
    
    --CharWithoutDash ::= Letter | Digit | SpecialWithoutDash | SpaceChar | AfterASCII
    --DirCommentContentsM ::= CharWithoutDash | DirCommentContentsM '-' CharWithoutDash
    --DirCommentContentsFull  ::= DirCommentContentsM | '-' DirCommentContentsM | DirCommentContentsM '-' | '-' DirCommentContentsM '-'
       
    --DirPIContents  ::= 
    EscapeQuot	   ::=   	'"' '"'
    EscapeApos	   ::=   	"'" "'"
    
    --QuotAttrContentChar ::= Letter | Digit | SpecialForQuotAttr | SpaceChar | AfterASCII
    
    --AposAttrContentChar ::= Letter | Digit | SpecialForAposAttr | SpaceChar | AfterASCII
    
    --
    --
    
    --Comment ::= '(' ':' ':' ')' | '(' ':' Chars ':' ')'
    CMInside ::= %empty
             | CMInside LeftParen NotLeftParenRightParenColon
             | CMInside RightParen NotLeftParenRightParenColon
             | CMInside Colon NotLeftParenRightParenColon
             | CMInside NotLeftParenRightParenColon
   
    
    NotLeftParenRightParenColon ->   Letter | Digit |  SpaceChar | AfterASCII
                                       | SpecialNotLeftRightParenColon


    --DirCommentConstructor
    DirCommentConstructor  ->  "<" "!" "-" "-"   DCCInside     "-" "-" ">"
    DCCInside ::=  %empty
             | DCCInside Minus NotDash
             | DCCInside NotDash
    NotDash ->   Letter | Digit |  SpaceChar | AfterASCII | SpecialNotDash
     
    --DirPIConstructor
    --DirPIConstructor ->       "<" "?" PITarget  DPICInside   "?" ">"
    --DPICInside  ->  %empty
    --         | DPICInside QuestionMark NotQuestionGreaterThan
    --        | DPICInside GreaterThan  NotQuestionGreaterThan
    --         | DPICInside NotQuestionGreaterThan 
    -- NotQuestionGreaterThan ->   Letter | Digit |  SpaceChar | AfterASCII | SpecialNotQuestionGreaterThan         
    --PITarget -> NCName    --   NCName Colon NCName        --XXX: not exclude xml....
    
    --CDataSection
    CDataSection ->  "<" "!" "[" "C" "D" "A" "T" "A" "["  CDSInside  "]" "]" ">"   
    CDSInside ->    %empty
             | CDSInside RightBracket NotRightBracketGreaterThan
             | CDSInside GreaterThan  NotRightBracketGreaterThan
             | CDSInside RightBracket RightBracket  NotRightBracketGreaterThan
             | CDSInside GreaterThan  GreaterThan   NotRightBracketGreaterThan
             | CDSInside RightBracket GreaterThan   NotRightBracketGreaterThan
             | CDSInside GreaterThan  RightBracket  NotRightBracketGreaterThan
             | CDSInside NotRightBracketGreaterThan 
    NotRightBracketGreaterThan ->  Letter | Digit |  SpaceChar | AfterASCII | SpecialNotRightBracketGreaterThan 
    
    
     
    
    
%End
