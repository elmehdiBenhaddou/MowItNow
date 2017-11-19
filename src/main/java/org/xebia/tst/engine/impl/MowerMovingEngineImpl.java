package org.xebia.tst.engine.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.xebia.tst.bo.Point;
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

	 static final Logger LOGGER = Logger.getLogger(MowerMovingEngineImpl.class.getName());
	 
	@Override
	public Mower startMovingMower(Rules rules,Mower mower,char[] commandes,Point borderCoord) {
		LOGGER.info("Start moving mower :" + mower);
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
		
		LOGGER.info("End moving mower :" + mower);
		return mower;
	}

	private boolean isMowerOverflowing(Mower mower,Point borderCoord){
		if(mower.getCoordinates().getX() > borderCoord.getX() || mower.getCoordinates().getY() > borderCoord.getY() 
				 || mower.getCoordinates().getX() < 0 || mower.getCoordinates().getY() < 0){
		   return true;
		}
		return false;
	}
	
}
