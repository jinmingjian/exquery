
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
 *<li>Rule 276:  DirAttributeValueQuotList ::= $Empty
 *<li>Rule 277:  DirAttributeValueQuotList ::= DirAttributeValueQuotList DirAttributeValueQuot
 *</b>
 */
public class DirAttributeValueQuotList extends AbstractASTNodeList implements IDirAttributeValueQuotList
{
    public IDirAttributeValueQuot getDirAttributeValueQuotAt(int i) { return (IDirAttributeValueQuot) getElementAt(i); }

    public DirAttributeValueQuotList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public DirAttributeValueQuotList(IDirAttributeValueQuot _DirAttributeValueQuot, boolean leftRecursive)
    {
        super((ASTNode) _DirAttributeValueQuot, leftRecursive);
        ((ASTNode) _DirAttributeValueQuot).setParent(this);
    }

    public void add(IDirAttributeValueQuot _DirAttributeValueQuot)
    {
        super.add((ASTNode) _DirAttributeValueQuot);
        ((ASTNode) _DirAttributeValueQuot).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof DirAttributeValueQuotList)) return false;
        if (! super.equals(o)) return false;
        DirAttributeValueQuotList other = (DirAttributeValueQuotList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            IDirAttributeValueQuot element = getDirAttributeValueQuotAt(i);
            if (! element.equals(other.getDirAttributeValueQuotAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getDirAttributeValueQuotAt(i).hashCode());
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
                IDirAttributeValueQuot element = getDirAttributeValueQuotAt(i);
                element.accept(v);
            }
        }
        v.endVisit(this);
    }
}


