
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
 *<li>Rule 45:  DefaultCollationDecl ::= declare default collation URILiteral
 *</b>
 */
public class DefaultCollationDecl extends ASTNode implements IDefaultCollationDecl
{
    private ASTNodeToken _declare;
    private ASTNodeToken _default;
    private ASTNodeToken _collation;
    private ASTNodeToken _StringLiteral;

    public ASTNodeToken getdeclare() { return _declare; }
    public ASTNodeToken getdefault() { return _default; }
    public ASTNodeToken getcollation() { return _collation; }
    public ASTNodeToken getStringLiteral() { return _StringLiteral; }

    public DefaultCollationDecl(IToken leftIToken, IToken rightIToken,
                                ASTNodeToken _declare,
                                ASTNodeToken _default,
                                ASTNodeToken _collation,
                                ASTNodeToken _StringLiteral)
    {
        super(leftIToken, rightIToken);

        this._declare = _declare;
        ((ASTNode) _declare).setParent(this);
        this._default = _default;
        ((ASTNode) _default).setParent(this);
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
        list.add(_declare);
        list.add(_default);
        list.add(_collation);
        list.add(_StringLiteral);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof DefaultCollationDecl)) return false;
        if (! super.equals(o)) return false;
        DefaultCollationDecl other = (DefaultCollationDecl) o;
        if (! _declare.equals(other._declare)) return false;
        if (! _default.equals(other._default)) return false;
        if (! _collation.equals(other._collation)) return false;
        if (! _StringLiteral.equals(other._StringLiteral)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_declare.hashCode());
        hash = hash * 31 + (_default.hashCode());
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
            _declare.accept(v);
            _default.accept(v);
            _collation.accept(v);
            _StringLiteral.accept(v);
        }
        v.endVisit(this);
    }
}


