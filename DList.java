public class DList<T>
{
	protected int size;
	protected DNode<T> header, trailer;
	public DList() 
	{ 
	size = 0;
	header = new DNode(0, null, null);
	trailer = new DNode(0, header, null);
	header.setNext(trailer);
	}
	public int size() { return size; }
	public boolean isEmpty() { return (size == 0); }
	public DNode<T> getFirst() throws IllegalStateException {
		if (isEmpty())
			throw new IllegalStateException("List is empty");
		return header.getNext(); 
	}
	
	public DNode<T> getLast() throws IllegalStateException {
		if (isEmpty())
			throw new IllegalStateException("List is empty");
		return trailer.getPrev();
	}
	
	public DNode<T> getPrev(DNode<T> v) throws IllegalArgumentException
	{
		if (v == header)
			throw new IllegalArgumentException("before header");
		return v.getPrev();
	}
	
	public DNode<T> getNext(DNode<T> v) throws IllegalArgumentException
	{
		if (v == trailer)
			throw new IllegalArgumentException("after trailer");
		return v.getNext();
	}
	
	public void addBefore(DNode<T> v, DNode<T> z) throws IllegalArgumentException
	{
		DNode<T> u = getPrev(v);
		z.setPrev(u); 
		u.setNext(z);
		z.setNext(v); 
		v.setPrev(z);
		size++;
	}
	
	public void addAfter(DNode<T> v, DNode<T> z) throws IllegalArgumentException
	{
		DNode<T> w = getNext(v);
		z.setPrev(v); 
		v.setNext(z);
		z.setNext(w); 
		w.setPrev(z);
		size++;
	}
	
	public void addIntInOrder(T x)
	{
		DNode<T> z = new DNode(x, null, null);
		DNode<T> search = null;
		if (this.isEmpty())
		{
			z.setNext(trailer);
			z.setPrev(header);
			header.setNext(z);
			trailer.setPrev(z);
			size++;
		}
		else
		{
			search = this.getFirst();
			boolean added = false;
			while (search != trailer && added == false)
			{
				if (((BTree) x).compareTo(search.getElement())<=0)
				{
					addBefore(search, z);
					added = true;
				}
				search = search.getNext();
			}
			if (added == false)
				addAfter(search, z);
		}
	}
	
	public void addFirst(DNode<T> v)
	{
		 addAfter(header, v);
	}
	
	public void addLast(DNode<T> v)
	{
		addBefore(trailer, v);
	}
	
	public void remove(DNode<T> v)
	{
		DNode<T> u = getPrev(v);
		DNode<T> w = getNext(v);
		w.setPrev(u);
		u.setNext(w);
		size--;
	}
	
	public boolean hasPrev(DNode<T> v)
	{
		 return v != header;
	}
	
	public boolean hasNext(DNode<T> v)
	{
		return v != trailer;
	}
	
	public int getSize()
	{
		return size;
	}
	
}