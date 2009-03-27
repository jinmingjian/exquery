
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
 *<li>Rule 268:  DirAttributeList ::= $Empty
 *<li>Rule 269:  DirAttributeList ::= DirAttributeList DirAttribute
 *</b>
 */
public class DirAttributeList extends AbstractASTNodeList implements IDirAttributeList
{
    public DirAttribute getDirAttributeAt(int i) { return (DirAttribute) getElementAt(i); }

    public DirAttributeList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public DirAttributeList(DirAttribute _DirAttribute, boolean leftRecursive)
    {
        super((ASTNode) _DirAttribute, leftRecursive);
        ((ASTNode) _DirAttribute).setParent(this);
    }

    public void add(DirAttribute _DirAttribute)
    {
        super.add((ASTNode) _DirAttribute);
        ((ASTNode) _DirAttribute).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof DirAttributeList)) return false;
        if (! super.equals(o)) return false;
        DirAttributeList other = (DirAttributeList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            DirAttribute element = getDirAttributeAt(i);
            if (! element.equals(other.getDirAttributeAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getDirAttributeAt(i).hashCode());
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
                DirAttribute element = getDirAttributeAt(i);
                if (! v.preVisit(element)) continue;
                element.enter(v);
                v.postVisit(element);
            }
        }
        v.endVisit(this);
    }
}


