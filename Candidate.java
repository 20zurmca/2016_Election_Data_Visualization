//** A class that represents a Candidate  //<>// //<>//
//  * A candidate has data assigned to them (instance variables)
//  * vairables include tweeter activity, results of polls, debate transcript and search popularity


import processing.core.PApplet;
import processing.data.IntDict;
import java.util.*;
import processing.data.StringList;
import processing.core.PImage;
public class Candidate {

  ///////////////////////////fields////////////////////////////////

  /* A name for the candidate */
  private String name;

  /* An array that stores a candidate's twiter activity */
  private Tweet [] tweets;

  /* An arry that stores a candidate's pollin data */
  private Poll [] polls;

  /* An array that stores the debate transcripts */
  private Debate_Transcript [] transcripts;

  /* An array that stores the values of popularity */
  private Search_Popularity [] popularity;

  /* Counters for all arrays */

  private int numoftweets;
  private int numofpolls;
  private int numoftranscripts;
  private int popularityindex;

  /*An array that holds random color values*/
  float []randomColors=new float[50];

  ////////////////////////////constructors//////////////////////////

  /* A constructor that creates a new candidate
   * @param Name the name of the candidate
   * @param tweetFilename name of the tweet file
   * @param pollFilename name of the poll file
   * @param transcript1Filename name of the first transcript file
   * @param transcript2Filename name of the second transcript file
   * @param transcript3Filename name of the third thranscript file
   * @param PopularityDataFilename popularity data filename
   */
  public Candidate(String name, String TweetFilename, String pollFilename, String transcript1Filename, String transcript2Filename, String transcript3Filename, String PopularityDataFilename, PApplet p) //added the PApplet
  {

    this.name=name;
    //Initialize arrays
    transcripts=new Debate_Transcript[3];
    if (this.name.equals("Clinton")) {
      tweets= new Tweet [3333];
    } else {
      tweets = new Tweet [1453];
    }
    polls=new Poll [180];
    popularity= new Search_Popularity[84];

    //Initialize counters to 0
    this.numoftweets=0;
    this.numofpolls=0;
    this.numoftranscripts=0;
    this.popularityindex=0;

    //call loading methods in constructor
    loadTranscripts(transcript1Filename, p);
    loadTranscripts(transcript2Filename, p);
    loadTranscripts(transcript3Filename, p);
    loadTweets(TweetFilename, p);
    loadPolls(pollFilename, p);
    loadPopularityData(PopularityDataFilename, p);


    //load randomColor array with colors
    for (int i=0; i<50; i++) {
      randomColors[i]=p.random(255);
    }
  }
  //////////////////////////Methods///////////////////////////////

  /** A method that will load the tweets data
   *@param Filename the name of the file
   *@param PApplet p the PApplet passed
   *Data will be loaded, read, and used for the tweet constructor
   */
  public void loadTweets(String Filename, PApplet p)
  {
    //Intitialize index counters for arrays that will hold data from the tweet file
    int TextIndex=0;
    int tweetIDindex=0;
    int DateIndex=0;
    //Initialize Sring arrays that hold all the instance variables for the Tweet class for each tweet
    String [] Tweet_Text;
    String [] Tweet_Dates;
    String [] Tweet_ID;

    //The size of these arrays will differ if the Candidate is trump or clinton (I printed out TextIndex to see how many tweets each candidate had)
    if (this.name.equals("Trump")) {
      Tweet_Text=new String [1453];
      Tweet_Dates=new String [1453];
      Tweet_ID=new String [1453];
    } else {
      Tweet_Text=new String [3333];
      Tweet_Dates=new String [3333];
      Tweet_ID=new String [3333];
    }

    //Load the tweets into a tweet lines array
    String [] Tweet_Lines=p.loadStrings(Filename);

    //the tweets cycle through the instance variables every three lines.  The text of the tweet starts at index position 0, ID number at index position 1, and the date at 2.
    for (int i=0; i<Tweet_Lines.length; i=i+3) {
      Tweet_Text[TextIndex]=Tweet_Lines[i];
      TextIndex++;
    }
    for (int i=1; i<Tweet_Lines.length; i=i+3) {
      Tweet_ID[tweetIDindex]=Tweet_Lines[i];
      tweetIDindex++;
    }
    for (int i=2; i<Tweet_Lines.length; i=i+3) {
      Tweet_Dates[DateIndex]=Tweet_Lines[i];
      DateIndex++;
    }
    //Use a for loop to create all tweet objects and add them to the candidates' tweet array
    for (int i=0; i<Tweet_Text.length; i++) {
      Tweet newTweet= new Tweet(Tweet_Dates[i], Tweet_Text[i], Tweet_ID[i]);
      tweets[this.numoftweets]=newTweet;
      this.numoftweets++;
    }
  }

  /** A method that will load the polls data
   *@param Filename the name of the file
   *@param PApplet p the PApplet passed
   *Data will be loaded, read, and used for the Poll constructor
   */
  public void loadPolls(String Filename, PApplet p)
  {
    //Load the Polling data by line
    String [] Poll_Lines=p.loadStrings(Filename);
    //Create arrays that will store the Poll class's instance variables.  We don't want the first line in the file so the array length is Poll_lines.length-1
    String [] PollDate=new String[Poll_Lines.length-1]; 
    String [] PollName=new String[Poll_Lines.length-1];
    String [] PollSampleSize=new String[Poll_Lines.length-1];
    String [] PollMOE=new String[Poll_Lines.length-1];
    String [] PollCandidateResult=new String[Poll_Lines.length-1];
    //Create an array that will store the data from every line that we catch in a SeparatedData array
    String [] SeparatedPollData=new String [(Poll_Lines.length-1)*8];
    //Index variables 
    int PollDateindex=0;
    int PollNameindex=0;
    int PollSampleSizeindex=0;
    int PollMOEindex=0;
    int PollCandidateResultindex=0;
    int separatedPollDataIndex=0;

    //go through Poll_Lines array and split each line by comma.  Store the separated data in the SeparatedData array. Since the array will be over written in the loop, use a double for loop to catch each itterance 
    for (int i=1; i<Poll_Lines.length; i++) {
      //create an array that will catch a line in the Poll_Lines, separated by comma.  A line will have a length of 8
      String [] SeparatedData=Poll_Lines[i].split(",");
      for (int j=0; j<SeparatedData.length; j++) {
        SeparatedPollData[separatedPollDataIndex]=SeparatedData[j];
        separatedPollDataIndex++;
      }
    }
    //go through the separatedPollData array and take individual data and store that data in the appropriate array intialized above.  The data types recycles every 8 index values in SeparatedPollData
    //Poll names start at i=0
    for (int i=0; i<SeparatedPollData.length; i=i+8) {
      PollName[PollNameindex]=SeparatedPollData[i];
      PollNameindex++;
    }
    //Poll date start at i=1 and 2
    for (int i=1; i<SeparatedPollData.length; i=i+8) {
      //Since we are not using the Poll data in the data visualization and we're keeping the dates as strings, we'll concatenate the start date and end date (index after i) as one string
      PollDate[PollDateindex]=SeparatedPollData[i]+" "+SeparatedPollData[i+1];
      PollDateindex++;
    }
    //Poll Sample Size starts atindex 3
    for (int i=3; i<SeparatedPollData.length; i=i+8) {
      PollSampleSize[PollSampleSizeindex]=SeparatedPollData[i];
      PollSampleSizeindex++;
    }
    //PollMOE starts at index 5
    for (int i=5; i<SeparatedPollData.length; i=i+8) {
      PollMOE[PollMOEindex]=SeparatedPollData[i];
      PollMOEindex++;
    }
    //CandidateResult starts at index 6 for clinton and index 7 for trump
    if (this.name.equals("Clinton")) {
      for (int i=6; i<SeparatedPollData.length; i=i+8) {
        PollCandidateResult[PollCandidateResultindex]=SeparatedPollData[i];
        PollCandidateResultindex++;
      }
    } else {
      for (int i=7; i<SeparatedPollData.length; i=i+8) {
        PollCandidateResult[PollCandidateResultindex]=SeparatedPollData[i];
        PollCandidateResultindex++;
      }
    }
    //Create 180 instances of the Poll Class for the candidate. Store each into the candidate's polls array and update this.numofpolls
    for (int i=0; i<Poll_Lines.length-1; i++) {
      Poll newPoll=new Poll(PollDate[i], PollName[i], PollSampleSize[i], PollCandidateResult[i], PollMOE[i]);
      this.polls[this.numofpolls]=newPoll;
      this.numofpolls++;
    }
  }

  //
  /** a method that will load the debate transcripts data
   *@param Filename the name of the file
   *@param PApplet p the PApplet passed
   *Data will be loaded, read, and used for the Debate_Transcript constructor
   */
  public void loadTranscripts(String Filename, PApplet p)
  {
    //loading file into string array one line at a time
    String [] Transcript_Lines=p.loadStrings(Filename);
    //Initialize variables from the transcript class that will be used when calling the constructor
    int numofPeopleStatements=0;
    int numofModeratorStatements=0;
    int numofTrumpWords=0;
    int numofClintonWords=0;
    String Moderator1name;
    String Moderator2name; 
    String Moderator3name;
    String date=Transcript_Lines[0];
    String []candidateWords;


    //splitting the second line that contains information about the speakers into a String array
    String [] Active_People=Transcript_Lines[1].split(" ");
    //Assigning variables with the data
    Moderator1name=Active_People[0];
    if (Active_People[1].equals("TRUMP") || Active_People[1].equals("CLINTON"))
    {
      Moderator2name="none";
    } else {
      Moderator2name=Active_People[1];
    }
    if (Active_People[2].equals("TRUMP") || Active_People[2].equals("CLINTON"))
    {

      Moderator3name="none";
    } else {
      Moderator3name=Active_People[2];
    }
    //Initializing arrays that will store data for the other variables we want to set (like indiviual words)
    String [] Moderator_Statements=new String [200];
    String [] People_Statements=new String[200];
    String [] TrumpStatements=new String [250];
    String [] ClintonStatements=new String [175];
    String [] TrumpWords=new String [9000];
    String [] ClintonWords=new String [9000];
    //Initialize a CurrentSpeaker variable so we know who is speaking and depending on who is speaking, the speakers' lines will be transferred to a statement array
    String CurrentSpeaker="Initialize";

    //integers for each index array.  we will be taking from the Transcript_Lines array and putting the data in Statements and then words arrays
    int TrumpStatementIndex=0;
    int ClintonStatementIndex=0;
    for (int i=2; i<Transcript_Lines.length; i++) {
      //if the line starts with a moderator, copy those lines to the moderator array. Specify that the moderator is speaking since some lines won't start with "MODERATOR: " but we still want their statements (will be in else condition)
      if (Transcript_Lines[i].startsWith(Moderator1name) || Transcript_Lines[i].startsWith(Moderator2name)) {
        Moderator_Statements[numofModeratorStatements]=Transcript_Lines[i];
        //update index value for the statement array--do the same for all arrays
        numofModeratorStatements++;
        CurrentSpeaker="Moderator";
        //Same for the people
      } else if (Transcript_Lines[i].startsWith("QUESTION")) {
        People_Statements[numofPeopleStatements]=Transcript_Lines[i];
        numofPeopleStatements++;
        CurrentSpeaker="People";
        //same for trump
      } else if (Transcript_Lines[i].startsWith("TRUMP")) {
        TrumpStatements[TrumpStatementIndex]=Transcript_Lines[i];
        TrumpStatementIndex++;
        CurrentSpeaker="Trump";
        //same for clinton
      } else if (Transcript_Lines[i].startsWith("CLINTON")) {
        ClintonStatements[ClintonStatementIndex]=Transcript_Lines[i];
        ClintonStatementIndex++;
        CurrentSpeaker="Clinton";
        //Since not all lines start with the name of the speaker but we still want those speaker's words in their respective arrays, check to see if the person wanted is still speaking.  If true, add their statement to array
      } else {
        if (CurrentSpeaker.equals("Moderator")) {
          Moderator_Statements[numofModeratorStatements]=Transcript_Lines[i];
          numofModeratorStatements++;
        } else if (CurrentSpeaker.equals("People")) {
          People_Statements[numofPeopleStatements]=Transcript_Lines[i];
          numofPeopleStatements++;
        } else if (CurrentSpeaker.equals("Trump")) {
          TrumpStatements[TrumpStatementIndex]=Transcript_Lines[i];
          TrumpStatementIndex++;
        } else if (CurrentSpeaker.equals("Clinton")) {
          ClintonStatements[ClintonStatementIndex]=Transcript_Lines[i];
          ClintonStatementIndex++;
        }
      }
    }

    //The next part of the method will take the statements and break the statements into specific words.  Some words we do not want to count.  We will create a string list with all the words we don't want (in a file)
    StringList uselessWords=new StringList();
    //upload the uselessWords to an array.  Since the words are all in one line, there will only be one index in the array
    String [] uselessWordsTogether=p.loadStrings("uselesswords.txt");
    //Take that array and separate the only index by comma and push it into another array where each index value is a separate word.
    String [] uselessWordsSeparate=uselessWordsTogether[0].split(",");

    //Append all words in the uselessWordsSeparate array to the StringList
    for (int i=0; i<uselessWordsSeparate.length; i++) {
      uselessWords.append(uselessWordsSeparate[i]);
    }

    //Put the trump words into their own array make sure the words we don't want don't get put into the TrumpWords array.  Use the .hasValue function in the StringList class
    for (int i=0; i<TrumpStatementIndex; i++) {
      String [] SeparateTrumpWords=TrumpStatements[i].split(" |,");
      //need a double for loop to prevent over writing and catch each iterrance into TrumpWords array
      for (int j=0; j<SeparateTrumpWords.length; j++) {
        if (!uselessWords.hasValue(SeparateTrumpWords[j])) {
          TrumpWords[numofTrumpWords]=SeparateTrumpWords[j];
          numofTrumpWords++;
        }
      }
    }
    //Put the clinton words into their own array; make sure the words we don't want don't get put into the ClintonWords array.  Use the .hasValue function in the StringList class
    //ClintonStatements.length can varry.  Have i go to less than the statement index.  same for trump
    for (int i=0; i<ClintonStatementIndex; i++) {
      String [] SeparateClintonWords=ClintonStatements[i].split(" |,");
      //need a double for loop to prevent over writing and catch each iterrance into ClintonWords array
      for (int j=0; j<SeparateClintonWords.length; j++) {
        //don't count the words in the uselesswords list
        if (!uselessWords.hasValue(SeparateClintonWords[j])) {
          ClintonWords[numofClintonWords]=SeparateClintonWords[j];
          numofClintonWords++;
        }
      }
    }
    //Put all the information into the Debate_Transcript constructor.  Information will vary if the candidate object is Trump or Clinton
    if (this.name.equals("Trump")) {
      candidateWords=TrumpWords;
      Debate_Transcript Debate=new Debate_Transcript(date, numofPeopleStatements, numofModeratorStatements, numofTrumpWords, Moderator1name, Moderator2name, Moderator3name, candidateWords);
      this.transcripts[this.numoftranscripts]=Debate;
      this.numoftranscripts++;
    } else {
      candidateWords=ClintonWords;
      Debate_Transcript Debate=new Debate_Transcript(date, numofPeopleStatements, numofModeratorStatements, numofClintonWords, Moderator1name, Moderator2name, Moderator3name, candidateWords);
      this.transcripts[this.numoftranscripts]=Debate;
      this.numoftranscripts++;
    }
  }

  /** a method that will load the search popularity data
   *@param Filename the name of the file
   *@param PApplet p the PApplet passed
   *Data will be loaded, read, and used for the Search_Popularity constructor
   */
  public void loadPopularityData(String Filename, PApplet p)
  {
    //Load the file into a string array that is separated by line
    String [] Popularity_Lines=p.loadStrings(Filename);
    //initialize an array that will store each individual cell in the file as an index (separated by comma)
    String [] separatedPopularityData=new String [Popularity_Lines.length*3];
    //initialize the arrays that will store the data for Search_Popularity's instance variables
    String [] date=new String [Popularity_Lines.length];
    String [] candidateResult=new String [Popularity_Lines.length];

    //intialize indexes
    int separatedPopularityDataindex=0;
    int dateIndex=0;
    int candidateResultindex=0;

    //Split the Popularity_Lines arrary by comma and shove that data into a separatedData array (will have a length of 3)
    for (int i=0; i<Popularity_Lines.length; i++) {
      String [] separatedData=Popularity_Lines[i].split(",");
      //Catch each line into the separatedPopularityData array since separatedData is overwritten with each itterance of the loop
      for (int j=0; j<separatedData.length; j++) {
        separatedPopularityData[separatedPopularityDataindex]=separatedData[j];
        separatedPopularityDataindex++;
      }
    }
    //Extract the dates from the separatedPopularityData array and shove them into the date array.  The dates cycle through each 3 indexes in the separatedPopularityData array
    for (int i=0; i<separatedPopularityData.length; i=i+3) {
      date[dateIndex]=separatedPopularityData[i];
      dateIndex++;
    }
    //Do the same for candidateResult.  i starts at 1 for trump and 2 for clinton
    if (this.name.equals("Trump")) {
      for (int i=1; i<separatedPopularityData.length; i=i+3) {
        candidateResult[candidateResultindex]=separatedPopularityData[i];
        candidateResultindex++;
      }
    } else {
      for (int i=2; i<separatedPopularityData.length; i=i+3) {
        candidateResult[candidateResultindex]=separatedPopularityData[i];
        candidateResultindex++;
      }
    }
    //create 84 instances of search_popularity and put each instance into the candidate's popularity array
    for (int i=0; i<Popularity_Lines.length; i++) {
      Search_Popularity addSearch_Popularity = new Search_Popularity(date[i], candidateResult[i]);
      this.popularity[this.popularityindex]=addSearch_Popularity;
      //update the index
      this.popularityindex++;
    }
  }

  public void wordCloud(PApplet p, int transcript) {
    //Creates array of tweets
    String [] candidateTweets=new String[10000];
    int tweetIndex=0;
    for (int i=0; i<this.transcripts[transcript].getcandidateWords().length; i++) {
      candidateTweets[tweetIndex]=transcripts[transcript].getcandidateWords()[i];
      tweetIndex++;
    }


    //Creates a new intdict inventory
    // must import processing library possibly... Says intdict is not a class or type
    IntDict inventory=new IntDict();
    //goes through a given array and adds all of the words to inventory
    for (int i=0; i<candidateTweets.length; i++) {
      //if the inventory already has the key increase the value of that key
      if (inventory.hasKey(candidateTweets[i])==true) {
        inventory.increment(candidateTweets[i]);
      }
      //if the inventory does not have the key add it to the inventory and set the value of the key to 1
      else {
        inventory.set(candidateTweets[i], 1);
      }
    }
    //Sorts the inventory based on the values of the words in descending order
    inventory.sortValuesReverse();
    //Tests the top words array by printing it, for some reason the first value in the array is null;
    String topWords[]= inventory.keyArray();
    //Tests what words are in the array
    //for (int i=0; i<30; i++)
    //{
    //System.out.println(topWords[i]);
    //}
    //Draws the word cloud with the most popular words being displayed as the most large
    //The if statement and else statement were added do to the way that the clinton array of words and trump array of words was oriented, one of the top values in each of these arrays was null and another was blank so these were avoided using these two statements
    //if(name.equals("Trump")){
    p.textSize(170);
    p.textAlign(p.CENTER);
    p.fill(randomColors[0], randomColors[1], randomColors[2]);
    p.text(topWords[2], 650, 170);
    p.fill(randomColors[3], randomColors[4], randomColors[5]);
    p.textSize(125);
    p.text(topWords[3], 650, 295);
    p.fill(randomColors[6], randomColors[7], randomColors[8]);
    p.textSize(100);
    p.text(topWords[4], 650, 395);
    p.fill(randomColors[9], randomColors[10], randomColors[11]);
    p.textSize(75);
    p.text(topWords[5], 650, 475);
    p.fill(randomColors[12], randomColors[46], randomColors[14]);
    p.textSize(65);
    p.text(topWords[6], 650, 535);
    p.fill(randomColors[15], randomColors[16], randomColors[17]);
    p.textSize(50);
    p.text(topWords[7], 650, 585);
    p.fill(randomColors[18], randomColors[19], randomColors[20]);
    p.textSize(35);
    p.text(topWords[8], 650, 620);
    p.fill(randomColors[38], randomColors[42], randomColors[22]);
    p.textSize(25);
    p.text(topWords[9], 650, 645);
    p.fill(randomColors[23], randomColors[34], randomColors[36]);
    p.textSize(15);
    p.text(topWords[10], 650, 660);
  }

  /** 
   *A Function that draws a Line Chart for the popularity data
   * @param ChartWidth the width of the chart
   * @param chartHeight the Height of the chart
   * @param xLocation the x location of the chart
   * @param yLocation the y location of the chart
   * @param p the PApplet passed from processing
   */

  public void drawLineChart(int chartWidth, int chartHeight, int xLocation, int yLocation, PApplet p) {

    //Intialize variables that will be used when drawing the Chart
    int [] candidatePopularity=new int[this.popularity.length];
    int populairtyindex=0;
    for (int i=0; i<this.popularity.length; i++) {
      candidatePopularity[populairtyindex]=this.popularity[i].getcandidateResult();
      populairtyindex++;
    }
    int numberofLinesY=10;
    float chartheight_spacing=chartHeight/numberofLinesY;
    float date_y=yLocation+chartHeight/2;
    float candidateX=chartWidth/this.popularity.length;
    int candidateMin=minVal(candidatePopularity);
    int candidateMax=maxVal(candidatePopularity);
    int [] candidateGains=difference(candidatePopularity);
    int maxGain=maxVal(candidateGains);
    int max_gain_day=arrayPos(candidateGains, maxGain);


    p.noFill();
    p.stroke(0);
    p.rectMode(p.CENTER);
    p.rect(xLocation, yLocation, chartWidth, chartHeight);

    // draw chart grid lines using loop--only thing changing is the y values of a line
    for (int n=0; n<=numberofLinesY; n++) {
      //Intial y value for chart is 100 because chart will have 100 pixels of space on each side
      p.line(100, 100+(n)*chartheight_spacing, chartWidth+100, 100+(n)*chartheight_spacing);
    }
    //Make the title and axis lables
    p.textAlign(p.CENTER, p.TOP);
    p.fill(0);
    p.textSize(20);

    //x axis title
    p.text("Date", p.width/2, p.height-50);

    //y axis label
    p.textAlign(p.CENTER);
    p.text("0", 90, chartHeight+100);
    p.textAlign(p.CENTER, p.CENTER);
    p.text("10", 85, 100+9*chartheight_spacing);
    p.text("20", 85, 100+8*chartheight_spacing);
    p.text("30", 85, 100+7*chartheight_spacing);
    p.text("40", 85, 100+6*chartheight_spacing);
    p.text("50", 85, 100+5*chartheight_spacing);
    p.text("60", 85, 100+4*chartheight_spacing);
    p.text("70", 85, 100+3*chartheight_spacing);
    p.text("80", 85, 100+2*chartheight_spacing);
    p.text("90", 85, 100+chartheight_spacing);
    p.text("100", 80, 100);

    //y axis title
    p.textSize(15);
    p.text("\nSearch\nPopularity\nResult", 35, p.height/2);

    //Chart Title
    p.textSize(20);
    p.text("Candidates' Search Popularity from Aug-Nov 2016", p.width/2, 50);

    //Plot Cadidate points--divide the chart height into 100 for percent purposes.  The Y value will be the array index in a percent form
    for (int i=0; i<candidatePopularity.length; i++) {
      p.point(100+i*candidateX, 100+chartHeight-candidatePopularity[i]*chartHeight/100);
    }
    //Connect Candidate points with a faded line--if the mouse hits the data point after, the faded line turns to a solid line connecting the prior point to the point the mouse passed.  Stop at the last data point.
    //if the mouse passes the max day gains for trump or clinton--turn bright green)
    for (int n=0; n<candidatePopularity.length-1; n++) {
      if (this.name.equals("Trump")) {
        p.stroke(255, 0, 0, 100);
      } else {
        p.stroke(0, 0, 255, 100);
      }
      if (p.mouseX>=100+(n+1)*candidateX && p.mouseX<=chartWidth+100 || p.mouseX>100+chartWidth || p.mouseX<100) { 
        if (this.name.equals("Trump")) {
          p.stroke(255, 0, 0);
        } else {
          p.stroke(0, 0, 255);
        }
      }
      p.strokeWeight(2);
      p.line(100+n*candidateX, 100+chartHeight-candidatePopularity[n]*chartHeight/100, 100+(n+1)*candidateX, 100+chartHeight-candidatePopularity[n+1]*chartHeight/100);
    }

    p.stroke(0, 255, 0, 100);
    //draw a green line that shows the max gain. Turn bright green if mouseX hits the data point after the max gain day for candidate.
    if (p.mouseX>=100+(max_gain_day+1)*candidateX && p.mouseX<=chartWidth+100 || p.mouseX>100+chartWidth || p.mouseX<100) {
      p.stroke(0, 255, 0);
    }
    p.line(100+max_gain_day*candidateX, 100+chartHeight-candidatePopularity[max_gain_day]*chartHeight/100, 100+(max_gain_day+1)*candidateX, 100+chartHeight-candidatePopularity[max_gain_day+1]*chartHeight/100);

    //create the dashed line on the data point MouseX is on--will mark that data point
    for (int x=0; x<candidatePopularity.length; x++) {
      if (p.mouseX>=100+x*candidateX && p.mouseX<100+(x+1)*candidateX) {
        p.stroke(0);

        //y1=0, y2=height (dash line will run from top of canvas to bottom)
        //x positon of line will vary with the mouse
        //Dash length and Space Length are both chartHeight/200
        for (int i=0; i*(chartHeight/200+chartHeight/200)+chartHeight/200<p.height; i++) {
          p.line(100+x*candidateX, i*(chartHeight/200+chartHeight/200), 100+x*candidateX, i*(chartHeight/200+chartHeight/200)+chartHeight/200);
        }

        //draw a circle to mark which data points the cursor is on 
        p.fill(255);
        p.stroke(0);
        p.ellipse(100+x*candidateX, 100+chartHeight-candidatePopularity[x]*chartHeight/100, 8, 8);
        p.ellipse(100+x*candidateX, date_y, 8, 8);
        //Draw a line connecting from the beginning of the chart to the most recent date the mouse is on; subtract  6 from second x value to prevent overlap
        p.stroke(255, 165, 0);
        if (p.mouseX>=100+candidateX) {
          p.line(100, date_y, 100+x*candidateX-6, date_y);
        }
        //Trump, Clinton, and Dates Data labels 
        p.textAlign(p.LEFT, p.CENTER);
        p.textSize(14);
        p.fill(0);
        //move the clinton and trump data labels a little to the right and up of the point to spread out the text
        if (this.name.equals("Trump")) {
          p.text("Trump: "+candidatePopularity[x], 100+x*candidateX+candidateX, 100+chartHeight-candidatePopularity[x]*chartHeight/100-chartHeight/40);
        } else {
          p.text("Clinton: " + candidatePopularity[x], 100+x*candidateX+candidateX, 100+chartHeight-candidatePopularity[x]*chartHeight/100+chartHeight/25);
        }
        p.textSize(12);
        p.text(this.popularity[x].getDate().toString(), 100+x*candidateX+candidateX, date_y+5);
      }
    }

    //Mark Candidate Max and change the fade depending where the mouse is (passed the point, on the point, or out of chart=full color)

    p.stroke(0);
    p.strokeWeight(1);
    //set to fade
    p.fill(0, 255, 0, 100);
    if (this.name.equals("Trump")) {
      //change fill to solid if on or passed point
      if (p.mouseX>=100+getMaxIndex(candidatePopularity)*candidateX && p.mouseX<=chartWidth+100 || p.mouseX>100+chartWidth || p.mouseX<100) {
        p.fill(0, 255, 0);
      }
    } else {
      if ( p.mouseX>=100+getMaxIndex(candidatePopularity)*candidateX && p.mouseX<=chartWidth+100 || p.mouseX>100+chartWidth || p.mouseX<100) {
        p.fill(0, 255, 0);
      }
    }
    p.ellipse(100+getMaxIndex(candidatePopularity)*candidateX, 100+chartHeight-candidateMax*chartHeight/100, 10, 10);


    //Same for Min

    p.fill(255, 0, 0, 100);
    if (this.name.equals("Trump")) {
      if (p.mouseX>=100+getMinIndex(candidatePopularity)*candidateX && p.mouseX<=chartWidth+100 || p.mouseX>100+chartWidth || p.mouseX<100) {
        p.fill(255, 0, 0);
      }
    } else {
      if (p.mouseX>=100+getMinIndex(candidatePopularity)*candidateX && p.mouseX<=chartWidth+100 || p.mouseX>100+chartWidth || p.mouseX<100) {
        p.fill(255, 0, 0);
      }
    }
    p.ellipse(100+getMinIndex(candidatePopularity)*candidateX, 100+chartHeight-candidateMin*chartHeight/100, 6, 6);

    //If the mouse passes a significant jump in the data, shortly describe what happened on that day

    p.textAlign(p.RIGHT, p.BOTTOM);

    //First clinton jump--label next to clinton at 28 points in
    if ((p.mouseX>=100+28*candidateX && p.mouseX<=chartWidth+100 || p.mouseX>100+chartWidth || p.mouseX<100) && this.name.equals("Clinton")) {
      p.fill(0);
      p.textSize(12);
      p.text("Clinton labels Trump Supporters Deplorable", 100+28*candidateX, 100+chartHeight-candidatePopularity[28]*chartHeight/100-3);
    }

    //First trump jump--label next to trump point at 43 points in
    if ((p.mouseX>=100+43*candidateX && p.mouseX<=chartWidth+100 || p.mouseX>100+chartWidth || p.mouseX<100) && this.name.equals("Trump")) {
      p.fill(0);
      p.textSize(12);
      p.text("Post First Debate", 100+43*candidateX, 100+chartHeight-candidatePopularity[43]*chartHeight/100-3);
    }

    //Second trump jump--label next to trump point at 54 points in
    if ((p.mouseX>=100+54*candidateX && p.mouseX<=chartWidth+100 || p.mouseX>100+chartWidth || p.mouseX<100) && this.name.equals("Trump")) {
      p.fill(0);
      p.text("Day After Clinton Leaks Controversial Video", 100+54*candidateX, 100+chartHeight-candidatePopularity[54]*chartHeight/100-3);
    }

    //second debate--label next to trump point at 56 points in
    if ((p.mouseX>=100+56*candidateX && p.mouseX<=chartWidth+100 || p.mouseX>100+chartWidth || p.mouseX<100) && this.name.equals("Trump")) {
      p.textAlign(p.LEFT, p.BOTTOM);
      p.fill(0);
      p.textSize(12);
      p.text("Post Second Debate", 100+56*candidateX, 100+chartHeight-candidatePopularity[56]*chartHeight/100+5);
    }

    //third debate--label next to trump point at 56 points in
    if ((p.mouseX>=100+66*candidateX && p.mouseX<=chartWidth+100 || p.mouseX>100+chartWidth || p.mouseX<100) && this.name.equals("Trump")) {
      p.fill(0);
      p.textSize(12);
      p.text("Post Third Debate", 100+66*candidateX, 100+chartHeight-candidatePopularity[66]*chartHeight/100+5);
    }
  }


  //function that draws a bar chart that visualizes the debate transcripts data
  // @param chartWidth the width of the chart
  //@param chartHeight the height of the chart
  //@param xLocation the xLocation of the chart
  //@param yLocation the yLocation of the chart
  //@param p PApplet passed from from tab



  public void drawBarChart(int chartWidth, int chartHeight, int xLocation, int yLocation, PApplet p)
  {
    //Intialize variables that will be used when drawing the Chart
    int numofCandidateWords1=this.transcripts[0].getnumofCandidateWords();
    int numofCandidateWords2=this.transcripts[1].getnumofCandidateWords();
    int numofCandidateWords3=this.transcripts[2].getnumofCandidateWords();
    int numofModeratorStatements1=this.transcripts[0].getnumofModeratorStatements();
    int numofModeratorStatements2=this.transcripts[1].getnumofModeratorStatements();
    int numofModeratorStatements3=this.transcripts[2].getnumofModeratorStatements();
    int numofPeopleStatements=this.transcripts[1].getnumofPeopleStatements();
    int numberofLinesY=10;
    float chartheight_spacing=chartHeight/numberofLinesY;
    int barWidth=80;
    int barSeparation=120;
    int bar1pos=chartWidth/4;
    int bar2pos=2*bar1pos;
    int bar3pos=3*bar1pos;
    boolean inbar1=(p.mouseX>100+bar1pos-barSeparation && p.mouseX<100+bar1pos-barSeparation+barWidth && p.mouseY<100+chartHeight && p.mouseY>100+chartHeight-(numofModeratorStatements1*chartHeight/5500));
    boolean inbar2=(p.mouseX>100+bar1pos-barSeparation+barWidth && p.mouseX<100+bar1pos-barSeparation+2*barWidth && p.mouseY<100+chartHeight && p.mouseY>100+chartHeight-(numofCandidateWords1*chartHeight/5500));
    boolean inbar3=(p.mouseX>100+bar1pos-barSeparation+2*barWidth && p.mouseX<100+bar1pos-barSeparation+3*barWidth && p.mouseY<100+chartHeight && p.mouseY>100+chartHeight-(numofCandidateWords1*chartHeight/5500));
    boolean inbar4=(p.mouseX>100+bar2pos-barSeparation && p.mouseX<100+bar2pos-barSeparation+barWidth && p.mouseY<100+chartHeight && p.mouseY>100+chartHeight-(numofModeratorStatements2+numofPeopleStatements)*chartHeight/5500);
    boolean inbar5=(p.mouseX>100+bar2pos-barSeparation+barWidth && p.mouseX<100+bar2pos-barSeparation+2*barWidth && p.mouseY<100+chartHeight && p.mouseY>100+chartHeight-numofCandidateWords2*chartHeight/5500);
    boolean inbar6=(p.mouseX>100+bar2pos-barSeparation+2*barWidth && p.mouseX<100+bar2pos-barSeparation+3*barWidth && p.mouseY<100+chartHeight && p.mouseY>100+chartHeight-(numofCandidateWords2*chartHeight/5500));
    boolean inbar7=(p.mouseX>100+3*bar1pos-barSeparation && p.mouseX<100+3*bar1pos-barSeparation+barWidth && p.mouseY<100+chartHeight && p.mouseY>100+chartHeight-(numofModeratorStatements3*chartHeight/5500));
    boolean inbar8=(p.mouseX>100+3*bar1pos-barSeparation+barWidth && p.mouseX<100+3*bar1pos-barSeparation+2*barWidth && p.mouseY<100+chartHeight && p.mouseY>100+chartHeight-(numofCandidateWords3*chartHeight/5500));
    boolean inbar9=(p.mouseX>100+3*bar1pos-barSeparation+2*barWidth && p.mouseX<100+3*bar1pos-barSeparation+3*barWidth && p.mouseY<100+chartHeight && p.mouseY>100+chartHeight-(numofCandidateWords3*chartHeight/5500));
    PImage Lester=p.loadImage("Lester.jpg");
    PImage Trump=p.loadImage("Trump.jpg");
    PImage Clinton=p.loadImage("Clinton.jpg");
    PImage kenBone=p.loadImage("kenBone.jpg");
    PImage Cooper=p.loadImage("Cooper.jpg");
    PImage Raddatz=p.loadImage("Raddatz.jpg");
    PImage Wallace=p.loadImage("Wallace.jpg");


    p.noFill();
    p.stroke(0);
    p.strokeWeight(0);
    p.rectMode(p.CENTER);
    p.rect(xLocation, yLocation, chartWidth, chartHeight);

    // draw chart grid lines using loop--only thing changing is the y values of a line
    for (int n=0; n<=numberofLinesY; n++) {
      //Intial y value for chart is 100 because chart will have 100 pixels of space on each side
      p.line(100, 100+(n)*chartheight_spacing, chartWidth+100, 100+(n)*chartheight_spacing);
    }
    //Make the title and axis lables
    p.textAlign(p.CENTER, p.TOP);
    p.fill(0);

    p.textSize(18);
    //x axis title
    p.text("Debate", p.width/2, p.height-50);

    //x axis label
    p.text("1st", 100+bar1pos, 100+chartHeight+10);
    p.text("2nd", 100+bar2pos, 100+chartHeight+10);
    p.text("3rd", 100+3*bar1pos, 100+chartHeight+10);


    //y axis label
    p.textAlign(p.CENTER);
    p.text("0", 90, chartHeight+100);
    p.textAlign(p.CENTER, p.CENTER);
    p.text("550", 80, 100+9*chartheight_spacing);
    p.text("1100", 80, 100+8*chartheight_spacing);
    p.text("1650", 80, 100+7*chartheight_spacing);
    p.text("2200", 80, 100+6*chartheight_spacing);
    p.text("2750", 80, 100+5*chartheight_spacing);
    p.text("3300", 80, 100+4*chartheight_spacing);
    p.text("3850", 80, 100+3*chartheight_spacing);
    p.text("4400", 80, 100+2*chartheight_spacing);
    p.text("5950", 80, 100+chartheight_spacing);
    p.text("5500", 80, 100);
    p.textSize(14);
    //y axis title
    p.textSize(13);
    p.text("#\n of\n State-\n ments\n or\n Words", 35, p.height/2);

    //Chart Title
    p.textSize(20);
    p.text("Candidates' Number of Words vs. Number of Moderator/People Statements", p.width/2, 50);

    //Put in The Moderator Bar Graph for the three debates and trump, clinton, too
    //if the mouse is in the bar, add a strokeweight, labels at top, and picture icon by the mouse.
    p.fill(147, 112, 219, 200);
    p.stroke(0);
    //first debate
    p.rectMode(p.CORNER);
    if (inbar1) {
      p.strokeWeight(2);
      p.textSize(12);
      p.textAlign(p.CENTER, p.CENTER);
      p.fill(0);
      //bar labels if mouse is over the bar
      p.text("Moderator\nStatements", 100+bar1pos-barSeparation+barWidth/2, 50+chartHeight-(numofModeratorStatements1*chartHeight/5500));
      p.text(numofModeratorStatements1, 100+bar1pos-barSeparation+barWidth/2, 80+chartHeight-(numofModeratorStatements1*chartHeight/5500));
      p.image(Lester, p.mouseX, p.mouseY);
    } else {
      p.strokeWeight(1);
    }
    //Moderator bar1
    //y values will start at the bottom of the chart and move up by the scale of the chart--1 unit is the chartheight/5500
    p.fill(147, 112, 219, 200);
    p.rect(100+bar1pos-barSeparation, 100+chartHeight-(numofModeratorStatements1*chartHeight/5500), barWidth, numofModeratorStatements1*chartHeight/5500);


    //Different bars for trump or clinton depending which candidate is created called
    if (this.name.equals("Trump")) {
      if (inbar2) {
        p.strokeWeight(2);
        p.textSize(12);
        p.textAlign(p.CENTER, p.CENTER);
        p.fill(0);
        p.text("Trump\nWords", 100+bar1pos, 50+chartHeight-(numofCandidateWords1*chartHeight/5500));
        p.text(numofCandidateWords1, 100+bar1pos, 80+chartHeight-(numofCandidateWords1*chartHeight/5500));
      } else {
        p.strokeWeight(1);
      }
      p.fill(255, 0, 0, 200);
      //trump bar1
      p.rect(100+bar1pos-barSeparation+barWidth, 100+chartHeight-(numofCandidateWords1*chartHeight/5500), barWidth, numofCandidateWords1*chartHeight/5500);
    } else {
      if (inbar3) {
        p.strokeWeight(2);
        p.textSize(12);
        p.textAlign(p.CENTER, p.CENTER);
        p.fill(0);
        p.text("Clinton\nWords", 100+bar1pos+barWidth, 50+chartHeight-(numofCandidateWords1*chartHeight/5500));
        p.text(numofCandidateWords1, 100+bar1pos+barWidth, 80+chartHeight-(numofCandidateWords1*chartHeight/5500));
      } else {
        p.strokeWeight(1);
      }
      p.fill(0, 0, 255, 200);
      //clinton bar1
      p.rect(100+bar1pos-barSeparation+2*barWidth, 100+chartHeight-(numofCandidateWords1*chartHeight/5500), barWidth, numofCandidateWords1*chartHeight/5500);
    }

    //Moderator bar2
    p.fill(147, 112, 219, 200);
    if (inbar4) {
      p.strokeWeight(2);
      p.textSize(12);
      p.textAlign(p.CENTER, p.CENTER);
      p.fill(0);
      p.text("Moderator\nStatements+\nPeople\nStatements", 100+bar2pos-barSeparation+barWidth/2, 20+chartHeight-(numofModeratorStatements2*chartHeight/5500));
      p.text(numofModeratorStatements2, 100+bar2pos-barSeparation+barWidth/2, 80+chartHeight-(numofModeratorStatements2*chartHeight/5500));
      //Image appears if mouse is in the bar
      p.image(kenBone, p.mouseX, p.mouseY);
      p.image(Raddatz, p.mouseX+kenBone.width, p.mouseY);
      p.image(Cooper, p.mouseX-Cooper.width, p.mouseY);
    } else {
      p.strokeWeight(1);
    }
    p.fill(147, 112, 219, 200);
    p.rect(100+bar2pos-barSeparation, 100+chartHeight-(numofModeratorStatements2+numofPeopleStatements)*chartHeight/5500, barWidth, (numofModeratorStatements2+numofPeopleStatements)*chartHeight/5500);

    //Different bars for trump or clinton depending which is called
    if (this.name.equals("Trump")) {
      if (inbar5) {
        p.strokeWeight(2);
        p.textSize(12);
        p.textAlign(p.CENTER, p.CENTER);
        p.fill(0);
        //bar label
        p.text("Trump\nWords", 100+bar2pos, 50+chartHeight-(numofCandidateWords2*chartHeight/5500));
        p.text(numofCandidateWords2, 100+bar2pos, 80+chartHeight-(numofCandidateWords2*chartHeight/5500));
      } else {
        p.strokeWeight(1);
      }
      p.fill(255, 0, 0, 200);
      //trump bar2
      p.rect(100+bar2pos-barSeparation+barWidth, 100+chartHeight-numofCandidateWords2*chartHeight/5500, barWidth, numofCandidateWords2*chartHeight/5500);
    } else {
      if (inbar6) {
        p.strokeWeight(2);
        p.textSize(12);
        p.textAlign(p.CENTER, p.CENTER);
        p.fill(0);
        //bar label
        p.text("Clinton\nWords", 100+bar2pos+barWidth, 50+chartHeight-(numofCandidateWords2*chartHeight/5500));
        p.text(numofCandidateWords2, 100+bar2pos+barWidth, 80+chartHeight-(numofCandidateWords2*chartHeight/5500));
      } else {
        p.strokeWeight(1);
      }
      p.fill(0, 0, 255, 200);
      //clinton bar2
      p.rect(100+bar2pos-barSeparation+2*barWidth, 100+chartHeight-(numofCandidateWords2*chartHeight/5500), barWidth, numofCandidateWords2*chartHeight/5500);
    }

    //Moderator bar3
    p.fill(147, 112, 219, 200);
    if (inbar7) {
      p.strokeWeight(2);
      p.textSize(12);
      p.textAlign(p.CENTER, p.CENTER);
      p.fill(0);
      //labels
      p.text("Moderator\nStatements", 100+3*bar1pos-barSeparation+barWidth/2, 50+chartHeight-(numofModeratorStatements3*chartHeight/5500));
      p.text(numofModeratorStatements3, 100+3*bar1pos-barSeparation+barWidth/2, 80+chartHeight-(numofModeratorStatements3*chartHeight/5500));
      p.image(Wallace, p.mouseX, p.mouseY);
    } else {
      p.strokeWeight(1);
    }
    p.fill(147, 112, 219, 200);
    p.rect(100+3*bar1pos-barSeparation, 100+chartHeight-numofModeratorStatements3*chartHeight/5500, barWidth, numofModeratorStatements3*chartHeight/5500);



    //Different bars for trump or clinton depending which is called
    if (this.name.equals("Trump")) {
      if (inbar8) {
        p.strokeWeight(2);
        p.textSize(12);
        p.textAlign(p.CENTER, p.CENTER);
        p.fill(0);
        //labels
        p.text("Trump\nWords", 100+3*bar1pos, 50+chartHeight-(numofCandidateWords3*chartHeight/5500));
        p.text(numofCandidateWords3, 100+3*bar1pos, 80+chartHeight-(numofCandidateWords3*chartHeight/5500));
      } else {
        p.strokeWeight(1);
      }
      p.fill(255, 0, 0, 200);
      //trump bar3
      p.rect(100+3*bar1pos-barSeparation+barWidth, 100+chartHeight-numofCandidateWords3*chartHeight/5500, barWidth, numofCandidateWords3*chartHeight/5500);
    } else {
      if (inbar9) {
        p.strokeWeight(2);
        p.textSize(12);
        p.textAlign(p.CENTER, p.CENTER);
        p.fill(0);
        //labels
        p.text("Clinton\nWords", 100+3*bar1pos+barWidth, 50+chartHeight-(numofCandidateWords3*chartHeight/5500));
        p.text(numofCandidateWords3, 100+3*bar1pos+barWidth, 80+chartHeight-(numofCandidateWords3*chartHeight/5500));
      } else {
        p.strokeWeight(1);
      }
      p.fill(0, 0, 255, 200);
      //clinton bar3
      p.rect(100+3*bar1pos-barSeparation+2*barWidth, 100+chartHeight-(numofCandidateWords3*chartHeight/5500), barWidth, numofCandidateWords3*chartHeight/5500);
    }
  


    //Display images of trump or clinton if mouse is in respective bar
    if (inbar2 || inbar5 || inbar8) {
      p.image(Trump, p.mouseX, p.mouseY);
    }
    if ( inbar3 || inbar6 || inbar9) {
      p.image(Clinton, p.mouseX, p.mouseY);
    }

    //instructions
    p.textSize(16);
    p.text("Click on a candidate's bar to see their words most said for that debate", p.width-300, p.height-50);
  }





  /*A function which given an int array returns the maximum value of the array.
   @param a the integer array
   @return the max value in the array
   */

  public int maxVal(int [] a) {
    int max=a[0];
    int i;
    for ( i=1; i<a.length; i++) {
      if (a[i]>max) {
        max=a[i];
      }
    }
    return max;
  }

  /*A function which given an int array returns the min value of the array.
   @param a the integer array
   @return the min value in the array
   */

  public int minVal(int [] a) {
    int min=a[0];
    for (int i=1; i<a.length; i++) {
      if (a[i]<min) {
        min=a[i];
      }
    }
    return min;
  }
  /*A function which given an int array returns the index value of the Max
   @param a the int array
   @return the index at which the max occurrs
   */
  public int getMaxIndex(int [] a) {
    int max=a[0];
    int j=0;
    for (int i=1; i<a.length; i++) {
      if (a[i]>max) {
        max=a[i];
        j=i;
      }
    }
    return j;
  }
  /* A function which given an int array returns the index value of the Min
   @param a the int array
   @return the index at which the min value occurs
   */
  public int getMinIndex(int [] a) {
    int min=a[0];
    int j=0;
    for (int i=1; i<a.length; i++) {
      if (a[i]<min) {
        min=a[i];
        j=i;
      }
    }
    return j;
  }


  /*A function which given an int array and an int, finds the int in the array and returns its position. 
   If the int is not in the array it should return -1. 
   If there are multiple instances of the int in the array, just return the last one.
   @param a the integer array
   @param b the integer that we want to find
   @return the position at which the integer is in the array
   */

  public int arrayPos(int [] a, int b) {
    int position=-1;
    for (int i=a.length-1; i>=0; i--) {
      if (a[i]==b) {
        position=i;
        return position;
      }
    }
    return -1;
  }


  /*A function which given an array returns an array of ints which is shorter by 1, and is the difference between every two array entries
   @param a the interger array
   @return the array of differences
   .*/
  public int [] difference(int [] a) {
    int [] b= new int [a.length-1];
    for (int i=0; i<a.length-1; i++) {
      b[i]=a[i+1]-a[i];
    }
    return (b);
  }

  //A toString method
  public String toString() 
  {
    return("This candidate has " + this.numoftweets + " tweets, " + this.numofpolls + " poll analyses, " + this.numoftranscripts + " debates, and " + this.popularityindex + " popularity analyses.");
  }

  //Get methods for all instance variable 

  public int getnumoftweets() {
    return this.numoftweets;
  }
  public int getnumofpolls() {
    return this.numofpolls;
  }
  public int getnumoftranscripts() {
    return this.numoftranscripts;
  }
  public int getpopularityindex() {
    return this.popularityindex;
  }
  public Poll [] getPollArray() {
    return this.polls;
  }
  public Debate_Transcript [] getTranscriptArray() {
    return this.transcripts;
  }
  public Tweet [] getTweetArray() {
    return this.tweets;
  }
  public Search_Popularity [] getPopularityArray() {
    return this.popularity;
  }
  public Debate_Transcript getTranscript(int i) {
    //if user puts in 0 or 1 for i, return index value 0.  Apply for all getmethods for the array instance variables.
    if (i==0) {
      i=1;
    }
    return this.transcripts[i-1];
  }
  //get methods for specific index values
  public Tweet getTweet(int i) {
    if (i==0) {
      i=1;
    }
    return this.tweets[i-1];
  }
  public Poll getPoll(int i) {
    if (i==0) {
      i=1;
    }
    return this.polls[i-1];
  }
  public Search_Popularity getPopularity(int i) {
    if (i==0) {
      i=1;
    }
    return this.popularity[i-1];
  }
}