package org.xebia.tst.engine;

import org.xebia.tst.bo.Coordonnees;
import org.xebia.tst.bo.Mower;
import org.xebia.tst.loaders.rules.Rules;

/**
 * 
 * 
 * @author elmehdi
 *
 */
public interface MowerMovingEngine {

	/**
	 * 
	 * 
	 * @param rules
	 * @param mower
	 * @param commandes
	 * @param borderCoord
	 * @return
	 */
	 Mower startMovingMower(Rules rules ,Mower mower , char[] commandes,Coordonnees borderCoord);
}
