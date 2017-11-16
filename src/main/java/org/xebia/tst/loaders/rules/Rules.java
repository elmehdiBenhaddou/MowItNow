package org.xebia.tst.loaders.rules;

import java.util.HashMap;
import java.util.Map;

import org.xebia.tst.bo.Orientation;
/**
 * 
 * classe modelise les regles
 * 
 * @author elmehdi
 *
 */
public class Rules {
	
	private  Map<String,Orientation> rules;

	
	public Rules() {
		super();
		rules=new HashMap<String, Orientation>();
	}

	public Map<String, Orientation> getRules() {
		return rules;
	}

	public void setRules(Map<String, Orientation> rules) {
		this.rules = rules;
	}
	/**
	 * methode pour ajouter une regle
	 * @param combination
	 * @param result
	 */
	public void addRule(String combination , String result){
		rules.put(combination, Orientation.valueOf(result));
	}
	
	public Orientation getResult(String rule ){
		return rules.get(rule);
	}
}
