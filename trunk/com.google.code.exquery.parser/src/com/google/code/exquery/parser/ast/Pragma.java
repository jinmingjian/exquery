
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
 *<li>Rule 193:  Pragma ::= PragmaST QName PragmaET
 *<li>Rule 194:  Pragma ::= PragmaST QName PragmaContents PragmaET
 *</b>
 */
public class Pragma extends ASTNode implements IPragma
{
    private ASTNodeToken _PragmaST;
    private IQName _QName;
    private ASTNodeToken _PragmaET;
    private ASTNodeToken _NCName;

    /**
     * The value returned by <b>getPragmaST</b> may be <b>null</b>
     */
    public ASTNodeToken getPragmaST() { return _PragmaST; }
    public IQName getQName() { return _QName; }
    /**
     * The value returned by <b>getPragmaET</b> may be <b>null</b>
     */
    public ASTNodeToken getPragmaET() { return _PragmaET; }
    /**
     * The value returned by <b>getNCName</b> may be <b>null</b>
     */
    public ASTNodeToken getNCName() { return _NCName; }

    public Pragma(IToken leftIToken, IToken rightIToken,
                  ASTNodeToken _PragmaST,
                  IQName _QName,
                  ASTNodeToken _PragmaET,
                  ASTNodeToken _NCName)
    {
        super(leftIToken, rightIToken);

        this._PragmaST = _PragmaST;
        if (_PragmaST != null) ((ASTNode) _PragmaST).setParent(this);
        this._QName = _QName;
        ((ASTNode) _QName).setParent(this);
        this._PragmaET = _PragmaET;
        if (_PragmaET != null) ((ASTNode) _PragmaET).setParent(this);
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
        list.add(_PragmaST);
        list.add(_QName);
        list.add(_PragmaET);
        list.add(_NCName);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof Pragma)) return false;
        if (! super.equals(o)) return false;
        Pragma other = (Pragma) o;
        if (_PragmaST == null)
            if (other._PragmaST != null) return false;
            else; // continue
        else if (! _PragmaST.equals(other._PragmaST)) return false;
        if (! _QName.equals(other._QName)) return false;
        if (_PragmaET == null)
            if (other._PragmaET != null) return false;
            else; // continue
        else if (! _PragmaET.equals(other._PragmaET)) return false;
        if (_NCName == null)
            if (other._NCName != null) return false;
            else; // continue
        else if (! _NCName.equals(other._NCName)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_PragmaST == null ? 0 : _PragmaST.hashCode());
        hash = hash * 31 + (_QName.hashCode());
        hash = hash * 31 + (_PragmaET == null ? 0 : _PragmaET.hashCode());
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
            if (_PragmaST != null) _PragmaST.accept(v);
            _QName.accept(v);
            if (_PragmaET != null) _PragmaET.accept(v);
            if (_NCName != null) _NCName.accept(v);
        }
        v.endVisit(this);
    }
}


