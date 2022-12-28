public class StackRefBased<T> implements Stack<T> {

	public int size;
	public StackNode<T> top;
	
    public StackRefBased() {
		top = null;
		size = 0;
    }

    public int size() {
        return size;
    }


    public boolean isEmpty() {
        return size == 0;
    }


    public void push(T data) {
        StackNode<T> newNode = new StackNode<T>(data);
		newNode.next = top;
		top = newNode;
		size++;
    }


    public T pop() throws StackEmptyException {
        if(size == 0) {
			throw new StackEmptyException();
		} else {
			T returnVal = top.getValue();
			top = top.getNext();
			size--;
			return returnVal;
		}
    }


    public T peek() throws StackEmptyException {
        if(size == 0) {
			throw new StackEmptyException();
		} else {
			T returnVal = top.getValue();
			return returnVal;
		}
    }


    public void makeEmpty() {
		top = null;
		size = 0;
    }


    public String toString() {
        String result = "";
		StackNode<T> cur = top;
		while(cur != null) {
			if(cur.next == null) {
				result += cur.getValue();
			} else {
				result += cur.getValue() + " ";
			}
			cur = cur.next;
		}
		return result;
    }
}

