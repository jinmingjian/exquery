
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
 *<li>Rule 360:  QName ::= NCName COLON NCName
 *</b>
 */
public class QName1 extends ASTNode implements IQName
{
    private ASTNodeToken _NCName;
    private ASTNodeToken _COLON;
    private ASTNodeToken _NCName3;

    public ASTNodeToken getNCName() { return _NCName; }
    public ASTNodeToken getCOLON() { return _COLON; }
    public ASTNodeToken getNCName3() { return _NCName3; }

    public QName1(IToken leftIToken, IToken rightIToken,
                  ASTNodeToken _NCName,
                  ASTNodeToken _COLON,
                  ASTNodeToken _NCName3)
    {
        super(leftIToken, rightIToken);

        this._NCName = _NCName;
        ((ASTNode) _NCName).setParent(this);
        this._COLON = _COLON;
        ((ASTNode) _COLON).setParent(this);
        this._NCName3 = _NCName3;
        ((ASTNode) _NCName3).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_NCName);
        list.add(_COLON);
        list.add(_NCName3);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof QName1)) return false;
        if (! super.equals(o)) return false;
        QName1 other = (QName1) o;
        if (! _NCName.equals(other._NCName)) return false;
        if (! _COLON.equals(other._COLON)) return false;
        if (! _NCName3.equals(other._NCName3)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_NCName.hashCode());
        hash = hash * 31 + (_COLON.hashCode());
        hash = hash * 31 + (_NCName3.hashCode());
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
            _NCName.accept(v);
            _COLON.accept(v);
            _NCName3.accept(v);
        }
        v.endVisit(this);
    }
}


