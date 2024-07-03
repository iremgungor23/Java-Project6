package SpellChecker;

public class LinearProbingHash<Key> {

    GenericLinkedQueue<Key>[] table;
    int M;
    int N;

    public LinearProbingHash(int M) {
        table = (GenericLinkedQueue<Key>[]) new GenericLinkedQueue[M];
        this.N = 0;
        this.M = M;
    }

    public int hash(Key t) {
        return ((t.hashCode() & 0x7fffffff) % M);
    }

    public boolean insert(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("first argument to put() is null");
        }
        if (N >= M / 2) {
            resize(2 * M);
        }

        key = filter(key);

        int hash = hash(key);
        if (table[hash] == null) {
            table[hash] = new GenericLinkedQueue();
        }

        table[hash].enqueue(key);
        System.out.println("inserted " + key);
        N++;
        return true;
    }

    private void resize(int capacity) {
        System.out.println("resize");
        LinearProbingHash<Key> temp = new LinearProbingHash<Key>(capacity);
        for (int i = 0; i < M; i++) {
            if (table[i] != null) {
                temp.insert(table[i].dequeue());
            }
        }
        table = temp.table;
        M = temp.M;
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to delete() is null");
        }

        key = filter(key);

        int i = hash(key);
        if (table[i] == null  || table[i].isEmpty()) {
            System.out.println("Key not found");
            return;
        }
        N--;
        System.out.println("deleted " + table[i].removeExact(key));
    }

    public Key find(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }

        key = filter(key);

        int i = hash(key);
        if (table[i] == null  || table[i].isEmpty()) {
            System.out.println("Key not found");
            return null;
        }

        GenericLinkedQueue<Key> temp = table[i];
        while(!temp.isEmpty()){
            Key a = filter(temp.dequeue());
            if(a.equals(key)){
                return a;
            }

            temp.enqueue(a);
        }

        return null;
    }

    public String toString() {
        String s = "[";
        for (int i = 0; i < M; i++) {
            s += table[i] + ",";
        }
        return s + "]";
    }

    public Key filter(Key key){
        if(key instanceof String){
            return (Key) ((String) key).toLowerCase();
        }

        return key;
    }
}
