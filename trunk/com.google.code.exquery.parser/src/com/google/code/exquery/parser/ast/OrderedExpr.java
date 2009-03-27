
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
 *<li>Rule 254:  OrderedExpr ::= ordered LEFTBRACE Expr RIGHTBRACE
 *</b>
 */
public class OrderedExpr extends ASTNode implements IOrderedExpr
{
    private ASTNodeToken _ordered;
    private ASTNodeToken _LEFTBRACE;
    private ExprSingleList _Expr;
    private ASTNodeToken _RIGHTBRACE;

    public ASTNodeToken getordered() { return _ordered; }
    public ASTNodeToken getLEFTBRACE() { return _LEFTBRACE; }
    public ExprSingleList getExpr() { return _Expr; }
    public ASTNodeToken getRIGHTBRACE() { return _RIGHTBRACE; }

    public OrderedExpr(IToken leftIToken, IToken rightIToken,
                       ASTNodeToken _ordered,
                       ASTNodeToken _LEFTBRACE,
                       ExprSingleList _Expr,
                       ASTNodeToken _RIGHTBRACE)
    {
        super(leftIToken, rightIToken);

        this._ordered = _ordered;
        ((ASTNode) _ordered).setParent(this);
        this._LEFTBRACE = _LEFTBRACE;
        ((ASTNode) _LEFTBRACE).setParent(this);
        this._Expr = _Expr;
        ((ASTNode) _Expr).setParent(this);
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
        list.add(_ordered);
        list.add(_LEFTBRACE);
        list.add(_Expr);
        list.add(_RIGHTBRACE);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof OrderedExpr)) return false;
        if (! super.equals(o)) return false;
        OrderedExpr other = (OrderedExpr) o;
        if (! _ordered.equals(other._ordered)) return false;
        if (! _LEFTBRACE.equals(other._LEFTBRACE)) return false;
        if (! _Expr.equals(other._Expr)) return false;
        if (! _RIGHTBRACE.equals(other._RIGHTBRACE)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_ordered.hashCode());
        hash = hash * 31 + (_LEFTBRACE.hashCode());
        hash = hash * 31 + (_Expr.hashCode());
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
            _ordered.accept(v);
            _LEFTBRACE.accept(v);
            _Expr.accept(v);
            _RIGHTBRACE.accept(v);
        }
        v.endVisit(this);
    }
}


