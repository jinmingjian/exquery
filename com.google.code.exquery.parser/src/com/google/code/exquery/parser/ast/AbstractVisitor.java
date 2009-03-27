
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

public abstract class AbstractVisitor implements Visitor
{
    public abstract void unimplementedVisitor(String s);

    public boolean preVisit(IAst element) { return true; }

    public void postVisit(IAst element) {}

    public boolean visit(ASTNodeToken n) { unimplementedVisitor("visit(ASTNodeToken)"); return true; }
    public void endVisit(ASTNodeToken n) { unimplementedVisitor("endVisit(ASTNodeToken)"); }

    public boolean visit(Module n) { unimplementedVisitor("visit(Module)"); return true; }
    public void endVisit(Module n) { unimplementedVisitor("endVisit(Module)"); }

    public boolean visit(VersionDeclOpt n) { unimplementedVisitor("visit(VersionDeclOpt)"); return true; }
    public void endVisit(VersionDeclOpt n) { unimplementedVisitor("endVisit(VersionDeclOpt)"); }

    public boolean visit(EncodingOpt n) { unimplementedVisitor("visit(EncodingOpt)"); return true; }
    public void endVisit(EncodingOpt n) { unimplementedVisitor("endVisit(EncodingOpt)"); }

    public boolean visit(MainModule n) { unimplementedVisitor("visit(MainModule)"); return true; }
    public void endVisit(MainModule n) { unimplementedVisitor("endVisit(MainModule)"); }

    public boolean visit(LibraryModule n) { unimplementedVisitor("visit(LibraryModule)"); return true; }
    public void endVisit(LibraryModule n) { unimplementedVisitor("endVisit(LibraryModule)"); }

    public boolean visit(ModuleDecl n) { unimplementedVisitor("visit(ModuleDecl)"); return true; }
    public void endVisit(ModuleDecl n) { unimplementedVisitor("endVisit(ModuleDecl)"); }

    public boolean visit(PrologEntry n) { unimplementedVisitor("visit(PrologEntry)"); return true; }
    public void endVisit(PrologEntry n) { unimplementedVisitor("endVisit(PrologEntry)"); }

    public boolean visit(PrologEntryList n) { unimplementedVisitor("visit(PrologEntryList)"); return true; }
    public void endVisit(PrologEntryList n) { unimplementedVisitor("endVisit(PrologEntryList)"); }

    public boolean visit(Setter n) { unimplementedVisitor("visit(Setter)"); return true; }
    public void endVisit(Setter n) { unimplementedVisitor("endVisit(Setter)"); }

    public boolean visit(ImportBlk n) { unimplementedVisitor("visit(ImportBlk)"); return true; }
    public void endVisit(ImportBlk n) { unimplementedVisitor("endVisit(ImportBlk)"); }

    public boolean visit(NamespaceDecl n) { unimplementedVisitor("visit(NamespaceDecl)"); return true; }
    public void endVisit(NamespaceDecl n) { unimplementedVisitor("endVisit(NamespaceDecl)"); }

    public boolean visit(BoundarySpaceDecl n) { unimplementedVisitor("visit(BoundarySpaceDecl)"); return true; }
    public void endVisit(BoundarySpaceDecl n) { unimplementedVisitor("endVisit(BoundarySpaceDecl)"); }

    public boolean visit(DefaultNamespaceDecl n) { unimplementedVisitor("visit(DefaultNamespaceDecl)"); return true; }
    public void endVisit(DefaultNamespaceDecl n) { unimplementedVisitor("endVisit(DefaultNamespaceDecl)"); }

    public boolean visit(OptionDecl n) { unimplementedVisitor("visit(OptionDecl)"); return true; }
    public void endVisit(OptionDecl n) { unimplementedVisitor("endVisit(OptionDecl)"); }

    public boolean visit(OrderingModeDecl n) { unimplementedVisitor("visit(OrderingModeDecl)"); return true; }
    public void endVisit(OrderingModeDecl n) { unimplementedVisitor("endVisit(OrderingModeDecl)"); }

    public boolean visit(EmptyOrderDecl n) { unimplementedVisitor("visit(EmptyOrderDecl)"); return true; }
    public void endVisit(EmptyOrderDecl n) { unimplementedVisitor("endVisit(EmptyOrderDecl)"); }

    public boolean visit(CopyNamespacesDecl n) { unimplementedVisitor("visit(CopyNamespacesDecl)"); return true; }
    public void endVisit(CopyNamespacesDecl n) { unimplementedVisitor("endVisit(CopyNamespacesDecl)"); }

    public boolean visit(PreserveMode n) { unimplementedVisitor("visit(PreserveMode)"); return true; }
    public void endVisit(PreserveMode n) { unimplementedVisitor("endVisit(PreserveMode)"); }

    public boolean visit(InheritMode n) { unimplementedVisitor("visit(InheritMode)"); return true; }
    public void endVisit(InheritMode n) { unimplementedVisitor("endVisit(InheritMode)"); }

    public boolean visit(DefaultCollationDecl n) { unimplementedVisitor("visit(DefaultCollationDecl)"); return true; }
    public void endVisit(DefaultCollationDecl n) { unimplementedVisitor("endVisit(DefaultCollationDecl)"); }

    public boolean visit(BaseURIDecl n) { unimplementedVisitor("visit(BaseURIDecl)"); return true; }
    public void endVisit(BaseURIDecl n) { unimplementedVisitor("endVisit(BaseURIDecl)"); }

    public boolean visit(URILiteralList n) { unimplementedVisitor("visit(URILiteralList)"); return true; }
    public void endVisit(URILiteralList n) { unimplementedVisitor("endVisit(URILiteralList)"); }

    public boolean visit(AtURILiteralSpecifierOpt n) { unimplementedVisitor("visit(AtURILiteralSpecifierOpt)"); return true; }
    public void endVisit(AtURILiteralSpecifierOpt n) { unimplementedVisitor("endVisit(AtURILiteralSpecifierOpt)"); }

    public boolean visit(SchemaImport n) { unimplementedVisitor("visit(SchemaImport)"); return true; }
    public void endVisit(SchemaImport n) { unimplementedVisitor("endVisit(SchemaImport)"); }

    public boolean visit(SchemaPrefixNamed n) { unimplementedVisitor("visit(SchemaPrefixNamed)"); return true; }
    public void endVisit(SchemaPrefixNamed n) { unimplementedVisitor("endVisit(SchemaPrefixNamed)"); }

    public boolean visit(SchemaPrefixDefault n) { unimplementedVisitor("visit(SchemaPrefixDefault)"); return true; }
    public void endVisit(SchemaPrefixDefault n) { unimplementedVisitor("endVisit(SchemaPrefixDefault)"); }

    public boolean visit(ModuleImport n) { unimplementedVisitor("visit(ModuleImport)"); return true; }
    public void endVisit(ModuleImport n) { unimplementedVisitor("endVisit(ModuleImport)"); }

    public boolean visit(ModuleImportNS n) { unimplementedVisitor("visit(ModuleImportNS)"); return true; }
    public void endVisit(ModuleImportNS n) { unimplementedVisitor("endVisit(ModuleImportNS)"); }

    public boolean visit(VarDecl n) { unimplementedVisitor("visit(VarDecl)"); return true; }
    public void endVisit(VarDecl n) { unimplementedVisitor("endVisit(VarDecl)"); }

    public boolean visit(VarDeclExternal n) { unimplementedVisitor("visit(VarDeclExternal)"); return true; }
    public void endVisit(VarDeclExternal n) { unimplementedVisitor("endVisit(VarDeclExternal)"); }

    public boolean visit(ConstructionDecl n) { unimplementedVisitor("visit(ConstructionDecl)"); return true; }
    public void endVisit(ConstructionDecl n) { unimplementedVisitor("endVisit(ConstructionDecl)"); }

    public boolean visit(AsSequenceTypeOpt n) { unimplementedVisitor("visit(AsSequenceTypeOpt)"); return true; }
    public void endVisit(AsSequenceTypeOpt n) { unimplementedVisitor("endVisit(AsSequenceTypeOpt)"); }

    public boolean visit(FunctionDecl n) { unimplementedVisitor("visit(FunctionDecl)"); return true; }
    public void endVisit(FunctionDecl n) { unimplementedVisitor("endVisit(FunctionDecl)"); }

    public boolean visit(FunctionDeclExternal n) { unimplementedVisitor("visit(FunctionDeclExternal)"); return true; }
    public void endVisit(FunctionDeclExternal n) { unimplementedVisitor("endVisit(FunctionDeclExternal)"); }

    public boolean visit(ParamList n) { unimplementedVisitor("visit(ParamList)"); return true; }
    public void endVisit(ParamList n) { unimplementedVisitor("endVisit(ParamList)"); }

    public boolean visit(Param n) { unimplementedVisitor("visit(Param)"); return true; }
    public void endVisit(Param n) { unimplementedVisitor("endVisit(Param)"); }

    public boolean visit(EnclosedExpr n) { unimplementedVisitor("visit(EnclosedExpr)"); return true; }
    public void endVisit(EnclosedExpr n) { unimplementedVisitor("endVisit(EnclosedExpr)"); }

    public boolean visit(ExprSingleList n) { unimplementedVisitor("visit(ExprSingleList)"); return true; }
    public void endVisit(ExprSingleList n) { unimplementedVisitor("endVisit(ExprSingleList)"); }

    public boolean visit(ForOrLetClauseList n) { unimplementedVisitor("visit(ForOrLetClauseList)"); return true; }
    public void endVisit(ForOrLetClauseList n) { unimplementedVisitor("endVisit(ForOrLetClauseList)"); }

    public boolean visit(FLWORExpr n) { unimplementedVisitor("visit(FLWORExpr)"); return true; }
    public void endVisit(FLWORExpr n) { unimplementedVisitor("endVisit(FLWORExpr)"); }

    public boolean visit(ForClauseVarBinding n) { unimplementedVisitor("visit(ForClauseVarBinding)"); return true; }
    public void endVisit(ForClauseVarBinding n) { unimplementedVisitor("endVisit(ForClauseVarBinding)"); }

    public boolean visit(ForClauseVarBindingList n) { unimplementedVisitor("visit(ForClauseVarBindingList)"); return true; }
    public void endVisit(ForClauseVarBindingList n) { unimplementedVisitor("endVisit(ForClauseVarBindingList)"); }

    public boolean visit(ForClause n) { unimplementedVisitor("visit(ForClause)"); return true; }
    public void endVisit(ForClause n) { unimplementedVisitor("endVisit(ForClause)"); }

    public boolean visit(PositionalVar n) { unimplementedVisitor("visit(PositionalVar)"); return true; }
    public void endVisit(PositionalVar n) { unimplementedVisitor("endVisit(PositionalVar)"); }

    public boolean visit(LetClauseVarBinding n) { unimplementedVisitor("visit(LetClauseVarBinding)"); return true; }
    public void endVisit(LetClauseVarBinding n) { unimplementedVisitor("endVisit(LetClauseVarBinding)"); }

    public boolean visit(LetClauseVarBindingList n) { unimplementedVisitor("visit(LetClauseVarBindingList)"); return true; }
    public void endVisit(LetClauseVarBindingList n) { unimplementedVisitor("endVisit(LetClauseVarBindingList)"); }

    public boolean visit(LetClause n) { unimplementedVisitor("visit(LetClause)"); return true; }
    public void endVisit(LetClause n) { unimplementedVisitor("endVisit(LetClause)"); }

    public boolean visit(WhereClause n) { unimplementedVisitor("visit(WhereClause)"); return true; }
    public void endVisit(WhereClause n) { unimplementedVisitor("endVisit(WhereClause)"); }

    public boolean visit(OrderByClause n) { unimplementedVisitor("visit(OrderByClause)"); return true; }
    public void endVisit(OrderByClause n) { unimplementedVisitor("endVisit(OrderByClause)"); }

    public boolean visit(OrderSpecList n) { unimplementedVisitor("visit(OrderSpecList)"); return true; }
    public void endVisit(OrderSpecList n) { unimplementedVisitor("endVisit(OrderSpecList)"); }

    public boolean visit(OrderSpec n) { unimplementedVisitor("visit(OrderSpec)"); return true; }
    public void endVisit(OrderSpec n) { unimplementedVisitor("endVisit(OrderSpec)"); }

    public boolean visit(SortDirectionSegment n) { unimplementedVisitor("visit(SortDirectionSegment)"); return true; }
    public void endVisit(SortDirectionSegment n) { unimplementedVisitor("endVisit(SortDirectionSegment)"); }

    public boolean visit(EmptySortSegment n) { unimplementedVisitor("visit(EmptySortSegment)"); return true; }
    public void endVisit(EmptySortSegment n) { unimplementedVisitor("endVisit(EmptySortSegment)"); }

    public boolean visit(CollationSegmentOpt n) { unimplementedVisitor("visit(CollationSegmentOpt)"); return true; }
    public void endVisit(CollationSegmentOpt n) { unimplementedVisitor("endVisit(CollationSegmentOpt)"); }

    public boolean visit(OrderModifier n) { unimplementedVisitor("visit(OrderModifier)"); return true; }
    public void endVisit(OrderModifier n) { unimplementedVisitor("endVisit(OrderModifier)"); }

    public boolean visit(QuantifiedExprVarBinding n) { unimplementedVisitor("visit(QuantifiedExprVarBinding)"); return true; }
    public void endVisit(QuantifiedExprVarBinding n) { unimplementedVisitor("endVisit(QuantifiedExprVarBinding)"); }

    public boolean visit(QuantifiedExprVarBindingList n) { unimplementedVisitor("visit(QuantifiedExprVarBindingList)"); return true; }
    public void endVisit(QuantifiedExprVarBindingList n) { unimplementedVisitor("endVisit(QuantifiedExprVarBindingList)"); }

    public boolean visit(SomeQuantifiedExpr n) { unimplementedVisitor("visit(SomeQuantifiedExpr)"); return true; }
    public void endVisit(SomeQuantifiedExpr n) { unimplementedVisitor("endVisit(SomeQuantifiedExpr)"); }

    public boolean visit(EveryQuantifiedExpr n) { unimplementedVisitor("visit(EveryQuantifiedExpr)"); return true; }
    public void endVisit(EveryQuantifiedExpr n) { unimplementedVisitor("endVisit(EveryQuantifiedExpr)"); }

    public boolean visit(CaseClauseList n) { unimplementedVisitor("visit(CaseClauseList)"); return true; }
    public void endVisit(CaseClauseList n) { unimplementedVisitor("endVisit(CaseClauseList)"); }

    public boolean visit(TypeswitchExpr n) { unimplementedVisitor("visit(TypeswitchExpr)"); return true; }
    public void endVisit(TypeswitchExpr n) { unimplementedVisitor("endVisit(TypeswitchExpr)"); }

    public boolean visit(CaseClauseVarNameDeclOpt n) { unimplementedVisitor("visit(CaseClauseVarNameDeclOpt)"); return true; }
    public void endVisit(CaseClauseVarNameDeclOpt n) { unimplementedVisitor("endVisit(CaseClauseVarNameDeclOpt)"); }

    public boolean visit(CaseClause n) { unimplementedVisitor("visit(CaseClause)"); return true; }
    public void endVisit(CaseClause n) { unimplementedVisitor("endVisit(CaseClause)"); }

    public boolean visit(IfExpr n) { unimplementedVisitor("visit(IfExpr)"); return true; }
    public void endVisit(IfExpr n) { unimplementedVisitor("endVisit(IfExpr)"); }

    public boolean visit(OrExpr n) { unimplementedVisitor("visit(OrExpr)"); return true; }
    public void endVisit(OrExpr n) { unimplementedVisitor("endVisit(OrExpr)"); }

    public boolean visit(AndExpr n) { unimplementedVisitor("visit(AndExpr)"); return true; }
    public void endVisit(AndExpr n) { unimplementedVisitor("endVisit(AndExpr)"); }

    public boolean visit(ComparisonExpr n) { unimplementedVisitor("visit(ComparisonExpr)"); return true; }
    public void endVisit(ComparisonExpr n) { unimplementedVisitor("endVisit(ComparisonExpr)"); }

    public boolean visit(RangeExpr n) { unimplementedVisitor("visit(RangeExpr)"); return true; }
    public void endVisit(RangeExpr n) { unimplementedVisitor("endVisit(RangeExpr)"); }

    public boolean visit(AdditiveExpr n) { unimplementedVisitor("visit(AdditiveExpr)"); return true; }
    public void endVisit(AdditiveExpr n) { unimplementedVisitor("endVisit(AdditiveExpr)"); }

    public boolean visit(MultiplicativeExpr n) { unimplementedVisitor("visit(MultiplicativeExpr)"); return true; }
    public void endVisit(MultiplicativeExpr n) { unimplementedVisitor("endVisit(MultiplicativeExpr)"); }

    public boolean visit(UnionExpr n) { unimplementedVisitor("visit(UnionExpr)"); return true; }
    public void endVisit(UnionExpr n) { unimplementedVisitor("endVisit(UnionExpr)"); }

    public boolean visit(IntersectExceptExpr n) { unimplementedVisitor("visit(IntersectExceptExpr)"); return true; }
    public void endVisit(IntersectExceptExpr n) { unimplementedVisitor("endVisit(IntersectExceptExpr)"); }

    public boolean visit(InstanceofExpr n) { unimplementedVisitor("visit(InstanceofExpr)"); return true; }
    public void endVisit(InstanceofExpr n) { unimplementedVisitor("endVisit(InstanceofExpr)"); }

    public boolean visit(TreatExpr n) { unimplementedVisitor("visit(TreatExpr)"); return true; }
    public void endVisit(TreatExpr n) { unimplementedVisitor("endVisit(TreatExpr)"); }

    public boolean visit(CastableExpr n) { unimplementedVisitor("visit(CastableExpr)"); return true; }
    public void endVisit(CastableExpr n) { unimplementedVisitor("endVisit(CastableExpr)"); }

    public boolean visit(CastExpr n) { unimplementedVisitor("visit(CastExpr)"); return true; }
    public void endVisit(CastExpr n) { unimplementedVisitor("endVisit(CastExpr)"); }

    public boolean visit(UnaryExpr n) { unimplementedVisitor("visit(UnaryExpr)"); return true; }
    public void endVisit(UnaryExpr n) { unimplementedVisitor("endVisit(UnaryExpr)"); }

    public boolean visit(PlusOrMinus n) { unimplementedVisitor("visit(PlusOrMinus)"); return true; }
    public void endVisit(PlusOrMinus n) { unimplementedVisitor("endVisit(PlusOrMinus)"); }

    public boolean visit(PlusOrMinusList n) { unimplementedVisitor("visit(PlusOrMinusList)"); return true; }
    public void endVisit(PlusOrMinusList n) { unimplementedVisitor("endVisit(PlusOrMinusList)"); }

    public boolean visit(ValidateExpr n) { unimplementedVisitor("visit(ValidateExpr)"); return true; }
    public void endVisit(ValidateExpr n) { unimplementedVisitor("endVisit(ValidateExpr)"); }

    public boolean visit(PragmaList n) { unimplementedVisitor("visit(PragmaList)"); return true; }
    public void endVisit(PragmaList n) { unimplementedVisitor("endVisit(PragmaList)"); }

    public boolean visit(Pragma n) { unimplementedVisitor("visit(Pragma)"); return true; }
    public void endVisit(Pragma n) { unimplementedVisitor("endVisit(Pragma)"); }

    public boolean visit(PathExpr n) { unimplementedVisitor("visit(PathExpr)"); return true; }
    public void endVisit(PathExpr n) { unimplementedVisitor("endVisit(PathExpr)"); }

    public boolean visit(RelativePathExpr n) { unimplementedVisitor("visit(RelativePathExpr)"); return true; }
    public void endVisit(RelativePathExpr n) { unimplementedVisitor("endVisit(RelativePathExpr)"); }

    public boolean visit(AxisStepReverse n) { unimplementedVisitor("visit(AxisStepReverse)"); return true; }
    public void endVisit(AxisStepReverse n) { unimplementedVisitor("endVisit(AxisStepReverse)"); }

    public boolean visit(AxisStepForward n) { unimplementedVisitor("visit(AxisStepForward)"); return true; }
    public void endVisit(AxisStepForward n) { unimplementedVisitor("endVisit(AxisStepForward)"); }

    public boolean visit(ForwardStep n) { unimplementedVisitor("visit(ForwardStep)"); return true; }
    public void endVisit(ForwardStep n) { unimplementedVisitor("endVisit(ForwardStep)"); }

    public boolean visit(ForwardAxis n) { unimplementedVisitor("visit(ForwardAxis)"); return true; }
    public void endVisit(ForwardAxis n) { unimplementedVisitor("endVisit(ForwardAxis)"); }

    public boolean visit(AbbrevForwardStep n) { unimplementedVisitor("visit(AbbrevForwardStep)"); return true; }
    public void endVisit(AbbrevForwardStep n) { unimplementedVisitor("endVisit(AbbrevForwardStep)"); }

    public boolean visit(ReverseStep n) { unimplementedVisitor("visit(ReverseStep)"); return true; }
    public void endVisit(ReverseStep n) { unimplementedVisitor("endVisit(ReverseStep)"); }

    public boolean visit(ReverseAxis n) { unimplementedVisitor("visit(ReverseAxis)"); return true; }
    public void endVisit(ReverseAxis n) { unimplementedVisitor("endVisit(ReverseAxis)"); }

    public boolean visit(AbbrevReverseStep n) { unimplementedVisitor("visit(AbbrevReverseStep)"); return true; }
    public void endVisit(AbbrevReverseStep n) { unimplementedVisitor("endVisit(AbbrevReverseStep)"); }

    public boolean visit(FilterExpr n) { unimplementedVisitor("visit(FilterExpr)"); return true; }
    public void endVisit(FilterExpr n) { unimplementedVisitor("endVisit(FilterExpr)"); }

    public boolean visit(PredicateList n) { unimplementedVisitor("visit(PredicateList)"); return true; }
    public void endVisit(PredicateList n) { unimplementedVisitor("endVisit(PredicateList)"); }

    public boolean visit(Predicate n) { unimplementedVisitor("visit(Predicate)"); return true; }
    public void endVisit(Predicate n) { unimplementedVisitor("endVisit(Predicate)"); }

    public boolean visit(Literal n) { unimplementedVisitor("visit(Literal)"); return true; }
    public void endVisit(Literal n) { unimplementedVisitor("endVisit(Literal)"); }

    public boolean visit(VarRef n) { unimplementedVisitor("visit(VarRef)"); return true; }
    public void endVisit(VarRef n) { unimplementedVisitor("endVisit(VarRef)"); }

    public boolean visit(ParenthesizedExpr n) { unimplementedVisitor("visit(ParenthesizedExpr)"); return true; }
    public void endVisit(ParenthesizedExpr n) { unimplementedVisitor("endVisit(ParenthesizedExpr)"); }

    public boolean visit(ContextItemExpr n) { unimplementedVisitor("visit(ContextItemExpr)"); return true; }
    public void endVisit(ContextItemExpr n) { unimplementedVisitor("endVisit(ContextItemExpr)"); }

    public boolean visit(OrderedExpr n) { unimplementedVisitor("visit(OrderedExpr)"); return true; }
    public void endVisit(OrderedExpr n) { unimplementedVisitor("endVisit(OrderedExpr)"); }

    public boolean visit(UnorderedExpr n) { unimplementedVisitor("visit(UnorderedExpr)"); return true; }
    public void endVisit(UnorderedExpr n) { unimplementedVisitor("endVisit(UnorderedExpr)"); }

    public boolean visit(DirectConstructor n) { unimplementedVisitor("visit(DirectConstructor)"); return true; }
    public void endVisit(DirectConstructor n) { unimplementedVisitor("endVisit(DirectConstructor)"); }

    public boolean visit(DirElemContentList n) { unimplementedVisitor("visit(DirElemContentList)"); return true; }
    public void endVisit(DirElemContentList n) { unimplementedVisitor("endVisit(DirElemContentList)"); }

    public boolean visit(DirAttribute n) { unimplementedVisitor("visit(DirAttribute)"); return true; }
    public void endVisit(DirAttribute n) { unimplementedVisitor("endVisit(DirAttribute)"); }

    public boolean visit(DirAttributeList n) { unimplementedVisitor("visit(DirAttributeList)"); return true; }
    public void endVisit(DirAttributeList n) { unimplementedVisitor("endVisit(DirAttributeList)"); }

    public boolean visit(DirAttributeValueQuot n) { unimplementedVisitor("visit(DirAttributeValueQuot)"); return true; }
    public void endVisit(DirAttributeValueQuot n) { unimplementedVisitor("endVisit(DirAttributeValueQuot)"); }

    public boolean visit(DirAttributeValueApos n) { unimplementedVisitor("visit(DirAttributeValueApos)"); return true; }
    public void endVisit(DirAttributeValueApos n) { unimplementedVisitor("endVisit(DirAttributeValueApos)"); }

    public boolean visit(DirAttributeValueQuotList n) { unimplementedVisitor("visit(DirAttributeValueQuotList)"); return true; }
    public void endVisit(DirAttributeValueQuotList n) { unimplementedVisitor("endVisit(DirAttributeValueQuotList)"); }

    public boolean visit(DirAttributeValueAposList n) { unimplementedVisitor("visit(DirAttributeValueAposList)"); return true; }
    public void endVisit(DirAttributeValueAposList n) { unimplementedVisitor("endVisit(DirAttributeValueAposList)"); }

    public boolean visit(CommonContent n) { unimplementedVisitor("visit(CommonContent)"); return true; }
    public void endVisit(CommonContent n) { unimplementedVisitor("endVisit(CommonContent)"); }

    public boolean visit(DirPIConstructor n) { unimplementedVisitor("visit(DirPIConstructor)"); return true; }
    public void endVisit(DirPIConstructor n) { unimplementedVisitor("endVisit(DirPIConstructor)"); }

    public boolean visit(CompDocConstructor n) { unimplementedVisitor("visit(CompDocConstructor)"); return true; }
    public void endVisit(CompDocConstructor n) { unimplementedVisitor("endVisit(CompDocConstructor)"); }

    public boolean visit(CompElemConstructor n) { unimplementedVisitor("visit(CompElemConstructor)"); return true; }
    public void endVisit(CompElemConstructor n) { unimplementedVisitor("endVisit(CompElemConstructor)"); }

    public boolean visit(CompAttrConstructor n) { unimplementedVisitor("visit(CompAttrConstructor)"); return true; }
    public void endVisit(CompAttrConstructor n) { unimplementedVisitor("endVisit(CompAttrConstructor)"); }

    public boolean visit(CompTextConstructor n) { unimplementedVisitor("visit(CompTextConstructor)"); return true; }
    public void endVisit(CompTextConstructor n) { unimplementedVisitor("endVisit(CompTextConstructor)"); }

    public boolean visit(CompCommentConstructor n) { unimplementedVisitor("visit(CompCommentConstructor)"); return true; }
    public void endVisit(CompCommentConstructor n) { unimplementedVisitor("endVisit(CompCommentConstructor)"); }

    public boolean visit(CompPIConstructor n) { unimplementedVisitor("visit(CompPIConstructor)"); return true; }
    public void endVisit(CompPIConstructor n) { unimplementedVisitor("endVisit(CompPIConstructor)"); }

    public boolean visit(SingleType n) { unimplementedVisitor("visit(SingleType)"); return true; }
    public void endVisit(SingleType n) { unimplementedVisitor("endVisit(SingleType)"); }

    public boolean visit(TypeDeclaration n) { unimplementedVisitor("visit(TypeDeclaration)"); return true; }
    public void endVisit(TypeDeclaration n) { unimplementedVisitor("endVisit(TypeDeclaration)"); }

    public boolean visit(SequenceTypeEmptySeq n) { unimplementedVisitor("visit(SequenceTypeEmptySeq)"); return true; }
    public void endVisit(SequenceTypeEmptySeq n) { unimplementedVisitor("endVisit(SequenceTypeEmptySeq)"); }

    public boolean visit(SequenceTypeItemType n) { unimplementedVisitor("visit(SequenceTypeItemType)"); return true; }
    public void endVisit(SequenceTypeItemType n) { unimplementedVisitor("endVisit(SequenceTypeItemType)"); }

    public boolean visit(OccurrenceIndicator n) { unimplementedVisitor("visit(OccurrenceIndicator)"); return true; }
    public void endVisit(OccurrenceIndicator n) { unimplementedVisitor("endVisit(OccurrenceIndicator)"); }

    public boolean visit(ItemType n) { unimplementedVisitor("visit(ItemType)"); return true; }
    public void endVisit(ItemType n) { unimplementedVisitor("endVisit(ItemType)"); }

    public boolean visit(AnyKindTest n) { unimplementedVisitor("visit(AnyKindTest)"); return true; }
    public void endVisit(AnyKindTest n) { unimplementedVisitor("endVisit(AnyKindTest)"); }

    public boolean visit(DocumentTest n) { unimplementedVisitor("visit(DocumentTest)"); return true; }
    public void endVisit(DocumentTest n) { unimplementedVisitor("endVisit(DocumentTest)"); }

    public boolean visit(TextTest n) { unimplementedVisitor("visit(TextTest)"); return true; }
    public void endVisit(TextTest n) { unimplementedVisitor("endVisit(TextTest)"); }

    public boolean visit(CommentTest n) { unimplementedVisitor("visit(CommentTest)"); return true; }
    public void endVisit(CommentTest n) { unimplementedVisitor("endVisit(CommentTest)"); }

    public boolean visit(PITest n) { unimplementedVisitor("visit(PITest)"); return true; }
    public void endVisit(PITest n) { unimplementedVisitor("endVisit(PITest)"); }

    public boolean visit(AttributeTest n) { unimplementedVisitor("visit(AttributeTest)"); return true; }
    public void endVisit(AttributeTest n) { unimplementedVisitor("endVisit(AttributeTest)"); }

    public boolean visit(SchemaAttributeTest n) { unimplementedVisitor("visit(SchemaAttributeTest)"); return true; }
    public void endVisit(SchemaAttributeTest n) { unimplementedVisitor("endVisit(SchemaAttributeTest)"); }

    public boolean visit(ElementTest n) { unimplementedVisitor("visit(ElementTest)"); return true; }
    public void endVisit(ElementTest n) { unimplementedVisitor("endVisit(ElementTest)"); }

    public boolean visit(SchemaElementTest n) { unimplementedVisitor("visit(SchemaElementTest)"); return true; }
    public void endVisit(SchemaElementTest n) { unimplementedVisitor("endVisit(SchemaElementTest)"); }

    public boolean visit(ValidationMode0 n) { unimplementedVisitor("visit(ValidationMode0)"); return true; }
    public void endVisit(ValidationMode0 n) { unimplementedVisitor("endVisit(ValidationMode0)"); }

    public boolean visit(ValidationMode1 n) { unimplementedVisitor("visit(ValidationMode1)"); return true; }
    public void endVisit(ValidationMode1 n) { unimplementedVisitor("endVisit(ValidationMode1)"); }

    public boolean visit(ExtensionExpr0 n) { unimplementedVisitor("visit(ExtensionExpr0)"); return true; }
    public void endVisit(ExtensionExpr0 n) { unimplementedVisitor("endVisit(ExtensionExpr0)"); }

    public boolean visit(ExtensionExpr1 n) { unimplementedVisitor("visit(ExtensionExpr1)"); return true; }
    public void endVisit(ExtensionExpr1 n) { unimplementedVisitor("endVisit(ExtensionExpr1)"); }

    public boolean visit(Wildcard0 n) { unimplementedVisitor("visit(Wildcard0)"); return true; }
    public void endVisit(Wildcard0 n) { unimplementedVisitor("endVisit(Wildcard0)"); }

    public boolean visit(Wildcard1 n) { unimplementedVisitor("visit(Wildcard1)"); return true; }
    public void endVisit(Wildcard1 n) { unimplementedVisitor("endVisit(Wildcard1)"); }

    public boolean visit(Wildcard2 n) { unimplementedVisitor("visit(Wildcard2)"); return true; }
    public void endVisit(Wildcard2 n) { unimplementedVisitor("endVisit(Wildcard2)"); }

    public boolean visit(NumericLiteral0 n) { unimplementedVisitor("visit(NumericLiteral0)"); return true; }
    public void endVisit(NumericLiteral0 n) { unimplementedVisitor("endVisit(NumericLiteral0)"); }

    public boolean visit(NumericLiteral1 n) { unimplementedVisitor("visit(NumericLiteral1)"); return true; }
    public void endVisit(NumericLiteral1 n) { unimplementedVisitor("endVisit(NumericLiteral1)"); }

    public boolean visit(NumericLiteral2 n) { unimplementedVisitor("visit(NumericLiteral2)"); return true; }
    public void endVisit(NumericLiteral2 n) { unimplementedVisitor("endVisit(NumericLiteral2)"); }

    public boolean visit(FunctionCall0 n) { unimplementedVisitor("visit(FunctionCall0)"); return true; }
    public void endVisit(FunctionCall0 n) { unimplementedVisitor("endVisit(FunctionCall0)"); }

    public boolean visit(FunctionCall1 n) { unimplementedVisitor("visit(FunctionCall1)"); return true; }
    public void endVisit(FunctionCall1 n) { unimplementedVisitor("endVisit(FunctionCall1)"); }

    public boolean visit(DirElemConstructor0 n) { unimplementedVisitor("visit(DirElemConstructor0)"); return true; }
    public void endVisit(DirElemConstructor0 n) { unimplementedVisitor("endVisit(DirElemConstructor0)"); }

    public boolean visit(DirElemConstructor1 n) { unimplementedVisitor("visit(DirElemConstructor1)"); return true; }
    public void endVisit(DirElemConstructor1 n) { unimplementedVisitor("endVisit(DirElemConstructor1)"); }

    public boolean visit(DirElemContent0 n) { unimplementedVisitor("visit(DirElemContent0)"); return true; }
    public void endVisit(DirElemContent0 n) { unimplementedVisitor("endVisit(DirElemContent0)"); }

    public boolean visit(DirElemContent1 n) { unimplementedVisitor("visit(DirElemContent1)"); return true; }
    public void endVisit(DirElemContent1 n) { unimplementedVisitor("endVisit(DirElemContent1)"); }

    public boolean visit(QName0 n) { unimplementedVisitor("visit(QName0)"); return true; }
    public void endVisit(QName0 n) { unimplementedVisitor("endVisit(QName0)"); }

    public boolean visit(QName1 n) { unimplementedVisitor("visit(QName1)"); return true; }
    public void endVisit(QName1 n) { unimplementedVisitor("endVisit(QName1)"); }


    public boolean visit(ASTNode n)
    {
        if (n instanceof ASTNodeToken) return visit((ASTNodeToken) n);
        else if (n instanceof Module) return visit((Module) n);
        else if (n instanceof VersionDeclOpt) return visit((VersionDeclOpt) n);
        else if (n instanceof EncodingOpt) return visit((EncodingOpt) n);
        else if (n instanceof MainModule) return visit((MainModule) n);
        else if (n instanceof LibraryModule) return visit((LibraryModule) n);
        else if (n instanceof ModuleDecl) return visit((ModuleDecl) n);
        else if (n instanceof PrologEntry) return visit((PrologEntry) n);
        else if (n instanceof PrologEntryList) return visit((PrologEntryList) n);
        else if (n instanceof Setter) return visit((Setter) n);
        else if (n instanceof ImportBlk) return visit((ImportBlk) n);
        else if (n instanceof NamespaceDecl) return visit((NamespaceDecl) n);
        else if (n instanceof BoundarySpaceDecl) return visit((BoundarySpaceDecl) n);
        else if (n instanceof DefaultNamespaceDecl) return visit((DefaultNamespaceDecl) n);
        else if (n instanceof OptionDecl) return visit((OptionDecl) n);
        else if (n instanceof OrderingModeDecl) return visit((OrderingModeDecl) n);
        else if (n instanceof EmptyOrderDecl) return visit((EmptyOrderDecl) n);
        else if (n instanceof CopyNamespacesDecl) return visit((CopyNamespacesDecl) n);
        else if (n instanceof PreserveMode) return visit((PreserveMode) n);
        else if (n instanceof InheritMode) return visit((InheritMode) n);
        else if (n instanceof DefaultCollationDecl) return visit((DefaultCollationDecl) n);
        else if (n instanceof BaseURIDecl) return visit((BaseURIDecl) n);
        else if (n instanceof URILiteralList) return visit((URILiteralList) n);
        else if (n instanceof AtURILiteralSpecifierOpt) return visit((AtURILiteralSpecifierOpt) n);
        else if (n instanceof SchemaImport) return visit((SchemaImport) n);
        else if (n instanceof SchemaPrefixNamed) return visit((SchemaPrefixNamed) n);
        else if (n instanceof SchemaPrefixDefault) return visit((SchemaPrefixDefault) n);
        else if (n instanceof ModuleImport) return visit((ModuleImport) n);
        else if (n instanceof ModuleImportNS) return visit((ModuleImportNS) n);
        else if (n instanceof VarDecl) return visit((VarDecl) n);
        else if (n instanceof VarDeclExternal) return visit((VarDeclExternal) n);
        else if (n instanceof ConstructionDecl) return visit((ConstructionDecl) n);
        else if (n instanceof AsSequenceTypeOpt) return visit((AsSequenceTypeOpt) n);
        else if (n instanceof FunctionDecl) return visit((FunctionDecl) n);
        else if (n instanceof FunctionDeclExternal) return visit((FunctionDeclExternal) n);
        else if (n instanceof ParamList) return visit((ParamList) n);
        else if (n instanceof Param) return visit((Param) n);
        else if (n instanceof EnclosedExpr) return visit((EnclosedExpr) n);
        else if (n instanceof ExprSingleList) return visit((ExprSingleList) n);
        else if (n instanceof ForOrLetClauseList) return visit((ForOrLetClauseList) n);
        else if (n instanceof FLWORExpr) return visit((FLWORExpr) n);
        else if (n instanceof ForClauseVarBinding) return visit((ForClauseVarBinding) n);
        else if (n instanceof ForClauseVarBindingList) return visit((ForClauseVarBindingList) n);
        else if (n instanceof ForClause) return visit((ForClause) n);
        else if (n instanceof PositionalVar) return visit((PositionalVar) n);
        else if (n instanceof LetClauseVarBinding) return visit((LetClauseVarBinding) n);
        else if (n instanceof LetClauseVarBindingList) return visit((LetClauseVarBindingList) n);
        else if (n instanceof LetClause) return visit((LetClause) n);
        else if (n instanceof WhereClause) return visit((WhereClause) n);
        else if (n instanceof OrderByClause) return visit((OrderByClause) n);
        else if (n instanceof OrderSpecList) return visit((OrderSpecList) n);
        else if (n instanceof OrderSpec) return visit((OrderSpec) n);
        else if (n instanceof SortDirectionSegment) return visit((SortDirectionSegment) n);
        else if (n instanceof EmptySortSegment) return visit((EmptySortSegment) n);
        else if (n instanceof CollationSegmentOpt) return visit((CollationSegmentOpt) n);
        else if (n instanceof OrderModifier) return visit((OrderModifier) n);
        else if (n instanceof QuantifiedExprVarBinding) return visit((QuantifiedExprVarBinding) n);
        else if (n instanceof QuantifiedExprVarBindingList) return visit((QuantifiedExprVarBindingList) n);
        else if (n instanceof SomeQuantifiedExpr) return visit((SomeQuantifiedExpr) n);
        else if (n instanceof EveryQuantifiedExpr) return visit((EveryQuantifiedExpr) n);
        else if (n instanceof CaseClauseList) return visit((CaseClauseList) n);
        else if (n instanceof TypeswitchExpr) return visit((TypeswitchExpr) n);
        else if (n instanceof CaseClauseVarNameDeclOpt) return visit((CaseClauseVarNameDeclOpt) n);
        else if (n instanceof CaseClause) return visit((CaseClause) n);
        else if (n instanceof IfExpr) return visit((IfExpr) n);
        else if (n instanceof OrExpr) return visit((OrExpr) n);
        else if (n instanceof AndExpr) return visit((AndExpr) n);
        else if (n instanceof ComparisonExpr) return visit((ComparisonExpr) n);
        else if (n instanceof RangeExpr) return visit((RangeExpr) n);
        else if (n instanceof AdditiveExpr) return visit((AdditiveExpr) n);
        else if (n instanceof MultiplicativeExpr) return visit((MultiplicativeExpr) n);
        else if (n instanceof UnionExpr) return visit((UnionExpr) n);
        else if (n instanceof IntersectExceptExpr) return visit((IntersectExceptExpr) n);
        else if (n instanceof InstanceofExpr) return visit((InstanceofExpr) n);
        else if (n instanceof TreatExpr) return visit((TreatExpr) n);
        else if (n instanceof CastableExpr) return visit((CastableExpr) n);
        else if (n instanceof CastExpr) return visit((CastExpr) n);
        else if (n instanceof UnaryExpr) return visit((UnaryExpr) n);
        else if (n instanceof PlusOrMinus) return visit((PlusOrMinus) n);
        else if (n instanceof PlusOrMinusList) return visit((PlusOrMinusList) n);
        else if (n instanceof ValidateExpr) return visit((ValidateExpr) n);
        else if (n instanceof PragmaList) return visit((PragmaList) n);
        else if (n instanceof Pragma) return visit((Pragma) n);
        else if (n instanceof PathExpr) return visit((PathExpr) n);
        else if (n instanceof RelativePathExpr) return visit((RelativePathExpr) n);
        else if (n instanceof AxisStepReverse) return visit((AxisStepReverse) n);
        else if (n instanceof AxisStepForward) return visit((AxisStepForward) n);
        else if (n instanceof ForwardStep) return visit((ForwardStep) n);
        else if (n instanceof ForwardAxis) return visit((ForwardAxis) n);
        else if (n instanceof AbbrevForwardStep) return visit((AbbrevForwardStep) n);
        else if (n instanceof ReverseStep) return visit((ReverseStep) n);
        else if (n instanceof ReverseAxis) return visit((ReverseAxis) n);
        else if (n instanceof AbbrevReverseStep) return visit((AbbrevReverseStep) n);
        else if (n instanceof FilterExpr) return visit((FilterExpr) n);
        else if (n instanceof PredicateList) return visit((PredicateList) n);
        else if (n instanceof Predicate) return visit((Predicate) n);
        else if (n instanceof Literal) return visit((Literal) n);
        else if (n instanceof VarRef) return visit((VarRef) n);
        else if (n instanceof ParenthesizedExpr) return visit((ParenthesizedExpr) n);
        else if (n instanceof ContextItemExpr) return visit((ContextItemExpr) n);
        else if (n instanceof OrderedExpr) return visit((OrderedExpr) n);
        else if (n instanceof UnorderedExpr) return visit((UnorderedExpr) n);
        else if (n instanceof DirectConstructor) return visit((DirectConstructor) n);
        else if (n instanceof DirElemContentList) return visit((DirElemContentList) n);
        else if (n instanceof DirAttribute) return visit((DirAttribute) n);
        else if (n instanceof DirAttributeList) return visit((DirAttributeList) n);
        else if (n instanceof DirAttributeValueQuot) return visit((DirAttributeValueQuot) n);
        else if (n instanceof DirAttributeValueApos) return visit((DirAttributeValueApos) n);
        else if (n instanceof DirAttributeValueQuotList) return visit((DirAttributeValueQuotList) n);
        else if (n instanceof DirAttributeValueAposList) return visit((DirAttributeValueAposList) n);
        else if (n instanceof CommonContent) return visit((CommonContent) n);
        else if (n instanceof DirPIConstructor) return visit((DirPIConstructor) n);
        else if (n instanceof CompDocConstructor) return visit((CompDocConstructor) n);
        else if (n instanceof CompElemConstructor) return visit((CompElemConstructor) n);
        else if (n instanceof CompAttrConstructor) return visit((CompAttrConstructor) n);
        else if (n instanceof CompTextConstructor) return visit((CompTextConstructor) n);
        else if (n instanceof CompCommentConstructor) return visit((CompCommentConstructor) n);
        else if (n instanceof CompPIConstructor) return visit((CompPIConstructor) n);
        else if (n instanceof SingleType) return visit((SingleType) n);
        else if (n instanceof TypeDeclaration) return visit((TypeDeclaration) n);
        else if (n instanceof SequenceTypeEmptySeq) return visit((SequenceTypeEmptySeq) n);
        else if (n instanceof SequenceTypeItemType) return visit((SequenceTypeItemType) n);
        else if (n instanceof OccurrenceIndicator) return visit((OccurrenceIndicator) n);
        else if (n instanceof ItemType) return visit((ItemType) n);
        else if (n instanceof AnyKindTest) return visit((AnyKindTest) n);
        else if (n instanceof DocumentTest) return visit((DocumentTest) n);
        else if (n instanceof TextTest) return visit((TextTest) n);
        else if (n instanceof CommentTest) return visit((CommentTest) n);
        else if (n instanceof PITest) return visit((PITest) n);
        else if (n instanceof AttributeTest) return visit((AttributeTest) n);
        else if (n instanceof SchemaAttributeTest) return visit((SchemaAttributeTest) n);
        else if (n instanceof ElementTest) return visit((ElementTest) n);
        else if (n instanceof SchemaElementTest) return visit((SchemaElementTest) n);
        else if (n instanceof ValidationMode0) return visit((ValidationMode0) n);
        else if (n instanceof ValidationMode1) return visit((ValidationMode1) n);
        else if (n instanceof ExtensionExpr0) return visit((ExtensionExpr0) n);
        else if (n instanceof ExtensionExpr1) return visit((ExtensionExpr1) n);
        else if (n instanceof Wildcard0) return visit((Wildcard0) n);
        else if (n instanceof Wildcard1) return visit((Wildcard1) n);
        else if (n instanceof Wildcard2) return visit((Wildcard2) n);
        else if (n instanceof NumericLiteral0) return visit((NumericLiteral0) n);
        else if (n instanceof NumericLiteral1) return visit((NumericLiteral1) n);
        else if (n instanceof NumericLiteral2) return visit((NumericLiteral2) n);
        else if (n instanceof FunctionCall0) return visit((FunctionCall0) n);
        else if (n instanceof FunctionCall1) return visit((FunctionCall1) n);
        else if (n instanceof DirElemConstructor0) return visit((DirElemConstructor0) n);
        else if (n instanceof DirElemConstructor1) return visit((DirElemConstructor1) n);
        else if (n instanceof DirElemContent0) return visit((DirElemContent0) n);
        else if (n instanceof DirElemContent1) return visit((DirElemContent1) n);
        else if (n instanceof QName0) return visit((QName0) n);
        else if (n instanceof QName1) return visit((QName1) n);
        throw new UnsupportedOperationException("visit(" + n.getClass().toString() + ")");
    }
    public void endVisit(ASTNode n)
    {
        if (n instanceof ASTNodeToken) endVisit((ASTNodeToken) n);
        else if (n instanceof Module) endVisit((Module) n);
        else if (n instanceof VersionDeclOpt) endVisit((VersionDeclOpt) n);
        else if (n instanceof EncodingOpt) endVisit((EncodingOpt) n);
        else if (n instanceof MainModule) endVisit((MainModule) n);
        else if (n instanceof LibraryModule) endVisit((LibraryModule) n);
        else if (n instanceof ModuleDecl) endVisit((ModuleDecl) n);
        else if (n instanceof PrologEntry) endVisit((PrologEntry) n);
        else if (n instanceof PrologEntryList) endVisit((PrologEntryList) n);
        else if (n instanceof Setter) endVisit((Setter) n);
        else if (n instanceof ImportBlk) endVisit((ImportBlk) n);
        else if (n instanceof NamespaceDecl) endVisit((NamespaceDecl) n);
        else if (n instanceof BoundarySpaceDecl) endVisit((BoundarySpaceDecl) n);
        else if (n instanceof DefaultNamespaceDecl) endVisit((DefaultNamespaceDecl) n);
        else if (n instanceof OptionDecl) endVisit((OptionDecl) n);
        else if (n instanceof OrderingModeDecl) endVisit((OrderingModeDecl) n);
        else if (n instanceof EmptyOrderDecl) endVisit((EmptyOrderDecl) n);
        else if (n instanceof CopyNamespacesDecl) endVisit((CopyNamespacesDecl) n);
        else if (n instanceof PreserveMode) endVisit((PreserveMode) n);
        else if (n instanceof InheritMode) endVisit((InheritMode) n);
        else if (n instanceof DefaultCollationDecl) endVisit((DefaultCollationDecl) n);
        else if (n instanceof BaseURIDecl) endVisit((BaseURIDecl) n);
        else if (n instanceof URILiteralList) endVisit((URILiteralList) n);
        else if (n instanceof AtURILiteralSpecifierOpt) endVisit((AtURILiteralSpecifierOpt) n);
        else if (n instanceof SchemaImport) endVisit((SchemaImport) n);
        else if (n instanceof SchemaPrefixNamed) endVisit((SchemaPrefixNamed) n);
        else if (n instanceof SchemaPrefixDefault) endVisit((SchemaPrefixDefault) n);
        else if (n instanceof ModuleImport) endVisit((ModuleImport) n);
        else if (n instanceof ModuleImportNS) endVisit((ModuleImportNS) n);
        else if (n instanceof VarDecl) endVisit((VarDecl) n);
        else if (n instanceof VarDeclExternal) endVisit((VarDeclExternal) n);
        else if (n instanceof ConstructionDecl) endVisit((ConstructionDecl) n);
        else if (n instanceof AsSequenceTypeOpt) endVisit((AsSequenceTypeOpt) n);
        else if (n instanceof FunctionDecl) endVisit((FunctionDecl) n);
        else if (n instanceof FunctionDeclExternal) endVisit((FunctionDeclExternal) n);
        else if (n instanceof ParamList) endVisit((ParamList) n);
        else if (n instanceof Param) endVisit((Param) n);
        else if (n instanceof EnclosedExpr) endVisit((EnclosedExpr) n);
        else if (n instanceof ExprSingleList) endVisit((ExprSingleList) n);
        else if (n instanceof ForOrLetClauseList) endVisit((ForOrLetClauseList) n);
        else if (n instanceof FLWORExpr) endVisit((FLWORExpr) n);
        else if (n instanceof ForClauseVarBinding) endVisit((ForClauseVarBinding) n);
        else if (n instanceof ForClauseVarBindingList) endVisit((ForClauseVarBindingList) n);
        else if (n instanceof ForClause) endVisit((ForClause) n);
        else if (n instanceof PositionalVar) endVisit((PositionalVar) n);
        else if (n instanceof LetClauseVarBinding) endVisit((LetClauseVarBinding) n);
        else if (n instanceof LetClauseVarBindingList) endVisit((LetClauseVarBindingList) n);
        else if (n instanceof LetClause) endVisit((LetClause) n);
        else if (n instanceof WhereClause) endVisit((WhereClause) n);
        else if (n instanceof OrderByClause) endVisit((OrderByClause) n);
        else if (n instanceof OrderSpecList) endVisit((OrderSpecList) n);
        else if (n instanceof OrderSpec) endVisit((OrderSpec) n);
        else if (n instanceof SortDirectionSegment) endVisit((SortDirectionSegment) n);
        else if (n instanceof EmptySortSegment) endVisit((EmptySortSegment) n);
        else if (n instanceof CollationSegmentOpt) endVisit((CollationSegmentOpt) n);
        else if (n instanceof OrderModifier) endVisit((OrderModifier) n);
        else if (n instanceof QuantifiedExprVarBinding) endVisit((QuantifiedExprVarBinding) n);
        else if (n instanceof QuantifiedExprVarBindingList) endVisit((QuantifiedExprVarBindingList) n);
        else if (n instanceof SomeQuantifiedExpr) endVisit((SomeQuantifiedExpr) n);
        else if (n instanceof EveryQuantifiedExpr) endVisit((EveryQuantifiedExpr) n);
        else if (n instanceof CaseClauseList) endVisit((CaseClauseList) n);
        else if (n instanceof TypeswitchExpr) endVisit((TypeswitchExpr) n);
        else if (n instanceof CaseClauseVarNameDeclOpt) endVisit((CaseClauseVarNameDeclOpt) n);
        else if (n instanceof CaseClause) endVisit((CaseClause) n);
        else if (n instanceof IfExpr) endVisit((IfExpr) n);
        else if (n instanceof OrExpr) endVisit((OrExpr) n);
        else if (n instanceof AndExpr) endVisit((AndExpr) n);
        else if (n instanceof ComparisonExpr) endVisit((ComparisonExpr) n);
        else if (n instanceof RangeExpr) endVisit((RangeExpr) n);
        else if (n instanceof AdditiveExpr) endVisit((AdditiveExpr) n);
        else if (n instanceof MultiplicativeExpr) endVisit((MultiplicativeExpr) n);
        else if (n instanceof UnionExpr) endVisit((UnionExpr) n);
        else if (n instanceof IntersectExceptExpr) endVisit((IntersectExceptExpr) n);
        else if (n instanceof InstanceofExpr) endVisit((InstanceofExpr) n);
        else if (n instanceof TreatExpr) endVisit((TreatExpr) n);
        else if (n instanceof CastableExpr) endVisit((CastableExpr) n);
        else if (n instanceof CastExpr) endVisit((CastExpr) n);
        else if (n instanceof UnaryExpr) endVisit((UnaryExpr) n);
        else if (n instanceof PlusOrMinus) endVisit((PlusOrMinus) n);
        else if (n instanceof PlusOrMinusList) endVisit((PlusOrMinusList) n);
        else if (n instanceof ValidateExpr) endVisit((ValidateExpr) n);
        else if (n instanceof PragmaList) endVisit((PragmaList) n);
        else if (n instanceof Pragma) endVisit((Pragma) n);
        else if (n instanceof PathExpr) endVisit((PathExpr) n);
        else if (n instanceof RelativePathExpr) endVisit((RelativePathExpr) n);
        else if (n instanceof AxisStepReverse) endVisit((AxisStepReverse) n);
        else if (n instanceof AxisStepForward) endVisit((AxisStepForward) n);
        else if (n instanceof ForwardStep) endVisit((ForwardStep) n);
        else if (n instanceof ForwardAxis) endVisit((ForwardAxis) n);
        else if (n instanceof AbbrevForwardStep) endVisit((AbbrevForwardStep) n);
        else if (n instanceof ReverseStep) endVisit((ReverseStep) n);
        else if (n instanceof ReverseAxis) endVisit((ReverseAxis) n);
        else if (n instanceof AbbrevReverseStep) endVisit((AbbrevReverseStep) n);
        else if (n instanceof FilterExpr) endVisit((FilterExpr) n);
        else if (n instanceof PredicateList) endVisit((PredicateList) n);
        else if (n instanceof Predicate) endVisit((Predicate) n);
        else if (n instanceof Literal) endVisit((Literal) n);
        else if (n instanceof VarRef) endVisit((VarRef) n);
        else if (n instanceof ParenthesizedExpr) endVisit((ParenthesizedExpr) n);
        else if (n instanceof ContextItemExpr) endVisit((ContextItemExpr) n);
        else if (n instanceof OrderedExpr) endVisit((OrderedExpr) n);
        else if (n instanceof UnorderedExpr) endVisit((UnorderedExpr) n);
        else if (n instanceof DirectConstructor) endVisit((DirectConstructor) n);
        else if (n instanceof DirElemContentList) endVisit((DirElemContentList) n);
        else if (n instanceof DirAttribute) endVisit((DirAttribute) n);
        else if (n instanceof DirAttributeList) endVisit((DirAttributeList) n);
        else if (n instanceof DirAttributeValueQuot) endVisit((DirAttributeValueQuot) n);
        else if (n instanceof DirAttributeValueApos) endVisit((DirAttributeValueApos) n);
        else if (n instanceof DirAttributeValueQuotList) endVisit((DirAttributeValueQuotList) n);
        else if (n instanceof DirAttributeValueAposList) endVisit((DirAttributeValueAposList) n);
        else if (n instanceof CommonContent) endVisit((CommonContent) n);
        else if (n instanceof DirPIConstructor) endVisit((DirPIConstructor) n);
        else if (n instanceof CompDocConstructor) endVisit((CompDocConstructor) n);
        else if (n instanceof CompElemConstructor) endVisit((CompElemConstructor) n);
        else if (n instanceof CompAttrConstructor) endVisit((CompAttrConstructor) n);
        else if (n instanceof CompTextConstructor) endVisit((CompTextConstructor) n);
        else if (n instanceof CompCommentConstructor) endVisit((CompCommentConstructor) n);
        else if (n instanceof CompPIConstructor) endVisit((CompPIConstructor) n);
        else if (n instanceof SingleType) endVisit((SingleType) n);
        else if (n instanceof TypeDeclaration) endVisit((TypeDeclaration) n);
        else if (n instanceof SequenceTypeEmptySeq) endVisit((SequenceTypeEmptySeq) n);
        else if (n instanceof SequenceTypeItemType) endVisit((SequenceTypeItemType) n);
        else if (n instanceof OccurrenceIndicator) endVisit((OccurrenceIndicator) n);
        else if (n instanceof ItemType) endVisit((ItemType) n);
        else if (n instanceof AnyKindTest) endVisit((AnyKindTest) n);
        else if (n instanceof DocumentTest) endVisit((DocumentTest) n);
        else if (n instanceof TextTest) endVisit((TextTest) n);
        else if (n instanceof CommentTest) endVisit((CommentTest) n);
        else if (n instanceof PITest) endVisit((PITest) n);
        else if (n instanceof AttributeTest) endVisit((AttributeTest) n);
        else if (n instanceof SchemaAttributeTest) endVisit((SchemaAttributeTest) n);
        else if (n instanceof ElementTest) endVisit((ElementTest) n);
        else if (n instanceof SchemaElementTest) endVisit((SchemaElementTest) n);
        else if (n instanceof ValidationMode0) endVisit((ValidationMode0) n);
        else if (n instanceof ValidationMode1) endVisit((ValidationMode1) n);
        else if (n instanceof ExtensionExpr0) endVisit((ExtensionExpr0) n);
        else if (n instanceof ExtensionExpr1) endVisit((ExtensionExpr1) n);
        else if (n instanceof Wildcard0) endVisit((Wildcard0) n);
        else if (n instanceof Wildcard1) endVisit((Wildcard1) n);
        else if (n instanceof Wildcard2) endVisit((Wildcard2) n);
        else if (n instanceof NumericLiteral0) endVisit((NumericLiteral0) n);
        else if (n instanceof NumericLiteral1) endVisit((NumericLiteral1) n);
        else if (n instanceof NumericLiteral2) endVisit((NumericLiteral2) n);
        else if (n instanceof FunctionCall0) endVisit((FunctionCall0) n);
        else if (n instanceof FunctionCall1) endVisit((FunctionCall1) n);
        else if (n instanceof DirElemConstructor0) endVisit((DirElemConstructor0) n);
        else if (n instanceof DirElemConstructor1) endVisit((DirElemConstructor1) n);
        else if (n instanceof DirElemContent0) endVisit((DirElemContent0) n);
        else if (n instanceof DirElemContent1) endVisit((DirElemContent1) n);
        else if (n instanceof QName0) endVisit((QName0) n);
        else if (n instanceof QName1) endVisit((QName1) n);
        throw new UnsupportedOperationException("visit(" + n.getClass().toString() + ")");
    }
}

