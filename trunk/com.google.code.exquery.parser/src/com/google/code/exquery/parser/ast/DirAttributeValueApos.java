
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
 *<li>Rule 273:  DirAttributeValueApos ::= EscapeApos
 *<li>Rule 274:  DirAttributeValueApos ::= AposAttrContentChar
 *<li>Rule 275:  DirAttributeValueApos ::= CommonContent
 *<li>Rule 281:  DirAttributeValue ::= SINGLEQUOTE DirAttributeValueAposList SINGLEQUOTE
 *</b>
 */
public class DirAttributeValueApos extends ASTNode implements IDirAttributeValueApos, IDirAttributeValue
{
    private ASTNodeToken _EscapeApos;
    private ASTNodeToken _NCName;
    private ICommonContent _CommonContent;
    private ASTNodeToken _SINGLEQUOTE;
    private DirAttributeValueAposList _DirAttributeValueAposList;
    private ASTNodeToken _SINGLEQUOTE3;

    /**
     * The value returned by <b>getEscapeApos</b> may be <b>null</b>
     */
    public ASTNodeToken getEscapeApos() { return _EscapeApos; }
    /**
     * The value returned by <b>getNCName</b> may be <b>null</b>
     */
    public ASTNodeToken getNCName() { return _NCName; }
    /**
     * The value returned by <b>getCommonContent</b> may be <b>null</b>
     */
    public ICommonContent getCommonContent() { return _CommonContent; }
    /**
     * The value returned by <b>getSINGLEQUOTE</b> may be <b>null</b>
     */
    public ASTNodeToken getSINGLEQUOTE() { return _SINGLEQUOTE; }
    /**
     * The value returned by <b>getDirAttributeValueAposList</b> may be <b>null</b>
     */
    public DirAttributeValueAposList getDirAttributeValueAposList() { return _DirAttributeValueAposList; }
    /**
     * The value returned by <b>getSINGLEQUOTE3</b> may be <b>null</b>
     */
    public ASTNodeToken getSINGLEQUOTE3() { return _SINGLEQUOTE3; }

    public DirAttributeValueApos(IToken leftIToken, IToken rightIToken,
                                 ASTNodeToken _EscapeApos,
                                 ASTNodeToken _NCName,
                                 ICommonContent _CommonContent,
                                 ASTNodeToken _SINGLEQUOTE,
                                 DirAttributeValueAposList _DirAttributeValueAposList,
                                 ASTNodeToken _SINGLEQUOTE3)
    {
        super(leftIToken, rightIToken);

        this._EscapeApos = _EscapeApos;
        if (_EscapeApos != null) ((ASTNode) _EscapeApos).setParent(this);
        this._NCName = _NCName;
        if (_NCName != null) ((ASTNode) _NCName).setParent(this);
        this._CommonContent = _CommonContent;
        if (_CommonContent != null) ((ASTNode) _CommonContent).setParent(this);
        this._SINGLEQUOTE = _SINGLEQUOTE;
        if (_SINGLEQUOTE != null) ((ASTNode) _SINGLEQUOTE).setParent(this);
        this._DirAttributeValueAposList = _DirAttributeValueAposList;
        if (_DirAttributeValueAposList != null) ((ASTNode) _DirAttributeValueAposList).setParent(this);
        this._SINGLEQUOTE3 = _SINGLEQUOTE3;
        if (_SINGLEQUOTE3 != null) ((ASTNode) _SINGLEQUOTE3).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_EscapeApos);
        list.add(_NCName);
        list.add(_CommonContent);
        list.add(_SINGLEQUOTE);
        list.add(_DirAttributeValueAposList);
        list.add(_SINGLEQUOTE3);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof DirAttributeValueApos)) return false;
        if (! super.equals(o)) return false;
        DirAttributeValueApos other = (DirAttributeValueApos) o;
        if (_EscapeApos == null)
            if (other._EscapeApos != null) return false;
            else; // continue
        else if (! _EscapeApos.equals(other._EscapeApos)) return false;
        if (_NCName == null)
            if (other._NCName != null) return false;
            else; // continue
        else if (! _NCName.equals(other._NCName)) return false;
        if (_CommonContent == null)
            if (other._CommonContent != null) return false;
            else; // continue
        else if (! _CommonContent.equals(other._CommonContent)) return false;
        if (_SINGLEQUOTE == null)
            if (other._SINGLEQUOTE != null) return false;
            else; // continue
        else if (! _SINGLEQUOTE.equals(other._SINGLEQUOTE)) return false;
        if (_DirAttributeValueAposList == null)
            if (other._DirAttributeValueAposList != null) return false;
            else; // continue
        else if (! _DirAttributeValueAposList.equals(other._DirAttributeValueAposList)) return false;
        if (_SINGLEQUOTE3 == null)
            if (other._SINGLEQUOTE3 != null) return false;
            else; // continue
        else if (! _SINGLEQUOTE3.equals(other._SINGLEQUOTE3)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_EscapeApos == null ? 0 : _EscapeApos.hashCode());
        hash = hash * 31 + (_NCName == null ? 0 : _NCName.hashCode());
        hash = hash * 31 + (_CommonContent == null ? 0 : _CommonContent.hashCode());
        hash = hash * 31 + (_SINGLEQUOTE == null ? 0 : _SINGLEQUOTE.hashCode());
        hash = hash * 31 + (_DirAttributeValueAposList == null ? 0 : _DirAttributeValueAposList.hashCode());
        hash = hash * 31 + (_SINGLEQUOTE3 == null ? 0 : _SINGLEQUOTE3.hashCode());
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
            if (_EscapeApos != null) _EscapeApos.accept(v);
            if (_NCName != null) _NCName.accept(v);
            if (_CommonContent != null) _CommonContent.accept(v);
            if (_SINGLEQUOTE != null) _SINGLEQUOTE.accept(v);
            if (_DirAttributeValueAposList != null) _DirAttributeValueAposList.accept(v);
            if (_SINGLEQUOTE3 != null) _SINGLEQUOTE3.accept(v);
        }
        v.endVisit(this);
    }
}


