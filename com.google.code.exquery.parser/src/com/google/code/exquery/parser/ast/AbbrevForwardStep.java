
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
 *<li>Rule 215:  AbbrevForwardStep ::= NodeTest
 *</em>
 *<p>
 *<b>
 *<li>Rule 216:  AbbrevForwardStep ::= ATSIGN NodeTest
 *</b>
 */
public class AbbrevForwardStep extends ASTNode implements IAbbrevForwardStep
{
    private ASTNodeToken _ATSIGN;
    private INodeTest _NodeTest;

    public ASTNodeToken getATSIGN() { return _ATSIGN; }
    public INodeTest getNodeTest() { return _NodeTest; }

    public AbbrevForwardStep(IToken leftIToken, IToken rightIToken,
                             ASTNodeToken _ATSIGN,
                             INodeTest _NodeTest)
    {
        super(leftIToken, rightIToken);

        this._ATSIGN = _ATSIGN;
        ((ASTNode) _ATSIGN).setParent(this);
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
        list.add(_ATSIGN);
        list.add(_NodeTest);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof AbbrevForwardStep)) return false;
        if (! super.equals(o)) return false;
        AbbrevForwardStep other = (AbbrevForwardStep) o;
        if (! _ATSIGN.equals(other._ATSIGN)) return false;
        if (! _NodeTest.equals(other._NodeTest)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_ATSIGN.hashCode());
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
            _ATSIGN.accept(v);
            _NodeTest.accept(v);
        }
        v.endVisit(this);
    }
}


