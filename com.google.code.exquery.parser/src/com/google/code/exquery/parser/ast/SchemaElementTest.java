
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
 *<li>Rule 358:  SchemaElementTest ::= schema_element LEFTPAREN ElementDeclaration RIGHTPAREN
 *</b>
 */
public class SchemaElementTest extends ASTNode implements ISchemaElementTest
{
    private ASTNodeToken _schema_element;
    private ASTNodeToken _LEFTPAREN;
    private IQName _QName;
    private ASTNodeToken _RIGHTPAREN;

    public ASTNodeToken getschema_element() { return _schema_element; }
    public ASTNodeToken getLEFTPAREN() { return _LEFTPAREN; }
    public IQName getQName() { return _QName; }
    public ASTNodeToken getRIGHTPAREN() { return _RIGHTPAREN; }

    public SchemaElementTest(IToken leftIToken, IToken rightIToken,
                             ASTNodeToken _schema_element,
                             ASTNodeToken _LEFTPAREN,
                             IQName _QName,
                             ASTNodeToken _RIGHTPAREN)
    {
        super(leftIToken, rightIToken);

        this._schema_element = _schema_element;
        ((ASTNode) _schema_element).setParent(this);
        this._LEFTPAREN = _LEFTPAREN;
        ((ASTNode) _LEFTPAREN).setParent(this);
        this._QName = _QName;
        ((ASTNode) _QName).setParent(this);
        this._RIGHTPAREN = _RIGHTPAREN;
        ((ASTNode) _RIGHTPAREN).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_schema_element);
        list.add(_LEFTPAREN);
        list.add(_QName);
        list.add(_RIGHTPAREN);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof SchemaElementTest)) return false;
        if (! super.equals(o)) return false;
        SchemaElementTest other = (SchemaElementTest) o;
        if (! _schema_element.equals(other._schema_element)) return false;
        if (! _LEFTPAREN.equals(other._LEFTPAREN)) return false;
        if (! _QName.equals(other._QName)) return false;
        if (! _RIGHTPAREN.equals(other._RIGHTPAREN)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_schema_element.hashCode());
        hash = hash * 31 + (_LEFTPAREN.hashCode());
        hash = hash * 31 + (_QName.hashCode());
        hash = hash * 31 + (_RIGHTPAREN.hashCode());
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
            _schema_element.accept(v);
            _LEFTPAREN.accept(v);
            _QName.accept(v);
            _RIGHTPAREN.accept(v);
        }
        v.endVisit(this);
    }
}


