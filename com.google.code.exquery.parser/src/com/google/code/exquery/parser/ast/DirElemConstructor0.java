
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
 *<li>Rule 263:  DirElemConstructor ::= LESS QName DirAttributeList STEndMark
 *</b>
 */
public class DirElemConstructor0 extends ASTNode implements IDirElemConstructor
{
    private ASTNodeToken _LESS;
    private IQName _QName;
    private DirAttributeList _DirAttributeList;
    private ASTNodeToken _STEndMark;

    public ASTNodeToken getLESS() { return _LESS; }
    public IQName getQName() { return _QName; }
    public DirAttributeList getDirAttributeList() { return _DirAttributeList; }
    public ASTNodeToken getSTEndMark() { return _STEndMark; }

    public DirElemConstructor0(IToken leftIToken, IToken rightIToken,
                               ASTNodeToken _LESS,
                               IQName _QName,
                               DirAttributeList _DirAttributeList,
                               ASTNodeToken _STEndMark)
    {
        super(leftIToken, rightIToken);

        this._LESS = _LESS;
        ((ASTNode) _LESS).setParent(this);
        this._QName = _QName;
        ((ASTNode) _QName).setParent(this);
        this._DirAttributeList = _DirAttributeList;
        ((ASTNode) _DirAttributeList).setParent(this);
        this._STEndMark = _STEndMark;
        ((ASTNode) _STEndMark).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_LESS);
        list.add(_QName);
        list.add(_DirAttributeList);
        list.add(_STEndMark);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof DirElemConstructor0)) return false;
        if (! super.equals(o)) return false;
        DirElemConstructor0 other = (DirElemConstructor0) o;
        if (! _LESS.equals(other._LESS)) return false;
        if (! _QName.equals(other._QName)) return false;
        if (! _DirAttributeList.equals(other._DirAttributeList)) return false;
        if (! _STEndMark.equals(other._STEndMark)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_LESS.hashCode());
        hash = hash * 31 + (_QName.hashCode());
        hash = hash * 31 + (_DirAttributeList.hashCode());
        hash = hash * 31 + (_STEndMark.hashCode());
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
            _LESS.accept(v);
            _QName.accept(v);
            _DirAttributeList.accept(v);
            _STEndMark.accept(v);
        }
        v.endVisit(this);
    }
}


