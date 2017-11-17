package org.xebia.tst;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.xebia.tst.loaders.rules.Rules;
import org.xebia.tst.loaders.rules.RulesLoader;
import org.xebia.tst.loaders.rules.impl.RulesLoaderImpl;
/**
 * 
 * classe principale d'appli
 * 
 * @author elmehdi
 *
 */
@SpringBootApplication
public class XebiaTstApplication {

	 public static String RULES_FILE_PATH;
	 public static Rules RULES;
	  
		public static void main(String[] args) throws Exception {
			//set rules path file
			 //RULES_FILE_PATH=args[0];
			 //MOWERS_INIT_FILE_PATH=args[1];
			 RULES_FILE_PATH="C:\\Users\\elmehdi\\Desktop\\rules.txt";
			 ApplicationContext app =SpringApplication.run(XebiaTstApplication.class, args);
			 RulesLoader rulesLoader = app.getBean(RulesLoaderImpl.class);
			//chargement du fichier de regles
			 RULES= rulesLoader.loadRules();
		}
}
