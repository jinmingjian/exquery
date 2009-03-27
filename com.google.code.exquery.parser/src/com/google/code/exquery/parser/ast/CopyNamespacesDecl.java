
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
 *<li>Rule 40:  CopyNamespacesDecl ::= declare copy_namespaces PreserveMode COMMA InheritMode
 *</b>
 */
public class CopyNamespacesDecl extends ASTNode implements ICopyNamespacesDecl
{
    private ASTNodeToken _declare;
    private ASTNodeToken _copy_namespaces;
    private PreserveMode _PreserveMode;
    private ASTNodeToken _COMMA;
    private InheritMode _InheritMode;

    public ASTNodeToken getdeclare() { return _declare; }
    public ASTNodeToken getcopy_namespaces() { return _copy_namespaces; }
    public PreserveMode getPreserveMode() { return _PreserveMode; }
    public ASTNodeToken getCOMMA() { return _COMMA; }
    public InheritMode getInheritMode() { return _InheritMode; }

    public CopyNamespacesDecl(IToken leftIToken, IToken rightIToken,
                              ASTNodeToken _declare,
                              ASTNodeToken _copy_namespaces,
                              PreserveMode _PreserveMode,
                              ASTNodeToken _COMMA,
                              InheritMode _InheritMode)
    {
        super(leftIToken, rightIToken);

        this._declare = _declare;
        ((ASTNode) _declare).setParent(this);
        this._copy_namespaces = _copy_namespaces;
        ((ASTNode) _copy_namespaces).setParent(this);
        this._PreserveMode = _PreserveMode;
        ((ASTNode) _PreserveMode).setParent(this);
        this._COMMA = _COMMA;
        ((ASTNode) _COMMA).setParent(this);
        this._InheritMode = _InheritMode;
        ((ASTNode) _InheritMode).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_declare);
        list.add(_copy_namespaces);
        list.add(_PreserveMode);
        list.add(_COMMA);
        list.add(_InheritMode);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof CopyNamespacesDecl)) return false;
        if (! super.equals(o)) return false;
        CopyNamespacesDecl other = (CopyNamespacesDecl) o;
        if (! _declare.equals(other._declare)) return false;
        if (! _copy_namespaces.equals(other._copy_namespaces)) return false;
        if (! _PreserveMode.equals(other._PreserveMode)) return false;
        if (! _COMMA.equals(other._COMMA)) return false;
        if (! _InheritMode.equals(other._InheritMode)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_declare.hashCode());
        hash = hash * 31 + (_copy_namespaces.hashCode());
        hash = hash * 31 + (_PreserveMode.hashCode());
        hash = hash * 31 + (_COMMA.hashCode());
        hash = hash * 31 + (_InheritMode.hashCode());
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
            _declare.accept(v);
            _copy_namespaces.accept(v);
            _PreserveMode.accept(v);
            _COMMA.accept(v);
            _InheritMode.accept(v);
        }
        v.endVisit(this);
    }
}


