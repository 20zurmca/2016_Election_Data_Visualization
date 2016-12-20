/** 
 * a class that represents a tweet
 *a tweet has a date, a text, and a tweet ID
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tweet {
  //////////////////fields//////////////////////////////////
  /** the date of the tweet */
  public Date date;

  /** the text of the tweet */
  public String text;

  /** the tweet ID */
  public String ID;

  ////////////////Constructors///////////////////////////

  /**
   *A constructor that creates a new tweet
   *@param date, the date of the tweet
   *@param text, the text of the tweet
   *@param ID, the tweet ID
   */
  public Tweet(String date, String text, String ID)
  {
    //Convert date to date data_type
    SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss +0000 yyyy");
    try {

      Date dates = formatter.parse(date);
      this.date=dates;
    } 
    catch (ParseException e) {
      e.printStackTrace();
    }
    this.text=text;
    this.ID=ID;
  }

  ///////////////////Methods///////////////////////////////

  //A to String Method
  public String toString()
  {
    return("This tweet occurred on " + this.date + "\n" + "The tweet text is: " + this.text + " " +this.ID);
  }

  //Get methods
  public Date getDate(){
    return this.date;
  }
  public String getText() { 
    return this.text;
  }
  public String getID() {
    return this.ID;
  }
}