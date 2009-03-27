
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
 *<li>Rule 130:  CaseClause ::= case CaseClauseVarNameDeclOpt SequenceType return ExprSingle
 *</b>
 */
public class CaseClause extends ASTNode implements ICaseClause
{
    private ASTNodeToken _case;
    private CaseClauseVarNameDeclOpt _CaseClauseVarNameDeclOpt;
    private ISequenceType _SequenceType;
    private ASTNodeToken _return;
    private IExprSingle _ExprSingle;

    public ASTNodeToken getcase() { return _case; }
    /**
     * The value returned by <b>getCaseClauseVarNameDeclOpt</b> may be <b>null</b>
     */
    public CaseClauseVarNameDeclOpt getCaseClauseVarNameDeclOpt() { return _CaseClauseVarNameDeclOpt; }
    public ISequenceType getSequenceType() { return _SequenceType; }
    public ASTNodeToken getreturn() { return _return; }
    public IExprSingle getExprSingle() { return _ExprSingle; }

    public CaseClause(IToken leftIToken, IToken rightIToken,
                      ASTNodeToken _case,
                      CaseClauseVarNameDeclOpt _CaseClauseVarNameDeclOpt,
                      ISequenceType _SequenceType,
                      ASTNodeToken _return,
                      IExprSingle _ExprSingle)
    {
        super(leftIToken, rightIToken);

        this._case = _case;
        ((ASTNode) _case).setParent(this);
        this._CaseClauseVarNameDeclOpt = _CaseClauseVarNameDeclOpt;
        if (_CaseClauseVarNameDeclOpt != null) ((ASTNode) _CaseClauseVarNameDeclOpt).setParent(this);
        this._SequenceType = _SequenceType;
        ((ASTNode) _SequenceType).setParent(this);
        this._return = _return;
        ((ASTNode) _return).setParent(this);
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
        list.add(_case);
        list.add(_CaseClauseVarNameDeclOpt);
        list.add(_SequenceType);
        list.add(_return);
        list.add(_ExprSingle);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof CaseClause)) return false;
        if (! super.equals(o)) return false;
        CaseClause other = (CaseClause) o;
        if (! _case.equals(other._case)) return false;
        if (_CaseClauseVarNameDeclOpt == null)
            if (other._CaseClauseVarNameDeclOpt != null) return false;
            else; // continue
        else if (! _CaseClauseVarNameDeclOpt.equals(other._CaseClauseVarNameDeclOpt)) return false;
        if (! _SequenceType.equals(other._SequenceType)) return false;
        if (! _return.equals(other._return)) return false;
        if (! _ExprSingle.equals(other._ExprSingle)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_case.hashCode());
        hash = hash * 31 + (_CaseClauseVarNameDeclOpt == null ? 0 : _CaseClauseVarNameDeclOpt.hashCode());
        hash = hash * 31 + (_SequenceType.hashCode());
        hash = hash * 31 + (_return.hashCode());
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
            _case.accept(v);
            if (_CaseClauseVarNameDeclOpt != null) _CaseClauseVarNameDeclOpt.accept(v);
            _SequenceType.accept(v);
            _return.accept(v);
            _ExprSingle.accept(v);
        }
        v.endVisit(this);
    }
}


