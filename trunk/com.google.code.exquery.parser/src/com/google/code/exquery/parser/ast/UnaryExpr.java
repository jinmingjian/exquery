
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
 *<li>Rule 176:  UnaryExpr ::= ValueExpr
 *</em>
 *<p>
 *<b>
 *<li>Rule 177:  UnaryExpr ::= PlusOrMinusList ValueExpr
 *</b>
 */
public class UnaryExpr extends ASTNode implements IUnaryExpr
{
    private PlusOrMinusList _PlusOrMinusList;
    private IValueExpr _ValueExpr;

    public PlusOrMinusList getPlusOrMinusList() { return _PlusOrMinusList; }
    public IValueExpr getValueExpr() { return _ValueExpr; }

    public UnaryExpr(IToken leftIToken, IToken rightIToken,
                     PlusOrMinusList _PlusOrMinusList,
                     IValueExpr _ValueExpr)
    {
        super(leftIToken, rightIToken);

        this._PlusOrMinusList = _PlusOrMinusList;
        ((ASTNode) _PlusOrMinusList).setParent(this);
        this._ValueExpr = _ValueExpr;
        ((ASTNode) _ValueExpr).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_PlusOrMinusList);
        list.add(_ValueExpr);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof UnaryExpr)) return false;
        if (! super.equals(o)) return false;
        UnaryExpr other = (UnaryExpr) o;
        if (! _PlusOrMinusList.equals(other._PlusOrMinusList)) return false;
        if (! _ValueExpr.equals(other._ValueExpr)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_PlusOrMinusList.hashCode());
        hash = hash * 31 + (_ValueExpr.hashCode());
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
            _PlusOrMinusList.accept(v);
            _ValueExpr.accept(v);
        }
        v.endVisit(this);
    }
}


