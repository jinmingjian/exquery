
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
 *<li>Rule 101:  LetClause ::= let LetClauseVarBindingList
 *</b>
 */
public class LetClause extends ASTNode implements ILetClause
{
    private ASTNodeToken _let;
    private LetClauseVarBindingList _LetClauseVarBindingList;

    public ASTNodeToken getlet() { return _let; }
    public LetClauseVarBindingList getLetClauseVarBindingList() { return _LetClauseVarBindingList; }

    public LetClause(IToken leftIToken, IToken rightIToken,
                     ASTNodeToken _let,
                     LetClauseVarBindingList _LetClauseVarBindingList)
    {
        super(leftIToken, rightIToken);

        this._let = _let;
        ((ASTNode) _let).setParent(this);
        this._LetClauseVarBindingList = _LetClauseVarBindingList;
        ((ASTNode) _LetClauseVarBindingList).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_let);
        list.add(_LetClauseVarBindingList);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof LetClause)) return false;
        if (! super.equals(o)) return false;
        LetClause other = (LetClause) o;
        if (! _let.equals(other._let)) return false;
        if (! _LetClauseVarBindingList.equals(other._LetClauseVarBindingList)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_let.hashCode());
        hash = hash * 31 + (_LetClauseVarBindingList.hashCode());
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
            _let.accept(v);
            _LetClauseVarBindingList.accept(v);
        }
        v.endVisit(this);
    }
}


