
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
 *<li>Rule 204:  AxisStep ::= ReverseStep PredicateList
 *</b>
 */
public class AxisStepReverse extends ASTNode implements IAxisStep
{
    private IReverseStep _ReverseStep;
    private PredicateList _PredicateList;

    public IReverseStep getReverseStep() { return _ReverseStep; }
    public PredicateList getPredicateList() { return _PredicateList; }

    public AxisStepReverse(IToken leftIToken, IToken rightIToken,
                           IReverseStep _ReverseStep,
                           PredicateList _PredicateList)
    {
        super(leftIToken, rightIToken);

        this._ReverseStep = _ReverseStep;
        ((ASTNode) _ReverseStep).setParent(this);
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
        list.add(_ReverseStep);
        list.add(_PredicateList);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof AxisStepReverse)) return false;
        if (! super.equals(o)) return false;
        AxisStepReverse other = (AxisStepReverse) o;
        if (! _ReverseStep.equals(other._ReverseStep)) return false;
        if (! _PredicateList.equals(other._PredicateList)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_ReverseStep.hashCode());
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
            _ReverseStep.accept(v);
            _PredicateList.accept(v);
        }
        v.endVisit(this);
    }
}


