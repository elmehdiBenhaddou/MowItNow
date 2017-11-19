package org.xebia.tst.web.ws;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.xebia.tst.XebiaTstApplication;
import org.xebia.tst.bo.Mower;
import org.xebia.tst.bo.Point;
import org.xebia.tst.dto.MowerDto;
import org.xebia.tst.dto.MowersBagDto;
import org.xebia.tst.engine.MowerMovingEngine;
import org.xebia.tst.exceptions.ErrorsMessages;
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

	static final Logger LOGGER = Logger.getLogger(MowItNowWs.class.getName());
	
	@Autowired
	private MowersFileParser mowersFileParser;
	
	@Autowired
	private MowerMovingEngine mowerMovingEngine;

	@Value("${prop.mowers.file.ext}")
	private String fileExt;

	/**
	  * web service qui attend un message Json en entrée qui represente les tondeuses
	 * et retourne les positions aprés deplacement de ces tondeuses 
	 * @param mowersToMove
	 * @return
	 */
	@RequestMapping( method = RequestMethod.POST)
	List<MowerDto> moveMowers(@Valid @RequestBody MowersBagDto mowersToMove){
		LOGGER.info("Call moving mowers WS from Json");
		List<MowerDto> movedMowers =new ArrayList<MowerDto>();
		Point border =new Point(mowersToMove.getBorderX(),mowersToMove.getBorderY());
		  for(MowerDto mowerTomove : mowersToMove.getMowers()){
			  MowerDto mowerDto=new MowerDto();
			  Mower mower=mowerMovingEngine.startMovingMower(XebiaTstApplication.RULES,
					  new Mower(new Point(mowerTomove.getX(), mowerTomove.getY()), mowerTomove.getOrientation(), false) , mowerTomove.getOrders().toCharArray(),border);
			  mowerDto.setX(mower.getCoordinates().getX());
			  mowerDto.setY(mower.getCoordinates().getY());	
			  mowerDto.setOrientation(mower.getOrientation());
			  movedMowers.add(mowerDto);
			}
		  LOGGER.info("End calling moving mowers WS from Json");
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
		LOGGER.info("Call moving mowers WS from file ");
		//verifier l'extension du fichier
		if(!file.getOriginalFilename().endsWith(fileExt)){
			LOGGER.error("Error ext file :"+ErrorsMessages.EXT_FILE_ERROR_MSG);
			throw new FileParsException(ErrorsMessages.EXT_FILE_ERROR_MSG);
		}
		List<MowerDto> movedMowers =new ArrayList<MowerDto>();
		  MowersBag mowersWrapper = mowersFileParser.parsFile(file);
		  for(MowerAndOrdersWrapper mowerAndOrders : mowersWrapper.getMowerAndOrders()){
			  MowerDto mowerDto=new MowerDto();
			  Mower mower=mowerMovingEngine.startMovingMower(XebiaTstApplication.RULES, mowerAndOrders.getMower(), mowerAndOrders.getOrders(),mowersWrapper.getBorder());
			  mowerDto.setX(mower.getCoordinates().getX());
			  mowerDto.setY(mower.getCoordinates().getY());	
			  mowerDto.setOrientation(mower.getOrientation());
			  movedMowers.add(mowerDto);
			}
		LOGGER.info("End calling moving mowers WS from file");
		return movedMowers;
    }
	
}