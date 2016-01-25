package Methods;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Methods.getHtml;

public class NumAllCitations {
  
public static void extractNumAllCitations(String googleScholarURL) {
    try {
      getHtml googleScholarParser = new getHtml();
      String rawHTMLString = googleScholarParser.getHTML(googleScholarURL);

      String reForAllCitationExtraction =
          "<td class=\"cit-borderleft cit-data\">(.*?)</td>";
      //runs through the regex
      Pattern patternObject = Pattern.compile(reForAllCitationExtraction);
      Matcher matcherObject = patternObject.matcher(rawHTMLString);
      matcherObject.find();
      System.out.println("2. Number of All Citations:\n" + "\t"+ 
      matcherObject.group(1));
      //uses the first group to get the value

    } catch (Exception e) {
      System.out.println("malformed URL or cannot open connection to "
          + "given URL");
    }
    
   }
  
}
