
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
 *<li>Rule 88:  ForOrLetClauseList ::= ForOrLetClause
 *<li>Rule 89:  ForOrLetClauseList ::= ForOrLetClauseList ForOrLetClause
 *</b>
 */
public class ForOrLetClauseList extends AbstractASTNodeList implements IForOrLetClauseList
{
    public IForOrLetClause getForOrLetClauseAt(int i) { return (IForOrLetClause) getElementAt(i); }

    public ForOrLetClauseList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public ForOrLetClauseList(IForOrLetClause _ForOrLetClause, boolean leftRecursive)
    {
        super((ASTNode) _ForOrLetClause, leftRecursive);
        ((ASTNode) _ForOrLetClause).setParent(this);
    }

    public void add(IForOrLetClause _ForOrLetClause)
    {
        super.add((ASTNode) _ForOrLetClause);
        ((ASTNode) _ForOrLetClause).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ForOrLetClauseList)) return false;
        if (! super.equals(o)) return false;
        ForOrLetClauseList other = (ForOrLetClauseList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            IForOrLetClause element = getForOrLetClauseAt(i);
            if (! element.equals(other.getForOrLetClauseAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getForOrLetClauseAt(i).hashCode());
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
                IForOrLetClause element = getForOrLetClauseAt(i);
                element.accept(v);
            }
        }
        v.endVisit(this);
    }
}


