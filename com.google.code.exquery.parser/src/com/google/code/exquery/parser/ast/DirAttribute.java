
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
 *<li>Rule 267:  DirAttribute ::= QName EQUAL DirAttributeValue
 *</b>
 */
public class DirAttribute extends ASTNode implements IDirAttribute
{
    private IQName _QName;
    private ASTNodeToken _EQUAL;
    private IDirAttributeValue _DirAttributeValue;

    public IQName getQName() { return _QName; }
    public ASTNodeToken getEQUAL() { return _EQUAL; }
    public IDirAttributeValue getDirAttributeValue() { return _DirAttributeValue; }

    public DirAttribute(IToken leftIToken, IToken rightIToken,
                        IQName _QName,
                        ASTNodeToken _EQUAL,
                        IDirAttributeValue _DirAttributeValue)
    {
        super(leftIToken, rightIToken);

        this._QName = _QName;
        ((ASTNode) _QName).setParent(this);
        this._EQUAL = _EQUAL;
        ((ASTNode) _EQUAL).setParent(this);
        this._DirAttributeValue = _DirAttributeValue;
        ((ASTNode) _DirAttributeValue).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_QName);
        list.add(_EQUAL);
        list.add(_DirAttributeValue);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof DirAttribute)) return false;
        if (! super.equals(o)) return false;
        DirAttribute other = (DirAttribute) o;
        if (! _QName.equals(other._QName)) return false;
        if (! _EQUAL.equals(other._EQUAL)) return false;
        if (! _DirAttributeValue.equals(other._DirAttributeValue)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_QName.hashCode());
        hash = hash * 31 + (_EQUAL.hashCode());
        hash = hash * 31 + (_DirAttributeValue.hashCode());
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
            _EQUAL.accept(v);
            _DirAttributeValue.accept(v);
        }
        v.endVisit(this);
    }
}


