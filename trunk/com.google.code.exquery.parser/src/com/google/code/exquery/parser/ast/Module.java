
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
 *<li>Rule 1:  Module ::= $Empty
 *<li>Rule 2:  Module ::= VersionDeclOpt LibraryModule
 *<li>Rule 3:  Module ::= VersionDeclOpt MainModule
 *</b>
 */
public class Module extends ASTNode implements IModule
{
    private VersionDeclOpt _VersionDeclOpt;
    private LibraryModule _LibraryModule;
    private MainModule _MainModule;

    /**
     * The value returned by <b>getVersionDeclOpt</b> may be <b>null</b>
     */
    public VersionDeclOpt getVersionDeclOpt() { return _VersionDeclOpt; }
    /**
     * The value returned by <b>getLibraryModule</b> may be <b>null</b>
     */
    public LibraryModule getLibraryModule() { return _LibraryModule; }
    /**
     * The value returned by <b>getMainModule</b> may be <b>null</b>
     */
    public MainModule getMainModule() { return _MainModule; }

    public Module(IToken leftIToken, IToken rightIToken,
                  VersionDeclOpt _VersionDeclOpt,
                  LibraryModule _LibraryModule,
                  MainModule _MainModule)
    {
        super(leftIToken, rightIToken);

        this._VersionDeclOpt = _VersionDeclOpt;
        if (_VersionDeclOpt != null) ((ASTNode) _VersionDeclOpt).setParent(this);
        this._LibraryModule = _LibraryModule;
        if (_LibraryModule != null) ((ASTNode) _LibraryModule).setParent(this);
        this._MainModule = _MainModule;
        if (_MainModule != null) ((ASTNode) _MainModule).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_VersionDeclOpt);
        list.add(_LibraryModule);
        list.add(_MainModule);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof Module)) return false;
        if (! super.equals(o)) return false;
        Module other = (Module) o;
        if (_VersionDeclOpt == null)
            if (other._VersionDeclOpt != null) return false;
            else; // continue
        else if (! _VersionDeclOpt.equals(other._VersionDeclOpt)) return false;
        if (_LibraryModule == null)
            if (other._LibraryModule != null) return false;
            else; // continue
        else if (! _LibraryModule.equals(other._LibraryModule)) return false;
        if (_MainModule == null)
            if (other._MainModule != null) return false;
            else; // continue
        else if (! _MainModule.equals(other._MainModule)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_VersionDeclOpt == null ? 0 : _VersionDeclOpt.hashCode());
        hash = hash * 31 + (_LibraryModule == null ? 0 : _LibraryModule.hashCode());
        hash = hash * 31 + (_MainModule == null ? 0 : _MainModule.hashCode());
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
            if (_VersionDeclOpt != null) _VersionDeclOpt.accept(v);
            if (_LibraryModule != null) _LibraryModule.accept(v);
            if (_MainModule != null) _MainModule.accept(v);
        }
        v.endVisit(this);
    }
}


