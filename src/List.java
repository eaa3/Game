
class Node
{
	int x,y;
	Node next;
	
	
	public Node(int x, int y)
	{
		this.x = x;
		this.y = y;
		this.next = null;
	}
	
	public Node()
	{
		
	}
}

public class List {

	Node head;
	Node tail;
	
	int size;
	
	public List()
	{
		this.head = null;
		this.tail = null;
	}
	
	public boolean isEmpty()
	{
		return this.head == null;
	}
	
	public void addBack(int x, int y)
	{
		this.addBack(new Node(x,y));
	}
	
	public void addFront(int x, int y)
	{
		this.addFront(new Node(x,y));
	}
	
	public void addFront(Node bodyPart)
	{
		if( this.head == null )
		{
			this.head = bodyPart;
			this.tail = this.head;
		}
		else
		{
			bodyPart.next = this.head;
			this.head = bodyPart;		
			

		}
		
		
		size++;
	}
	
	public void addBack(Node bodyPart)
	{
		if( this.head == null )
		{
			this.head = bodyPart;
			this.tail = this.head;
		}
		else
		{			
			this.tail.next = bodyPart;			
			
			//Seta a ultima parte do corpo como sendo a nova parte do corpo adicionada
			this.tail = bodyPart;
		}
		
		
		size++;
	}
	
	public void removeFront()
	{
		if( this.head == null )
		{
			return;
		}
		else if( this.head == this.tail )
		{
			this.head = null;
			this.tail = null;
			size = 0;
		}
		else
		{
			this.head = this.head.next;
			size--;
		}
	}
	
	public void removeBack()
	{
		if( this.head == null )
		{
			return;
		}
		else if( this.head == this.tail )
		{
			this.head = null;
			this.tail = null;
			size = 0;
		}
		else
		{
			Node currNode = this.head;
			
			while( currNode.next != this.tail )
			{
				currNode = currNode.next;
			}
			
			currNode.next = null;
			
			this.tail = currNode;
			size--;
		}
	}

}
