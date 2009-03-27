
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
 *<li>Rule 35:  OptionDecl ::= declare option QName StringLiteral
 *</b>
 */
public class OptionDecl extends ASTNode implements IOptionDecl
{
    private ASTNodeToken _declare;
    private ASTNodeToken _option;
    private IQName _QName;
    private ASTNodeToken _StringLiteral;

    public ASTNodeToken getdeclare() { return _declare; }
    public ASTNodeToken getoption() { return _option; }
    public IQName getQName() { return _QName; }
    public ASTNodeToken getStringLiteral() { return _StringLiteral; }

    public OptionDecl(IToken leftIToken, IToken rightIToken,
                      ASTNodeToken _declare,
                      ASTNodeToken _option,
                      IQName _QName,
                      ASTNodeToken _StringLiteral)
    {
        super(leftIToken, rightIToken);

        this._declare = _declare;
        ((ASTNode) _declare).setParent(this);
        this._option = _option;
        ((ASTNode) _option).setParent(this);
        this._QName = _QName;
        ((ASTNode) _QName).setParent(this);
        this._StringLiteral = _StringLiteral;
        ((ASTNode) _StringLiteral).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_declare);
        list.add(_option);
        list.add(_QName);
        list.add(_StringLiteral);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof OptionDecl)) return false;
        if (! super.equals(o)) return false;
        OptionDecl other = (OptionDecl) o;
        if (! _declare.equals(other._declare)) return false;
        if (! _option.equals(other._option)) return false;
        if (! _QName.equals(other._QName)) return false;
        if (! _StringLiteral.equals(other._StringLiteral)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_declare.hashCode());
        hash = hash * 31 + (_option.hashCode());
        hash = hash * 31 + (_QName.hashCode());
        hash = hash * 31 + (_StringLiteral.hashCode());
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
            _option.accept(v);
            _QName.accept(v);
            _StringLiteral.accept(v);
        }
        v.endVisit(this);
    }
}


