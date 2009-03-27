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
-- The XQuery KeyWord Lexer
--

%options package=com.google.code.exquery.parser
%options template=./lpg/KeywordTemplateF.gi

%Include
    ./lpg/KWLexerLowerCaseMapXQ.gi
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

    -- List all the keywords the kwlexer will export to the lexer and parser
    
    
    -- keywords below, not reserved.
    xquery
    version
    encoding
    module 
    namespace
    declare 
    boundary_space 
    strip
    default
    function
    option
    ordering
    ordered
    order
    empty
    greatest
    least
    copy_namespaces
    preserve
    no_preserve
    inherit
    no_inherit
    collation
    base_uri
    at
    import
    schema
    variable
    construction
    as
    external
    return
    in
    for
    let
    where 
    by
    stable
    ascending
    descending
    some
    every
    satisfies
    case
    then
    else
    or
    and
    to
    union
    intersect
    except
    instance
    of
    treat
    castable
    cast
    validate
    lax
    strict
    child
    descendant
    self
    descendant_or_self
    following_sibling
    following
    parent
    ancestor
    preceding_sibling
    preceding
    ancestor_or_self
    unordered
    document
       
    --?
    div
    idiv
    mod
    eq
    ne
    lt
    le
    gt
    ge
    is
    
    
    
    -- keywords below reserved. 
    -- the following names are not allowed as function names in an unprefixed form
    -- (and only not allowed in this case )
    -- see more, REC-xquery-20070123, A.3
    attribute
    comment
    document_node
    element
    empty_sequence
    if
    item
    node
    processing_instruction
    schema_attribute
    schema_element
    text
    typeswitch
    
    
%End
 

%Start
    Keyword
%End

%Rules

    -- The Goal for the parser is a single Keyword

    Keyword ::= x q u e r y
        /.$BeginAction
            $setResult($_xquery);
          $EndAction
        ./
        
    Keyword ::= v e r s i o n
        /.$BeginAction
            $setResult($_version);
          $EndAction
        ./
   
   Keyword ::= e n c o d i n g
        /.$BeginAction
            $setResult($_encoding);
          $EndAction
        ./
        
   Keyword ::= m o d u l e
        /.$BeginAction
            $setResult($_module);
          $EndAction
        ./
        
   Keyword ::= n a m e s p a c e
        /.$BeginAction
            $setResult($_namespace);
          $EndAction
        ./
        
  Keyword ::= d e c l a r e
        /.$BeginAction
            $setResult($_declare);
          $EndAction
        ./
        
  Keyword ::= b o u n d a r y '-' s p a c e
        /.$BeginAction
            $setResult($_boundary_space);
          $EndAction
        ./
        
  Keyword ::= p r e s e r v e
        /.$BeginAction
            $setResult($_preserve);
          $EndAction
        ./
        
  Keyword ::= s t r i p
        /.$BeginAction
            $setResult($_strip);
          $EndAction
        ./
        
  Keyword ::= d e f a u l t
        /.$BeginAction
            $setResult($_default);
          $EndAction
        ./
        
  Keyword ::= f u n c t i o n
        /.$BeginAction
            $setResult($_function);
          $EndAction
        ./
        
        Keyword ::= o p t i o n
        /.$BeginAction
            $setResult($_option);
          $EndAction
        ./
        
        Keyword ::= o r d e r i n g
        /.$BeginAction
            $setResult($_ordering);
          $EndAction
        ./
        
        Keyword ::= o r d e r e d
        /.$BeginAction
            $setResult($_ordered);
          $EndAction
        ./
        
        Keyword ::= o r d e r
        /.$BeginAction
            $setResult($_order);
          $EndAction
        ./
        
        Keyword ::= e m p t y
        /.$BeginAction
            $setResult($_empty);
          $EndAction
        ./
        
        Keyword ::= g r e a t e s t
        /.$BeginAction
            $setResult($_greatest);
          $EndAction
        ./
        
        Keyword ::= l e a s t
        /.$BeginAction
            $setResult($_least);
          $EndAction
        ./
        
        Keyword ::= c o p y '-' n a m e s p a c e s
        /.$BeginAction
            $setResult($_copy_namespaces);
          $EndAction
        ./
         
        
        Keyword ::= n o '-' p r e s e r v e
        /.$BeginAction
            $setResult($_no_preserve);
          $EndAction
        ./
        
        Keyword ::= i n h e r i t
        /.$BeginAction
            $setResult($_inherit);
          $EndAction
        ./
        
        Keyword ::= n o '-' i n h e r i t
        /.$BeginAction
            $setResult($_no_inherit);
          $EndAction
        ./
        
        Keyword ::= c o l l a t i o n
        /.$BeginAction
            $setResult($_collation);
          $EndAction
        ./
        
        Keyword ::= b a s e '-' u r i
        /.$BeginAction
            $setResult($_base_uri);
          $EndAction
        ./
        
        Keyword ::= a t
        /.$BeginAction
            $setResult($_at);
          $EndAction
        ./
        
        Keyword ::= i m p o r t
        /.$BeginAction
            $setResult($_import);
          $EndAction
        ./
        
        Keyword ::= s c h e m a
        /.$BeginAction
            $setResult($_schema);
          $EndAction
        ./
        
        Keyword ::= v a r i a b l e
        /.$BeginAction
            $setResult($_variable);
          $EndAction
        ./
        
        Keyword ::= c o n s t r u c t i o n
        /.$BeginAction
            $setResult($_construction);
          $EndAction
        ./
        
        Keyword ::= a s
        /.$BeginAction
            $setResult($_as);
          $EndAction
        ./
        
        Keyword ::= e x t e r n a l
        /.$BeginAction
            $setResult($_external);
          $EndAction
        ./
        
        Keyword ::= r e t u r n
        /.$BeginAction
            $setResult($_return);
          $EndAction
        ./
        
        Keyword ::= i n
        /.$BeginAction
            $setResult($_in);
          $EndAction
        ./
        
        Keyword ::= f o r
        /.$BeginAction
            $setResult($_for);
          $EndAction
        ./
        
        Keyword ::= l e t
        /.$BeginAction
            $setResult($_let);
          $EndAction
        ./
        
        Keyword ::= w h e r e
        /.$BeginAction
            $setResult($_where);
          $EndAction
        ./
        
        Keyword ::= b y
        /.$BeginAction
            $setResult($_by);
          $EndAction
        ./
        
        Keyword ::= s t a b l e
        /.$BeginAction
            $setResult($_stable);
          $EndAction
        ./
        
        Keyword ::= a s c e n d i n g
        /.$BeginAction
            $setResult($_ascending);
          $EndAction
        ./
        
        Keyword ::= d e s c e n d i n g
        /.$BeginAction
            $setResult($_descending);
          $EndAction
        ./
        
        Keyword ::= s o m e
        /.$BeginAction
            $setResult($_some);
          $EndAction
        ./
        
        Keyword ::= e v e r y
        /.$BeginAction
            $setResult($_every);
          $EndAction
        ./
        
        Keyword ::= s a t i s f i e s
        /.$BeginAction
            $setResult($_satisfies);
          $EndAction
        ./
        
        Keyword ::= c a s e
        /.$BeginAction
            $setResult($_case);
          $EndAction
        ./
        
        Keyword ::= t h e n
        /.$BeginAction
            $setResult($_then);
          $EndAction
        ./
        
        Keyword ::= e l s e
        /.$BeginAction
            $setResult($_else);
          $EndAction
        ./
        
        Keyword ::= o r
        /.$BeginAction
            $setResult($_or);
          $EndAction
        ./
        
        Keyword ::= a n d
        /.$BeginAction
            $setResult($_and);
          $EndAction
        ./
        
        Keyword ::= t o
        /.$BeginAction
            $setResult($_to);
          $EndAction
        ./
        
        Keyword ::= u n i o n
        /.$BeginAction
            $setResult($_union);
          $EndAction
        ./
        
        Keyword ::= i n t e r s e c t
        /.$BeginAction
            $setResult($_intersect);
          $EndAction
        ./
        
        Keyword ::= e x c e p t
        /.$BeginAction
            $setResult($_except);
          $EndAction
        ./
        
        Keyword ::= i n s t a n c e
        /.$BeginAction
            $setResult($_instance);
          $EndAction
        ./
        
        Keyword ::= o f
        /.$BeginAction
            $setResult($_of);
          $EndAction
        ./
        
        Keyword ::= t r e a t
        /.$BeginAction
            $setResult($_treat);
          $EndAction
        ./
        
        Keyword ::= c a s t a b l e
        /.$BeginAction
            $setResult($_castable);
          $EndAction
        ./
        
        Keyword ::= c a s t
        /.$BeginAction
            $setResult($_cast);
          $EndAction
        ./
        
        Keyword ::= v a l i d a t e
        /.$BeginAction
            $setResult($_validate);
          $EndAction
        ./
        
        Keyword ::= l a x
        /.$BeginAction
            $setResult($_lax);
          $EndAction
        ./
        
        Keyword ::= s t r i c t
        /.$BeginAction
            $setResult($_strict);
          $EndAction
        ./
        
        Keyword ::= c h i l d
        /.$BeginAction
            $setResult($_child);
          $EndAction
        ./
        
        Keyword ::= d e s c e n d a n t
        /.$BeginAction
            $setResult($_descendant);
          $EndAction
        ./
        
        Keyword ::= s e l f
        /.$BeginAction
            $setResult($_self);
          $EndAction
        ./
        
        Keyword ::= d e s c e n d a n t '-' o r '-' s e l f
        /.$BeginAction
            $setResult($_descendant_or_self);
          $EndAction
        ./
        
        Keyword ::= f o l l o w i n g '-' s i b l i n g
        /.$BeginAction
            $setResult($_following_sibling);
          $EndAction
        ./
        
        Keyword ::= f o l l o w i n g
        /.$BeginAction
            $setResult($_following);
          $EndAction
        ./
        
        Keyword ::= p a r e n t
        /.$BeginAction
            $setResult($_parent);
          $EndAction
        ./
        
        Keyword ::= a n c e s t o r
        /.$BeginAction
            $setResult($_ancestor);
          $EndAction
        ./
        
        Keyword ::= p r e c e d i n g '-' s i b l i n g
        /.$BeginAction
            $setResult($_preceding_sibling);
          $EndAction
        ./
        
        Keyword ::= p r e c e d i n g
        /.$BeginAction
            $setResult($_preceding);
          $EndAction
        ./
        
        Keyword ::= a n c e s t o r '-' o r '-' s e l f
        /.$BeginAction
            $setResult($_ancestor_or_self);
          $EndAction
        ./
        
        Keyword ::= u n o r d e r e d
        /.$BeginAction
            $setResult($_unordered);
          $EndAction
        ./
        
        Keyword ::= d o c u m e n t
        /.$BeginAction
            $setResult($_document);
          $EndAction
        ./
        
        --?
        
        Keyword ::= d i v
        /.$BeginAction
            $setResult($_div);
          $EndAction
        ./
        
        Keyword ::= i d i v
        /.$BeginAction
            $setResult($_idiv);
          $EndAction
        ./
        
        Keyword ::= m o d
        /.$BeginAction
            $setResult($_mod);
          $EndAction
        ./
        
        Keyword ::= e q
        /.$BeginAction
            $setResult($_eq);
          $EndAction
        ./
        
        Keyword ::= n e
        /.$BeginAction
            $setResult($_ne);
          $EndAction
        ./
        
        Keyword ::= l t
        /.$BeginAction
            $setResult($_lt);
          $EndAction
        ./
        
        Keyword ::= l e
        /.$BeginAction
            $setResult($_le);
          $EndAction
        ./
        
        Keyword ::= g t
        /.$BeginAction
            $setResult($_gt);
          $EndAction
        ./
        
        Keyword ::= g e
        /.$BeginAction
            $setResult($_ge);
          $EndAction
        ./
        
        Keyword ::= i s
        /.$BeginAction
            $setResult($_is);
          $EndAction
        ./
        
        
        --
        --
        
       Keyword ::= a t t r i b u t e
        /.$BeginAction
            $setResult($_attribute);
          $EndAction
        ./
        
        Keyword ::= c o m m e n t
        /.$BeginAction
            $setResult($_comment);
          $EndAction
        ./
        
        Keyword ::= d o c u m e n t '-' n o d e
        /.$BeginAction
            $setResult($_document_node);
          $EndAction
        ./
        
        Keyword ::= e l e m e n t
        /.$BeginAction
            $setResult($_element);
          $EndAction
        ./
        
        Keyword ::= e m p t y '-' s e q u e n c e
        /.$BeginAction
            $setResult($_empty_sequence);
          $EndAction
        ./
        
        Keyword ::= i f
        /.$BeginAction
            $setResult($_if);
          $EndAction
        ./
        
        Keyword ::= i t e m
        /.$BeginAction
            $setResult($_item);
          $EndAction
        ./
        
        Keyword ::= n o d e
        /.$BeginAction
            $setResult($_node);
          $EndAction
        ./
        
        Keyword ::=  p r o c e s s i n g '-' i n s t r u c t i o n
        /.$BeginAction
            $setResult($_processing_instruction);
          $EndAction
        ./  
        
         Keyword ::= s c h e m a '-' a t t r i b u t e
        /.$BeginAction
            $setResult($_schema_attribute);
          $EndAction
        ./
        
        Keyword ::= s c h e m a '-' e l e m e n t
        /.$BeginAction
            $setResult($_schema_element);
          $EndAction
        ./ 
        
        Keyword ::= t e x t
        /.$BeginAction
            $setResult($_text);
          $EndAction
        ./
        
        Keyword ::= t y p e s w i t c h
        /.$BeginAction
            $setResult($_typeswitch);
          $EndAction
        ./         
  

%End
