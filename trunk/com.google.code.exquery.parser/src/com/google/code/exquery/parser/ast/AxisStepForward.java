
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
 *<li>Rule 205:  AxisStep ::= ForwardStep PredicateList
 *</b>
 */
public class AxisStepForward extends ASTNode implements IAxisStep
{
    private IForwardStep _ForwardStep;
    private PredicateList _PredicateList;

    public IForwardStep getForwardStep() { return _ForwardStep; }
    public PredicateList getPredicateList() { return _PredicateList; }

    public AxisStepForward(IToken leftIToken, IToken rightIToken,
                           IForwardStep _ForwardStep,
                           PredicateList _PredicateList)
    {
        super(leftIToken, rightIToken);

        this._ForwardStep = _ForwardStep;
        ((ASTNode) _ForwardStep).setParent(this);
        this._PredicateList = _PredicateList;
        ((ASTNode) _PredicateList).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_ForwardStep);
        list.add(_PredicateList);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof AxisStepForward)) return false;
        if (! super.equals(o)) return false;
        AxisStepForward other = (AxisStepForward) o;
        if (! _ForwardStep.equals(other._ForwardStep)) return false;
        if (! _PredicateList.equals(other._PredicateList)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_ForwardStep.hashCode());
        hash = hash * 31 + (_PredicateList.hashCode());
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
            _ForwardStep.accept(v);
            _PredicateList.accept(v);
        }
        v.endVisit(this);
    }
}


