
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
 *<li>Rule 9:  LibraryModule ::= ModuleDecl Prolog
 *</b>
 */
public class LibraryModule extends ASTNode implements ILibraryModule
{
    private ModuleDecl _ModuleDecl;
    private PrologEntryList _Prolog;

    public ModuleDecl getModuleDecl() { return _ModuleDecl; }
    public PrologEntryList getProlog() { return _Prolog; }

    public LibraryModule(IToken leftIToken, IToken rightIToken,
                         ModuleDecl _ModuleDecl,
                         PrologEntryList _Prolog)
    {
        super(leftIToken, rightIToken);

        this._ModuleDecl = _ModuleDecl;
        ((ASTNode) _ModuleDecl).setParent(this);
        this._Prolog = _Prolog;
        ((ASTNode) _Prolog).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_ModuleDecl);
        list.add(_Prolog);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof LibraryModule)) return false;
        if (! super.equals(o)) return false;
        LibraryModule other = (LibraryModule) o;
        if (! _ModuleDecl.equals(other._ModuleDecl)) return false;
        if (! _Prolog.equals(other._Prolog)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_ModuleDecl.hashCode());
        hash = hash * 31 + (_Prolog.hashCode());
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
            _ModuleDecl.accept(v);
            _Prolog.accept(v);
        }
        v.endVisit(this);
    }
}


