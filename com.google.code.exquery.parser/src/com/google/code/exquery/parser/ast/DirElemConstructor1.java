
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
 *<li>Rule 264:  DirElemConstructor ::= LESS QName DirAttributeList GREATER DirElemContentList ETStartMark QName GREATER
 *</b>
 */
public class DirElemConstructor1 extends ASTNode implements IDirElemConstructor
{
    private ASTNodeToken _LESS;
    private IQName _QName;
    private DirAttributeList _DirAttributeList;
    private ASTNodeToken _GREATER;
    private DirElemContentList _DirElemContentList;
    private ASTNodeToken _ETStartMark;
    private IQName _QName7;
    private ASTNodeToken _GREATER8;

    public ASTNodeToken getLESS() { return _LESS; }
    public IQName getQName() { return _QName; }
    public DirAttributeList getDirAttributeList() { return _DirAttributeList; }
    public ASTNodeToken getGREATER() { return _GREATER; }
    public DirElemContentList getDirElemContentList() { return _DirElemContentList; }
    public ASTNodeToken getETStartMark() { return _ETStartMark; }
    public IQName getQName7() { return _QName7; }
    public ASTNodeToken getGREATER8() { return _GREATER8; }

    public DirElemConstructor1(IToken leftIToken, IToken rightIToken,
                               ASTNodeToken _LESS,
                               IQName _QName,
                               DirAttributeList _DirAttributeList,
                               ASTNodeToken _GREATER,
                               DirElemContentList _DirElemContentList,
                               ASTNodeToken _ETStartMark,
                               IQName _QName7,
                               ASTNodeToken _GREATER8)
    {
        super(leftIToken, rightIToken);

        this._LESS = _LESS;
        ((ASTNode) _LESS).setParent(this);
        this._QName = _QName;
        ((ASTNode) _QName).setParent(this);
        this._DirAttributeList = _DirAttributeList;
        ((ASTNode) _DirAttributeList).setParent(this);
        this._GREATER = _GREATER;
        ((ASTNode) _GREATER).setParent(this);
        this._DirElemContentList = _DirElemContentList;
        ((ASTNode) _DirElemContentList).setParent(this);
        this._ETStartMark = _ETStartMark;
        ((ASTNode) _ETStartMark).setParent(this);
        this._QName7 = _QName7;
        ((ASTNode) _QName7).setParent(this);
        this._GREATER8 = _GREATER8;
        ((ASTNode) _GREATER8).setParent(this);
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
        list.add(_GREATER);
        list.add(_DirElemContentList);
        list.add(_ETStartMark);
        list.add(_QName7);
        list.add(_GREATER8);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof DirElemConstructor1)) return false;
        if (! super.equals(o)) return false;
        DirElemConstructor1 other = (DirElemConstructor1) o;
        if (! _LESS.equals(other._LESS)) return false;
        if (! _QName.equals(other._QName)) return false;
        if (! _DirAttributeList.equals(other._DirAttributeList)) return false;
        if (! _GREATER.equals(other._GREATER)) return false;
        if (! _DirElemContentList.equals(other._DirElemContentList)) return false;
        if (! _ETStartMark.equals(other._ETStartMark)) return false;
        if (! _QName7.equals(other._QName7)) return false;
        if (! _GREATER8.equals(other._GREATER8)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_LESS.hashCode());
        hash = hash * 31 + (_QName.hashCode());
        hash = hash * 31 + (_DirAttributeList.hashCode());
        hash = hash * 31 + (_GREATER.hashCode());
        hash = hash * 31 + (_DirElemContentList.hashCode());
        hash = hash * 31 + (_ETStartMark.hashCode());
        hash = hash * 31 + (_QName7.hashCode());
        hash = hash * 31 + (_GREATER8.hashCode());
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
            _GREATER.accept(v);
            _DirElemContentList.accept(v);
            _ETStartMark.accept(v);
            _QName7.accept(v);
            _GREATER8.accept(v);
        }
        v.endVisit(this);
    }
}


