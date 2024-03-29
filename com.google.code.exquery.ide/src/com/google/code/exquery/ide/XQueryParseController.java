package com.google.code.exquery.ide;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.imp.services.ILanguageSyntaxProperties;
import org.eclipse.imp.model.ISourceProject;
import org.eclipse.imp.parser.ILexer;
import org.eclipse.imp.parser.IMessageHandler;
import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.parser.IParser;
import org.eclipse.imp.parser.ISourcePositionLocator;
import org.eclipse.imp.parser.MessageHandlerAdapter;
import org.eclipse.imp.parser.SimpleLPGParseController;

import com.google.code.exquery.ide.XQueryPlugin;
import com.google.code.exquery.parser.XQueryLexer;
import com.google.code.exquery.parser.XQueryParser;
import com.google.code.exquery.parser.ast.ASTNode;
 

/**
 * The XQuery implementation of the IParseController IMP interface.
 */
public class XQueryParseController extends SimpleLPGParseController implements
		IParseController {
	private XQueryParser parser;

	private XQueryLexer lexer;

	public XQueryParseController() {
		super(XQueryPlugin.kLanguageName);
	}

	/**
	 * @param filePath
	 *            Absolute path of file
	 * @param project
	 *            Project that contains the file
	 * @param handler
	 *            A message handler to receive error messages (or any others)
	 *            from the parser
	 */
	public void initialize(IPath filePath, ISourceProject project,
			IMessageHandler handler) {
		super.initialize(filePath, project, handler);
	}

	public IParser getParser() {
		return parser;
	}

	public ILexer getLexer() {
		return lexer;
	}

	public ISourcePositionLocator getNodeLocator() {
		return new XQueryASTNodeLocator();
	}

	public ILanguageSyntaxProperties getSyntaxProperties() {
		return null;
	}

	/**
	 * setFilePath() should be called before calling this method.
	 */
	public Object parse(String contents, boolean scanOnly,
			IProgressMonitor monitor) {
		PMMonitor my_monitor = new PMMonitor(monitor);
		char[] contentsArray = contents.toCharArray();

		if (lexer == null) {
			lexer = new XQueryLexer();
		}
		lexer.reset(contentsArray, fFilePath.toPortableString());

		if (parser == null) {
			parser = new XQueryParser(lexer.getLexStream());
		}
		parser.reset(lexer.getLexStream());
		parser.getParseStream().setMessageHandler(
				new MessageHandlerAdapter(handler));

		lexer.lexer(my_monitor, parser.getParseStream()); // Lex the stream to
		// produce the token
		// stream
		if (my_monitor.isCancelled())
			return fCurrentAst; // TODO fCurrentAst might (probably will) be
		// inconsistent wrt the lex stream now

		fCurrentAst = parser.parser(my_monitor, 0);

		// SMS 18 Dec 2007:  functionDeclarationList is a LEG-specific type
		// not suitable for other languages; try replacing with ASTNode
		//		if (fCurrentAst instanceof functionDeclarationList) {
		//			parser.resolve((ASTNode) fCurrentAst);
		//		}
//		if (fCurrentAst instanceof ASTNode) {
//			parser.resolve((ASTNode) fCurrentAst);
//		}

		cacheKeywordsOnce();

		Object result = fCurrentAst;
		return result;
	}
}
