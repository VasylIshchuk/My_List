import java.util.AbstractList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class CustomList<T> extends AbstractList<T> {
    public  Node head, tail;
    public class Node{ //pointer to an object
        public Node next;
        public  T value;
        public Node(T value) {
            this.next = null;
            this.value = value;
        }
    }

    public CustomList() {
        this.head = null;
        this.tail = null;
    }
    //1 Task
    @Override
    public int size() {
        int size = 1;
        Node tmp = head;
        while (tmp.value != tail.value){
            tmp = tmp.next;
            ++size;
        }
        return size;
    }

    @Override
    public T get(int index) {
        int size = 0;
        Node tmp = head;
        while (size != index){
            tmp = tmp.next;
            ++size;
        }
        return tmp.value;
    }

    public  boolean add(T t) {
        if (head == null) {
            head = new Node(t);
            tail = head;
        } else {
            tail.next = new Node(t);
            tail = tail.next;
        }
        return true;
    }
// 2 Task
    public void addLast(T value){
        if(head == null){
            head = new Node(value);
            tail = head ;// now we have same address, if you want with different one's : new Node(value);
        } else {
            tail.next = new Node(value);
            tail = tail.next;
//            if(head.next == null)  head.next = new Node(value);
//            else tail.next = new Node(value);
//            tail = tail.next;
//            At the beginning it did not work for me because I created
//            2 objects(head,tail) with different addresses and the reference therefore did not work.
        }
    }
    public void addFirst(T value){
        if(head == null){
            head = new Node(value);
            tail = head;//new Node(value);
        } else {
            Node tmp = head;
            head = new Node(value);
            head.next = tmp;
        }
    }
    public T getLast(){
        if(tail == null) throw new NoSuchElementException("List is empty");
        return tail.value;
    }
    public T getFirst(){
        if(head == null) throw new NoSuchElementException("List is empty");
        return  head.value;
    }
    public T removeLast(){
        if(tail == null)
            throw new NoSuchElementException("Trying to remove element from empty list");
        Node removed = tail;
        if(head == tail) { //(head.next == null)
            head = tail = null;
        } else {
            Node tmp = head;
            while (tmp.next.value != tail.value){
                tmp = tmp.next;
            }
            tail = tmp;
        }
        return removed.value;
    }
    public  T removeFirst(){
        if(head == null)
            throw new NoSuchElementException("Trying to remove element from empty list");
        Node removed = head;
        if(head == tail) { //(head.next == null)
            head = tail = null;
        } else {
            head = head.next;
        }
        return removed.value;
    }
//     3 Task
    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size() && get(currentIndex) != null;
            }

            @Override
            public T next() {
                return get(currentIndex++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
                //which means that the delete operation is not supported for this iterator.
            }
        };
        return it;
    }
//        Iterator is needed in order for us to use for(String value : str) ,
//        but we can avoid writing this method because Java automatically does this
//        instead of us, and we can use for(String value : str).
/* Another way-> (Here is one's + it's when we use record like this we can use method (next),
                    but in previous way we can't because we go through (currentIndex)
                    and set zero(0) every time when use(next))
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                Node temp = start;

                @Override
                public boolean hasNext() {
                    return temp != null;
                }

                @Override
                public T next() {
                    T value = temp.value;
                    temp = temp.next;
                    return value;
                }
            };
        }
*/

    public Stream<T> stream(){
        Stream.Builder<T> streamBuilder= Stream.builder();
        for(T item : this){
            streamBuilder.accept(item);
        }
        return streamBuilder.build();
    }
}
