package org.xebia.tst.parser.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.xebia.tst.bo.Point;
import org.xebia.tst.bo.Mower;
import org.xebia.tst.bo.Orientation;
import org.xebia.tst.exceptions.ErrorsMessages;
import org.xebia.tst.exceptions.FileParsException;
import org.xebia.tst.parser.MowerAndOrdersWrapper;
import org.xebia.tst.parser.MowersBag;
import org.xebia.tst.parser.MowersFileParser;

/**
 * L'implementation du parseur des fichiers des tondeuses
 * 
 * @author elmehdi
 *
 */
@Component
public class MowersFileParserImpl implements MowersFileParser{ 
	
	static final Logger LOGGER = Logger.getLogger(MowersFileParserImpl.class.getName());
	
	@Value("${prop.orders.definition.pattern}")
	private String ordersDefPattern;
	
	@Value("${prop.border.coord.definition.pattern}")
	private String borderCoordDefPattern;
	
	@Value("${prop.mower.definition.pattern}")
	private String mowerDefPattern;
	
	@Override
	public MowersBag parsFile(MultipartFile file) throws FileParsException {
		LOGGER.info("Start parsing mowers file");
		  if(file.isEmpty()){
			  LOGGER.error("Error to pars mowers file :"+ErrorsMessages.EMPTY_FILE_ERROR_MSG);
			  throw new FileParsException(ErrorsMessages.EMPTY_FILE_ERROR_MSG);
		  }
		String content = null;
		try {
			content = new String(file.getBytes());
		}catch (IOException e) {
			LOGGER.error("Error to pars mowers file : I/O Exception");
			throw new RuntimeException("I/O Exception :" + e.getMessage());
		}
        String[] lines = content.split("\\r?\\n"); 
        MowersBag mowersWrapper = new MowersBag();
        List<MowerAndOrdersWrapper> mowersAndOrders = new ArrayList<MowerAndOrdersWrapper>();
        //verifier le nombre de lines 
        if(lines.length % 2 == 0){ 
        	LOGGER.error("Error to pars mowers file : "+ErrorsMessages.FILE_DEF_ERROR_MSG);
        	throw new FileParsException(ErrorsMessages.FILE_DEF_ERROR_MSG);
	     }
        //lire la limite du gazon 
        if(!lines[0].matches(borderCoordDefPattern)){
        	LOGGER.error("Error to pars mowers file : Border coordinates format error");
        	 throw new FileParsException(String.format(ErrorsMessages.BORDER_DEF_ERROR_MSG ,lines[0], borderCoordDefPattern));
        }
        StringTokenizer st  = new StringTokenizer(lines[0]," ", false);
        int limitX = Integer.parseInt(st.nextElement().toString());
        int limitY = Integer.parseInt(st.nextElement().toString());
        mowersWrapper.setBorder(new Point(limitX, limitY));
        //construction des tondeuses
        for(int i =1 ; i < lines.length ; i = i + 2){
        	if(!lines[i].matches(mowerDefPattern)){
        	 LOGGER.error("Error to pars mowers file : Mower format error");
           	 throw new FileParsException(String.format(ErrorsMessages.MOWER_DEF_ERROR_MSG ,lines[i], mowerDefPattern));
        	}
        	st  = new StringTokenizer(lines[i]," ", false);
        	MowerAndOrdersWrapper mowerWrapper= new MowerAndOrdersWrapper();
        	Point initMowerCoord=new Point(Integer.parseInt(st.nextElement().toString()), Integer.parseInt(st.nextElement().toString()));
        	Mower mower = new Mower(initMowerCoord,  Orientation.valueOf(st.nextElement().toString()), false);
        	mowerWrapper.setMower(mower);
        	//set les commandes
        	if(!lines[i+1].matches(ordersDefPattern)){
        		LOGGER.error("Error to pars mowers file : Orders format error");
        		throw new FileParsException(String.format(ErrorsMessages.ORDER_DEF_ERROR_MSG ,lines[i+1], ordersDefPattern));
        	}
        	mowerWrapper.setOrders(lines[i+1].toCharArray());
        	mowersAndOrders.add(mowerWrapper);
        }
        mowersWrapper.setMowerAndOrders(mowersAndOrders);
        LOGGER.info("End parsing mowers file");
         return mowersWrapper;
		
	}
}
