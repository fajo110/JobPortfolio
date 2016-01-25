package Methods;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import driver.MyParser;


public class FirstThreePubs {
  
 public static String extractFirstThreePubs(String googleScholarURL) {
    try {
      getHtml googleScholarParser = new getHtml();
      String rawHTMLString = googleScholarParser.getHTML(googleScholarURL);

      String reForFirstThreePubs =
          "<tr class=\"cit-table item\"><td id=\"col-title\"><a href=\"(.*?)\" "
          + "class=\"cit-dark-large-link\">([^<]+)";
      Pattern patternObject = Pattern.compile(reForFirstThreePubs);
      Matcher matcherObject = patternObject.matcher(rawHTMLString);
      //runs through the string, finds the value
      String firstThreePubs = new String();
      for (int number=1; number <=3; number++){
        matcherObject.find();
        firstThreePubs += "\t"+ number + "- " + matcherObject.group(2) + "\n";
        //gets it from the second group
      }
    System.out.println("4. Title of the first 3 Publications: " +"\n" 
        + firstThreePubs);
    } catch (Exception e) {
      System.out.println("malformed URL or cannot open connection to "
          + "given URL");
    }
    return googleScholarURL;     
 
}
}