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
 *  Purpose:    Mileage redeemer logic.                     *
 *                                                          *
 *                                                          * 
 ***********************************************************/
import java.util.ArrayList ;
import java.util.Arrays ;
import java.util.Scanner ;
import java.util.Comparator ;

public class MileRedeemer
{
  //ArrayList of destination options
  ArrayList<Destination> destinations = new ArrayList<Destination>() ;
  
  //Track the number of remainingMiles for an object caller
  static int remainingMiles = 0 ;

/**************************************************************
*                                                             *
* getRemainingMiles retrieves remainingMiles value.           *
*                                                             *
***************************************************************/
  
  public int getRemainingMiles()
  {
    return remainingMiles ;
  }
  
/**************************************************************
*                                                             *
* readDestinations reads input file to populate dest array.   *
*                                                             *
***************************************************************/
  
  public void readDestinations(Scanner fileScanner)
  {
    while(fileScanner.hasNextLine() )
    {
      //extract full line; i.e. one destination
      String extractedLine = fileScanner.nextLine() ;
      
      //split string into an array based on ; delimnator
      String[] extractedArray = extractedLine.split(";") ;
      
      //create destination object
      if(extractedArray.length == 5)
      {
      Destination newDest = new Destination( extractedArray[0] , extractedArray[1] , extractedArray[2] ,
                                             extractedArray[3] , extractedArray[4] ) ;
      
      
      //add to destinations array list
      destinations.add(newDest) ;
      }
      
    }
  }
  
/**************************************************************
*                                                             *
* getCityNames retrieves all avaliable cities for travel and  *
* displays.                                                   *
*                                                             *
***************************************************************/
  
  public String[] getCityNames()
  {
    //create array of destination objects from ArrayList for looping
    Destination[] destinationArray = new Destination[destinations.size()] ;
    destinationArray = destinations.toArray(destinationArray) ;    
    
    //create array of destination cities
    String[] destinationCities = new String[destinations.size()] ;
    
    //Populate city array by looping through created array
    for (int destCounter = 0 ; destCounter < destinationArray.length ; destCounter++ )
    {
      destinationCities[destCounter] = destinationArray[destCounter].getCity() ;
    }
    
    //standard sort array
    Arrays.sort(destinationCities);
    
    //return array of cities avaliable
    return destinationCities ;
  }
  
/**************************************************************
*                                                             *
* findDestination retrieves all info for a city               *
*                                                             *
***************************************************************/
  
  public Destination findDestination( String cityName)
  {
    
    Destination destination = null ;
    
    //Populate city array by looping through created array
    for (Destination destCounter : destinations )
    {
      
      if(cityName.equals(destCounter.getCity()))
      {
        destination = destCounter;
      }
    }
    System.out.println(destination);
    //return array of cities avaliable
    return destination ;
  }
  
/**************************************************************
*                                                             *
* redeemMiles redeems frequent flyer miles to travel most     *
* distance.                                                   *
*                                                             *
***************************************************************/
  
  public String[] redeemMiles (int miles, int month )
  {
    remainingMiles = miles ;
    
    //create array of destination objects from ArrayList for looping
    Destination[] destinationArray = new Destination[destinations.size()] ;
    destinationArray = destinations.toArray(destinationArray) ;    
    
    
    //sort
    Arrays.sort( destinationArray, new MileageComparator() ); 
    
    //Create string to list matches
    String[] matchesArray = new String[destinations.size()];
    
    //Create counter for list matches
    int resultsCounter = 0 ;
    
    //Check for avaliable flights
    for (int destCounter = 0 ; destCounter < destinationArray.length ; destCounter++ )
    {
      //if between month range and enough miles for frequent mileage
      if(destinationArray[destCounter].getBeginMonth() <= month && 
         destinationArray[destCounter].getEndMonth() >= month && 
         remainingMiles >= destinationArray[destCounter].getFreqMile() )
      {
        //update remaining miles and store city traveling to
        remainingMiles -= destinationArray[destCounter].getFreqMile() ;
        matchesArray[resultsCounter] = "* A trip to " + destinationArray[destCounter].getCity() + " in Economy Class";
      }
      
      //else, can go by redeeming for normal rate?
      else if ( remainingMiles >= destinationArray[destCounter].getNormMile() )
      {
        //update remaining miles and store city traveling to
        remainingMiles -= destinationArray[destCounter].getNormMile() ;
        matchesArray[resultsCounter] = "* A trip to " + destinationArray[destCounter].getCity() + " in Economy Class";
      }
      
      resultsCounter++ ;
      
    }
    
    
    //Check for avaiable upgrades
    for ( int j = 0 ; j < ( resultsCounter - 1 ) ; j++ )
    {
      //is the array item not null (i.e. traveled city) and remaining miles > then upgrade fee?
      if ( destinationArray[j] != null && remainingMiles >= destinationArray[j].getAddtlMile() )
      {
        //update remaining miles and store city traveling to with first class
        remainingMiles -= destinationArray[j].getAddtlMile() ;
        matchesArray[j] = "* A trip to " + destinationArray[j].getCity() + " in First Class";
      }
    }
    
    //return cities travelled to
    return matchesArray ;
  }
  
/**************************************************************
*                                                             *
* Class implementation to redefine how a Destination object   *
* is sorted.                                                  *
*                                                             *
***************************************************************/
  
  public class MileageComparator implements Comparator<Destination> 
  {
    @Override
    public int compare(Destination d1, Destination d2) 
    {
      return (d2.getNormMile() - d1.getNormMile());
    }
  }
  
}