
/**
 * @author Anatoliy Shikin
 */
public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> numbs = new MyArrayList<>();
        numbs.add(1);
        numbs.add(1);
        numbs.add(7);
        numbs.add(8);
        System.out.println(numbs);
        numbs.set(1, 5);
        System.out.println(numbs);
        System.out.println(numbs.get(2));
        System.out.println(numbs.size());
        System.out.println(numbs.subList(1, 3));
        System.out.println(numbs);
        System.out.println(numbs.remove(2));
        System.out.println(numbs);

        System.out.println("-".repeat(40));

        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
        myLinkedList.add("Яблоки");
        myLinkedList.add("Вишня");
        myLinkedList.add("Вишня");
        myLinkedList.add("Бананы");
        System.out.println(myLinkedList);
        myLinkedList.set(2, "Ананас");
        System.out.println(myLinkedList);
        System.out.println(myLinkedList.get(3));
        System.out.println(myLinkedList.size());
        myLinkedList.subList(1, 3);
        System.out.println(myLinkedList);
        System.out.println(myLinkedList.remove(2));
        System.out.println(myLinkedList);
    }
}