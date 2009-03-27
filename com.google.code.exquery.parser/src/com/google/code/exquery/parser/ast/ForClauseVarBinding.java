
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
 *<li>Rule 93:  ForClauseVarBinding ::= DOLLAR VarName TypeDeclarationOpt PositionalVarOpt in ExprSingle
 *</b>
 */
public class ForClauseVarBinding extends ASTNode implements IForClauseVarBinding
{
    private ASTNodeToken _DOLLAR;
    private IVarName _VarName;
    private TypeDeclaration _TypeDeclarationOpt;
    private PositionalVar _PositionalVarOpt;
    private ASTNodeToken _in;
    private IExprSingle _ExprSingle;

    public ASTNodeToken getDOLLAR() { return _DOLLAR; }
    public IVarName getVarName() { return _VarName; }
    /**
     * The value returned by <b>getTypeDeclarationOpt</b> may be <b>null</b>
     */
    public TypeDeclaration getTypeDeclarationOpt() { return _TypeDeclarationOpt; }
    /**
     * The value returned by <b>getPositionalVarOpt</b> may be <b>null</b>
     */
    public PositionalVar getPositionalVarOpt() { return _PositionalVarOpt; }
    public ASTNodeToken getin() { return _in; }
    public IExprSingle getExprSingle() { return _ExprSingle; }

    public ForClauseVarBinding(IToken leftIToken, IToken rightIToken,
                               ASTNodeToken _DOLLAR,
                               IVarName _VarName,
                               TypeDeclaration _TypeDeclarationOpt,
                               PositionalVar _PositionalVarOpt,
                               ASTNodeToken _in,
                               IExprSingle _ExprSingle)
    {
        super(leftIToken, rightIToken);

        this._DOLLAR = _DOLLAR;
        ((ASTNode) _DOLLAR).setParent(this);
        this._VarName = _VarName;
        ((ASTNode) _VarName).setParent(this);
        this._TypeDeclarationOpt = _TypeDeclarationOpt;
        if (_TypeDeclarationOpt != null) ((ASTNode) _TypeDeclarationOpt).setParent(this);
        this._PositionalVarOpt = _PositionalVarOpt;
        if (_PositionalVarOpt != null) ((ASTNode) _PositionalVarOpt).setParent(this);
        this._in = _in;
        ((ASTNode) _in).setParent(this);
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
        list.add(_DOLLAR);
        list.add(_VarName);
        list.add(_TypeDeclarationOpt);
        list.add(_PositionalVarOpt);
        list.add(_in);
        list.add(_ExprSingle);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ForClauseVarBinding)) return false;
        if (! super.equals(o)) return false;
        ForClauseVarBinding other = (ForClauseVarBinding) o;
        if (! _DOLLAR.equals(other._DOLLAR)) return false;
        if (! _VarName.equals(other._VarName)) return false;
        if (_TypeDeclarationOpt == null)
            if (other._TypeDeclarationOpt != null) return false;
            else; // continue
        else if (! _TypeDeclarationOpt.equals(other._TypeDeclarationOpt)) return false;
        if (_PositionalVarOpt == null)
            if (other._PositionalVarOpt != null) return false;
            else; // continue
        else if (! _PositionalVarOpt.equals(other._PositionalVarOpt)) return false;
        if (! _in.equals(other._in)) return false;
        if (! _ExprSingle.equals(other._ExprSingle)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_DOLLAR.hashCode());
        hash = hash * 31 + (_VarName.hashCode());
        hash = hash * 31 + (_TypeDeclarationOpt == null ? 0 : _TypeDeclarationOpt.hashCode());
        hash = hash * 31 + (_PositionalVarOpt == null ? 0 : _PositionalVarOpt.hashCode());
        hash = hash * 31 + (_in.hashCode());
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
            _DOLLAR.accept(v);
            _VarName.accept(v);
            if (_TypeDeclarationOpt != null) _TypeDeclarationOpt.accept(v);
            if (_PositionalVarOpt != null) _PositionalVarOpt.accept(v);
            _in.accept(v);
            _ExprSingle.accept(v);
        }
        v.endVisit(this);
    }
}


