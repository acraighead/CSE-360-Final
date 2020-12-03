import java.util.*;
import java.lang.*;


//This class creates the Attendance class. A Linked List of type Attendance is used in GUI and Ops for managing student attendance data.
public class Attendance {


	String asurite;
	int hours;
	String month;
	String day;

//Constructor for Attendance object. An Attendance object tracks the hours of
//attendance for a given date of month/day for a student tracked by asurite ID
	public Attendance(String newASU,int baseHours, String monthEntry, String dayEntry)
	{
		asurite = newASU;
		hours = baseHours;
		month = monthEntry;
		day = dayEntry;
	}

//Adds to the hours variable of an instance
	public void addTime(int time)
	{
		hours+=time;
	}
//returns the date of an instance in the form month/day
	public String getDate()
	{
		return month + "/" + day;
	}
//returns the asurite variable of an object
	public String checkASU()
	{
		return asurite;
	}
//Returns the hours variable of an object
	public int getTime()
	{
		return hours;
	}



//takes in a day and month, and verifies if the resultant date matches the date of a given instance of object Attendance. Returns true if matched, and false if not.
	public boolean verifyDate(String entryDay, String entryMonth)
	{
		if(this.month.contentEquals(entryMonth) && this.day.contentEquals(entryDay))
			return true;
		else
			return false;

	}

}


