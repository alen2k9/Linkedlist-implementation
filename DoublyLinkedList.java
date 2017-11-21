import java.util.*;

public class DoublyLinkedList<E extends Comparable <E>> implements Iterable<E>{ //Doubly Linked list main class
																				//extends comparable and implements iterator interface
    private static class Node<E>		//Private static node class
    {

            private E element;			//data in node
            private Node<E> next;		//Node next
            private Node<E> prev;		//Node previous

            private Node(E element, Node<E> next, Node<E> prev)	//node constructor
            {
                this.element = element;
                this.next = next;
                this.prev = prev;
            }

            public E getElement()		//gets element in node
            {
                return element;
            }

            private Node<E> getNext()	//gets next node
            {
                return next;
            }

            private Node<E> getPrev()	//get previous node
            {
                return prev;
            }

            public void setNext(Node<E> next)	//set next node
            {
                this.next = next;
            }

            private void setPrev(Node<E> prev)	//set previous node
            {
                this.prev = prev;
            }

            public void setElement(E element)	//set data in node
            {
                this.element = element;
            }
        }

    private Node<E> header;		//Linked list head and tail
    private Node<E> trailer;	
    private int size = 0;
    
    public DoublyLinkedList() //Linked list constructor
    {
    	 header = new Node<>(null, null, null); 
    	 trailer = new Node<>(null, null, header); 
    	 header.setNext(trailer); 
    }

    private int size()		//gets size of liked list
    {
        return size;
    }

    private boolean isEmpty()	//checks if linked list is empty
    {
        return size == 0;
    }

    private E getFirst()		//gets first element in linked list
    {
        if (isEmpty())
        {
            return null;
        }
        return header.getNext().getElement();
    }

    private E getLast()			//gets last element in linked list
    {
        if (isEmpty())
        {
            return null;
        }
        return trailer.getPrev().getElement();
    }

    private void addFirst(E data)	//adds element after header
    {
        Node<E> node1 = new Node<E>(data, header.getNext(), header);
        
            
            header.setPrev(node1);
            header = node1;
        size++;
    }
    private void addLast(E data)	//add element before trailer
    {
       Node<E> node1 = new Node<E>(data, trailer, trailer.getPrev());
      
           trailer.setPrev(node1);
           node1.getPrev().setNext(node1);
           size++;
    }

    private void addBetween(E e, Node<E> predecessor, Node<E> successor)	//adds node with data between two nodes
    {
        Node<E> node1 = new Node<>(e, predecessor, successor);
        predecessor.setNext(node1);
        successor.setPrev(node1);
        size++;
    }

    private E removeBetween(Node<E> noderemove)		//removes the node from a linked list
    {
        Node<E> node1 = noderemove.getPrev();
        Node<E> node2 = noderemove.getNext();
        node1.setNext(node2);
        node2.setPrev(node1);
        size--;
        return noderemove.getElement();
    }

    private E removeFirst()		//removes first node after header from linked list
    {
        if(size == 0)
        {
            throw new NoSuchElementException();
        }
        else
        {
            Node<E> node1 = header;
            header = header.getNext();
            header.prev = null;
            size--;
            return node1.getElement();
        }
    }

    private E removeLast()		//removes last node before trailer from linked list
    {
        if(size == 0)
        {
            return null;
        }
        else
        {
           Node<E> node1 = trailer;
           trailer = trailer.getPrev();
           trailer.next = null;
           size--;
           return node1.getElement();
        }
        
    }

    public Iterator<E> iterator() 
    {			//Iterator implementation
        return new ListIterator<E>();
    }

    public class ListIterator<E> implements Iterator<E> 
    {
        private Node<E> currentNode;	//Nodes
        private Node<E> previous;

        /**
         * @param currentNode
         */
        public ListIterator() 	//Traverse over linked list
        {	
            super();
            this.currentNode = (Node<E>) header;
            this.previous = null;
        }

        @Override
        public boolean hasNext() 	//Boolean function to see if there is a next node
        {
            if (currentNode != null && currentNode.next != null)
                return true;
            else
                return false;
        }

        @Override
        public E next() {		//gets next element
            if (!hasNext())
                throw new NoSuchElementException();
            if ( previous == null )
            {
                previous = currentNode;
                return previous.getElement();
            }
            E node = currentNode.getElement();
            currentNode = currentNode.next;
            return currentNode.getElement();
        }

        @Override
        public void remove() {
            // TODO Auto-generated method stub
        }
    }
    
    
    public boolean isPalindrome()	//Palindrome method to test if a linked list is a palindrome
    {
    	
        if(this.header == null)
        {
            return false;
        }

        else
        {
            Node<E> left = this.header;
            Node<E> right = this.trailer;

            int length = (this.size)/2;
            int i = 0;
            while(i <= length)
            {
                if(left.getElement() != right.getElement())
                {
                    return false;
                }
                left = left.getNext();
                right = right.getPrev();
                
                i++;
            }
            return true;
        }
    }
    
    public E getKth(int x)		//Algorithm to get the kth element of a inked list
    {
    	Node<E> curr = this.header;
    	int i = 0;
    	
    	while(curr != null)
    	{
    		if(i == x)
    		{
    			return curr.element;
    		}
    		i++;
    		curr = curr.next;
    	}
    	
		return null;
    	
    }
    
    public void reverse()		//Algorithm to reverse a linked list
    {
    	Node<E> node1 = null;
    	Node<E> curr = header;
    	
    	while(curr != null)
    	{
    		node1 = curr.prev;
    		curr.prev = curr.next;
    		curr.next = node1;
    		curr = curr.prev;
    	}
    	
    	if(node1 != null)
    	{
    		header = node1.prev;
    	}
    }
    
    public E popMinimum()		//Algorithm to find smallest element in a linked list and remove it from the list and return it
    {
    	if(header.getNext() == trailer)
    	{
    		throw new NoSuchElementException();
    	}
    
    	
    		
    		Node <E> min = this.header.next;
    		Node<E> n;
    		for(n = this.header.next.next; n != trailer; n = n.next)
    		{
    			
    				if(n.getElement().compareTo(min.getElement()) < 0)
    				{
    					min = n;
    				}
    		}
    		removeBetween(min);
    		return min.getElement();
    	}
			
    
    
  
    public static void testPalindrome() {		//paindrome tester
 		String [] data = {"a", "m", "a", "n", "a", "p", "l", "a", "n", "a", "c", "a", "n", "a", "l", "p", "a", "n", "a", "m", "a"};
 		DoublyLinkedList<String> ll = new DoublyLinkedList<String>();
 		
 		for(String s : data) {
 			ll.addLast(s);
 		}
 		
 		Iterator<String> itr = ll.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
 		System.out.println("isPalindrome(): " + ll.isPalindrome());
 }
	
    public static void testKth() {		//Kth element tester
		String [] data = {"one", "two", "three", "four", "five", "six"};
	DoublyLinkedList<String> ll = new DoublyLinkedList<String>();
	
	for(String s : data) {
		ll.addLast(s);
	}
	for(int k = 0; k < data.length; ++k) {
		System.out.println("k: " + k + " " + data[k] + " " + ll.getKth(k));
	}

    }
    
    public static void reverse1() {	//reverse tester
		String [] data = {"one", "two", "three", "four", "five", "six"};
		DoublyLinkedList<String> ll = new DoublyLinkedList<String>();
	
		for(String s : data) {
			ll.addLast(s);
		}
		
		
		System.out.println("before reverse: " );
		for(Object o : ll){
		    System.out.println(o);
		}
		ll.reverse();
		System.out.println("after reverse: " );
		
		for(Object o : ll){
		    System.out.println(o);
		}
    }	
    
    public static void selectionSort() 	//tester for pop minimum
    {
		Random random = new Random(20010);
	DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
	int n = 100;
	for(int i = 0; i < n; i++) {
		ll.addLast(new Integer(random.nextInt(1000)));
	}
	System.out.println("before sorting : ");
	for(Object o : ll){
	    System.out.println(o);
	}
	
	
	DoublyLinkedList<Integer> sorted_ll = new DoublyLinkedList<Integer>();
	while(!ll.isEmpty()) 
	{
		
		sorted_ll.addLast(ll.popMinimum());
		
	}
	System.out.println("after sorting : " );
	for(Object o : sorted_ll)
	{
	    System.out.println(o);
	}
	
}
    

	public static void main(String[] args) //Main function where methods are called
	{
		System.out.println("Palindrome \n");
		testPalindrome();
		System.out.println("\n\nTest k \n");
		testKth();
		System.out.println("\n\nReverse test \n");
		reverse1();
		System.out.println("\n\nsort \n");
		selectionSort();

	}
	
	}



