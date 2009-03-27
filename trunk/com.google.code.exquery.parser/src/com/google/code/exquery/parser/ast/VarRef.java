
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
 *<li>Rule 249:  VarRef ::= DOLLAR VarName
 *</b>
 */
public class VarRef extends ASTNode implements IVarRef
{
    private ASTNodeToken _DOLLAR;
    private IVarName _VarName;

    public ASTNodeToken getDOLLAR() { return _DOLLAR; }
    public IVarName getVarName() { return _VarName; }

    public VarRef(IToken leftIToken, IToken rightIToken,
                  ASTNodeToken _DOLLAR,
                  IVarName _VarName)
    {
        super(leftIToken, rightIToken);

        this._DOLLAR = _DOLLAR;
        ((ASTNode) _DOLLAR).setParent(this);
        this._VarName = _VarName;
        ((ASTNode) _VarName).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_DOLLAR);
        list.add(_VarName);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof VarRef)) return false;
        if (! super.equals(o)) return false;
        VarRef other = (VarRef) o;
        if (! _DOLLAR.equals(other._DOLLAR)) return false;
        if (! _VarName.equals(other._VarName)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_DOLLAR.hashCode());
        hash = hash * 31 + (_VarName.hashCode());
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
            _DOLLAR.accept(v);
            _VarName.accept(v);
        }
        v.endVisit(this);
    }
}


