
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
 *<li>ForwardStep
 *<li>AbbrevForwardStep
 *<li>AnyKindTest
 *<li>DocumentTest
 *<li>TextTest
 *<li>CommentTest
 *<li>PITest
 *<li>AttributeTest
 *<li>SchemaAttributeTest
 *<li>ElementTest
 *<li>SchemaElementTest
 *<li>Wildcard0
 *<li>Wildcard1
 *<li>Wildcard2
 *<li>QName0
 *<li>QName1
 *</ul>
 *</b>
 */
public interface IForwardStep
{
    public IToken getLeftIToken();
    public IToken getRightIToken();

    void accept(IAstVisitor v);
}


