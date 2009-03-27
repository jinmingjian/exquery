
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
 *<li>Rule 105:  OrderSpecList ::= OrderSpec
 *<li>Rule 106:  OrderSpecList ::= OrderSpecList COMMA OrderSpec
 *</b>
 */
public class OrderSpecList extends AbstractASTNodeList implements IOrderSpecList
{
    public OrderSpec getOrderSpecAt(int i) { return (OrderSpec) getElementAt(i); }

    public OrderSpecList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public OrderSpecList(OrderSpec _OrderSpec, boolean leftRecursive)
    {
        super((ASTNode) _OrderSpec, leftRecursive);
        ((ASTNode) _OrderSpec).setParent(this);
    }

    public void add(OrderSpec _OrderSpec)
    {
        super.add((ASTNode) _OrderSpec);
        ((ASTNode) _OrderSpec).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof OrderSpecList)) return false;
        if (! super.equals(o)) return false;
        OrderSpecList other = (OrderSpecList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            OrderSpec element = getOrderSpecAt(i);
            if (! element.equals(other.getOrderSpecAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getOrderSpecAt(i).hashCode());
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
            for (int i = 0; i < size(); i++)
            {
                OrderSpec element = getOrderSpecAt(i);
                if (! v.preVisit(element)) continue;
                element.enter(v);
                v.postVisit(element);
            }
        }
        v.endVisit(this);
    }
}


