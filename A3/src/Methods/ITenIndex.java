package Methods;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Methods.getHtml;

public class ITenIndex {
  
 public static void extractITenIndex(String googleScholarURL) {
    try {
      getHtml googleScholarParser = new getHtml();
      String rawHTMLString = googleScholarParser.getHTML(googleScholarURL);

      String reForITenIndex ="i10-index</a></td>(.*)</td><td "
          + "class=\"cit-borderleft cit-data\">(.*?)<";
    //runs through it, finds the right regex for it
      Pattern patternObject = Pattern.compile(reForITenIndex);
      Matcher matcherObject = patternObject.matcher(rawHTMLString);
      
      matcherObject.find();
      System.out.println("3. Number of i10-index after 2009:\n" + "\t"+ 
          matcherObject.group(2)); 
    //uses the group 2 from the regex

    } catch (Exception e) {
      System.out.println("malformed URL or cannot open connection to "
          + "given URL");
    }
    
   }
  
}
