
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
 *<li>Rule 172:  CastableExpr ::= CastExpr
 *</em>
 *<p>
 *<b>
 *<li>Rule 173:  CastableExpr ::= CastExpr castable as SequenceType
 *</b>
 */
public class CastableExpr extends ASTNode implements ICastableExpr
{
    private ICastExpr _CastExpr;
    private ASTNodeToken _castable;
    private ASTNodeToken _as;
    private ISequenceType _SequenceType;

    public ICastExpr getCastExpr() { return _CastExpr; }
    public ASTNodeToken getcastable() { return _castable; }
    public ASTNodeToken getas() { return _as; }
    public ISequenceType getSequenceType() { return _SequenceType; }

    public CastableExpr(IToken leftIToken, IToken rightIToken,
                        ICastExpr _CastExpr,
                        ASTNodeToken _castable,
                        ASTNodeToken _as,
                        ISequenceType _SequenceType)
    {
        super(leftIToken, rightIToken);

        this._CastExpr = _CastExpr;
        ((ASTNode) _CastExpr).setParent(this);
        this._castable = _castable;
        ((ASTNode) _castable).setParent(this);
        this._as = _as;
        ((ASTNode) _as).setParent(this);
        this._SequenceType = _SequenceType;
        ((ASTNode) _SequenceType).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_CastExpr);
        list.add(_castable);
        list.add(_as);
        list.add(_SequenceType);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof CastableExpr)) return false;
        if (! super.equals(o)) return false;
        CastableExpr other = (CastableExpr) o;
        if (! _CastExpr.equals(other._CastExpr)) return false;
        if (! _castable.equals(other._castable)) return false;
        if (! _as.equals(other._as)) return false;
        if (! _SequenceType.equals(other._SequenceType)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_CastExpr.hashCode());
        hash = hash * 31 + (_castable.hashCode());
        hash = hash * 31 + (_as.hashCode());
        hash = hash * 31 + (_SequenceType.hashCode());
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
            _CastExpr.accept(v);
            _castable.accept(v);
            _as.accept(v);
            _SequenceType.accept(v);
        }
        v.endVisit(this);
    }
}


