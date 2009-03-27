
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
 *<li>Rule 11:  PrologEntry ::= DefaultNamespaceDecl Separator
 *<li>Rule 12:  PrologEntry ::= Setter Separator
 *<li>Rule 13:  PrologEntry ::= NamespaceDecl Separator
 *<li>Rule 14:  PrologEntry ::= Import Separator
 *<li>Rule 15:  PrologEntry ::= VarDecl Separator
 *<li>Rule 16:  PrologEntry ::= FunctionDecl Separator
 *<li>Rule 17:  PrologEntry ::= OptionDecl Separator
 *</b>
 */
public class PrologEntry extends ASTNode implements IPrologEntry
{
    private DefaultNamespaceDecl _DefaultNamespaceDecl;
    private ASTNodeToken _SEMICOLON;
    private ISetter _Setter;
    private NamespaceDecl _NamespaceDecl;
    private IImport _Import;
    private IVarDecl _VarDecl;
    private IFunctionDecl _FunctionDecl;
    private OptionDecl _OptionDecl;

    /**
     * The value returned by <b>getDefaultNamespaceDecl</b> may be <b>null</b>
     */
    public DefaultNamespaceDecl getDefaultNamespaceDecl() { return _DefaultNamespaceDecl; }
    public ASTNodeToken getSEMICOLON() { return _SEMICOLON; }
    /**
     * The value returned by <b>getSetter</b> may be <b>null</b>
     */
    public ISetter getSetter() { return _Setter; }
    /**
     * The value returned by <b>getNamespaceDecl</b> may be <b>null</b>
     */
    public NamespaceDecl getNamespaceDecl() { return _NamespaceDecl; }
    /**
     * The value returned by <b>getImport</b> may be <b>null</b>
     */
    public IImport getImport() { return _Import; }
    /**
     * The value returned by <b>getVarDecl</b> may be <b>null</b>
     */
    public IVarDecl getVarDecl() { return _VarDecl; }
    /**
     * The value returned by <b>getFunctionDecl</b> may be <b>null</b>
     */
    public IFunctionDecl getFunctionDecl() { return _FunctionDecl; }
    /**
     * The value returned by <b>getOptionDecl</b> may be <b>null</b>
     */
    public OptionDecl getOptionDecl() { return _OptionDecl; }

    public PrologEntry(IToken leftIToken, IToken rightIToken,
                       DefaultNamespaceDecl _DefaultNamespaceDecl,
                       ASTNodeToken _SEMICOLON,
                       ISetter _Setter,
                       NamespaceDecl _NamespaceDecl,
                       IImport _Import,
                       IVarDecl _VarDecl,
                       IFunctionDecl _FunctionDecl,
                       OptionDecl _OptionDecl)
    {
        super(leftIToken, rightIToken);

        this._DefaultNamespaceDecl = _DefaultNamespaceDecl;
        if (_DefaultNamespaceDecl != null) ((ASTNode) _DefaultNamespaceDecl).setParent(this);
        this._SEMICOLON = _SEMICOLON;
        ((ASTNode) _SEMICOLON).setParent(this);
        this._Setter = _Setter;
        if (_Setter != null) ((ASTNode) _Setter).setParent(this);
        this._NamespaceDecl = _NamespaceDecl;
        if (_NamespaceDecl != null) ((ASTNode) _NamespaceDecl).setParent(this);
        this._Import = _Import;
        if (_Import != null) ((ASTNode) _Import).setParent(this);
        this._VarDecl = _VarDecl;
        if (_VarDecl != null) ((ASTNode) _VarDecl).setParent(this);
        this._FunctionDecl = _FunctionDecl;
        if (_FunctionDecl != null) ((ASTNode) _FunctionDecl).setParent(this);
        this._OptionDecl = _OptionDecl;
        if (_OptionDecl != null) ((ASTNode) _OptionDecl).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_DefaultNamespaceDecl);
        list.add(_SEMICOLON);
        list.add(_Setter);
        list.add(_NamespaceDecl);
        list.add(_Import);
        list.add(_VarDecl);
        list.add(_FunctionDecl);
        list.add(_OptionDecl);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof PrologEntry)) return false;
        if (! super.equals(o)) return false;
        PrologEntry other = (PrologEntry) o;
        if (_DefaultNamespaceDecl == null)
            if (other._DefaultNamespaceDecl != null) return false;
            else; // continue
        else if (! _DefaultNamespaceDecl.equals(other._DefaultNamespaceDecl)) return false;
        if (! _SEMICOLON.equals(other._SEMICOLON)) return false;
        if (_Setter == null)
            if (other._Setter != null) return false;
            else; // continue
        else if (! _Setter.equals(other._Setter)) return false;
        if (_NamespaceDecl == null)
            if (other._NamespaceDecl != null) return false;
            else; // continue
        else if (! _NamespaceDecl.equals(other._NamespaceDecl)) return false;
        if (_Import == null)
            if (other._Import != null) return false;
            else; // continue
        else if (! _Import.equals(other._Import)) return false;
        if (_VarDecl == null)
            if (other._VarDecl != null) return false;
            else; // continue
        else if (! _VarDecl.equals(other._VarDecl)) return false;
        if (_FunctionDecl == null)
            if (other._FunctionDecl != null) return false;
            else; // continue
        else if (! _FunctionDecl.equals(other._FunctionDecl)) return false;
        if (_OptionDecl == null)
            if (other._OptionDecl != null) return false;
            else; // continue
        else if (! _OptionDecl.equals(other._OptionDecl)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_DefaultNamespaceDecl == null ? 0 : _DefaultNamespaceDecl.hashCode());
        hash = hash * 31 + (_SEMICOLON.hashCode());
        hash = hash * 31 + (_Setter == null ? 0 : _Setter.hashCode());
        hash = hash * 31 + (_NamespaceDecl == null ? 0 : _NamespaceDecl.hashCode());
        hash = hash * 31 + (_Import == null ? 0 : _Import.hashCode());
        hash = hash * 31 + (_VarDecl == null ? 0 : _VarDecl.hashCode());
        hash = hash * 31 + (_FunctionDecl == null ? 0 : _FunctionDecl.hashCode());
        hash = hash * 31 + (_OptionDecl == null ? 0 : _OptionDecl.hashCode());
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
            if (_DefaultNamespaceDecl != null) _DefaultNamespaceDecl.accept(v);
            _SEMICOLON.accept(v);
            if (_Setter != null) _Setter.accept(v);
            if (_NamespaceDecl != null) _NamespaceDecl.accept(v);
            if (_Import != null) _Import.accept(v);
            if (_VarDecl != null) _VarDecl.accept(v);
            if (_FunctionDecl != null) _FunctionDecl.accept(v);
            if (_OptionDecl != null) _OptionDecl.accept(v);
        }
        v.endVisit(this);
    }
}


