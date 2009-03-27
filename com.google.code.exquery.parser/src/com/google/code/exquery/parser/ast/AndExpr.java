
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
 *<li>Rule 134:  AndExpr ::= ComparisonExpr
 *</em>
 *<p>
 *<b>
 *<li>Rule 135:  AndExpr ::= AndExpr and ComparisonExpr
 *</b>
 */
public class AndExpr extends ASTNode implements IAndExpr
{
    private IAndExpr _AndExpr;
    private ASTNodeToken _and;
    private IComparisonExpr _ComparisonExpr;

    public IAndExpr getAndExpr() { return _AndExpr; }
    public ASTNodeToken getand() { return _and; }
    public IComparisonExpr getComparisonExpr() { return _ComparisonExpr; }

    public AndExpr(IToken leftIToken, IToken rightIToken,
                   IAndExpr _AndExpr,
                   ASTNodeToken _and,
                   IComparisonExpr _ComparisonExpr)
    {
        super(leftIToken, rightIToken);

        this._AndExpr = _AndExpr;
        ((ASTNode) _AndExpr).setParent(this);
        this._and = _and;
        ((ASTNode) _and).setParent(this);
        this._ComparisonExpr = _ComparisonExpr;
        ((ASTNode) _ComparisonExpr).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_AndExpr);
        list.add(_and);
        list.add(_ComparisonExpr);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof AndExpr)) return false;
        if (! super.equals(o)) return false;
        AndExpr other = (AndExpr) o;
        if (! _AndExpr.equals(other._AndExpr)) return false;
        if (! _and.equals(other._and)) return false;
        if (! _ComparisonExpr.equals(other._ComparisonExpr)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_AndExpr.hashCode());
        hash = hash * 31 + (_and.hashCode());
        hash = hash * 31 + (_ComparisonExpr.hashCode());
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
            _AndExpr.accept(v);
            _and.accept(v);
            _ComparisonExpr.accept(v);
        }
        v.endVisit(this);
    }
}


