package org.xebia.tst;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.xebia.tst.bo.Orientation;
import org.xebia.tst.dto.MowerDto;
import org.xebia.tst.dto.MowersBagDto;
import org.xebia.tst.loaders.rules.RulesLoader;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
/**
 * 
 * test de bout en bout de l'application
 * 
 * @author elmehdi
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MowItNowAppIntegrationTest {
	
	
	    @Autowired
	    private TestRestTemplate restTemplate;
	    
	    @Autowired
	    private RulesLoader rulesLoader;
	    /**
	     * test nominal pour lancer les tondeuses entrés en JSON
	     * 
	     * 
	     * @throws JsonParseException
	     * @throws JsonMappingException
	     * @throws IOException
	     */
	    @Test
	    public void test_moveMowers_nominal() throws JsonParseException, JsonMappingException, IOException{
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        MowersBagDto mowerBag=new MowersBagDto();
	        mowerBag.setBorderX(5);
	        mowerBag.setBorderY(5);
	        List<MowerDto> mowersToMove =new ArrayList<MowerDto>();
	        MowerDto mowerToMove=new MowerDto();
	        mowerToMove.setX(1);
	        mowerToMove.setY(2);
	        mowerToMove.setOrientation(Orientation.N);
	        mowerToMove.setOrders("GAGAGAGAA");
	        mowersToMove.add(mowerToMove);
	        mowerBag.setMowers(mowersToMove);
	        HttpEntity<MowersBagDto> entity = new HttpEntity<>(mowerBag, headers);
	        //charger les regles dans l'application
	        XebiaTstApplication.RULES_FILE_PATH=System.getProperty("user.dir")+"/src/test/resources/rules.txt";
	        XebiaTstApplication.RULES=rulesLoader.loadRules();
	        //apl du ws pour lancer le moving
	        ResponseEntity<List> response = restTemplate.postForEntity("/v1/mowItNow", entity, List.class, Collections.EMPTY_LIST);
	        
	        //verification
	        assertEquals(response.getStatusCode(),HttpStatus.OK);
	        assertEquals(((LinkedHashMap<?, ?>)response.getBody().get(0)).get("x"),1);
	        assertEquals(((LinkedHashMap<?, ?>)response.getBody().get(0)).get("y"),3);
	        assertEquals(((LinkedHashMap<?, ?>)response.getBody().get(0)).get("orientation"),Orientation.N.toString());
	    }
	    /**
	     * 
	     * test nominal pour lancer les tondeuses entrés par fichier
	     * 
	     * 
	     * 
	     * @throws JsonParseException
	     * @throws JsonMappingException
	     * @throws IOException
	     */
	    @Test
	    public void test_handleMowersFileUpload_nominal() throws JsonParseException, JsonMappingException, IOException{
	    	//charger les regles dans l'application
	        XebiaTstApplication.RULES_FILE_PATH=System.getProperty("user.dir")+"/src/test/resources/rules.txt";
	        XebiaTstApplication.RULES=rulesLoader.loadRules();
	    	MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<String, Object>();
	    	parameters.add("file", new FileSystemResource(System.getProperty("user.dir")+"/src/test/resources/mowers.txt"));

	    	HttpHeaders headers = new HttpHeaders();
	    	headers.set("Content-Type", "multipart/form-data");
	    	headers.set("Accept", "application/json");
	    	HttpEntity<MultiValueMap<String, Object>> entity=	new HttpEntity<MultiValueMap<String, Object>>(parameters, headers);
	        ResponseEntity<List> response = restTemplate.postForEntity("/v1/mowItNow/uploadMowersFile", entity, List.class, Collections.EMPTY_LIST);
	        
	        //verification
	        assertEquals(response.getStatusCode(),HttpStatus.OK);
	        assertEquals(((LinkedHashMap<?, ?>)response.getBody().get(0)).get("x"),1);
	        assertEquals(((LinkedHashMap<?, ?>)response.getBody().get(0)).get("y"),3);
	        assertEquals(((LinkedHashMap<?, ?>)response.getBody().get(0)).get("orientation"),Orientation.N.toString());
	     }
}
