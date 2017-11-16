package org.xebia.tst.loaders.rules.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xebia.tst.XebiaTstApplication;
import org.xebia.tst.loaders.rules.Rules;
import org.xebia.tst.loaders.rules.RulesLoader;


/**
 * 
 * 
 * @author elmehdi
 *
 */
@Component
public class RulesLoaderImpl implements RulesLoader{
	
	@Value("${prop.rule.definition.pattern}")
	private String ruleDefPattern;

	protected static final Logger LOGGER = Logger.getLogger(RulesLoaderImpl.class.getName());
	
	public Rules loadRules() {
		Rules rules=new Rules();
		try (Stream<String> stream = Files.lines(Paths.get(XebiaTstApplication.RULES_FILE_PATH))) {
			stream.forEach(line -> {
				if(!line.matches(ruleDefPattern)){
					LOGGER.error("==============> Error to start application : Rule Error Definition in rules file in line " + line);
					//stop le processus
					System.exit(1);
				}
				StringTokenizer st  = new StringTokenizer(line,"=", false);
				rules.addRule(st.nextElement().toString(), st.nextElement().toString());
			});
		} catch (IOException e) {
			LOGGER.error("==============>Error to start application : I/O Exception =" + e.getMessage());
			//stop le processus
			System.exit(1);
		}
		return rules;
	}
}
