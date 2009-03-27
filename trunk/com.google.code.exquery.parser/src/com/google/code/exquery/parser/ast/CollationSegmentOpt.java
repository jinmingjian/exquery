
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
 *<li>Rule 116:  CollationSegmentOpt ::= $Empty
 *</em>
 *<p>
 *<b>
 *<li>Rule 117:  CollationSegmentOpt ::= collation URILiteral
 *</b>
 */
public class CollationSegmentOpt extends ASTNode implements ICollationSegmentOpt
{
    private ASTNodeToken _collation;
    private ASTNodeToken _StringLiteral;

    public ASTNodeToken getcollation() { return _collation; }
    public ASTNodeToken getStringLiteral() { return _StringLiteral; }

    public CollationSegmentOpt(IToken leftIToken, IToken rightIToken,
                               ASTNodeToken _collation,
                               ASTNodeToken _StringLiteral)
    {
        super(leftIToken, rightIToken);

        this._collation = _collation;
        ((ASTNode) _collation).setParent(this);
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
        list.add(_collation);
        list.add(_StringLiteral);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof CollationSegmentOpt)) return false;
        if (! super.equals(o)) return false;
        CollationSegmentOpt other = (CollationSegmentOpt) o;
        if (! _collation.equals(other._collation)) return false;
        if (! _StringLiteral.equals(other._StringLiteral)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_collation.hashCode());
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
            _collation.accept(v);
            _StringLiteral.accept(v);
        }
        v.endVisit(this);
    }
}


