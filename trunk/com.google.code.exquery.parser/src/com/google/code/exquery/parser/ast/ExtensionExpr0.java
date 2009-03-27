
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
 *<li>Rule 191:  ExtensionExpr ::= PragmaList LEFTBRACE RIGHTBRACE
 *</b>
 */
public class ExtensionExpr0 extends ASTNode implements IExtensionExpr
{
    private PragmaList _PragmaList;
    private ASTNodeToken _LEFTBRACE;
    private ASTNodeToken _RIGHTBRACE;

    public PragmaList getPragmaList() { return _PragmaList; }
    public ASTNodeToken getLEFTBRACE() { return _LEFTBRACE; }
    public ASTNodeToken getRIGHTBRACE() { return _RIGHTBRACE; }

    public ExtensionExpr0(IToken leftIToken, IToken rightIToken,
                          PragmaList _PragmaList,
                          ASTNodeToken _LEFTBRACE,
                          ASTNodeToken _RIGHTBRACE)
    {
        super(leftIToken, rightIToken);

        this._PragmaList = _PragmaList;
        ((ASTNode) _PragmaList).setParent(this);
        this._LEFTBRACE = _LEFTBRACE;
        ((ASTNode) _LEFTBRACE).setParent(this);
        this._RIGHTBRACE = _RIGHTBRACE;
        ((ASTNode) _RIGHTBRACE).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_PragmaList);
        list.add(_LEFTBRACE);
        list.add(_RIGHTBRACE);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ExtensionExpr0)) return false;
        if (! super.equals(o)) return false;
        ExtensionExpr0 other = (ExtensionExpr0) o;
        if (! _PragmaList.equals(other._PragmaList)) return false;
        if (! _LEFTBRACE.equals(other._LEFTBRACE)) return false;
        if (! _RIGHTBRACE.equals(other._RIGHTBRACE)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_PragmaList.hashCode());
        hash = hash * 31 + (_LEFTBRACE.hashCode());
        hash = hash * 31 + (_RIGHTBRACE.hashCode());
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
            _PragmaList.accept(v);
            _LEFTBRACE.accept(v);
            _RIGHTBRACE.accept(v);
        }
        v.endVisit(this);
    }
}


