
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
 *<li>Rule 189:  PragmaList ::= Pragma
 *<li>Rule 190:  PragmaList ::= PragmaList Pragma
 *</b>
 */
public class PragmaList extends AbstractASTNodeList implements IPragmaList
{
    public Pragma getPragmaAt(int i) { return (Pragma) getElementAt(i); }

    public PragmaList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public PragmaList(Pragma _Pragma, boolean leftRecursive)
    {
        super((ASTNode) _Pragma, leftRecursive);
        ((ASTNode) _Pragma).setParent(this);
    }

    public void add(Pragma _Pragma)
    {
        super.add((ASTNode) _Pragma);
        ((ASTNode) _Pragma).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof PragmaList)) return false;
        if (! super.equals(o)) return false;
        PragmaList other = (PragmaList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            Pragma element = getPragmaAt(i);
            if (! element.equals(other.getPragmaAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getPragmaAt(i).hashCode());
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
                Pragma element = getPragmaAt(i);
                if (! v.preVisit(element)) continue;
                element.enter(v);
                v.postVisit(element);
            }
        }
        v.endVisit(this);
    }
}


