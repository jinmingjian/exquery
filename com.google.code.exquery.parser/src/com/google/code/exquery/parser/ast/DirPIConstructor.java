
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
 *<li>Rule 291:  DirPIConstructor ::= PIST PITarget PIET
 *<li>Rule 292:  DirPIConstructor ::= PIST PITarget DirPIContents PIET
 *</b>
 */
public class DirPIConstructor extends ASTNode implements IDirPIConstructor
{
    private ASTNodeToken _PIST;
    private IQName _QName;
    private ASTNodeToken _PIET;
    private ASTNodeToken _NCName;

    /**
     * The value returned by <b>getPIST</b> may be <b>null</b>
     */
    public ASTNodeToken getPIST() { return _PIST; }
    public IQName getQName() { return _QName; }
    /**
     * The value returned by <b>getPIET</b> may be <b>null</b>
     */
    public ASTNodeToken getPIET() { return _PIET; }
    /**
     * The value returned by <b>getNCName</b> may be <b>null</b>
     */
    public ASTNodeToken getNCName() { return _NCName; }

    public DirPIConstructor(IToken leftIToken, IToken rightIToken,
                            ASTNodeToken _PIST,
                            IQName _QName,
                            ASTNodeToken _PIET,
                            ASTNodeToken _NCName)
    {
        super(leftIToken, rightIToken);

        this._PIST = _PIST;
        if (_PIST != null) ((ASTNode) _PIST).setParent(this);
        this._QName = _QName;
        ((ASTNode) _QName).setParent(this);
        this._PIET = _PIET;
        if (_PIET != null) ((ASTNode) _PIET).setParent(this);
        this._NCName = _NCName;
        if (_NCName != null) ((ASTNode) _NCName).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_PIST);
        list.add(_QName);
        list.add(_PIET);
        list.add(_NCName);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof DirPIConstructor)) return false;
        if (! super.equals(o)) return false;
        DirPIConstructor other = (DirPIConstructor) o;
        if (_PIST == null)
            if (other._PIST != null) return false;
            else; // continue
        else if (! _PIST.equals(other._PIST)) return false;
        if (! _QName.equals(other._QName)) return false;
        if (_PIET == null)
            if (other._PIET != null) return false;
            else; // continue
        else if (! _PIET.equals(other._PIET)) return false;
        if (_NCName == null)
            if (other._NCName != null) return false;
            else; // continue
        else if (! _NCName.equals(other._NCName)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_PIST == null ? 0 : _PIST.hashCode());
        hash = hash * 31 + (_QName.hashCode());
        hash = hash * 31 + (_PIET == null ? 0 : _PIET.hashCode());
        hash = hash * 31 + (_NCName == null ? 0 : _NCName.hashCode());
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
            if (_PIST != null) _PIST.accept(v);
            _QName.accept(v);
            if (_PIET != null) _PIET.accept(v);
            if (_NCName != null) _NCName.accept(v);
        }
        v.endVisit(this);
    }
}


