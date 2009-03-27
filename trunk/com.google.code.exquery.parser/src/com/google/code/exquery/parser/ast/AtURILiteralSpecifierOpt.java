
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
 *<em>
 *<li>Rule 51:  AtURILiteralSpecifierOpt ::= $Empty
 *</em>
 *<p>
 *<b>
 *<li>Rule 52:  AtURILiteralSpecifierOpt ::= at CommaSeparatedURILiteral
 *</b>
 */
public class AtURILiteralSpecifierOpt extends ASTNode implements IAtURILiteralSpecifierOpt
{
    private ASTNodeToken _at;
    private URILiteralList _CommaSeparatedURILiteral;

    public ASTNodeToken getat() { return _at; }
    public URILiteralList getCommaSeparatedURILiteral() { return _CommaSeparatedURILiteral; }

    public AtURILiteralSpecifierOpt(IToken leftIToken, IToken rightIToken,
                                    ASTNodeToken _at,
                                    URILiteralList _CommaSeparatedURILiteral)
    {
        super(leftIToken, rightIToken);

        this._at = _at;
        ((ASTNode) _at).setParent(this);
        this._CommaSeparatedURILiteral = _CommaSeparatedURILiteral;
        ((ASTNode) _CommaSeparatedURILiteral).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_at);
        list.add(_CommaSeparatedURILiteral);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof AtURILiteralSpecifierOpt)) return false;
        if (! super.equals(o)) return false;
        AtURILiteralSpecifierOpt other = (AtURILiteralSpecifierOpt) o;
        if (! _at.equals(other._at)) return false;
        if (! _CommaSeparatedURILiteral.equals(other._CommaSeparatedURILiteral)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_at.hashCode());
        hash = hash * 31 + (_CommaSeparatedURILiteral.hashCode());
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
            _at.accept(v);
            _CommaSeparatedURILiteral.accept(v);
        }
        v.endVisit(this);
    }
}


