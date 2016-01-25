package Methods;
import Methods.*;
public class workFormat {
  private static String StarterCode(String[] args) throws Exception {
    try {
      System.out.println("----------------------------------------------"
          + "-------------------------");
    } catch (Exception e) {
      System.out.println("Did you change the run configuration in"
          + "Eclipse for argument0 and argument 1?");
    }
    
    
    // TODO Auto-generated method stub
    String inputFiles[] = args[0].split(",");
    String result = new String();
    String rawHTML = new String();
    for (String inputFile : inputFiles) {
      result = "1. Name of Author: " + AuthorsName.extractAuthorsName(rawHTML);
      NumAllCitations.extractNumAllCitations(inputFile);
      ITenIndex.extractITenIndex(inputFile);
      FirstThreePubs.extractFirstThreePubs(inputFile);
      FirstFivePub.extractFirstFivePub(inputFile);
      CoAuthors.extractCoAuthors(inputFile);
      //CoAuthorsSorted.extractCoAuthorsSorted(inputFile);
      
    }
    return result;
   }
}
