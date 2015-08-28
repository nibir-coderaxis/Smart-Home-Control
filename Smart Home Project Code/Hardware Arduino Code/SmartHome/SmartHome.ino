const int L1 = 12;
const int L2 = 10;
const int L3 = 8;

const int L1Out = 11;
const int L2Out = 9;
const int L3Out = 7;

String inputString = "";         // a string to hold incoming data
boolean stringComplete = false;  // whether the string is complete

void setup() {
  // put your setup code here, to run once:
  // initialize serial:
  Serial.begin(9600);
   inputString.reserve(200);
  // make the pins outputs:
  pinMode(L1, OUTPUT); 
  pinMode(L2, OUTPUT); 
  pinMode(L3, OUTPUT); 
  
  pinMode(L1Out, INPUT); 
  pinMode(L2Out, INPUT); 
  pinMode(L3Out, INPUT);


}

void loop() {
  delay(200);
   serialEvent();
  // print the string when a newline arrives:
  if (stringComplete) {
    SwitchingLight();
    //Serial.println(inputString); 
    // clear the string:
    inputString = "";
    stringComplete = false;
  }
  
  sendStatus();

}

void SwitchingLight(){
    if(inputString=="Light1On")
    {
      digitalWrite(L1, HIGH);
    }
    else if(inputString=="Light1Off")
    {
      digitalWrite(L1, LOW);
    }
    else if(inputString=="Light2On")
    {
      digitalWrite(L2, HIGH);
    }
    else if(inputString=="Light2Off")
    {
      digitalWrite(L2, LOW);
    }
    else if(inputString=="Light3On")
    {
      digitalWrite(L3, HIGH);
    }
    else if(inputString=="Light3Off")
    {
      digitalWrite(L3, LOW);
    }
}



void sendStatus()
{
  if(digitalRead(L1Out)==1)
  {
    Serial.print(1);
  }
  else if(digitalRead(L1Out)==0)
  {
    Serial.print(4);
  }
  
  if(digitalRead(L2Out)==1)
  {
    Serial.print(2);
  }
  else if(digitalRead(L2Out)==0)
  {
    Serial.print(5);
  }
  
   if(digitalRead(L3Out)==1)
  {
    Serial.print(3);
  }
  else if(digitalRead(L3Out)==0)
  {
    Serial.print(6);
  }
  
}




void serialEvent() {
  while (Serial.available()) {
    // get the new byte:
    char inChar = (char)Serial.read(); 
    // add it to the inputString:
    inputString += inChar;
    // if the incoming character is a newline, set a flag
    // so the main loop can do something about it:
    if (inChar == '\0') {
      stringComplete = true;
    } 
  }
}


