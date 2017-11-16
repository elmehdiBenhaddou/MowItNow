package org.xebia.tst.loaders.rules;


/**
 * 
 * interface a implementer selon la facon avec laquelle on veut charger notre base de regles
 * au demarrage de l'appli
 * 
 * @author elmehdi
 *
 */
public interface RulesLoader {

	/**
	 * 
	 * @return
	 */
	Rules loadRules();
	
}
