
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
 *<li>Rule 155:  AdditiveExpr ::= AdditiveExpr PLUS MultiplicativeExpr
 *<li>Rule 156:  AdditiveExpr ::= AdditiveExpr MINUS MultiplicativeExpr
 *</b>
 */
public class AdditiveExpr extends ASTNode implements IAdditiveExpr
{
    private IAdditiveExpr _AdditiveExpr;
    private ASTNodeToken _PLUS;
    private IMultiplicativeExpr _MultiplicativeExpr;
    private ASTNodeToken _MINUS;

    /**
     * The value returned by <b>getAdditiveExpr</b> may be <b>null</b>
     */
    public IAdditiveExpr getAdditiveExpr() { return _AdditiveExpr; }
    /**
     * The value returned by <b>getPLUS</b> may be <b>null</b>
     */
    public ASTNodeToken getPLUS() { return _PLUS; }
    /**
     * The value returned by <b>getMultiplicativeExpr</b> may be <b>null</b>
     */
    public IMultiplicativeExpr getMultiplicativeExpr() { return _MultiplicativeExpr; }
    /**
     * The value returned by <b>getMINUS</b> may be <b>null</b>
     */
    public ASTNodeToken getMINUS() { return _MINUS; }

    public AdditiveExpr(IToken leftIToken, IToken rightIToken,
                        IAdditiveExpr _AdditiveExpr,
                        ASTNodeToken _PLUS,
                        IMultiplicativeExpr _MultiplicativeExpr,
                        ASTNodeToken _MINUS)
    {
        super(leftIToken, rightIToken);

        this._AdditiveExpr = _AdditiveExpr;
        if (_AdditiveExpr != null) ((ASTNode) _AdditiveExpr).setParent(this);
        this._PLUS = _PLUS;
        if (_PLUS != null) ((ASTNode) _PLUS).setParent(this);
        this._MultiplicativeExpr = _MultiplicativeExpr;
        if (_MultiplicativeExpr != null) ((ASTNode) _MultiplicativeExpr).setParent(this);
        this._MINUS = _MINUS;
        if (_MINUS != null) ((ASTNode) _MINUS).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_AdditiveExpr);
        list.add(_PLUS);
        list.add(_MultiplicativeExpr);
        list.add(_MINUS);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof AdditiveExpr)) return false;
        if (! super.equals(o)) return false;
        AdditiveExpr other = (AdditiveExpr) o;
        if (_AdditiveExpr == null)
            if (other._AdditiveExpr != null) return false;
            else; // continue
        else if (! _AdditiveExpr.equals(other._AdditiveExpr)) return false;
        if (_PLUS == null)
            if (other._PLUS != null) return false;
            else; // continue
        else if (! _PLUS.equals(other._PLUS)) return false;
        if (_MultiplicativeExpr == null)
            if (other._MultiplicativeExpr != null) return false;
            else; // continue
        else if (! _MultiplicativeExpr.equals(other._MultiplicativeExpr)) return false;
        if (_MINUS == null)
            if (other._MINUS != null) return false;
            else; // continue
        else if (! _MINUS.equals(other._MINUS)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_AdditiveExpr == null ? 0 : _AdditiveExpr.hashCode());
        hash = hash * 31 + (_PLUS == null ? 0 : _PLUS.hashCode());
        hash = hash * 31 + (_MultiplicativeExpr == null ? 0 : _MultiplicativeExpr.hashCode());
        hash = hash * 31 + (_MINUS == null ? 0 : _MINUS.hashCode());
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
            if (_AdditiveExpr != null) _AdditiveExpr.accept(v);
            if (_PLUS != null) _PLUS.accept(v);
            if (_MultiplicativeExpr != null) _MultiplicativeExpr.accept(v);
            if (_MINUS != null) _MINUS.accept(v);
        }
        v.endVisit(this);
    }
}


