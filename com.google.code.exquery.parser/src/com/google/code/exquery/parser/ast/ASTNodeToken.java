
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

public class ASTNodeToken extends ASTNode implements IASTNodeToken
{
    public ASTNodeToken(IToken token) { super(token); }
    public IToken getIToken() { return leftIToken; }
    public String toString() { return leftIToken.toString(); }

    /**
     * A token class has no children. So, we return the empty list.
     */
    public java.util.ArrayList getAllChildren() { return new java.util.ArrayList(); }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ASTNodeToken)) return false;
        ASTNodeToken other = (ASTNodeToken) o;
        return getIToken().getILexStream() == other.getIToken().getILexStream() &&
               getIToken().getTokenIndex() == other.getIToken().getTokenIndex();
    }

    public int hashCode()
    {
        int hash = 7;
        if (getIToken().getILexStream() != null) hash = hash * 31 + getIToken().getILexStream().hashCode();
        hash = hash * 31 + getIToken().getTokenIndex();
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
        v.visit(this);
        v.endVisit(this);
    }
}


