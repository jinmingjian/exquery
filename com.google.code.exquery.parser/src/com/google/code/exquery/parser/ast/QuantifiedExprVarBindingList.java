
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
 *<li>Rule 120:  QuantifiedExprVarBindingList ::= QuantifiedExprVarBinding
 *<li>Rule 121:  QuantifiedExprVarBindingList ::= QuantifiedExprVarBindingList COMMA QuantifiedExprVarBinding
 *</b>
 */
public class QuantifiedExprVarBindingList extends AbstractASTNodeList implements IQuantifiedExprVarBindingList
{
    public QuantifiedExprVarBinding getQuantifiedExprVarBindingAt(int i) { return (QuantifiedExprVarBinding) getElementAt(i); }

    public QuantifiedExprVarBindingList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public QuantifiedExprVarBindingList(QuantifiedExprVarBinding _QuantifiedExprVarBinding, boolean leftRecursive)
    {
        super((ASTNode) _QuantifiedExprVarBinding, leftRecursive);
        ((ASTNode) _QuantifiedExprVarBinding).setParent(this);
    }

    public void add(QuantifiedExprVarBinding _QuantifiedExprVarBinding)
    {
        super.add((ASTNode) _QuantifiedExprVarBinding);
        ((ASTNode) _QuantifiedExprVarBinding).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof QuantifiedExprVarBindingList)) return false;
        if (! super.equals(o)) return false;
        QuantifiedExprVarBindingList other = (QuantifiedExprVarBindingList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            QuantifiedExprVarBinding element = getQuantifiedExprVarBindingAt(i);
            if (! element.equals(other.getQuantifiedExprVarBindingAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getQuantifiedExprVarBindingAt(i).hashCode());
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
                QuantifiedExprVarBinding element = getQuantifiedExprVarBindingAt(i);
                if (! v.preVisit(element)) continue;
                element.enter(v);
                v.postVisit(element);
            }
        }
        v.endVisit(this);
    }
}


