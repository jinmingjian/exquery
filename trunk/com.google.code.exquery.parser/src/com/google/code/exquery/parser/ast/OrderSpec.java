
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
 *<li>Rule 107:  OrderSpec ::= ExprSingle OrderModifier
 *</b>
 */
public class OrderSpec extends ASTNode implements IOrderSpec
{
    private IExprSingle _ExprSingle;
    private OrderModifier _OrderModifier;

    public IExprSingle getExprSingle() { return _ExprSingle; }
    public OrderModifier getOrderModifier() { return _OrderModifier; }

    public OrderSpec(IToken leftIToken, IToken rightIToken,
                     IExprSingle _ExprSingle,
                     OrderModifier _OrderModifier)
    {
        super(leftIToken, rightIToken);

        this._ExprSingle = _ExprSingle;
        ((ASTNode) _ExprSingle).setParent(this);
        this._OrderModifier = _OrderModifier;
        ((ASTNode) _OrderModifier).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_ExprSingle);
        list.add(_OrderModifier);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof OrderSpec)) return false;
        if (! super.equals(o)) return false;
        OrderSpec other = (OrderSpec) o;
        if (! _ExprSingle.equals(other._ExprSingle)) return false;
        if (! _OrderModifier.equals(other._OrderModifier)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_ExprSingle.hashCode());
        hash = hash * 31 + (_OrderModifier.hashCode());
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
            _ExprSingle.accept(v);
            _OrderModifier.accept(v);
        }
        v.endVisit(this);
    }
}


