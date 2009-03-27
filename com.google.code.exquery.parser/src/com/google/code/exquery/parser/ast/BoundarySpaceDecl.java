
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
 *<li>Rule 31:  BoundarySpaceDecl ::= declare boundary_space preserve
 *<li>Rule 32:  BoundarySpaceDecl ::= declare boundary_space strip
 *</b>
 */
public class BoundarySpaceDecl extends ASTNode implements IBoundarySpaceDecl
{
    private ASTNodeToken _declare;
    private ASTNodeToken _boundary_space;
    private ASTNodeToken _preserve;
    private ASTNodeToken _strip;

    /**
     * The value returned by <b>getdeclare</b> may be <b>null</b>
     */
    public ASTNodeToken getdeclare() { return _declare; }
    public ASTNodeToken getboundary_space() { return _boundary_space; }
    /**
     * The value returned by <b>getpreserve</b> may be <b>null</b>
     */
    public ASTNodeToken getpreserve() { return _preserve; }
    /**
     * The value returned by <b>getstrip</b> may be <b>null</b>
     */
    public ASTNodeToken getstrip() { return _strip; }

    public BoundarySpaceDecl(IToken leftIToken, IToken rightIToken,
                             ASTNodeToken _declare,
                             ASTNodeToken _boundary_space,
                             ASTNodeToken _preserve,
                             ASTNodeToken _strip)
    {
        super(leftIToken, rightIToken);

        this._declare = _declare;
        if (_declare != null) ((ASTNode) _declare).setParent(this);
        this._boundary_space = _boundary_space;
        ((ASTNode) _boundary_space).setParent(this);
        this._preserve = _preserve;
        if (_preserve != null) ((ASTNode) _preserve).setParent(this);
        this._strip = _strip;
        if (_strip != null) ((ASTNode) _strip).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_declare);
        list.add(_boundary_space);
        list.add(_preserve);
        list.add(_strip);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof BoundarySpaceDecl)) return false;
        if (! super.equals(o)) return false;
        BoundarySpaceDecl other = (BoundarySpaceDecl) o;
        if (_declare == null)
            if (other._declare != null) return false;
            else; // continue
        else if (! _declare.equals(other._declare)) return false;
        if (! _boundary_space.equals(other._boundary_space)) return false;
        if (_preserve == null)
            if (other._preserve != null) return false;
            else; // continue
        else if (! _preserve.equals(other._preserve)) return false;
        if (_strip == null)
            if (other._strip != null) return false;
            else; // continue
        else if (! _strip.equals(other._strip)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_declare == null ? 0 : _declare.hashCode());
        hash = hash * 31 + (_boundary_space.hashCode());
        hash = hash * 31 + (_preserve == null ? 0 : _preserve.hashCode());
        hash = hash * 31 + (_strip == null ? 0 : _strip.hashCode());
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
            _boundary_space.accept(v);
            if (_preserve != null) _preserve.accept(v);
            if (_strip != null) _strip.accept(v);
        }
        v.endVisit(this);
    }
}


