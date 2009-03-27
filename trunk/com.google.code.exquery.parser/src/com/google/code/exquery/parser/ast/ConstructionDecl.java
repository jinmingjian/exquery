
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
 *<li>Rule 62:  ConstructionDecl ::= declare construction strip
 *<li>Rule 63:  ConstructionDecl ::= declare construction preserve
 *</b>
 */
public class ConstructionDecl extends ASTNode implements IConstructionDecl
{
    private ASTNodeToken _declare;
    private ASTNodeToken _construction;
    private ASTNodeToken _strip;
    private ASTNodeToken _preserve;

    /**
     * The value returned by <b>getdeclare</b> may be <b>null</b>
     */
    public ASTNodeToken getdeclare() { return _declare; }
    public ASTNodeToken getconstruction() { return _construction; }
    /**
     * The value returned by <b>getstrip</b> may be <b>null</b>
     */
    public ASTNodeToken getstrip() { return _strip; }
    /**
     * The value returned by <b>getpreserve</b> may be <b>null</b>
     */
    public ASTNodeToken getpreserve() { return _preserve; }

    public ConstructionDecl(IToken leftIToken, IToken rightIToken,
                            ASTNodeToken _declare,
                            ASTNodeToken _construction,
                            ASTNodeToken _strip,
                            ASTNodeToken _preserve)
    {
        super(leftIToken, rightIToken);

        this._declare = _declare;
        if (_declare != null) ((ASTNode) _declare).setParent(this);
        this._construction = _construction;
        ((ASTNode) _construction).setParent(this);
        this._strip = _strip;
        if (_strip != null) ((ASTNode) _strip).setParent(this);
        this._preserve = _preserve;
        if (_preserve != null) ((ASTNode) _preserve).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_declare);
        list.add(_construction);
        list.add(_strip);
        list.add(_preserve);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ConstructionDecl)) return false;
        if (! super.equals(o)) return false;
        ConstructionDecl other = (ConstructionDecl) o;
        if (_declare == null)
            if (other._declare != null) return false;
            else; // continue
        else if (! _declare.equals(other._declare)) return false;
        if (! _construction.equals(other._construction)) return false;
        if (_strip == null)
            if (other._strip != null) return false;
            else; // continue
        else if (! _strip.equals(other._strip)) return false;
        if (_preserve == null)
            if (other._preserve != null) return false;
            else; // continue
        else if (! _preserve.equals(other._preserve)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_declare == null ? 0 : _declare.hashCode());
        hash = hash * 31 + (_construction.hashCode());
        hash = hash * 31 + (_strip == null ? 0 : _strip.hashCode());
        hash = hash * 31 + (_preserve == null ? 0 : _preserve.hashCode());
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
            _construction.accept(v);
            if (_strip != null) _strip.accept(v);
            if (_preserve != null) _preserve.accept(v);
        }
        v.endVisit(this);
    }
}


