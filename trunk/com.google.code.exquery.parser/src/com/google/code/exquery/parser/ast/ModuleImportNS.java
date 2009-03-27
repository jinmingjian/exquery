
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
 *<li>Rule 57:  ModuleImport ::= import module namespace NCName EQUAL URILiteral AtURILiteralSpecifierOpt
 *</b>
 */
public class ModuleImportNS extends ASTNode implements IModuleImport
{
    private ASTNodeToken _import;
    private ASTNodeToken _module;
    private ASTNodeToken _namespace;
    private ASTNodeToken _NCName;
    private ASTNodeToken _EQUAL;
    private ASTNodeToken _StringLiteral;
    private AtURILiteralSpecifierOpt _AtURILiteralSpecifierOpt;

    public ASTNodeToken getimport() { return _import; }
    public ASTNodeToken getmodule() { return _module; }
    public ASTNodeToken getnamespace() { return _namespace; }
    public ASTNodeToken getNCName() { return _NCName; }
    public ASTNodeToken getEQUAL() { return _EQUAL; }
    public ASTNodeToken getStringLiteral() { return _StringLiteral; }
    /**
     * The value returned by <b>getAtURILiteralSpecifierOpt</b> may be <b>null</b>
     */
    public AtURILiteralSpecifierOpt getAtURILiteralSpecifierOpt() { return _AtURILiteralSpecifierOpt; }

    public ModuleImportNS(IToken leftIToken, IToken rightIToken,
                          ASTNodeToken _import,
                          ASTNodeToken _module,
                          ASTNodeToken _namespace,
                          ASTNodeToken _NCName,
                          ASTNodeToken _EQUAL,
                          ASTNodeToken _StringLiteral,
                          AtURILiteralSpecifierOpt _AtURILiteralSpecifierOpt)
    {
        super(leftIToken, rightIToken);

        this._import = _import;
        ((ASTNode) _import).setParent(this);
        this._module = _module;
        ((ASTNode) _module).setParent(this);
        this._namespace = _namespace;
        ((ASTNode) _namespace).setParent(this);
        this._NCName = _NCName;
        ((ASTNode) _NCName).setParent(this);
        this._EQUAL = _EQUAL;
        ((ASTNode) _EQUAL).setParent(this);
        this._StringLiteral = _StringLiteral;
        ((ASTNode) _StringLiteral).setParent(this);
        this._AtURILiteralSpecifierOpt = _AtURILiteralSpecifierOpt;
        if (_AtURILiteralSpecifierOpt != null) ((ASTNode) _AtURILiteralSpecifierOpt).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_import);
        list.add(_module);
        list.add(_namespace);
        list.add(_NCName);
        list.add(_EQUAL);
        list.add(_StringLiteral);
        list.add(_AtURILiteralSpecifierOpt);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ModuleImportNS)) return false;
        if (! super.equals(o)) return false;
        ModuleImportNS other = (ModuleImportNS) o;
        if (! _import.equals(other._import)) return false;
        if (! _module.equals(other._module)) return false;
        if (! _namespace.equals(other._namespace)) return false;
        if (! _NCName.equals(other._NCName)) return false;
        if (! _EQUAL.equals(other._EQUAL)) return false;
        if (! _StringLiteral.equals(other._StringLiteral)) return false;
        if (_AtURILiteralSpecifierOpt == null)
            if (other._AtURILiteralSpecifierOpt != null) return false;
            else; // continue
        else if (! _AtURILiteralSpecifierOpt.equals(other._AtURILiteralSpecifierOpt)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_import.hashCode());
        hash = hash * 31 + (_module.hashCode());
        hash = hash * 31 + (_namespace.hashCode());
        hash = hash * 31 + (_NCName.hashCode());
        hash = hash * 31 + (_EQUAL.hashCode());
        hash = hash * 31 + (_StringLiteral.hashCode());
        hash = hash * 31 + (_AtURILiteralSpecifierOpt == null ? 0 : _AtURILiteralSpecifierOpt.hashCode());
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
            _import.accept(v);
            _module.accept(v);
            _namespace.accept(v);
            _NCName.accept(v);
            _EQUAL.accept(v);
            _StringLiteral.accept(v);
            if (_AtURILiteralSpecifierOpt != null) _AtURILiteralSpecifierOpt.accept(v);
        }
        v.endVisit(this);
    }
}


