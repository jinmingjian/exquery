
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
 *<li>Rule 36:  OrderingModeDecl ::= declare ordering ordered
 *<li>Rule 37:  OrderingModeDecl ::= declare ordering unordered
 *</b>
 */
public class OrderingModeDecl extends ASTNode implements IOrderingModeDecl
{
    private ASTNodeToken _declare;
    private ASTNodeToken _ordering;
    private ASTNodeToken _ordered;
    private ASTNodeToken _unordered;

    /**
     * The value returned by <b>getdeclare</b> may be <b>null</b>
     */
    public ASTNodeToken getdeclare() { return _declare; }
    public ASTNodeToken getordering() { return _ordering; }
    /**
     * The value returned by <b>getordered</b> may be <b>null</b>
     */
    public ASTNodeToken getordered() { return _ordered; }
    /**
     * The value returned by <b>getunordered</b> may be <b>null</b>
     */
    public ASTNodeToken getunordered() { return _unordered; }

    public OrderingModeDecl(IToken leftIToken, IToken rightIToken,
                            ASTNodeToken _declare,
                            ASTNodeToken _ordering,
                            ASTNodeToken _ordered,
                            ASTNodeToken _unordered)
    {
        super(leftIToken, rightIToken);

        this._declare = _declare;
        if (_declare != null) ((ASTNode) _declare).setParent(this);
        this._ordering = _ordering;
        ((ASTNode) _ordering).setParent(this);
        this._ordered = _ordered;
        if (_ordered != null) ((ASTNode) _ordered).setParent(this);
        this._unordered = _unordered;
        if (_unordered != null) ((ASTNode) _unordered).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_declare);
        list.add(_ordering);
        list.add(_ordered);
        list.add(_unordered);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof OrderingModeDecl)) return false;
        if (! super.equals(o)) return false;
        OrderingModeDecl other = (OrderingModeDecl) o;
        if (_declare == null)
            if (other._declare != null) return false;
            else; // continue
        else if (! _declare.equals(other._declare)) return false;
        if (! _ordering.equals(other._ordering)) return false;
        if (_ordered == null)
            if (other._ordered != null) return false;
            else; // continue
        else if (! _ordered.equals(other._ordered)) return false;
        if (_unordered == null)
            if (other._unordered != null) return false;
            else; // continue
        else if (! _unordered.equals(other._unordered)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_declare == null ? 0 : _declare.hashCode());
        hash = hash * 31 + (_ordering.hashCode());
        hash = hash * 31 + (_ordered == null ? 0 : _ordered.hashCode());
        hash = hash * 31 + (_unordered == null ? 0 : _unordered.hashCode());
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
            _ordering.accept(v);
            if (_ordered != null) _ordered.accept(v);
            if (_unordered != null) _unordered.accept(v);
        }
        v.endVisit(this);
    }
}


