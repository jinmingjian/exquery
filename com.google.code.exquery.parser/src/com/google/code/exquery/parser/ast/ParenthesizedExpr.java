
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
 *<li>Rule 251:  ParenthesizedExpr ::= LEFTPAREN RIGHTPAREN
 *<li>Rule 252:  ParenthesizedExpr ::= LEFTPAREN Expr RIGHTPAREN
 *</b>
 */
public class ParenthesizedExpr extends ASTNode implements IParenthesizedExpr
{
    private ASTNodeToken _LEFTPAREN;
    private ASTNodeToken _RIGHTPAREN;
    private ExprSingleList _Expr;

    /**
     * The value returned by <b>getLEFTPAREN</b> may be <b>null</b>
     */
    public ASTNodeToken getLEFTPAREN() { return _LEFTPAREN; }
    /**
     * The value returned by <b>getRIGHTPAREN</b> may be <b>null</b>
     */
    public ASTNodeToken getRIGHTPAREN() { return _RIGHTPAREN; }
    /**
     * The value returned by <b>getExpr</b> may be <b>null</b>
     */
    public ExprSingleList getExpr() { return _Expr; }

    public ParenthesizedExpr(IToken leftIToken, IToken rightIToken,
                             ASTNodeToken _LEFTPAREN,
                             ASTNodeToken _RIGHTPAREN,
                             ExprSingleList _Expr)
    {
        super(leftIToken, rightIToken);

        this._LEFTPAREN = _LEFTPAREN;
        if (_LEFTPAREN != null) ((ASTNode) _LEFTPAREN).setParent(this);
        this._RIGHTPAREN = _RIGHTPAREN;
        if (_RIGHTPAREN != null) ((ASTNode) _RIGHTPAREN).setParent(this);
        this._Expr = _Expr;
        if (_Expr != null) ((ASTNode) _Expr).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_LEFTPAREN);
        list.add(_RIGHTPAREN);
        list.add(_Expr);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ParenthesizedExpr)) return false;
        if (! super.equals(o)) return false;
        ParenthesizedExpr other = (ParenthesizedExpr) o;
        if (_LEFTPAREN == null)
            if (other._LEFTPAREN != null) return false;
            else; // continue
        else if (! _LEFTPAREN.equals(other._LEFTPAREN)) return false;
        if (_RIGHTPAREN == null)
            if (other._RIGHTPAREN != null) return false;
            else; // continue
        else if (! _RIGHTPAREN.equals(other._RIGHTPAREN)) return false;
        if (_Expr == null)
            if (other._Expr != null) return false;
            else; // continue
        else if (! _Expr.equals(other._Expr)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_LEFTPAREN == null ? 0 : _LEFTPAREN.hashCode());
        hash = hash * 31 + (_RIGHTPAREN == null ? 0 : _RIGHTPAREN.hashCode());
        hash = hash * 31 + (_Expr == null ? 0 : _Expr.hashCode());
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
            if (_LEFTPAREN != null) _LEFTPAREN.accept(v);
            if (_RIGHTPAREN != null) _RIGHTPAREN.accept(v);
            if (_Expr != null) _Expr.accept(v);
        }
        v.endVisit(this);
    }
}


