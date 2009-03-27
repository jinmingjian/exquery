
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
 *<li>Rule 68:  FunctionDecl ::= declare function QName LEFTPAREN ParamListOpt RIGHTPAREN AsSequenceTypeOpt EnclosedExpr
 *</b>
 */
public class FunctionDecl extends ASTNode implements IFunctionDecl
{
    private ASTNodeToken _declare;
    private ASTNodeToken _function;
    private IQName _QName;
    private ASTNodeToken _LEFTPAREN;
    private ParamList _ParamListOpt;
    private ASTNodeToken _RIGHTPAREN;
    private AsSequenceTypeOpt _AsSequenceTypeOpt;
    private EnclosedExpr _EnclosedExpr;

    public ASTNodeToken getdeclare() { return _declare; }
    public ASTNodeToken getfunction() { return _function; }
    public IQName getQName() { return _QName; }
    public ASTNodeToken getLEFTPAREN() { return _LEFTPAREN; }
    /**
     * The value returned by <b>getParamListOpt</b> may be <b>null</b>
     */
    public ParamList getParamListOpt() { return _ParamListOpt; }
    public ASTNodeToken getRIGHTPAREN() { return _RIGHTPAREN; }
    /**
     * The value returned by <b>getAsSequenceTypeOpt</b> may be <b>null</b>
     */
    public AsSequenceTypeOpt getAsSequenceTypeOpt() { return _AsSequenceTypeOpt; }
    public EnclosedExpr getEnclosedExpr() { return _EnclosedExpr; }

    public FunctionDecl(IToken leftIToken, IToken rightIToken,
                        ASTNodeToken _declare,
                        ASTNodeToken _function,
                        IQName _QName,
                        ASTNodeToken _LEFTPAREN,
                        ParamList _ParamListOpt,
                        ASTNodeToken _RIGHTPAREN,
                        AsSequenceTypeOpt _AsSequenceTypeOpt,
                        EnclosedExpr _EnclosedExpr)
    {
        super(leftIToken, rightIToken);

        this._declare = _declare;
        ((ASTNode) _declare).setParent(this);
        this._function = _function;
        ((ASTNode) _function).setParent(this);
        this._QName = _QName;
        ((ASTNode) _QName).setParent(this);
        this._LEFTPAREN = _LEFTPAREN;
        ((ASTNode) _LEFTPAREN).setParent(this);
        this._ParamListOpt = _ParamListOpt;
        if (_ParamListOpt != null) ((ASTNode) _ParamListOpt).setParent(this);
        this._RIGHTPAREN = _RIGHTPAREN;
        ((ASTNode) _RIGHTPAREN).setParent(this);
        this._AsSequenceTypeOpt = _AsSequenceTypeOpt;
        if (_AsSequenceTypeOpt != null) ((ASTNode) _AsSequenceTypeOpt).setParent(this);
        this._EnclosedExpr = _EnclosedExpr;
        ((ASTNode) _EnclosedExpr).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_declare);
        list.add(_function);
        list.add(_QName);
        list.add(_LEFTPAREN);
        list.add(_ParamListOpt);
        list.add(_RIGHTPAREN);
        list.add(_AsSequenceTypeOpt);
        list.add(_EnclosedExpr);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof FunctionDecl)) return false;
        if (! super.equals(o)) return false;
        FunctionDecl other = (FunctionDecl) o;
        if (! _declare.equals(other._declare)) return false;
        if (! _function.equals(other._function)) return false;
        if (! _QName.equals(other._QName)) return false;
        if (! _LEFTPAREN.equals(other._LEFTPAREN)) return false;
        if (_ParamListOpt == null)
            if (other._ParamListOpt != null) return false;
            else; // continue
        else if (! _ParamListOpt.equals(other._ParamListOpt)) return false;
        if (! _RIGHTPAREN.equals(other._RIGHTPAREN)) return false;
        if (_AsSequenceTypeOpt == null)
            if (other._AsSequenceTypeOpt != null) return false;
            else; // continue
        else if (! _AsSequenceTypeOpt.equals(other._AsSequenceTypeOpt)) return false;
        if (! _EnclosedExpr.equals(other._EnclosedExpr)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_declare.hashCode());
        hash = hash * 31 + (_function.hashCode());
        hash = hash * 31 + (_QName.hashCode());
        hash = hash * 31 + (_LEFTPAREN.hashCode());
        hash = hash * 31 + (_ParamListOpt == null ? 0 : _ParamListOpt.hashCode());
        hash = hash * 31 + (_RIGHTPAREN.hashCode());
        hash = hash * 31 + (_AsSequenceTypeOpt == null ? 0 : _AsSequenceTypeOpt.hashCode());
        hash = hash * 31 + (_EnclosedExpr.hashCode());
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
            _declare.accept(v);
            _function.accept(v);
            _QName.accept(v);
            _LEFTPAREN.accept(v);
            if (_ParamListOpt != null) _ParamListOpt.accept(v);
            _RIGHTPAREN.accept(v);
            if (_AsSequenceTypeOpt != null) _AsSequenceTypeOpt.accept(v);
            _EnclosedExpr.accept(v);
        }
        v.endVisit(this);
    }
}


