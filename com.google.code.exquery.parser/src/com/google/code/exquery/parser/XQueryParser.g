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
-- The XQuery Parser
--


%options package=com.google.code.exquery.parser
%options parent_saved
%options automatic_ast=toplevel
%options visitor=preorder
%options ast_directory=./ast
%options ast_type=ASTNode
%options la=3 
%options import_terminals=XQueryLexer.gi
%options template=./lpg/btParserTemplateF.gi


%Globals   
    /.import org.eclipse.imp.parser.IParser;
    import java.util.Hashtable;
    import java.util.Stack;
    ./ 
%End 

%Define
    $ast_class /.Object./
    $additional_interfaces /., IParser./
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


%Alias
    Separator   ::=    SEMICOLON
    URILiteral    ::=    StringLiteral
    --
    DirPIContents    ::=  NCName
    PITarget         ::=  QName 
    
    PragmaContents   ::=  NCName
    
    ElementDeclaration    ::=    QName 
    AttributeDeclaration  ::=    QName 
    ElementName           ::=    QName
    AttributeName         ::=    QName
    TypeName              ::=    QName
    AtomicType            ::=    QName
    
    
    QuotAttrContentChar ::= NCName
    ElementContentChar ::= NCName
    AposAttrContentChar ::= NCName
    
%End


%Start
         Module
%End

--%Recover
--   MissingExpression
--%End

%Rules
    --  some naming convention:
    --  Opt     =  optional  (?)
    --  List    =  repetition (*/+)
    --  Blk     =  for convenience, if words in w3c grammar confluct with keywords in LPG Gen, use this trick temporarily 
    
    --x Module    ::=    VersionDeclOpt (LibraryModule | MainModule)
    Module$Module    ::=   %empty                           -- additonal grammar for validating %empty
                         | VersionDeclOpt LibraryModule 
                         | VersionDeclOpt MainModule
    
    VersionDeclOpt  ::=  %empty  |  xquery version StringLiteral EncodingOpt Separator 
    
    EncodingOpt     ::=  %empty  |  encoding StringLiteral  --("encoding" StringLiteral)?
    
    MainModule    ::=    Prolog QueryBody   
    
    LibraryModule    ::=   ModuleDecl Prolog
    
    ModuleDecl    ::=    module namespace NCName EQUAL URILiteral Separator 
    
    --x Prolog    ::=    ((DefaultNamespaceDecl | Setter | NamespaceDecl | Import) Separator)* ((VarDecl | FunctionDecl | OptionDecl) Separator)* 
    PrologEntry$PrologEntry    ::=  DefaultNamespaceDecl Separator 
                                  | Setter Separator
                                  | NamespaceDecl Separator
                                  | Import Separator 
                                  | VarDecl Separator
                                  | FunctionDecl Separator
                                  | OptionDecl Separator
    PrologEntryList$$PrologEntry ::= %empty  | PrologEntryList PrologEntry
    Prolog  ::= PrologEntryList
    
    Setter$Setter    ::=    BoundarySpaceDecl | DefaultCollationDecl | BaseURIDecl | ConstructionDecl | OrderingModeDecl | EmptyOrderDecl | CopyNamespacesDecl 
    
    Import$ImportBlk    ::=    SchemaImport | ModuleImport   --? XXX:$Import is a section header for lpg. some problems?need rename?
    
    --x Separator    ::=    ";" 
    
    NamespaceDecl    ::=    declare namespace NCName EQUAL URILiteral 
    
    --x BoundarySpaceDecl    ::=    "declare" "boundary-space" ("preserve" | "strip")  
    BoundarySpaceDecl$BoundarySpaceDecl    ::=    declare boundary_space preserve 
                                                | declare boundary_space strip
    
    --x DefaultNamespaceDecl    ::=    "declare" "default" ("element" | "function") "namespace" URILiteral 
    DefaultNamespaceDecl$DefaultNamespaceDecl    ::=    declare default element namespace URILiteral
                                                      | declare default function namespace URILiteral
    
    OptionDecl    ::=    declare option QName StringLiteral 
    
    --x OrderingModeDecl    ::=    "declare" "ordering" ("ordered" | "unordered") 
    OrderingModeDecl$OrderingModeDecl    ::=    declare ordering ordered 
                                              | declare ordering unordered 
                             
    --x EmptyOrderDecl    ::=    "declare" "default" "order" "empty" ("greatest" | "least") 
    EmptyOrderDecl$EmptyOrderDecl    ::=     declare   default   order  empty greatest 
                                          |  declare   default   order  empty least 
    
    CopyNamespacesDecl    ::=     declare  copy_namespaces PreserveMode COMMA InheritMode 
    
    PreserveMode$PreserveMode    ::=     preserve  | no_preserve 
    
    InheritMode$InheritMode    ::=    inherit  | no_inherit 
    
    DefaultCollationDecl    ::=     declare default collation  URILiteral 
    
    BaseURIDecl    ::=     declare  base_uri URILiteral 
    
    --x SchemaImport    ::=    "import" "schema" SchemaPrefix? URILiteral ("at" URILiteral ("," URILiteral)*)? 
    SchemaPrefixOpt          ::= %empty  |  SchemaPrefix 
    CommaSeparatedURILiteral$$URILiteral    ::=   URILiteral
                                    | CommaSeparatedURILiteral COMMA URILiteral
    AtURILiteralSpecifierOpt  ::=  %empty  |   at  CommaSeparatedURILiteral
    SchemaImport    ::=     import schema   SchemaPrefixOpt URILiteral AtURILiteralSpecifierOpt                              
    
    --x SchemaPrefix    ::=    ("namespace" NCName "=") | ("default" "element" "namespace") 
    SchemaPrefix$SchemaPrefixNamed      ::=     namespace  NCName  EQUAL 
    SchemaPrefix$SchemaPrefixDefault    ::=     default element   namespace  
    
    --x ModuleImport    ::=    "import" "module" ("namespace" NCName "=")? URILiteral ("at" URILiteral ("," URILiteral)*)? 
    ModuleImport                  ::=    import module URILiteral AtURILiteralSpecifierOpt
    ModuleImport$ModuleImportNS   ::=    import module namespace NCName EQUAL URILiteral AtURILiteralSpecifierOpt  
    
    --x VarDecl    ::=    "declare" "variable" "$" QName TypeDeclaration? ((":=" ExprSingle) | "external") 
    TypeDeclarationOpt   ::= %empty | TypeDeclaration
    VarDecl              ::=    declare variable DOLLAR QName TypeDeclarationOpt ASSIGN ExprSingle 
    VarDecl$VarDeclExternal    ::=     declare variable  DOLLAR QName TypeDeclarationOpt  external 
    
    --x ConstructionDecl    ::=    "declare" "construction" ("strip" | "preserve") 
    ConstructionDecl$ConstructionDecl  ::=     declare construction strip   |  declare construction preserve 
    
    --x FunctionDecl    ::=    "declare" "function" QName "(" ParamList? ")" ("as" SequenceType)? (EnclosedExpr | "external") 
    ParamListOpt      ::=   %empty   |   ParamList
    AsSequenceTypeOpt ::=   %empty   |   as  SequenceType
    FunctionDecl                         ::=     declare function QName LEFTPAREN ParamListOpt RIGHTPAREN AsSequenceTypeOpt EnclosedExpr 
    FunctionDecl$FunctionDeclExternal    ::=     declare function QName LEFTPAREN ParamListOpt RIGHTPAREN AsSequenceTypeOpt  external  
    
    ParamList$$Param    ::=   Param
                            | ParamList COMMA  Param
    
    --x Param    ::=    "$" QName TypeDeclaration? 
    Param    ::=    DOLLAR QName TypeDeclarationOpt 
    
    EnclosedExpr    ::=    LEFTBRACE Expr RIGHTBRACE 
    
    QueryBody    ::=    Expr 
    
    --x Expr    ::=    ExprSingle ("," ExprSingle)* 
    Expr$$ExprSingle    ::=    ExprSingle
                           | Expr COMMA ExprSingle
    
    ExprSingle    ::=   FLWORExpr
    ExprSingle    ::=   QuantifiedExpr
    ExprSingle    ::=   TypeswitchExpr
    ExprSingle    ::=   IfExpr
    ExprSingle    ::=   OrExpr
    
    
    --x FLWORExpr    ::=    (ForClause | LetClause)+ WhereClause? OrderByClause? "return" ExprSingle 
    WhereClauseOpt      ::=  %empty  | WhereClause
    OrderByClauseOpt    ::=  %empty  | OrderByClause
    ForOrLetClause      ::=  ForClause | LetClause
    ForOrLetClauseList$$ForOrLetClause  ::=  ForOrLetClause | ForOrLetClauseList ForOrLetClause
    FLWORExpr    ::=    ForOrLetClauseList WhereClauseOpt OrderByClauseOpt  return  ExprSingle
    
    --x ForClause    ::=    "for" "$" VarName TypeDeclaration? PositionalVar? "in" ExprSingle ("," "$" VarName TypeDeclaration? PositionalVar? "in" ExprSingle)* 
    PositionalVarOpt  ::= %empty  |  PositionalVar
    ForClauseVarBinding     ::=  DOLLAR VarName TypeDeclarationOpt PositionalVarOpt  in  ExprSingle
    ForClauseVarBindingList$$ForClauseVarBinding   ::=  ForClauseVarBinding  | ForClauseVarBindingList COMMA  ForClauseVarBinding
    ForClause    ::=     for  ForClauseVarBindingList
    
    PositionalVar    ::=     at  DOLLAR VarName 
    
    --x LetClause    ::=    "let" "$" VarName TypeDeclaration? ":=" ExprSingle ("," "$" VarName TypeDeclaration? ":=" ExprSingle)* 
    LetClauseVarBinding   ::=  DOLLAR VarName TypeDeclarationOpt ASSIGN ExprSingle
    LetClauseVarBindingList$$LetClauseVarBinding   ::=  LetClauseVarBinding  | LetClauseVarBindingList COMMA LetClauseVarBinding
    LetClause    ::=     let  LetClauseVarBindingList
    
    WhereClause    ::=     where  ExprSingle 
    
    OrderByClause$OrderByClause    ::=      order   by  OrderSpecList 
                                        |   stable   order  by  OrderSpecList 
    
    --x OrderSpecList    ::=    OrderSpec ("," OrderSpec)* 
    OrderSpecList$$OrderSpec    ::=    OrderSpec | OrderSpecList COMMA OrderSpec
    
    OrderSpec    ::=    ExprSingle OrderModifier 
    
    --x OrderModifier    ::=    ("ascending" | "descending")? ("empty" ("greatest" | "least"))? ("collation" URILiteral)? 
    SortDirectionSegment$SortDirectionSegment  ::=  ascending  |  descending 
    SortDirectionSegmentOpt  ::=    %empty  |   SortDirectionSegment
    EmptySortSegment$EmptySortSegment  ::=   empty  greatest  |  empty  least 
    EmptySortSegmentOpt   ::=    %empty  |   EmptySortSegment
    CollationSegmentOpt   ::=    %empty  |   collation  URILiteral
    OrderModifier         ::=    SortDirectionSegmentOpt EmptySortSegmentOpt CollationSegmentOpt  
    
    --x QuantifiedExpr    ::=    ("some" | "every") "$" VarName TypeDeclaration? "in" ExprSingle ("," "$" VarName TypeDeclaration? "in" ExprSingle)* "satisfies" ExprSingle 
    QuantifiedExprVarBinding   ::= DOLLAR VarName TypeDeclarationOpt  in  ExprSingle
    QuantifiedExprVarBindingList$$QuantifiedExprVarBinding ::=  QuantifiedExprVarBinding
                                                              | QuantifiedExprVarBindingList COMMA QuantifiedExprVarBinding
    QuantifiedExpr$SomeQuantifiedExpr    ::=  some   QuantifiedExprVarBindingList   satisfies  ExprSingle                                                        
    QuantifiedExpr$EveryQuantifiedExpr   ::=  every  QuantifiedExprVarBindingList   satisfies  ExprSingle

    --x TypeswitchExpr    ::=    "typeswitch" "(" Expr ")" CaseClause+ "default" ("$" VarName)? "return" ExprSingle 
    CaseClauseList$$CaseClause   ::=   CaseClause  | CaseClauseList CaseClause
    TypeswitchExpr$TypeswitchExpr    ::=    typeswitch  LEFTPAREN Expr RIGHTPAREN CaseClauseList  default                return  ExprSingle 
                                          | typeswitch  LEFTPAREN Expr RIGHTPAREN CaseClauseList  default DOLLAR VarName return  ExprSingle
    --x CaseClause    ::=    "case" ("$" VarName "as")? SequenceType "return" ExprSingle 
    CaseClauseVarNameDeclOpt ::=   %empty  | DOLLAR VarName  as 
    CaseClause               ::=     case CaseClauseVarNameDeclOpt SequenceType  return  ExprSingle
    
    IfExpr    ::=     if  LEFTPAREN Expr RIGHTPAREN   then  ExprSingle  else  ExprSingle 
    
    --x OrExpr    ::=    AndExpr ( "or" AndExpr )* 
    OrExpr     ::=    AndExpr | OrExpr  or  AndExpr
    
    AndExpr    ::=    ComparisonExpr | AndExpr  and  ComparisonExpr  
    
    --x ComparisonExpr    ::=    RangeExpr ( (ValueComp | GeneralComp | NodeComp) RangeExpr )? 
    ComparisonExpr                   ::=    RangeExpr 
    ComparisonExpr$ComparisonExpr    ::=    RangeExpr EQUAL         RangeExpr
                                          | RangeExpr NOTEQUAL      RangeExpr
                                          | RangeExpr LESS          RangeExpr
                                          | RangeExpr LESSEQUAL     RangeExpr
                                          | RangeExpr GREATER       RangeExpr
                                          | RangeExpr GREATEREQUAL  RangeExpr
                                          | RangeExpr eq            RangeExpr
                                          | RangeExpr ne            RangeExpr
                                          | RangeExpr lt            RangeExpr
                                          | RangeExpr le            RangeExpr
                                          | RangeExpr gt            RangeExpr
                                          | RangeExpr ge            RangeExpr
                                          | RangeExpr is            RangeExpr
                                          | RangeExpr NodeCompLeft  RangeExpr
                                          | RangeExpr NodeCompRight RangeExpr
    
    --x RangeExpr    ::=    AdditiveExpr ( "to" AdditiveExpr )?
    RangeExpr  ::=    AdditiveExpr  | AdditiveExpr   to   AdditiveExpr
 
    --x AdditiveExpr    ::=    MultiplicativeExpr ( ("+" | "-") MultiplicativeExpr )* 
    AdditiveExpr                 ::=     MultiplicativeExpr
    AdditiveExpr$AdditiveExpr    ::=     AdditiveExpr  PLUS  MultiplicativeExpr
                                      |  AdditiveExpr  MINUS  MultiplicativeExpr  
    
    --x MultiplicativeExpr    ::=    UnionExpr ( ("*" | "div" | "idiv" | "mod") UnionExpr )* 
    MultiplicativeExpr                       ::=     UnionExpr
    MultiplicativeExpr$MultiplicativeExpr    ::=     MultiplicativeExpr  TIMES UnionExpr
                                                  |  MultiplicativeExpr  div   UnionExpr
                                                  |  MultiplicativeExpr  idiv  UnionExpr
                                                  |  MultiplicativeExpr  mod   UnionExpr
    
    --x UnionExpr    ::=    IntersectExceptExpr ( ("union" | "|") IntersectExceptExpr )* 
    UnionExpr              ::=    IntersectExceptExpr 
    UnionExpr$UnionExpr    ::=    UnionExpr  union  IntersectExceptExpr
                                | UnionExpr  ORBar  IntersectExceptExpr    
    
    --x IntersectExceptExpr    ::=    InstanceofExpr ( ("intersect" | "except") InstanceofExpr )* 
    IntersectExceptExpr                      ::=    InstanceofExpr
    IntersectExceptExpr$IntersectExceptExpr  ::=    IntersectExceptExpr  intersect  InstanceofExpr
                                                  | IntersectExceptExpr  except     InstanceofExpr
    --x InstanceofExpr    ::=    TreatExpr ( "instance" "of" SequenceType )? 
    InstanceofExpr      ::=  TreatExpr    |  TreatExpr  instance   of  SequenceType
    
    --x TreatExpr    ::=    CastableExpr ( "treat" "as" SequenceType )? 
    TreatExpr           ::=  CastableExpr |  CastableExpr  treat   as  SequenceType
    
    --x CastableExpr    ::=    CastExpr ( "castable" "as" SingleType )? 
    CastableExpr        ::=  CastExpr     |  CastExpr  castable  as  SequenceType
    
    --x CastExpr    ::=    UnaryExpr ( "cast" "as" SingleType )? 
    CastExpr            ::=  UnaryExpr    |  UnaryExpr  cast   as  SingleType
    
    --x UnaryExpr    ::=    ("-" | "+")* ValueExpr 
    UnaryExpr    ::=   ValueExpr
    UnaryExpr    ::=   PlusOrMinusList ValueExpr
    PlusOrMinus$PlusOrMinus  ::= PLUS | MINUS
    PlusOrMinusList$$PlusOrMinus ::= PlusOrMinus | PlusOrMinusList PlusOrMinus
    
    ValueExpr    ::=    PathExpr | ValidateExpr  | ExtensionExpr 
    
    --x ValidateExpr    ::=    "validate" ValidationMode? "{" Expr "}" 
    ValidateExpr$ValidateExpr    ::=     validate                 LEFTBRACE Expr RIGHTBRACE
                                      |  validate  ValidationMode LEFTBRACE Expr RIGHTBRACE
    
    ValidationMode    ::=     lax  |  strict  
    
    --x ExtensionExpr    ::=    Pragma+ "{" Expr? "}" 
    PragmaList$$Pragma  ::= Pragma | PragmaList Pragma
    ExtensionExpr       ::=    PragmaList LEFTBRACE RIGHTBRACE  | PragmaList LEFTBRACE Expr RIGHTBRACE 
    
    --x Pragma    ::=    "(#" S? QName (S PragmaContents)? "#)" -- ws: explicit --    --XXX:S space 
    Pragma$Pragma    ::=    PragmaST  QName                   PragmaET 
                          | PragmaST  QName   PragmaContents  PragmaET   --XXX:?what does PragmaContents mean?
                          -- | "(#" S  QName  S PragmaContents  "#)"     --XXX:?what does PragmaContents mean?
    
    --x PragmaContents    ::=    Char* - (Char* '#)' Char*)       --XXX:?what does PragmaContents mean?
    
    
    --x PathExpr    ::=    ("/" RelativePathExpr?) | ("//" RelativePathExpr) | RelativePathExpr -- xgs: leading-lone-slash -- 
    PathExpr$PathExpr    ::=    SLASH
                              | SLASH RelativePathExpr
                              | DoubleSlash RelativePathExpr
                              | RelativePathExpr
    
    --x RelativePathExpr    ::=    StepExpr (("/" | "//") StepExpr)* 
    RelativePathExpr    ::=    StepExpr
    RelativePathExpr$RelativePathExpr    ::=    RelativePathExpr SLASH       StepExpr 
                                              | RelativePathExpr DoubleSlash StepExpr
    
    StepExpr   ::=   FilterExpr
    StepExpr   ::=   AxisStep 
    
    --x AxisStep    ::=    (ReverseStep | ForwardStep) PredicateList 
    AxisStep$AxisStepReverse    ::=     ReverseStep PredicateList
    AxisStep$AxisStepForward    ::=     ForwardStep PredicateList
    
    ForwardStep    ::=    ForwardAxis NodeTest | AbbrevForwardStep 
    
    ForwardAxis$ForwardAxis    ::=      child  DoubleCOLON
                                     |  descendant  DoubleCOLON
                                     |  attribute  DoubleCOLON
                                     |  self  DoubleCOLON
                                     | descendant_or_self DoubleCOLON
                                     | following_sibling DoubleCOLON
                                     |  following  DoubleCOLON 
    
    --x AbbrevForwardStep    ::=    "@"? NodeTest 
    AbbrevForwardStep    ::=    NodeTest | ATSIGN NodeTest
    
    ReverseStep    ::=    ReverseAxis NodeTest
    ReverseStep    ::=    AbbrevReverseStep 
    
    ReverseAxis$ReverseAxis    ::=      parent  DoubleCOLON
                                   |  ancestor  DoubleCOLON
                           |  preceding_sibling DoubleCOLON
                                  |  preceding  DoubleCOLON
                            |  ancestor_or_self DoubleCOLON 
    
    AbbrevReverseStep    ::=    DOTDOT
    
    NodeTest    ::=    KindTest | NameTest 
    
    NameTest    ::=    QName | Wildcard 
    
    Wildcard    ::=    TIMES
                         | NCName COLON TIMES
                         | TIMES  COLON NCName        -- ws: explicit -- 
    
    FilterExpr    ::=    PrimaryExpr PredicateList 
   
    --x PredicateList    ::=    Predicate* 
    PredicateList$$Predicate    ::=   %empty | PredicateList Predicate
    
    Predicate    ::=    LEFTBRACKET Expr RIGHTBRACKET
    
    PrimaryExpr    ::=    Literal
    PrimaryExpr    ::=    VarRef 
    PrimaryExpr    ::=    ParenthesizedExpr
    PrimaryExpr    ::=    ContextItemExpr
    PrimaryExpr    ::=    FunctionCall
    PrimaryExpr    ::=    OrderedExpr
    PrimaryExpr    ::=    UnorderedExpr
    PrimaryExpr    ::=    Constructor 
    
    Literal    ::=    NumericLiteral
    Literal    ::=    StringLiteral 
    
    NumericLiteral    ::=    IntegerLiteral
    NumericLiteral    ::=    DecimalLiteral
    NumericLiteral    ::=    DoubleLiteral 
    
    VarRef    ::=    DOLLAR VarName 
    
    VarName    ::=    QName 
    
    ParenthesizedExpr$ParenthesizedExpr    ::=    LEFTPAREN RIGHTPAREN  | LEFTPAREN Expr RIGHTPAREN
    
    ContextItemExpr    ::=    DOT 
    
    OrderedExpr    ::=     ordered    LEFTBRACE Expr RIGHTBRACE 
    
    UnorderedExpr    ::=   unordered  LEFTBRACE Expr RIGHTBRACE 
    
    FunctionCall    ::=     QName LEFTPAREN      RIGHTPAREN -- xgs: reserved-function-names, gn: parens -- 
                          | QName LEFTPAREN Expr RIGHTPAREN 
    
    Constructor    ::=    DirectConstructor
    Constructor    ::=    ComputedConstructor 
    
    DirectConstructor    ::=    DirElemConstructor
    DirectConstructor    ::=    DirCommentConstructor
    DirectConstructor    ::=    DirPIConstructor 
                         
    --x DirElemConstructor    ::=    "<" QName DirAttributeList ("/>" | (">" DirElemContent* "</" QName S? ">")) -- ws: explicit -- --XXX:S space 
    DirElemConstructor    ::=      LESS QName DirAttributeList STEndMark
                                 | LESS QName DirAttributeList GREATER DirElemContentList ETStartMark QName GREATER       
    DirElemContentList$$DirElemContent ::=  %empty  | DirElemContentList DirElemContent
    
    -- DirAttributeList    ::=    (S (QName S? "=" S? DirAttributeValue)?)* -- ws: explicit --   --XXX:S space 
    DirAttribute    ::=    QName   EQUAL   DirAttributeValue  
                                         
    DirAttributeList$$DirAttribute    ::=  %empty | DirAttributeList DirAttribute 
        
    --x DirAttributeValue  ::=    ('"' (EscapeQuot | QuotAttrValueContent)* '"') | ("'" (EscapeApos | AposAttrValueContent)* "'") -- ws: explicit -- 
    --x QuotAttrVC  ::= EscapeQuot
    --x QuotAttrVC  ::= QuotAttrValueContent
    --x QuotAttrVCList$$QuotAttrVC  ::=  %empty  |  QuotAttrVCList QuotAttrVC
    --x AposAttrVC  ::= EscapeApos
    --x AposAttrVC  ::= AposAttrValueContent
    --x AposAttrVCList$$AposAttrVC  ::=  %empty  |  AposAttrVCList AposAttrVC
    
    DirAttributeValueQuot$DirAttributeValueQuot  ::=  EscapeQuot
                              | QuotAttrContentChar
                              | CommonContent
    DirAttributeValueApos$DirAttributeValueApos  ::=  EscapeApos
                              | AposAttrContentChar
                              | CommonContent                            
    DirAttributeValueQuotList$$DirAttributeValueQuot ::= %empty | DirAttributeValueQuotList DirAttributeValueQuot
    DirAttributeValueAposList$$DirAttributeValueApos ::= %empty | DirAttributeValueAposList DirAttributeValueApos
    
    DirAttributeValue$DirAttributeValueQuot  ::=    DOUBLEQUOTE DirAttributeValueQuotList DOUBLEQUOTE 
    DirAttributeValue$DirAttributeValueApos  ::=    SINGLEQUOTE DirAttributeValueAposList SINGLEQUOTE 
    
    --x QuotAttrValueContent    ::=    QuotAttrContentChar    --XXX:QuotAttrContentChar=?
    --x QuotAttrValueContent    ::=    CommonContent
    --x AposAttrValueContent    ::=    AposAttrContentChar
    --x AposAttrValueContent    ::=    CommonContent 
    
    DirElemContent    ::=    DirectConstructor    
    DirElemContent    ::=    ElementContentChar
    DirElemContent    ::=    CDataSection
    DirElemContent    ::=    CommonContent
        
    --x CommonContent    ::=    PredefinedEntityRef | CharRef | "{{" | "}}" | EnclosedExpr 
    CommonContent$CommonContent     ::=    PredefinedEntityRef | CharRef | DoubleLEFTBRACE | DoubleRIGHTBRACE
    CommonContent     ::=    EnclosedExpr
    
    --x DirCommentConstructor    ::=    DirCOMMENTST DirCommentContents DirCOMMENTET    -- ws: explicit -- 
    
    --x DirCommentContents    ::=    ((Char - '-') | ('-' (Char - '-')))*  -- ws: explicit -- --XXX:?
    --x DirCommentContents    ::=  Chars
    
     DirPIConstructor$DirPIConstructor      ::=    PIST PITarget PIET                    -- ws: explicit -- --XXX:S space 
                                                |  PIST PITarget DirPIContents PIET
    --x DirPIContents    ::=    (Char* - (Char* '?>' Char*))            -- ws: explicit --    --XXX:?
    
    --x CDataSection    ::=    CDATAST CDataSectionContents CDATAET      -- ws: explicit -- 
    --x CDataSectionContents    ::=    (Char* - (Char* ']]>' Char*)) -- ws: explicit -- 
    --x CDataSectionContents    ::=   Chars
    
    ComputedConstructor    ::=    CompDocConstructor
    ComputedConstructor    ::=    CompElemConstructor
    ComputedConstructor    ::=    CompAttrConstructor
    ComputedConstructor    ::=    CompTextConstructor
    ComputedConstructor    ::=    CompCommentConstructor
    ComputedConstructor    ::=    CompPIConstructor 
    
    CompDocConstructor    ::=     document   LEFTBRACE Expr RIGHTBRACE 
    
    --x CompElemConstructor    ::=    "element" (QName | ("{" Expr "}")) "{" ContentExpr? "}" 
    CompElemConstructor$CompElemConstructor    ::=     element   QName    LEFTBRACE RIGHTBRACE 
                                                    |  element   QName    LEFTBRACE ContentExpr RIGHTBRACE 
                                |  element   LEFTBRACE Expr RIGHTBRACE LEFTBRACE             RIGHTBRACE 
                                |  element   LEFTBRACE Expr RIGHTBRACE LEFTBRACE ContentExpr RIGHTBRACE 
    
    ContentExpr    ::=    Expr 
    
    --x CompAttrConstructor    ::=    "attribute" (QName | ("{" Expr "}")) "{" Expr? "}" 
    CompAttrConstructor$CompAttrConstructor    ::=     attribute   QName   LEFTBRACE      RIGHTBRACE
                                                    |  attribute   QName   LEFTBRACE Expr RIGHTBRACE
                                |  attribute  LEFTBRACE Expr RIGHTBRACE  LEFTBRACE RIGHTBRACE
                                |  attribute  LEFTBRACE Expr RIGHTBRACE  LEFTBRACE Expr RIGHTBRACE
    
    
    CompTextConstructor    ::=     text  LEFTBRACE Expr RIGHTBRACE 
    
    CompCommentConstructor    ::=     comment  LEFTBRACE Expr RIGHTBRACE 
    
    --x CompPIConstructor    ::=    "processing-instruction" (NCName | ("{" Expr "}")) "{" Expr? "}" 
    CompPIConstructor$CompPIConstructor    ::=  processing_instruction  NCName   LEFTBRACE RIGHTBRACE
                                              | processing_instruction  NCName   LEFTBRACE Expr RIGHTBRACE
                              | processing_instruction  LEFTBRACE Expr RIGHTBRACE  LEFTBRACE RIGHTBRACE
                              | processing_instruction  LEFTBRACE Expr RIGHTBRACE  LEFTBRACE Expr RIGHTBRACE 
    
    SingleType    ::=   AtomicType | AtomicType QUESTION 
    
    TypeDeclaration    ::=     as  SequenceType 
    
    SequenceType$SequenceTypeEmptySeq    ::=    empty_sequence LEFTPAREN RIGHTPAREN
    SequenceType$SequenceTypeItemType    ::=    ItemType | ItemType OccurrenceIndicator
    
    OccurrenceIndicator$OccurrenceIndicator    ::=    QUESTION | TIMES | PLUS     -- xgs: occurrence-indicators --
    
    ItemType    ::=    KindTest
    ItemType    ::=    AtomicType
    ItemType    ::=    item  LEFTPAREN RIGHTPAREN
    
    KindTest    ::=    DocumentTest
    KindTest    ::=    ElementTest
    KindTest    ::=    AttributeTest
    KindTest    ::=    SchemaElementTest
    KindTest    ::=    SchemaAttributeTest
    KindTest    ::=    PITest
    KindTest    ::=    CommentTest
    KindTest    ::=    TextTest
    KindTest    ::=    AnyKindTest 
    
    AnyKindTest    ::=     node  LEFTPAREN RIGHTPAREN 
    
    --x DocumentTest    ::=    "document-node" "(" (ElementTest | SchemaElementTest)? ")" 
    DocumentTest$DocumentTest    ::=     document_node LEFTPAREN RIGHTPAREN
                          | document_node LEFTPAREN  ElementTest         RIGHTPAREN
                          | document_node LEFTPAREN  SchemaElementTest   RIGHTPAREN
    
    
    TextTest    ::=     text  LEFTPAREN RIGHTPAREN 
    
    CommentTest    ::=     comment  LEFTPAREN RIGHTPAREN 
    
    PITest$PITest    ::=     processing_instruction  LEFTPAREN RIGHTPAREN
                  |  processing_instruction LEFTPAREN  NCName           RIGHTPAREN 
                  |  processing_instruction LEFTPAREN  StringLiteral    RIGHTPAREN 
   
    AttributeTest$AttributeTest    ::=     attribute  LEFTPAREN RIGHTPAREN 
                             |    attribute  LEFTPAREN  AttributeName                   RIGHTPAREN
                             |    attribute  LEFTPAREN  TIMES                           RIGHTPAREN
                             |    attribute  LEFTPAREN  AttributeName  COMMA TypeName   RIGHTPAREN
                             |    attribute  LEFTPAREN  TIMES          COMMA TypeName   RIGHTPAREN 
    
    --x AttribNameOrWildcard ::=  
    
    SchemaAttributeTest    ::=    schema_attribute LEFTPAREN AttributeDeclaration RIGHTPAREN 
    
    --x AttributeDeclaration    ::=    AttributeName 
    
    ElementTest$ElementTest    ::=     element  LEFTPAREN   RIGHTPAREN 
                                   |   element  LEFTPAREN  ElementName                      RIGHTPAREN
                                   |   element  LEFTPAREN  TIMES                              RIGHTPAREN  
                                   |   element  LEFTPAREN  ElementName  COMMA TypeName        RIGHTPAREN
                                   |   element  LEFTPAREN  TIMES         COMMA TypeName        RIGHTPAREN  
                                   |   element  LEFTPAREN  ElementName  COMMA TypeName QUESTION    RIGHTPAREN 
                                   |   element  LEFTPAREN  TIMES          COMMA TypeName QUESTION    RIGHTPAREN 
    
    --ElementNameOrWildcard$    ::=    ElementName | "*" 
    
    SchemaElementTest    ::=    schema_element LEFTPAREN ElementDeclaration RIGHTPAREN 
    
     

    --
    
    QName  ::=  NCName
    QName  ::=  NCName COLON NCName 
    

    
    

    --BadAssignment ::= identifier '=' MissingExpression 
%End



