package Methods;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Methods.getHtml;

public class FirstFivePub {
  
 public static String extractFirstFivePub(String googleScholarURL) {
    try {
      getHtml googleScholarParser = new getHtml();
      String rawHTMLString = googleScholarParser.getHTML(googleScholarURL);

      String reForFirstFivePub =
          "<td id=\"col-citedby\"><a class=\"cit-dark-link\" "
          + "href=\"(.*?)\">(.*?)</a>";
    //runs through it, finds the right regex for it
      Pattern patternObject = Pattern.compile(reForFirstFivePub);
      Matcher matcherObject = patternObject.matcher(rawHTMLString);
      int totalCitation = 0;
      for(int count = 1; count < 6; count++){
        matcherObject.find();
        totalCitation += Integer.parseInt(matcherObject.group(2));
      }
      //through the for loop, it will find the sum of the first five 
      //publications
      System.out.println("5. Total paper citation (first 5 papers):\n" + "\t"+ 
      totalCitation);
      
    } catch (Exception e) {
      System.out.println("malformed URL or cannot open connection to "
          + "given URL");
    }
    return googleScholarURL;
    
   }
  
}
