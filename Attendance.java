import java.util.*;
import java.lang.*;



public class Attendance {

	String asurite; 
	int hours;
	String month;
	String day;
	
	public Attendance(String newASU,int baseHours, String monthEntry, String dayEntry)
	{
		asurite = newASU;
		hours = baseHours;
		month = monthEntry;
		day = dayEntry;
	}
	
	public void addTime(int time)
	{
		hours+=time;
	}
	
	public String getDate()
	{
		return month + "/" + day;
	}
	
	public String checkASU()
	{
		return asurite;
	}
	
	public int getTime()
	{
		return hours;
	}


	
	
	public boolean verifyDate(String entryDay, String entryMonth)
	{
		if(this.month.contentEquals(entryMonth) && this.day.contentEquals(entryDay))
			return true;
		else
			return false;
		
	}
	
}


