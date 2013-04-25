import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.util.PriorityQueue;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class HufmanTree {
	public static void main(String args[]) throws IOException
	{	
		JFileChooser chooser;
		int status;
		int freq[] = new int[128];
		int huffmanCost = 0;
		int asciiCost = 0;
		String fileName;
		String huffmanCode[] = new String[128];
		
		for(int i = 0; i < 128; i++)
		{
			huffmanCode[i] = null;
		}
		PriorityQueue<BTree> q = new PriorityQueue<BTree>();
		String code = "";
		//asks user for an input file 
		chooser = new JFileChooser( );
		status = chooser.showOpenDialog(null);	
		if (status == JFileChooser.APPROVE_OPTION) 
	         freq = HW3.readSource(chooser.getSelectedFile());
		else 	
			JOptionPane.showMessageDialog(null, "Open File dialog canceled");
		
		//creates the PQ
		for(int i = 0; i<freq.length; i++)
		{
			if(freq[i]>0)
			{
				BTree tree = new BTree();
				tree.element = (char)i;
				tree.freq = freq[i];
				q.add(tree);
			}
		}
		//ask the user for an output file
		fileName = JOptionPane.showInputDialog(null, null, "Enter the name of the file you are writing to.", JOptionPane.QUESTION_MESSAGE);
		BufferedWriter write = new BufferedWriter(new FileWriter(fileName));
		write.write("Data Format: Huffman Code, ASCII Number in Decimal (Character Name), Frequency");
		write.newLine();
		//creates the tree
		BTree root = buildHT(q);
		huffmanCodeGenerator(root, code, huffmanCode);
		
		for(int i = 0; i < 128; i++)
		{
			if(huffmanCode[i] != null)
			{
				System.out.println(huffmanCode[i] + "," + " " + i + " " + "(" + (char)i + ")" + "," + " " + freq[i]);
				write.write(huffmanCode[i] + "," + " " + i + " " + "(" + (char)i + ")" + "," + " " + freq[i]);
				write.newLine();
			}
		}
		//calculate the cost of the huffman encoding
		huffmanCost = huffmanCost(huffmanCode, freq);
		write.write("Huffman Cost = " + huffmanCost);
        write.newLine();
		//calculate the ASCII cost
		asciiCost = asciiCost(freq);
		write.write("ACII Cost = " + asciiCost);
        write.newLine();
		System.out.println("Compression Ratio = " + ((float)huffmanCost/(float)asciiCost));
		write.write("Compression Ratio = " + ((float)huffmanCost/(float)asciiCost));
		write.flush();
		write.close();
	}
	
	public static BTree  buildHT(PriorityQueue<BTree> q)
	{
		//creates a tree
		while (q.size() > 1)
		{
			BTree root = new BTree();
			root.left = q.poll();
			root.right = q.poll();
			root.freq = (root.left.freq+root.right.freq);
			q.add(root);
		}
		return q.poll();
	}
	
	public static void huffmanCodeGenerator(BTree root, String s, String x[])
	{
		if(root.element == null)
		{
			huffmanCodeGenerator(root.left, s + 0, x);
			huffmanCodeGenerator(root.right, s + 1, x);
		}
		if (root.element != null) 
			x[(int)root.element] = s;
	}
	
	public static int huffmanCost(String code[], int freq[])
	{
		int cost = 0;
		for(int i = 0; i < code.length; i++)
		{
			if(code[i] != null)
				cost += code[i].length() * freq[i];
		}
		System.out.println("Huffman Cost = " + cost);
		return cost;
	}
	
	public static int asciiCost(int freq[])
	{
		int cost = 0;
		for(int i = 0; i < freq.length; i++)
		{
			if( freq[i] > 0)
				cost += 7 * freq[i];
		}
		System.out.println("ASCII Cost = " + cost);
		return cost;
	}
}
