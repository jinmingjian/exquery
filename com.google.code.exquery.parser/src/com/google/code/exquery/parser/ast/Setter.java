
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
 *<li>Rule 21:  Setter ::= BoundarySpaceDecl
 *<li>Rule 22:  Setter ::= DefaultCollationDecl
 *<li>Rule 23:  Setter ::= BaseURIDecl
 *<li>Rule 24:  Setter ::= ConstructionDecl
 *<li>Rule 25:  Setter ::= OrderingModeDecl
 *<li>Rule 26:  Setter ::= EmptyOrderDecl
 *<li>Rule 27:  Setter ::= CopyNamespacesDecl
 *</b>
 */
public class Setter extends ASTNode implements ISetter
{
    private BoundarySpaceDecl _BoundarySpaceDecl;
    private DefaultCollationDecl _DefaultCollationDecl;
    private BaseURIDecl _BaseURIDecl;
    private ConstructionDecl _ConstructionDecl;
    private OrderingModeDecl _OrderingModeDecl;
    private EmptyOrderDecl _EmptyOrderDecl;
    private CopyNamespacesDecl _CopyNamespacesDecl;

    /**
     * The value returned by <b>getBoundarySpaceDecl</b> may be <b>null</b>
     */
    public BoundarySpaceDecl getBoundarySpaceDecl() { return _BoundarySpaceDecl; }
    /**
     * The value returned by <b>getDefaultCollationDecl</b> may be <b>null</b>
     */
    public DefaultCollationDecl getDefaultCollationDecl() { return _DefaultCollationDecl; }
    /**
     * The value returned by <b>getBaseURIDecl</b> may be <b>null</b>
     */
    public BaseURIDecl getBaseURIDecl() { return _BaseURIDecl; }
    /**
     * The value returned by <b>getConstructionDecl</b> may be <b>null</b>
     */
    public ConstructionDecl getConstructionDecl() { return _ConstructionDecl; }
    /**
     * The value returned by <b>getOrderingModeDecl</b> may be <b>null</b>
     */
    public OrderingModeDecl getOrderingModeDecl() { return _OrderingModeDecl; }
    /**
     * The value returned by <b>getEmptyOrderDecl</b> may be <b>null</b>
     */
    public EmptyOrderDecl getEmptyOrderDecl() { return _EmptyOrderDecl; }
    /**
     * The value returned by <b>getCopyNamespacesDecl</b> may be <b>null</b>
     */
    public CopyNamespacesDecl getCopyNamespacesDecl() { return _CopyNamespacesDecl; }

    public Setter(IToken leftIToken, IToken rightIToken,
                  BoundarySpaceDecl _BoundarySpaceDecl,
                  DefaultCollationDecl _DefaultCollationDecl,
                  BaseURIDecl _BaseURIDecl,
                  ConstructionDecl _ConstructionDecl,
                  OrderingModeDecl _OrderingModeDecl,
                  EmptyOrderDecl _EmptyOrderDecl,
                  CopyNamespacesDecl _CopyNamespacesDecl)
    {
        super(leftIToken, rightIToken);

        this._BoundarySpaceDecl = _BoundarySpaceDecl;
        if (_BoundarySpaceDecl != null) ((ASTNode) _BoundarySpaceDecl).setParent(this);
        this._DefaultCollationDecl = _DefaultCollationDecl;
        if (_DefaultCollationDecl != null) ((ASTNode) _DefaultCollationDecl).setParent(this);
        this._BaseURIDecl = _BaseURIDecl;
        if (_BaseURIDecl != null) ((ASTNode) _BaseURIDecl).setParent(this);
        this._ConstructionDecl = _ConstructionDecl;
        if (_ConstructionDecl != null) ((ASTNode) _ConstructionDecl).setParent(this);
        this._OrderingModeDecl = _OrderingModeDecl;
        if (_OrderingModeDecl != null) ((ASTNode) _OrderingModeDecl).setParent(this);
        this._EmptyOrderDecl = _EmptyOrderDecl;
        if (_EmptyOrderDecl != null) ((ASTNode) _EmptyOrderDecl).setParent(this);
        this._CopyNamespacesDecl = _CopyNamespacesDecl;
        if (_CopyNamespacesDecl != null) ((ASTNode) _CopyNamespacesDecl).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_BoundarySpaceDecl);
        list.add(_DefaultCollationDecl);
        list.add(_BaseURIDecl);
        list.add(_ConstructionDecl);
        list.add(_OrderingModeDecl);
        list.add(_EmptyOrderDecl);
        list.add(_CopyNamespacesDecl);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof Setter)) return false;
        if (! super.equals(o)) return false;
        Setter other = (Setter) o;
        if (_BoundarySpaceDecl == null)
            if (other._BoundarySpaceDecl != null) return false;
            else; // continue
        else if (! _BoundarySpaceDecl.equals(other._BoundarySpaceDecl)) return false;
        if (_DefaultCollationDecl == null)
            if (other._DefaultCollationDecl != null) return false;
            else; // continue
        else if (! _DefaultCollationDecl.equals(other._DefaultCollationDecl)) return false;
        if (_BaseURIDecl == null)
            if (other._BaseURIDecl != null) return false;
            else; // continue
        else if (! _BaseURIDecl.equals(other._BaseURIDecl)) return false;
        if (_ConstructionDecl == null)
            if (other._ConstructionDecl != null) return false;
            else; // continue
        else if (! _ConstructionDecl.equals(other._ConstructionDecl)) return false;
        if (_OrderingModeDecl == null)
            if (other._OrderingModeDecl != null) return false;
            else; // continue
        else if (! _OrderingModeDecl.equals(other._OrderingModeDecl)) return false;
        if (_EmptyOrderDecl == null)
            if (other._EmptyOrderDecl != null) return false;
            else; // continue
        else if (! _EmptyOrderDecl.equals(other._EmptyOrderDecl)) return false;
        if (_CopyNamespacesDecl == null)
            if (other._CopyNamespacesDecl != null) return false;
            else; // continue
        else if (! _CopyNamespacesDecl.equals(other._CopyNamespacesDecl)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_BoundarySpaceDecl == null ? 0 : _BoundarySpaceDecl.hashCode());
        hash = hash * 31 + (_DefaultCollationDecl == null ? 0 : _DefaultCollationDecl.hashCode());
        hash = hash * 31 + (_BaseURIDecl == null ? 0 : _BaseURIDecl.hashCode());
        hash = hash * 31 + (_ConstructionDecl == null ? 0 : _ConstructionDecl.hashCode());
        hash = hash * 31 + (_OrderingModeDecl == null ? 0 : _OrderingModeDecl.hashCode());
        hash = hash * 31 + (_EmptyOrderDecl == null ? 0 : _EmptyOrderDecl.hashCode());
        hash = hash * 31 + (_CopyNamespacesDecl == null ? 0 : _CopyNamespacesDecl.hashCode());
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
            if (_BoundarySpaceDecl != null) _BoundarySpaceDecl.accept(v);
            if (_DefaultCollationDecl != null) _DefaultCollationDecl.accept(v);
            if (_BaseURIDecl != null) _BaseURIDecl.accept(v);
            if (_ConstructionDecl != null) _ConstructionDecl.accept(v);
            if (_OrderingModeDecl != null) _OrderingModeDecl.accept(v);
            if (_EmptyOrderDecl != null) _EmptyOrderDecl.accept(v);
            if (_CopyNamespacesDecl != null) _CopyNamespacesDecl.accept(v);
        }
        v.endVisit(this);
    }
}


