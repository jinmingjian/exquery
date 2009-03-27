
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
 *<li>Rule 170:  TreatExpr ::= CastableExpr
 *</em>
 *<p>
 *<b>
 *<li>Rule 171:  TreatExpr ::= CastableExpr treat as SequenceType
 *</b>
 */
public class TreatExpr extends ASTNode implements ITreatExpr
{
    private ICastableExpr _CastableExpr;
    private ASTNodeToken _treat;
    private ASTNodeToken _as;
    private ISequenceType _SequenceType;

    public ICastableExpr getCastableExpr() { return _CastableExpr; }
    public ASTNodeToken gettreat() { return _treat; }
    public ASTNodeToken getas() { return _as; }
    public ISequenceType getSequenceType() { return _SequenceType; }

    public TreatExpr(IToken leftIToken, IToken rightIToken,
                     ICastableExpr _CastableExpr,
                     ASTNodeToken _treat,
                     ASTNodeToken _as,
                     ISequenceType _SequenceType)
    {
        super(leftIToken, rightIToken);

        this._CastableExpr = _CastableExpr;
        ((ASTNode) _CastableExpr).setParent(this);
        this._treat = _treat;
        ((ASTNode) _treat).setParent(this);
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
        list.add(_CastableExpr);
        list.add(_treat);
        list.add(_as);
        list.add(_SequenceType);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof TreatExpr)) return false;
        if (! super.equals(o)) return false;
        TreatExpr other = (TreatExpr) o;
        if (! _CastableExpr.equals(other._CastableExpr)) return false;
        if (! _treat.equals(other._treat)) return false;
        if (! _as.equals(other._as)) return false;
        if (! _SequenceType.equals(other._SequenceType)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_CastableExpr.hashCode());
        hash = hash * 31 + (_treat.hashCode());
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
            _CastableExpr.accept(v);
            _treat.accept(v);
            _as.accept(v);
            _SequenceType.accept(v);
        }
        v.endVisit(this);
    }
}


