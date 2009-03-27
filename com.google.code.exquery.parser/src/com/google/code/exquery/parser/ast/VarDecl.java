
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
 *<li>Rule 60:  VarDecl ::= declare variable DOLLAR QName TypeDeclarationOpt ASSIGN ExprSingle
 *</b>
 */
public class VarDecl extends ASTNode implements IVarDecl
{
    private ASTNodeToken _declare;
    private ASTNodeToken _variable;
    private ASTNodeToken _DOLLAR;
    private IQName _QName;
    private TypeDeclaration _TypeDeclarationOpt;
    private ASTNodeToken _ASSIGN;
    private IExprSingle _ExprSingle;

    public ASTNodeToken getdeclare() { return _declare; }
    public ASTNodeToken getvariable() { return _variable; }
    public ASTNodeToken getDOLLAR() { return _DOLLAR; }
    public IQName getQName() { return _QName; }
    /**
     * The value returned by <b>getTypeDeclarationOpt</b> may be <b>null</b>
     */
    public TypeDeclaration getTypeDeclarationOpt() { return _TypeDeclarationOpt; }
    public ASTNodeToken getASSIGN() { return _ASSIGN; }
    public IExprSingle getExprSingle() { return _ExprSingle; }

    public VarDecl(IToken leftIToken, IToken rightIToken,
                   ASTNodeToken _declare,
                   ASTNodeToken _variable,
                   ASTNodeToken _DOLLAR,
                   IQName _QName,
                   TypeDeclaration _TypeDeclarationOpt,
                   ASTNodeToken _ASSIGN,
                   IExprSingle _ExprSingle)
    {
        super(leftIToken, rightIToken);

        this._declare = _declare;
        ((ASTNode) _declare).setParent(this);
        this._variable = _variable;
        ((ASTNode) _variable).setParent(this);
        this._DOLLAR = _DOLLAR;
        ((ASTNode) _DOLLAR).setParent(this);
        this._QName = _QName;
        ((ASTNode) _QName).setParent(this);
        this._TypeDeclarationOpt = _TypeDeclarationOpt;
        if (_TypeDeclarationOpt != null) ((ASTNode) _TypeDeclarationOpt).setParent(this);
        this._ASSIGN = _ASSIGN;
        ((ASTNode) _ASSIGN).setParent(this);
        this._ExprSingle = _ExprSingle;
        ((ASTNode) _ExprSingle).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_declare);
        list.add(_variable);
        list.add(_DOLLAR);
        list.add(_QName);
        list.add(_TypeDeclarationOpt);
        list.add(_ASSIGN);
        list.add(_ExprSingle);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof VarDecl)) return false;
        if (! super.equals(o)) return false;
        VarDecl other = (VarDecl) o;
        if (! _declare.equals(other._declare)) return false;
        if (! _variable.equals(other._variable)) return false;
        if (! _DOLLAR.equals(other._DOLLAR)) return false;
        if (! _QName.equals(other._QName)) return false;
        if (_TypeDeclarationOpt == null)
            if (other._TypeDeclarationOpt != null) return false;
            else; // continue
        else if (! _TypeDeclarationOpt.equals(other._TypeDeclarationOpt)) return false;
        if (! _ASSIGN.equals(other._ASSIGN)) return false;
        if (! _ExprSingle.equals(other._ExprSingle)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_declare.hashCode());
        hash = hash * 31 + (_variable.hashCode());
        hash = hash * 31 + (_DOLLAR.hashCode());
        hash = hash * 31 + (_QName.hashCode());
        hash = hash * 31 + (_TypeDeclarationOpt == null ? 0 : _TypeDeclarationOpt.hashCode());
        hash = hash * 31 + (_ASSIGN.hashCode());
        hash = hash * 31 + (_ExprSingle.hashCode());
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
            _variable.accept(v);
            _DOLLAR.accept(v);
            _QName.accept(v);
            if (_TypeDeclarationOpt != null) _TypeDeclarationOpt.accept(v);
            _ASSIGN.accept(v);
            _ExprSingle.accept(v);
        }
        v.endVisit(this);
    }
}


