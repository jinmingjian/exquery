
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
 * is implemented by:
 *<b>
 *<ul>
 *<li>Literal
 *<li>VarRef
 *<li>ParenthesizedExpr
 *<li>ContextItemExpr
 *<li>OrderedExpr
 *<li>UnorderedExpr
 *<li>DirectConstructor
 *<li>DirPIConstructor
 *<li>CompDocConstructor
 *<li>CompElemConstructor
 *<li>CompAttrConstructor
 *<li>CompTextConstructor
 *<li>CompCommentConstructor
 *<li>CompPIConstructor
 *<li>NumericLiteral0
 *<li>NumericLiteral1
 *<li>NumericLiteral2
 *<li>FunctionCall0
 *<li>FunctionCall1
 *<li>DirElemConstructor0
 *<li>DirElemConstructor1
 *</ul>
 *</b>
 */
public interface IPrimaryExpr
{
    public IToken getLeftIToken();
    public IToken getRightIToken();

    void accept(IAstVisitor v);
}


