package SpellChecker;

public class Node<T> {

    private T item;
    public Node<T> next;

    public Node(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

}