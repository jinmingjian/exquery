
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
 *<li>Rule 315:  SingleType ::= AtomicType
 *</em>
 *<p>
 *<b>
 *<li>Rule 316:  SingleType ::= AtomicType QUESTION
 *</b>
 */
public class SingleType extends ASTNode implements ISingleType
{
    private IQName _QName;
    private ASTNodeToken _QUESTION;

    public IQName getQName() { return _QName; }
    public ASTNodeToken getQUESTION() { return _QUESTION; }

    public SingleType(IToken leftIToken, IToken rightIToken,
                      IQName _QName,
                      ASTNodeToken _QUESTION)
    {
        super(leftIToken, rightIToken);

        this._QName = _QName;
        ((ASTNode) _QName).setParent(this);
        this._QUESTION = _QUESTION;
        ((ASTNode) _QUESTION).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_QName);
        list.add(_QUESTION);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof SingleType)) return false;
        if (! super.equals(o)) return false;
        SingleType other = (SingleType) o;
        if (! _QName.equals(other._QName)) return false;
        if (! _QUESTION.equals(other._QUESTION)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_QName.hashCode());
        hash = hash * 31 + (_QUESTION.hashCode());
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
            _QName.accept(v);
            _QUESTION.accept(v);
        }
        v.endVisit(this);
    }
}


