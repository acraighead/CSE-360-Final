/**
 *   Authors: Jacob Rollings, Seth Ryals, Aaron Craighead
 *            
 *   Class ID: CSE 360, Class# 70605 
 *   Final Project
 *   Description: This is the ScatterPlot class that contains
 *   methods for creating the Plot window for the user. It has a private 
 *   variable for holding the dataset to be used. It also contains a 
 *   constructor to make the dialog box. It also contains a method to 
 *   Create the dataset for the plot to use.
 */


import java.awt.BorderLayout;
import java.awt.Color;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.JDialog;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;



public class ScatterPlot{
	
	/**  
	 * The ScatterPlot Class contains 1 private variable, 1 constructor,
	 *  and 1 public method 
	 *  dataset is a private XYDataset variable containing the dataset provided
	 *  by the user.
	 *  
	 *  ScatterPlot() is a constructor that sets up the formatting of the dataset
	 *  as well as creating the dialog box that contains the dataset.
	 *   
	 *  CreateDataset() returns a dataset of the data that can be used to create a plot
	 *  
	 */
	
	private
	XYDataset dataset;
	
	public ScatterPlot(LinkedList<Attendance> attendanceTimes)
	{
		/**
		 * This is the ScatterPlot Constructor, it has code to create a plot of the 
		 * data in the attendance linked list as well as the code for setting
		 * up the dialog box that contains the plot
		 */
		
		dataset = createDataSet(attendanceTimes);
		
		//create the scatterplot chart
		JFreeChart chart = ChartFactory.createScatterPlot("Attendance Percentage "
				+ "vs. Count", "Percentage", "Count", dataset);
		
		//set the plot color
		XYPlot plot = (XYPlot)chart.getPlot();
		plot.setBackgroundPaint(new Color(255,228,196));
		
		//make the units integers instead of having decimal points
		NumberAxis yAxis = (NumberAxis) plot.getRangeAxis(); 
		yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		
		//create a panel with a border layout
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		//create a chartpanel and put it in the jpanel
		ChartPanel chartPanel = new ChartPanel(chart);
		panel.add(chartPanel, BorderLayout.CENTER);
		panel.validate();
		
		//create a dialog box and add the JPanel, set the size
		//and make it visible to the user
		JDialog dialogPanel = new JDialog();
	
		dialogPanel.add(panel);
		dialogPanel.setSize(700,500);
		dialogPanel.setLocationRelativeTo(null);
		dialogPanel.setVisible(true);
		
		
	}
	
	private XYDataset createDataSet(LinkedList<Attendance> AttendanceTimes)
	{
		/**
		 * This is the createDataSet Method, it has code to create the 
		 * dataset of the data in the attendance linked list. It also calculates
		 * which percent region that the values are in and then sets up the data series.
		 * It will use a floor function to categorize the points on the graph.
		 * ex. 29% will be placed in the 20% category.
		 */
		
		XYSeriesCollection data = new XYSeriesCollection();
		
		
		int[] count = new int[11];
		double percentage;
		
		
		XYSeries seriesData = new XYSeries(AttendanceTimes.get(0).getDate());
		
		//Go through whole linked list and calculate attendance percent
		for(int i = 0; i<AttendanceTimes.size(); i++)
		{
			if(i>=1)
			{
				if(!AttendanceTimes.get(i).getDate().equals
						(AttendanceTimes.get(i-1).getDate()))
				{
					//when it finds that the date isn't the same, it will 
					//make a new dataseries
					seriesData.add(0,count[0]);
					seriesData.add(10,count[1]);
					seriesData.add(20,count[2]);
					seriesData.add(30,count[3]);
					seriesData.add(40,count[4]);
					seriesData.add(50,count[5]);
					seriesData.add(60,count[6]);
					seriesData.add(70,count[7]);
					seriesData.add(80,count[8]);
					seriesData.add(90,count[9]);
					seriesData.add(100,count[10]);
					
					//add the series onto the collection
					data.addSeries(seriesData);
					//Reset the series data to a different name to add to dataset
					seriesData = new XYSeries(AttendanceTimes.get(i).getDate());
					
					//reset the counts for the next wave of dates
					for(int j = 0 ; j < 11 ; j++)
					{
						count[j] = 0;
					}
				}
			}
			
			percentage = AttendanceTimes.get(i).getTime();
			percentage = percentage/75;
			if(percentage>=1)
			{
				count[10] += 1;
			}
			else if(percentage>=.9)
			{
				count[9] += 1;
			}
			else if(percentage>=.8)
			{
				count[8] += 1;
			}
			else if(percentage>=.7)
			{
				count[7] += 1;
			}
			else if(percentage>=.6)
			{
				count[6] += 1;
			}
			else if(percentage>=.5)
			{
				count[5] += 1;
			}
			else if(percentage>=.4)
			{
				count[4] += 1;
			}
			else if(percentage>=.3)
			{
				count[3] += 1;
			}
			else if(percentage>=.2)
			{
				count[2] += 1;
			}
			else if(percentage>=.1)
			{
				count[1] += 1;
			}
			else if(percentage<.1)
			{
				count[0] += 1;
			}		
		}
		
		//when it finds that the date isn't the same, it will 
		//make a new dataseries
		seriesData.add(0,count[0]);
		seriesData.add(10,count[1]);
		seriesData.add(20,count[2]);
		seriesData.add(30,count[3]);
		seriesData.add(40,count[4]);
		seriesData.add(50,count[5]);
		seriesData.add(60,count[6]);
		seriesData.add(70,count[7]);
		seriesData.add(80,count[8]);
		seriesData.add(90,count[9]);
		seriesData.add(100,count[10]);
		
		//add the series onto the collection
		data.addSeries(seriesData);
		
		return data;
	}
}



