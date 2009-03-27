
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
 *<li>Rule 345:  AttributeTest ::= attribute LEFTPAREN RIGHTPAREN
 *<li>Rule 346:  AttributeTest ::= attribute LEFTPAREN AttributeName RIGHTPAREN
 *<li>Rule 347:  AttributeTest ::= attribute LEFTPAREN TIMES RIGHTPAREN
 *<li>Rule 348:  AttributeTest ::= attribute LEFTPAREN AttributeName COMMA TypeName RIGHTPAREN
 *<li>Rule 349:  AttributeTest ::= attribute LEFTPAREN TIMES COMMA TypeName RIGHTPAREN
 *</b>
 */
public class AttributeTest extends ASTNode implements IAttributeTest
{
    private ASTNodeToken _attribute;
    private ASTNodeToken _LEFTPAREN;
    private ASTNodeToken _RIGHTPAREN;
    private IQName _QName;
    private ASTNodeToken _TIMES;
    private ASTNodeToken _COMMA;
    private IQName _QName5;

    /**
     * The value returned by <b>getattribute</b> may be <b>null</b>
     */
    public ASTNodeToken getattribute() { return _attribute; }
    public ASTNodeToken getLEFTPAREN() { return _LEFTPAREN; }
    /**
     * The value returned by <b>getRIGHTPAREN</b> may be <b>null</b>
     */
    public ASTNodeToken getRIGHTPAREN() { return _RIGHTPAREN; }
    /**
     * The value returned by <b>getQName</b> may be <b>null</b>
     */
    public IQName getQName() { return _QName; }
    /**
     * The value returned by <b>getTIMES</b> may be <b>null</b>
     */
    public ASTNodeToken getTIMES() { return _TIMES; }
    /**
     * The value returned by <b>getCOMMA</b> may be <b>null</b>
     */
    public ASTNodeToken getCOMMA() { return _COMMA; }
    /**
     * The value returned by <b>getQName5</b> may be <b>null</b>
     */
    public IQName getQName5() { return _QName5; }

    public AttributeTest(IToken leftIToken, IToken rightIToken,
                         ASTNodeToken _attribute,
                         ASTNodeToken _LEFTPAREN,
                         ASTNodeToken _RIGHTPAREN,
                         IQName _QName,
                         ASTNodeToken _TIMES,
                         ASTNodeToken _COMMA,
                         IQName _QName5)
    {
        super(leftIToken, rightIToken);

        this._attribute = _attribute;
        if (_attribute != null) ((ASTNode) _attribute).setParent(this);
        this._LEFTPAREN = _LEFTPAREN;
        ((ASTNode) _LEFTPAREN).setParent(this);
        this._RIGHTPAREN = _RIGHTPAREN;
        if (_RIGHTPAREN != null) ((ASTNode) _RIGHTPAREN).setParent(this);
        this._QName = _QName;
        if (_QName != null) ((ASTNode) _QName).setParent(this);
        this._TIMES = _TIMES;
        if (_TIMES != null) ((ASTNode) _TIMES).setParent(this);
        this._COMMA = _COMMA;
        if (_COMMA != null) ((ASTNode) _COMMA).setParent(this);
        this._QName5 = _QName5;
        if (_QName5 != null) ((ASTNode) _QName5).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_attribute);
        list.add(_LEFTPAREN);
        list.add(_RIGHTPAREN);
        list.add(_QName);
        list.add(_TIMES);
        list.add(_COMMA);
        list.add(_QName5);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof AttributeTest)) return false;
        if (! super.equals(o)) return false;
        AttributeTest other = (AttributeTest) o;
        if (_attribute == null)
            if (other._attribute != null) return false;
            else; // continue
        else if (! _attribute.equals(other._attribute)) return false;
        if (! _LEFTPAREN.equals(other._LEFTPAREN)) return false;
        if (_RIGHTPAREN == null)
            if (other._RIGHTPAREN != null) return false;
            else; // continue
        else if (! _RIGHTPAREN.equals(other._RIGHTPAREN)) return false;
        if (_QName == null)
            if (other._QName != null) return false;
            else; // continue
        else if (! _QName.equals(other._QName)) return false;
        if (_TIMES == null)
            if (other._TIMES != null) return false;
            else; // continue
        else if (! _TIMES.equals(other._TIMES)) return false;
        if (_COMMA == null)
            if (other._COMMA != null) return false;
            else; // continue
        else if (! _COMMA.equals(other._COMMA)) return false;
        if (_QName5 == null)
            if (other._QName5 != null) return false;
            else; // continue
        else if (! _QName5.equals(other._QName5)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_attribute == null ? 0 : _attribute.hashCode());
        hash = hash * 31 + (_LEFTPAREN.hashCode());
        hash = hash * 31 + (_RIGHTPAREN == null ? 0 : _RIGHTPAREN.hashCode());
        hash = hash * 31 + (_QName == null ? 0 : _QName.hashCode());
        hash = hash * 31 + (_TIMES == null ? 0 : _TIMES.hashCode());
        hash = hash * 31 + (_COMMA == null ? 0 : _COMMA.hashCode());
        hash = hash * 31 + (_QName5 == null ? 0 : _QName5.hashCode());
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
            if (_attribute != null) _attribute.accept(v);
            _LEFTPAREN.accept(v);
            if (_RIGHTPAREN != null) _RIGHTPAREN.accept(v);
            if (_QName != null) _QName.accept(v);
            if (_TIMES != null) _TIMES.accept(v);
            if (_COMMA != null) _COMMA.accept(v);
            if (_QName5 != null) _QName5.accept(v);
        }
        v.endVisit(this);
    }
}


