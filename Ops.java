import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.*;
import java.util.*;
import java.lang.*;

//This class handles file I/O and the creation/management of Linked Lists for tracking student roster and attendance data
public class Ops{

	public static LinkedList<Roster> list = new LinkedList<Roster>();
	public static LinkedList<Attendance> attend = new LinkedList<Attendance>();

	public static final String delimiter = ",";

//Uses buffered reader to read an input CSV line by line. Each line is split using "," as a deliminator, and these split data points used to create objects of type Roster that
//are added tot he Roster Linked List.
	public static void read(File csv) {
	try {
	         File file = csv;
	         FileReader fr = new FileReader(file);
	         BufferedReader br = new BufferedReader(fr);
	         String line = "";
	         String[] input;
	         while((line = br.readLine()) != null) {
	            input = line.split(delimiter);

	            Roster entry = new Roster(input[0],input[1],input[2],input[3],input[4],input[5]);

	            list.add(entry);

	         }
	         br.close();
	         } catch(IOException ioe) {
	            ioe.printStackTrace();
	         }
	}

//Reads in an attendance file CSV line by line. Each line is split using "," as a deliminator. The data is used to verify if A. a studen exists int he Roster and B. if the student is already
//being trakce din the Attendance Linked List for that Month/day date. If yes to both, the student's current entry has the input number of hours added tot hat entry. If the student
//is int he Roster but not already in the attendance list for that date, an entry for that student and date is created and added tot he linked list and the hours of attendance initialized.
//If the student is not in the Roster, a message is displayed informing the user.
	public static ArrayList<String> takeAttendance(File csv,String month,String day)
	{
		ArrayList<String> nonStudents = new ArrayList<String>();
		try {
	         File file = csv;
	         FileReader fr = new FileReader(file);
	         BufferedReader br = new BufferedReader(fr);
	         String line = "";
	         String[] input;


	         while((line = br.readLine()) != null) {
	            input = line.split(delimiter);

	            String email = input[0];
	            int time = Integer.parseInt(input[1]);

	            for(Roster r : list)
	            {

	            	boolean stuExists = false;
	            	boolean stuTracked = false;
	            	if(email.equals(r.getASU()))
	           		{
	            		stuExists = true;

	            		if(attend.size()==0){
							Attendance node = new Attendance(email,time,month,day);
							attend.add(node);
						} else{
							for(Attendance a : attend) {
								if (email.contentEquals(a.checkASU()) && a.verifyDate(day, month)) {
									stuTracked = true;
									a.addTime(time);
								}
							}


							if(stuExists && !stuTracked)
							{
								Attendance node = new Attendance(email,time,month,day);
								attend.add(node);
							}
							else if(!stuExists)
							{
								System.out.println("Student " + email + " does not exist in our records\n");
							}
						}
	           		}
	            }
				 if(search(email)==-1){
					 nonStudents.add(email+","+time);
				 }
	         }
	         br.close();
	         } catch(IOException ioe) {
	            ioe.printStackTrace();
	         }

		return nonStudents;
	}

//Outputs the data stored in the Roster and Attendance Linked Lists to a CSV file.
	public static void save(JTable finalTable){
		try{

			TableModel model = finalTable.getModel();
			FileWriter csvWriter = new FileWriter(new File("SavedTable.csv"));

			for(int i=0; i<model.getColumnCount();i++){
				csvWriter.write(model.getColumnName(i)+",");
			}

			csvWriter.write("\n");

			for(int i=0;i< model.getRowCount();i++){
				for(int j=0; j< model.getColumnCount();j++){
					csvWriter.write(model.getValueAt(i,j).toString()+",");
				}
				csvWriter.write("\n");
			}
			csvWriter.close();


		} catch(IOException e){
			e.printStackTrace();
		}
	}

//Returns the contents of the Roster list
	public static LinkedList<Roster> getList(){
		return list;
	}

//Returns the contends of the Attendance list
	public static LinkedList<Attendance> getAttend(){
		return attend;
	}

//searches the Roster list for a given asurite ID and returns the index with that ID.
	public static int search(String asu){
		int index=-1;
		for(int i=0;i<list.size();i++){
			if(list.get(i).getASU().compareTo(asu)==0){
				index =i;
				return index;
			}
		}
			return index;
	}
}
