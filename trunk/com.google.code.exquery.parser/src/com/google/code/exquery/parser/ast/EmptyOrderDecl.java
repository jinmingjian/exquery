
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
 *<li>Rule 38:  EmptyOrderDecl ::= declare default order empty greatest
 *<li>Rule 39:  EmptyOrderDecl ::= declare default order empty least
 *</b>
 */
public class EmptyOrderDecl extends ASTNode implements IEmptyOrderDecl
{
    private ASTNodeToken _declare;
    private ASTNodeToken _default;
    private ASTNodeToken _order;
    private ASTNodeToken _empty;
    private ASTNodeToken _greatest;
    private ASTNodeToken _least;

    /**
     * The value returned by <b>getdeclare</b> may be <b>null</b>
     */
    public ASTNodeToken getdeclare() { return _declare; }
    public ASTNodeToken getdefault() { return _default; }
    /**
     * The value returned by <b>getorder</b> may be <b>null</b>
     */
    public ASTNodeToken getorder() { return _order; }
    public ASTNodeToken getempty() { return _empty; }
    /**
     * The value returned by <b>getgreatest</b> may be <b>null</b>
     */
    public ASTNodeToken getgreatest() { return _greatest; }
    /**
     * The value returned by <b>getleast</b> may be <b>null</b>
     */
    public ASTNodeToken getleast() { return _least; }

    public EmptyOrderDecl(IToken leftIToken, IToken rightIToken,
                          ASTNodeToken _declare,
                          ASTNodeToken _default,
                          ASTNodeToken _order,
                          ASTNodeToken _empty,
                          ASTNodeToken _greatest,
                          ASTNodeToken _least)
    {
        super(leftIToken, rightIToken);

        this._declare = _declare;
        if (_declare != null) ((ASTNode) _declare).setParent(this);
        this._default = _default;
        ((ASTNode) _default).setParent(this);
        this._order = _order;
        if (_order != null) ((ASTNode) _order).setParent(this);
        this._empty = _empty;
        ((ASTNode) _empty).setParent(this);
        this._greatest = _greatest;
        if (_greatest != null) ((ASTNode) _greatest).setParent(this);
        this._least = _least;
        if (_least != null) ((ASTNode) _least).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_declare);
        list.add(_default);
        list.add(_order);
        list.add(_empty);
        list.add(_greatest);
        list.add(_least);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof EmptyOrderDecl)) return false;
        if (! super.equals(o)) return false;
        EmptyOrderDecl other = (EmptyOrderDecl) o;
        if (_declare == null)
            if (other._declare != null) return false;
            else; // continue
        else if (! _declare.equals(other._declare)) return false;
        if (! _default.equals(other._default)) return false;
        if (_order == null)
            if (other._order != null) return false;
            else; // continue
        else if (! _order.equals(other._order)) return false;
        if (! _empty.equals(other._empty)) return false;
        if (_greatest == null)
            if (other._greatest != null) return false;
            else; // continue
        else if (! _greatest.equals(other._greatest)) return false;
        if (_least == null)
            if (other._least != null) return false;
            else; // continue
        else if (! _least.equals(other._least)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_declare == null ? 0 : _declare.hashCode());
        hash = hash * 31 + (_default.hashCode());
        hash = hash * 31 + (_order == null ? 0 : _order.hashCode());
        hash = hash * 31 + (_empty.hashCode());
        hash = hash * 31 + (_greatest == null ? 0 : _greatest.hashCode());
        hash = hash * 31 + (_least == null ? 0 : _least.hashCode());
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
            if (_declare != null) _declare.accept(v);
            _default.accept(v);
            if (_order != null) _order.accept(v);
            _empty.accept(v);
            if (_greatest != null) _greatest.accept(v);
            if (_least != null) _least.accept(v);
        }
        v.endVisit(this);
    }
}


