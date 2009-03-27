
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
 *<li>Rule 118:  OrderModifier ::= SortDirectionSegmentOpt EmptySortSegmentOpt CollationSegmentOpt
 *</b>
 */
public class OrderModifier extends ASTNode implements IOrderModifier
{
    private SortDirectionSegment _SortDirectionSegmentOpt;
    private EmptySortSegment _EmptySortSegmentOpt;
    private CollationSegmentOpt _CollationSegmentOpt;

    /**
     * The value returned by <b>getSortDirectionSegmentOpt</b> may be <b>null</b>
     */
    public SortDirectionSegment getSortDirectionSegmentOpt() { return _SortDirectionSegmentOpt; }
    /**
     * The value returned by <b>getEmptySortSegmentOpt</b> may be <b>null</b>
     */
    public EmptySortSegment getEmptySortSegmentOpt() { return _EmptySortSegmentOpt; }
    /**
     * The value returned by <b>getCollationSegmentOpt</b> may be <b>null</b>
     */
    public CollationSegmentOpt getCollationSegmentOpt() { return _CollationSegmentOpt; }

    public OrderModifier(IToken leftIToken, IToken rightIToken,
                         SortDirectionSegment _SortDirectionSegmentOpt,
                         EmptySortSegment _EmptySortSegmentOpt,
                         CollationSegmentOpt _CollationSegmentOpt)
    {
        super(leftIToken, rightIToken);

        this._SortDirectionSegmentOpt = _SortDirectionSegmentOpt;
        if (_SortDirectionSegmentOpt != null) ((ASTNode) _SortDirectionSegmentOpt).setParent(this);
        this._EmptySortSegmentOpt = _EmptySortSegmentOpt;
        if (_EmptySortSegmentOpt != null) ((ASTNode) _EmptySortSegmentOpt).setParent(this);
        this._CollationSegmentOpt = _CollationSegmentOpt;
        if (_CollationSegmentOpt != null) ((ASTNode) _CollationSegmentOpt).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_SortDirectionSegmentOpt);
        list.add(_EmptySortSegmentOpt);
        list.add(_CollationSegmentOpt);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof OrderModifier)) return false;
        if (! super.equals(o)) return false;
        OrderModifier other = (OrderModifier) o;
        if (_SortDirectionSegmentOpt == null)
            if (other._SortDirectionSegmentOpt != null) return false;
            else; // continue
        else if (! _SortDirectionSegmentOpt.equals(other._SortDirectionSegmentOpt)) return false;
        if (_EmptySortSegmentOpt == null)
            if (other._EmptySortSegmentOpt != null) return false;
            else; // continue
        else if (! _EmptySortSegmentOpt.equals(other._EmptySortSegmentOpt)) return false;
        if (_CollationSegmentOpt == null)
            if (other._CollationSegmentOpt != null) return false;
            else; // continue
        else if (! _CollationSegmentOpt.equals(other._CollationSegmentOpt)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_SortDirectionSegmentOpt == null ? 0 : _SortDirectionSegmentOpt.hashCode());
        hash = hash * 31 + (_EmptySortSegmentOpt == null ? 0 : _EmptySortSegmentOpt.hashCode());
        hash = hash * 31 + (_CollationSegmentOpt == null ? 0 : _CollationSegmentOpt.hashCode());
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
            if (_SortDirectionSegmentOpt != null) _SortDirectionSegmentOpt.accept(v);
            if (_EmptySortSegmentOpt != null) _EmptySortSegmentOpt.accept(v);
            if (_CollationSegmentOpt != null) _CollationSegmentOpt.accept(v);
        }
        v.endVisit(this);
    }
}


