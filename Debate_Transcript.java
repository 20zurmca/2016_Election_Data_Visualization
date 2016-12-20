/** A class that represents the transcripts of the debate
 *A transcript has the date of the debate, and the text
 *A transcript has the debate text and the date of the debate
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Debate_Transcript {
  //////////////////////fields//////////////////////////////
  /* A date variable */
  private Date date;
  /* A number of questions asked by townsmen*/
  private int numofPeopleStatements;
  /* A number of questions asked by moderators*/
  private int numofModeratorStatements;
  /* The amount of words a candidate said */
  private int numofCandidateWords;
  /* Moderator 1 name */
  private String Moderator1name;
  /* Moderator 2 name */
  private String Moderator2name;
  /* Moderator 3 name */
  private String Moderator3name;
  /* A string array that holds the candidat's words */
  private String [] candidateWords;

  /////////////////////Constructors/////////////////////////

  /**
   *A constructor that creates a Debate_Transcript object
   * @param date the date of the debate
   * @param numofPeopleStatements the number of statements the people had in the third debate
   * @param numofModeratorStatements the number of statements the moderator(s) had in the debate
   * @param numofCandidateWords the number of words the candidate said in the debate
   * @param Moderator1name the name of the first moderator
   * @param Moderator2name the name of the second moderator (if applicable)
   * @param Moderator3name the name of the third moderator (if applicable)
   */
  public Debate_Transcript(String date, int numofPeopleStatements, int numofModeratorStatements, int numofCandidateWords, String Moderator1name, String Moderator2name, String Moderator3name, String [] candidateWords) 
  {
    //Convert date to date data_type
    SimpleDateFormat formatter = new SimpleDateFormat("M/dd/yy");
    try {

      Date dates = formatter.parse(date);
      this.date=dates;
    } 
    catch (ParseException e) {
      e.printStackTrace();
    }

    this.numofPeopleStatements=numofPeopleStatements;
    this.numofModeratorStatements=numofModeratorStatements;
    this.numofCandidateWords=numofCandidateWords;
    this.Moderator1name=Moderator1name;
    this.Moderator2name=Moderator2name;
    this.Moderator3name=Moderator3name;
    this.candidateWords=candidateWords;
  }

  ///////////////////Methods///////////////////////////////

  //A to String Method
  public String toString(){
    if (!this.Moderator2name.equals("none")) {
      return("This debate occurred on " + this.date + "." + " The candidate said " + this.numofCandidateWords + " words in the debate, while the moderators had " + this.numofModeratorStatements + " statements in the debate." + "\n" + "The moderators' names were " + this.Moderator1name + ","+ this.Moderator2name + " and " + this.Moderator3name + "." + "\n" + "The people had " + this.numofPeopleStatements + " statements in this debate.");
    } else {
      return("This debate occurred on " + this.date + "." + " The candidate said " + this.numofCandidateWords + " words in the debate, while the moderator had " + this.numofModeratorStatements + " statements in the debate." + "\n" + "The moderator's name was " + this.Moderator1name + ".");
    }
  }

  //Get Methods for all instance variables

  public Date getDate() {
    return this.date;
  }
  public int getnumofPeopleStatements() {
    return this.numofPeopleStatements;
  }
  public int getnumofModeratorStatements() {
    return this.numofModeratorStatements;
  }
  public int getnumofCandidateWords() {
    return this.numofCandidateWords;
  }
  public String getModerator1name() {
    return this.Moderator1name;
  }
  public String getModerator2name() {
    return this.Moderator2name;
  }
  public String getModerator3name() {
    return this.Moderator3name;
  }
  public String [] getcandidateWords() {
    return this.candidateWords;
  }
}