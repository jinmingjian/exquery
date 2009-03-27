
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
 *<li>Rule 185:  ValidateExpr ::= validate LEFTBRACE Expr RIGHTBRACE
 *<li>Rule 186:  ValidateExpr ::= validate ValidationMode LEFTBRACE Expr RIGHTBRACE
 *</b>
 */
public class ValidateExpr extends ASTNode implements IValidateExpr
{
    private ASTNodeToken _validate;
    private ASTNodeToken _LEFTBRACE;
    private ExprSingleList _Expr;
    private ASTNodeToken _RIGHTBRACE;
    private IValidationMode _ValidationMode;

    /**
     * The value returned by <b>getvalidate</b> may be <b>null</b>
     */
    public ASTNodeToken getvalidate() { return _validate; }
    /**
     * The value returned by <b>getLEFTBRACE</b> may be <b>null</b>
     */
    public ASTNodeToken getLEFTBRACE() { return _LEFTBRACE; }
    /**
     * The value returned by <b>getExpr</b> may be <b>null</b>
     */
    public ExprSingleList getExpr() { return _Expr; }
    public ASTNodeToken getRIGHTBRACE() { return _RIGHTBRACE; }
    /**
     * The value returned by <b>getValidationMode</b> may be <b>null</b>
     */
    public IValidationMode getValidationMode() { return _ValidationMode; }

    public ValidateExpr(IToken leftIToken, IToken rightIToken,
                        ASTNodeToken _validate,
                        ASTNodeToken _LEFTBRACE,
                        ExprSingleList _Expr,
                        ASTNodeToken _RIGHTBRACE,
                        IValidationMode _ValidationMode)
    {
        super(leftIToken, rightIToken);

        this._validate = _validate;
        if (_validate != null) ((ASTNode) _validate).setParent(this);
        this._LEFTBRACE = _LEFTBRACE;
        if (_LEFTBRACE != null) ((ASTNode) _LEFTBRACE).setParent(this);
        this._Expr = _Expr;
        if (_Expr != null) ((ASTNode) _Expr).setParent(this);
        this._RIGHTBRACE = _RIGHTBRACE;
        ((ASTNode) _RIGHTBRACE).setParent(this);
        this._ValidationMode = _ValidationMode;
        if (_ValidationMode != null) ((ASTNode) _ValidationMode).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_validate);
        list.add(_LEFTBRACE);
        list.add(_Expr);
        list.add(_RIGHTBRACE);
        list.add(_ValidationMode);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ValidateExpr)) return false;
        if (! super.equals(o)) return false;
        ValidateExpr other = (ValidateExpr) o;
        if (_validate == null)
            if (other._validate != null) return false;
            else; // continue
        else if (! _validate.equals(other._validate)) return false;
        if (_LEFTBRACE == null)
            if (other._LEFTBRACE != null) return false;
            else; // continue
        else if (! _LEFTBRACE.equals(other._LEFTBRACE)) return false;
        if (_Expr == null)
            if (other._Expr != null) return false;
            else; // continue
        else if (! _Expr.equals(other._Expr)) return false;
        if (! _RIGHTBRACE.equals(other._RIGHTBRACE)) return false;
        if (_ValidationMode == null)
            if (other._ValidationMode != null) return false;
            else; // continue
        else if (! _ValidationMode.equals(other._ValidationMode)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_validate == null ? 0 : _validate.hashCode());
        hash = hash * 31 + (_LEFTBRACE == null ? 0 : _LEFTBRACE.hashCode());
        hash = hash * 31 + (_Expr == null ? 0 : _Expr.hashCode());
        hash = hash * 31 + (_RIGHTBRACE.hashCode());
        hash = hash * 31 + (_ValidationMode == null ? 0 : _ValidationMode.hashCode());
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
            if (_validate != null) _validate.accept(v);
            if (_LEFTBRACE != null) _LEFTBRACE.accept(v);
            if (_Expr != null) _Expr.accept(v);
            _RIGHTBRACE.accept(v);
            if (_ValidationMode != null) _ValidationMode.accept(v);
        }
        v.endVisit(this);
    }
}


