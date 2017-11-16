package org.xebia.tst.parser;

import org.springframework.web.multipart.MultipartFile;
import org.xebia.tst.exceptions.FileParsException;
/**
 * 
 * 
 * @author elmehdi
 *
 */
public interface MowersFileParser {

	/**
	 * methode pour parser un fichier de tondeuse et fournir une liste des tondeuses avec les commandes associers
	 * ainsi que la limite du gazon
	 * 
	 * @param file
	 * @return
	 * @throws FileParsException
	 */
	MowersBag parsFile(MultipartFile file) throws FileParsException;
	
}
