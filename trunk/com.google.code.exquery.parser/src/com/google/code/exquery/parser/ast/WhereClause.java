
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
 *<li>Rule 102:  WhereClause ::= where ExprSingle
 *</b>
 */
public class WhereClause extends ASTNode implements IWhereClause
{
    private ASTNodeToken _where;
    private IExprSingle _ExprSingle;

    public ASTNodeToken getwhere() { return _where; }
    public IExprSingle getExprSingle() { return _ExprSingle; }

    public WhereClause(IToken leftIToken, IToken rightIToken,
                       ASTNodeToken _where,
                       IExprSingle _ExprSingle)
    {
        super(leftIToken, rightIToken);

        this._where = _where;
        ((ASTNode) _where).setParent(this);
        this._ExprSingle = _ExprSingle;
        ((ASTNode) _ExprSingle).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_where);
        list.add(_ExprSingle);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof WhereClause)) return false;
        if (! super.equals(o)) return false;
        WhereClause other = (WhereClause) o;
        if (! _where.equals(other._where)) return false;
        if (! _ExprSingle.equals(other._ExprSingle)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_where.hashCode());
        hash = hash * 31 + (_ExprSingle.hashCode());
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
            _where.accept(v);
            _ExprSingle.accept(v);
        }
        v.endVisit(this);
    }
}


