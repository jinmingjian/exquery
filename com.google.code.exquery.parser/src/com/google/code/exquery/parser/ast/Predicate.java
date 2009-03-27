
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
 *<li>Rule 235:  Predicate ::= LEFTBRACKET Expr RIGHTBRACKET
 *</b>
 */
public class Predicate extends ASTNode implements IPredicate
{
    private ASTNodeToken _LEFTBRACKET;
    private ExprSingleList _Expr;
    private ASTNodeToken _RIGHTBRACKET;

    public ASTNodeToken getLEFTBRACKET() { return _LEFTBRACKET; }
    public ExprSingleList getExpr() { return _Expr; }
    public ASTNodeToken getRIGHTBRACKET() { return _RIGHTBRACKET; }

    public Predicate(IToken leftIToken, IToken rightIToken,
                     ASTNodeToken _LEFTBRACKET,
                     ExprSingleList _Expr,
                     ASTNodeToken _RIGHTBRACKET)
    {
        super(leftIToken, rightIToken);

        this._LEFTBRACKET = _LEFTBRACKET;
        ((ASTNode) _LEFTBRACKET).setParent(this);
        this._Expr = _Expr;
        ((ASTNode) _Expr).setParent(this);
        this._RIGHTBRACKET = _RIGHTBRACKET;
        ((ASTNode) _RIGHTBRACKET).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_LEFTBRACKET);
        list.add(_Expr);
        list.add(_RIGHTBRACKET);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof Predicate)) return false;
        if (! super.equals(o)) return false;
        Predicate other = (Predicate) o;
        if (! _LEFTBRACKET.equals(other._LEFTBRACKET)) return false;
        if (! _Expr.equals(other._Expr)) return false;
        if (! _RIGHTBRACKET.equals(other._RIGHTBRACKET)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_LEFTBRACKET.hashCode());
        hash = hash * 31 + (_Expr.hashCode());
        hash = hash * 31 + (_RIGHTBRACKET.hashCode());
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
            _LEFTBRACKET.accept(v);
            _Expr.accept(v);
            _RIGHTBRACKET.accept(v);
        }
        v.endVisit(this);
    }
}


