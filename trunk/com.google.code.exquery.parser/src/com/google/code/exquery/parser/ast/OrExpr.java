
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
 *<em>
 *<li>Rule 132:  OrExpr ::= AndExpr
 *</em>
 *<p>
 *<b>
 *<li>Rule 133:  OrExpr ::= OrExpr or AndExpr
 *</b>
 */
public class OrExpr extends ASTNode implements IOrExpr
{
    private IOrExpr _OrExpr;
    private ASTNodeToken _or;
    private IAndExpr _AndExpr;

    public IOrExpr getOrExpr() { return _OrExpr; }
    public ASTNodeToken getor() { return _or; }
    public IAndExpr getAndExpr() { return _AndExpr; }

    public OrExpr(IToken leftIToken, IToken rightIToken,
                  IOrExpr _OrExpr,
                  ASTNodeToken _or,
                  IAndExpr _AndExpr)
    {
        super(leftIToken, rightIToken);

        this._OrExpr = _OrExpr;
        ((ASTNode) _OrExpr).setParent(this);
        this._or = _or;
        ((ASTNode) _or).setParent(this);
        this._AndExpr = _AndExpr;
        ((ASTNode) _AndExpr).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_OrExpr);
        list.add(_or);
        list.add(_AndExpr);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof OrExpr)) return false;
        if (! super.equals(o)) return false;
        OrExpr other = (OrExpr) o;
        if (! _OrExpr.equals(other._OrExpr)) return false;
        if (! _or.equals(other._or)) return false;
        if (! _AndExpr.equals(other._AndExpr)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_OrExpr.hashCode());
        hash = hash * 31 + (_or.hashCode());
        hash = hash * 31 + (_AndExpr.hashCode());
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
            _OrExpr.accept(v);
            _or.accept(v);
            _AndExpr.accept(v);
        }
        v.endVisit(this);
    }
}


