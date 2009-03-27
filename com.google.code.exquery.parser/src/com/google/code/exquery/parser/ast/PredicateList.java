
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
 *<li>Rule 233:  PredicateList ::= $Empty
 *<li>Rule 234:  PredicateList ::= PredicateList Predicate
 *</b>
 */
public class PredicateList extends AbstractASTNodeList implements IPredicateList
{
    public Predicate getPredicateAt(int i) { return (Predicate) getElementAt(i); }

    public PredicateList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public PredicateList(Predicate _Predicate, boolean leftRecursive)
    {
        super((ASTNode) _Predicate, leftRecursive);
        ((ASTNode) _Predicate).setParent(this);
    }

    public void add(Predicate _Predicate)
    {
        super.add((ASTNode) _Predicate);
        ((ASTNode) _Predicate).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof PredicateList)) return false;
        if (! super.equals(o)) return false;
        PredicateList other = (PredicateList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            Predicate element = getPredicateAt(i);
            if (! element.equals(other.getPredicateAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getPredicateAt(i).hashCode());
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
                Predicate element = getPredicateAt(i);
                if (! v.preVisit(element)) continue;
                element.enter(v);
                v.postVisit(element);
            }
        }
        v.endVisit(this);
    }
}


