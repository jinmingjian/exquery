
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
 * is always implemented by <b>ASTNodeToken</b>. It is also implemented by:
 *<b>
 *<ul>
 *<li>PreserveMode
 *<li>InheritMode
 *<li>EnclosedExpr
 *<li>SortDirectionSegment
 *<li>PlusOrMinus
 *<li>PathExpr
 *<li>RelativePathExpr
 *<li>AxisStepReverse
 *<li>AxisStepForward
 *<li>AbbrevReverseStep
 *<li>FilterExpr
 *<li>Literal
 *<li>ContextItemExpr
 *<li>DirectConstructor
 *<li>DirAttributeValueQuot
 *<li>DirAttributeValueApos
 *<li>CommonContent
 *<li>DirPIConstructor
 *<li>OccurrenceIndicator
 *<li>ValidationMode0
 *<li>ValidationMode1
 *<li>Wildcard0
 *<li>Wildcard1
 *<li>Wildcard2
 *<li>NumericLiteral0
 *<li>NumericLiteral1
 *<li>NumericLiteral2
 *<li>DirElemConstructor0
 *<li>DirElemConstructor1
 *<li>DirElemContent0
 *<li>DirElemContent1
 *<li>QName0
 *<li>QName1
 *</ul>
 *</b>
 */
public interface IASTNodeToken
{
    public IToken getLeftIToken();
    public IToken getRightIToken();

    void accept(IAstVisitor v);
}


