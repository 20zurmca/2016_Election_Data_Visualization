

//declare global objects
Candidate Trump;
Candidate Clinton;
Button lineChart1;
Button lineChart;
Button barChart;
Button barChart1;

//declare boolean variables that define the state the draw is in.  Clicking buttons will change the display.  Initial state is state 0.
boolean state0=true;
boolean state1=false;
boolean state2=false;
boolean state3=false;
boolean state4=false;
boolean state5=false;
boolean state6=false;
boolean state7=false;
boolean state8=false;

void setup() {
  size(1300, 800);
  background(255);
  Trump=new Candidate("Trump", "TrumpTweets.txt", "polling.csv", "Sep_26_Hofstra.txt", "Oct_9_Missouri.txt", "Oct_19_LasVegas.txt", "trump_hillary_search_popularity.csv", this);
  Clinton=new Candidate("Clinton", "ClintonTweets.txt", "polling.csv", "Sep_26_Hofstra.txt", "Oct_9_Missouri.txt", "Oct_19_LasVegas.txt", "trump_hillary_search_popularity.csv", this);
}
void draw()
{



  //Declares the buttons for all states except state 0
  lineChart=new Button(100, height-30, 175, 50, "Visualize Line Chart", this);
  barChart=new Button(300, height-30, 175, 50, "Visualize Bar Chart", this);

//initial state
  if (state0) {
    //Declares Buttons for home page and draws them
    lineChart1=new Button(300, height*2/3, 500, height/3, "Visualize Line Chart", this);
    barChart1=new Button(width-300, height*2/3, 500, height/3, "Visualize Bar Chart", this);
    background(255);
    textAlign(CENTER, CENTER);
    textSize(32);
    text("Cameron Zurmuhl and Emmett O'Toole", 650, 100);
    textSize(25);
    text("CS 105 Final Project", 650, 150);
    text("Click A Button To Begin", 650, 180);
    barChart1.drawButton(this);
    lineChart1.drawButton(this);
  }
  //If the first button is clicked draws the linechart display
  if (state1) {
    background(255);
    lineChart.drawButton(this);

    barChart.drawButton(this);
    Trump.drawLineChart(width-200, height-200, width/2, height/2, this);
    Clinton.drawLineChart(width-200, height-200, width/2, height/2, this);
    textSize(16);
    fill(0);
    text("Scroll over the chart to see popularity results for that day", width-50, height-50);
  }

  //If the second button is clicked draws the barchart display
  if (state2) {
    background(255);
    lineChart.drawButton(this);

    barChart.drawButton(this);
    Trump.drawBarChart(width-200, height-200, width/2, height/2, this);
    Clinton.drawBarChart(width-200, height-200, width/2, height/2, this);
  }
  //if the first trump bar is clicked, state is 3 and the word cloud for his words for that debate will appear
  if (state3) {
    background(255);

    Trump.wordCloud(this, 0);

    lineChart.drawButton(this);

    barChart.drawButton(this);
  }

  //if second trump bar is clicked, state is 4 and the word cloud for his words for that debate will appear
  if (state4) {
    background(255);

    Trump.wordCloud(this, 1);

    lineChart.drawButton(this);

    barChart.drawButton(this);
  }

  //if third trump bar is clicked, state is 5 and the word cloud for his words for that debate will appear
  if (state5) {
    background(255);

    Trump.wordCloud(this, 2);

    lineChart.drawButton(this);

    barChart.drawButton(this);
  }
  
  //if first clinton bar is clicked, state is 6 and the word cloud for her words for that debate will appear
  if (state6) {
    background(255);

    Clinton.wordCloud(this, 0);
    lineChart.drawButton(this);

    barChart.drawButton(this);
  }
  
  //if second clinton bar is clicked, state is 7 and the word cloud for her words for that debate will appear
  if (state7) {
    background(255);

    Clinton.wordCloud(this, 1);
    lineChart.drawButton(this);

    barChart.drawButton(this);
  }
  
  //if third clinton bar is clicked, state is 8 and the word cloud for her words for that debate will appear
  if (state8) {
    background(255);

    Clinton.wordCloud(this, 2);
    lineChart.drawButton(this);

    barChart.drawButton(this);
  }
}


void mouseClicked() {

  //variables that will be used for states 3-8
  int numofTrumpWords1=Trump.getTranscript(1).getnumofCandidateWords();
  int numofClintonWords1=Clinton.getTranscript(1).getnumofCandidateWords();
  int numofTrumpWords2=Trump.getTranscript(2).getnumofCandidateWords();
  int numofClintonWords2=Clinton.getTranscript(2).getnumofCandidateWords();
  int numofTrumpWords3=Trump.getTranscript(3).getnumofCandidateWords();
  int numofClintonWords3=Clinton.getTranscript(3).getnumofCandidateWords();
  int chartHeight=height-200;
  int chartWidth=width-200;
  int barWidth=80;
  int barSeparation=120;
  int bar1pos=chartWidth/4;
  int bar2pos=2*bar1pos;
  int bar3pos=3*bar1pos;

  boolean intrumpbar1=state2&&(mouseX>100+bar1pos-barSeparation+barWidth && mouseX<100+bar1pos-barSeparation+2*barWidth && mouseY<100+chartHeight && mouseY>100+chartHeight-(numofTrumpWords1*chartHeight/5500));
  boolean inclintonbar1=state2&&(mouseX>100+bar1pos-barSeparation+2*barWidth && mouseX<100+bar1pos-barSeparation+3*barWidth && mouseY<100+chartHeight && mouseY>100+chartHeight-(numofClintonWords1*chartHeight/5500));
  boolean intrumpbar2=state2&&(mouseX>100+bar2pos-barSeparation+barWidth && mouseX<100+bar2pos-barSeparation+2*barWidth && mouseY<100+chartHeight && mouseY>100+chartHeight-numofTrumpWords2*chartHeight/5500);
  boolean inclintonbar2=state2&&(mouseX>100+bar2pos-barSeparation+2*barWidth && mouseX<100+bar2pos-barSeparation+3*barWidth && mouseY<100+chartHeight && mouseY>100+chartHeight-(numofClintonWords2*chartHeight/5500));
  boolean inclintonbar3=state2&&(mouseX>100+3*bar1pos-barSeparation+barWidth && mouseX<100+3*bar1pos-barSeparation+2*barWidth && mouseY<100+chartHeight && mouseY>100+chartHeight-(numofTrumpWords3*chartHeight/5500));
  boolean intrumpbar3=state2&&(mouseX>100+3*bar1pos-barSeparation+2*barWidth && mouseX<100+3*bar1pos-barSeparation+3*barWidth && mouseY<100+chartHeight && mouseY>100+chartHeight-(numofClintonWords3*chartHeight/5500));

//if intitial lineChart1 button is pressed, change to state 1 
  if (lineChart1.getinButton()&& state0) {
    state0=false;
    state1=true;
    state2=false;
    state3=false;
    state4=false;
    state5=false;
    state6=false;
    state7=false;
    state8=false;
  }
//if initial barChart1 button is pressed, change to state 2
  if (barChart1.getinButton()&& state0) {
    state0=false;
    state1=false;
    state2=true;
    state3=false;
    state4=false;
    state5=false;
    state6=false;
    state7=false;
    state8=false;
  }
  
  //if the lineChart button in any state is pressed, change to state 1
  if (lineChart.getinButton()) {
    state0=false;
    state1=true;
    state2=false;
    state3=false;
    state4=false;
    state5=false;
    state6=false;
    state7=false;
    state8=false;
  }

//if the barChart button in any state is pressed, change to state 2
  if (barChart.getinButton()) {
    state0=false;
    state1=false;
    state2=true;
    state3=false;
    state4=false;
    state5=false;
    state6=false;
    state7=false;
    state8=false;
  }

//if the first trump bar is clicked in state2, change to state 3
  if (intrumpbar1) {
    state0=false;
    state1=false;
    state2=false;
    state3=true;
    state4=false;
    state5=false;
    state6=false;
    state7=false;
    state8=false;
  }
  
  //if the second trump bar is clicked in state 2, change to state 4
  if (intrumpbar2) {
    state0=false;
    state1=false;
    state2=false;
    state3=false;
    state4=true;
    state5=false;
    state6=false;
    state7=false;
    state8=false;
  }
  
  //if the third trump bar is clicked in state 2, change to state 5
  if (intrumpbar3) {
    state0=false;
    state1=false;
    state2=false;
    state3=false;
    state4=false;
    state5=true;
    state6=false;
    state7=false;
    state8=false;
  }
  
  //if the first clinton bar is clicked in state 2, change to state 6
  if (inclintonbar1) {
    state0=false;
    state1=false;
    state2=false;
    state3=false;
    state4=false;
    state5=false;
    state6=true;
    state7=false;
    state8=false;
  }
  
  //if the second clinton bar is clicked in state 2, change to state 7
  if (inclintonbar2) {
    state0=false;
    state1=false;
    state2=false;
    state3=false;
    state4=false;
    state5=false;
    state6=false;
    state7=true;
    state8=false;
  }
  
  //if the third clinton bar is clicked in state 2, change to state 8
  if (inclintonbar3) {
    state0=false;
    state1=false;
    state2=false;
    state3=false;
    state4=false;
    state5=false;
    state6=false;
    state7=false;
    state8=true;
  }
}