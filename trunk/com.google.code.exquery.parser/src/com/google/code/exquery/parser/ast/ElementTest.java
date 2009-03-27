
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
 *<li>Rule 351:  ElementTest ::= element LEFTPAREN RIGHTPAREN
 *<li>Rule 352:  ElementTest ::= element LEFTPAREN ElementName RIGHTPAREN
 *<li>Rule 353:  ElementTest ::= element LEFTPAREN TIMES RIGHTPAREN
 *<li>Rule 354:  ElementTest ::= element LEFTPAREN ElementName COMMA TypeName RIGHTPAREN
 *<li>Rule 355:  ElementTest ::= element LEFTPAREN TIMES COMMA TypeName RIGHTPAREN
 *<li>Rule 356:  ElementTest ::= element LEFTPAREN ElementName COMMA TypeName QUESTION RIGHTPAREN
 *<li>Rule 357:  ElementTest ::= element LEFTPAREN TIMES COMMA TypeName QUESTION RIGHTPAREN
 *</b>
 */
public class ElementTest extends ASTNode implements IElementTest
{
    private ASTNodeToken _element;
    private ASTNodeToken _LEFTPAREN;
    private ASTNodeToken _RIGHTPAREN;
    private IQName _QName;
    private ASTNodeToken _TIMES;
    private ASTNodeToken _COMMA;
    private IQName _QName5;
    private ASTNodeToken _QUESTION;

    /**
     * The value returned by <b>getelement</b> may be <b>null</b>
     */
    public ASTNodeToken getelement() { return _element; }
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
    /**
     * The value returned by <b>getQUESTION</b> may be <b>null</b>
     */
    public ASTNodeToken getQUESTION() { return _QUESTION; }

    public ElementTest(IToken leftIToken, IToken rightIToken,
                       ASTNodeToken _element,
                       ASTNodeToken _LEFTPAREN,
                       ASTNodeToken _RIGHTPAREN,
                       IQName _QName,
                       ASTNodeToken _TIMES,
                       ASTNodeToken _COMMA,
                       IQName _QName5,
                       ASTNodeToken _QUESTION)
    {
        super(leftIToken, rightIToken);

        this._element = _element;
        if (_element != null) ((ASTNode) _element).setParent(this);
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
        this._QUESTION = _QUESTION;
        if (_QUESTION != null) ((ASTNode) _QUESTION).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_element);
        list.add(_LEFTPAREN);
        list.add(_RIGHTPAREN);
        list.add(_QName);
        list.add(_TIMES);
        list.add(_COMMA);
        list.add(_QName5);
        list.add(_QUESTION);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ElementTest)) return false;
        if (! super.equals(o)) return false;
        ElementTest other = (ElementTest) o;
        if (_element == null)
            if (other._element != null) return false;
            else; // continue
        else if (! _element.equals(other._element)) return false;
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
        if (_QUESTION == null)
            if (other._QUESTION != null) return false;
            else; // continue
        else if (! _QUESTION.equals(other._QUESTION)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_element == null ? 0 : _element.hashCode());
        hash = hash * 31 + (_LEFTPAREN.hashCode());
        hash = hash * 31 + (_RIGHTPAREN == null ? 0 : _RIGHTPAREN.hashCode());
        hash = hash * 31 + (_QName == null ? 0 : _QName.hashCode());
        hash = hash * 31 + (_TIMES == null ? 0 : _TIMES.hashCode());
        hash = hash * 31 + (_COMMA == null ? 0 : _COMMA.hashCode());
        hash = hash * 31 + (_QName5 == null ? 0 : _QName5.hashCode());
        hash = hash * 31 + (_QUESTION == null ? 0 : _QUESTION.hashCode());
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
            if (_element != null) _element.accept(v);
            _LEFTPAREN.accept(v);
            if (_RIGHTPAREN != null) _RIGHTPAREN.accept(v);
            if (_QName != null) _QName.accept(v);
            if (_TIMES != null) _TIMES.accept(v);
            if (_COMMA != null) _COMMA.accept(v);
            if (_QName5 != null) _QName5.accept(v);
            if (_QUESTION != null) _QUESTION.accept(v);
        }
        v.endVisit(this);
    }
}


