
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
 *<em>
 *<li>Rule 4:  VersionDeclOpt ::= $Empty
 *</em>
 *<p>
 *<b>
 *<li>Rule 5:  VersionDeclOpt ::= xquery version StringLiteral EncodingOpt Separator
 *</b>
 */
public class VersionDeclOpt extends ASTNode implements IVersionDeclOpt
{
    private ASTNodeToken _xquery;
    private ASTNodeToken _version;
    private ASTNodeToken _StringLiteral;
    private EncodingOpt _EncodingOpt;
    private ASTNodeToken _SEMICOLON;

    public ASTNodeToken getxquery() { return _xquery; }
    public ASTNodeToken getversion() { return _version; }
    public ASTNodeToken getStringLiteral() { return _StringLiteral; }
    /**
     * The value returned by <b>getEncodingOpt</b> may be <b>null</b>
     */
    public EncodingOpt getEncodingOpt() { return _EncodingOpt; }
    public ASTNodeToken getSEMICOLON() { return _SEMICOLON; }

    public VersionDeclOpt(IToken leftIToken, IToken rightIToken,
                          ASTNodeToken _xquery,
                          ASTNodeToken _version,
                          ASTNodeToken _StringLiteral,
                          EncodingOpt _EncodingOpt,
                          ASTNodeToken _SEMICOLON)
    {
        super(leftIToken, rightIToken);

        this._xquery = _xquery;
        ((ASTNode) _xquery).setParent(this);
        this._version = _version;
        ((ASTNode) _version).setParent(this);
        this._StringLiteral = _StringLiteral;
        ((ASTNode) _StringLiteral).setParent(this);
        this._EncodingOpt = _EncodingOpt;
        if (_EncodingOpt != null) ((ASTNode) _EncodingOpt).setParent(this);
        this._SEMICOLON = _SEMICOLON;
        ((ASTNode) _SEMICOLON).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_xquery);
        list.add(_version);
        list.add(_StringLiteral);
        list.add(_EncodingOpt);
        list.add(_SEMICOLON);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof VersionDeclOpt)) return false;
        if (! super.equals(o)) return false;
        VersionDeclOpt other = (VersionDeclOpt) o;
        if (! _xquery.equals(other._xquery)) return false;
        if (! _version.equals(other._version)) return false;
        if (! _StringLiteral.equals(other._StringLiteral)) return false;
        if (_EncodingOpt == null)
            if (other._EncodingOpt != null) return false;
            else; // continue
        else if (! _EncodingOpt.equals(other._EncodingOpt)) return false;
        if (! _SEMICOLON.equals(other._SEMICOLON)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_xquery.hashCode());
        hash = hash * 31 + (_version.hashCode());
        hash = hash * 31 + (_StringLiteral.hashCode());
        hash = hash * 31 + (_EncodingOpt == null ? 0 : _EncodingOpt.hashCode());
        hash = hash * 31 + (_SEMICOLON.hashCode());
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
            _xquery.accept(v);
            _version.accept(v);
            _StringLiteral.accept(v);
            if (_EncodingOpt != null) _EncodingOpt.accept(v);
            _SEMICOLON.accept(v);
        }
        v.endVisit(this);
    }
}


