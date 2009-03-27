
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
 *<li>Rule 54:  SchemaPrefix ::= namespace NCName EQUAL
 *</b>
 */
public class SchemaPrefixNamed extends ASTNode implements ISchemaPrefix
{
    private ASTNodeToken _namespace;
    private ASTNodeToken _NCName;
    private ASTNodeToken _EQUAL;

    public ASTNodeToken getnamespace() { return _namespace; }
    public ASTNodeToken getNCName() { return _NCName; }
    public ASTNodeToken getEQUAL() { return _EQUAL; }

    public SchemaPrefixNamed(IToken leftIToken, IToken rightIToken,
                             ASTNodeToken _namespace,
                             ASTNodeToken _NCName,
                             ASTNodeToken _EQUAL)
    {
        super(leftIToken, rightIToken);

        this._namespace = _namespace;
        ((ASTNode) _namespace).setParent(this);
        this._NCName = _NCName;
        ((ASTNode) _NCName).setParent(this);
        this._EQUAL = _EQUAL;
        ((ASTNode) _EQUAL).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_namespace);
        list.add(_NCName);
        list.add(_EQUAL);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof SchemaPrefixNamed)) return false;
        if (! super.equals(o)) return false;
        SchemaPrefixNamed other = (SchemaPrefixNamed) o;
        if (! _namespace.equals(other._namespace)) return false;
        if (! _NCName.equals(other._NCName)) return false;
        if (! _EQUAL.equals(other._EQUAL)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_namespace.hashCode());
        hash = hash * 31 + (_NCName.hashCode());
        hash = hash * 31 + (_EQUAL.hashCode());
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
            _namespace.accept(v);
            _NCName.accept(v);
            _EQUAL.accept(v);
        }
        v.endVisit(this);
    }
}


