
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
 *<li>Rule 166:  IntersectExceptExpr ::= IntersectExceptExpr intersect InstanceofExpr
 *<li>Rule 167:  IntersectExceptExpr ::= IntersectExceptExpr except InstanceofExpr
 *</b>
 */
public class IntersectExceptExpr extends ASTNode implements IIntersectExceptExpr
{
    private IIntersectExceptExpr _IntersectExceptExpr;
    private ASTNodeToken _intersect;
    private IInstanceofExpr _InstanceofExpr;
    private ASTNodeToken _except;

    /**
     * The value returned by <b>getIntersectExceptExpr</b> may be <b>null</b>
     */
    public IIntersectExceptExpr getIntersectExceptExpr() { return _IntersectExceptExpr; }
    /**
     * The value returned by <b>getintersect</b> may be <b>null</b>
     */
    public ASTNodeToken getintersect() { return _intersect; }
    /**
     * The value returned by <b>getInstanceofExpr</b> may be <b>null</b>
     */
    public IInstanceofExpr getInstanceofExpr() { return _InstanceofExpr; }
    /**
     * The value returned by <b>getexcept</b> may be <b>null</b>
     */
    public ASTNodeToken getexcept() { return _except; }

    public IntersectExceptExpr(IToken leftIToken, IToken rightIToken,
                               IIntersectExceptExpr _IntersectExceptExpr,
                               ASTNodeToken _intersect,
                               IInstanceofExpr _InstanceofExpr,
                               ASTNodeToken _except)
    {
        super(leftIToken, rightIToken);

        this._IntersectExceptExpr = _IntersectExceptExpr;
        if (_IntersectExceptExpr != null) ((ASTNode) _IntersectExceptExpr).setParent(this);
        this._intersect = _intersect;
        if (_intersect != null) ((ASTNode) _intersect).setParent(this);
        this._InstanceofExpr = _InstanceofExpr;
        if (_InstanceofExpr != null) ((ASTNode) _InstanceofExpr).setParent(this);
        this._except = _except;
        if (_except != null) ((ASTNode) _except).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_IntersectExceptExpr);
        list.add(_intersect);
        list.add(_InstanceofExpr);
        list.add(_except);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof IntersectExceptExpr)) return false;
        if (! super.equals(o)) return false;
        IntersectExceptExpr other = (IntersectExceptExpr) o;
        if (_IntersectExceptExpr == null)
            if (other._IntersectExceptExpr != null) return false;
            else; // continue
        else if (! _IntersectExceptExpr.equals(other._IntersectExceptExpr)) return false;
        if (_intersect == null)
            if (other._intersect != null) return false;
            else; // continue
        else if (! _intersect.equals(other._intersect)) return false;
        if (_InstanceofExpr == null)
            if (other._InstanceofExpr != null) return false;
            else; // continue
        else if (! _InstanceofExpr.equals(other._InstanceofExpr)) return false;
        if (_except == null)
            if (other._except != null) return false;
            else; // continue
        else if (! _except.equals(other._except)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_IntersectExceptExpr == null ? 0 : _IntersectExceptExpr.hashCode());
        hash = hash * 31 + (_intersect == null ? 0 : _intersect.hashCode());
        hash = hash * 31 + (_InstanceofExpr == null ? 0 : _InstanceofExpr.hashCode());
        hash = hash * 31 + (_except == null ? 0 : _except.hashCode());
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
            if (_IntersectExceptExpr != null) _IntersectExceptExpr.accept(v);
            if (_intersect != null) _intersect.accept(v);
            if (_InstanceofExpr != null) _InstanceofExpr.accept(v);
            if (_except != null) _except.accept(v);
        }
        v.endVisit(this);
    }
}


