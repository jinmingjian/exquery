
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
 *<li>Rule 300:  CompElemConstructor ::= element QName LEFTBRACE RIGHTBRACE
 *<li>Rule 301:  CompElemConstructor ::= element QName LEFTBRACE ContentExpr RIGHTBRACE
 *<li>Rule 302:  CompElemConstructor ::= element LEFTBRACE Expr RIGHTBRACE LEFTBRACE RIGHTBRACE
 *<li>Rule 303:  CompElemConstructor ::= element LEFTBRACE Expr RIGHTBRACE LEFTBRACE ContentExpr RIGHTBRACE
 *</b>
 */
public class CompElemConstructor extends ASTNode implements ICompElemConstructor
{
    private ASTNodeToken _element;
    private IQName _QName;
    private ASTNodeToken _LEFTBRACE;
    private ASTNodeToken _RIGHTBRACE;
    private ExprSingleList _ContentExpr;
    private ExprSingleList _Expr;
    private ASTNodeToken _LEFTBRACE5;
    private ASTNodeToken _RIGHTBRACE6;
    private ASTNodeToken _RIGHTBRACE7;

    /**
     * The value returned by <b>getelement</b> may be <b>null</b>
     */
    public ASTNodeToken getelement() { return _element; }
    /**
     * The value returned by <b>getQName</b> may be <b>null</b>
     */
    public IQName getQName() { return _QName; }
    /**
     * The value returned by <b>getLEFTBRACE</b> may be <b>null</b>
     */
    public ASTNodeToken getLEFTBRACE() { return _LEFTBRACE; }
    public ASTNodeToken getRIGHTBRACE() { return _RIGHTBRACE; }
    /**
     * The value returned by <b>getContentExpr</b> may be <b>null</b>
     */
    public ExprSingleList getContentExpr() { return _ContentExpr; }
    /**
     * The value returned by <b>getExpr</b> may be <b>null</b>
     */
    public ExprSingleList getExpr() { return _Expr; }
    /**
     * The value returned by <b>getLEFTBRACE5</b> may be <b>null</b>
     */
    public ASTNodeToken getLEFTBRACE5() { return _LEFTBRACE5; }
    /**
     * The value returned by <b>getRIGHTBRACE6</b> may be <b>null</b>
     */
    public ASTNodeToken getRIGHTBRACE6() { return _RIGHTBRACE6; }
    /**
     * The value returned by <b>getRIGHTBRACE7</b> may be <b>null</b>
     */
    public ASTNodeToken getRIGHTBRACE7() { return _RIGHTBRACE7; }

    public CompElemConstructor(IToken leftIToken, IToken rightIToken,
                               ASTNodeToken _element,
                               IQName _QName,
                               ASTNodeToken _LEFTBRACE,
                               ASTNodeToken _RIGHTBRACE,
                               ExprSingleList _ContentExpr,
                               ExprSingleList _Expr,
                               ASTNodeToken _LEFTBRACE5,
                               ASTNodeToken _RIGHTBRACE6,
                               ASTNodeToken _RIGHTBRACE7)
    {
        super(leftIToken, rightIToken);

        this._element = _element;
        if (_element != null) ((ASTNode) _element).setParent(this);
        this._QName = _QName;
        if (_QName != null) ((ASTNode) _QName).setParent(this);
        this._LEFTBRACE = _LEFTBRACE;
        if (_LEFTBRACE != null) ((ASTNode) _LEFTBRACE).setParent(this);
        this._RIGHTBRACE = _RIGHTBRACE;
        ((ASTNode) _RIGHTBRACE).setParent(this);
        this._ContentExpr = _ContentExpr;
        if (_ContentExpr != null) ((ASTNode) _ContentExpr).setParent(this);
        this._Expr = _Expr;
        if (_Expr != null) ((ASTNode) _Expr).setParent(this);
        this._LEFTBRACE5 = _LEFTBRACE5;
        if (_LEFTBRACE5 != null) ((ASTNode) _LEFTBRACE5).setParent(this);
        this._RIGHTBRACE6 = _RIGHTBRACE6;
        if (_RIGHTBRACE6 != null) ((ASTNode) _RIGHTBRACE6).setParent(this);
        this._RIGHTBRACE7 = _RIGHTBRACE7;
        if (_RIGHTBRACE7 != null) ((ASTNode) _RIGHTBRACE7).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_element);
        list.add(_QName);
        list.add(_LEFTBRACE);
        list.add(_RIGHTBRACE);
        list.add(_ContentExpr);
        list.add(_Expr);
        list.add(_LEFTBRACE5);
        list.add(_RIGHTBRACE6);
        list.add(_RIGHTBRACE7);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof CompElemConstructor)) return false;
        if (! super.equals(o)) return false;
        CompElemConstructor other = (CompElemConstructor) o;
        if (_element == null)
            if (other._element != null) return false;
            else; // continue
        else if (! _element.equals(other._element)) return false;
        if (_QName == null)
            if (other._QName != null) return false;
            else; // continue
        else if (! _QName.equals(other._QName)) return false;
        if (_LEFTBRACE == null)
            if (other._LEFTBRACE != null) return false;
            else; // continue
        else if (! _LEFTBRACE.equals(other._LEFTBRACE)) return false;
        if (! _RIGHTBRACE.equals(other._RIGHTBRACE)) return false;
        if (_ContentExpr == null)
            if (other._ContentExpr != null) return false;
            else; // continue
        else if (! _ContentExpr.equals(other._ContentExpr)) return false;
        if (_Expr == null)
            if (other._Expr != null) return false;
            else; // continue
        else if (! _Expr.equals(other._Expr)) return false;
        if (_LEFTBRACE5 == null)
            if (other._LEFTBRACE5 != null) return false;
            else; // continue
        else if (! _LEFTBRACE5.equals(other._LEFTBRACE5)) return false;
        if (_RIGHTBRACE6 == null)
            if (other._RIGHTBRACE6 != null) return false;
            else; // continue
        else if (! _RIGHTBRACE6.equals(other._RIGHTBRACE6)) return false;
        if (_RIGHTBRACE7 == null)
            if (other._RIGHTBRACE7 != null) return false;
            else; // continue
        else if (! _RIGHTBRACE7.equals(other._RIGHTBRACE7)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_element == null ? 0 : _element.hashCode());
        hash = hash * 31 + (_QName == null ? 0 : _QName.hashCode());
        hash = hash * 31 + (_LEFTBRACE == null ? 0 : _LEFTBRACE.hashCode());
        hash = hash * 31 + (_RIGHTBRACE.hashCode());
        hash = hash * 31 + (_ContentExpr == null ? 0 : _ContentExpr.hashCode());
        hash = hash * 31 + (_Expr == null ? 0 : _Expr.hashCode());
        hash = hash * 31 + (_LEFTBRACE5 == null ? 0 : _LEFTBRACE5.hashCode());
        hash = hash * 31 + (_RIGHTBRACE6 == null ? 0 : _RIGHTBRACE6.hashCode());
        hash = hash * 31 + (_RIGHTBRACE7 == null ? 0 : _RIGHTBRACE7.hashCode());
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
            if (_element != null) _element.accept(v);
            if (_QName != null) _QName.accept(v);
            if (_LEFTBRACE != null) _LEFTBRACE.accept(v);
            _RIGHTBRACE.accept(v);
            if (_ContentExpr != null) _ContentExpr.accept(v);
            if (_Expr != null) _Expr.accept(v);
            if (_LEFTBRACE5 != null) _LEFTBRACE5.accept(v);
            if (_RIGHTBRACE6 != null) _RIGHTBRACE6.accept(v);
            if (_RIGHTBRACE7 != null) _RIGHTBRACE7.accept(v);
        }
        v.endVisit(this);
    }
}


