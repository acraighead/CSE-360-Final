//This class is used to create a Linked List of type Roster which will be used by Ops and GUI for managing student data
public class Roster {

	private String id;
	private String fName;
	private String lName;
	private String program;
	private String level;
	private String asurite;

//Constructor for Roster. And object of type Roster stores information about a student: their id, first and last name, degree program, level of study, and asurite
	public Roster(String newID, String newFirst, String newLast, String newProg, String newLev, String newASU)
	{
		id = newID;
		fName = newFirst;
		lName = newLast;
		program = newProg;
		level = newLev;
		asurite = newASU;
	}
//resturns asurite variable
	public String getASU()
	{
		return asurite;
	}

//returns id variable
	public String getID()
	{
		return id;
	}
//returns fName varaible(the student's first name)
	public String getFirst()
	{
		return fName;
	}
//returns lName variable(the student's last name)
	public String getLast()
	{
		return lName;
	}
//returns the program variable
	public String getProgram()
	{
		return program;
	}
//returns the level variable
	public String getLevel()
	{
		return level;
	}

//returns a string of student data formated for a CSV file.
	public String toCSV()
	{
		return id + "," + fName + "," + lName + "," + program + "," + level + "," + asurite + "\n";
	}
}
