
public class BTree implements Comparable
{
	 public Character element = null;
	 public int freq = 0;
	 public BTree left, right;
	 
	 public int compareTo(Object o)
	 {
		 BTree comp = (BTree)o;
		 if (freq == comp.freq)
			 return 0;
		 if (freq > comp.freq)
			 return 1;
		 else
			 return -1;
	 }
}
