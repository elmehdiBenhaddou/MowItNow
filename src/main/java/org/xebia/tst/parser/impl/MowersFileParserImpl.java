package org.xebia.tst.parser.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.xebia.tst.bo.Coordonnees;
import org.xebia.tst.bo.Mower;
import org.xebia.tst.bo.Orientation;
import org.xebia.tst.exceptions.FileParsException;
import org.xebia.tst.parser.MowerAndOrdersWrapper;
import org.xebia.tst.parser.MowersBag;
import org.xebia.tst.parser.MowersFileParser;

/**
 * 
 * 
 * @author elmehdi
 *
 */
@Component
public class MowersFileParserImpl implements MowersFileParser{
	
	
	@Value("${prop.orders.definition.pattern}")
	private String ordersDefPattern;
	
	@Value("${prop.border.coord.definition.pattern}")
	private String borderCoordDefPattern;
	
	@Value("${prop.mower.definition.pattern}")
	private String mowerDefPattern;
	
	@Override
	public MowersBag parsFile(MultipartFile file) throws FileParsException {
		  if(file.isEmpty()){
			  throw new FileParsException("Empty file");
		  }
		String content = null;
		try {
			content = new String(file.getBytes());
		}catch (IOException e) {
			throw new RuntimeException("I/O Exception :" + e.getMessage());
		}
        String[] lines = content.split("\\r?\\n");
        MowersBag mowersWrapper = new MowersBag();
        List<MowerAndOrdersWrapper> mowersAndOrders = new ArrayList<MowerAndOrdersWrapper>();
        //verifier le nombre de lines
        if(lines.length % 2 == 0){
        	throw new FileParsException("File definition error" );
	     }
        //lire la limite du gazon
        if(!lines[0].matches(borderCoordDefPattern)){
        	 throw new FileParsException("Error Border point definition "+ lines[0]+"; Correct Pattern :" + borderCoordDefPattern);
        }
        StringTokenizer st  = new StringTokenizer(lines[0]," ", false);
        int limitX = Integer.parseInt(st.nextElement().toString());
        int limitY = Integer.parseInt(st.nextElement().toString());
        mowersWrapper.setBorder(new Coordonnees(limitX, limitY));
        //construction des tondeuses
        for(int i =1 ; i < lines.length ; i = i + 2){
        	if(!lines[i].matches(mowerDefPattern)){
        		throw new FileParsException("Error to define Mower " + lines[i] +"; Correct Pattern :" + mowerDefPattern);
        	}
        	st  = new StringTokenizer(lines[i]," ", false);
        	MowerAndOrdersWrapper mowerWrapper= new MowerAndOrdersWrapper();
        	Coordonnees initMowerCoord=new Coordonnees(Integer.parseInt(st.nextElement().toString()), Integer.parseInt(st.nextElement().toString()));
        	Mower mower = new Mower(initMowerCoord,  Orientation.valueOf(st.nextElement().toString()), false);
        	mowerWrapper.setMower(mower);
        	//set les commandes
        	if(!lines[i+1].matches(ordersDefPattern)){
        		throw new FileParsException("Error to define mower orders :  " + lines[i+1] +"; Correct Pattern :" + ordersDefPattern);
        	}
        	mowerWrapper.setOrders(lines[i+1].toCharArray());
        	mowersAndOrders.add(mowerWrapper);
        }
        mowersWrapper.setMowerAndOrders(mowersAndOrders);
         return mowersWrapper;
		
	}
}
