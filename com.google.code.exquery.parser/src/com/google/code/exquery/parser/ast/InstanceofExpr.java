
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
 *<li>Rule 168:  InstanceofExpr ::= TreatExpr
 *</em>
 *<p>
 *<b>
 *<li>Rule 169:  InstanceofExpr ::= TreatExpr instance of SequenceType
 *</b>
 */
public class InstanceofExpr extends ASTNode implements IInstanceofExpr
{
    private ITreatExpr _TreatExpr;
    private ASTNodeToken _instance;
    private ASTNodeToken _of;
    private ISequenceType _SequenceType;

    public ITreatExpr getTreatExpr() { return _TreatExpr; }
    public ASTNodeToken getinstance() { return _instance; }
    public ASTNodeToken getof() { return _of; }
    public ISequenceType getSequenceType() { return _SequenceType; }

    public InstanceofExpr(IToken leftIToken, IToken rightIToken,
                          ITreatExpr _TreatExpr,
                          ASTNodeToken _instance,
                          ASTNodeToken _of,
                          ISequenceType _SequenceType)
    {
        super(leftIToken, rightIToken);

        this._TreatExpr = _TreatExpr;
        ((ASTNode) _TreatExpr).setParent(this);
        this._instance = _instance;
        ((ASTNode) _instance).setParent(this);
        this._of = _of;
        ((ASTNode) _of).setParent(this);
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
        list.add(_TreatExpr);
        list.add(_instance);
        list.add(_of);
        list.add(_SequenceType);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof InstanceofExpr)) return false;
        if (! super.equals(o)) return false;
        InstanceofExpr other = (InstanceofExpr) o;
        if (! _TreatExpr.equals(other._TreatExpr)) return false;
        if (! _instance.equals(other._instance)) return false;
        if (! _of.equals(other._of)) return false;
        if (! _SequenceType.equals(other._SequenceType)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_TreatExpr.hashCode());
        hash = hash * 31 + (_instance.hashCode());
        hash = hash * 31 + (_of.hashCode());
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
            _TreatExpr.accept(v);
            _instance.accept(v);
            _of.accept(v);
            _SequenceType.accept(v);
        }
        v.endVisit(this);
    }
}


