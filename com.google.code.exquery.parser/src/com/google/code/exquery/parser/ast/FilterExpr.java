
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
 *<li>Rule 232:  FilterExpr ::= PrimaryExpr PredicateList
 *</b>
 */
public class FilterExpr extends ASTNode implements IFilterExpr
{
    private IPrimaryExpr _PrimaryExpr;
    private PredicateList _PredicateList;

    public IPrimaryExpr getPrimaryExpr() { return _PrimaryExpr; }
    public PredicateList getPredicateList() { return _PredicateList; }

    public FilterExpr(IToken leftIToken, IToken rightIToken,
                      IPrimaryExpr _PrimaryExpr,
                      PredicateList _PredicateList)
    {
        super(leftIToken, rightIToken);

        this._PrimaryExpr = _PrimaryExpr;
        ((ASTNode) _PrimaryExpr).setParent(this);
        this._PredicateList = _PredicateList;
        ((ASTNode) _PredicateList).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_PrimaryExpr);
        list.add(_PredicateList);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof FilterExpr)) return false;
        if (! super.equals(o)) return false;
        FilterExpr other = (FilterExpr) o;
        if (! _PrimaryExpr.equals(other._PrimaryExpr)) return false;
        if (! _PredicateList.equals(other._PredicateList)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_PrimaryExpr.hashCode());
        hash = hash * 31 + (_PredicateList.hashCode());
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
            _PrimaryExpr.accept(v);
            _PredicateList.accept(v);
        }
        v.endVisit(this);
    }
}


