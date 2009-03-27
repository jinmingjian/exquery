
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
 *<em>
 *<li>Rule 152:  RangeExpr ::= AdditiveExpr
 *</em>
 *<p>
 *<b>
 *<li>Rule 153:  RangeExpr ::= AdditiveExpr to AdditiveExpr
 *</b>
 */
public class RangeExpr extends ASTNode implements IRangeExpr
{
    private IAdditiveExpr _AdditiveExpr;
    private ASTNodeToken _to;
    private IAdditiveExpr _AdditiveExpr3;

    public IAdditiveExpr getAdditiveExpr() { return _AdditiveExpr; }
    public ASTNodeToken getto() { return _to; }
    public IAdditiveExpr getAdditiveExpr3() { return _AdditiveExpr3; }

    public RangeExpr(IToken leftIToken, IToken rightIToken,
                     IAdditiveExpr _AdditiveExpr,
                     ASTNodeToken _to,
                     IAdditiveExpr _AdditiveExpr3)
    {
        super(leftIToken, rightIToken);

        this._AdditiveExpr = _AdditiveExpr;
        ((ASTNode) _AdditiveExpr).setParent(this);
        this._to = _to;
        ((ASTNode) _to).setParent(this);
        this._AdditiveExpr3 = _AdditiveExpr3;
        ((ASTNode) _AdditiveExpr3).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_AdditiveExpr);
        list.add(_to);
        list.add(_AdditiveExpr3);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof RangeExpr)) return false;
        if (! super.equals(o)) return false;
        RangeExpr other = (RangeExpr) o;
        if (! _AdditiveExpr.equals(other._AdditiveExpr)) return false;
        if (! _to.equals(other._to)) return false;
        if (! _AdditiveExpr3.equals(other._AdditiveExpr3)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_AdditiveExpr.hashCode());
        hash = hash * 31 + (_to.hashCode());
        hash = hash * 31 + (_AdditiveExpr3.hashCode());
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
            _AdditiveExpr.accept(v);
            _to.accept(v);
            _AdditiveExpr3.accept(v);
        }
        v.endVisit(this);
    }
}


