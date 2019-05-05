/************************************************************
 *                                                          *
 *  CSCI 470/502          Assignment 8           Fall 2018  *                                             
 *                                                          *
 *  Programmer(s): Eric Davis (z136652),                    *  
 *                 Michael Simon (z1838011),                *
 *                 Jordan Park (z1816715)                   *
 *  Section:    1                                           *
 *                                                          *
 *  Date Due:   11:59 PM on Wednesday, 11/28/2018           *                           
 *                                                          *
 *  Purpose:    MileRedeemer trigger app.                   *
 *                                                          *
 *                                                          * 
 ***********************************************************/
import javax.swing.JFrame ;
import java.io.IOException;

public class MileRedeemerTest
{
  public static void main(String[] args) throws IOException
  {
    MileRedemptionApp mileRedemptionApp = new MileRedemptionApp();
    mileRedemptionApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mileRedemptionApp.setSize(825,400);
    mileRedemptionApp.setVisible(true);
  }
}