
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
 *<li>Rule 231:  Wildcard ::= TIMES COLON NCName
 *</b>
 */
public class Wildcard2 extends ASTNode implements IWildcard
{
    private ASTNodeToken _TIMES;
    private ASTNodeToken _COLON;
    private ASTNodeToken _NCName;

    public ASTNodeToken getTIMES() { return _TIMES; }
    public ASTNodeToken getCOLON() { return _COLON; }
    public ASTNodeToken getNCName() { return _NCName; }

    public Wildcard2(IToken leftIToken, IToken rightIToken,
                     ASTNodeToken _TIMES,
                     ASTNodeToken _COLON,
                     ASTNodeToken _NCName)
    {
        super(leftIToken, rightIToken);

        this._TIMES = _TIMES;
        ((ASTNode) _TIMES).setParent(this);
        this._COLON = _COLON;
        ((ASTNode) _COLON).setParent(this);
        this._NCName = _NCName;
        ((ASTNode) _NCName).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_TIMES);
        list.add(_COLON);
        list.add(_NCName);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof Wildcard2)) return false;
        if (! super.equals(o)) return false;
        Wildcard2 other = (Wildcard2) o;
        if (! _TIMES.equals(other._TIMES)) return false;
        if (! _COLON.equals(other._COLON)) return false;
        if (! _NCName.equals(other._NCName)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_TIMES.hashCode());
        hash = hash * 31 + (_COLON.hashCode());
        hash = hash * 31 + (_NCName.hashCode());
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
            _TIMES.accept(v);
            _COLON.accept(v);
            _NCName.accept(v);
        }
        v.endVisit(this);
    }
}


