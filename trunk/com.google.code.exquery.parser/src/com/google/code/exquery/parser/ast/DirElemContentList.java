
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
 *<li>Rule 265:  DirElemContentList ::= $Empty
 *<li>Rule 266:  DirElemContentList ::= DirElemContentList DirElemContent
 *</b>
 */
public class DirElemContentList extends AbstractASTNodeList implements IDirElemContentList
{
    public IDirElemContent getDirElemContentAt(int i) { return (IDirElemContent) getElementAt(i); }

    public DirElemContentList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public DirElemContentList(IDirElemContent _DirElemContent, boolean leftRecursive)
    {
        super((ASTNode) _DirElemContent, leftRecursive);
        ((ASTNode) _DirElemContent).setParent(this);
    }

    public void add(IDirElemContent _DirElemContent)
    {
        super.add((ASTNode) _DirElemContent);
        ((ASTNode) _DirElemContent).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof DirElemContentList)) return false;
        if (! super.equals(o)) return false;
        DirElemContentList other = (DirElemContentList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            IDirElemContent element = getDirElemContentAt(i);
            if (! element.equals(other.getDirElemContentAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getDirElemContentAt(i).hashCode());
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
                IDirElemContent element = getDirElemContentAt(i);
                element.accept(v);
            }
        }
        v.endVisit(this);
    }
}


