
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
 *<li>Rule 342:  PITest ::= processing_instruction LEFTPAREN RIGHTPAREN
 *<li>Rule 343:  PITest ::= processing_instruction LEFTPAREN NCName RIGHTPAREN
 *<li>Rule 344:  PITest ::= processing_instruction LEFTPAREN StringLiteral RIGHTPAREN
 *</b>
 */
public class PITest extends ASTNode implements IPITest
{
    private ASTNodeToken _processing_instruction;
    private ASTNodeToken _LEFTPAREN;
    private ASTNodeToken _RIGHTPAREN;
    private ASTNodeToken _NCName;
    private ASTNodeToken _StringLiteral;

    /**
     * The value returned by <b>getprocessing_instruction</b> may be <b>null</b>
     */
    public ASTNodeToken getprocessing_instruction() { return _processing_instruction; }
    public ASTNodeToken getLEFTPAREN() { return _LEFTPAREN; }
    /**
     * The value returned by <b>getRIGHTPAREN</b> may be <b>null</b>
     */
    public ASTNodeToken getRIGHTPAREN() { return _RIGHTPAREN; }
    /**
     * The value returned by <b>getNCName</b> may be <b>null</b>
     */
    public ASTNodeToken getNCName() { return _NCName; }
    /**
     * The value returned by <b>getStringLiteral</b> may be <b>null</b>
     */
    public ASTNodeToken getStringLiteral() { return _StringLiteral; }

    public PITest(IToken leftIToken, IToken rightIToken,
                  ASTNodeToken _processing_instruction,
                  ASTNodeToken _LEFTPAREN,
                  ASTNodeToken _RIGHTPAREN,
                  ASTNodeToken _NCName,
                  ASTNodeToken _StringLiteral)
    {
        super(leftIToken, rightIToken);

        this._processing_instruction = _processing_instruction;
        if (_processing_instruction != null) ((ASTNode) _processing_instruction).setParent(this);
        this._LEFTPAREN = _LEFTPAREN;
        ((ASTNode) _LEFTPAREN).setParent(this);
        this._RIGHTPAREN = _RIGHTPAREN;
        if (_RIGHTPAREN != null) ((ASTNode) _RIGHTPAREN).setParent(this);
        this._NCName = _NCName;
        if (_NCName != null) ((ASTNode) _NCName).setParent(this);
        this._StringLiteral = _StringLiteral;
        if (_StringLiteral != null) ((ASTNode) _StringLiteral).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_processing_instruction);
        list.add(_LEFTPAREN);
        list.add(_RIGHTPAREN);
        list.add(_NCName);
        list.add(_StringLiteral);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof PITest)) return false;
        if (! super.equals(o)) return false;
        PITest other = (PITest) o;
        if (_processing_instruction == null)
            if (other._processing_instruction != null) return false;
            else; // continue
        else if (! _processing_instruction.equals(other._processing_instruction)) return false;
        if (! _LEFTPAREN.equals(other._LEFTPAREN)) return false;
        if (_RIGHTPAREN == null)
            if (other._RIGHTPAREN != null) return false;
            else; // continue
        else if (! _RIGHTPAREN.equals(other._RIGHTPAREN)) return false;
        if (_NCName == null)
            if (other._NCName != null) return false;
            else; // continue
        else if (! _NCName.equals(other._NCName)) return false;
        if (_StringLiteral == null)
            if (other._StringLiteral != null) return false;
            else; // continue
        else if (! _StringLiteral.equals(other._StringLiteral)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_processing_instruction == null ? 0 : _processing_instruction.hashCode());
        hash = hash * 31 + (_LEFTPAREN.hashCode());
        hash = hash * 31 + (_RIGHTPAREN == null ? 0 : _RIGHTPAREN.hashCode());
        hash = hash * 31 + (_NCName == null ? 0 : _NCName.hashCode());
        hash = hash * 31 + (_StringLiteral == null ? 0 : _StringLiteral.hashCode());
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
            if (_processing_instruction != null) _processing_instruction.accept(v);
            _LEFTPAREN.accept(v);
            if (_RIGHTPAREN != null) _RIGHTPAREN.accept(v);
            if (_NCName != null) _NCName.accept(v);
            if (_StringLiteral != null) _StringLiteral.accept(v);
        }
        v.endVisit(this);
    }
}


