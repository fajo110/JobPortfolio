package a1;

public class man {
  public man(String cmd) {
    if (cmd.equals("mkdir")) {
      getMkdir();
    } else if (cmd.equals("exit")) {
      getExit();
    } else if (cmd.equals("cd")) {
      getCd();
    } else if (cmd.equals("ls")) {
      getLs();
    } else if (cmd.equals("mv")) {
      getMv();
    } else if (cmd.equals("cp")) {
      getCp();
    } else if (cmd.equals("cat")) {
      getCat();
    } else if (cmd.equals("echo")) {
      getEcho();
    } 
    else if (cmd.equals("pwd")){
      getPwd();
    }else if (cmd.equals("man")) {

      getMan();
    } else {
      System.out.println("Invalid command, please try again.");
    }
  }

  public void getExit() {
    System.out.println("Quit the program.");
  }

  public void getMkdir() {
    System.out.println("Create directories, each of which may be relative "
        + "to the current directory or may be a full path.");
  }

  public void getCd() {
    System.out.println("Change directory to DIR, which may be "+
        "relative to the current directory or may be a full path\n"+
        "As with Unix, .. means a parent directory "+
        "and a . mean the current directory");
  }

  public void getLs() {
    System.out.println("If no paths are given, print the contents "+
        "(file or directory) of the current directory\n"+
        "with a  new line following each of the content (file or directory).");
  }

  public void getPwd() {
    System.out.println("Print the current working directory path (i.e. the"+
        " whole absolute path)");
  }

  public void getMv() {
    System.out.println("Move item OLDPATH to NEWPATH. Both OLDPATH  "+
        "and NEWPATH may be relative to the current directory or may be full paths.\n"+
        "If NEWPATH is a directory, move the item into the directory");
  }

  public void getEcho() {
    System.out.println("Move item OLDPATH to NEWPATH. Both OLDPATH and NEWPATH may be "+
        "relative to the current directory or may be full paths.\n"+
        "If NEWPATH is a directory, move the item into the directory. "+
        "Doesn't remove item OLDPATH.");
  }
  public void getCat() {
    System.out.println("Move item OLDPATH to NEWPATH. Both OLDPATH and NEWPATH may be "+
        "relative to the current directory or may be full paths.\n"+
        "If NEWPATH is a directory, move the item into the directory. "+
        "Doesn't remove item OLDPATH.");
  }
  public void getCp() {
    System.out.println("Move item OLDPATH to NEWPATH. Both OLDPATH and NEWPATH may be "+
        "relative to the current directory or may be full paths.\n"+
        "If NEWPATH is a directory, move the item into the directory. "+
        "Doesn't remove item OLDPATH.");
  }
  public void getMan() {
    System.out.println("Move item OLDPATH to NEWPATH. Both OLDPATH and NEWPATH may be "+
        "relative to the current directory or may be full paths.\n"+
        "If NEWPATH is a directory, move the item into the directory. "+
        "Doesn't remove item OLDPATH.");
  }
}

