
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
 *<li>Rule 18:  PrologEntryList ::= $Empty
 *<li>Rule 19:  PrologEntryList ::= PrologEntryList PrologEntry
 *</b>
 */
public class PrologEntryList extends AbstractASTNodeList implements IPrologEntryList
{
    public PrologEntry getPrologEntryAt(int i) { return (PrologEntry) getElementAt(i); }

    public PrologEntryList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public PrologEntryList(PrologEntry _PrologEntry, boolean leftRecursive)
    {
        super((ASTNode) _PrologEntry, leftRecursive);
        ((ASTNode) _PrologEntry).setParent(this);
    }

    public void add(PrologEntry _PrologEntry)
    {
        super.add((ASTNode) _PrologEntry);
        ((ASTNode) _PrologEntry).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof PrologEntryList)) return false;
        if (! super.equals(o)) return false;
        PrologEntryList other = (PrologEntryList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            PrologEntry element = getPrologEntryAt(i);
            if (! element.equals(other.getPrologEntryAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getPrologEntryAt(i).hashCode());
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
                PrologEntry element = getPrologEntryAt(i);
                if (! v.preVisit(element)) continue;
                element.enter(v);
                v.postVisit(element);
            }
        }
        v.endVisit(this);
    }
}


