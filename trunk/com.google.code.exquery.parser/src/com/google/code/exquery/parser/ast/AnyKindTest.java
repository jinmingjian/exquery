
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
 *<li>Rule 336:  AnyKindTest ::= node LEFTPAREN RIGHTPAREN
 *</b>
 */
public class AnyKindTest extends ASTNode implements IAnyKindTest
{
    private ASTNodeToken _node;
    private ASTNodeToken _LEFTPAREN;
    private ASTNodeToken _RIGHTPAREN;

    public ASTNodeToken getnode() { return _node; }
    public ASTNodeToken getLEFTPAREN() { return _LEFTPAREN; }
    public ASTNodeToken getRIGHTPAREN() { return _RIGHTPAREN; }

    public AnyKindTest(IToken leftIToken, IToken rightIToken,
                       ASTNodeToken _node,
                       ASTNodeToken _LEFTPAREN,
                       ASTNodeToken _RIGHTPAREN)
    {
        super(leftIToken, rightIToken);

        this._node = _node;
        ((ASTNode) _node).setParent(this);
        this._LEFTPAREN = _LEFTPAREN;
        ((ASTNode) _LEFTPAREN).setParent(this);
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
        list.add(_node);
        list.add(_LEFTPAREN);
        list.add(_RIGHTPAREN);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof AnyKindTest)) return false;
        if (! super.equals(o)) return false;
        AnyKindTest other = (AnyKindTest) o;
        if (! _node.equals(other._node)) return false;
        if (! _LEFTPAREN.equals(other._LEFTPAREN)) return false;
        if (! _RIGHTPAREN.equals(other._RIGHTPAREN)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_node.hashCode());
        hash = hash * 31 + (_LEFTPAREN.hashCode());
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
            _node.accept(v);
            _LEFTPAREN.accept(v);
            _RIGHTPAREN.accept(v);
        }
        v.endVisit(this);
    }
}


