
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

public abstract class ASTNode implements IAst
{
    public IAst getNextAst() { return null; }
    protected IToken leftIToken,
                     rightIToken;
    protected IAst parent = null;
    protected void setParent(IAst parent) { this.parent = parent; }
    public IAst getParent() { return parent; }

    public IToken getLeftIToken() { return leftIToken; }
    public IToken getRightIToken() { return rightIToken; }
    public IToken[] getPrecedingAdjuncts() { return leftIToken.getPrecedingAdjuncts(); }
    public IToken[] getFollowingAdjuncts() { return rightIToken.getFollowingAdjuncts(); }

    public String toString()
    {
        return leftIToken.getILexStream().toString(leftIToken.getStartOffset(), rightIToken.getEndOffset());
    }

    public ASTNode(IToken token) { this.leftIToken = this.rightIToken = token; }
    public ASTNode(IToken leftIToken, IToken rightIToken)
    {
        this.leftIToken = leftIToken;
        this.rightIToken = rightIToken;
    }

    void initialize() {}

    /**
     * A list of all children of this node, excluding the null ones.
     */
    public java.util.ArrayList getChildren()
    {
        java.util.ArrayList list = getAllChildren();
        int k = -1;
        for (int i = 0; i < list.size(); i++)
        {
            Object element = list.get(i);
            if (element != null)
            {
                if (++k != i)
                    list.set(k, element);
            }
        }
        for (int i = list.size() - 1; i > k; i--) // remove extraneous elements
            list.remove(i);
        return list;
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public abstract java.util.ArrayList getAllChildren();

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ASTNode)) return false;
        ASTNode other = (ASTNode) o;
        return getLeftIToken().getILexStream() == other.getLeftIToken().getILexStream() &&
               getLeftIToken().getTokenIndex() == other.getLeftIToken().getTokenIndex() &&
               getRightIToken().getILexStream() == other.getRightIToken().getILexStream() &&
               getRightIToken().getTokenIndex() == other.getRightIToken().getTokenIndex();
    }

    public int hashCode()
    {
        int hash = 7;
        if (getLeftIToken().getILexStream() != null) hash = hash * 31 + getLeftIToken().getILexStream().hashCode();
        hash = hash * 31 + getLeftIToken().getTokenIndex();
        if (getRightIToken().getILexStream() != null) hash = hash * 31 + getRightIToken().getILexStream().hashCode();
        hash = hash * 31 + getRightIToken().getTokenIndex();
        return hash;
    }
    public abstract void accept(IAstVisitor v);
}


