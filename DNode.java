/** Node class for a doubly linked list of strings */
public class DNode<T>
{  
   protected T element;	   // Element of data
   protected DNode<T> next, prev; // Next and previous

   public DNode(T e, DNode<T> p, DNode<T> n) 
      { element = e; prev = p; next = n; } 

   public T getElement()	{ return element; }  
   public DNode<T> getPrev() 	{ return prev; }  
   public DNode<T> getNext() 	{ return next; }  

   public void setElement(T e)	{ element = e; }  
   public void setPrev(DNode<T> p) 	{ prev = p; }  
   public void setNext(DNode<T> n) 	{ next = n; }
}