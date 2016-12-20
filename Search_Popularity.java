/** 
 *A class that represents the search popularity for the candidate
 *Search popularity is simply a relative number of popularity where 100 is the highest
 *includes the date
 
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Search_Popularity {

  /////////////////fields//////////////////////

  /** the date of the data as a date type */

  private Date date;

  /** the candidate result */
  private int candidateResult;

  /////////////////constructors////////////////////

  /**
   *A constructor that creates a new search_popularity object
   *@param date the date of the sample
   *@param candidateResult the result for the candidate
   */
  public Search_Popularity(String date, String candidateResult)

  {
    //Convert date to date data_type
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
    try {

      Date dates = formatter.parse(date);
      this.date=dates;
    } 
    catch (ParseException e) {
      e.printStackTrace();
    }
     this.candidateResult=Integer.parseInt(candidateResult);
  }
  ///////////////////Methods///////////////////////////////

  //A toString Method
  public String toString()
  {
    return("This search analysis occurred on " +this.date + "." + " The candidate's result was " + this.candidateResult +".");
  }


  //Get Methods for all instance variables

  public Date getDate() {
    return this.date;
  }

  public int getcandidateResult() {
    return this.candidateResult;
  }
}