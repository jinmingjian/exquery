
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
 *<li>Rule 260:  DirectConstructor ::= DirElemConstructor
 *<li>Rule 262:  DirectConstructor ::= DirPIConstructor
 *</em>
 *<p>
 *<b>
 *<li>Rule 261:  DirectConstructor ::= DirCommentConstructor
 *</b>
 */
public class DirectConstructor extends ASTNodeToken implements IDirectConstructor
{
    public IToken getDirCommentConstructor() { return leftIToken; }

    public DirectConstructor(IToken token) { super(token); initialize(); }

    public void accept(IAstVisitor v)
    {
        if (! v.preVisit(this)) return;
        enter((Visitor) v);
        v.postVisit(this);
    }

    public void enter(Visitor v)
    {
        v.visit(this);
        v.endVisit(this);
    }
}


