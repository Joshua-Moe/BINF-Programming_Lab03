import java.io.*;

public class FastaClass 
{	
	public static final String INPUT_FILE = "C:\\UNC_Fall_2020\\BINF_Advanced_Programming\\Labs\\Lab_03\\Mdomestica_SUBSET_testing.fa";
	public static final String OUTPUT_FILE = "myOutput.txt";
	
	public static void main(String args[]) throws Exception
	{
		StringBuilder content = new StringBuilder();
		String contentAsString = "";
		content.append("sequenceID\tnumA\tnumC\tnumG\tnumT\tsequence\n");
		
		
		BufferedReader reader = new BufferedReader(new FileReader(new File(INPUT_FILE)));
	
		String header = null;
		int numA = 0;
		int numC = 0;
		int numG = 0;
		int numT = 0;
		String seq = "";
		
		for(String nextLine = reader.readLine(); nextLine != null; nextLine = reader.readLine())
		//while(reader.hasNextLine() == true)
		{
			
			if(nextLine.startsWith(">") && header == null) //The first time ">" is seen.
			{
				header = nextLine;
				continue;
			}
			
			if(nextLine.startsWith(">") == false) 
			{
				for(int i = 0; i < nextLine.length();i++) 
				{
					char nucleotide = nextLine.charAt(i);
					numA = (nucleotide=='A') ? numA + 1:numA;
					numC = (nucleotide=='C') ? numC + 1:numC;
					numG = (nucleotide=='G') ? numG + 1:numG;
					numT = (nucleotide=='T') ? numT + 1:numT;
					seq += nextLine;
				}
			}
			
			if(nextLine.startsWith(">") && header != null) //All other times ">" is seen after the first.
			{
				content.append(header + "\t" + numA + "\t" + numC + "\t" + numG + "\t" + numT + "\t" + seq + "\n");
				header = nextLine;
				numA = 0;
				numC = 0;
				numG = 0;
				numT = 0;
				continue;
			}
		}
		//When the last sequenceID header occurs, the above 'if' statements would not record the last round of data.
		content.append(header + "\t" + numA + "\t" + numC + "\t" + numG + "\t" + numT + "\t" + seq + "\n");
		contentAsString = content.toString();
		reader.close();
	
	
	//Write out to a file.
	contentAsString = content.toString();
	
	BufferedWriter writer = new BufferedWriter(new FileWriter(new File(OUTPUT_FILE))); 
	writer.write(contentAsString);
	writer.flush(); writer.close();
	}
}
