
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
 *<li>Rule 174:  CastExpr ::= UnaryExpr
 *</em>
 *<p>
 *<b>
 *<li>Rule 175:  CastExpr ::= UnaryExpr cast as SingleType
 *</b>
 */
public class CastExpr extends ASTNode implements ICastExpr
{
    private IUnaryExpr _UnaryExpr;
    private ASTNodeToken _cast;
    private ASTNodeToken _as;
    private ISingleType _SingleType;

    public IUnaryExpr getUnaryExpr() { return _UnaryExpr; }
    public ASTNodeToken getcast() { return _cast; }
    public ASTNodeToken getas() { return _as; }
    public ISingleType getSingleType() { return _SingleType; }

    public CastExpr(IToken leftIToken, IToken rightIToken,
                    IUnaryExpr _UnaryExpr,
                    ASTNodeToken _cast,
                    ASTNodeToken _as,
                    ISingleType _SingleType)
    {
        super(leftIToken, rightIToken);

        this._UnaryExpr = _UnaryExpr;
        ((ASTNode) _UnaryExpr).setParent(this);
        this._cast = _cast;
        ((ASTNode) _cast).setParent(this);
        this._as = _as;
        ((ASTNode) _as).setParent(this);
        this._SingleType = _SingleType;
        ((ASTNode) _SingleType).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_UnaryExpr);
        list.add(_cast);
        list.add(_as);
        list.add(_SingleType);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof CastExpr)) return false;
        if (! super.equals(o)) return false;
        CastExpr other = (CastExpr) o;
        if (! _UnaryExpr.equals(other._UnaryExpr)) return false;
        if (! _cast.equals(other._cast)) return false;
        if (! _as.equals(other._as)) return false;
        if (! _SingleType.equals(other._SingleType)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_UnaryExpr.hashCode());
        hash = hash * 31 + (_cast.hashCode());
        hash = hash * 31 + (_as.hashCode());
        hash = hash * 31 + (_SingleType.hashCode());
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
            _UnaryExpr.accept(v);
            _cast.accept(v);
            _as.accept(v);
            _SingleType.accept(v);
        }
        v.endVisit(this);
    }
}


