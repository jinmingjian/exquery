
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
 *<li>Rule 96:  ForClause ::= for ForClauseVarBindingList
 *</b>
 */
public class ForClause extends ASTNode implements IForClause
{
    private ASTNodeToken _for;
    private ForClauseVarBindingList _ForClauseVarBindingList;

    public ASTNodeToken getfor() { return _for; }
    public ForClauseVarBindingList getForClauseVarBindingList() { return _ForClauseVarBindingList; }

    public ForClause(IToken leftIToken, IToken rightIToken,
                     ASTNodeToken _for,
                     ForClauseVarBindingList _ForClauseVarBindingList)
    {
        super(leftIToken, rightIToken);

        this._for = _for;
        ((ASTNode) _for).setParent(this);
        this._ForClauseVarBindingList = _ForClauseVarBindingList;
        ((ASTNode) _ForClauseVarBindingList).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_for);
        list.add(_ForClauseVarBindingList);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ForClause)) return false;
        if (! super.equals(o)) return false;
        ForClause other = (ForClause) o;
        if (! _for.equals(other._for)) return false;
        if (! _ForClauseVarBindingList.equals(other._ForClauseVarBindingList)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_for.hashCode());
        hash = hash * 31 + (_ForClauseVarBindingList.hashCode());
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
            _for.accept(v);
            _ForClauseVarBindingList.accept(v);
        }
        v.endVisit(this);
    }
}


