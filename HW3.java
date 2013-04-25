import java.io.*;

class HW3{
	
	
	static int[] readSource(File chosenFile) throws IOException 
	{
	       String chosenFileName = chosenFile.getPath();
	       BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(chosenFileName)));
	       int input = br.read();
	       int[] freq = new int[128];
	       for(int i = 0; i < 128; i++)
	       {
	    	   freq[i] = 0;
	       }      
	       
	       while(input >= 0)
	       {
	    	   freq[input]++;
	    	   input = br.read();
	       }
	       br.close();
	       return freq;
	}
}