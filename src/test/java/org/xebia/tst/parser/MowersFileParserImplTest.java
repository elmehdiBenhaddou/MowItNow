package org.xebia.tst.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;
import org.xebia.tst.bo.Orientation;
import org.xebia.tst.exceptions.ErrorsMessages;
import org.xebia.tst.exceptions.FileParsException;
import org.xebia.tst.parser.impl.MowersFileParserImpl;

@RunWith(SpringRunner.class)
@Import(MowersFileParserImpl.class)
@TestPropertySource(locations="classpath:/application.properties")
public class MowersFileParserImplTest {

	@Autowired
	private MowersFileParser mowersFileParser;
	
	@Value("${prop.orders.definition.pattern}")
	private String ordersDefPattern;
	
	@Value("${prop.border.coord.definition.pattern}")
	private String borderCoordDefPattern;
	
	@Value("${prop.mower.definition.pattern}")
	private String mowerDefPattern;
	 
	@Test
	public void test_pars_file_nominal() throws IOException, FileParsException{
		
		    MultipartFile multipartFile = new MockMultipartFile("mowers",
		            "mowers.txt", "text/plain", Files.readAllBytes(Paths.get("src/test/resources/mowers.txt")));
		    MowersBag mowersBag = mowersFileParser.parsFile(multipartFile);
		    assertNotNull(mowersBag);
		    assertNotNull(mowersBag.getMowerAndOrders());
		    assertEquals(mowersBag.getMowerAndOrders().size(), 3);
		    assertEquals(mowersBag.getBorder().getX(), 5);
		    assertEquals(mowersBag.getMowerAndOrders().get(0).getMower().getOrientation(), Orientation.N);
		    assertEquals(mowersBag.getMowerAndOrders().get(0).getMower().getCoordinates().getX(), 1);
		    assertEquals(mowersBag.getMowerAndOrders().get(0).getMower().getCoordinates().getY(), 2);
		    assertEquals(mowersBag.getMowerAndOrders().get(2).getMower().getCoordinates().getY(), 4);
	}
	
	@Test
	public void test_pars_file_empty() throws IOException{
		    MultipartFile multipartFile = new MockMultipartFile("mowersEmpty",
		            "mowersEmpty.txt", "text/plain", Files.readAllBytes(Paths.get("src/test/resources/mowersEmpty.txt")));
		    try {
			  mowersFileParser.parsFile(multipartFile);
			} catch (FileParsException e) {
				   assertEquals(e.getMessage(), ErrorsMessages.EMPTY_FILE_ERROR_MSG);
			}
	}
	
	@Test
	public void test_pars_file_definition_error() throws IOException{
		    MultipartFile multipartFile = new MockMultipartFile("mowersDefError",
		            "mowersDefError.txt", "text/plain", Files.readAllBytes(Paths.get("src/test/resources/mowersDefError.txt")));
		    try {
			  mowersFileParser.parsFile(multipartFile);
			} catch (FileParsException e) {
				   assertEquals(e.getMessage(), ErrorsMessages.FILE_DEF_ERROR_MSG);
			}
	}
	
	@Test
	public void test_pars_file_border_definition_error() throws IOException{
		    MultipartFile multipartFile = new MockMultipartFile("mowersBorderDefError",
		            "mowersBorderDefError.txt", "text/plain", Files.readAllBytes(Paths.get("src/test/resources/mowersBorderDefError.txt")));
		    try {
			  mowersFileParser.parsFile(multipartFile);
			} catch (FileParsException e) {
				   assertEquals(e.getMessage(), String.format(ErrorsMessages.BORDER_DEF_ERROR_MSG ,"5 A", borderCoordDefPattern));
			}
	}
	
	
	@Test
	public void test_pars_file_mower_definition_error() throws IOException{
		    MultipartFile multipartFile = new MockMultipartFile("mowerDefError",
		            "mowerDefError.txt", "text/plain", Files.readAllBytes(Paths.get("src/test/resources/mowerDefError.txt")));
		    try {
			  mowersFileParser.parsFile(multipartFile);
			} catch (FileParsException e) {
				   assertEquals(e.getMessage(), String.format(ErrorsMessages.MOWER_DEF_ERROR_MSG ,"1 5 R", mowerDefPattern));
			}
	}
	@Test
	public void test_pars_file_mower_order_definition_error() throws IOException{
		    MultipartFile multipartFile = new MockMultipartFile("mowerOrderDefError",
		            "mowerOrderDefError.txt", "text/plain", Files.readAllBytes(Paths.get("src/test/resources/mowerOrderDefError.txt")));
		    try {
			  mowersFileParser.parsFile(multipartFile);
			} catch (FileParsException e) {
				   assertEquals(e.getMessage(), String.format(ErrorsMessages.ORDER_DEF_ERROR_MSG ,"MMMDDD", ordersDefPattern));
			}
	}
}
