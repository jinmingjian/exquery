
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
 *<li>Rule 158:  MultiplicativeExpr ::= MultiplicativeExpr TIMES UnionExpr
 *<li>Rule 159:  MultiplicativeExpr ::= MultiplicativeExpr div UnionExpr
 *<li>Rule 160:  MultiplicativeExpr ::= MultiplicativeExpr idiv UnionExpr
 *<li>Rule 161:  MultiplicativeExpr ::= MultiplicativeExpr mod UnionExpr
 *</b>
 */
public class MultiplicativeExpr extends ASTNode implements IMultiplicativeExpr
{
    private IMultiplicativeExpr _MultiplicativeExpr;
    private ASTNodeToken _TIMES;
    private IUnionExpr _UnionExpr;
    private ASTNodeToken _div;
    private ASTNodeToken _idiv;
    private ASTNodeToken _mod;

    /**
     * The value returned by <b>getMultiplicativeExpr</b> may be <b>null</b>
     */
    public IMultiplicativeExpr getMultiplicativeExpr() { return _MultiplicativeExpr; }
    /**
     * The value returned by <b>getTIMES</b> may be <b>null</b>
     */
    public ASTNodeToken getTIMES() { return _TIMES; }
    /**
     * The value returned by <b>getUnionExpr</b> may be <b>null</b>
     */
    public IUnionExpr getUnionExpr() { return _UnionExpr; }
    /**
     * The value returned by <b>getdiv</b> may be <b>null</b>
     */
    public ASTNodeToken getdiv() { return _div; }
    /**
     * The value returned by <b>getidiv</b> may be <b>null</b>
     */
    public ASTNodeToken getidiv() { return _idiv; }
    /**
     * The value returned by <b>getmod</b> may be <b>null</b>
     */
    public ASTNodeToken getmod() { return _mod; }

    public MultiplicativeExpr(IToken leftIToken, IToken rightIToken,
                              IMultiplicativeExpr _MultiplicativeExpr,
                              ASTNodeToken _TIMES,
                              IUnionExpr _UnionExpr,
                              ASTNodeToken _div,
                              ASTNodeToken _idiv,
                              ASTNodeToken _mod)
    {
        super(leftIToken, rightIToken);

        this._MultiplicativeExpr = _MultiplicativeExpr;
        if (_MultiplicativeExpr != null) ((ASTNode) _MultiplicativeExpr).setParent(this);
        this._TIMES = _TIMES;
        if (_TIMES != null) ((ASTNode) _TIMES).setParent(this);
        this._UnionExpr = _UnionExpr;
        if (_UnionExpr != null) ((ASTNode) _UnionExpr).setParent(this);
        this._div = _div;
        if (_div != null) ((ASTNode) _div).setParent(this);
        this._idiv = _idiv;
        if (_idiv != null) ((ASTNode) _idiv).setParent(this);
        this._mod = _mod;
        if (_mod != null) ((ASTNode) _mod).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_MultiplicativeExpr);
        list.add(_TIMES);
        list.add(_UnionExpr);
        list.add(_div);
        list.add(_idiv);
        list.add(_mod);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof MultiplicativeExpr)) return false;
        if (! super.equals(o)) return false;
        MultiplicativeExpr other = (MultiplicativeExpr) o;
        if (_MultiplicativeExpr == null)
            if (other._MultiplicativeExpr != null) return false;
            else; // continue
        else if (! _MultiplicativeExpr.equals(other._MultiplicativeExpr)) return false;
        if (_TIMES == null)
            if (other._TIMES != null) return false;
            else; // continue
        else if (! _TIMES.equals(other._TIMES)) return false;
        if (_UnionExpr == null)
            if (other._UnionExpr != null) return false;
            else; // continue
        else if (! _UnionExpr.equals(other._UnionExpr)) return false;
        if (_div == null)
            if (other._div != null) return false;
            else; // continue
        else if (! _div.equals(other._div)) return false;
        if (_idiv == null)
            if (other._idiv != null) return false;
            else; // continue
        else if (! _idiv.equals(other._idiv)) return false;
        if (_mod == null)
            if (other._mod != null) return false;
            else; // continue
        else if (! _mod.equals(other._mod)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_MultiplicativeExpr == null ? 0 : _MultiplicativeExpr.hashCode());
        hash = hash * 31 + (_TIMES == null ? 0 : _TIMES.hashCode());
        hash = hash * 31 + (_UnionExpr == null ? 0 : _UnionExpr.hashCode());
        hash = hash * 31 + (_div == null ? 0 : _div.hashCode());
        hash = hash * 31 + (_idiv == null ? 0 : _idiv.hashCode());
        hash = hash * 31 + (_mod == null ? 0 : _mod.hashCode());
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
            if (_MultiplicativeExpr != null) _MultiplicativeExpr.accept(v);
            if (_TIMES != null) _TIMES.accept(v);
            if (_UnionExpr != null) _UnionExpr.accept(v);
            if (_div != null) _div.accept(v);
            if (_idiv != null) _idiv.accept(v);
            if (_mod != null) _mod.accept(v);
        }
        v.endVisit(this);
    }
}


