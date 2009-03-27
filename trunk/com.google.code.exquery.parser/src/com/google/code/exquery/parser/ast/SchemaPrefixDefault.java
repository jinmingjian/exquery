
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
 *<li>Rule 55:  SchemaPrefix ::= default element namespace
 *</b>
 */
public class SchemaPrefixDefault extends ASTNode implements ISchemaPrefix
{
    private ASTNodeToken _default;
    private ASTNodeToken _element;
    private ASTNodeToken _namespace;

    public ASTNodeToken getdefault() { return _default; }
    public ASTNodeToken getelement() { return _element; }
    public ASTNodeToken getnamespace() { return _namespace; }

    public SchemaPrefixDefault(IToken leftIToken, IToken rightIToken,
                               ASTNodeToken _default,
                               ASTNodeToken _element,
                               ASTNodeToken _namespace)
    {
        super(leftIToken, rightIToken);

        this._default = _default;
        ((ASTNode) _default).setParent(this);
        this._element = _element;
        ((ASTNode) _element).setParent(this);
        this._namespace = _namespace;
        ((ASTNode) _namespace).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_default);
        list.add(_element);
        list.add(_namespace);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof SchemaPrefixDefault)) return false;
        if (! super.equals(o)) return false;
        SchemaPrefixDefault other = (SchemaPrefixDefault) o;
        if (! _default.equals(other._default)) return false;
        if (! _element.equals(other._element)) return false;
        if (! _namespace.equals(other._namespace)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_default.hashCode());
        hash = hash * 31 + (_element.hashCode());
        hash = hash * 31 + (_namespace.hashCode());
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
            _default.accept(v);
            _element.accept(v);
            _namespace.accept(v);
        }
        v.endVisit(this);
    }
}


