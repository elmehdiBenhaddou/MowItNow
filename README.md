# MowItNow

Techno:
 Java 8 , spring boot
 
To build and run the appli :
  1- get the source from repo :
      git clone https://github.com/elmehdiBenhaddou/MowItNow.git
  2- run maven cmd :
     mvn clean install
  3- cd to target 
  4- run the jar with the path of rules file:
      java -jar pathToJar/mowItNow-0.0.1-SNAPSHOT.jar "pathToRulesFile"
      
      ***to get rules file , creat a txt file with content of rules.txt in test resources.
   
  5-and go to http://yourHost:8080/upload 
       
  6-to send json message :
     -method POST
     -url:http://yourHost:8080/v1/mowItNow/
     -json message example:
      {
	"borderX" : "5",
	"borderY" : "5",

    "mowers" :	[
		
		{
			"x" :"1",
			"y" : "2",
			"orientation" : "N",
			"orders" : "GAGAGAGAA"
		},
			{
			"x" :"3",
			"y" : "3",
			"orientation" : "E",
			"orders" : "AADAADADDA"
		}
		]
}