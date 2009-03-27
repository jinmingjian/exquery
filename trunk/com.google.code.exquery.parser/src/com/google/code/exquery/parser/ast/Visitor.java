
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

public interface Visitor extends IAstVisitor
{
    boolean visit(ASTNode n);
    void endVisit(ASTNode n);

    boolean visit(ASTNodeToken n);
    void endVisit(ASTNodeToken n);

    boolean visit(Module n);
    void endVisit(Module n);

    boolean visit(VersionDeclOpt n);
    void endVisit(VersionDeclOpt n);

    boolean visit(EncodingOpt n);
    void endVisit(EncodingOpt n);

    boolean visit(MainModule n);
    void endVisit(MainModule n);

    boolean visit(LibraryModule n);
    void endVisit(LibraryModule n);

    boolean visit(ModuleDecl n);
    void endVisit(ModuleDecl n);

    boolean visit(PrologEntry n);
    void endVisit(PrologEntry n);

    boolean visit(PrologEntryList n);
    void endVisit(PrologEntryList n);

    boolean visit(Setter n);
    void endVisit(Setter n);

    boolean visit(ImportBlk n);
    void endVisit(ImportBlk n);

    boolean visit(NamespaceDecl n);
    void endVisit(NamespaceDecl n);

    boolean visit(BoundarySpaceDecl n);
    void endVisit(BoundarySpaceDecl n);

    boolean visit(DefaultNamespaceDecl n);
    void endVisit(DefaultNamespaceDecl n);

    boolean visit(OptionDecl n);
    void endVisit(OptionDecl n);

    boolean visit(OrderingModeDecl n);
    void endVisit(OrderingModeDecl n);

    boolean visit(EmptyOrderDecl n);
    void endVisit(EmptyOrderDecl n);

    boolean visit(CopyNamespacesDecl n);
    void endVisit(CopyNamespacesDecl n);

    boolean visit(PreserveMode n);
    void endVisit(PreserveMode n);

    boolean visit(InheritMode n);
    void endVisit(InheritMode n);

    boolean visit(DefaultCollationDecl n);
    void endVisit(DefaultCollationDecl n);

    boolean visit(BaseURIDecl n);
    void endVisit(BaseURIDecl n);

    boolean visit(URILiteralList n);
    void endVisit(URILiteralList n);

    boolean visit(AtURILiteralSpecifierOpt n);
    void endVisit(AtURILiteralSpecifierOpt n);

    boolean visit(SchemaImport n);
    void endVisit(SchemaImport n);

    boolean visit(SchemaPrefixNamed n);
    void endVisit(SchemaPrefixNamed n);

    boolean visit(SchemaPrefixDefault n);
    void endVisit(SchemaPrefixDefault n);

    boolean visit(ModuleImport n);
    void endVisit(ModuleImport n);

    boolean visit(ModuleImportNS n);
    void endVisit(ModuleImportNS n);

    boolean visit(VarDecl n);
    void endVisit(VarDecl n);

    boolean visit(VarDeclExternal n);
    void endVisit(VarDeclExternal n);

    boolean visit(ConstructionDecl n);
    void endVisit(ConstructionDecl n);

    boolean visit(AsSequenceTypeOpt n);
    void endVisit(AsSequenceTypeOpt n);

    boolean visit(FunctionDecl n);
    void endVisit(FunctionDecl n);

    boolean visit(FunctionDeclExternal n);
    void endVisit(FunctionDeclExternal n);

    boolean visit(ParamList n);
    void endVisit(ParamList n);

    boolean visit(Param n);
    void endVisit(Param n);

    boolean visit(EnclosedExpr n);
    void endVisit(EnclosedExpr n);

    boolean visit(ExprSingleList n);
    void endVisit(ExprSingleList n);

    boolean visit(ForOrLetClauseList n);
    void endVisit(ForOrLetClauseList n);

    boolean visit(FLWORExpr n);
    void endVisit(FLWORExpr n);

    boolean visit(ForClauseVarBinding n);
    void endVisit(ForClauseVarBinding n);

    boolean visit(ForClauseVarBindingList n);
    void endVisit(ForClauseVarBindingList n);

    boolean visit(ForClause n);
    void endVisit(ForClause n);

    boolean visit(PositionalVar n);
    void endVisit(PositionalVar n);

    boolean visit(LetClauseVarBinding n);
    void endVisit(LetClauseVarBinding n);

    boolean visit(LetClauseVarBindingList n);
    void endVisit(LetClauseVarBindingList n);

    boolean visit(LetClause n);
    void endVisit(LetClause n);

    boolean visit(WhereClause n);
    void endVisit(WhereClause n);

    boolean visit(OrderByClause n);
    void endVisit(OrderByClause n);

    boolean visit(OrderSpecList n);
    void endVisit(OrderSpecList n);

    boolean visit(OrderSpec n);
    void endVisit(OrderSpec n);

    boolean visit(SortDirectionSegment n);
    void endVisit(SortDirectionSegment n);

    boolean visit(EmptySortSegment n);
    void endVisit(EmptySortSegment n);

    boolean visit(CollationSegmentOpt n);
    void endVisit(CollationSegmentOpt n);

    boolean visit(OrderModifier n);
    void endVisit(OrderModifier n);

    boolean visit(QuantifiedExprVarBinding n);
    void endVisit(QuantifiedExprVarBinding n);

    boolean visit(QuantifiedExprVarBindingList n);
    void endVisit(QuantifiedExprVarBindingList n);

    boolean visit(SomeQuantifiedExpr n);
    void endVisit(SomeQuantifiedExpr n);

    boolean visit(EveryQuantifiedExpr n);
    void endVisit(EveryQuantifiedExpr n);

    boolean visit(CaseClauseList n);
    void endVisit(CaseClauseList n);

    boolean visit(TypeswitchExpr n);
    void endVisit(TypeswitchExpr n);

    boolean visit(CaseClauseVarNameDeclOpt n);
    void endVisit(CaseClauseVarNameDeclOpt n);

    boolean visit(CaseClause n);
    void endVisit(CaseClause n);

    boolean visit(IfExpr n);
    void endVisit(IfExpr n);

    boolean visit(OrExpr n);
    void endVisit(OrExpr n);

    boolean visit(AndExpr n);
    void endVisit(AndExpr n);

    boolean visit(ComparisonExpr n);
    void endVisit(ComparisonExpr n);

    boolean visit(RangeExpr n);
    void endVisit(RangeExpr n);

    boolean visit(AdditiveExpr n);
    void endVisit(AdditiveExpr n);

    boolean visit(MultiplicativeExpr n);
    void endVisit(MultiplicativeExpr n);

    boolean visit(UnionExpr n);
    void endVisit(UnionExpr n);

    boolean visit(IntersectExceptExpr n);
    void endVisit(IntersectExceptExpr n);

    boolean visit(InstanceofExpr n);
    void endVisit(InstanceofExpr n);

    boolean visit(TreatExpr n);
    void endVisit(TreatExpr n);

    boolean visit(CastableExpr n);
    void endVisit(CastableExpr n);

    boolean visit(CastExpr n);
    void endVisit(CastExpr n);

    boolean visit(UnaryExpr n);
    void endVisit(UnaryExpr n);

    boolean visit(PlusOrMinus n);
    void endVisit(PlusOrMinus n);

    boolean visit(PlusOrMinusList n);
    void endVisit(PlusOrMinusList n);

    boolean visit(ValidateExpr n);
    void endVisit(ValidateExpr n);

    boolean visit(PragmaList n);
    void endVisit(PragmaList n);

    boolean visit(Pragma n);
    void endVisit(Pragma n);

    boolean visit(PathExpr n);
    void endVisit(PathExpr n);

    boolean visit(RelativePathExpr n);
    void endVisit(RelativePathExpr n);

    boolean visit(AxisStepReverse n);
    void endVisit(AxisStepReverse n);

    boolean visit(AxisStepForward n);
    void endVisit(AxisStepForward n);

    boolean visit(ForwardStep n);
    void endVisit(ForwardStep n);

    boolean visit(ForwardAxis n);
    void endVisit(ForwardAxis n);

    boolean visit(AbbrevForwardStep n);
    void endVisit(AbbrevForwardStep n);

    boolean visit(ReverseStep n);
    void endVisit(ReverseStep n);

    boolean visit(ReverseAxis n);
    void endVisit(ReverseAxis n);

    boolean visit(AbbrevReverseStep n);
    void endVisit(AbbrevReverseStep n);

    boolean visit(FilterExpr n);
    void endVisit(FilterExpr n);

    boolean visit(PredicateList n);
    void endVisit(PredicateList n);

    boolean visit(Predicate n);
    void endVisit(Predicate n);

    boolean visit(Literal n);
    void endVisit(Literal n);

    boolean visit(VarRef n);
    void endVisit(VarRef n);

    boolean visit(ParenthesizedExpr n);
    void endVisit(ParenthesizedExpr n);

    boolean visit(ContextItemExpr n);
    void endVisit(ContextItemExpr n);

    boolean visit(OrderedExpr n);
    void endVisit(OrderedExpr n);

    boolean visit(UnorderedExpr n);
    void endVisit(UnorderedExpr n);

    boolean visit(DirectConstructor n);
    void endVisit(DirectConstructor n);

    boolean visit(DirElemContentList n);
    void endVisit(DirElemContentList n);

    boolean visit(DirAttribute n);
    void endVisit(DirAttribute n);

    boolean visit(DirAttributeList n);
    void endVisit(DirAttributeList n);

    boolean visit(DirAttributeValueQuot n);
    void endVisit(DirAttributeValueQuot n);

    boolean visit(DirAttributeValueApos n);
    void endVisit(DirAttributeValueApos n);

    boolean visit(DirAttributeValueQuotList n);
    void endVisit(DirAttributeValueQuotList n);

    boolean visit(DirAttributeValueAposList n);
    void endVisit(DirAttributeValueAposList n);

    boolean visit(CommonContent n);
    void endVisit(CommonContent n);

    boolean visit(DirPIConstructor n);
    void endVisit(DirPIConstructor n);

    boolean visit(CompDocConstructor n);
    void endVisit(CompDocConstructor n);

    boolean visit(CompElemConstructor n);
    void endVisit(CompElemConstructor n);

    boolean visit(CompAttrConstructor n);
    void endVisit(CompAttrConstructor n);

    boolean visit(CompTextConstructor n);
    void endVisit(CompTextConstructor n);

    boolean visit(CompCommentConstructor n);
    void endVisit(CompCommentConstructor n);

    boolean visit(CompPIConstructor n);
    void endVisit(CompPIConstructor n);

    boolean visit(SingleType n);
    void endVisit(SingleType n);

    boolean visit(TypeDeclaration n);
    void endVisit(TypeDeclaration n);

    boolean visit(SequenceTypeEmptySeq n);
    void endVisit(SequenceTypeEmptySeq n);

    boolean visit(SequenceTypeItemType n);
    void endVisit(SequenceTypeItemType n);

    boolean visit(OccurrenceIndicator n);
    void endVisit(OccurrenceIndicator n);

    boolean visit(ItemType n);
    void endVisit(ItemType n);

    boolean visit(AnyKindTest n);
    void endVisit(AnyKindTest n);

    boolean visit(DocumentTest n);
    void endVisit(DocumentTest n);

    boolean visit(TextTest n);
    void endVisit(TextTest n);

    boolean visit(CommentTest n);
    void endVisit(CommentTest n);

    boolean visit(PITest n);
    void endVisit(PITest n);

    boolean visit(AttributeTest n);
    void endVisit(AttributeTest n);

    boolean visit(SchemaAttributeTest n);
    void endVisit(SchemaAttributeTest n);

    boolean visit(ElementTest n);
    void endVisit(ElementTest n);

    boolean visit(SchemaElementTest n);
    void endVisit(SchemaElementTest n);

    boolean visit(ValidationMode0 n);
    void endVisit(ValidationMode0 n);

    boolean visit(ValidationMode1 n);
    void endVisit(ValidationMode1 n);

    boolean visit(ExtensionExpr0 n);
    void endVisit(ExtensionExpr0 n);

    boolean visit(ExtensionExpr1 n);
    void endVisit(ExtensionExpr1 n);

    boolean visit(Wildcard0 n);
    void endVisit(Wildcard0 n);

    boolean visit(Wildcard1 n);
    void endVisit(Wildcard1 n);

    boolean visit(Wildcard2 n);
    void endVisit(Wildcard2 n);

    boolean visit(NumericLiteral0 n);
    void endVisit(NumericLiteral0 n);

    boolean visit(NumericLiteral1 n);
    void endVisit(NumericLiteral1 n);

    boolean visit(NumericLiteral2 n);
    void endVisit(NumericLiteral2 n);

    boolean visit(FunctionCall0 n);
    void endVisit(FunctionCall0 n);

    boolean visit(FunctionCall1 n);
    void endVisit(FunctionCall1 n);

    boolean visit(DirElemConstructor0 n);
    void endVisit(DirElemConstructor0 n);

    boolean visit(DirElemConstructor1 n);
    void endVisit(DirElemConstructor1 n);

    boolean visit(DirElemContent0 n);
    void endVisit(DirElemContent0 n);

    boolean visit(DirElemContent1 n);
    void endVisit(DirElemContent1 n);

    boolean visit(QName0 n);
    void endVisit(QName0 n);

    boolean visit(QName1 n);
    void endVisit(QName1 n);

}


