/************************************************************
 *                                                          *
 *  CSCI 470/502          Assignment 8           Fall 2018  *                                             
 *                                                          *
 *  Programmer(s): Eric Davis (z136652),                    *  
 *                 Michael Simon (z1838011)                 *
 *                 Jordan Park (z1816715)                   *
 *  Section:    1                                           *
 *                                                          *
 *  Date Due:   11:59 PM on Friday, 09/28/2018              *                          
 *                                                          *
 *  Purpose:    MileRedeemer app.                           *
 *                                                          *
 *                                                          * 
 ***********************************************************/
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

public class MileRedemptionApp extends JFrame
{
  //private final JFrame frame;
  private final JPanel destJPanel;
  private final JPanel milesJPanel;
  private final JPanel redeemJPanel;
  private final JPanel inputJPanel;
  private final JPanel redeemButtonJPanel;
  private final JPanel outputJPanel;
  private final JPanel labelJPanel1;
  private final JPanel labelJPanel2;
  private final JPanel labelJPanel3;
  private final JPanel textJPanel1;
  private final JPanel textJPanel2;
  private final JPanel textJPanel3;
  private final JPanel listJPanel;

  private final JTextField textField1;
  private final JTextField textField2;
  private final JTextField textField3;
  private final JTextField textField4;
  private final JTextField textField5;
  private final JTextField textField6;
  private final JTextField textField7;

  private final JLabel label1;
  private final JLabel label2;
  private final JLabel label3;
  private final JLabel label4;
  private final JLabel label5;
  private final JLabel label6;
  private final JLabel label7;
    
  private final JComboBox<String> monthJComboBox; // hold month names
  private static final String[] month =
        {"January","February","March","April","May","June",
         "July","August","September","October","November","December"};
  private static final int[] monthNum =
        {1,2,3,4,5,6,7,8,9,10,11,12};

  private final JTextArea output;
  private static String outputString; //for showing results of app
    
  private final JList<String> destinationCitiesJList;
  private static String[] avaliableCities = null;
    
  private final JButton redeemJButton;

  private final Container container; // frame container  
  Destination destinationInfo = null;
  private static MileRedeemer currentView = new MileRedeemer();
  
  public MileRedemptionApp() throws IOException
  {
    super("Mile Redemption App");
    
    //Retrieve file
    JFileChooser chooser = new JFileChooser();
    chooser.showDialog(null,"Please Select the Destination File Listing");
    chooser.setVisible(true);
    File fileInput = chooser.getSelectedFile();
    
    //Store file name and create new scanner to read
    Scanner fileScan = new Scanner(fileInput);
    
    //Process input file for destination lists
    currentView.readDestinations(fileScan);
    //Process for list of cities
    avaliableCities = currentView.getCityNames();
    
    //container layout
    container = getContentPane();
    container.setLayout(new BorderLayout());
    setResizable(false);

    // panels
    destJPanel = new JPanel(new BorderLayout());
    redeemJPanel = new JPanel(new BorderLayout());
    milesJPanel = new JPanel(new BorderLayout());
    inputJPanel = new JPanel(new BorderLayout());
    redeemButtonJPanel = new JPanel(new GridLayout(1,1));
    outputJPanel = new JPanel(new BorderLayout());
    labelJPanel1 = new JPanel(new GridLayout(4,1));
    labelJPanel2 = new JPanel(new GridLayout(2,1));
    labelJPanel3 = new JPanel(new GridLayout(1,1));
    textJPanel1 = new JPanel(new GridLayout(4,1));
    textJPanel2 = new JPanel(new GridLayout(2,1));
    textJPanel3 = new JPanel(new GridLayout(1,1));
    listJPanel = new JPanel(new BorderLayout());

    // set background color
    Color darkGreen = new Color(101,141,101);
    Color purplish = new Color(101,101,149);
    destJPanel.setBackground(darkGreen);
    redeemJPanel.setBackground(purplish);
    //add title
    destJPanel.setBorder(BorderFactory.createTitledBorder("Destinations"));
    redeemJPanel.setBorder(BorderFactory.createTitledBorder("Redeem Miles"));
        
    labelJPanel1.setOpaque(false);
    labelJPanel2.setOpaque(false);
    labelJPanel3.setOpaque(false);
    textJPanel1.setOpaque(false);
    textJPanel2.setOpaque(false);
    textJPanel3.setOpaque(false);
    milesJPanel.setOpaque(false);
    inputJPanel.setOpaque(false);
    outputJPanel.setOpaque(false);
    redeemButtonJPanel.setOpaque(false);

    // label
    label1 = new JLabel("Normal miles");
    label2 = new JLabel("Supersaver miles");
    label3 = new JLabel("Upgrade cost");
    label4 = new JLabel("Supersaver dates");
    label5 = new JLabel("Enter your miles");
    label6 = new JLabel("Select the month of departure");
    label7 = new JLabel("Your remaining miles");

    labelJPanel1.add(label1);
    labelJPanel1.add(label2);
    labelJPanel1.add(label3);
    labelJPanel1.add(label4);

    labelJPanel2.add(label5);
    labelJPanel2.add(label6);

    labelJPanel3.add(label7);

    //combo box
    monthJComboBox = new JComboBox<String>(month);
    monthJComboBox.setMaximumRowCount(12);

    // text field
    textField1 = new JTextField(15);
    textField2 = new JTextField(15);
    textField3 = new JTextField(15);
    textField4 = new JTextField(15);
    textField5 = new JTextField(15);
    textField6 = new JTextField(15);
    textField7 = new JTextField(15);

    textField1.setText("");
    textField1.setEditable(false);
    textField2.setText("");
    textField2.setEditable(false);
    textField3.setText("");
    textField3.setEditable(false);
    textField4.setText("");
    textField4.setEditable(false);

    textJPanel1.add(textField1);
    textJPanel1.add(textField2);
    textJPanel1.add(textField3);
    textJPanel1.add(textField4);

    textJPanel2.add(textField5);
    textJPanel2.add(monthJComboBox);

    textJPanel3.add(textField7);
        
    //text area
    output = new JTextArea(15,42);
    String outputString = "";
    output.setText(outputString);
        
    //redeem button
    redeemJButton = new JButton("Redeem miles");
    redeemButtonJPanel.add(redeemJButton);
        
    //Create JList from avaliableCities[]
    destinationCitiesJList = new JList<String>(avaliableCities);
    destinationCitiesJList.setVisibleRowCount(10);
    destinationCitiesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    listJPanel.add(new JScrollPane(destinationCitiesJList));
        
    // add JPanel to JFrame/contentPane
    //Add miles and miles info
    milesJPanel.add(listJPanel, BorderLayout.NORTH);
    milesJPanel.add(labelJPanel1, BorderLayout.WEST);
    milesJPanel.add(textJPanel1, BorderLayout.EAST);//, BorderLayout.NORTH);
    destJPanel.add(milesJPanel); //green background
        
    //Add input
    inputJPanel.add(labelJPanel2, BorderLayout.WEST);
    inputJPanel.add(textJPanel2, BorderLayout.EAST);//, BorderLayout.NORTH);
    inputJPanel.add(redeemButtonJPanel, BorderLayout.SOUTH);
    //Add text area
    outputJPanel.add(output, BorderLayout.NORTH);
    //Add output
    outputJPanel.add(labelJPanel3, BorderLayout.WEST);
    outputJPanel.add(textJPanel3, BorderLayout.EAST);
    redeemJPanel.add(inputJPanel, BorderLayout.NORTH); //purple backgroun
    redeemJPanel.add(outputJPanel, BorderLayout.SOUTH);//purple backgroun

    // add main panels to content pane container
    container.add(destJPanel, BorderLayout.WEST);
    container.add(redeemJPanel, BorderLayout.CENTER);

    // register event handlers
    EventHandler handler = new EventHandler();
    textField5.addActionListener(handler); //miles
        
    ButtonHandler buttonHandler = new ButtonHandler();
    redeemJButton.addActionListener(buttonHandler);

    destinationCitiesJList.addListSelectionListener(
      new ListSelectionListener()
      {
        @Override
        public void valueChanged(ListSelectionEvent event)
        {
          if(event.getValueIsAdjusting())
            return;

          //code for selecting from list
          //retrieve destination info
          destinationInfo = currentView.findDestination(avaliableCities[destinationCitiesJList.getSelectedIndex()] );
          //set fields
              
          textField1.setText(String.valueOf(destinationInfo.getNormMile()));

          textField2.setText(String.valueOf(destinationInfo.getFreqMile()));

          textField3.setText(String.valueOf(destinationInfo.getAddtlMile()));

          textField4.setText(month[destinationInfo.getBeginMonth()-1] + " - " + month[destinationInfo.getEndMonth()-1]);
        }
      }
    );
        
    fileScan.close() ;
  }
  
  private class EventHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent event)
        {
          //No code needed for entering values
        }
    }
    
    private class ButtonHandler implements ActionListener
    {
      
      @Override
      public void actionPerformed(ActionEvent event)
      {
        String milesValue = textField5.getText();
        //int miles = Integer.parseInt(milesValue);
        int miles = 0;
        try{
            miles = Integer.parseInt(milesValue);
        }catch (NumberFormatException ex) {
            System.out.println("Error.");
        }
        int month = monthNum[monthJComboBox.getSelectedIndex()];
        String solutionFlightResults = "" ;
        
        String[] flightResults = currentView.redeemMiles(miles,month);
        
        int loopCount = 0 ; //count blank records
        int loopCountSuccess = 0 ;
        
        //Loop through results and display
        for(int i= 0 ; i<flightResults.length ; i++ )
        {
          if(flightResults[i] != null)
          {
            ++loopCountSuccess ;
            if(loopCountSuccess==1)
              solutionFlightResults = ( solutionFlightResults + "Your client's Frequent Flyer Miles can be used to redeem the following tickets:\n\n" );
            solutionFlightResults = ( solutionFlightResults + flightResults[i] + "\n" ) ;
          }
          else
          {
            //count null results
            loopCount++ ;
          }
        }
        
        if(flightResults.length == ( loopCount ) )
          {
            solutionFlightResults = ("*** Your client has not accumulated enough Frequent Flyer Miles ***") ;
          }
        
        //set output
        output.setText(solutionFlightResults);
        textField7.setText(String.valueOf(currentView.getRemainingMiles()));
      }
    }
}