
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
 *<li>Rule 94:  ForClauseVarBindingList ::= ForClauseVarBinding
 *<li>Rule 95:  ForClauseVarBindingList ::= ForClauseVarBindingList COMMA ForClauseVarBinding
 *</b>
 */
public class ForClauseVarBindingList extends AbstractASTNodeList implements IForClauseVarBindingList
{
    public ForClauseVarBinding getForClauseVarBindingAt(int i) { return (ForClauseVarBinding) getElementAt(i); }

    public ForClauseVarBindingList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public ForClauseVarBindingList(ForClauseVarBinding _ForClauseVarBinding, boolean leftRecursive)
    {
        super((ASTNode) _ForClauseVarBinding, leftRecursive);
        ((ASTNode) _ForClauseVarBinding).setParent(this);
    }

    public void add(ForClauseVarBinding _ForClauseVarBinding)
    {
        super.add((ASTNode) _ForClauseVarBinding);
        ((ASTNode) _ForClauseVarBinding).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ForClauseVarBindingList)) return false;
        if (! super.equals(o)) return false;
        ForClauseVarBindingList other = (ForClauseVarBindingList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            ForClauseVarBinding element = getForClauseVarBindingAt(i);
            if (! element.equals(other.getForClauseVarBindingAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getForClauseVarBindingAt(i).hashCode());
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
            for (int i = 0; i < size(); i++)
            {
                ForClauseVarBinding element = getForClauseVarBindingAt(i);
                if (! v.preVisit(element)) continue;
                element.enter(v);
                v.postVisit(element);
            }
        }
        v.endVisit(this);
    }
}


