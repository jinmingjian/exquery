
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

package com.google.code.exquery.parser.ast;

import lpg.runtime.*;

import org.eclipse.imp.parser.IParser;
import java.util.Hashtable;
import java.util.Stack;

/**
 *<b>
 *<li>Rule 337:  DocumentTest ::= document_node LEFTPAREN RIGHTPAREN
 *<li>Rule 338:  DocumentTest ::= document_node LEFTPAREN ElementTest RIGHTPAREN
 *<li>Rule 339:  DocumentTest ::= document_node LEFTPAREN SchemaElementTest RIGHTPAREN
 *</b>
 */
public class DocumentTest extends ASTNode implements IDocumentTest
{
    private ASTNodeToken _document_node;
    private ASTNodeToken _LEFTPAREN;
    private ASTNodeToken _RIGHTPAREN;
    private ElementTest _ElementTest;
    private SchemaElementTest _SchemaElementTest;

    /**
     * The value returned by <b>getdocument_node</b> may be <b>null</b>
     */
    public ASTNodeToken getdocument_node() { return _document_node; }
    public ASTNodeToken getLEFTPAREN() { return _LEFTPAREN; }
    /**
     * The value returned by <b>getRIGHTPAREN</b> may be <b>null</b>
     */
    public ASTNodeToken getRIGHTPAREN() { return _RIGHTPAREN; }
    /**
     * The value returned by <b>getElementTest</b> may be <b>null</b>
     */
    public ElementTest getElementTest() { return _ElementTest; }
    /**
     * The value returned by <b>getSchemaElementTest</b> may be <b>null</b>
     */
    public SchemaElementTest getSchemaElementTest() { return _SchemaElementTest; }

    public DocumentTest(IToken leftIToken, IToken rightIToken,
                        ASTNodeToken _document_node,
                        ASTNodeToken _LEFTPAREN,
                        ASTNodeToken _RIGHTPAREN,
                        ElementTest _ElementTest,
                        SchemaElementTest _SchemaElementTest)
    {
        super(leftIToken, rightIToken);

        this._document_node = _document_node;
        if (_document_node != null) ((ASTNode) _document_node).setParent(this);
        this._LEFTPAREN = _LEFTPAREN;
        ((ASTNode) _LEFTPAREN).setParent(this);
        this._RIGHTPAREN = _RIGHTPAREN;
        if (_RIGHTPAREN != null) ((ASTNode) _RIGHTPAREN).setParent(this);
        this._ElementTest = _ElementTest;
        if (_ElementTest != null) ((ASTNode) _ElementTest).setParent(this);
        this._SchemaElementTest = _SchemaElementTest;
        if (_SchemaElementTest != null) ((ASTNode) _SchemaElementTest).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_document_node);
        list.add(_LEFTPAREN);
        list.add(_RIGHTPAREN);
        list.add(_ElementTest);
        list.add(_SchemaElementTest);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof DocumentTest)) return false;
        if (! super.equals(o)) return false;
        DocumentTest other = (DocumentTest) o;
        if (_document_node == null)
            if (other._document_node != null) return false;
            else; // continue
        else if (! _document_node.equals(other._document_node)) return false;
        if (! _LEFTPAREN.equals(other._LEFTPAREN)) return false;
        if (_RIGHTPAREN == null)
            if (other._RIGHTPAREN != null) return false;
            else; // continue
        else if (! _RIGHTPAREN.equals(other._RIGHTPAREN)) return false;
        if (_ElementTest == null)
            if (other._ElementTest != null) return false;
            else; // continue
        else if (! _ElementTest.equals(other._ElementTest)) return false;
        if (_SchemaElementTest == null)
            if (other._SchemaElementTest != null) return false;
            else; // continue
        else if (! _SchemaElementTest.equals(other._SchemaElementTest)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_document_node == null ? 0 : _document_node.hashCode());
        hash = hash * 31 + (_LEFTPAREN.hashCode());
        hash = hash * 31 + (_RIGHTPAREN == null ? 0 : _RIGHTPAREN.hashCode());
        hash = hash * 31 + (_ElementTest == null ? 0 : _ElementTest.hashCode());
        hash = hash * 31 + (_SchemaElementTest == null ? 0 : _SchemaElementTest.hashCode());
        return hash;
    }

    public void accept(IAstVisitor v)
    {
        if (! v.preVisit(this)) return;
        enter((Visitor) v);
        v.postVisit(this);
    }

    public void enter(Visitor v)
    {
        boolean checkChildren = v.visit(this);
        if (checkChildren)
        {
            if (_document_node != null) _document_node.accept(v);
            _LEFTPAREN.accept(v);
            if (_RIGHTPAREN != null) _RIGHTPAREN.accept(v);
            if (_ElementTest != null) _ElementTest.accept(v);
            if (_SchemaElementTest != null) _SchemaElementTest.accept(v);
        }
        v.endVisit(this);
    }
}


