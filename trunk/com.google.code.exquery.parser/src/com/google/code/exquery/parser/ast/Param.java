
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
 *<li>Rule 72:  Param ::= DOLLAR QName TypeDeclarationOpt
 *</b>
 */
public class Param extends ASTNode implements IParam
{
    private ASTNodeToken _DOLLAR;
    private IQName _QName;
    private TypeDeclaration _TypeDeclarationOpt;

    public ASTNodeToken getDOLLAR() { return _DOLLAR; }
    public IQName getQName() { return _QName; }
    /**
     * The value returned by <b>getTypeDeclarationOpt</b> may be <b>null</b>
     */
    public TypeDeclaration getTypeDeclarationOpt() { return _TypeDeclarationOpt; }

    public Param(IToken leftIToken, IToken rightIToken,
                 ASTNodeToken _DOLLAR,
                 IQName _QName,
                 TypeDeclaration _TypeDeclarationOpt)
    {
        super(leftIToken, rightIToken);

        this._DOLLAR = _DOLLAR;
        ((ASTNode) _DOLLAR).setParent(this);
        this._QName = _QName;
        ((ASTNode) _QName).setParent(this);
        this._TypeDeclarationOpt = _TypeDeclarationOpt;
        if (_TypeDeclarationOpt != null) ((ASTNode) _TypeDeclarationOpt).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_DOLLAR);
        list.add(_QName);
        list.add(_TypeDeclarationOpt);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof Param)) return false;
        if (! super.equals(o)) return false;
        Param other = (Param) o;
        if (! _DOLLAR.equals(other._DOLLAR)) return false;
        if (! _QName.equals(other._QName)) return false;
        if (_TypeDeclarationOpt == null)
            if (other._TypeDeclarationOpt != null) return false;
            else; // continue
        else if (! _TypeDeclarationOpt.equals(other._TypeDeclarationOpt)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_DOLLAR.hashCode());
        hash = hash * 31 + (_QName.hashCode());
        hash = hash * 31 + (_TypeDeclarationOpt == null ? 0 : _TypeDeclarationOpt.hashCode());
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
            _DOLLAR.accept(v);
            _QName.accept(v);
            if (_TypeDeclarationOpt != null) _TypeDeclarationOpt.accept(v);
        }
        v.endVisit(this);
    }
}


