package org.xebia.tst.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * controlleur qui envoi la page html d'upload
 * 
 * @author elmehdi
 *
 */
@Controller
public class IhmsController {

		@RequestMapping("/upload")
	    public String greeting() {
	        return "uploadFile.html";
	    }
}
