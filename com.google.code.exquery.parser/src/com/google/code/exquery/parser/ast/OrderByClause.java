
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
 *<li>Rule 103:  OrderByClause ::= order by OrderSpecList
 *<li>Rule 104:  OrderByClause ::= stable order by OrderSpecList
 *</b>
 */
public class OrderByClause extends ASTNode implements IOrderByClause
{
    private ASTNodeToken _order;
    private ASTNodeToken _by;
    private OrderSpecList _OrderSpecList;
    private ASTNodeToken _stable;

    /**
     * The value returned by <b>getorder</b> may be <b>null</b>
     */
    public ASTNodeToken getorder() { return _order; }
    /**
     * The value returned by <b>getby</b> may be <b>null</b>
     */
    public ASTNodeToken getby() { return _by; }
    /**
     * The value returned by <b>getOrderSpecList</b> may be <b>null</b>
     */
    public OrderSpecList getOrderSpecList() { return _OrderSpecList; }
    /**
     * The value returned by <b>getstable</b> may be <b>null</b>
     */
    public ASTNodeToken getstable() { return _stable; }

    public OrderByClause(IToken leftIToken, IToken rightIToken,
                         ASTNodeToken _order,
                         ASTNodeToken _by,
                         OrderSpecList _OrderSpecList,
                         ASTNodeToken _stable)
    {
        super(leftIToken, rightIToken);

        this._order = _order;
        if (_order != null) ((ASTNode) _order).setParent(this);
        this._by = _by;
        if (_by != null) ((ASTNode) _by).setParent(this);
        this._OrderSpecList = _OrderSpecList;
        if (_OrderSpecList != null) ((ASTNode) _OrderSpecList).setParent(this);
        this._stable = _stable;
        if (_stable != null) ((ASTNode) _stable).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_order);
        list.add(_by);
        list.add(_OrderSpecList);
        list.add(_stable);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof OrderByClause)) return false;
        if (! super.equals(o)) return false;
        OrderByClause other = (OrderByClause) o;
        if (_order == null)
            if (other._order != null) return false;
            else; // continue
        else if (! _order.equals(other._order)) return false;
        if (_by == null)
            if (other._by != null) return false;
            else; // continue
        else if (! _by.equals(other._by)) return false;
        if (_OrderSpecList == null)
            if (other._OrderSpecList != null) return false;
            else; // continue
        else if (! _OrderSpecList.equals(other._OrderSpecList)) return false;
        if (_stable == null)
            if (other._stable != null) return false;
            else; // continue
        else if (! _stable.equals(other._stable)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_order == null ? 0 : _order.hashCode());
        hash = hash * 31 + (_by == null ? 0 : _by.hashCode());
        hash = hash * 31 + (_OrderSpecList == null ? 0 : _OrderSpecList.hashCode());
        hash = hash * 31 + (_stable == null ? 0 : _stable.hashCode());
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
            if (_order != null) _order.accept(v);
            if (_by != null) _by.accept(v);
            if (_OrderSpecList != null) _OrderSpecList.accept(v);
            if (_stable != null) _stable.accept(v);
        }
        v.endVisit(this);
    }
}


