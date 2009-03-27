
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
 *<li>Rule 53:  SchemaImport ::= import schema SchemaPrefixOpt URILiteral AtURILiteralSpecifierOpt
 *</b>
 */
public class SchemaImport extends ASTNode implements ISchemaImport
{
    private ASTNodeToken _import;
    private ASTNodeToken _schema;
    private ISchemaPrefixOpt _SchemaPrefixOpt;
    private ASTNodeToken _StringLiteral;
    private AtURILiteralSpecifierOpt _AtURILiteralSpecifierOpt;

    public ASTNodeToken getimport() { return _import; }
    public ASTNodeToken getschema() { return _schema; }
    /**
     * The value returned by <b>getSchemaPrefixOpt</b> may be <b>null</b>
     */
    public ISchemaPrefixOpt getSchemaPrefixOpt() { return _SchemaPrefixOpt; }
    public ASTNodeToken getStringLiteral() { return _StringLiteral; }
    /**
     * The value returned by <b>getAtURILiteralSpecifierOpt</b> may be <b>null</b>
     */
    public AtURILiteralSpecifierOpt getAtURILiteralSpecifierOpt() { return _AtURILiteralSpecifierOpt; }

    public SchemaImport(IToken leftIToken, IToken rightIToken,
                        ASTNodeToken _import,
                        ASTNodeToken _schema,
                        ISchemaPrefixOpt _SchemaPrefixOpt,
                        ASTNodeToken _StringLiteral,
                        AtURILiteralSpecifierOpt _AtURILiteralSpecifierOpt)
    {
        super(leftIToken, rightIToken);

        this._import = _import;
        ((ASTNode) _import).setParent(this);
        this._schema = _schema;
        ((ASTNode) _schema).setParent(this);
        this._SchemaPrefixOpt = _SchemaPrefixOpt;
        if (_SchemaPrefixOpt != null) ((ASTNode) _SchemaPrefixOpt).setParent(this);
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
        list.add(_schema);
        list.add(_SchemaPrefixOpt);
        list.add(_StringLiteral);
        list.add(_AtURILiteralSpecifierOpt);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof SchemaImport)) return false;
        if (! super.equals(o)) return false;
        SchemaImport other = (SchemaImport) o;
        if (! _import.equals(other._import)) return false;
        if (! _schema.equals(other._schema)) return false;
        if (_SchemaPrefixOpt == null)
            if (other._SchemaPrefixOpt != null) return false;
            else; // continue
        else if (! _SchemaPrefixOpt.equals(other._SchemaPrefixOpt)) return false;
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
        hash = hash * 31 + (_schema.hashCode());
        hash = hash * 31 + (_SchemaPrefixOpt == null ? 0 : _SchemaPrefixOpt.hashCode());
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
            _schema.accept(v);
            if (_SchemaPrefixOpt != null) _SchemaPrefixOpt.accept(v);
            _StringLiteral.accept(v);
            if (_AtURILiteralSpecifierOpt != null) _AtURILiteralSpecifierOpt.accept(v);
        }
        v.endVisit(this);
    }
}


