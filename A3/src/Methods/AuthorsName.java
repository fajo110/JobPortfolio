package Methods;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Methods.getHtml;

public class AuthorsName {
  
  public static String extractAuthorsName(String googleScholarURL) {
    try {
      getHtml googleScholarParser = new getHtml();
      
      String rawHTMLString = googleScholarParser.getHTML(googleScholarURL);
//gets it from getHTML
      String reForAuthorExtraction =
          "<span id=\"cit-name-display\" class=\"cit-in-place-nohover\">"
          + "(.*?)</span>";
      //runs through the regex
      Pattern patternObject = Pattern.compile(reForAuthorExtraction);
      Matcher matcherObject = patternObject.matcher(rawHTMLString);
      while (matcherObject.find()) {
        System.out.println("1.Name of Author:\n " + "\t" +
            matcherObject.group(1));
        //takes the value from the first group, for the author name
      }

    } catch (Exception e) {
      System.out.println("malformed URL or cannot open connection to "
          + "given URL");
    }
    return googleScholarURL;
    
   }
  
}
