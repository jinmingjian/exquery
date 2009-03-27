
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
 *<li>Rule 122:  QuantifiedExpr ::= some QuantifiedExprVarBindingList satisfies ExprSingle
 *</b>
 */
public class SomeQuantifiedExpr extends ASTNode implements IQuantifiedExpr
{
    private ASTNodeToken _some;
    private QuantifiedExprVarBindingList _QuantifiedExprVarBindingList;
    private ASTNodeToken _satisfies;
    private IExprSingle _ExprSingle;

    public ASTNodeToken getsome() { return _some; }
    public QuantifiedExprVarBindingList getQuantifiedExprVarBindingList() { return _QuantifiedExprVarBindingList; }
    public ASTNodeToken getsatisfies() { return _satisfies; }
    public IExprSingle getExprSingle() { return _ExprSingle; }

    public SomeQuantifiedExpr(IToken leftIToken, IToken rightIToken,
                              ASTNodeToken _some,
                              QuantifiedExprVarBindingList _QuantifiedExprVarBindingList,
                              ASTNodeToken _satisfies,
                              IExprSingle _ExprSingle)
    {
        super(leftIToken, rightIToken);

        this._some = _some;
        ((ASTNode) _some).setParent(this);
        this._QuantifiedExprVarBindingList = _QuantifiedExprVarBindingList;
        ((ASTNode) _QuantifiedExprVarBindingList).setParent(this);
        this._satisfies = _satisfies;
        ((ASTNode) _satisfies).setParent(this);
        this._ExprSingle = _ExprSingle;
        ((ASTNode) _ExprSingle).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_some);
        list.add(_QuantifiedExprVarBindingList);
        list.add(_satisfies);
        list.add(_ExprSingle);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof SomeQuantifiedExpr)) return false;
        if (! super.equals(o)) return false;
        SomeQuantifiedExpr other = (SomeQuantifiedExpr) o;
        if (! _some.equals(other._some)) return false;
        if (! _QuantifiedExprVarBindingList.equals(other._QuantifiedExprVarBindingList)) return false;
        if (! _satisfies.equals(other._satisfies)) return false;
        if (! _ExprSingle.equals(other._ExprSingle)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_some.hashCode());
        hash = hash * 31 + (_QuantifiedExprVarBindingList.hashCode());
        hash = hash * 31 + (_satisfies.hashCode());
        hash = hash * 31 + (_ExprSingle.hashCode());
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
            _some.accept(v);
            _QuantifiedExprVarBindingList.accept(v);
            _satisfies.accept(v);
            _ExprSingle.accept(v);
        }
        v.endVisit(this);
    }
}


