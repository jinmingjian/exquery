
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
 *<li>Rule 8:  MainModule ::= Prolog QueryBody
 *</b>
 */
public class MainModule extends ASTNode implements IMainModule
{
    private PrologEntryList _Prolog;
    private ExprSingleList _QueryBody;

    public PrologEntryList getProlog() { return _Prolog; }
    public ExprSingleList getQueryBody() { return _QueryBody; }

    public MainModule(IToken leftIToken, IToken rightIToken,
                      PrologEntryList _Prolog,
                      ExprSingleList _QueryBody)
    {
        super(leftIToken, rightIToken);

        this._Prolog = _Prolog;
        ((ASTNode) _Prolog).setParent(this);
        this._QueryBody = _QueryBody;
        ((ASTNode) _QueryBody).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_Prolog);
        list.add(_QueryBody);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof MainModule)) return false;
        if (! super.equals(o)) return false;
        MainModule other = (MainModule) o;
        if (! _Prolog.equals(other._Prolog)) return false;
        if (! _QueryBody.equals(other._QueryBody)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_Prolog.hashCode());
        hash = hash * 31 + (_QueryBody.hashCode());
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
            _Prolog.accept(v);
            _QueryBody.accept(v);
        }
        v.endVisit(this);
    }
}


