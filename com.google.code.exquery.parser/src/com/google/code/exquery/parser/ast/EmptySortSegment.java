
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
 *<li>Rule 112:  EmptySortSegment ::= empty greatest
 *<li>Rule 113:  EmptySortSegment ::= empty least
 *</b>
 */
public class EmptySortSegment extends ASTNode implements IEmptySortSegment
{
    private ASTNodeToken _empty;
    private ASTNodeToken _greatest;
    private ASTNodeToken _least;

    /**
     * The value returned by <b>getempty</b> may be <b>null</b>
     */
    public ASTNodeToken getempty() { return _empty; }
    /**
     * The value returned by <b>getgreatest</b> may be <b>null</b>
     */
    public ASTNodeToken getgreatest() { return _greatest; }
    /**
     * The value returned by <b>getleast</b> may be <b>null</b>
     */
    public ASTNodeToken getleast() { return _least; }

    public EmptySortSegment(IToken leftIToken, IToken rightIToken,
                            ASTNodeToken _empty,
                            ASTNodeToken _greatest,
                            ASTNodeToken _least)
    {
        super(leftIToken, rightIToken);

        this._empty = _empty;
        if (_empty != null) ((ASTNode) _empty).setParent(this);
        this._greatest = _greatest;
        if (_greatest != null) ((ASTNode) _greatest).setParent(this);
        this._least = _least;
        if (_least != null) ((ASTNode) _least).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_empty);
        list.add(_greatest);
        list.add(_least);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof EmptySortSegment)) return false;
        if (! super.equals(o)) return false;
        EmptySortSegment other = (EmptySortSegment) o;
        if (_empty == null)
            if (other._empty != null) return false;
            else; // continue
        else if (! _empty.equals(other._empty)) return false;
        if (_greatest == null)
            if (other._greatest != null) return false;
            else; // continue
        else if (! _greatest.equals(other._greatest)) return false;
        if (_least == null)
            if (other._least != null) return false;
            else; // continue
        else if (! _least.equals(other._least)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_empty == null ? 0 : _empty.hashCode());
        hash = hash * 31 + (_greatest == null ? 0 : _greatest.hashCode());
        hash = hash * 31 + (_least == null ? 0 : _least.hashCode());
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
            if (_empty != null) _empty.accept(v);
            if (_greatest != null) _greatest.accept(v);
            if (_least != null) _least.accept(v);
        }
        v.endVisit(this);
    }
}


