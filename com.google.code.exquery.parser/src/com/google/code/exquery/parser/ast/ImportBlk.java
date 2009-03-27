
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
 *<li>Rule 28:  Import ::= SchemaImport
 *<li>Rule 29:  Import ::= ModuleImport
 *</b>
 */
public class ImportBlk extends ASTNode implements IImport
{
    private SchemaImport _SchemaImport;
    private IModuleImport _ModuleImport;

    /**
     * The value returned by <b>getSchemaImport</b> may be <b>null</b>
     */
    public SchemaImport getSchemaImport() { return _SchemaImport; }
    /**
     * The value returned by <b>getModuleImport</b> may be <b>null</b>
     */
    public IModuleImport getModuleImport() { return _ModuleImport; }

    public ImportBlk(IToken leftIToken, IToken rightIToken,
                     SchemaImport _SchemaImport,
                     IModuleImport _ModuleImport)
    {
        super(leftIToken, rightIToken);

        this._SchemaImport = _SchemaImport;
        if (_SchemaImport != null) ((ASTNode) _SchemaImport).setParent(this);
        this._ModuleImport = _ModuleImport;
        if (_ModuleImport != null) ((ASTNode) _ModuleImport).setParent(this);
        initialize();
    }

    /**
     * A list of all children of this node, including the null ones.
     */
    public java.util.ArrayList getAllChildren()
    {
        java.util.ArrayList list = new java.util.ArrayList();
        list.add(_SchemaImport);
        list.add(_ModuleImport);
        return list;
    }

    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (! (o instanceof ImportBlk)) return false;
        if (! super.equals(o)) return false;
        ImportBlk other = (ImportBlk) o;
        if (_SchemaImport == null)
            if (other._SchemaImport != null) return false;
            else; // continue
        else if (! _SchemaImport.equals(other._SchemaImport)) return false;
        if (_ModuleImport == null)
            if (other._ModuleImport != null) return false;
            else; // continue
        else if (! _ModuleImport.equals(other._ModuleImport)) return false;
        return true;
    }

    public int hashCode()
    {
        int hash = super.hashCode();
        hash = hash * 31 + (_SchemaImport == null ? 0 : _SchemaImport.hashCode());
        hash = hash * 31 + (_ModuleImport == null ? 0 : _ModuleImport.hashCode());
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
            if (_SchemaImport != null) _SchemaImport.accept(v);
            if (_ModuleImport != null) _ModuleImport.accept(v);
        }
        v.endVisit(this);
    }
}


