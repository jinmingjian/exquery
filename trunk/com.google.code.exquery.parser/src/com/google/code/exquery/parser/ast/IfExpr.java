
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
 *<li>Rule 131:  IfExpr ::= if LEFTPAREN Expr RIGHTPAREN then ExprSingle else ExprSingle
 *</b>
 */
public class IfExpr extends ASTNode implements IIfExpr
{
    private ASTNodeToken _if;
    private ASTNodeToken _LEFTPAREN;
    private ExprSingleList _Expr;
    private ASTNodeToken _RIGHTPAREN;
    private ASTNodeToken _then;
    private IExprSingle _ExprSingle;
    private ASTNodeToken _else;
    private IExprSingle _ExprSingle8;

    public ASTNodeToken getif() { return _if; }
    public ASTNodeToken getLEFTPAREN() { return _LEFTPAREN; }
    public ExprSingleList getExpr() { return _Expr; }
    public ASTNodeToken getRIGHTPAREN() { return _RIGHTPAREN; }
    public ASTNodeToken getthen() { return _then; }
    public IExprSingle getExprSingle() { return _ExprSingle; }
    public ASTNodeToken getelse() { return _else; }
    public IExprSingle getExprSingle8() { return _ExprSingle8; }

    public IfExpr(IToken leftIToken, IToken rightIToken,
                  ASTNodeToken _if,
                  ASTNodeToken _LEFTPAREN,
                  ExprSingleList _Expr,
                  ASTNodeToken _RIGHTPAREN,
                  ASTNodeToken _then,
                  IExprSingle _ExprSingle,
                  ASTNodeToken _else,
                  IExprSingle _ExprSingle8)
    {
        super(leftIToken, rightIToken);

        this._if = _if;
        ((ASTNode) _if).setParent(this);
        this._LEFTPAREN = _LEFTPAREN;
        ((ASTNode) _LEFTPAREN).setParent(this);
        this._Expr = _Expr;
        ((ASTNode) _Expr).setParent(this);
        this._RIGHTPAREN = _RIGHTPAREN;
        ((ASTNode) _RIGHTPAREN).setParent(this);
        this._then = _then;
        ((ASTNode) _then).setParent(this);
        this._ExprSingle = _ExprSingle;
        ((ASTNode) _ExprSingle).setParent(this);
        this._else = _else;
        ((ASTNode) _else).setParent(this);
        this._ExprSingle8 = _ExprSingle8;
        ((ASTNode) _ExprSingle8).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_if);
        list.add(_LEFTPAREN);
        list.add(_Expr);
        list.add(_RIGHTPAREN);
        list.add(_then);
        list.add(_ExprSingle);
        list.add(_else);
        list.add(_ExprSingle8);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof IfExpr)) return false;
        if (! super.equals(o)) return false;
        IfExpr other = (IfExpr) o;
        if (! _if.equals(other._if)) return false;
        if (! _LEFTPAREN.equals(other._LEFTPAREN)) return false;
        if (! _Expr.equals(other._Expr)) return false;
        if (! _RIGHTPAREN.equals(other._RIGHTPAREN)) return false;
        if (! _then.equals(other._then)) return false;
        if (! _ExprSingle.equals(other._ExprSingle)) return false;
        if (! _else.equals(other._else)) return false;
        if (! _ExprSingle8.equals(other._ExprSingle8)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_if.hashCode());
        hash = hash * 31 + (_LEFTPAREN.hashCode());
        hash = hash * 31 + (_Expr.hashCode());
        hash = hash * 31 + (_RIGHTPAREN.hashCode());
        hash = hash * 31 + (_then.hashCode());
        hash = hash * 31 + (_ExprSingle.hashCode());
        hash = hash * 31 + (_else.hashCode());
        hash = hash * 31 + (_ExprSingle8.hashCode());
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
            _if.accept(v);
            _LEFTPAREN.accept(v);
            _Expr.accept(v);
            _RIGHTPAREN.accept(v);
            _then.accept(v);
            _ExprSingle.accept(v);
            _else.accept(v);
            _ExprSingle8.accept(v);
        }
        v.endVisit(this);
    }
}


