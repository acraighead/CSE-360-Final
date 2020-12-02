import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.*;
import java.util.*;
//import java.util.concurrent.locks;
import java.lang.*;
import java.util.concurrent.locks.Lock;


public class Ops extends DatePicker{
	
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
	
	
	public static void takeAttendance(File csv)
	{
		try {
	         File file = csv;
	         FileReader fr = new FileReader(file);
	         BufferedReader br = new BufferedReader(fr);
	         String line = "";
	         String[] input;
	         Boolean selected = false;


			String month;
			String day;

			DatePicker d = new DatePicker();
			d.setVisible(true);

			while(!selected){
				d.setVisible(true);
				selected = d.checkButton();
			}

			month = d.getMonth();
			day = d.getDay();




			month = d.getMonth();
			day = d.getDay();
	         

	        		 
	         
	         
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
	         }
	         br.close();
	         } catch(IOException ioe) {
	            ioe.printStackTrace();
	         }
	}
	
	public static void write(String cvs) {
		try {
			FileWriter fw = new FileWriter(cvs);
	 
			for (Roster r : list ) {
				fw.write(r.toCSV() + " ");
				for(Attendance a: attend)
				{
					
					
				}
			
			}
		
		fw.close();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
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
	public int search(String asu){
		int index=-1;
		for(int i=0;i<attend.size();i++){
			if(attend.get(i).asurite.compareTo(asu)==0){
				index =i;
				return index;
			}
		}
			return index;
	}
}
