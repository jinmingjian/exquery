package com.google.code.exquery.ide;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.imp.preferences.PreferencesService;
import org.eclipse.imp.runtime.PluginBase;
import org.osgi.framework.BundleContext;

public class XQueryPlugin extends PluginBase {

	public static final String kPluginID = "com.google.code.exquery.ide";
	public static final String kLanguageName = "XQuery";

	/**
	 * The unique instance of this plugin class
	 */
	protected static XQueryPlugin sPlugin;

	public static XQueryPlugin getInstance() {
		if (sPlugin == null)
			new XQueryPlugin();
		return sPlugin;
	}

	public XQueryPlugin() {
		super();
		sPlugin = this;
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
	}

	@Override
	public String getID() {
		return kPluginID;
	}

	@Override
	public String getLanguageID() {
		return kLanguageName;
	}
}
