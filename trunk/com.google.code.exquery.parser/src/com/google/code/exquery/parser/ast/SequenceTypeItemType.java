
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
 *<li>Rule 319:  SequenceType ::= ItemType
 *<li>Rule 320:  SequenceType ::= ItemType OccurrenceIndicator
 *</b>
 */
public class SequenceTypeItemType extends ASTNode implements ISequenceType
{
    private IItemType _ItemType;
    private OccurrenceIndicator _OccurrenceIndicator;

    /**
     * The value returned by <b>getItemType</b> may be <b>null</b>
     */
    public IItemType getItemType() { return _ItemType; }
    /**
     * The value returned by <b>getOccurrenceIndicator</b> may be <b>null</b>
     */
    public OccurrenceIndicator getOccurrenceIndicator() { return _OccurrenceIndicator; }

    public SequenceTypeItemType(IToken leftIToken, IToken rightIToken,
                                IItemType _ItemType,
                                OccurrenceIndicator _OccurrenceIndicator)
    {
        super(leftIToken, rightIToken);

        this._ItemType = _ItemType;
        if (_ItemType != null) ((ASTNode) _ItemType).setParent(this);
        this._OccurrenceIndicator = _OccurrenceIndicator;
        if (_OccurrenceIndicator != null) ((ASTNode) _OccurrenceIndicator).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_ItemType);
        list.add(_OccurrenceIndicator);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof SequenceTypeItemType)) return false;
        if (! super.equals(o)) return false;
        SequenceTypeItemType other = (SequenceTypeItemType) o;
        if (_ItemType == null)
            if (other._ItemType != null) return false;
            else; // continue
        else if (! _ItemType.equals(other._ItemType)) return false;
        if (_OccurrenceIndicator == null)
            if (other._OccurrenceIndicator != null) return false;
            else; // continue
        else if (! _OccurrenceIndicator.equals(other._OccurrenceIndicator)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_ItemType == null ? 0 : _ItemType.hashCode());
        hash = hash * 31 + (_OccurrenceIndicator == null ? 0 : _OccurrenceIndicator.hashCode());
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
            if (_ItemType != null) _ItemType.accept(v);
            if (_OccurrenceIndicator != null) _OccurrenceIndicator.accept(v);
        }
        v.endVisit(this);
    }
}


