package SpellChecker;

public class GenericLinkedQueue<T> {

    private int size; 
    private Node<T> first;
    private Node<T> last;

    public GenericLinkedQueue() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void enqueue(T entry) {
        Node<T> newNode = new Node(entry);
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Stack underflow");
        }
        Node<T> tmp = first;
        first = first.next;
        tmp.next = null;
        size--;
        return tmp.getItem();
    }

    public T removeExact(T entry){
        Node<T> current = first;
        Node<T> previous = null;
        while(current != null){
            if(current.getItem().equals(entry)){
                if(previous == null){
                    first = current.next;
                }else{
                    previous.next = current.next;
                }
                size--;
                return current.getItem();
            }
            previous = current;
            current = current.next;
        }
        return null;
    }

}