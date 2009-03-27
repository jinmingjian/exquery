
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
 *<li>Rule 49:  CommaSeparatedURILiteral ::= URILiteral
 *<li>Rule 50:  CommaSeparatedURILiteral ::= CommaSeparatedURILiteral COMMA URILiteral
 *</b>
 */
public class URILiteralList extends AbstractASTNodeList implements ICommaSeparatedURILiteral
{
    public ASTNodeToken getURILiteralAt(int i) { return (ASTNodeToken) getElementAt(i); }

    public URILiteralList(IToken leftIToken, IToken rightIToken, boolean leftRecursive)
    {
        super(leftIToken, rightIToken, leftRecursive);
    }

    public URILiteralList(ASTNodeToken _URILiteral, boolean leftRecursive)
    {
        super((ASTNode) _URILiteral, leftRecursive);
        ((ASTNode) _URILiteral).setParent(this);
    }

    public void add(ASTNodeToken _URILiteral)
    {
        super.add((ASTNode) _URILiteral);
        ((ASTNode) _URILiteral).setParent(this);
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof URILiteralList)) return false;
        if (! super.equals(o)) return false;
        URILiteralList other = (URILiteralList) o;
        if (size() != other.size()) return false;
        for (int i = 0; i < size(); i++)
        {
            ASTNodeToken element = getURILiteralAt(i);
            if (! element.equals(other.getURILiteralAt(i))) return false;
        }
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        for (int i = 0; i < size(); i++)
            hash = hash * 31 + (getURILiteralAt(i).hashCode());
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
                ASTNodeToken element = getURILiteralAt(i);
                if (! v.preVisit(element)) continue;
                element.enter(v);
                v.postVisit(element);
            }
        }
        v.endVisit(this);
    }
}


