package org.xebia.tst.web.ws;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xebia.tst.XebiaTstApplication;
import org.xebia.tst.bo.Coordonnees;
import org.xebia.tst.bo.Mower;
import org.xebia.tst.dto.MowerDto;
import org.xebia.tst.dto.MowersBagDto;
import org.xebia.tst.engine.MowerMovingEngine;
import org.xebia.tst.exceptions.FileParsException;
import org.xebia.tst.parser.MowerAndOrdersWrapper;
import org.xebia.tst.parser.MowersBag;
import org.xebia.tst.parser.MowersFileParser;
/**
 * 
 * web services pour dialoguer aver l'appli
 * 
 * 
 * 
 * 
 * @author elmehdi
 *
 */
@RestController
@RequestMapping("/v1/mowItNow")
public class MowItNowWs {

	@Autowired
	private MowersFileParser mowersFileParser;
	
	@Autowired
	private MowerMovingEngine mowerMovingEngine;


	/**
	  * web service qui attend un message Json en entrée qui represente les tondeuses
	 * et retourne les positions aprés deplacement de ces tondeuses 
	 * @param mowersToMove
	 * @return
	 */
	@RequestMapping( method = RequestMethod.POST)
	List<MowerDto> moveMowers(@Valid @RequestBody MowersBagDto mowersToMove){
		List<MowerDto> movedMowers =new ArrayList<MowerDto>();
		Coordonnees border =new Coordonnees(mowersToMove.getBorderX(),mowersToMove.getBorderY());
		  for(MowerDto mowerTomove : mowersToMove.getMowers()){
			  MowerDto mowerDto=new MowerDto();
			  Mower mower=mowerMovingEngine.startMovingMower(XebiaTstApplication.RULES,
					  new Mower(new Coordonnees(mowerTomove.getX(), mowerTomove.getY()), mowerTomove.getOrientation(), false) , mowerTomove.getOrders().toCharArray(),border);
			  mowerDto.setX(mower.getCoordonnees().getX());
			  mowerDto.setY(mower.getCoordonnees().getY());	
			  mowerDto.setOrientation(mower.getOrientation());
			  movedMowers.add(mowerDto);
			}
		return movedMowers;
	}
	/**
	 * 
	 * web service qui attend un fichier txt en entrée qui represente les tondeuses
	 * et retourne les positions aprés deplacement de ces tondeuses
	 * 
	 * @param file
	 * @return
	 * @throws FileParsException
	 */
	@RequestMapping(value="/uploadMowersFile", method=RequestMethod.POST)
    public List<MowerDto> handleMowersFileUpload( @RequestParam("file") MultipartFile file) throws FileParsException{
		List<MowerDto> movedMowers =new ArrayList<MowerDto>();
		  MowersBag mowersWrapper = mowersFileParser.parsFile(file);
		  for(MowerAndOrdersWrapper mowerAndOrders : mowersWrapper.getMowerAndOrders()){
			  MowerDto mowerDto=new MowerDto();
			  Mower mower=mowerMovingEngine.startMovingMower(XebiaTstApplication.RULES, mowerAndOrders.getMower(), mowerAndOrders.getOrders(),mowersWrapper.getBorder());
			  mowerDto.setX(mower.getCoordonnees().getX());
			  mowerDto.setY(mower.getCoordonnees().getY());	
			  mowerDto.setOrientation(mower.getOrientation());
			  movedMowers.add(mowerDto);
			}
		return movedMowers;
    }
	
}