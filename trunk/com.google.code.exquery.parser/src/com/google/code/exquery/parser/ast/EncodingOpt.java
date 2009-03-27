
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
 *<li>Rule 6:  EncodingOpt ::= $Empty
 *</em>
 *<p>
 *<b>
 *<li>Rule 7:  EncodingOpt ::= encoding StringLiteral
 *</b>
 */
public class EncodingOpt extends ASTNode implements IEncodingOpt
{
    private ASTNodeToken _encoding;
    private ASTNodeToken _StringLiteral;

    public ASTNodeToken getencoding() { return _encoding; }
    public ASTNodeToken getStringLiteral() { return _StringLiteral; }

    public EncodingOpt(IToken leftIToken, IToken rightIToken,
                       ASTNodeToken _encoding,
                       ASTNodeToken _StringLiteral)
    {
        super(leftIToken, rightIToken);

        this._encoding = _encoding;
        ((ASTNode) _encoding).setParent(this);
        this._StringLiteral = _StringLiteral;
        ((ASTNode) _StringLiteral).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_encoding);
        list.add(_StringLiteral);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof EncodingOpt)) return false;
        if (! super.equals(o)) return false;
        EncodingOpt other = (EncodingOpt) o;
        if (! _encoding.equals(other._encoding)) return false;
        if (! _StringLiteral.equals(other._StringLiteral)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_encoding.hashCode());
        hash = hash * 31 + (_StringLiteral.hashCode());
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
            _encoding.accept(v);
            _StringLiteral.accept(v);
        }
        v.endVisit(this);
    }
}


