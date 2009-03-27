
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
 *<li>Rule 126:  TypeswitchExpr ::= typeswitch LEFTPAREN Expr RIGHTPAREN CaseClauseList default return ExprSingle
 *<li>Rule 127:  TypeswitchExpr ::= typeswitch LEFTPAREN Expr RIGHTPAREN CaseClauseList default DOLLAR VarName return ExprSingle
 *</b>
 */
public class TypeswitchExpr extends ASTNode implements ITypeswitchExpr
{
    private ASTNodeToken _typeswitch;
    private ASTNodeToken _LEFTPAREN;
    private ExprSingleList _Expr;
    private ASTNodeToken _RIGHTPAREN;
    private CaseClauseList _CaseClauseList;
    private ASTNodeToken _default;
    private ASTNodeToken _return;
    private IExprSingle _ExprSingle;
    private ASTNodeToken _DOLLAR;
    private IVarName _VarName;

    /**
     * The value returned by <b>gettypeswitch</b> may be <b>null</b>
     */
    public ASTNodeToken gettypeswitch() { return _typeswitch; }
    public ASTNodeToken getLEFTPAREN() { return _LEFTPAREN; }
    /**
     * The value returned by <b>getExpr</b> may be <b>null</b>
     */
    public ExprSingleList getExpr() { return _Expr; }
    public ASTNodeToken getRIGHTPAREN() { return _RIGHTPAREN; }
    public CaseClauseList getCaseClauseList() { return _CaseClauseList; }
    public ASTNodeToken getdefault() { return _default; }
    public ASTNodeToken getreturn() { return _return; }
    /**
     * The value returned by <b>getExprSingle</b> may be <b>null</b>
     */
    public IExprSingle getExprSingle() { return _ExprSingle; }
    /**
     * The value returned by <b>getDOLLAR</b> may be <b>null</b>
     */
    public ASTNodeToken getDOLLAR() { return _DOLLAR; }
    /**
     * The value returned by <b>getVarName</b> may be <b>null</b>
     */
    public IVarName getVarName() { return _VarName; }

    public TypeswitchExpr(IToken leftIToken, IToken rightIToken,
                          ASTNodeToken _typeswitch,
                          ASTNodeToken _LEFTPAREN,
                          ExprSingleList _Expr,
                          ASTNodeToken _RIGHTPAREN,
                          CaseClauseList _CaseClauseList,
                          ASTNodeToken _default,
                          ASTNodeToken _return,
                          IExprSingle _ExprSingle,
                          ASTNodeToken _DOLLAR,
                          IVarName _VarName)
    {
        super(leftIToken, rightIToken);

        this._typeswitch = _typeswitch;
        if (_typeswitch != null) ((ASTNode) _typeswitch).setParent(this);
        this._LEFTPAREN = _LEFTPAREN;
        ((ASTNode) _LEFTPAREN).setParent(this);
        this._Expr = _Expr;
        if (_Expr != null) ((ASTNode) _Expr).setParent(this);
        this._RIGHTPAREN = _RIGHTPAREN;
        ((ASTNode) _RIGHTPAREN).setParent(this);
        this._CaseClauseList = _CaseClauseList;
        ((ASTNode) _CaseClauseList).setParent(this);
        this._default = _default;
        ((ASTNode) _default).setParent(this);
        this._return = _return;
        ((ASTNode) _return).setParent(this);
        this._ExprSingle = _ExprSingle;
        if (_ExprSingle != null) ((ASTNode) _ExprSingle).setParent(this);
        this._DOLLAR = _DOLLAR;
        if (_DOLLAR != null) ((ASTNode) _DOLLAR).setParent(this);
        this._VarName = _VarName;
        if (_VarName != null) ((ASTNode) _VarName).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_typeswitch);
        list.add(_LEFTPAREN);
        list.add(_Expr);
        list.add(_RIGHTPAREN);
        list.add(_CaseClauseList);
        list.add(_default);
        list.add(_return);
        list.add(_ExprSingle);
        list.add(_DOLLAR);
        list.add(_VarName);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof TypeswitchExpr)) return false;
        if (! super.equals(o)) return false;
        TypeswitchExpr other = (TypeswitchExpr) o;
        if (_typeswitch == null)
            if (other._typeswitch != null) return false;
            else; // continue
        else if (! _typeswitch.equals(other._typeswitch)) return false;
        if (! _LEFTPAREN.equals(other._LEFTPAREN)) return false;
        if (_Expr == null)
            if (other._Expr != null) return false;
            else; // continue
        else if (! _Expr.equals(other._Expr)) return false;
        if (! _RIGHTPAREN.equals(other._RIGHTPAREN)) return false;
        if (! _CaseClauseList.equals(other._CaseClauseList)) return false;
        if (! _default.equals(other._default)) return false;
        if (! _return.equals(other._return)) return false;
        if (_ExprSingle == null)
            if (other._ExprSingle != null) return false;
            else; // continue
        else if (! _ExprSingle.equals(other._ExprSingle)) return false;
        if (_DOLLAR == null)
            if (other._DOLLAR != null) return false;
            else; // continue
        else if (! _DOLLAR.equals(other._DOLLAR)) return false;
        if (_VarName == null)
            if (other._VarName != null) return false;
            else; // continue
        else if (! _VarName.equals(other._VarName)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_typeswitch == null ? 0 : _typeswitch.hashCode());
        hash = hash * 31 + (_LEFTPAREN.hashCode());
        hash = hash * 31 + (_Expr == null ? 0 : _Expr.hashCode());
        hash = hash * 31 + (_RIGHTPAREN.hashCode());
        hash = hash * 31 + (_CaseClauseList.hashCode());
        hash = hash * 31 + (_default.hashCode());
        hash = hash * 31 + (_return.hashCode());
        hash = hash * 31 + (_ExprSingle == null ? 0 : _ExprSingle.hashCode());
        hash = hash * 31 + (_DOLLAR == null ? 0 : _DOLLAR.hashCode());
        hash = hash * 31 + (_VarName == null ? 0 : _VarName.hashCode());
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
            if (_typeswitch != null) _typeswitch.accept(v);
            _LEFTPAREN.accept(v);
            if (_Expr != null) _Expr.accept(v);
            _RIGHTPAREN.accept(v);
            _CaseClauseList.accept(v);
            _default.accept(v);
            _return.accept(v);
            if (_ExprSingle != null) _ExprSingle.accept(v);
            if (_DOLLAR != null) _DOLLAR.accept(v);
            if (_VarName != null) _VarName.accept(v);
        }
        v.endVisit(this);
    }
}


