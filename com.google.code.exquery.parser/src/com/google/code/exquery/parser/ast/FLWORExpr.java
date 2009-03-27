
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
 *<li>Rule 90:  FLWORExpr ::= ForOrLetClauseList WhereClauseOpt OrderByClauseOpt return ExprSingle
 *</b>
 */
public class FLWORExpr extends ASTNode implements IFLWORExpr
{
    private ForOrLetClauseList _ForOrLetClauseList;
    private WhereClause _WhereClauseOpt;
    private OrderByClause _OrderByClauseOpt;
    private ASTNodeToken _return;
    private IExprSingle _ExprSingle;

    public ForOrLetClauseList getForOrLetClauseList() { return _ForOrLetClauseList; }
    /**
     * The value returned by <b>getWhereClauseOpt</b> may be <b>null</b>
     */
    public WhereClause getWhereClauseOpt() { return _WhereClauseOpt; }
    /**
     * The value returned by <b>getOrderByClauseOpt</b> may be <b>null</b>
     */
    public OrderByClause getOrderByClauseOpt() { return _OrderByClauseOpt; }
    public ASTNodeToken getreturn() { return _return; }
    public IExprSingle getExprSingle() { return _ExprSingle; }

    public FLWORExpr(IToken leftIToken, IToken rightIToken,
                     ForOrLetClauseList _ForOrLetClauseList,
                     WhereClause _WhereClauseOpt,
                     OrderByClause _OrderByClauseOpt,
                     ASTNodeToken _return,
                     IExprSingle _ExprSingle)
    {
        super(leftIToken, rightIToken);

        this._ForOrLetClauseList = _ForOrLetClauseList;
        ((ASTNode) _ForOrLetClauseList).setParent(this);
        this._WhereClauseOpt = _WhereClauseOpt;
        if (_WhereClauseOpt != null) ((ASTNode) _WhereClauseOpt).setParent(this);
        this._OrderByClauseOpt = _OrderByClauseOpt;
        if (_OrderByClauseOpt != null) ((ASTNode) _OrderByClauseOpt).setParent(this);
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
        list.add(_ForOrLetClauseList);
        list.add(_WhereClauseOpt);
        list.add(_OrderByClauseOpt);
        list.add(_return);
        list.add(_ExprSingle);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof FLWORExpr)) return false;
        if (! super.equals(o)) return false;
        FLWORExpr other = (FLWORExpr) o;
        if (! _ForOrLetClauseList.equals(other._ForOrLetClauseList)) return false;
        if (_WhereClauseOpt == null)
            if (other._WhereClauseOpt != null) return false;
            else; // continue
        else if (! _WhereClauseOpt.equals(other._WhereClauseOpt)) return false;
        if (_OrderByClauseOpt == null)
            if (other._OrderByClauseOpt != null) return false;
            else; // continue
        else if (! _OrderByClauseOpt.equals(other._OrderByClauseOpt)) return false;
        if (! _return.equals(other._return)) return false;
        if (! _ExprSingle.equals(other._ExprSingle)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_ForOrLetClauseList.hashCode());
        hash = hash * 31 + (_WhereClauseOpt == null ? 0 : _WhereClauseOpt.hashCode());
        hash = hash * 31 + (_OrderByClauseOpt == null ? 0 : _OrderByClauseOpt.hashCode());
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
            _ForOrLetClauseList.accept(v);
            if (_WhereClauseOpt != null) _WhereClauseOpt.accept(v);
            if (_OrderByClauseOpt != null) _OrderByClauseOpt.accept(v);
            _return.accept(v);
            _ExprSingle.accept(v);
        }
        v.endVisit(this);
    }
}


