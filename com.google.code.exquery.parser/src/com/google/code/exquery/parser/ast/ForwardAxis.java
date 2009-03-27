
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
 *<li>Rule 208:  ForwardAxis ::= child DoubleCOLON
 *<li>Rule 209:  ForwardAxis ::= descendant DoubleCOLON
 *<li>Rule 210:  ForwardAxis ::= attribute DoubleCOLON
 *<li>Rule 211:  ForwardAxis ::= self DoubleCOLON
 *<li>Rule 212:  ForwardAxis ::= descendant_or_self DoubleCOLON
 *<li>Rule 213:  ForwardAxis ::= following_sibling DoubleCOLON
 *<li>Rule 214:  ForwardAxis ::= following DoubleCOLON
 *</b>
 */
public class ForwardAxis extends ASTNode implements IForwardAxis
{
    private ASTNodeToken _child;
    private ASTNodeToken _DoubleCOLON;
    private ASTNodeToken _descendant;
    private ASTNodeToken _attribute;
    private ASTNodeToken _self;
    private ASTNodeToken _descendant_or_self;
    private ASTNodeToken _following_sibling;
    private ASTNodeToken _following;

    /**
     * The value returned by <b>getchild</b> may be <b>null</b>
     */
    public ASTNodeToken getchild() { return _child; }
    public ASTNodeToken getDoubleCOLON() { return _DoubleCOLON; }
    /**
     * The value returned by <b>getdescendant</b> may be <b>null</b>
     */
    public ASTNodeToken getdescendant() { return _descendant; }
    /**
     * The value returned by <b>getattribute</b> may be <b>null</b>
     */
    public ASTNodeToken getattribute() { return _attribute; }
    /**
     * The value returned by <b>getself</b> may be <b>null</b>
     */
    public ASTNodeToken getself() { return _self; }
    /**
     * The value returned by <b>getdescendant_or_self</b> may be <b>null</b>
     */
    public ASTNodeToken getdescendant_or_self() { return _descendant_or_self; }
    /**
     * The value returned by <b>getfollowing_sibling</b> may be <b>null</b>
     */
    public ASTNodeToken getfollowing_sibling() { return _following_sibling; }
    /**
     * The value returned by <b>getfollowing</b> may be <b>null</b>
     */
    public ASTNodeToken getfollowing() { return _following; }

    public ForwardAxis(IToken leftIToken, IToken rightIToken,
                       ASTNodeToken _child,
                       ASTNodeToken _DoubleCOLON,
                       ASTNodeToken _descendant,
                       ASTNodeToken _attribute,
                       ASTNodeToken _self,
                       ASTNodeToken _descendant_or_self,
                       ASTNodeToken _following_sibling,
                       ASTNodeToken _following)
    {
        super(leftIToken, rightIToken);

        this._child = _child;
        if (_child != null) ((ASTNode) _child).setParent(this);
        this._DoubleCOLON = _DoubleCOLON;
        ((ASTNode) _DoubleCOLON).setParent(this);
        this._descendant = _descendant;
        if (_descendant != null) ((ASTNode) _descendant).setParent(this);
        this._attribute = _attribute;
        if (_attribute != null) ((ASTNode) _attribute).setParent(this);
        this._self = _self;
        if (_self != null) ((ASTNode) _self).setParent(this);
        this._descendant_or_self = _descendant_or_self;
        if (_descendant_or_self != null) ((ASTNode) _descendant_or_self).setParent(this);
        this._following_sibling = _following_sibling;
        if (_following_sibling != null) ((ASTNode) _following_sibling).setParent(this);
        this._following = _following;
        if (_following != null) ((ASTNode) _following).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_child);
        list.add(_DoubleCOLON);
        list.add(_descendant);
        list.add(_attribute);
        list.add(_self);
        list.add(_descendant_or_self);
        list.add(_following_sibling);
        list.add(_following);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ForwardAxis)) return false;
        if (! super.equals(o)) return false;
        ForwardAxis other = (ForwardAxis) o;
        if (_child == null)
            if (other._child != null) return false;
            else; // continue
        else if (! _child.equals(other._child)) return false;
        if (! _DoubleCOLON.equals(other._DoubleCOLON)) return false;
        if (_descendant == null)
            if (other._descendant != null) return false;
            else; // continue
        else if (! _descendant.equals(other._descendant)) return false;
        if (_attribute == null)
            if (other._attribute != null) return false;
            else; // continue
        else if (! _attribute.equals(other._attribute)) return false;
        if (_self == null)
            if (other._self != null) return false;
            else; // continue
        else if (! _self.equals(other._self)) return false;
        if (_descendant_or_self == null)
            if (other._descendant_or_self != null) return false;
            else; // continue
        else if (! _descendant_or_self.equals(other._descendant_or_self)) return false;
        if (_following_sibling == null)
            if (other._following_sibling != null) return false;
            else; // continue
        else if (! _following_sibling.equals(other._following_sibling)) return false;
        if (_following == null)
            if (other._following != null) return false;
            else; // continue
        else if (! _following.equals(other._following)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_child == null ? 0 : _child.hashCode());
        hash = hash * 31 + (_DoubleCOLON.hashCode());
        hash = hash * 31 + (_descendant == null ? 0 : _descendant.hashCode());
        hash = hash * 31 + (_attribute == null ? 0 : _attribute.hashCode());
        hash = hash * 31 + (_self == null ? 0 : _self.hashCode());
        hash = hash * 31 + (_descendant_or_self == null ? 0 : _descendant_or_self.hashCode());
        hash = hash * 31 + (_following_sibling == null ? 0 : _following_sibling.hashCode());
        hash = hash * 31 + (_following == null ? 0 : _following.hashCode());
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
            if (_child != null) _child.accept(v);
            _DoubleCOLON.accept(v);
            if (_descendant != null) _descendant.accept(v);
            if (_attribute != null) _attribute.accept(v);
            if (_self != null) _self.accept(v);
            if (_descendant_or_self != null) _descendant_or_self.accept(v);
            if (_following_sibling != null) _following_sibling.accept(v);
            if (_following != null) _following.accept(v);
        }
        v.endVisit(this);
    }
}


