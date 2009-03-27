
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

import com.google.code.exquery.parser.ast.*;
import lpg.runtime.*;
import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

public class XQueryParser implements RuleAction, IParser
{
    private PrsStream prsStream = null;
    
    private boolean unimplementedSymbolsWarning = false;

    private static ParseTable prsTable = new XQueryParserprs();
    public ParseTable getParseTable() { return prsTable; }

    private BacktrackingParser btParser = null;
    public BacktrackingParser getParser() { return btParser; }

    private void setResult(Object object) { btParser.setSym1(object); }
    public Object getRhsSym(int i) { return btParser.getSym(i); }

    public int getRhsTokenIndex(int i) { return btParser.getToken(i); }
    public IToken getRhsIToken(int i) { return prsStream.getIToken(getRhsTokenIndex(i)); }
    
    public int getRhsFirstTokenIndex(int i) { return btParser.getFirstToken(i); }
    public IToken getRhsFirstIToken(int i) { return prsStream.getIToken(getRhsFirstTokenIndex(i)); }

    public int getRhsLastTokenIndex(int i) { return btParser.getLastToken(i); }
    public IToken getRhsLastIToken(int i) { return prsStream.getIToken(getRhsLastTokenIndex(i)); }

    public int getLeftSpan() { return btParser.getFirstToken(); }
    public IToken getLeftIToken()  { return prsStream.getIToken(getLeftSpan()); }

    public int getRightSpan() { return btParser.getLastToken(); }
    public IToken getRightIToken() { return prsStream.getIToken(getRightSpan()); }

    public int getRhsErrorTokenIndex(int i)
    {
        int index = btParser.getToken(i);
        IToken err = prsStream.getIToken(index);
        return (err instanceof ErrorToken ? index : 0);
    }
    public ErrorToken getRhsErrorIToken(int i)
    {
        int index = btParser.getToken(i);
        IToken err = prsStream.getIToken(index);
        return (ErrorToken) (err instanceof ErrorToken ? err : null);
    }

    public void reset(ILexStream lexStream)
    {
        prsStream = new PrsStream(lexStream);
        btParser.reset(prsStream);

        try
        {
            prsStream.remapTerminalSymbols(orderedTerminalSymbols(), prsTable.getEoftSymbol());
        }
        catch(NullExportedSymbolsException e) {
        }
        catch(NullTerminalSymbolsException e) {
        }
        catch(UnimplementedTerminalsException e)
        {
            if (unimplementedSymbolsWarning) {
                java.util.ArrayList unimplemented_symbols = e.getSymbols();
                System.out.println("The Lexer will not scan the following token(s):");
                for (int i = 0; i < unimplemented_symbols.size(); i++)
                {
                    Integer id = (Integer) unimplemented_symbols.get(i);
                    System.out.println("    " + XQueryParsersym.orderedTerminalSymbols[id.intValue()]);               
                }
                System.out.println();
            }
        }
        catch(UndefinedEofSymbolException e)
        {
            throw new Error(new UndefinedEofSymbolException
                                ("The Lexer does not implement the Eof symbol " +
                                 XQueryParsersym.orderedTerminalSymbols[prsTable.getEoftSymbol()]));
        } 
    }
    
    public XQueryParser()
    {
        try
        {
            btParser = new BacktrackingParser(prsStream, prsTable, (RuleAction) this);
        }
        catch (NotBacktrackParseTableException e)
        {
            throw new Error(new NotBacktrackParseTableException
                                ("Regenerate XQueryParserprs.java with -BACKTRACK option"));
        }
        catch (BadParseSymFileException e)
        {
            throw new Error(new BadParseSymFileException("Bad Parser Symbol File -- XQueryParsersym.java"));
        }
    }
    
    public XQueryParser(ILexStream lexStream)
    {
        this();
        reset(lexStream);
    }
    
    public int numTokenKinds() { return XQueryParsersym.numTokenKinds; }
    public String[] orderedTerminalSymbols() { return XQueryParsersym.orderedTerminalSymbols; }
    public String getTokenKindName(int kind) { return XQueryParsersym.orderedTerminalSymbols[kind]; }
    public int getEOFTokenKind() { return prsTable.getEoftSymbol(); }
    public IPrsStream getIPrsStream() { return prsStream; }

    /**
     * @deprecated replaced by {@link #getIPrsStream()}
     *
     */
    public PrsStream getPrsStream() { return prsStream; }

    /**
     * @deprecated replaced by {@link #getIPrsStream()}
     *
     */
    public PrsStream getParseStream() { return prsStream; }

    public Object parser()
    {
        return parser(null, 0);
    }
    
    public Object parser(Monitor monitor)
    {
        return parser(monitor, 0);
    }
    
    public Object parser(int error_repair_count)
    {
        return parser(null, error_repair_count);
    }

    public Object parser(Monitor monitor, int error_repair_count)
    {
        btParser.setMonitor(monitor);
        
        try
        {
            return (Object) btParser.fuzzyParse(error_repair_count);
        }
        catch (BadParseException e)
        {
            prsStream.reset(e.error_token); // point to error token

            DiagnoseParser diagnoseParser = new DiagnoseParser(prsStream, prsTable);
            diagnoseParser.diagnose(e.error_token);
        }

        return null;
    }

    //
    // Additional entry points, if any
    //
    

    public void ruleAction(int ruleNumber)
    {
        switch (ruleNumber)
        {

            //
            // Rule 1:  Module ::= $Empty
            //
            case 1: {
               //#line 104 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new Module(getLeftIToken(), getRightIToken(),
                               (VersionDeclOpt)null,
                               (LibraryModule)null,
                               (MainModule)null)
                );
                break;
            }
            //
            // Rule 2:  Module ::= VersionDeclOpt LibraryModule
            //
            case 2: {
               //#line 105 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new Module(getLeftIToken(), getRightIToken(),
                               (VersionDeclOpt)getRhsSym(1),
                               (LibraryModule)getRhsSym(2),
                               (MainModule)null)
                );
                break;
            }
            //
            // Rule 3:  Module ::= VersionDeclOpt MainModule
            //
            case 3: {
               //#line 106 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new Module(getLeftIToken(), getRightIToken(),
                               (VersionDeclOpt)getRhsSym(1),
                               (LibraryModule)null,
                               (MainModule)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 4:  VersionDeclOpt ::= $Empty
            //
            case 4: {
               //#line 108 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(null);
                break;
            }
            //
            // Rule 5:  VersionDeclOpt ::= xquery version StringLiteral EncodingOpt Separator
            //
            case 5: {
               //#line 108 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new VersionDeclOpt(getLeftIToken(), getRightIToken(),
                                       new ASTNodeToken(getRhsIToken(1)),
                                       new ASTNodeToken(getRhsIToken(2)),
                                       new ASTNodeToken(getRhsIToken(3)),
                                       (EncodingOpt)getRhsSym(4),
                                       new ASTNodeToken(getRhsIToken(5)))
                );
                break;
            }
            //
            // Rule 6:  EncodingOpt ::= $Empty
            //
            case 6: {
               //#line 110 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(null);
                break;
            }
            //
            // Rule 7:  EncodingOpt ::= encoding StringLiteral
            //
            case 7: {
               //#line 110 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new EncodingOpt(getLeftIToken(), getRightIToken(),
                                    new ASTNodeToken(getRhsIToken(1)),
                                    new ASTNodeToken(getRhsIToken(2)))
                );
                break;
            }
            //
            // Rule 8:  MainModule ::= Prolog QueryBody
            //
            case 8: {
               //#line 112 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new MainModule(getLeftIToken(), getRightIToken(),
                                   (PrologEntryList)getRhsSym(1),
                                   (ExprSingleList)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 9:  LibraryModule ::= ModuleDecl Prolog
            //
            case 9: {
               //#line 114 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new LibraryModule(getLeftIToken(), getRightIToken(),
                                      (ModuleDecl)getRhsSym(1),
                                      (PrologEntryList)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 10:  ModuleDecl ::= module namespace NCName EQUAL URILiteral Separator
            //
            case 10: {
               //#line 116 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ModuleDecl(getLeftIToken(), getRightIToken(),
                                   new ASTNodeToken(getRhsIToken(1)),
                                   new ASTNodeToken(getRhsIToken(2)),
                                   new ASTNodeToken(getRhsIToken(3)),
                                   new ASTNodeToken(getRhsIToken(4)),
                                   new ASTNodeToken(getRhsIToken(5)),
                                   new ASTNodeToken(getRhsIToken(6)))
                );
                break;
            }
            //
            // Rule 11:  PrologEntry ::= DefaultNamespaceDecl Separator
            //
            case 11: {
               //#line 119 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new PrologEntry(getLeftIToken(), getRightIToken(),
                                    (DefaultNamespaceDecl)getRhsSym(1),
                                    new ASTNodeToken(getRhsIToken(2)),
                                    (ISetter)null,
                                    (NamespaceDecl)null,
                                    (IImport)null,
                                    (IVarDecl)null,
                                    (IFunctionDecl)null,
                                    (OptionDecl)null)
                );
                break;
            }
            //
            // Rule 12:  PrologEntry ::= Setter Separator
            //
            case 12: {
               //#line 120 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new PrologEntry(getLeftIToken(), getRightIToken(),
                                    (DefaultNamespaceDecl)null,
                                    new ASTNodeToken(getRhsIToken(2)),
                                    (ISetter)getRhsSym(1),
                                    (NamespaceDecl)null,
                                    (IImport)null,
                                    (IVarDecl)null,
                                    (IFunctionDecl)null,
                                    (OptionDecl)null)
                );
                break;
            }
            //
            // Rule 13:  PrologEntry ::= NamespaceDecl Separator
            //
            case 13: {
               //#line 121 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new PrologEntry(getLeftIToken(), getRightIToken(),
                                    (DefaultNamespaceDecl)null,
                                    new ASTNodeToken(getRhsIToken(2)),
                                    (ISetter)null,
                                    (NamespaceDecl)getRhsSym(1),
                                    (IImport)null,
                                    (IVarDecl)null,
                                    (IFunctionDecl)null,
                                    (OptionDecl)null)
                );
                break;
            }
            //
            // Rule 14:  PrologEntry ::= Import Separator
            //
            case 14: {
               //#line 122 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new PrologEntry(getLeftIToken(), getRightIToken(),
                                    (DefaultNamespaceDecl)null,
                                    new ASTNodeToken(getRhsIToken(2)),
                                    (ISetter)null,
                                    (NamespaceDecl)null,
                                    (IImport)getRhsSym(1),
                                    (IVarDecl)null,
                                    (IFunctionDecl)null,
                                    (OptionDecl)null)
                );
                break;
            }
            //
            // Rule 15:  PrologEntry ::= VarDecl Separator
            //
            case 15: {
               //#line 123 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new PrologEntry(getLeftIToken(), getRightIToken(),
                                    (DefaultNamespaceDecl)null,
                                    new ASTNodeToken(getRhsIToken(2)),
                                    (ISetter)null,
                                    (NamespaceDecl)null,
                                    (IImport)null,
                                    (IVarDecl)getRhsSym(1),
                                    (IFunctionDecl)null,
                                    (OptionDecl)null)
                );
                break;
            }
            //
            // Rule 16:  PrologEntry ::= FunctionDecl Separator
            //
            case 16: {
               //#line 124 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new PrologEntry(getLeftIToken(), getRightIToken(),
                                    (DefaultNamespaceDecl)null,
                                    new ASTNodeToken(getRhsIToken(2)),
                                    (ISetter)null,
                                    (NamespaceDecl)null,
                                    (IImport)null,
                                    (IVarDecl)null,
                                    (IFunctionDecl)getRhsSym(1),
                                    (OptionDecl)null)
                );
                break;
            }
            //
            // Rule 17:  PrologEntry ::= OptionDecl Separator
            //
            case 17: {
               //#line 125 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new PrologEntry(getLeftIToken(), getRightIToken(),
                                    (DefaultNamespaceDecl)null,
                                    new ASTNodeToken(getRhsIToken(2)),
                                    (ISetter)null,
                                    (NamespaceDecl)null,
                                    (IImport)null,
                                    (IVarDecl)null,
                                    (IFunctionDecl)null,
                                    (OptionDecl)getRhsSym(1))
                );
                break;
            }
            //
            // Rule 18:  PrologEntryList ::= $Empty
            //
            case 18: {
               //#line 126 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new PrologEntryList(getLeftIToken(), getRightIToken(), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 19:  PrologEntryList ::= PrologEntryList PrologEntry
            //
            case 19: {
               //#line 126 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                ((PrologEntryList)getRhsSym(1)).add((PrologEntry)getRhsSym(2));
                break;
            }
            //
            // Rule 20:  Prolog ::= PrologEntryList
            //
            case 20:
                break;
            //
            // Rule 21:  Setter ::= BoundarySpaceDecl
            //
            case 21: {
               //#line 129 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new Setter(getLeftIToken(), getRightIToken(),
                               (BoundarySpaceDecl)getRhsSym(1),
                               (DefaultCollationDecl)null,
                               (BaseURIDecl)null,
                               (ConstructionDecl)null,
                               (OrderingModeDecl)null,
                               (EmptyOrderDecl)null,
                               (CopyNamespacesDecl)null)
                );
                break;
            }
            //
            // Rule 22:  Setter ::= DefaultCollationDecl
            //
            case 22: {
               //#line 129 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new Setter(getLeftIToken(), getRightIToken(),
                               (BoundarySpaceDecl)null,
                               (DefaultCollationDecl)getRhsSym(1),
                               (BaseURIDecl)null,
                               (ConstructionDecl)null,
                               (OrderingModeDecl)null,
                               (EmptyOrderDecl)null,
                               (CopyNamespacesDecl)null)
                );
                break;
            }
            //
            // Rule 23:  Setter ::= BaseURIDecl
            //
            case 23: {
               //#line 129 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new Setter(getLeftIToken(), getRightIToken(),
                               (BoundarySpaceDecl)null,
                               (DefaultCollationDecl)null,
                               (BaseURIDecl)getRhsSym(1),
                               (ConstructionDecl)null,
                               (OrderingModeDecl)null,
                               (EmptyOrderDecl)null,
                               (CopyNamespacesDecl)null)
                );
                break;
            }
            //
            // Rule 24:  Setter ::= ConstructionDecl
            //
            case 24: {
               //#line 129 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new Setter(getLeftIToken(), getRightIToken(),
                               (BoundarySpaceDecl)null,
                               (DefaultCollationDecl)null,
                               (BaseURIDecl)null,
                               (ConstructionDecl)getRhsSym(1),
                               (OrderingModeDecl)null,
                               (EmptyOrderDecl)null,
                               (CopyNamespacesDecl)null)
                );
                break;
            }
            //
            // Rule 25:  Setter ::= OrderingModeDecl
            //
            case 25: {
               //#line 129 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new Setter(getLeftIToken(), getRightIToken(),
                               (BoundarySpaceDecl)null,
                               (DefaultCollationDecl)null,
                               (BaseURIDecl)null,
                               (ConstructionDecl)null,
                               (OrderingModeDecl)getRhsSym(1),
                               (EmptyOrderDecl)null,
                               (CopyNamespacesDecl)null)
                );
                break;
            }
            //
            // Rule 26:  Setter ::= EmptyOrderDecl
            //
            case 26: {
               //#line 129 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new Setter(getLeftIToken(), getRightIToken(),
                               (BoundarySpaceDecl)null,
                               (DefaultCollationDecl)null,
                               (BaseURIDecl)null,
                               (ConstructionDecl)null,
                               (OrderingModeDecl)null,
                               (EmptyOrderDecl)getRhsSym(1),
                               (CopyNamespacesDecl)null)
                );
                break;
            }
            //
            // Rule 27:  Setter ::= CopyNamespacesDecl
            //
            case 27: {
               //#line 129 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new Setter(getLeftIToken(), getRightIToken(),
                               (BoundarySpaceDecl)null,
                               (DefaultCollationDecl)null,
                               (BaseURIDecl)null,
                               (ConstructionDecl)null,
                               (OrderingModeDecl)null,
                               (EmptyOrderDecl)null,
                               (CopyNamespacesDecl)getRhsSym(1))
                );
                break;
            }
            //
            // Rule 28:  Import ::= SchemaImport
            //
            case 28: {
               //#line 131 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ImportBlk(getLeftIToken(), getRightIToken(),
                                  (SchemaImport)getRhsSym(1),
                                  (IModuleImport)null)
                );
                break;
            }
            //
            // Rule 29:  Import ::= ModuleImport
            //
            case 29: {
               //#line 131 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ImportBlk(getLeftIToken(), getRightIToken(),
                                  (SchemaImport)null,
                                  (IModuleImport)getRhsSym(1))
                );
                break;
            }
            //
            // Rule 30:  NamespaceDecl ::= declare namespace NCName EQUAL URILiteral
            //
            case 30: {
               //#line 135 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new NamespaceDecl(getLeftIToken(), getRightIToken(),
                                      new ASTNodeToken(getRhsIToken(1)),
                                      new ASTNodeToken(getRhsIToken(2)),
                                      new ASTNodeToken(getRhsIToken(3)),
                                      new ASTNodeToken(getRhsIToken(4)),
                                      new ASTNodeToken(getRhsIToken(5)))
                );
                break;
            }
            //
            // Rule 31:  BoundarySpaceDecl ::= declare boundary_space preserve
            //
            case 31: {
               //#line 138 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new BoundarySpaceDecl(getLeftIToken(), getRightIToken(),
                                          new ASTNodeToken(getRhsIToken(1)),
                                          new ASTNodeToken(getRhsIToken(2)),
                                          new ASTNodeToken(getRhsIToken(3)),
                                          (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 32:  BoundarySpaceDecl ::= declare boundary_space strip
            //
            case 32: {
               //#line 139 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new BoundarySpaceDecl(getLeftIToken(), getRightIToken(),
                                          new ASTNodeToken(getRhsIToken(1)),
                                          new ASTNodeToken(getRhsIToken(2)),
                                          (ASTNodeToken)null,
                                          new ASTNodeToken(getRhsIToken(3)))
                );
                break;
            }
            //
            // Rule 33:  DefaultNamespaceDecl ::= declare default element namespace URILiteral
            //
            case 33: {
               //#line 142 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new DefaultNamespaceDecl(getLeftIToken(), getRightIToken(),
                                             new ASTNodeToken(getRhsIToken(1)),
                                             new ASTNodeToken(getRhsIToken(2)),
                                             new ASTNodeToken(getRhsIToken(3)),
                                             new ASTNodeToken(getRhsIToken(4)),
                                             new ASTNodeToken(getRhsIToken(5)),
                                             (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 34:  DefaultNamespaceDecl ::= declare default function namespace URILiteral
            //
            case 34: {
               //#line 143 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new DefaultNamespaceDecl(getLeftIToken(), getRightIToken(),
                                             new ASTNodeToken(getRhsIToken(1)),
                                             new ASTNodeToken(getRhsIToken(2)),
                                             (ASTNodeToken)null,
                                             new ASTNodeToken(getRhsIToken(4)),
                                             new ASTNodeToken(getRhsIToken(5)),
                                             new ASTNodeToken(getRhsIToken(3)))
                );
                break;
            }
            //
            // Rule 35:  OptionDecl ::= declare option QName StringLiteral
            //
            case 35: {
               //#line 145 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new OptionDecl(getLeftIToken(), getRightIToken(),
                                   new ASTNodeToken(getRhsIToken(1)),
                                   new ASTNodeToken(getRhsIToken(2)),
                                   (IQName)getRhsSym(3),
                                   new ASTNodeToken(getRhsIToken(4)))
                );
                break;
            }
            //
            // Rule 36:  OrderingModeDecl ::= declare ordering ordered
            //
            case 36: {
               //#line 148 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new OrderingModeDecl(getLeftIToken(), getRightIToken(),
                                         new ASTNodeToken(getRhsIToken(1)),
                                         new ASTNodeToken(getRhsIToken(2)),
                                         new ASTNodeToken(getRhsIToken(3)),
                                         (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 37:  OrderingModeDecl ::= declare ordering unordered
            //
            case 37: {
               //#line 149 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new OrderingModeDecl(getLeftIToken(), getRightIToken(),
                                         new ASTNodeToken(getRhsIToken(1)),
                                         new ASTNodeToken(getRhsIToken(2)),
                                         (ASTNodeToken)null,
                                         new ASTNodeToken(getRhsIToken(3)))
                );
                break;
            }
            //
            // Rule 38:  EmptyOrderDecl ::= declare default order empty greatest
            //
            case 38: {
               //#line 152 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new EmptyOrderDecl(getLeftIToken(), getRightIToken(),
                                       new ASTNodeToken(getRhsIToken(1)),
                                       new ASTNodeToken(getRhsIToken(2)),
                                       new ASTNodeToken(getRhsIToken(3)),
                                       new ASTNodeToken(getRhsIToken(4)),
                                       new ASTNodeToken(getRhsIToken(5)),
                                       (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 39:  EmptyOrderDecl ::= declare default order empty least
            //
            case 39: {
               //#line 153 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new EmptyOrderDecl(getLeftIToken(), getRightIToken(),
                                       new ASTNodeToken(getRhsIToken(1)),
                                       new ASTNodeToken(getRhsIToken(2)),
                                       new ASTNodeToken(getRhsIToken(3)),
                                       new ASTNodeToken(getRhsIToken(4)),
                                       (ASTNodeToken)null,
                                       new ASTNodeToken(getRhsIToken(5)))
                );
                break;
            }
            //
            // Rule 40:  CopyNamespacesDecl ::= declare copy_namespaces PreserveMode COMMA InheritMode
            //
            case 40: {
               //#line 155 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new CopyNamespacesDecl(getLeftIToken(), getRightIToken(),
                                           new ASTNodeToken(getRhsIToken(1)),
                                           new ASTNodeToken(getRhsIToken(2)),
                                           (PreserveMode)getRhsSym(3),
                                           new ASTNodeToken(getRhsIToken(4)),
                                           (InheritMode)getRhsSym(5))
                );
                break;
            }
            //
            // Rule 41:  PreserveMode ::= preserve
            //
            case 41: {
               //#line 157 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new PreserveMode(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 42:  PreserveMode ::= no_preserve
            //
            case 42: {
               //#line 157 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new PreserveMode(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 43:  InheritMode ::= inherit
            //
            case 43: {
               //#line 159 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new InheritMode(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 44:  InheritMode ::= no_inherit
            //
            case 44: {
               //#line 159 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new InheritMode(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 45:  DefaultCollationDecl ::= declare default collation URILiteral
            //
            case 45: {
               //#line 161 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new DefaultCollationDecl(getLeftIToken(), getRightIToken(),
                                             new ASTNodeToken(getRhsIToken(1)),
                                             new ASTNodeToken(getRhsIToken(2)),
                                             new ASTNodeToken(getRhsIToken(3)),
                                             new ASTNodeToken(getRhsIToken(4)))
                );
                break;
            }
            //
            // Rule 46:  BaseURIDecl ::= declare base_uri URILiteral
            //
            case 46: {
               //#line 163 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new BaseURIDecl(getLeftIToken(), getRightIToken(),
                                    new ASTNodeToken(getRhsIToken(1)),
                                    new ASTNodeToken(getRhsIToken(2)),
                                    new ASTNodeToken(getRhsIToken(3)))
                );
                break;
            }
            //
            // Rule 47:  SchemaPrefixOpt ::= $Empty
            //
            case 47: {
               //#line 166 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(null);
                break;
            }
            //
            // Rule 48:  SchemaPrefixOpt ::= SchemaPrefix
            //
            case 48:
                break;
            //
            // Rule 49:  CommaSeparatedURILiteral ::= URILiteral
            //
            case 49: {
               //#line 167 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new URILiteralList(new ASTNodeToken(getRhsIToken(1)), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 50:  CommaSeparatedURILiteral ::= CommaSeparatedURILiteral COMMA URILiteral
            //
            case 50: {
               //#line 168 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                ((URILiteralList)getRhsSym(1)).add(new ASTNodeToken(getRhsIToken(3)));
                break;
            }
            //
            // Rule 51:  AtURILiteralSpecifierOpt ::= $Empty
            //
            case 51: {
               //#line 169 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(null);
                break;
            }
            //
            // Rule 52:  AtURILiteralSpecifierOpt ::= at CommaSeparatedURILiteral
            //
            case 52: {
               //#line 169 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new AtURILiteralSpecifierOpt(getLeftIToken(), getRightIToken(),
                                                 new ASTNodeToken(getRhsIToken(1)),
                                                 (URILiteralList)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 53:  SchemaImport ::= import schema SchemaPrefixOpt URILiteral AtURILiteralSpecifierOpt
            //
            case 53: {
               //#line 170 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new SchemaImport(getLeftIToken(), getRightIToken(),
                                     new ASTNodeToken(getRhsIToken(1)),
                                     new ASTNodeToken(getRhsIToken(2)),
                                     (ISchemaPrefixOpt)getRhsSym(3),
                                     new ASTNodeToken(getRhsIToken(4)),
                                     (AtURILiteralSpecifierOpt)getRhsSym(5))
                );
                break;
            }
            //
            // Rule 54:  SchemaPrefix ::= namespace NCName EQUAL
            //
            case 54: {
               //#line 173 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new SchemaPrefixNamed(getLeftIToken(), getRightIToken(),
                                          new ASTNodeToken(getRhsIToken(1)),
                                          new ASTNodeToken(getRhsIToken(2)),
                                          new ASTNodeToken(getRhsIToken(3)))
                );
                break;
            }
            //
            // Rule 55:  SchemaPrefix ::= default element namespace
            //
            case 55: {
               //#line 174 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new SchemaPrefixDefault(getLeftIToken(), getRightIToken(),
                                            new ASTNodeToken(getRhsIToken(1)),
                                            new ASTNodeToken(getRhsIToken(2)),
                                            new ASTNodeToken(getRhsIToken(3)))
                );
                break;
            }
            //
            // Rule 56:  ModuleImport ::= import module URILiteral AtURILiteralSpecifierOpt
            //
            case 56: {
               //#line 177 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ModuleImport(getLeftIToken(), getRightIToken(),
                                     new ASTNodeToken(getRhsIToken(1)),
                                     new ASTNodeToken(getRhsIToken(2)),
                                     new ASTNodeToken(getRhsIToken(3)),
                                     (AtURILiteralSpecifierOpt)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 57:  ModuleImport ::= import module namespace NCName EQUAL URILiteral AtURILiteralSpecifierOpt
            //
            case 57: {
               //#line 178 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ModuleImportNS(getLeftIToken(), getRightIToken(),
                                       new ASTNodeToken(getRhsIToken(1)),
                                       new ASTNodeToken(getRhsIToken(2)),
                                       new ASTNodeToken(getRhsIToken(3)),
                                       new ASTNodeToken(getRhsIToken(4)),
                                       new ASTNodeToken(getRhsIToken(5)),
                                       new ASTNodeToken(getRhsIToken(6)),
                                       (AtURILiteralSpecifierOpt)getRhsSym(7))
                );
                break;
            }
            //
            // Rule 58:  TypeDeclarationOpt ::= $Empty
            //
            case 58: {
               //#line 181 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(null);
                break;
            }
            //
            // Rule 59:  TypeDeclarationOpt ::= TypeDeclaration
            //
            case 59:
                break;
            //
            // Rule 60:  VarDecl ::= declare variable DOLLAR QName TypeDeclarationOpt ASSIGN ExprSingle
            //
            case 60: {
               //#line 182 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new VarDecl(getLeftIToken(), getRightIToken(),
                                new ASTNodeToken(getRhsIToken(1)),
                                new ASTNodeToken(getRhsIToken(2)),
                                new ASTNodeToken(getRhsIToken(3)),
                                (IQName)getRhsSym(4),
                                (TypeDeclaration)getRhsSym(5),
                                new ASTNodeToken(getRhsIToken(6)),
                                (IExprSingle)getRhsSym(7))
                );
                break;
            }
            //
            // Rule 61:  VarDecl ::= declare variable DOLLAR QName TypeDeclarationOpt external
            //
            case 61: {
               //#line 183 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new VarDeclExternal(getLeftIToken(), getRightIToken(),
                                        new ASTNodeToken(getRhsIToken(1)),
                                        new ASTNodeToken(getRhsIToken(2)),
                                        new ASTNodeToken(getRhsIToken(3)),
                                        (IQName)getRhsSym(4),
                                        (TypeDeclaration)getRhsSym(5),
                                        new ASTNodeToken(getRhsIToken(6)))
                );
                break;
            }
            //
            // Rule 62:  ConstructionDecl ::= declare construction strip
            //
            case 62: {
               //#line 186 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ConstructionDecl(getLeftIToken(), getRightIToken(),
                                         new ASTNodeToken(getRhsIToken(1)),
                                         new ASTNodeToken(getRhsIToken(2)),
                                         new ASTNodeToken(getRhsIToken(3)),
                                         (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 63:  ConstructionDecl ::= declare construction preserve
            //
            case 63: {
               //#line 186 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ConstructionDecl(getLeftIToken(), getRightIToken(),
                                         new ASTNodeToken(getRhsIToken(1)),
                                         new ASTNodeToken(getRhsIToken(2)),
                                         (ASTNodeToken)null,
                                         new ASTNodeToken(getRhsIToken(3)))
                );
                break;
            }
            //
            // Rule 64:  ParamListOpt ::= $Empty
            //
            case 64: {
               //#line 189 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(null);
                break;
            }
            //
            // Rule 65:  ParamListOpt ::= ParamList
            //
            case 65:
                break;
            //
            // Rule 66:  AsSequenceTypeOpt ::= $Empty
            //
            case 66: {
               //#line 190 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(null);
                break;
            }
            //
            // Rule 67:  AsSequenceTypeOpt ::= as SequenceType
            //
            case 67: {
               //#line 190 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new AsSequenceTypeOpt(getLeftIToken(), getRightIToken(),
                                          new ASTNodeToken(getRhsIToken(1)),
                                          (ISequenceType)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 68:  FunctionDecl ::= declare function QName LEFTPAREN ParamListOpt RIGHTPAREN AsSequenceTypeOpt EnclosedExpr
            //
            case 68: {
               //#line 191 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new FunctionDecl(getLeftIToken(), getRightIToken(),
                                     new ASTNodeToken(getRhsIToken(1)),
                                     new ASTNodeToken(getRhsIToken(2)),
                                     (IQName)getRhsSym(3),
                                     new ASTNodeToken(getRhsIToken(4)),
                                     (ParamList)getRhsSym(5),
                                     new ASTNodeToken(getRhsIToken(6)),
                                     (AsSequenceTypeOpt)getRhsSym(7),
                                     (EnclosedExpr)getRhsSym(8))
                );
                break;
            }
            //
            // Rule 69:  FunctionDecl ::= declare function QName LEFTPAREN ParamListOpt RIGHTPAREN AsSequenceTypeOpt external
            //
            case 69: {
               //#line 192 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new FunctionDeclExternal(getLeftIToken(), getRightIToken(),
                                             new ASTNodeToken(getRhsIToken(1)),
                                             new ASTNodeToken(getRhsIToken(2)),
                                             (IQName)getRhsSym(3),
                                             new ASTNodeToken(getRhsIToken(4)),
                                             (ParamList)getRhsSym(5),
                                             new ASTNodeToken(getRhsIToken(6)),
                                             (AsSequenceTypeOpt)getRhsSym(7),
                                             new ASTNodeToken(getRhsIToken(8)))
                );
                break;
            }
            //
            // Rule 70:  ParamList ::= Param
            //
            case 70: {
               //#line 194 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ParamList((Param)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 71:  ParamList ::= ParamList COMMA Param
            //
            case 71: {
               //#line 195 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                ((ParamList)getRhsSym(1)).add((Param)getRhsSym(3));
                break;
            }
            //
            // Rule 72:  Param ::= DOLLAR QName TypeDeclarationOpt
            //
            case 72: {
               //#line 198 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new Param(getLeftIToken(), getRightIToken(),
                              new ASTNodeToken(getRhsIToken(1)),
                              (IQName)getRhsSym(2),
                              (TypeDeclaration)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 73:  EnclosedExpr ::= LEFTBRACE Expr RIGHTBRACE
            //
            case 73: {
               //#line 200 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new EnclosedExpr(getLeftIToken(), getRightIToken(),
                                     new ASTNodeToken(getRhsIToken(1)),
                                     (ExprSingleList)getRhsSym(2),
                                     new ASTNodeToken(getRhsIToken(3)))
                );
                break;
            }
            //
            // Rule 74:  QueryBody ::= Expr
            //
            case 74:
                break;
            //
            // Rule 75:  Expr ::= ExprSingle
            //
            case 75: {
               //#line 205 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ExprSingleList((IExprSingle)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 76:  Expr ::= Expr COMMA ExprSingle
            //
            case 76: {
               //#line 206 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                ((ExprSingleList)getRhsSym(1)).add((IExprSingle)getRhsSym(3));
                break;
            }
            //
            // Rule 77:  ExprSingle ::= FLWORExpr
            //
            case 77:
                break;
            //
            // Rule 78:  ExprSingle ::= QuantifiedExpr
            //
            case 78:
                break;
            //
            // Rule 79:  ExprSingle ::= TypeswitchExpr
            //
            case 79:
                break;
            //
            // Rule 80:  ExprSingle ::= IfExpr
            //
            case 80:
                break;
            //
            // Rule 81:  ExprSingle ::= OrExpr
            //
            case 81:
                break;
            //
            // Rule 82:  WhereClauseOpt ::= $Empty
            //
            case 82: {
               //#line 216 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(null);
                break;
            }
            //
            // Rule 83:  WhereClauseOpt ::= WhereClause
            //
            case 83:
                break;
            //
            // Rule 84:  OrderByClauseOpt ::= $Empty
            //
            case 84: {
               //#line 217 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(null);
                break;
            }
            //
            // Rule 85:  OrderByClauseOpt ::= OrderByClause
            //
            case 85:
                break;
            //
            // Rule 86:  ForOrLetClause ::= ForClause
            //
            case 86:
                break;
            //
            // Rule 87:  ForOrLetClause ::= LetClause
            //
            case 87:
                break;
            //
            // Rule 88:  ForOrLetClauseList ::= ForOrLetClause
            //
            case 88: {
               //#line 219 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ForOrLetClauseList((IForOrLetClause)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 89:  ForOrLetClauseList ::= ForOrLetClauseList ForOrLetClause
            //
            case 89: {
               //#line 219 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                ((ForOrLetClauseList)getRhsSym(1)).add((IForOrLetClause)getRhsSym(2));
                break;
            }
            //
            // Rule 90:  FLWORExpr ::= ForOrLetClauseList WhereClauseOpt OrderByClauseOpt return ExprSingle
            //
            case 90: {
               //#line 220 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new FLWORExpr(getLeftIToken(), getRightIToken(),
                                  (ForOrLetClauseList)getRhsSym(1),
                                  (WhereClause)getRhsSym(2),
                                  (OrderByClause)getRhsSym(3),
                                  new ASTNodeToken(getRhsIToken(4)),
                                  (IExprSingle)getRhsSym(5))
                );
                break;
            }
            //
            // Rule 91:  PositionalVarOpt ::= $Empty
            //
            case 91: {
               //#line 223 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(null);
                break;
            }
            //
            // Rule 92:  PositionalVarOpt ::= PositionalVar
            //
            case 92:
                break;
            //
            // Rule 93:  ForClauseVarBinding ::= DOLLAR VarName TypeDeclarationOpt PositionalVarOpt in ExprSingle
            //
            case 93: {
               //#line 224 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ForClauseVarBinding(getLeftIToken(), getRightIToken(),
                                            new ASTNodeToken(getRhsIToken(1)),
                                            (IVarName)getRhsSym(2),
                                            (TypeDeclaration)getRhsSym(3),
                                            (PositionalVar)getRhsSym(4),
                                            new ASTNodeToken(getRhsIToken(5)),
                                            (IExprSingle)getRhsSym(6))
                );
                break;
            }
            //
            // Rule 94:  ForClauseVarBindingList ::= ForClauseVarBinding
            //
            case 94: {
               //#line 225 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ForClauseVarBindingList((ForClauseVarBinding)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 95:  ForClauseVarBindingList ::= ForClauseVarBindingList COMMA ForClauseVarBinding
            //
            case 95: {
               //#line 225 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                ((ForClauseVarBindingList)getRhsSym(1)).add((ForClauseVarBinding)getRhsSym(3));
                break;
            }
            //
            // Rule 96:  ForClause ::= for ForClauseVarBindingList
            //
            case 96: {
               //#line 226 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ForClause(getLeftIToken(), getRightIToken(),
                                  new ASTNodeToken(getRhsIToken(1)),
                                  (ForClauseVarBindingList)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 97:  PositionalVar ::= at DOLLAR VarName
            //
            case 97: {
               //#line 228 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new PositionalVar(getLeftIToken(), getRightIToken(),
                                      new ASTNodeToken(getRhsIToken(1)),
                                      new ASTNodeToken(getRhsIToken(2)),
                                      (IVarName)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 98:  LetClauseVarBinding ::= DOLLAR VarName TypeDeclarationOpt ASSIGN ExprSingle
            //
            case 98: {
               //#line 231 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new LetClauseVarBinding(getLeftIToken(), getRightIToken(),
                                            new ASTNodeToken(getRhsIToken(1)),
                                            (IVarName)getRhsSym(2),
                                            (TypeDeclaration)getRhsSym(3),
                                            new ASTNodeToken(getRhsIToken(4)),
                                            (IExprSingle)getRhsSym(5))
                );
                break;
            }
            //
            // Rule 99:  LetClauseVarBindingList ::= LetClauseVarBinding
            //
            case 99: {
               //#line 232 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new LetClauseVarBindingList((LetClauseVarBinding)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 100:  LetClauseVarBindingList ::= LetClauseVarBindingList COMMA LetClauseVarBinding
            //
            case 100: {
               //#line 232 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                ((LetClauseVarBindingList)getRhsSym(1)).add((LetClauseVarBinding)getRhsSym(3));
                break;
            }
            //
            // Rule 101:  LetClause ::= let LetClauseVarBindingList
            //
            case 101: {
               //#line 233 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new LetClause(getLeftIToken(), getRightIToken(),
                                  new ASTNodeToken(getRhsIToken(1)),
                                  (LetClauseVarBindingList)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 102:  WhereClause ::= where ExprSingle
            //
            case 102: {
               //#line 235 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new WhereClause(getLeftIToken(), getRightIToken(),
                                    new ASTNodeToken(getRhsIToken(1)),
                                    (IExprSingle)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 103:  OrderByClause ::= order by OrderSpecList
            //
            case 103: {
               //#line 237 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new OrderByClause(getLeftIToken(), getRightIToken(),
                                      new ASTNodeToken(getRhsIToken(1)),
                                      new ASTNodeToken(getRhsIToken(2)),
                                      (OrderSpecList)getRhsSym(3),
                                      (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 104:  OrderByClause ::= stable order by OrderSpecList
            //
            case 104: {
               //#line 238 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new OrderByClause(getLeftIToken(), getRightIToken(),
                                      new ASTNodeToken(getRhsIToken(2)),
                                      new ASTNodeToken(getRhsIToken(3)),
                                      (OrderSpecList)getRhsSym(4),
                                      new ASTNodeToken(getRhsIToken(1)))
                );
                break;
            }
            //
            // Rule 105:  OrderSpecList ::= OrderSpec
            //
            case 105: {
               //#line 241 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new OrderSpecList((OrderSpec)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 106:  OrderSpecList ::= OrderSpecList COMMA OrderSpec
            //
            case 106: {
               //#line 241 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                ((OrderSpecList)getRhsSym(1)).add((OrderSpec)getRhsSym(3));
                break;
            }
            //
            // Rule 107:  OrderSpec ::= ExprSingle OrderModifier
            //
            case 107: {
               //#line 243 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new OrderSpec(getLeftIToken(), getRightIToken(),
                                  (IExprSingle)getRhsSym(1),
                                  (OrderModifier)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 108:  SortDirectionSegment ::= ascending
            //
            case 108: {
               //#line 246 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new SortDirectionSegment(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 109:  SortDirectionSegment ::= descending
            //
            case 109: {
               //#line 246 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new SortDirectionSegment(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 110:  SortDirectionSegmentOpt ::= $Empty
            //
            case 110: {
               //#line 247 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(null);
                break;
            }
            //
            // Rule 111:  SortDirectionSegmentOpt ::= SortDirectionSegment
            //
            case 111:
                break;
            //
            // Rule 112:  EmptySortSegment ::= empty greatest
            //
            case 112: {
               //#line 248 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new EmptySortSegment(getLeftIToken(), getRightIToken(),
                                         new ASTNodeToken(getRhsIToken(1)),
                                         new ASTNodeToken(getRhsIToken(2)),
                                         (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 113:  EmptySortSegment ::= empty least
            //
            case 113: {
               //#line 248 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new EmptySortSegment(getLeftIToken(), getRightIToken(),
                                         new ASTNodeToken(getRhsIToken(1)),
                                         (ASTNodeToken)null,
                                         new ASTNodeToken(getRhsIToken(2)))
                );
                break;
            }
            //
            // Rule 114:  EmptySortSegmentOpt ::= $Empty
            //
            case 114: {
               //#line 249 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(null);
                break;
            }
            //
            // Rule 115:  EmptySortSegmentOpt ::= EmptySortSegment
            //
            case 115:
                break;
            //
            // Rule 116:  CollationSegmentOpt ::= $Empty
            //
            case 116: {
               //#line 250 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(null);
                break;
            }
            //
            // Rule 117:  CollationSegmentOpt ::= collation URILiteral
            //
            case 117: {
               //#line 250 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new CollationSegmentOpt(getLeftIToken(), getRightIToken(),
                                            new ASTNodeToken(getRhsIToken(1)),
                                            new ASTNodeToken(getRhsIToken(2)))
                );
                break;
            }
            //
            // Rule 118:  OrderModifier ::= SortDirectionSegmentOpt EmptySortSegmentOpt CollationSegmentOpt
            //
            case 118: {
               //#line 251 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new OrderModifier(getLeftIToken(), getRightIToken(),
                                      (SortDirectionSegment)getRhsSym(1),
                                      (EmptySortSegment)getRhsSym(2),
                                      (CollationSegmentOpt)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 119:  QuantifiedExprVarBinding ::= DOLLAR VarName TypeDeclarationOpt in ExprSingle
            //
            case 119: {
               //#line 254 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new QuantifiedExprVarBinding(getLeftIToken(), getRightIToken(),
                                                 new ASTNodeToken(getRhsIToken(1)),
                                                 (IVarName)getRhsSym(2),
                                                 (TypeDeclaration)getRhsSym(3),
                                                 new ASTNodeToken(getRhsIToken(4)),
                                                 (IExprSingle)getRhsSym(5))
                );
                break;
            }
            //
            // Rule 120:  QuantifiedExprVarBindingList ::= QuantifiedExprVarBinding
            //
            case 120: {
               //#line 255 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new QuantifiedExprVarBindingList((QuantifiedExprVarBinding)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 121:  QuantifiedExprVarBindingList ::= QuantifiedExprVarBindingList COMMA QuantifiedExprVarBinding
            //
            case 121: {
               //#line 256 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                ((QuantifiedExprVarBindingList)getRhsSym(1)).add((QuantifiedExprVarBinding)getRhsSym(3));
                break;
            }
            //
            // Rule 122:  QuantifiedExpr ::= some QuantifiedExprVarBindingList satisfies ExprSingle
            //
            case 122: {
               //#line 257 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new SomeQuantifiedExpr(getLeftIToken(), getRightIToken(),
                                           new ASTNodeToken(getRhsIToken(1)),
                                           (QuantifiedExprVarBindingList)getRhsSym(2),
                                           new ASTNodeToken(getRhsIToken(3)),
                                           (IExprSingle)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 123:  QuantifiedExpr ::= every QuantifiedExprVarBindingList satisfies ExprSingle
            //
            case 123: {
               //#line 258 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new EveryQuantifiedExpr(getLeftIToken(), getRightIToken(),
                                            new ASTNodeToken(getRhsIToken(1)),
                                            (QuantifiedExprVarBindingList)getRhsSym(2),
                                            new ASTNodeToken(getRhsIToken(3)),
                                            (IExprSingle)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 124:  CaseClauseList ::= CaseClause
            //
            case 124: {
               //#line 261 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new CaseClauseList((CaseClause)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 125:  CaseClauseList ::= CaseClauseList CaseClause
            //
            case 125: {
               //#line 261 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                ((CaseClauseList)getRhsSym(1)).add((CaseClause)getRhsSym(2));
                break;
            }
            //
            // Rule 126:  TypeswitchExpr ::= typeswitch LEFTPAREN Expr RIGHTPAREN CaseClauseList default return ExprSingle
            //
            case 126: {
               //#line 262 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new TypeswitchExpr(getLeftIToken(), getRightIToken(),
                                       new ASTNodeToken(getRhsIToken(1)),
                                       new ASTNodeToken(getRhsIToken(2)),
                                       (ExprSingleList)getRhsSym(3),
                                       new ASTNodeToken(getRhsIToken(4)),
                                       (CaseClauseList)getRhsSym(5),
                                       new ASTNodeToken(getRhsIToken(6)),
                                       new ASTNodeToken(getRhsIToken(7)),
                                       (IExprSingle)getRhsSym(8),
                                       (ASTNodeToken)null,
                                       (IVarName)null)
                );
                break;
            }
            //
            // Rule 127:  TypeswitchExpr ::= typeswitch LEFTPAREN Expr RIGHTPAREN CaseClauseList default DOLLAR VarName return ExprSingle
            //
            case 127: {
               //#line 263 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new TypeswitchExpr(getLeftIToken(), getRightIToken(),
                                       new ASTNodeToken(getRhsIToken(1)),
                                       new ASTNodeToken(getRhsIToken(2)),
                                       (ExprSingleList)getRhsSym(3),
                                       new ASTNodeToken(getRhsIToken(4)),
                                       (CaseClauseList)getRhsSym(5),
                                       new ASTNodeToken(getRhsIToken(6)),
                                       new ASTNodeToken(getRhsIToken(9)),
                                       (IExprSingle)getRhsSym(10),
                                       new ASTNodeToken(getRhsIToken(7)),
                                       (IVarName)getRhsSym(8))
                );
                break;
            }
            //
            // Rule 128:  CaseClauseVarNameDeclOpt ::= $Empty
            //
            case 128: {
               //#line 265 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(null);
                break;
            }
            //
            // Rule 129:  CaseClauseVarNameDeclOpt ::= DOLLAR VarName as
            //
            case 129: {
               //#line 265 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new CaseClauseVarNameDeclOpt(getLeftIToken(), getRightIToken(),
                                                 new ASTNodeToken(getRhsIToken(1)),
                                                 (IVarName)getRhsSym(2),
                                                 new ASTNodeToken(getRhsIToken(3)))
                );
                break;
            }
            //
            // Rule 130:  CaseClause ::= case CaseClauseVarNameDeclOpt SequenceType return ExprSingle
            //
            case 130: {
               //#line 266 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new CaseClause(getLeftIToken(), getRightIToken(),
                                   new ASTNodeToken(getRhsIToken(1)),
                                   (CaseClauseVarNameDeclOpt)getRhsSym(2),
                                   (ISequenceType)getRhsSym(3),
                                   new ASTNodeToken(getRhsIToken(4)),
                                   (IExprSingle)getRhsSym(5))
                );
                break;
            }
            //
            // Rule 131:  IfExpr ::= if LEFTPAREN Expr RIGHTPAREN then ExprSingle else ExprSingle
            //
            case 131: {
               //#line 268 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new IfExpr(getLeftIToken(), getRightIToken(),
                               new ASTNodeToken(getRhsIToken(1)),
                               new ASTNodeToken(getRhsIToken(2)),
                               (ExprSingleList)getRhsSym(3),
                               new ASTNodeToken(getRhsIToken(4)),
                               new ASTNodeToken(getRhsIToken(5)),
                               (IExprSingle)getRhsSym(6),
                               new ASTNodeToken(getRhsIToken(7)),
                               (IExprSingle)getRhsSym(8))
                );
                break;
            }
            //
            // Rule 132:  OrExpr ::= AndExpr
            //
            case 132:
                break;
            //
            // Rule 133:  OrExpr ::= OrExpr or AndExpr
            //
            case 133: {
               //#line 271 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new OrExpr(getLeftIToken(), getRightIToken(),
                               (IOrExpr)getRhsSym(1),
                               new ASTNodeToken(getRhsIToken(2)),
                               (IAndExpr)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 134:  AndExpr ::= ComparisonExpr
            //
            case 134:
                break;
            //
            // Rule 135:  AndExpr ::= AndExpr and ComparisonExpr
            //
            case 135: {
               //#line 273 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new AndExpr(getLeftIToken(), getRightIToken(),
                                (IAndExpr)getRhsSym(1),
                                new ASTNodeToken(getRhsIToken(2)),
                                (IComparisonExpr)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 136:  ComparisonExpr ::= RangeExpr
            //
            case 136:
                break;
            //
            // Rule 137:  ComparisonExpr ::= RangeExpr EQUAL RangeExpr
            //
            case 137: {
               //#line 277 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ComparisonExpr(getLeftIToken(), getRightIToken(),
                                       (IRangeExpr)getRhsSym(1),
                                       new ASTNodeToken(getRhsIToken(2)),
                                       (IRangeExpr)getRhsSym(3),
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 138:  ComparisonExpr ::= RangeExpr NOTEQUAL RangeExpr
            //
            case 138: {
               //#line 278 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ComparisonExpr(getLeftIToken(), getRightIToken(),
                                       (IRangeExpr)getRhsSym(1),
                                       (ASTNodeToken)null,
                                       (IRangeExpr)getRhsSym(3),
                                       new ASTNodeToken(getRhsIToken(2)),
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 139:  ComparisonExpr ::= RangeExpr LESS RangeExpr
            //
            case 139: {
               //#line 279 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ComparisonExpr(getLeftIToken(), getRightIToken(),
                                       (IRangeExpr)getRhsSym(1),
                                       (ASTNodeToken)null,
                                       (IRangeExpr)getRhsSym(3),
                                       (ASTNodeToken)null,
                                       new ASTNodeToken(getRhsIToken(2)),
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 140:  ComparisonExpr ::= RangeExpr LESSEQUAL RangeExpr
            //
            case 140: {
               //#line 280 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ComparisonExpr(getLeftIToken(), getRightIToken(),
                                       (IRangeExpr)getRhsSym(1),
                                       (ASTNodeToken)null,
                                       (IRangeExpr)getRhsSym(3),
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       new ASTNodeToken(getRhsIToken(2)),
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 141:  ComparisonExpr ::= RangeExpr GREATER RangeExpr
            //
            case 141: {
               //#line 281 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ComparisonExpr(getLeftIToken(), getRightIToken(),
                                       (IRangeExpr)getRhsSym(1),
                                       (ASTNodeToken)null,
                                       (IRangeExpr)getRhsSym(3),
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       new ASTNodeToken(getRhsIToken(2)),
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 142:  ComparisonExpr ::= RangeExpr GREATEREQUAL RangeExpr
            //
            case 142: {
               //#line 282 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ComparisonExpr(getLeftIToken(), getRightIToken(),
                                       (IRangeExpr)getRhsSym(1),
                                       (ASTNodeToken)null,
                                       (IRangeExpr)getRhsSym(3),
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       new ASTNodeToken(getRhsIToken(2)),
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 143:  ComparisonExpr ::= RangeExpr eq RangeExpr
            //
            case 143: {
               //#line 283 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ComparisonExpr(getLeftIToken(), getRightIToken(),
                                       (IRangeExpr)getRhsSym(1),
                                       (ASTNodeToken)null,
                                       (IRangeExpr)getRhsSym(3),
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       new ASTNodeToken(getRhsIToken(2)),
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 144:  ComparisonExpr ::= RangeExpr ne RangeExpr
            //
            case 144: {
               //#line 284 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ComparisonExpr(getLeftIToken(), getRightIToken(),
                                       (IRangeExpr)getRhsSym(1),
                                       (ASTNodeToken)null,
                                       (IRangeExpr)getRhsSym(3),
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       new ASTNodeToken(getRhsIToken(2)),
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 145:  ComparisonExpr ::= RangeExpr lt RangeExpr
            //
            case 145: {
               //#line 285 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ComparisonExpr(getLeftIToken(), getRightIToken(),
                                       (IRangeExpr)getRhsSym(1),
                                       (ASTNodeToken)null,
                                       (IRangeExpr)getRhsSym(3),
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       new ASTNodeToken(getRhsIToken(2)),
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 146:  ComparisonExpr ::= RangeExpr le RangeExpr
            //
            case 146: {
               //#line 286 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ComparisonExpr(getLeftIToken(), getRightIToken(),
                                       (IRangeExpr)getRhsSym(1),
                                       (ASTNodeToken)null,
                                       (IRangeExpr)getRhsSym(3),
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       new ASTNodeToken(getRhsIToken(2)),
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 147:  ComparisonExpr ::= RangeExpr gt RangeExpr
            //
            case 147: {
               //#line 287 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ComparisonExpr(getLeftIToken(), getRightIToken(),
                                       (IRangeExpr)getRhsSym(1),
                                       (ASTNodeToken)null,
                                       (IRangeExpr)getRhsSym(3),
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       new ASTNodeToken(getRhsIToken(2)),
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 148:  ComparisonExpr ::= RangeExpr ge RangeExpr
            //
            case 148: {
               //#line 288 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ComparisonExpr(getLeftIToken(), getRightIToken(),
                                       (IRangeExpr)getRhsSym(1),
                                       (ASTNodeToken)null,
                                       (IRangeExpr)getRhsSym(3),
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       new ASTNodeToken(getRhsIToken(2)),
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 149:  ComparisonExpr ::= RangeExpr is RangeExpr
            //
            case 149: {
               //#line 289 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ComparisonExpr(getLeftIToken(), getRightIToken(),
                                       (IRangeExpr)getRhsSym(1),
                                       (ASTNodeToken)null,
                                       (IRangeExpr)getRhsSym(3),
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       new ASTNodeToken(getRhsIToken(2)),
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 150:  ComparisonExpr ::= RangeExpr NodeCompLeft RangeExpr
            //
            case 150: {
               //#line 290 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ComparisonExpr(getLeftIToken(), getRightIToken(),
                                       (IRangeExpr)getRhsSym(1),
                                       (ASTNodeToken)null,
                                       (IRangeExpr)getRhsSym(3),
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       new ASTNodeToken(getRhsIToken(2)),
                                       (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 151:  ComparisonExpr ::= RangeExpr NodeCompRight RangeExpr
            //
            case 151: {
               //#line 291 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ComparisonExpr(getLeftIToken(), getRightIToken(),
                                       (IRangeExpr)getRhsSym(1),
                                       (ASTNodeToken)null,
                                       (IRangeExpr)getRhsSym(3),
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       (ASTNodeToken)null,
                                       new ASTNodeToken(getRhsIToken(2)))
                );
                break;
            }
            //
            // Rule 152:  RangeExpr ::= AdditiveExpr
            //
            case 152:
                break;
            //
            // Rule 153:  RangeExpr ::= AdditiveExpr to AdditiveExpr
            //
            case 153: {
               //#line 294 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new RangeExpr(getLeftIToken(), getRightIToken(),
                                  (IAdditiveExpr)getRhsSym(1),
                                  new ASTNodeToken(getRhsIToken(2)),
                                  (IAdditiveExpr)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 154:  AdditiveExpr ::= MultiplicativeExpr
            //
            case 154:
                break;
            //
            // Rule 155:  AdditiveExpr ::= AdditiveExpr PLUS MultiplicativeExpr
            //
            case 155: {
               //#line 298 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new AdditiveExpr(getLeftIToken(), getRightIToken(),
                                     (IAdditiveExpr)getRhsSym(1),
                                     new ASTNodeToken(getRhsIToken(2)),
                                     (IMultiplicativeExpr)getRhsSym(3),
                                     (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 156:  AdditiveExpr ::= AdditiveExpr MINUS MultiplicativeExpr
            //
            case 156: {
               //#line 299 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new AdditiveExpr(getLeftIToken(), getRightIToken(),
                                     (IAdditiveExpr)getRhsSym(1),
                                     (ASTNodeToken)null,
                                     (IMultiplicativeExpr)getRhsSym(3),
                                     new ASTNodeToken(getRhsIToken(2)))
                );
                break;
            }
            //
            // Rule 157:  MultiplicativeExpr ::= UnionExpr
            //
            case 157:
                break;
            //
            // Rule 158:  MultiplicativeExpr ::= MultiplicativeExpr TIMES UnionExpr
            //
            case 158: {
               //#line 303 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new MultiplicativeExpr(getLeftIToken(), getRightIToken(),
                                           (IMultiplicativeExpr)getRhsSym(1),
                                           new ASTNodeToken(getRhsIToken(2)),
                                           (IUnionExpr)getRhsSym(3),
                                           (ASTNodeToken)null,
                                           (ASTNodeToken)null,
                                           (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 159:  MultiplicativeExpr ::= MultiplicativeExpr div UnionExpr
            //
            case 159: {
               //#line 304 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new MultiplicativeExpr(getLeftIToken(), getRightIToken(),
                                           (IMultiplicativeExpr)getRhsSym(1),
                                           (ASTNodeToken)null,
                                           (IUnionExpr)getRhsSym(3),
                                           new ASTNodeToken(getRhsIToken(2)),
                                           (ASTNodeToken)null,
                                           (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 160:  MultiplicativeExpr ::= MultiplicativeExpr idiv UnionExpr
            //
            case 160: {
               //#line 305 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new MultiplicativeExpr(getLeftIToken(), getRightIToken(),
                                           (IMultiplicativeExpr)getRhsSym(1),
                                           (ASTNodeToken)null,
                                           (IUnionExpr)getRhsSym(3),
                                           (ASTNodeToken)null,
                                           new ASTNodeToken(getRhsIToken(2)),
                                           (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 161:  MultiplicativeExpr ::= MultiplicativeExpr mod UnionExpr
            //
            case 161: {
               //#line 306 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new MultiplicativeExpr(getLeftIToken(), getRightIToken(),
                                           (IMultiplicativeExpr)getRhsSym(1),
                                           (ASTNodeToken)null,
                                           (IUnionExpr)getRhsSym(3),
                                           (ASTNodeToken)null,
                                           (ASTNodeToken)null,
                                           new ASTNodeToken(getRhsIToken(2)))
                );
                break;
            }
            //
            // Rule 162:  UnionExpr ::= IntersectExceptExpr
            //
            case 162:
                break;
            //
            // Rule 163:  UnionExpr ::= UnionExpr union IntersectExceptExpr
            //
            case 163: {
               //#line 310 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new UnionExpr(getLeftIToken(), getRightIToken(),
                                  (IUnionExpr)getRhsSym(1),
                                  new ASTNodeToken(getRhsIToken(2)),
                                  (IIntersectExceptExpr)getRhsSym(3),
                                  (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 164:  UnionExpr ::= UnionExpr ORBar IntersectExceptExpr
            //
            case 164: {
               //#line 311 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new UnionExpr(getLeftIToken(), getRightIToken(),
                                  (IUnionExpr)getRhsSym(1),
                                  (ASTNodeToken)null,
                                  (IIntersectExceptExpr)getRhsSym(3),
                                  new ASTNodeToken(getRhsIToken(2)))
                );
                break;
            }
            //
            // Rule 165:  IntersectExceptExpr ::= InstanceofExpr
            //
            case 165:
                break;
            //
            // Rule 166:  IntersectExceptExpr ::= IntersectExceptExpr intersect InstanceofExpr
            //
            case 166: {
               //#line 315 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new IntersectExceptExpr(getLeftIToken(), getRightIToken(),
                                            (IIntersectExceptExpr)getRhsSym(1),
                                            new ASTNodeToken(getRhsIToken(2)),
                                            (IInstanceofExpr)getRhsSym(3),
                                            (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 167:  IntersectExceptExpr ::= IntersectExceptExpr except InstanceofExpr
            //
            case 167: {
               //#line 316 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new IntersectExceptExpr(getLeftIToken(), getRightIToken(),
                                            (IIntersectExceptExpr)getRhsSym(1),
                                            (ASTNodeToken)null,
                                            (IInstanceofExpr)getRhsSym(3),
                                            new ASTNodeToken(getRhsIToken(2)))
                );
                break;
            }
            //
            // Rule 168:  InstanceofExpr ::= TreatExpr
            //
            case 168:
                break;
            //
            // Rule 169:  InstanceofExpr ::= TreatExpr instance of SequenceType
            //
            case 169: {
               //#line 318 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new InstanceofExpr(getLeftIToken(), getRightIToken(),
                                       (ITreatExpr)getRhsSym(1),
                                       new ASTNodeToken(getRhsIToken(2)),
                                       new ASTNodeToken(getRhsIToken(3)),
                                       (ISequenceType)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 170:  TreatExpr ::= CastableExpr
            //
            case 170:
                break;
            //
            // Rule 171:  TreatExpr ::= CastableExpr treat as SequenceType
            //
            case 171: {
               //#line 321 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new TreatExpr(getLeftIToken(), getRightIToken(),
                                  (ICastableExpr)getRhsSym(1),
                                  new ASTNodeToken(getRhsIToken(2)),
                                  new ASTNodeToken(getRhsIToken(3)),
                                  (ISequenceType)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 172:  CastableExpr ::= CastExpr
            //
            case 172:
                break;
            //
            // Rule 173:  CastableExpr ::= CastExpr castable as SequenceType
            //
            case 173: {
               //#line 324 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new CastableExpr(getLeftIToken(), getRightIToken(),
                                     (ICastExpr)getRhsSym(1),
                                     new ASTNodeToken(getRhsIToken(2)),
                                     new ASTNodeToken(getRhsIToken(3)),
                                     (ISequenceType)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 174:  CastExpr ::= UnaryExpr
            //
            case 174:
                break;
            //
            // Rule 175:  CastExpr ::= UnaryExpr cast as SingleType
            //
            case 175: {
               //#line 327 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new CastExpr(getLeftIToken(), getRightIToken(),
                                 (IUnaryExpr)getRhsSym(1),
                                 new ASTNodeToken(getRhsIToken(2)),
                                 new ASTNodeToken(getRhsIToken(3)),
                                 (ISingleType)getRhsSym(4))
                );
                break;
            }
            //
            // Rule 176:  UnaryExpr ::= ValueExpr
            //
            case 176:
                break;
            //
            // Rule 177:  UnaryExpr ::= PlusOrMinusList ValueExpr
            //
            case 177: {
               //#line 331 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new UnaryExpr(getLeftIToken(), getRightIToken(),
                                  (PlusOrMinusList)getRhsSym(1),
                                  (IValueExpr)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 178:  PlusOrMinus ::= PLUS
            //
            case 178: {
               //#line 332 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new PlusOrMinus(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 179:  PlusOrMinus ::= MINUS
            //
            case 179: {
               //#line 332 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new PlusOrMinus(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 180:  PlusOrMinusList ::= PlusOrMinus
            //
            case 180: {
               //#line 333 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new PlusOrMinusList((PlusOrMinus)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 181:  PlusOrMinusList ::= PlusOrMinusList PlusOrMinus
            //
            case 181: {
               //#line 333 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                ((PlusOrMinusList)getRhsSym(1)).add((PlusOrMinus)getRhsSym(2));
                break;
            }
            //
            // Rule 182:  ValueExpr ::= PathExpr
            //
            case 182:
                break;
            //
            // Rule 183:  ValueExpr ::= ValidateExpr
            //
            case 183:
                break;
            //
            // Rule 184:  ValueExpr ::= ExtensionExpr
            //
            case 184:
                break;
            //
            // Rule 185:  ValidateExpr ::= validate LEFTBRACE Expr RIGHTBRACE
            //
            case 185: {
               //#line 338 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ValidateExpr(getLeftIToken(), getRightIToken(),
                                     new ASTNodeToken(getRhsIToken(1)),
                                     new ASTNodeToken(getRhsIToken(2)),
                                     (ExprSingleList)getRhsSym(3),
                                     new ASTNodeToken(getRhsIToken(4)),
                                     (IValidationMode)null)
                );
                break;
            }
            //
            // Rule 186:  ValidateExpr ::= validate ValidationMode LEFTBRACE Expr RIGHTBRACE
            //
            case 186: {
               //#line 339 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ValidateExpr(getLeftIToken(), getRightIToken(),
                                     new ASTNodeToken(getRhsIToken(1)),
                                     new ASTNodeToken(getRhsIToken(3)),
                                     (ExprSingleList)getRhsSym(4),
                                     new ASTNodeToken(getRhsIToken(5)),
                                     (IValidationMode)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 187:  ValidationMode ::= lax
            //
            case 187: {
               //#line 341 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ValidationMode0(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 188:  ValidationMode ::= strict
            //
            case 188: {
               //#line 341 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ValidationMode1(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 189:  PragmaList ::= Pragma
            //
            case 189: {
               //#line 344 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new PragmaList((Pragma)getRhsSym(1), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 190:  PragmaList ::= PragmaList Pragma
            //
            case 190: {
               //#line 344 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                ((PragmaList)getRhsSym(1)).add((Pragma)getRhsSym(2));
                break;
            }
            //
            // Rule 191:  ExtensionExpr ::= PragmaList LEFTBRACE RIGHTBRACE
            //
            case 191: {
               //#line 345 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ExtensionExpr0(getLeftIToken(), getRightIToken(),
                                       (PragmaList)getRhsSym(1),
                                       new ASTNodeToken(getRhsIToken(2)),
                                       new ASTNodeToken(getRhsIToken(3)))
                );
                break;
            }
            //
            // Rule 192:  ExtensionExpr ::= PragmaList LEFTBRACE Expr RIGHTBRACE
            //
            case 192: {
               //#line 345 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ExtensionExpr1(getLeftIToken(), getRightIToken(),
                                       (PragmaList)getRhsSym(1),
                                       new ASTNodeToken(getRhsIToken(2)),
                                       (ExprSingleList)getRhsSym(3),
                                       new ASTNodeToken(getRhsIToken(4)))
                );
                break;
            }
            //
            // Rule 193:  Pragma ::= PragmaST QName PragmaET
            //
            case 193: {
               //#line 348 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new Pragma(getLeftIToken(), getRightIToken(),
                               new ASTNodeToken(getRhsIToken(1)),
                               (IQName)getRhsSym(2),
                               new ASTNodeToken(getRhsIToken(3)),
                               (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 194:  Pragma ::= PragmaST QName PragmaContents PragmaET
            //
            case 194: {
               //#line 349 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new Pragma(getLeftIToken(), getRightIToken(),
                               new ASTNodeToken(getRhsIToken(1)),
                               (IQName)getRhsSym(2),
                               new ASTNodeToken(getRhsIToken(4)),
                               new ASTNodeToken(getRhsIToken(3)))
                );
                break;
            }
            //
            // Rule 195:  PathExpr ::= SLASH
            //
            case 195: {
               //#line 356 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new PathExpr(getLeftIToken(), getRightIToken(),
                                 new ASTNodeToken(getRhsIToken(1)),
                                 (IRelativePathExpr)null,
                                 (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 196:  PathExpr ::= SLASH RelativePathExpr
            //
            case 196: {
               //#line 357 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new PathExpr(getLeftIToken(), getRightIToken(),
                                 new ASTNodeToken(getRhsIToken(1)),
                                 (IRelativePathExpr)getRhsSym(2),
                                 (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 197:  PathExpr ::= DoubleSlash RelativePathExpr
            //
            case 197: {
               //#line 358 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new PathExpr(getLeftIToken(), getRightIToken(),
                                 (ASTNodeToken)null,
                                 (IRelativePathExpr)getRhsSym(2),
                                 new ASTNodeToken(getRhsIToken(1)))
                );
                break;
            }
            //
            // Rule 198:  PathExpr ::= RelativePathExpr
            //
            case 198: {
               //#line 359 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new PathExpr(getLeftIToken(), getRightIToken(),
                                 (ASTNodeToken)null,
                                 (IRelativePathExpr)getRhsSym(1),
                                 (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 199:  RelativePathExpr ::= StepExpr
            //
            case 199:
                break;
            //
            // Rule 200:  RelativePathExpr ::= RelativePathExpr SLASH StepExpr
            //
            case 200: {
               //#line 363 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new RelativePathExpr(getLeftIToken(), getRightIToken(),
                                         (IRelativePathExpr)getRhsSym(1),
                                         new ASTNodeToken(getRhsIToken(2)),
                                         (IStepExpr)getRhsSym(3),
                                         (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 201:  RelativePathExpr ::= RelativePathExpr DoubleSlash StepExpr
            //
            case 201: {
               //#line 364 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new RelativePathExpr(getLeftIToken(), getRightIToken(),
                                         (IRelativePathExpr)getRhsSym(1),
                                         (ASTNodeToken)null,
                                         (IStepExpr)getRhsSym(3),
                                         new ASTNodeToken(getRhsIToken(2)))
                );
                break;
            }
            //
            // Rule 202:  StepExpr ::= FilterExpr
            //
            case 202:
                break;
            //
            // Rule 203:  StepExpr ::= AxisStep
            //
            case 203:
                break;
            //
            // Rule 204:  AxisStep ::= ReverseStep PredicateList
            //
            case 204: {
               //#line 370 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new AxisStepReverse(getLeftIToken(), getRightIToken(),
                                        (IReverseStep)getRhsSym(1),
                                        (PredicateList)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 205:  AxisStep ::= ForwardStep PredicateList
            //
            case 205: {
               //#line 371 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new AxisStepForward(getLeftIToken(), getRightIToken(),
                                        (IForwardStep)getRhsSym(1),
                                        (PredicateList)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 206:  ForwardStep ::= ForwardAxis NodeTest
            //
            case 206: {
               //#line 373 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ForwardStep(getLeftIToken(), getRightIToken(),
                                    (ForwardAxis)getRhsSym(1),
                                    (INodeTest)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 207:  ForwardStep ::= AbbrevForwardStep
            //
            case 207:
                break;
            //
            // Rule 208:  ForwardAxis ::= child DoubleCOLON
            //
            case 208: {
               //#line 375 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ForwardAxis(getLeftIToken(), getRightIToken(),
                                    new ASTNodeToken(getRhsIToken(1)),
                                    new ASTNodeToken(getRhsIToken(2)),
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 209:  ForwardAxis ::= descendant DoubleCOLON
            //
            case 209: {
               //#line 376 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ForwardAxis(getLeftIToken(), getRightIToken(),
                                    (ASTNodeToken)null,
                                    new ASTNodeToken(getRhsIToken(2)),
                                    new ASTNodeToken(getRhsIToken(1)),
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 210:  ForwardAxis ::= attribute DoubleCOLON
            //
            case 210: {
               //#line 377 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ForwardAxis(getLeftIToken(), getRightIToken(),
                                    (ASTNodeToken)null,
                                    new ASTNodeToken(getRhsIToken(2)),
                                    (ASTNodeToken)null,
                                    new ASTNodeToken(getRhsIToken(1)),
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 211:  ForwardAxis ::= self DoubleCOLON
            //
            case 211: {
               //#line 378 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ForwardAxis(getLeftIToken(), getRightIToken(),
                                    (ASTNodeToken)null,
                                    new ASTNodeToken(getRhsIToken(2)),
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null,
                                    new ASTNodeToken(getRhsIToken(1)),
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 212:  ForwardAxis ::= descendant_or_self DoubleCOLON
            //
            case 212: {
               //#line 379 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ForwardAxis(getLeftIToken(), getRightIToken(),
                                    (ASTNodeToken)null,
                                    new ASTNodeToken(getRhsIToken(2)),
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null,
                                    new ASTNodeToken(getRhsIToken(1)),
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 213:  ForwardAxis ::= following_sibling DoubleCOLON
            //
            case 213: {
               //#line 380 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ForwardAxis(getLeftIToken(), getRightIToken(),
                                    (ASTNodeToken)null,
                                    new ASTNodeToken(getRhsIToken(2)),
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null,
                                    new ASTNodeToken(getRhsIToken(1)),
                                    (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 214:  ForwardAxis ::= following DoubleCOLON
            //
            case 214: {
               //#line 381 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ForwardAxis(getLeftIToken(), getRightIToken(),
                                    (ASTNodeToken)null,
                                    new ASTNodeToken(getRhsIToken(2)),
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null,
                                    new ASTNodeToken(getRhsIToken(1)))
                );
                break;
            }
            //
            // Rule 215:  AbbrevForwardStep ::= NodeTest
            //
            case 215:
                break;
            //
            // Rule 216:  AbbrevForwardStep ::= ATSIGN NodeTest
            //
            case 216: {
               //#line 384 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new AbbrevForwardStep(getLeftIToken(), getRightIToken(),
                                          new ASTNodeToken(getRhsIToken(1)),
                                          (INodeTest)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 217:  ReverseStep ::= ReverseAxis NodeTest
            //
            case 217: {
               //#line 386 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ReverseStep(getLeftIToken(), getRightIToken(),
                                    (ReverseAxis)getRhsSym(1),
                                    (INodeTest)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 218:  ReverseStep ::= AbbrevReverseStep
            //
            case 218:
                break;
            //
            // Rule 219:  ReverseAxis ::= parent DoubleCOLON
            //
            case 219: {
               //#line 389 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ReverseAxis(getLeftIToken(), getRightIToken(),
                                    new ASTNodeToken(getRhsIToken(1)),
                                    new ASTNodeToken(getRhsIToken(2)),
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 220:  ReverseAxis ::= ancestor DoubleCOLON
            //
            case 220: {
               //#line 390 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ReverseAxis(getLeftIToken(), getRightIToken(),
                                    (ASTNodeToken)null,
                                    new ASTNodeToken(getRhsIToken(2)),
                                    new ASTNodeToken(getRhsIToken(1)),
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 221:  ReverseAxis ::= preceding_sibling DoubleCOLON
            //
            case 221: {
               //#line 391 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ReverseAxis(getLeftIToken(), getRightIToken(),
                                    (ASTNodeToken)null,
                                    new ASTNodeToken(getRhsIToken(2)),
                                    (ASTNodeToken)null,
                                    new ASTNodeToken(getRhsIToken(1)),
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 222:  ReverseAxis ::= preceding DoubleCOLON
            //
            case 222: {
               //#line 392 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ReverseAxis(getLeftIToken(), getRightIToken(),
                                    (ASTNodeToken)null,
                                    new ASTNodeToken(getRhsIToken(2)),
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null,
                                    new ASTNodeToken(getRhsIToken(1)),
                                    (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 223:  ReverseAxis ::= ancestor_or_self DoubleCOLON
            //
            case 223: {
               //#line 393 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ReverseAxis(getLeftIToken(), getRightIToken(),
                                    (ASTNodeToken)null,
                                    new ASTNodeToken(getRhsIToken(2)),
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null,
                                    new ASTNodeToken(getRhsIToken(1)))
                );
                break;
            }
            //
            // Rule 224:  AbbrevReverseStep ::= DOTDOT
            //
            case 224: {
               //#line 395 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new AbbrevReverseStep(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 225:  NodeTest ::= KindTest
            //
            case 225:
                break;
            //
            // Rule 226:  NodeTest ::= NameTest
            //
            case 226:
                break;
            //
            // Rule 227:  NameTest ::= QName
            //
            case 227:
                break;
            //
            // Rule 228:  NameTest ::= Wildcard
            //
            case 228:
                break;
            //
            // Rule 229:  Wildcard ::= TIMES
            //
            case 229: {
               //#line 401 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new Wildcard0(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 230:  Wildcard ::= NCName COLON TIMES
            //
            case 230: {
               //#line 402 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new Wildcard1(getLeftIToken(), getRightIToken(),
                                  new ASTNodeToken(getRhsIToken(1)),
                                  new ASTNodeToken(getRhsIToken(2)),
                                  new ASTNodeToken(getRhsIToken(3)))
                );
                break;
            }
            //
            // Rule 231:  Wildcard ::= TIMES COLON NCName
            //
            case 231: {
               //#line 403 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new Wildcard2(getLeftIToken(), getRightIToken(),
                                  new ASTNodeToken(getRhsIToken(1)),
                                  new ASTNodeToken(getRhsIToken(2)),
                                  new ASTNodeToken(getRhsIToken(3)))
                );
                break;
            }
            //
            // Rule 232:  FilterExpr ::= PrimaryExpr PredicateList
            //
            case 232: {
               //#line 405 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new FilterExpr(getLeftIToken(), getRightIToken(),
                                   (IPrimaryExpr)getRhsSym(1),
                                   (PredicateList)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 233:  PredicateList ::= $Empty
            //
            case 233: {
               //#line 408 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new PredicateList(getLeftIToken(), getRightIToken(), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 234:  PredicateList ::= PredicateList Predicate
            //
            case 234: {
               //#line 408 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                ((PredicateList)getRhsSym(1)).add((Predicate)getRhsSym(2));
                break;
            }
            //
            // Rule 235:  Predicate ::= LEFTBRACKET Expr RIGHTBRACKET
            //
            case 235: {
               //#line 410 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new Predicate(getLeftIToken(), getRightIToken(),
                                  new ASTNodeToken(getRhsIToken(1)),
                                  (ExprSingleList)getRhsSym(2),
                                  new ASTNodeToken(getRhsIToken(3)))
                );
                break;
            }
            //
            // Rule 236:  PrimaryExpr ::= Literal
            //
            case 236:
                break;
            //
            // Rule 237:  PrimaryExpr ::= VarRef
            //
            case 237:
                break;
            //
            // Rule 238:  PrimaryExpr ::= ParenthesizedExpr
            //
            case 238:
                break;
            //
            // Rule 239:  PrimaryExpr ::= ContextItemExpr
            //
            case 239:
                break;
            //
            // Rule 240:  PrimaryExpr ::= FunctionCall
            //
            case 240:
                break;
            //
            // Rule 241:  PrimaryExpr ::= OrderedExpr
            //
            case 241:
                break;
            //
            // Rule 242:  PrimaryExpr ::= UnorderedExpr
            //
            case 242:
                break;
            //
            // Rule 243:  PrimaryExpr ::= Constructor
            //
            case 243:
                break;
            //
            // Rule 244:  Literal ::= NumericLiteral
            //
            case 244:
                break;
            //
            // Rule 245:  Literal ::= StringLiteral
            //
            case 245: {
               //#line 422 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new Literal(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 246:  NumericLiteral ::= IntegerLiteral
            //
            case 246: {
               //#line 424 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new NumericLiteral0(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 247:  NumericLiteral ::= DecimalLiteral
            //
            case 247: {
               //#line 425 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new NumericLiteral1(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 248:  NumericLiteral ::= DoubleLiteral
            //
            case 248: {
               //#line 426 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new NumericLiteral2(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 249:  VarRef ::= DOLLAR VarName
            //
            case 249: {
               //#line 428 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new VarRef(getLeftIToken(), getRightIToken(),
                               new ASTNodeToken(getRhsIToken(1)),
                               (IVarName)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 250:  VarName ::= QName
            //
            case 250:
                break;
            //
            // Rule 251:  ParenthesizedExpr ::= LEFTPAREN RIGHTPAREN
            //
            case 251: {
               //#line 432 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ParenthesizedExpr(getLeftIToken(), getRightIToken(),
                                          new ASTNodeToken(getRhsIToken(1)),
                                          new ASTNodeToken(getRhsIToken(2)),
                                          (ExprSingleList)null)
                );
                break;
            }
            //
            // Rule 252:  ParenthesizedExpr ::= LEFTPAREN Expr RIGHTPAREN
            //
            case 252: {
               //#line 432 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ParenthesizedExpr(getLeftIToken(), getRightIToken(),
                                          new ASTNodeToken(getRhsIToken(1)),
                                          new ASTNodeToken(getRhsIToken(3)),
                                          (ExprSingleList)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 253:  ContextItemExpr ::= DOT
            //
            case 253: {
               //#line 434 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ContextItemExpr(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 254:  OrderedExpr ::= ordered LEFTBRACE Expr RIGHTBRACE
            //
            case 254: {
               //#line 436 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new OrderedExpr(getLeftIToken(), getRightIToken(),
                                    new ASTNodeToken(getRhsIToken(1)),
                                    new ASTNodeToken(getRhsIToken(2)),
                                    (ExprSingleList)getRhsSym(3),
                                    new ASTNodeToken(getRhsIToken(4)))
                );
                break;
            }
            //
            // Rule 255:  UnorderedExpr ::= unordered LEFTBRACE Expr RIGHTBRACE
            //
            case 255: {
               //#line 438 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new UnorderedExpr(getLeftIToken(), getRightIToken(),
                                      new ASTNodeToken(getRhsIToken(1)),
                                      new ASTNodeToken(getRhsIToken(2)),
                                      (ExprSingleList)getRhsSym(3),
                                      new ASTNodeToken(getRhsIToken(4)))
                );
                break;
            }
            //
            // Rule 256:  FunctionCall ::= QName LEFTPAREN RIGHTPAREN
            //
            case 256: {
               //#line 440 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new FunctionCall0(getLeftIToken(), getRightIToken(),
                                      (IQName)getRhsSym(1),
                                      new ASTNodeToken(getRhsIToken(2)),
                                      new ASTNodeToken(getRhsIToken(3)))
                );
                break;
            }
            //
            // Rule 257:  FunctionCall ::= QName LEFTPAREN Expr RIGHTPAREN
            //
            case 257: {
               //#line 441 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new FunctionCall1(getLeftIToken(), getRightIToken(),
                                      (IQName)getRhsSym(1),
                                      new ASTNodeToken(getRhsIToken(2)),
                                      (ExprSingleList)getRhsSym(3),
                                      new ASTNodeToken(getRhsIToken(4)))
                );
                break;
            }
            //
            // Rule 258:  Constructor ::= DirectConstructor
            //
            case 258:
                break;
            //
            // Rule 259:  Constructor ::= ComputedConstructor
            //
            case 259:
                break;
            //
            // Rule 260:  DirectConstructor ::= DirElemConstructor
            //
            case 260:
                break;
            //
            // Rule 261:  DirectConstructor ::= DirCommentConstructor
            //
            case 261: {
               //#line 447 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new DirectConstructor(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 262:  DirectConstructor ::= DirPIConstructor
            //
            case 262:
                break;
            //
            // Rule 263:  DirElemConstructor ::= LESS QName DirAttributeList STEndMark
            //
            case 263: {
               //#line 451 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new DirElemConstructor0(getLeftIToken(), getRightIToken(),
                                            new ASTNodeToken(getRhsIToken(1)),
                                            (IQName)getRhsSym(2),
                                            (DirAttributeList)getRhsSym(3),
                                            new ASTNodeToken(getRhsIToken(4)))
                );
                break;
            }
            //
            // Rule 264:  DirElemConstructor ::= LESS QName DirAttributeList GREATER DirElemContentList ETStartMark QName GREATER
            //
            case 264: {
               //#line 452 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new DirElemConstructor1(getLeftIToken(), getRightIToken(),
                                            new ASTNodeToken(getRhsIToken(1)),
                                            (IQName)getRhsSym(2),
                                            (DirAttributeList)getRhsSym(3),
                                            new ASTNodeToken(getRhsIToken(4)),
                                            (DirElemContentList)getRhsSym(5),
                                            new ASTNodeToken(getRhsIToken(6)),
                                            (IQName)getRhsSym(7),
                                            new ASTNodeToken(getRhsIToken(8)))
                );
                break;
            }
            //
            // Rule 265:  DirElemContentList ::= $Empty
            //
            case 265: {
               //#line 453 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new DirElemContentList(getLeftIToken(), getRightIToken(), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 266:  DirElemContentList ::= DirElemContentList DirElemContent
            //
            case 266: {
               //#line 453 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                ((DirElemContentList)getRhsSym(1)).add((IDirElemContent)getRhsSym(2));
                break;
            }
            //
            // Rule 267:  DirAttribute ::= QName EQUAL DirAttributeValue
            //
            case 267: {
               //#line 456 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new DirAttribute(getLeftIToken(), getRightIToken(),
                                     (IQName)getRhsSym(1),
                                     new ASTNodeToken(getRhsIToken(2)),
                                     (IDirAttributeValue)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 268:  DirAttributeList ::= $Empty
            //
            case 268: {
               //#line 458 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new DirAttributeList(getLeftIToken(), getRightIToken(), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 269:  DirAttributeList ::= DirAttributeList DirAttribute
            //
            case 269: {
               //#line 458 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                ((DirAttributeList)getRhsSym(1)).add((DirAttribute)getRhsSym(2));
                break;
            }
            //
            // Rule 270:  DirAttributeValueQuot ::= EscapeQuot
            //
            case 270: {
               //#line 468 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new DirAttributeValueQuot(getLeftIToken(), getRightIToken(),
                                              new ASTNodeToken(getRhsIToken(1)),
                                              (ASTNodeToken)null,
                                              (ICommonContent)null,
                                              (ASTNodeToken)null,
                                              (DirAttributeValueQuotList)null,
                                              (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 271:  DirAttributeValueQuot ::= QuotAttrContentChar
            //
            case 271: {
               //#line 469 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new DirAttributeValueQuot(getLeftIToken(), getRightIToken(),
                                              (ASTNodeToken)null,
                                              new ASTNodeToken(getRhsIToken(1)),
                                              (ICommonContent)null,
                                              (ASTNodeToken)null,
                                              (DirAttributeValueQuotList)null,
                                              (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 272:  DirAttributeValueQuot ::= CommonContent
            //
            case 272: {
               //#line 470 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new DirAttributeValueQuot(getLeftIToken(), getRightIToken(),
                                              (ASTNodeToken)null,
                                              (ASTNodeToken)null,
                                              (ICommonContent)getRhsSym(1),
                                              (ASTNodeToken)null,
                                              (DirAttributeValueQuotList)null,
                                              (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 273:  DirAttributeValueApos ::= EscapeApos
            //
            case 273: {
               //#line 471 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new DirAttributeValueApos(getLeftIToken(), getRightIToken(),
                                              new ASTNodeToken(getRhsIToken(1)),
                                              (ASTNodeToken)null,
                                              (ICommonContent)null,
                                              (ASTNodeToken)null,
                                              (DirAttributeValueAposList)null,
                                              (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 274:  DirAttributeValueApos ::= AposAttrContentChar
            //
            case 274: {
               //#line 472 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new DirAttributeValueApos(getLeftIToken(), getRightIToken(),
                                              (ASTNodeToken)null,
                                              new ASTNodeToken(getRhsIToken(1)),
                                              (ICommonContent)null,
                                              (ASTNodeToken)null,
                                              (DirAttributeValueAposList)null,
                                              (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 275:  DirAttributeValueApos ::= CommonContent
            //
            case 275: {
               //#line 473 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new DirAttributeValueApos(getLeftIToken(), getRightIToken(),
                                              (ASTNodeToken)null,
                                              (ASTNodeToken)null,
                                              (ICommonContent)getRhsSym(1),
                                              (ASTNodeToken)null,
                                              (DirAttributeValueAposList)null,
                                              (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 276:  DirAttributeValueQuotList ::= $Empty
            //
            case 276: {
               //#line 474 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new DirAttributeValueQuotList(getLeftIToken(), getRightIToken(), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 277:  DirAttributeValueQuotList ::= DirAttributeValueQuotList DirAttributeValueQuot
            //
            case 277: {
               //#line 474 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                ((DirAttributeValueQuotList)getRhsSym(1)).add((IDirAttributeValueQuot)getRhsSym(2));
                break;
            }
            //
            // Rule 278:  DirAttributeValueAposList ::= $Empty
            //
            case 278: {
               //#line 475 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new DirAttributeValueAposList(getLeftIToken(), getRightIToken(), true /* left recursive */)
                );
                break;
            }
            //
            // Rule 279:  DirAttributeValueAposList ::= DirAttributeValueAposList DirAttributeValueApos
            //
            case 279: {
               //#line 475 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                ((DirAttributeValueAposList)getRhsSym(1)).add((IDirAttributeValueApos)getRhsSym(2));
                break;
            }
            //
            // Rule 280:  DirAttributeValue ::= DOUBLEQUOTE DirAttributeValueQuotList DOUBLEQUOTE
            //
            case 280: {
               //#line 477 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new DirAttributeValueQuot(getLeftIToken(), getRightIToken(),
                                              (ASTNodeToken)null,
                                              (ASTNodeToken)null,
                                              (ICommonContent)null,
                                              new ASTNodeToken(getRhsIToken(1)),
                                              (DirAttributeValueQuotList)getRhsSym(2),
                                              new ASTNodeToken(getRhsIToken(3)))
                );
                break;
            }
            //
            // Rule 281:  DirAttributeValue ::= SINGLEQUOTE DirAttributeValueAposList SINGLEQUOTE
            //
            case 281: {
               //#line 478 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new DirAttributeValueApos(getLeftIToken(), getRightIToken(),
                                              (ASTNodeToken)null,
                                              (ASTNodeToken)null,
                                              (ICommonContent)null,
                                              new ASTNodeToken(getRhsIToken(1)),
                                              (DirAttributeValueAposList)getRhsSym(2),
                                              new ASTNodeToken(getRhsIToken(3)))
                );
                break;
            }
            //
            // Rule 282:  DirElemContent ::= DirectConstructor
            //
            case 282:
                break;
            //
            // Rule 283:  DirElemContent ::= ElementContentChar
            //
            case 283: {
               //#line 486 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new DirElemContent0(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 284:  DirElemContent ::= CDataSection
            //
            case 284: {
               //#line 487 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new DirElemContent1(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 285:  DirElemContent ::= CommonContent
            //
            case 285:
                break;
            //
            // Rule 286:  CommonContent ::= PredefinedEntityRef
            //
            case 286: {
               //#line 491 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new CommonContent(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 287:  CommonContent ::= CharRef
            //
            case 287: {
               //#line 491 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new CommonContent(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 288:  CommonContent ::= DoubleLEFTBRACE
            //
            case 288: {
               //#line 491 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new CommonContent(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 289:  CommonContent ::= DoubleRIGHTBRACE
            //
            case 289: {
               //#line 491 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new CommonContent(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 290:  CommonContent ::= EnclosedExpr
            //
            case 290:
                break;
            //
            // Rule 291:  DirPIConstructor ::= PIST PITarget PIET
            //
            case 291: {
               //#line 499 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new DirPIConstructor(getLeftIToken(), getRightIToken(),
                                         new ASTNodeToken(getRhsIToken(1)),
                                         (IQName)getRhsSym(2),
                                         new ASTNodeToken(getRhsIToken(3)),
                                         (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 292:  DirPIConstructor ::= PIST PITarget DirPIContents PIET
            //
            case 292: {
               //#line 500 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new DirPIConstructor(getLeftIToken(), getRightIToken(),
                                         new ASTNodeToken(getRhsIToken(1)),
                                         (IQName)getRhsSym(2),
                                         new ASTNodeToken(getRhsIToken(4)),
                                         new ASTNodeToken(getRhsIToken(3)))
                );
                break;
            }
            //
            // Rule 293:  ComputedConstructor ::= CompDocConstructor
            //
            case 293:
                break;
            //
            // Rule 294:  ComputedConstructor ::= CompElemConstructor
            //
            case 294:
                break;
            //
            // Rule 295:  ComputedConstructor ::= CompAttrConstructor
            //
            case 295:
                break;
            //
            // Rule 296:  ComputedConstructor ::= CompTextConstructor
            //
            case 296:
                break;
            //
            // Rule 297:  ComputedConstructor ::= CompCommentConstructor
            //
            case 297:
                break;
            //
            // Rule 298:  ComputedConstructor ::= CompPIConstructor
            //
            case 298:
                break;
            //
            // Rule 299:  CompDocConstructor ::= document LEFTBRACE Expr RIGHTBRACE
            //
            case 299: {
               //#line 514 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new CompDocConstructor(getLeftIToken(), getRightIToken(),
                                           new ASTNodeToken(getRhsIToken(1)),
                                           new ASTNodeToken(getRhsIToken(2)),
                                           (ExprSingleList)getRhsSym(3),
                                           new ASTNodeToken(getRhsIToken(4)))
                );
                break;
            }
            //
            // Rule 300:  CompElemConstructor ::= element QName LEFTBRACE RIGHTBRACE
            //
            case 300: {
               //#line 517 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new CompElemConstructor(getLeftIToken(), getRightIToken(),
                                            new ASTNodeToken(getRhsIToken(1)),
                                            (IQName)getRhsSym(2),
                                            new ASTNodeToken(getRhsIToken(3)),
                                            new ASTNodeToken(getRhsIToken(4)),
                                            (ExprSingleList)null,
                                            (ExprSingleList)null,
                                            (ASTNodeToken)null,
                                            (ASTNodeToken)null,
                                            (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 301:  CompElemConstructor ::= element QName LEFTBRACE ContentExpr RIGHTBRACE
            //
            case 301: {
               //#line 518 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new CompElemConstructor(getLeftIToken(), getRightIToken(),
                                            new ASTNodeToken(getRhsIToken(1)),
                                            (IQName)getRhsSym(2),
                                            new ASTNodeToken(getRhsIToken(3)),
                                            new ASTNodeToken(getRhsIToken(5)),
                                            (ExprSingleList)getRhsSym(4),
                                            (ExprSingleList)null,
                                            (ASTNodeToken)null,
                                            (ASTNodeToken)null,
                                            (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 302:  CompElemConstructor ::= element LEFTBRACE Expr RIGHTBRACE LEFTBRACE RIGHTBRACE
            //
            case 302: {
               //#line 519 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new CompElemConstructor(getLeftIToken(), getRightIToken(),
                                            new ASTNodeToken(getRhsIToken(1)),
                                            (IQName)null,
                                            new ASTNodeToken(getRhsIToken(2)),
                                            new ASTNodeToken(getRhsIToken(4)),
                                            (ExprSingleList)null,
                                            (ExprSingleList)getRhsSym(3),
                                            new ASTNodeToken(getRhsIToken(5)),
                                            new ASTNodeToken(getRhsIToken(6)),
                                            (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 303:  CompElemConstructor ::= element LEFTBRACE Expr RIGHTBRACE LEFTBRACE ContentExpr RIGHTBRACE
            //
            case 303: {
               //#line 520 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new CompElemConstructor(getLeftIToken(), getRightIToken(),
                                            new ASTNodeToken(getRhsIToken(1)),
                                            (IQName)null,
                                            new ASTNodeToken(getRhsIToken(2)),
                                            new ASTNodeToken(getRhsIToken(4)),
                                            (ExprSingleList)getRhsSym(6),
                                            (ExprSingleList)getRhsSym(3),
                                            new ASTNodeToken(getRhsIToken(5)),
                                            (ASTNodeToken)null,
                                            new ASTNodeToken(getRhsIToken(7)))
                );
                break;
            }
            //
            // Rule 304:  ContentExpr ::= Expr
            //
            case 304:
                break;
            //
            // Rule 305:  CompAttrConstructor ::= attribute QName LEFTBRACE RIGHTBRACE
            //
            case 305: {
               //#line 525 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new CompAttrConstructor(getLeftIToken(), getRightIToken(),
                                            new ASTNodeToken(getRhsIToken(1)),
                                            (IQName)getRhsSym(2),
                                            new ASTNodeToken(getRhsIToken(3)),
                                            new ASTNodeToken(getRhsIToken(4)),
                                            (ExprSingleList)null,
                                            (ASTNodeToken)null,
                                            (ASTNodeToken)null,
                                            (ExprSingleList)null,
                                            (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 306:  CompAttrConstructor ::= attribute QName LEFTBRACE Expr RIGHTBRACE
            //
            case 306: {
               //#line 526 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new CompAttrConstructor(getLeftIToken(), getRightIToken(),
                                            new ASTNodeToken(getRhsIToken(1)),
                                            (IQName)getRhsSym(2),
                                            new ASTNodeToken(getRhsIToken(3)),
                                            new ASTNodeToken(getRhsIToken(5)),
                                            (ExprSingleList)getRhsSym(4),
                                            (ASTNodeToken)null,
                                            (ASTNodeToken)null,
                                            (ExprSingleList)null,
                                            (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 307:  CompAttrConstructor ::= attribute LEFTBRACE Expr RIGHTBRACE LEFTBRACE RIGHTBRACE
            //
            case 307: {
               //#line 527 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new CompAttrConstructor(getLeftIToken(), getRightIToken(),
                                            new ASTNodeToken(getRhsIToken(1)),
                                            (IQName)null,
                                            new ASTNodeToken(getRhsIToken(2)),
                                            new ASTNodeToken(getRhsIToken(4)),
                                            (ExprSingleList)getRhsSym(3),
                                            new ASTNodeToken(getRhsIToken(5)),
                                            new ASTNodeToken(getRhsIToken(6)),
                                            (ExprSingleList)null,
                                            (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 308:  CompAttrConstructor ::= attribute LEFTBRACE Expr RIGHTBRACE LEFTBRACE Expr RIGHTBRACE
            //
            case 308: {
               //#line 528 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new CompAttrConstructor(getLeftIToken(), getRightIToken(),
                                            new ASTNodeToken(getRhsIToken(1)),
                                            (IQName)null,
                                            new ASTNodeToken(getRhsIToken(2)),
                                            new ASTNodeToken(getRhsIToken(4)),
                                            (ExprSingleList)getRhsSym(3),
                                            new ASTNodeToken(getRhsIToken(5)),
                                            (ASTNodeToken)null,
                                            (ExprSingleList)getRhsSym(6),
                                            new ASTNodeToken(getRhsIToken(7)))
                );
                break;
            }
            //
            // Rule 309:  CompTextConstructor ::= text LEFTBRACE Expr RIGHTBRACE
            //
            case 309: {
               //#line 531 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new CompTextConstructor(getLeftIToken(), getRightIToken(),
                                            new ASTNodeToken(getRhsIToken(1)),
                                            new ASTNodeToken(getRhsIToken(2)),
                                            (ExprSingleList)getRhsSym(3),
                                            new ASTNodeToken(getRhsIToken(4)))
                );
                break;
            }
            //
            // Rule 310:  CompCommentConstructor ::= comment LEFTBRACE Expr RIGHTBRACE
            //
            case 310: {
               //#line 533 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new CompCommentConstructor(getLeftIToken(), getRightIToken(),
                                               new ASTNodeToken(getRhsIToken(1)),
                                               new ASTNodeToken(getRhsIToken(2)),
                                               (ExprSingleList)getRhsSym(3),
                                               new ASTNodeToken(getRhsIToken(4)))
                );
                break;
            }
            //
            // Rule 311:  CompPIConstructor ::= processing_instruction NCName LEFTBRACE RIGHTBRACE
            //
            case 311: {
               //#line 536 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new CompPIConstructor(getLeftIToken(), getRightIToken(),
                                          new ASTNodeToken(getRhsIToken(1)),
                                          new ASTNodeToken(getRhsIToken(2)),
                                          new ASTNodeToken(getRhsIToken(3)),
                                          new ASTNodeToken(getRhsIToken(4)),
                                          (ExprSingleList)null,
                                          (ASTNodeToken)null,
                                          (ASTNodeToken)null,
                                          (ExprSingleList)null,
                                          (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 312:  CompPIConstructor ::= processing_instruction NCName LEFTBRACE Expr RIGHTBRACE
            //
            case 312: {
               //#line 537 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new CompPIConstructor(getLeftIToken(), getRightIToken(),
                                          new ASTNodeToken(getRhsIToken(1)),
                                          new ASTNodeToken(getRhsIToken(2)),
                                          new ASTNodeToken(getRhsIToken(3)),
                                          new ASTNodeToken(getRhsIToken(5)),
                                          (ExprSingleList)getRhsSym(4),
                                          (ASTNodeToken)null,
                                          (ASTNodeToken)null,
                                          (ExprSingleList)null,
                                          (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 313:  CompPIConstructor ::= processing_instruction LEFTBRACE Expr RIGHTBRACE LEFTBRACE RIGHTBRACE
            //
            case 313: {
               //#line 538 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new CompPIConstructor(getLeftIToken(), getRightIToken(),
                                          new ASTNodeToken(getRhsIToken(1)),
                                          (ASTNodeToken)null,
                                          new ASTNodeToken(getRhsIToken(2)),
                                          new ASTNodeToken(getRhsIToken(4)),
                                          (ExprSingleList)getRhsSym(3),
                                          new ASTNodeToken(getRhsIToken(5)),
                                          new ASTNodeToken(getRhsIToken(6)),
                                          (ExprSingleList)null,
                                          (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 314:  CompPIConstructor ::= processing_instruction LEFTBRACE Expr RIGHTBRACE LEFTBRACE Expr RIGHTBRACE
            //
            case 314: {
               //#line 539 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new CompPIConstructor(getLeftIToken(), getRightIToken(),
                                          new ASTNodeToken(getRhsIToken(1)),
                                          (ASTNodeToken)null,
                                          new ASTNodeToken(getRhsIToken(2)),
                                          new ASTNodeToken(getRhsIToken(4)),
                                          (ExprSingleList)getRhsSym(3),
                                          new ASTNodeToken(getRhsIToken(5)),
                                          (ASTNodeToken)null,
                                          (ExprSingleList)getRhsSym(6),
                                          new ASTNodeToken(getRhsIToken(7)))
                );
                break;
            }
            //
            // Rule 315:  SingleType ::= AtomicType
            //
            case 315:
                break;
            //
            // Rule 316:  SingleType ::= AtomicType QUESTION
            //
            case 316: {
               //#line 541 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new SingleType(getLeftIToken(), getRightIToken(),
                                   (IQName)getRhsSym(1),
                                   new ASTNodeToken(getRhsIToken(2)))
                );
                break;
            }
            //
            // Rule 317:  TypeDeclaration ::= as SequenceType
            //
            case 317: {
               //#line 543 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new TypeDeclaration(getLeftIToken(), getRightIToken(),
                                        new ASTNodeToken(getRhsIToken(1)),
                                        (ISequenceType)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 318:  SequenceType ::= empty_sequence LEFTPAREN RIGHTPAREN
            //
            case 318: {
               //#line 545 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new SequenceTypeEmptySeq(getLeftIToken(), getRightIToken(),
                                             new ASTNodeToken(getRhsIToken(1)),
                                             new ASTNodeToken(getRhsIToken(2)),
                                             new ASTNodeToken(getRhsIToken(3)))
                );
                break;
            }
            //
            // Rule 319:  SequenceType ::= ItemType
            //
            case 319: {
               //#line 546 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new SequenceTypeItemType(getLeftIToken(), getRightIToken(),
                                             (IItemType)getRhsSym(1),
                                             (OccurrenceIndicator)null)
                );
                break;
            }
            //
            // Rule 320:  SequenceType ::= ItemType OccurrenceIndicator
            //
            case 320: {
               //#line 546 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new SequenceTypeItemType(getLeftIToken(), getRightIToken(),
                                             (IItemType)getRhsSym(1),
                                             (OccurrenceIndicator)getRhsSym(2))
                );
                break;
            }
            //
            // Rule 321:  OccurrenceIndicator ::= QUESTION
            //
            case 321: {
               //#line 548 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new OccurrenceIndicator(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 322:  OccurrenceIndicator ::= TIMES
            //
            case 322: {
               //#line 548 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new OccurrenceIndicator(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 323:  OccurrenceIndicator ::= PLUS
            //
            case 323: {
               //#line 548 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new OccurrenceIndicator(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 324:  ItemType ::= KindTest
            //
            case 324:
                break;
            //
            // Rule 325:  ItemType ::= AtomicType
            //
            case 325:
                break;
            //
            // Rule 326:  ItemType ::= item LEFTPAREN RIGHTPAREN
            //
            case 326: {
               //#line 552 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ItemType(getLeftIToken(), getRightIToken(),
                                 new ASTNodeToken(getRhsIToken(1)),
                                 new ASTNodeToken(getRhsIToken(2)),
                                 new ASTNodeToken(getRhsIToken(3)))
                );
                break;
            }
            //
            // Rule 327:  KindTest ::= DocumentTest
            //
            case 327:
                break;
            //
            // Rule 328:  KindTest ::= ElementTest
            //
            case 328:
                break;
            //
            // Rule 329:  KindTest ::= AttributeTest
            //
            case 329:
                break;
            //
            // Rule 330:  KindTest ::= SchemaElementTest
            //
            case 330:
                break;
            //
            // Rule 331:  KindTest ::= SchemaAttributeTest
            //
            case 331:
                break;
            //
            // Rule 332:  KindTest ::= PITest
            //
            case 332:
                break;
            //
            // Rule 333:  KindTest ::= CommentTest
            //
            case 333:
                break;
            //
            // Rule 334:  KindTest ::= TextTest
            //
            case 334:
                break;
            //
            // Rule 335:  KindTest ::= AnyKindTest
            //
            case 335:
                break;
            //
            // Rule 336:  AnyKindTest ::= node LEFTPAREN RIGHTPAREN
            //
            case 336: {
               //#line 564 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new AnyKindTest(getLeftIToken(), getRightIToken(),
                                    new ASTNodeToken(getRhsIToken(1)),
                                    new ASTNodeToken(getRhsIToken(2)),
                                    new ASTNodeToken(getRhsIToken(3)))
                );
                break;
            }
            //
            // Rule 337:  DocumentTest ::= document_node LEFTPAREN RIGHTPAREN
            //
            case 337: {
               //#line 567 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new DocumentTest(getLeftIToken(), getRightIToken(),
                                     new ASTNodeToken(getRhsIToken(1)),
                                     new ASTNodeToken(getRhsIToken(2)),
                                     new ASTNodeToken(getRhsIToken(3)),
                                     (ElementTest)null,
                                     (SchemaElementTest)null)
                );
                break;
            }
            //
            // Rule 338:  DocumentTest ::= document_node LEFTPAREN ElementTest RIGHTPAREN
            //
            case 338: {
               //#line 568 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new DocumentTest(getLeftIToken(), getRightIToken(),
                                     new ASTNodeToken(getRhsIToken(1)),
                                     new ASTNodeToken(getRhsIToken(2)),
                                     new ASTNodeToken(getRhsIToken(4)),
                                     (ElementTest)getRhsSym(3),
                                     (SchemaElementTest)null)
                );
                break;
            }
            //
            // Rule 339:  DocumentTest ::= document_node LEFTPAREN SchemaElementTest RIGHTPAREN
            //
            case 339: {
               //#line 569 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new DocumentTest(getLeftIToken(), getRightIToken(),
                                     new ASTNodeToken(getRhsIToken(1)),
                                     new ASTNodeToken(getRhsIToken(2)),
                                     new ASTNodeToken(getRhsIToken(4)),
                                     (ElementTest)null,
                                     (SchemaElementTest)getRhsSym(3))
                );
                break;
            }
            //
            // Rule 340:  TextTest ::= text LEFTPAREN RIGHTPAREN
            //
            case 340: {
               //#line 572 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new TextTest(getLeftIToken(), getRightIToken(),
                                 new ASTNodeToken(getRhsIToken(1)),
                                 new ASTNodeToken(getRhsIToken(2)),
                                 new ASTNodeToken(getRhsIToken(3)))
                );
                break;
            }
            //
            // Rule 341:  CommentTest ::= comment LEFTPAREN RIGHTPAREN
            //
            case 341: {
               //#line 574 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new CommentTest(getLeftIToken(), getRightIToken(),
                                    new ASTNodeToken(getRhsIToken(1)),
                                    new ASTNodeToken(getRhsIToken(2)),
                                    new ASTNodeToken(getRhsIToken(3)))
                );
                break;
            }
            //
            // Rule 342:  PITest ::= processing_instruction LEFTPAREN RIGHTPAREN
            //
            case 342: {
               //#line 576 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new PITest(getLeftIToken(), getRightIToken(),
                               new ASTNodeToken(getRhsIToken(1)),
                               new ASTNodeToken(getRhsIToken(2)),
                               new ASTNodeToken(getRhsIToken(3)),
                               (ASTNodeToken)null,
                               (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 343:  PITest ::= processing_instruction LEFTPAREN NCName RIGHTPAREN
            //
            case 343: {
               //#line 577 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new PITest(getLeftIToken(), getRightIToken(),
                               new ASTNodeToken(getRhsIToken(1)),
                               new ASTNodeToken(getRhsIToken(2)),
                               new ASTNodeToken(getRhsIToken(4)),
                               new ASTNodeToken(getRhsIToken(3)),
                               (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 344:  PITest ::= processing_instruction LEFTPAREN StringLiteral RIGHTPAREN
            //
            case 344: {
               //#line 578 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new PITest(getLeftIToken(), getRightIToken(),
                               new ASTNodeToken(getRhsIToken(1)),
                               new ASTNodeToken(getRhsIToken(2)),
                               new ASTNodeToken(getRhsIToken(4)),
                               (ASTNodeToken)null,
                               new ASTNodeToken(getRhsIToken(3)))
                );
                break;
            }
            //
            // Rule 345:  AttributeTest ::= attribute LEFTPAREN RIGHTPAREN
            //
            case 345: {
               //#line 580 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new AttributeTest(getLeftIToken(), getRightIToken(),
                                      new ASTNodeToken(getRhsIToken(1)),
                                      new ASTNodeToken(getRhsIToken(2)),
                                      new ASTNodeToken(getRhsIToken(3)),
                                      (IQName)null,
                                      (ASTNodeToken)null,
                                      (ASTNodeToken)null,
                                      (IQName)null)
                );
                break;
            }
            //
            // Rule 346:  AttributeTest ::= attribute LEFTPAREN AttributeName RIGHTPAREN
            //
            case 346: {
               //#line 581 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new AttributeTest(getLeftIToken(), getRightIToken(),
                                      new ASTNodeToken(getRhsIToken(1)),
                                      new ASTNodeToken(getRhsIToken(2)),
                                      new ASTNodeToken(getRhsIToken(4)),
                                      (IQName)getRhsSym(3),
                                      (ASTNodeToken)null,
                                      (ASTNodeToken)null,
                                      (IQName)null)
                );
                break;
            }
            //
            // Rule 347:  AttributeTest ::= attribute LEFTPAREN TIMES RIGHTPAREN
            //
            case 347: {
               //#line 582 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new AttributeTest(getLeftIToken(), getRightIToken(),
                                      new ASTNodeToken(getRhsIToken(1)),
                                      new ASTNodeToken(getRhsIToken(2)),
                                      new ASTNodeToken(getRhsIToken(4)),
                                      (IQName)null,
                                      new ASTNodeToken(getRhsIToken(3)),
                                      (ASTNodeToken)null,
                                      (IQName)null)
                );
                break;
            }
            //
            // Rule 348:  AttributeTest ::= attribute LEFTPAREN AttributeName COMMA TypeName RIGHTPAREN
            //
            case 348: {
               //#line 583 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new AttributeTest(getLeftIToken(), getRightIToken(),
                                      new ASTNodeToken(getRhsIToken(1)),
                                      new ASTNodeToken(getRhsIToken(2)),
                                      new ASTNodeToken(getRhsIToken(6)),
                                      (IQName)getRhsSym(3),
                                      (ASTNodeToken)null,
                                      new ASTNodeToken(getRhsIToken(4)),
                                      (IQName)getRhsSym(5))
                );
                break;
            }
            //
            // Rule 349:  AttributeTest ::= attribute LEFTPAREN TIMES COMMA TypeName RIGHTPAREN
            //
            case 349: {
               //#line 584 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new AttributeTest(getLeftIToken(), getRightIToken(),
                                      new ASTNodeToken(getRhsIToken(1)),
                                      new ASTNodeToken(getRhsIToken(2)),
                                      new ASTNodeToken(getRhsIToken(6)),
                                      (IQName)getRhsSym(5),
                                      new ASTNodeToken(getRhsIToken(3)),
                                      new ASTNodeToken(getRhsIToken(4)),
                                      (IQName)null)
                );
                break;
            }
            //
            // Rule 350:  SchemaAttributeTest ::= schema_attribute LEFTPAREN AttributeDeclaration RIGHTPAREN
            //
            case 350: {
               //#line 588 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new SchemaAttributeTest(getLeftIToken(), getRightIToken(),
                                            new ASTNodeToken(getRhsIToken(1)),
                                            new ASTNodeToken(getRhsIToken(2)),
                                            (IQName)getRhsSym(3),
                                            new ASTNodeToken(getRhsIToken(4)))
                );
                break;
            }
            //
            // Rule 351:  ElementTest ::= element LEFTPAREN RIGHTPAREN
            //
            case 351: {
               //#line 592 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ElementTest(getLeftIToken(), getRightIToken(),
                                    new ASTNodeToken(getRhsIToken(1)),
                                    new ASTNodeToken(getRhsIToken(2)),
                                    new ASTNodeToken(getRhsIToken(3)),
                                    (IQName)null,
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null,
                                    (IQName)null,
                                    (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 352:  ElementTest ::= element LEFTPAREN ElementName RIGHTPAREN
            //
            case 352: {
               //#line 593 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ElementTest(getLeftIToken(), getRightIToken(),
                                    new ASTNodeToken(getRhsIToken(1)),
                                    new ASTNodeToken(getRhsIToken(2)),
                                    new ASTNodeToken(getRhsIToken(4)),
                                    (IQName)getRhsSym(3),
                                    (ASTNodeToken)null,
                                    (ASTNodeToken)null,
                                    (IQName)null,
                                    (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 353:  ElementTest ::= element LEFTPAREN TIMES RIGHTPAREN
            //
            case 353: {
               //#line 594 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ElementTest(getLeftIToken(), getRightIToken(),
                                    new ASTNodeToken(getRhsIToken(1)),
                                    new ASTNodeToken(getRhsIToken(2)),
                                    new ASTNodeToken(getRhsIToken(4)),
                                    (IQName)null,
                                    new ASTNodeToken(getRhsIToken(3)),
                                    (ASTNodeToken)null,
                                    (IQName)null,
                                    (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 354:  ElementTest ::= element LEFTPAREN ElementName COMMA TypeName RIGHTPAREN
            //
            case 354: {
               //#line 595 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ElementTest(getLeftIToken(), getRightIToken(),
                                    new ASTNodeToken(getRhsIToken(1)),
                                    new ASTNodeToken(getRhsIToken(2)),
                                    new ASTNodeToken(getRhsIToken(6)),
                                    (IQName)getRhsSym(3),
                                    (ASTNodeToken)null,
                                    new ASTNodeToken(getRhsIToken(4)),
                                    (IQName)getRhsSym(5),
                                    (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 355:  ElementTest ::= element LEFTPAREN TIMES COMMA TypeName RIGHTPAREN
            //
            case 355: {
               //#line 596 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ElementTest(getLeftIToken(), getRightIToken(),
                                    new ASTNodeToken(getRhsIToken(1)),
                                    new ASTNodeToken(getRhsIToken(2)),
                                    new ASTNodeToken(getRhsIToken(6)),
                                    (IQName)getRhsSym(5),
                                    new ASTNodeToken(getRhsIToken(3)),
                                    new ASTNodeToken(getRhsIToken(4)),
                                    (IQName)null,
                                    (ASTNodeToken)null)
                );
                break;
            }
            //
            // Rule 356:  ElementTest ::= element LEFTPAREN ElementName COMMA TypeName QUESTION RIGHTPAREN
            //
            case 356: {
               //#line 597 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ElementTest(getLeftIToken(), getRightIToken(),
                                    new ASTNodeToken(getRhsIToken(1)),
                                    new ASTNodeToken(getRhsIToken(2)),
                                    new ASTNodeToken(getRhsIToken(7)),
                                    (IQName)getRhsSym(3),
                                    (ASTNodeToken)null,
                                    new ASTNodeToken(getRhsIToken(4)),
                                    (IQName)getRhsSym(5),
                                    new ASTNodeToken(getRhsIToken(6)))
                );
                break;
            }
            //
            // Rule 357:  ElementTest ::= element LEFTPAREN TIMES COMMA TypeName QUESTION RIGHTPAREN
            //
            case 357: {
               //#line 598 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new ElementTest(getLeftIToken(), getRightIToken(),
                                    new ASTNodeToken(getRhsIToken(1)),
                                    new ASTNodeToken(getRhsIToken(2)),
                                    new ASTNodeToken(getRhsIToken(7)),
                                    (IQName)getRhsSym(5),
                                    new ASTNodeToken(getRhsIToken(3)),
                                    new ASTNodeToken(getRhsIToken(4)),
                                    (IQName)null,
                                    new ASTNodeToken(getRhsIToken(6)))
                );
                break;
            }
            //
            // Rule 358:  SchemaElementTest ::= schema_element LEFTPAREN ElementDeclaration RIGHTPAREN
            //
            case 358: {
               //#line 602 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new SchemaElementTest(getLeftIToken(), getRightIToken(),
                                          new ASTNodeToken(getRhsIToken(1)),
                                          new ASTNodeToken(getRhsIToken(2)),
                                          (IQName)getRhsSym(3),
                                          new ASTNodeToken(getRhsIToken(4)))
                );
                break;
            }
            //
            // Rule 359:  QName ::= NCName
            //
            case 359: {
               //#line 608 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new QName0(getRhsIToken(1))
                );
                break;
            }
            //
            // Rule 360:  QName ::= NCName COLON NCName
            //
            case 360: {
               //#line 609 "E:/gsoc2009/imp_dev/ws_imp/com.google.code.exquery.parser/src/com/google/code/exquery/parser/./lpg/btParserTemplateF.gi"
                setResult(
                    new QName1(getLeftIToken(), getRightIToken(),
                               new ASTNodeToken(getRhsIToken(1)),
                               new ASTNodeToken(getRhsIToken(2)),
                               new ASTNodeToken(getRhsIToken(3)))
                );
                break;
            }
    
            default:
                break;
        }
        return;
    }
}

