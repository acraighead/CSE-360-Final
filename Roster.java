
public class Roster {

	private String id;
	private String fName;
	private String lName;
	private String program;
	private String level;
	private String asurite;
	
	
	public Roster(String newID, String newFirst, String newLast, String newProg, String newLev, String newASU)
	{
		id = newID;
		fName = newFirst;
		lName = newLast;
		program = newProg;
		level = newLev;
		asurite = newASU;
	}
	
	public String getASU()
	{
		return asurite;
	}
	
	
	public String getID()
	{
		return id;
	}
	
	public String getFirst()
	{
		return fName;
	}
	
	public String getLast()
	{
		return lName;
	}
	
	public String getProgram()
	{
		return program;
	}
	
	public String getLevel()
	{
		return level;
	}
	
	
	public String toCSV()
	{
		return id + "," + fName + "," + lName + "," + program + "," + level + "," + asurite + "\n";
	}
}
