package Methods;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import driver.MyParser;

public class CoAuthors {
  
 public static String extractCoAuthors(String googleScholarURL) {
    try {
      getHtml googleScholarParser = new getHtml();
      String rawHTMLString = googleScholarParser.getHTML(googleScholarURL);

      String reForCoAuthors =
          "Co-authors(.*?)";    
      Pattern patternObject = Pattern.compile(reForCoAuthors);
      Matcher matcherObject = patternObject.matcher(rawHTMLString);
      matcherObject.find();
      
      System.out.println("6. Co Authors:\n " +"\t");
          
      System.out.println("------------------------------------------------"
          + "-----------------------");
    } catch (Exception e) {
      System.out.println("malformed URL or cannot open connection to "
          + "given URL");
    }
    return googleScholarURL;
    
   }
  
}



