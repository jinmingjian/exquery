
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
 *<li>Rule 163:  UnionExpr ::= UnionExpr union IntersectExceptExpr
 *<li>Rule 164:  UnionExpr ::= UnionExpr ORBar IntersectExceptExpr
 *</b>
 */
public class UnionExpr extends ASTNode implements IUnionExpr
{
    private IUnionExpr _UnionExpr;
    private ASTNodeToken _union;
    private IIntersectExceptExpr _IntersectExceptExpr;
    private ASTNodeToken _ORBar;

    /**
     * The value returned by <b>getUnionExpr</b> may be <b>null</b>
     */
    public IUnionExpr getUnionExpr() { return _UnionExpr; }
    /**
     * The value returned by <b>getunion</b> may be <b>null</b>
     */
    public ASTNodeToken getunion() { return _union; }
    /**
     * The value returned by <b>getIntersectExceptExpr</b> may be <b>null</b>
     */
    public IIntersectExceptExpr getIntersectExceptExpr() { return _IntersectExceptExpr; }
    /**
     * The value returned by <b>getORBar</b> may be <b>null</b>
     */
    public ASTNodeToken getORBar() { return _ORBar; }

    public UnionExpr(IToken leftIToken, IToken rightIToken,
                     IUnionExpr _UnionExpr,
                     ASTNodeToken _union,
                     IIntersectExceptExpr _IntersectExceptExpr,
                     ASTNodeToken _ORBar)
    {
        super(leftIToken, rightIToken);

        this._UnionExpr = _UnionExpr;
        if (_UnionExpr != null) ((ASTNode) _UnionExpr).setParent(this);
        this._union = _union;
        if (_union != null) ((ASTNode) _union).setParent(this);
        this._IntersectExceptExpr = _IntersectExceptExpr;
        if (_IntersectExceptExpr != null) ((ASTNode) _IntersectExceptExpr).setParent(this);
        this._ORBar = _ORBar;
        if (_ORBar != null) ((ASTNode) _ORBar).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_UnionExpr);
        list.add(_union);
        list.add(_IntersectExceptExpr);
        list.add(_ORBar);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof UnionExpr)) return false;
        if (! super.equals(o)) return false;
        UnionExpr other = (UnionExpr) o;
        if (_UnionExpr == null)
            if (other._UnionExpr != null) return false;
            else; // continue
        else if (! _UnionExpr.equals(other._UnionExpr)) return false;
        if (_union == null)
            if (other._union != null) return false;
            else; // continue
        else if (! _union.equals(other._union)) return false;
        if (_IntersectExceptExpr == null)
            if (other._IntersectExceptExpr != null) return false;
            else; // continue
        else if (! _IntersectExceptExpr.equals(other._IntersectExceptExpr)) return false;
        if (_ORBar == null)
            if (other._ORBar != null) return false;
            else; // continue
        else if (! _ORBar.equals(other._ORBar)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_UnionExpr == null ? 0 : _UnionExpr.hashCode());
        hash = hash * 31 + (_union == null ? 0 : _union.hashCode());
        hash = hash * 31 + (_IntersectExceptExpr == null ? 0 : _IntersectExceptExpr.hashCode());
        hash = hash * 31 + (_ORBar == null ? 0 : _ORBar.hashCode());
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
            if (_UnionExpr != null) _UnionExpr.accept(v);
            if (_union != null) _union.accept(v);
            if (_IntersectExceptExpr != null) _IntersectExceptExpr.accept(v);
            if (_ORBar != null) _ORBar.accept(v);
        }
        v.endVisit(this);
    }
}


