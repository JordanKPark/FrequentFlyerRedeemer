/************************************************************
 *                                                          *
 *  CSCI 470/502          Assignment 8           Fall 2018  *                                             
 *                                                          *
 *  Programmer(s): Eric Davis (z136652),                    *  
 *                 Michael Simon (z1838011)                 *
 *                 Jordan Park (z1816715)                   *
 *  Section:    1                                           *
 *                                                          *
 *  Date Due:   11:59 PM on Wednesday, 11/28/2018           *                          
 *                                                          *
 *  Purpose:    Class encapsulating destination information.*
 *                                                          *
 *                                                          * 
 ***********************************************************/
import java.util.Arrays ;

public class Destination
{
  //Define variables for use in calculations
  private String city ;
  private int normMile ;
  private int freqMile ;
  private int addtlMile ;
  
  //Declare variable to read string range of months for later breakout
  private String readMonth ;
  private int beginMonth ;
  private int endMonth ;
  
/**************************************************************
*                                                             *
* Destination initiliazes an object of destination class.     *
*                                                             *
***************************************************************/
  
  public Destination ( String newCity , String newNormMile , String newFreqMile , String newAddtlMile , String newReadMonth )
  {
    //scanner is sending all values in as strings so need to convert
    city = newCity ;
    normMile = Integer.parseInt(newNormMile) ;
    freqMile = Integer.parseInt(newFreqMile) ;
    addtlMile = Integer.parseInt(newAddtlMile) ;
    readMonth = newReadMonth ;
    
    String[] Months = new String[2] ;
    
    //how do i split up months?
    Months = readMonth.split("-") ; //split up string into array based on a "String regex"
    
    beginMonth = Integer.parseInt(Months[0]) ; //grab first month from array; need to convert from string to int
    endMonth = Integer.parseInt(Months[1]) ; //grab second month from array; need to convert from string to int
  }
  
/**************************************************************
*                                                             *
*  getCity retrieves city variable.                           *
*                                                             *
***************************************************************/
  
  public String getCity ()
  {
    return city ;
  }

/**************************************************************
*                                                             *
* getNormMile retrieves normMile variable.                    *
*                                                             *
***************************************************************/  
  
  public int getNormMile ()
  {
    return normMile ;
  }
  
/**************************************************************
*                                                             *
* getFreqMile retrieves freqMile variable.                    *
*                                                             *
***************************************************************/
  
  public int getFreqMile ()
  {
    return freqMile ;
  }
  
/**************************************************************
*                                                             *
* getAddtlMile retrieves addtlMile variable.                  *
*                                                             *
***************************************************************/
  
  public int getAddtlMile ()
  {
    return addtlMile ;
  }

/**************************************************************
*                                                             *
* getBeginMonth retrieves beginMonth variable.                *
*                                                             *
***************************************************************/
  
  public int getBeginMonth ()
  {
    return beginMonth ;
  }

/**************************************************************
*                                                             *
* getEndMonth retrieves endMonth variable.                    *
*                                                             *
***************************************************************/
  
  public int getEndMonth ()
  {
    return endMonth ;
  }
  
/**************************************************************
*                                                             *
*  setCity populates city variable.                           *
*                                                             *
***************************************************************/
  
  public void setCity (String newCity)
  {
    city = newCity ;
  }
  
/**************************************************************
*                                                             *
*  setFreqMile populates freqMile variable.                   *
*                                                             *
***************************************************************/
  
  public void setFreqMile (int newFreqMile)
  {
    freqMile = newFreqMile ;
  }
  
/**************************************************************
*                                                             *
* setAddtlMile populates addtlMile variable.                  *
*                                                             *
***************************************************************/
  
  public void setAddtlMile (int newAddtlMile)
  {
    addtlMile = newAddtlMile ;
  }
  
/**************************************************************
*                                                             *
* setBeginMonth populates beginMonth variable.                *
*                                                             *
***************************************************************/
  
  public void setBeginMonth (int newBeginMonth)
  {
    beginMonth = newBeginMonth ;
  }
  
/**************************************************************
*                                                             *
* setEndMonth populates endMonth variable.                    *
*                                                             *
***************************************************************/
  
  public void setEndMonth (int newEndMonth)
  {
    endMonth = newEndMonth ;
  }
}