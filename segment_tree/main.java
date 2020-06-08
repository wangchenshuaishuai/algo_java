package segment_tree;

public class main {
    public static void main(String[] args) {
        for(String item:args){
            System.out.println(item);
        }
        Integer[] arrs = {1,2,3,4,5,6};

        // Merger<Integer> merger = new Merger<Integer>() {
        //     @Override
        //     public Integer merge(Integer a, Integer b){
        //         return a + b;
        //     }
        // };

        // Tree<Integer> tree = new Tree<>(arrs, merger);
        Tree<Integer> tree = new Tree<>(arrs, (a,b)->a+b);
        System.out.println("segment tree test code:"+tree.toString());

        System.out.println("query:"+tree.query(0,5));
    }
}