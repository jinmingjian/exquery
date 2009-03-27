package com.google.code.exquery.ide.tokenColorer;

import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.services.ITokenColorer;
import org.eclipse.imp.services.base.TokenColorerBase;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

import com.google.code.exquery.ide.XQueryParseController;
import com.google.code.exquery.parser.XQueryParsersym;

import lpg.runtime.IToken;

public class XQueryTokenColorer extends TokenColorerBase implements
		XQueryParsersym, ITokenColorer {

	TextAttribute commentAttribute, keywordAttribute, stringAttribute,
			numberAttribute, doubleAttribute, ncnameAttribute;

	public TextAttribute getColoring(IParseController controller, Object o) {
		if (o == null)
			return null;
		IToken token = (IToken) o;
		if (token.getKind() == TK_EOF_TOKEN)
			return null;

		switch (token.getKind()) {
		// START_HERE
		case TK_NCName:
			return ncnameAttribute;
		case TK_DoubleLiteral:
			return doubleAttribute;
			//          case TK_StringLiteral:
			//               return stringAttribute;
		case TK_Comment:
			return commentAttribute;
		default:
			if (((XQueryParseController) controller).isKeyword(token.getKind()))
				return keywordAttribute;
			return super.getColoring(controller, token);
		}
	}

	public XQueryTokenColorer() {
		super();
		// TODO:  Define text attributes for the various
		// token types that will have their text colored
		//
		// NOTE:  Colors (i.e., instances of org.eclipse.swt.graphics.Color) are system resources
		// and are limited in number.  THEREFORE, it is good practice to reuse existing system Colors
		// or to allocate a fixed set of new Colors and reuse those.  If new Colors are instantiated
		// beyond the bounds of your system capacity then your Eclipse invocation may cease to function
		// properly or at all.
		Display display = Display.getDefault();
		commentAttribute = new TextAttribute(display
				.getSystemColor(SWT.COLOR_DARK_RED), null, SWT.ITALIC);
		stringAttribute = new TextAttribute(display
				.getSystemColor(SWT.COLOR_DARK_BLUE), null, SWT.BOLD);
		ncnameAttribute = new TextAttribute(display
				.getSystemColor(SWT.COLOR_BLACK), null, SWT.NORMAL);
		doubleAttribute = new TextAttribute(display
				.getSystemColor(SWT.COLOR_DARK_GREEN), null, SWT.BOLD);
		numberAttribute = new TextAttribute(display
				.getSystemColor(SWT.COLOR_DARK_YELLOW), null, SWT.BOLD);
		keywordAttribute = new TextAttribute(display
				.getSystemColor(SWT.COLOR_DARK_MAGENTA), null, SWT.BOLD);
	}

	public IRegion calculateDamageExtent(IRegion seed) {
		return seed;
	}

}
