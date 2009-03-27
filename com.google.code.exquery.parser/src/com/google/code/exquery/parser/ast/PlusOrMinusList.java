
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
 *<li>Rule 180:  PlusOrMinusList ::= PlusOrMinus
 *<li>Rule 181:  PlusOrMinusList ::= PlusOrMinusList PlusOrMinus
 *</b>
 */
public class PlusOrMinusList extends AbstractASTNodeList implements IPlusOrMinusList
{
    public PlusOrMinus getPlusOrMinusAt(int i) { return (PlusOrMinus) getElementAt(i); }

    public PlusOrMinusList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public PlusOrMinusList(PlusOrMinus _PlusOrMinus, boolean leftRecursive)
    {
        super((ASTNode) _PlusOrMinus, leftRecursive);
        ((ASTNode) _PlusOrMinus).setParent(this);
    }

    public void add(PlusOrMinus _PlusOrMinus)
    {
        super.add((ASTNode) _PlusOrMinus);
        ((ASTNode) _PlusOrMinus).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof PlusOrMinusList)) return false;
        if (! super.equals(o)) return false;
        PlusOrMinusList other = (PlusOrMinusList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            PlusOrMinus element = getPlusOrMinusAt(i);
            if (! element.equals(other.getPlusOrMinusAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getPlusOrMinusAt(i).hashCode());
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
                PlusOrMinus element = getPlusOrMinusAt(i);
                if (! v.preVisit(element)) continue;
                element.enter(v);
                v.postVisit(element);
            }
        }
        v.endVisit(this);
    }
}


