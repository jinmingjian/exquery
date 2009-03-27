
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
 *<li>Rule 195:  PathExpr ::= SLASH
 *<li>Rule 196:  PathExpr ::= SLASH RelativePathExpr
 *<li>Rule 197:  PathExpr ::= DoubleSlash RelativePathExpr
 *<li>Rule 198:  PathExpr ::= RelativePathExpr
 *</b>
 */
public class PathExpr extends ASTNode implements IPathExpr
{
    private ASTNodeToken _SLASH;
    private IRelativePathExpr _RelativePathExpr;
    private ASTNodeToken _DoubleSlash;

    /**
     * The value returned by <b>getSLASH</b> may be <b>null</b>
     */
    public ASTNodeToken getSLASH() { return _SLASH; }
    /**
     * The value returned by <b>getRelativePathExpr</b> may be <b>null</b>
     */
    public IRelativePathExpr getRelativePathExpr() { return _RelativePathExpr; }
    /**
     * The value returned by <b>getDoubleSlash</b> may be <b>null</b>
     */
    public ASTNodeToken getDoubleSlash() { return _DoubleSlash; }

    public PathExpr(IToken leftIToken, IToken rightIToken,
                    ASTNodeToken _SLASH,
                    IRelativePathExpr _RelativePathExpr,
                    ASTNodeToken _DoubleSlash)
    {
        super(leftIToken, rightIToken);

        this._SLASH = _SLASH;
        if (_SLASH != null) ((ASTNode) _SLASH).setParent(this);
        this._RelativePathExpr = _RelativePathExpr;
        if (_RelativePathExpr != null) ((ASTNode) _RelativePathExpr).setParent(this);
        this._DoubleSlash = _DoubleSlash;
        if (_DoubleSlash != null) ((ASTNode) _DoubleSlash).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_SLASH);
        list.add(_RelativePathExpr);
        list.add(_DoubleSlash);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof PathExpr)) return false;
        if (! super.equals(o)) return false;
        PathExpr other = (PathExpr) o;
        if (_SLASH == null)
            if (other._SLASH != null) return false;
            else; // continue
        else if (! _SLASH.equals(other._SLASH)) return false;
        if (_RelativePathExpr == null)
            if (other._RelativePathExpr != null) return false;
            else; // continue
        else if (! _RelativePathExpr.equals(other._RelativePathExpr)) return false;
        if (_DoubleSlash == null)
            if (other._DoubleSlash != null) return false;
            else; // continue
        else if (! _DoubleSlash.equals(other._DoubleSlash)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_SLASH == null ? 0 : _SLASH.hashCode());
        hash = hash * 31 + (_RelativePathExpr == null ? 0 : _RelativePathExpr.hashCode());
        hash = hash * 31 + (_DoubleSlash == null ? 0 : _DoubleSlash.hashCode());
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
            if (_SLASH != null) _SLASH.accept(v);
            if (_RelativePathExpr != null) _RelativePathExpr.accept(v);
            if (_DoubleSlash != null) _DoubleSlash.accept(v);
        }
        v.endVisit(this);
    }
}


