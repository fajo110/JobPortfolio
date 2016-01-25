package Methods;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import driver.MyParser;

public class CoAuthorsSorted {
  
 public static ArrayList<String> extractCoAuthors(String googleScholarURL) {
    try {

      ArrayList<String> ListCoAuthors = new ArrayList<String>();
      MyParser googleScholarParser = new MyParser();
      String rawHTMLString = googleScholarParser.getHTML(googleScholarURL);

      String reForCoAuthors =
          "Co-authors(.*?)View all co-authors</a>";    
      //"<a class=\"cit-dark-link\" href=\"(.*?)\" title=\"(.*?)\">(.*?)</a>;
      Pattern patternObject = Pattern.compile(reForCoAuthors);
      Matcher matcherObject = patternObject.matcher(rawHTMLString);
      String temp = new String();
      while (matcherObject.find()){
        temp = matcherObject.group(1);
      }
      String reTotalCoAuthors =
          "<a class=\"cit-dark-link\" href=\"(.*?)\" title=\"(.*?)\">(.*?)</a>";    
      Pattern patternObject1 = Pattern.compile(reTotalCoAuthors);
      Matcher matcherObject1 = patternObject1.matcher(temp);
      
      int sum =0;
      while (matcherObject1.find()){
        ListCoAuthors.add(matcherObject.group(1));
      }
      System.out.println("6. Co Authors: "+ sum);

    } catch (Exception e) {
      System.out.println("malformed URL or cannot open connection to "
          + "given URL");
    }
    return googleScholarURL;
    
   }
  
}
