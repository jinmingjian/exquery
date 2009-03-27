
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
 *<li>Rule 137:  ComparisonExpr ::= RangeExpr EQUAL RangeExpr
 *<li>Rule 138:  ComparisonExpr ::= RangeExpr NOTEQUAL RangeExpr
 *<li>Rule 139:  ComparisonExpr ::= RangeExpr LESS RangeExpr
 *<li>Rule 140:  ComparisonExpr ::= RangeExpr LESSEQUAL RangeExpr
 *<li>Rule 141:  ComparisonExpr ::= RangeExpr GREATER RangeExpr
 *<li>Rule 142:  ComparisonExpr ::= RangeExpr GREATEREQUAL RangeExpr
 *<li>Rule 143:  ComparisonExpr ::= RangeExpr eq RangeExpr
 *<li>Rule 144:  ComparisonExpr ::= RangeExpr ne RangeExpr
 *<li>Rule 145:  ComparisonExpr ::= RangeExpr lt RangeExpr
 *<li>Rule 146:  ComparisonExpr ::= RangeExpr le RangeExpr
 *<li>Rule 147:  ComparisonExpr ::= RangeExpr gt RangeExpr
 *<li>Rule 148:  ComparisonExpr ::= RangeExpr ge RangeExpr
 *<li>Rule 149:  ComparisonExpr ::= RangeExpr is RangeExpr
 *<li>Rule 150:  ComparisonExpr ::= RangeExpr NodeCompLeft RangeExpr
 *<li>Rule 151:  ComparisonExpr ::= RangeExpr NodeCompRight RangeExpr
 *</b>
 */
public class ComparisonExpr extends ASTNode implements IComparisonExpr
{
    private IRangeExpr _RangeExpr;
    private ASTNodeToken _EQUAL;
    private IRangeExpr _RangeExpr3;
    private ASTNodeToken _NOTEQUAL;
    private ASTNodeToken _LESS;
    private ASTNodeToken _LESSEQUAL;
    private ASTNodeToken _GREATER;
    private ASTNodeToken _GREATEREQUAL;
    private ASTNodeToken _eq;
    private ASTNodeToken _ne;
    private ASTNodeToken _lt;
    private ASTNodeToken _le;
    private ASTNodeToken _gt;
    private ASTNodeToken _ge;
    private ASTNodeToken _is;
    private ASTNodeToken _NodeCompLeft;
    private ASTNodeToken _NodeCompRight;

    /**
     * The value returned by <b>getRangeExpr</b> may be <b>null</b>
     */
    public IRangeExpr getRangeExpr() { return _RangeExpr; }
    /**
     * The value returned by <b>getEQUAL</b> may be <b>null</b>
     */
    public ASTNodeToken getEQUAL() { return _EQUAL; }
    /**
     * The value returned by <b>getRangeExpr3</b> may be <b>null</b>
     */
    public IRangeExpr getRangeExpr3() { return _RangeExpr3; }
    /**
     * The value returned by <b>getNOTEQUAL</b> may be <b>null</b>
     */
    public ASTNodeToken getNOTEQUAL() { return _NOTEQUAL; }
    /**
     * The value returned by <b>getLESS</b> may be <b>null</b>
     */
    public ASTNodeToken getLESS() { return _LESS; }
    /**
     * The value returned by <b>getLESSEQUAL</b> may be <b>null</b>
     */
    public ASTNodeToken getLESSEQUAL() { return _LESSEQUAL; }
    /**
     * The value returned by <b>getGREATER</b> may be <b>null</b>
     */
    public ASTNodeToken getGREATER() { return _GREATER; }
    /**
     * The value returned by <b>getGREATEREQUAL</b> may be <b>null</b>
     */
    public ASTNodeToken getGREATEREQUAL() { return _GREATEREQUAL; }
    /**
     * The value returned by <b>geteq</b> may be <b>null</b>
     */
    public ASTNodeToken geteq() { return _eq; }
    /**
     * The value returned by <b>getne</b> may be <b>null</b>
     */
    public ASTNodeToken getne() { return _ne; }
    /**
     * The value returned by <b>getlt</b> may be <b>null</b>
     */
    public ASTNodeToken getlt() { return _lt; }
    /**
     * The value returned by <b>getle</b> may be <b>null</b>
     */
    public ASTNodeToken getle() { return _le; }
    /**
     * The value returned by <b>getgt</b> may be <b>null</b>
     */
    public ASTNodeToken getgt() { return _gt; }
    /**
     * The value returned by <b>getge</b> may be <b>null</b>
     */
    public ASTNodeToken getge() { return _ge; }
    /**
     * The value returned by <b>getis</b> may be <b>null</b>
     */
    public ASTNodeToken getis() { return _is; }
    /**
     * The value returned by <b>getNodeCompLeft</b> may be <b>null</b>
     */
    public ASTNodeToken getNodeCompLeft() { return _NodeCompLeft; }
    /**
     * The value returned by <b>getNodeCompRight</b> may be <b>null</b>
     */
    public ASTNodeToken getNodeCompRight() { return _NodeCompRight; }

    public ComparisonExpr(IToken leftIToken, IToken rightIToken,
                          IRangeExpr _RangeExpr,
                          ASTNodeToken _EQUAL,
                          IRangeExpr _RangeExpr3,
                          ASTNodeToken _NOTEQUAL,
                          ASTNodeToken _LESS,
                          ASTNodeToken _LESSEQUAL,
                          ASTNodeToken _GREATER,
                          ASTNodeToken _GREATEREQUAL,
                          ASTNodeToken _eq,
                          ASTNodeToken _ne,
                          ASTNodeToken _lt,
                          ASTNodeToken _le,
                          ASTNodeToken _gt,
                          ASTNodeToken _ge,
                          ASTNodeToken _is,
                          ASTNodeToken _NodeCompLeft,
                          ASTNodeToken _NodeCompRight)
    {
        super(leftIToken, rightIToken);

        this._RangeExpr = _RangeExpr;
        if (_RangeExpr != null) ((ASTNode) _RangeExpr).setParent(this);
        this._EQUAL = _EQUAL;
        if (_EQUAL != null) ((ASTNode) _EQUAL).setParent(this);
        this._RangeExpr3 = _RangeExpr3;
        if (_RangeExpr3 != null) ((ASTNode) _RangeExpr3).setParent(this);
        this._NOTEQUAL = _NOTEQUAL;
        if (_NOTEQUAL != null) ((ASTNode) _NOTEQUAL).setParent(this);
        this._LESS = _LESS;
        if (_LESS != null) ((ASTNode) _LESS).setParent(this);
        this._LESSEQUAL = _LESSEQUAL;
        if (_LESSEQUAL != null) ((ASTNode) _LESSEQUAL).setParent(this);
        this._GREATER = _GREATER;
        if (_GREATER != null) ((ASTNode) _GREATER).setParent(this);
        this._GREATEREQUAL = _GREATEREQUAL;
        if (_GREATEREQUAL != null) ((ASTNode) _GREATEREQUAL).setParent(this);
        this._eq = _eq;
        if (_eq != null) ((ASTNode) _eq).setParent(this);
        this._ne = _ne;
        if (_ne != null) ((ASTNode) _ne).setParent(this);
        this._lt = _lt;
        if (_lt != null) ((ASTNode) _lt).setParent(this);
        this._le = _le;
        if (_le != null) ((ASTNode) _le).setParent(this);
        this._gt = _gt;
        if (_gt != null) ((ASTNode) _gt).setParent(this);
        this._ge = _ge;
        if (_ge != null) ((ASTNode) _ge).setParent(this);
        this._is = _is;
        if (_is != null) ((ASTNode) _is).setParent(this);
        this._NodeCompLeft = _NodeCompLeft;
        if (_NodeCompLeft != null) ((ASTNode) _NodeCompLeft).setParent(this);
        this._NodeCompRight = _NodeCompRight;
        if (_NodeCompRight != null) ((ASTNode) _NodeCompRight).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_RangeExpr);
        list.add(_EQUAL);
        list.add(_RangeExpr3);
        list.add(_NOTEQUAL);
        list.add(_LESS);
        list.add(_LESSEQUAL);
        list.add(_GREATER);
        list.add(_GREATEREQUAL);
        list.add(_eq);
        list.add(_ne);
        list.add(_lt);
        list.add(_le);
        list.add(_gt);
        list.add(_ge);
        list.add(_is);
        list.add(_NodeCompLeft);
        list.add(_NodeCompRight);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ComparisonExpr)) return false;
        if (! super.equals(o)) return false;
        ComparisonExpr other = (ComparisonExpr) o;
        if (_RangeExpr == null)
            if (other._RangeExpr != null) return false;
            else; // continue
        else if (! _RangeExpr.equals(other._RangeExpr)) return false;
        if (_EQUAL == null)
            if (other._EQUAL != null) return false;
            else; // continue
        else if (! _EQUAL.equals(other._EQUAL)) return false;
        if (_RangeExpr3 == null)
            if (other._RangeExpr3 != null) return false;
            else; // continue
        else if (! _RangeExpr3.equals(other._RangeExpr3)) return false;
        if (_NOTEQUAL == null)
            if (other._NOTEQUAL != null) return false;
            else; // continue
        else if (! _NOTEQUAL.equals(other._NOTEQUAL)) return false;
        if (_LESS == null)
            if (other._LESS != null) return false;
            else; // continue
        else if (! _LESS.equals(other._LESS)) return false;
        if (_LESSEQUAL == null)
            if (other._LESSEQUAL != null) return false;
            else; // continue
        else if (! _LESSEQUAL.equals(other._LESSEQUAL)) return false;
        if (_GREATER == null)
            if (other._GREATER != null) return false;
            else; // continue
        else if (! _GREATER.equals(other._GREATER)) return false;
        if (_GREATEREQUAL == null)
            if (other._GREATEREQUAL != null) return false;
            else; // continue
        else if (! _GREATEREQUAL.equals(other._GREATEREQUAL)) return false;
        if (_eq == null)
            if (other._eq != null) return false;
            else; // continue
        else if (! _eq.equals(other._eq)) return false;
        if (_ne == null)
            if (other._ne != null) return false;
            else; // continue
        else if (! _ne.equals(other._ne)) return false;
        if (_lt == null)
            if (other._lt != null) return false;
            else; // continue
        else if (! _lt.equals(other._lt)) return false;
        if (_le == null)
            if (other._le != null) return false;
            else; // continue
        else if (! _le.equals(other._le)) return false;
        if (_gt == null)
            if (other._gt != null) return false;
            else; // continue
        else if (! _gt.equals(other._gt)) return false;
        if (_ge == null)
            if (other._ge != null) return false;
            else; // continue
        else if (! _ge.equals(other._ge)) return false;
        if (_is == null)
            if (other._is != null) return false;
            else; // continue
        else if (! _is.equals(other._is)) return false;
        if (_NodeCompLeft == null)
            if (other._NodeCompLeft != null) return false;
            else; // continue
        else if (! _NodeCompLeft.equals(other._NodeCompLeft)) return false;
        if (_NodeCompRight == null)
            if (other._NodeCompRight != null) return false;
            else; // continue
        else if (! _NodeCompRight.equals(other._NodeCompRight)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_RangeExpr == null ? 0 : _RangeExpr.hashCode());
        hash = hash * 31 + (_EQUAL == null ? 0 : _EQUAL.hashCode());
        hash = hash * 31 + (_RangeExpr3 == null ? 0 : _RangeExpr3.hashCode());
        hash = hash * 31 + (_NOTEQUAL == null ? 0 : _NOTEQUAL.hashCode());
        hash = hash * 31 + (_LESS == null ? 0 : _LESS.hashCode());
        hash = hash * 31 + (_LESSEQUAL == null ? 0 : _LESSEQUAL.hashCode());
        hash = hash * 31 + (_GREATER == null ? 0 : _GREATER.hashCode());
        hash = hash * 31 + (_GREATEREQUAL == null ? 0 : _GREATEREQUAL.hashCode());
        hash = hash * 31 + (_eq == null ? 0 : _eq.hashCode());
        hash = hash * 31 + (_ne == null ? 0 : _ne.hashCode());
        hash = hash * 31 + (_lt == null ? 0 : _lt.hashCode());
        hash = hash * 31 + (_le == null ? 0 : _le.hashCode());
        hash = hash * 31 + (_gt == null ? 0 : _gt.hashCode());
        hash = hash * 31 + (_ge == null ? 0 : _ge.hashCode());
        hash = hash * 31 + (_is == null ? 0 : _is.hashCode());
        hash = hash * 31 + (_NodeCompLeft == null ? 0 : _NodeCompLeft.hashCode());
        hash = hash * 31 + (_NodeCompRight == null ? 0 : _NodeCompRight.hashCode());
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
            if (_RangeExpr != null) _RangeExpr.accept(v);
            if (_EQUAL != null) _EQUAL.accept(v);
            if (_RangeExpr3 != null) _RangeExpr3.accept(v);
            if (_NOTEQUAL != null) _NOTEQUAL.accept(v);
            if (_LESS != null) _LESS.accept(v);
            if (_LESSEQUAL != null) _LESSEQUAL.accept(v);
            if (_GREATER != null) _GREATER.accept(v);
            if (_GREATEREQUAL != null) _GREATEREQUAL.accept(v);
            if (_eq != null) _eq.accept(v);
            if (_ne != null) _ne.accept(v);
            if (_lt != null) _lt.accept(v);
            if (_le != null) _le.accept(v);
            if (_gt != null) _gt.accept(v);
            if (_ge != null) _ge.accept(v);
            if (_is != null) _is.accept(v);
            if (_NodeCompLeft != null) _NodeCompLeft.accept(v);
            if (_NodeCompRight != null) _NodeCompRight.accept(v);
        }
        v.endVisit(this);
    }
}


