
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
 *<li>Rule 99:  LetClauseVarBindingList ::= LetClauseVarBinding
 *<li>Rule 100:  LetClauseVarBindingList ::= LetClauseVarBindingList COMMA LetClauseVarBinding
 *</b>
 */
public class LetClauseVarBindingList extends AbstractASTNodeList implements ILetClauseVarBindingList
{
    public LetClauseVarBinding getLetClauseVarBindingAt(int i) { return (LetClauseVarBinding) getElementAt(i); }

    public LetClauseVarBindingList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public LetClauseVarBindingList(LetClauseVarBinding _LetClauseVarBinding, boolean leftRecursive)
    {
        super((ASTNode) _LetClauseVarBinding, leftRecursive);
        ((ASTNode) _LetClauseVarBinding).setParent(this);
    }

    public void add(LetClauseVarBinding _LetClauseVarBinding)
    {
        super.add((ASTNode) _LetClauseVarBinding);
        ((ASTNode) _LetClauseVarBinding).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof LetClauseVarBindingList)) return false;
        if (! super.equals(o)) return false;
        LetClauseVarBindingList other = (LetClauseVarBindingList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            LetClauseVarBinding element = getLetClauseVarBindingAt(i);
            if (! element.equals(other.getLetClauseVarBindingAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getLetClauseVarBindingAt(i).hashCode());
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
                LetClauseVarBinding element = getLetClauseVarBindingAt(i);
                if (! v.preVisit(element)) continue;
                element.enter(v);
                v.postVisit(element);
            }
        }
        v.endVisit(this);
    }
}


