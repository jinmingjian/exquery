
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
 *<li>Rule 200:  RelativePathExpr ::= RelativePathExpr SLASH StepExpr
 *<li>Rule 201:  RelativePathExpr ::= RelativePathExpr DoubleSlash StepExpr
 *</b>
 */
public class RelativePathExpr extends ASTNode implements IRelativePathExpr
{
    private IRelativePathExpr _RelativePathExpr;
    private ASTNodeToken _SLASH;
    private IStepExpr _StepExpr;
    private ASTNodeToken _DoubleSlash;

    /**
     * The value returned by <b>getRelativePathExpr</b> may be <b>null</b>
     */
    public IRelativePathExpr getRelativePathExpr() { return _RelativePathExpr; }
    /**
     * The value returned by <b>getSLASH</b> may be <b>null</b>
     */
    public ASTNodeToken getSLASH() { return _SLASH; }
    /**
     * The value returned by <b>getStepExpr</b> may be <b>null</b>
     */
    public IStepExpr getStepExpr() { return _StepExpr; }
    /**
     * The value returned by <b>getDoubleSlash</b> may be <b>null</b>
     */
    public ASTNodeToken getDoubleSlash() { return _DoubleSlash; }

    public RelativePathExpr(IToken leftIToken, IToken rightIToken,
                            IRelativePathExpr _RelativePathExpr,
                            ASTNodeToken _SLASH,
                            IStepExpr _StepExpr,
                            ASTNodeToken _DoubleSlash)
    {
        super(leftIToken, rightIToken);

        this._RelativePathExpr = _RelativePathExpr;
        if (_RelativePathExpr != null) ((ASTNode) _RelativePathExpr).setParent(this);
        this._SLASH = _SLASH;
        if (_SLASH != null) ((ASTNode) _SLASH).setParent(this);
        this._StepExpr = _StepExpr;
        if (_StepExpr != null) ((ASTNode) _StepExpr).setParent(this);
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
        list.add(_RelativePathExpr);
        list.add(_SLASH);
        list.add(_StepExpr);
        list.add(_DoubleSlash);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof RelativePathExpr)) return false;
        if (! super.equals(o)) return false;
        RelativePathExpr other = (RelativePathExpr) o;
        if (_RelativePathExpr == null)
            if (other._RelativePathExpr != null) return false;
            else; // continue
        else if (! _RelativePathExpr.equals(other._RelativePathExpr)) return false;
        if (_SLASH == null)
            if (other._SLASH != null) return false;
            else; // continue
        else if (! _SLASH.equals(other._SLASH)) return false;
        if (_StepExpr == null)
            if (other._StepExpr != null) return false;
            else; // continue
        else if (! _StepExpr.equals(other._StepExpr)) return false;
        if (_DoubleSlash == null)
            if (other._DoubleSlash != null) return false;
            else; // continue
        else if (! _DoubleSlash.equals(other._DoubleSlash)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_RelativePathExpr == null ? 0 : _RelativePathExpr.hashCode());
        hash = hash * 31 + (_SLASH == null ? 0 : _SLASH.hashCode());
        hash = hash * 31 + (_StepExpr == null ? 0 : _StepExpr.hashCode());
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
            if (_RelativePathExpr != null) _RelativePathExpr.accept(v);
            if (_SLASH != null) _SLASH.accept(v);
            if (_StepExpr != null) _StepExpr.accept(v);
            if (_DoubleSlash != null) _DoubleSlash.accept(v);
        }
        v.endVisit(this);
    }
}


