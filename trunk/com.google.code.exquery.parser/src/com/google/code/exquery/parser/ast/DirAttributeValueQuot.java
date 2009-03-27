
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
 *<li>Rule 270:  DirAttributeValueQuot ::= EscapeQuot
 *<li>Rule 271:  DirAttributeValueQuot ::= QuotAttrContentChar
 *<li>Rule 272:  DirAttributeValueQuot ::= CommonContent
 *<li>Rule 280:  DirAttributeValue ::= DOUBLEQUOTE DirAttributeValueQuotList DOUBLEQUOTE
 *</b>
 */
public class DirAttributeValueQuot extends ASTNode implements IDirAttributeValueQuot, IDirAttributeValue
{
    private ASTNodeToken _EscapeQuot;
    private ASTNodeToken _NCName;
    private ICommonContent _CommonContent;
    private ASTNodeToken _DOUBLEQUOTE;
    private DirAttributeValueQuotList _DirAttributeValueQuotList;
    private ASTNodeToken _DOUBLEQUOTE3;

    /**
     * The value returned by <b>getEscapeQuot</b> may be <b>null</b>
     */
    public ASTNodeToken getEscapeQuot() { return _EscapeQuot; }
    /**
     * The value returned by <b>getNCName</b> may be <b>null</b>
     */
    public ASTNodeToken getNCName() { return _NCName; }
    /**
     * The value returned by <b>getCommonContent</b> may be <b>null</b>
     */
    public ICommonContent getCommonContent() { return _CommonContent; }
    /**
     * The value returned by <b>getDOUBLEQUOTE</b> may be <b>null</b>
     */
    public ASTNodeToken getDOUBLEQUOTE() { return _DOUBLEQUOTE; }
    /**
     * The value returned by <b>getDirAttributeValueQuotList</b> may be <b>null</b>
     */
    public DirAttributeValueQuotList getDirAttributeValueQuotList() { return _DirAttributeValueQuotList; }
    /**
     * The value returned by <b>getDOUBLEQUOTE3</b> may be <b>null</b>
     */
    public ASTNodeToken getDOUBLEQUOTE3() { return _DOUBLEQUOTE3; }

    public DirAttributeValueQuot(IToken leftIToken, IToken rightIToken,
                                 ASTNodeToken _EscapeQuot,
                                 ASTNodeToken _NCName,
                                 ICommonContent _CommonContent,
                                 ASTNodeToken _DOUBLEQUOTE,
                                 DirAttributeValueQuotList _DirAttributeValueQuotList,
                                 ASTNodeToken _DOUBLEQUOTE3)
    {
        super(leftIToken, rightIToken);

        this._EscapeQuot = _EscapeQuot;
        if (_EscapeQuot != null) ((ASTNode) _EscapeQuot).setParent(this);
        this._NCName = _NCName;
        if (_NCName != null) ((ASTNode) _NCName).setParent(this);
        this._CommonContent = _CommonContent;
        if (_CommonContent != null) ((ASTNode) _CommonContent).setParent(this);
        this._DOUBLEQUOTE = _DOUBLEQUOTE;
        if (_DOUBLEQUOTE != null) ((ASTNode) _DOUBLEQUOTE).setParent(this);
        this._DirAttributeValueQuotList = _DirAttributeValueQuotList;
        if (_DirAttributeValueQuotList != null) ((ASTNode) _DirAttributeValueQuotList).setParent(this);
        this._DOUBLEQUOTE3 = _DOUBLEQUOTE3;
        if (_DOUBLEQUOTE3 != null) ((ASTNode) _DOUBLEQUOTE3).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_EscapeQuot);
        list.add(_NCName);
        list.add(_CommonContent);
        list.add(_DOUBLEQUOTE);
        list.add(_DirAttributeValueQuotList);
        list.add(_DOUBLEQUOTE3);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof DirAttributeValueQuot)) return false;
        if (! super.equals(o)) return false;
        DirAttributeValueQuot other = (DirAttributeValueQuot) o;
        if (_EscapeQuot == null)
            if (other._EscapeQuot != null) return false;
            else; // continue
        else if (! _EscapeQuot.equals(other._EscapeQuot)) return false;
        if (_NCName == null)
            if (other._NCName != null) return false;
            else; // continue
        else if (! _NCName.equals(other._NCName)) return false;
        if (_CommonContent == null)
            if (other._CommonContent != null) return false;
            else; // continue
        else if (! _CommonContent.equals(other._CommonContent)) return false;
        if (_DOUBLEQUOTE == null)
            if (other._DOUBLEQUOTE != null) return false;
            else; // continue
        else if (! _DOUBLEQUOTE.equals(other._DOUBLEQUOTE)) return false;
        if (_DirAttributeValueQuotList == null)
            if (other._DirAttributeValueQuotList != null) return false;
            else; // continue
        else if (! _DirAttributeValueQuotList.equals(other._DirAttributeValueQuotList)) return false;
        if (_DOUBLEQUOTE3 == null)
            if (other._DOUBLEQUOTE3 != null) return false;
            else; // continue
        else if (! _DOUBLEQUOTE3.equals(other._DOUBLEQUOTE3)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_EscapeQuot == null ? 0 : _EscapeQuot.hashCode());
        hash = hash * 31 + (_NCName == null ? 0 : _NCName.hashCode());
        hash = hash * 31 + (_CommonContent == null ? 0 : _CommonContent.hashCode());
        hash = hash * 31 + (_DOUBLEQUOTE == null ? 0 : _DOUBLEQUOTE.hashCode());
        hash = hash * 31 + (_DirAttributeValueQuotList == null ? 0 : _DirAttributeValueQuotList.hashCode());
        hash = hash * 31 + (_DOUBLEQUOTE3 == null ? 0 : _DOUBLEQUOTE3.hashCode());
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
            if (_EscapeQuot != null) _EscapeQuot.accept(v);
            if (_NCName != null) _NCName.accept(v);
            if (_CommonContent != null) _CommonContent.accept(v);
            if (_DOUBLEQUOTE != null) _DOUBLEQUOTE.accept(v);
            if (_DirAttributeValueQuotList != null) _DirAttributeValueQuotList.accept(v);
            if (_DOUBLEQUOTE3 != null) _DOUBLEQUOTE3.accept(v);
        }
        v.endVisit(this);
    }
}


