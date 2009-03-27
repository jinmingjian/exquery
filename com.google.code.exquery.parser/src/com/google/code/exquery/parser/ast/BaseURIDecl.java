
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
 *<li>Rule 46:  BaseURIDecl ::= declare base_uri URILiteral
 *</b>
 */
public class BaseURIDecl extends ASTNode implements IBaseURIDecl
{
    private ASTNodeToken _declare;
    private ASTNodeToken _base_uri;
    private ASTNodeToken _StringLiteral;

    public ASTNodeToken getdeclare() { return _declare; }
    public ASTNodeToken getbase_uri() { return _base_uri; }
    public ASTNodeToken getStringLiteral() { return _StringLiteral; }

    public BaseURIDecl(IToken leftIToken, IToken rightIToken,
                       ASTNodeToken _declare,
                       ASTNodeToken _base_uri,
                       ASTNodeToken _StringLiteral)
    {
        super(leftIToken, rightIToken);

        this._declare = _declare;
        ((ASTNode) _declare).setParent(this);
        this._base_uri = _base_uri;
        ((ASTNode) _base_uri).setParent(this);
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
        list.add(_base_uri);
        list.add(_StringLiteral);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof BaseURIDecl)) return false;
        if (! super.equals(o)) return false;
        BaseURIDecl other = (BaseURIDecl) o;
        if (! _declare.equals(other._declare)) return false;
        if (! _base_uri.equals(other._base_uri)) return false;
        if (! _StringLiteral.equals(other._StringLiteral)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_declare.hashCode());
        hash = hash * 31 + (_base_uri.hashCode());
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
            _base_uri.accept(v);
            _StringLiteral.accept(v);
        }
        v.endVisit(this);
    }
}


