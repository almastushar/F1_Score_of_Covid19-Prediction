import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Scanner;


public class MainClass {
	
	public static String[][] Prediction = new String[100][2];
	public static String[][] Person = new String[100][2];
	public static int i=0,j=0,k=0,l=0;
	public static double TP=0,FN=0,FP=0,TN=0;
	
	public static void predictionModel(final File folder) throws FileNotFoundException {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	        	predictionModel(fileEntry);
	        } else {
	        	Scanner myReader = new Scanner(fileEntry);
	            //System.out.println(fileEntry.getName());
	            Prediction [i][j]=fileEntry.getName();
	            j++;
	            while (myReader.hasNextLine()) {
			        String data = myReader.nextLine();
			        //System.out.println(data);
			        Prediction [i][j]=data;
			        j--;
			        i++;
			      }
			      myReader.close();
	        }
	    }
	}
	
	public static void personActual(File file) throws FileNotFoundException {
		Scanner myReader = new Scanner(file);
		 while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        String Temp[] = data.split(",");
		        Person [k][l] = Temp[0];
		        l++;
		        Person [k][l] = Temp[1];
		        l--;
		        k++;
		        Temp=null;
		        //System.out.println(data);
		      }
		      myReader.close();	
	}
	
	public static void F1_score(String[][] Prediction,String[][] Person) {
		for (int i = 0; i < Prediction.length; i++) {
			for (int j = 0; j < Person.length; j++) {
				if(Prediction[i][0].equals(Person[j][0])) {
					if(Prediction[i][1].equals("1")) {
						if(Person[j][1].equals("True")) {
							TP++;
						}
						else if(Person[j][1].equals("False")) {
							FP++;
						}
					}
					else if(Prediction[i][1].equals("0")) {
						if(Person[j][1].equals("True")) {
							FN++;
						}
						else if(Person[j][1].equals("False")) {
							TN++;
						}
					}
				}
			}
			
		}
		
		double R = TP/(TP+FN);
		double P = TP/(TP+FP);
		double Total = (R*P)/(R+P);
		double F1_Score = 2*Total;
		System.out.println("F1 Score: "+F1_Score);
	}
	

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		final File folder = new File("assignmnet\\pred");
		File file = new File("assignmnet\\person");
		predictionModel(folder);
		personActual(file);
		F1_score(Prediction,Person);
	}

}
