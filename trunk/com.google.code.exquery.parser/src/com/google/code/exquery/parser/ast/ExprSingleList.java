
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
 *<li>Rule 75:  Expr ::= ExprSingle
 *<li>Rule 76:  Expr ::= Expr COMMA ExprSingle
 *</b>
 */
public class ExprSingleList extends AbstractASTNodeList implements IExpr
{
    public IExprSingle getExprSingleAt(int i) { return (IExprSingle) getElementAt(i); }

    public ExprSingleList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public ExprSingleList(IExprSingle _ExprSingle, boolean leftRecursive)
    {
        super((ASTNode) _ExprSingle, leftRecursive);
        ((ASTNode) _ExprSingle).setParent(this);
    }

    public void add(IExprSingle _ExprSingle)
    {
        super.add((ASTNode) _ExprSingle);
        ((ASTNode) _ExprSingle).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ExprSingleList)) return false;
        if (! super.equals(o)) return false;
        ExprSingleList other = (ExprSingleList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            IExprSingle element = getExprSingleAt(i);
            if (! element.equals(other.getExprSingleAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getExprSingleAt(i).hashCode());
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
                IExprSingle element = getExprSingleAt(i);
                element.accept(v);
            }
        }
        v.endVisit(this);
    }
}


