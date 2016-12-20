/** 
 *A class that represents a poll
 * A poll has a name, date, sample size, MOE, if it addressed likely voters or RV, and result
 *Import file java.utils.*
 */
import java.util.*;
public class Poll {
  /////////////////////////////fields////////////////////////////////////

  /** the name of the poll */
  private String pollName;
  /** the date of the poll */
  private String date;
  /** the sample size of the poll */
  private int sampleSize;
  /** the margin of error of the poll */
  private float MOE;
  //result for candidate */
  private int candidateResult;

  //////////////////////////Constructors//////////////////////////////////

  /**
   *A constructor that creates a new poll object
   *@param date the date of the poll
   *@param pollName name of poll
   *@param sampleSize the sample size of poll
   *@param candidateResult the result for the candidate 
   *@param MOE the margin of error
   */
  public Poll(String date, String pollName, String sampleSize, String candidateResult, String MOE)
  {
    //Convert date to date data_type

    this.date=date;
    this.pollName=pollName;
    if(!sampleSize.equals("--")){
    this.sampleSize=Integer.parseInt(sampleSize); //<>// //<>//
    } else{
      //if no sampleSize exists, set to -1;
      this.sampleSize=-1;
    }
    if(!MOE.equals("--")){
    this.MOE=Float.parseFloat(MOE);
    } else{
      //if no MOE exists, set to -1;
      this.MOE=-1;
    }
    this.candidateResult=Integer.parseInt(candidateResult);
  }
//
  ///////////////////Methods///////////////////////////////
  
  //A toString Method
  public String toString()
  {
    return("This poll was conducted on " +this.date + " by " +this.pollName + "." + " The poll's sample size was " + this.sampleSize + " voters." + "\n" + "The poll's margin of error was " + this.MOE +"." + "\n"+ "The candidate polled at " +this.candidateResult + "%.");
  }


  //Get Methods for all instance variables

  public String getpollName() {
    return this.pollName;
  }
  public String date() {  
    return this.date;
  }
  public int getsampleSize() {
    if(this.sampleSize==-1){
      System.out.println("No sample size was recorded for this poll");
    }
    return this.sampleSize;
  }
  public int getcandidateResult() {
    return this.candidateResult;
  }
  public float getMOE() {
    if(this.MOE==-1){
      System.out.println("No Margin of Error was recorded for this poll");
    }
    return this.MOE;
  }
}