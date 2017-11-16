package org.xebia.tst.engine.impl;

import org.springframework.stereotype.Component;
import org.xebia.tst.bo.Coordonnees;
import org.xebia.tst.bo.Mower;
import org.xebia.tst.engine.MowerMovingEngine;
import org.xebia.tst.loaders.rules.Rules;
/**
 * implementation du moteur 
 * 
 * 
 * @author elmehdi
 *
 */
@Component
public class MowerMovingEngineImpl implements MowerMovingEngine {

	@Override
	public Mower startMovingMower(Rules rules,Mower mower,char[] commandes,Coordonnees limits) {
		for(int i = 0 ; i< commandes.length ; i++){
			if(mower.getCoordonnees().getX() > limits.getX() || mower.getCoordonnees().getY() > limits.getY() 
					 || mower.getCoordonnees().getX() < 0 || mower.getCoordonnees().getY() < 0){
			   mower.setStoped(true);
			   return mower;
			}	
			if(commandes[i] == 'A'){
				mower.moveMower();
			}else{
				mower.setOrientation(rules.getResult(mower.getOrientation().toString()+commandes[i]));
			}
		}
		return mower;
	}

}
