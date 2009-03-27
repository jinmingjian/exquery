
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
 *<li>Rule 33:  DefaultNamespaceDecl ::= declare default element namespace URILiteral
 *<li>Rule 34:  DefaultNamespaceDecl ::= declare default function namespace URILiteral
 *</b>
 */
public class DefaultNamespaceDecl extends ASTNode implements IDefaultNamespaceDecl
{
    private ASTNodeToken _declare;
    private ASTNodeToken _default;
    private ASTNodeToken _element;
    private ASTNodeToken _namespace;
    private ASTNodeToken _StringLiteral;
    private ASTNodeToken _function;

    /**
     * The value returned by <b>getdeclare</b> may be <b>null</b>
     */
    public ASTNodeToken getdeclare() { return _declare; }
    public ASTNodeToken getdefault() { return _default; }
    /**
     * The value returned by <b>getelement</b> may be <b>null</b>
     */
    public ASTNodeToken getelement() { return _element; }
    public ASTNodeToken getnamespace() { return _namespace; }
    public ASTNodeToken getStringLiteral() { return _StringLiteral; }
    /**
     * The value returned by <b>getfunction</b> may be <b>null</b>
     */
    public ASTNodeToken getfunction() { return _function; }

    public DefaultNamespaceDecl(IToken leftIToken, IToken rightIToken,
                                ASTNodeToken _declare,
                                ASTNodeToken _default,
                                ASTNodeToken _element,
                                ASTNodeToken _namespace,
                                ASTNodeToken _StringLiteral,
                                ASTNodeToken _function)
    {
        super(leftIToken, rightIToken);

        this._declare = _declare;
        if (_declare != null) ((ASTNode) _declare).setParent(this);
        this._default = _default;
        ((ASTNode) _default).setParent(this);
        this._element = _element;
        if (_element != null) ((ASTNode) _element).setParent(this);
        this._namespace = _namespace;
        ((ASTNode) _namespace).setParent(this);
        this._StringLiteral = _StringLiteral;
        ((ASTNode) _StringLiteral).setParent(this);
        this._function = _function;
        if (_function != null) ((ASTNode) _function).setParent(this);
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
        list.add(_element);
        list.add(_namespace);
        list.add(_StringLiteral);
        list.add(_function);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof DefaultNamespaceDecl)) return false;
        if (! super.equals(o)) return false;
        DefaultNamespaceDecl other = (DefaultNamespaceDecl) o;
        if (_declare == null)
            if (other._declare != null) return false;
            else; // continue
        else if (! _declare.equals(other._declare)) return false;
        if (! _default.equals(other._default)) return false;
        if (_element == null)
            if (other._element != null) return false;
            else; // continue
        else if (! _element.equals(other._element)) return false;
        if (! _namespace.equals(other._namespace)) return false;
        if (! _StringLiteral.equals(other._StringLiteral)) return false;
        if (_function == null)
            if (other._function != null) return false;
            else; // continue
        else if (! _function.equals(other._function)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_declare == null ? 0 : _declare.hashCode());
        hash = hash * 31 + (_default.hashCode());
        hash = hash * 31 + (_element == null ? 0 : _element.hashCode());
        hash = hash * 31 + (_namespace.hashCode());
        hash = hash * 31 + (_StringLiteral.hashCode());
        hash = hash * 31 + (_function == null ? 0 : _function.hashCode());
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
            if (_declare != null) _declare.accept(v);
            _default.accept(v);
            if (_element != null) _element.accept(v);
            _namespace.accept(v);
            _StringLiteral.accept(v);
            if (_function != null) _function.accept(v);
        }
        v.endVisit(this);
    }
}


