
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
 *<li>Rule 207:  ForwardStep ::= AbbrevForwardStep
 *</em>
 *<p>
 *<b>
 *<li>Rule 206:  ForwardStep ::= ForwardAxis NodeTest
 *</b>
 */
public class ForwardStep extends ASTNode implements IForwardStep
{
    private ForwardAxis _ForwardAxis;
    private INodeTest _NodeTest;

    public ForwardAxis getForwardAxis() { return _ForwardAxis; }
    public INodeTest getNodeTest() { return _NodeTest; }

    public ForwardStep(IToken leftIToken, IToken rightIToken,
                       ForwardAxis _ForwardAxis,
                       INodeTest _NodeTest)
    {
        super(leftIToken, rightIToken);

        this._ForwardAxis = _ForwardAxis;
        ((ASTNode) _ForwardAxis).setParent(this);
        this._NodeTest = _NodeTest;
        ((ASTNode) _NodeTest).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_ForwardAxis);
        list.add(_NodeTest);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ForwardStep)) return false;
        if (! super.equals(o)) return false;
        ForwardStep other = (ForwardStep) o;
        if (! _ForwardAxis.equals(other._ForwardAxis)) return false;
        if (! _NodeTest.equals(other._NodeTest)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_ForwardAxis.hashCode());
        hash = hash * 31 + (_NodeTest.hashCode());
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
            _ForwardAxis.accept(v);
            _NodeTest.accept(v);
        }
        v.endVisit(this);
    }
}


