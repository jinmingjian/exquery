
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
 *<li>Rule 324:  ItemType ::= KindTest
 *<li>Rule 325:  ItemType ::= AtomicType
 *</em>
 *<p>
 *<b>
 *<li>Rule 326:  ItemType ::= item LEFTPAREN RIGHTPAREN
 *</b>
 */
public class ItemType extends ASTNode implements IItemType
{
    private ASTNodeToken _item;
    private ASTNodeToken _LEFTPAREN;
    private ASTNodeToken _RIGHTPAREN;

    public ASTNodeToken getitem() { return _item; }
    public ASTNodeToken getLEFTPAREN() { return _LEFTPAREN; }
    public ASTNodeToken getRIGHTPAREN() { return _RIGHTPAREN; }

    public ItemType(IToken leftIToken, IToken rightIToken,
                    ASTNodeToken _item,
                    ASTNodeToken _LEFTPAREN,
                    ASTNodeToken _RIGHTPAREN)
    {
        super(leftIToken, rightIToken);

        this._item = _item;
        ((ASTNode) _item).setParent(this);
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
        list.add(_item);
        list.add(_LEFTPAREN);
        list.add(_RIGHTPAREN);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ItemType)) return false;
        if (! super.equals(o)) return false;
        ItemType other = (ItemType) o;
        if (! _item.equals(other._item)) return false;
        if (! _LEFTPAREN.equals(other._LEFTPAREN)) return false;
        if (! _RIGHTPAREN.equals(other._RIGHTPAREN)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_item.hashCode());
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
            _item.accept(v);
            _LEFTPAREN.accept(v);
            _RIGHTPAREN.accept(v);
        }
        v.endVisit(this);
    }
}


