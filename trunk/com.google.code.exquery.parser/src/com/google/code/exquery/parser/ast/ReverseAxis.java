
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
 *<li>Rule 219:  ReverseAxis ::= parent DoubleCOLON
 *<li>Rule 220:  ReverseAxis ::= ancestor DoubleCOLON
 *<li>Rule 221:  ReverseAxis ::= preceding_sibling DoubleCOLON
 *<li>Rule 222:  ReverseAxis ::= preceding DoubleCOLON
 *<li>Rule 223:  ReverseAxis ::= ancestor_or_self DoubleCOLON
 *</b>
 */
public class ReverseAxis extends ASTNode implements IReverseAxis
{
    private ASTNodeToken _parent;
    private ASTNodeToken _DoubleCOLON;
    private ASTNodeToken _ancestor;
    private ASTNodeToken _preceding_sibling;
    private ASTNodeToken _preceding;
    private ASTNodeToken _ancestor_or_self;

    /**
     * The value returned by <b>getparent</b> may be <b>null</b>
     */
    public ASTNodeToken getparent() { return _parent; }
    public ASTNodeToken getDoubleCOLON() { return _DoubleCOLON; }
    /**
     * The value returned by <b>getancestor</b> may be <b>null</b>
     */
    public ASTNodeToken getancestor() { return _ancestor; }
    /**
     * The value returned by <b>getpreceding_sibling</b> may be <b>null</b>
     */
    public ASTNodeToken getpreceding_sibling() { return _preceding_sibling; }
    /**
     * The value returned by <b>getpreceding</b> may be <b>null</b>
     */
    public ASTNodeToken getpreceding() { return _preceding; }
    /**
     * The value returned by <b>getancestor_or_self</b> may be <b>null</b>
     */
    public ASTNodeToken getancestor_or_self() { return _ancestor_or_self; }

    public ReverseAxis(IToken leftIToken, IToken rightIToken,
                       ASTNodeToken _parent,
                       ASTNodeToken _DoubleCOLON,
                       ASTNodeToken _ancestor,
                       ASTNodeToken _preceding_sibling,
                       ASTNodeToken _preceding,
                       ASTNodeToken _ancestor_or_self)
    {
        super(leftIToken, rightIToken);

        this._parent = _parent;
        if (_parent != null) ((ASTNode) _parent).setParent(this);
        this._DoubleCOLON = _DoubleCOLON;
        ((ASTNode) _DoubleCOLON).setParent(this);
        this._ancestor = _ancestor;
        if (_ancestor != null) ((ASTNode) _ancestor).setParent(this);
        this._preceding_sibling = _preceding_sibling;
        if (_preceding_sibling != null) ((ASTNode) _preceding_sibling).setParent(this);
        this._preceding = _preceding;
        if (_preceding != null) ((ASTNode) _preceding).setParent(this);
        this._ancestor_or_self = _ancestor_or_self;
        if (_ancestor_or_self != null) ((ASTNode) _ancestor_or_self).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_parent);
        list.add(_DoubleCOLON);
        list.add(_ancestor);
        list.add(_preceding_sibling);
        list.add(_preceding);
        list.add(_ancestor_or_self);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ReverseAxis)) return false;
        if (! super.equals(o)) return false;
        ReverseAxis other = (ReverseAxis) o;
        if (_parent == null)
            if (other._parent != null) return false;
            else; // continue
        else if (! _parent.equals(other._parent)) return false;
        if (! _DoubleCOLON.equals(other._DoubleCOLON)) return false;
        if (_ancestor == null)
            if (other._ancestor != null) return false;
            else; // continue
        else if (! _ancestor.equals(other._ancestor)) return false;
        if (_preceding_sibling == null)
            if (other._preceding_sibling != null) return false;
            else; // continue
        else if (! _preceding_sibling.equals(other._preceding_sibling)) return false;
        if (_preceding == null)
            if (other._preceding != null) return false;
            else; // continue
        else if (! _preceding.equals(other._preceding)) return false;
        if (_ancestor_or_self == null)
            if (other._ancestor_or_self != null) return false;
            else; // continue
        else if (! _ancestor_or_self.equals(other._ancestor_or_self)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_parent == null ? 0 : _parent.hashCode());
        hash = hash * 31 + (_DoubleCOLON.hashCode());
        hash = hash * 31 + (_ancestor == null ? 0 : _ancestor.hashCode());
        hash = hash * 31 + (_preceding_sibling == null ? 0 : _preceding_sibling.hashCode());
        hash = hash * 31 + (_preceding == null ? 0 : _preceding.hashCode());
        hash = hash * 31 + (_ancestor_or_self == null ? 0 : _ancestor_or_self.hashCode());
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
            if (_parent != null) _parent.accept(v);
            _DoubleCOLON.accept(v);
            if (_ancestor != null) _ancestor.accept(v);
            if (_preceding_sibling != null) _preceding_sibling.accept(v);
            if (_preceding != null) _preceding.accept(v);
            if (_ancestor_or_self != null) _ancestor_or_self.accept(v);
        }
        v.endVisit(this);
    }
}


