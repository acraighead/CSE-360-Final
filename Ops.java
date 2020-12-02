import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.*;
import java.util.*;
import java.lang.*;


public class Ops{
	
	public static LinkedList<Roster> list = new LinkedList<Roster>();
	public static LinkedList<Attendance> attend = new LinkedList<Attendance>();
	
	public static final String delimiter = ",";
	
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

	public static LinkedList<Roster> getList(){
		return list;
	}
	public static LinkedList<Attendance> getAttend(){
		return attend;
	}
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
