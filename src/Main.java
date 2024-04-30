import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        CustomList<String> str = new CustomList<>();
        str.addLast("what happened");
        str.addLast(", happened");
        str.addFirst( "Lad ");
//        stream()
        str.stream().skip(1).forEach(System.out::println);
//        iterator()
        for(String value : str){
            System.out.print(value);
        }
//        System.out.print(str.removeLast());
//        System.out.print(str.head.value);
//        System.out.print(str.head.next.value);
//        System.out.print(str.head.next.next.value);
//        System.out.print(str.get(2));
    }
}