
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
 *<li>FLWORExpr
 *<li>SomeQuantifiedExpr
 *<li>EveryQuantifiedExpr
 *<li>TypeswitchExpr
 *<li>IfExpr
 *<li>OrExpr
 *<li>AndExpr
 *<li>ComparisonExpr
 *<li>RangeExpr
 *<li>AdditiveExpr
 *<li>MultiplicativeExpr
 *<li>UnionExpr
 *<li>IntersectExceptExpr
 *<li>InstanceofExpr
 *<li>TreatExpr
 *<li>CastableExpr
 *<li>CastExpr
 *<li>UnaryExpr
 *<li>ValidateExpr
 *<li>PathExpr
 *<li>RelativePathExpr
 *<li>AxisStepReverse
 *<li>AxisStepForward
 *<li>FilterExpr
 *<li>ExtensionExpr0
 *<li>ExtensionExpr1
 *</ul>
 *</b>
 */
public interface IExprSingle
{
    public IToken getLeftIToken();
    public IToken getRightIToken();

    void accept(IAstVisitor v);
}


