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
	public Mower startMovingMower(Rules rules,Mower mower,char[] commandes,Coordonnees borderCoord) {
		for(int i = 0 ; i< commandes.length ; i++){
			if(isMowerOverflowing(mower, borderCoord)){
			   mower.setStoped(true);
			   return mower;
			}
			if(commandes[i] == 'A'){
				mower.moveMower();
				if(isMowerOverflowing(mower, borderCoord)){
					   mower.setStoped(true);
					   return mower;
					}	
			}else{
				mower.setOrientation(rules.getResult(mower.getOrientation().toString()+commandes[i]));
			}
		}
		return mower;
	}

	private boolean isMowerOverflowing(Mower mower,Coordonnees borderCoord){
		if(mower.getCoordonnees().getX() > borderCoord.getX() || mower.getCoordonnees().getY() > borderCoord.getY() 
				 || mower.getCoordonnees().getX() < 0 || mower.getCoordonnees().getY() < 0){
		   return true;
		}
		return false;
	}
	
}
