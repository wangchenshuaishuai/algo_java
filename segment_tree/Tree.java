package segment_tree;
public class Tree<E>{
    private E[] tree;   
    private E[] data;   
    private Merger<E> merger;

    public Tree(E[] arr, Merger<E> merger){
        this.merger = merger;

        data = (E[])new Object[arr.length];
        for(int i=0; i<arr.length; i++){
            data[i] = arr[i];
        }

        tree = (E[])new Object[4*arr.length];
        buildSegmentTree(0, 0, data.length-1);
    }

    private void buildSegmentTree(int index, int l, int r){
        if(l == r){
            tree[index] = data[l];
            System.out.println("tree["+index+"] : " + data[l]);
            return;
        }

        int leftIndex = leftChild(index);
        int rightIndex = rightChild(index);

        int mid = (r-l)/2 + l;
        buildSegmentTree(leftIndex, l, mid);
        buildSegmentTree(rightIndex, mid+1, r);
        tree[index] = merger.merge(tree[leftIndex], tree[rightIndex]);
    }

    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if(index<0 || index>=data.length){
            throw new IllegalArgumentException("index error:"+index);
        }
        return data[index];
    }

    public E query(int left, int right){
        if(left<0 || left >= data.length || right<0 || right>=data.length || left>right){
            throw new IllegalArgumentException("index is illegal.");
        }
        return query(0, 0, data.length-1, left, right);
    }

    private E query(int treeIndex, int l, int r, int queryL, int queryR){
        if(l == queryL && r==queryR){
            return tree[treeIndex];
        }

        int mid = (r-l)/2 + l;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if(queryL > mid){
            return query(rightTreeIndex, mid+1, r, queryL, queryR);
        }else if (queryR <= mid){
            return query(leftTreeIndex, l, mid, queryL, queryR);
        }else{
            E leftE = query(leftTreeIndex, l, mid, queryL, mid);
            E rightE = query(rightTreeIndex, mid+1, r, mid+1, queryR);
            return merger.merge(leftE, rightE);
        }
    }

    private int leftChild(int index){
        return index*2 + 1;
    }

    private int rightChild(int index){
        return index*2 + 2;
    }

    // static public void main(String[] argv){
    //     String str = "first java algo coding";
    //     System.out.println("hello world "+str);
    // }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append('[');
        for(int i=0; i<tree.length; i++){
            if(tree[i] != null){
                res.append(tree[i]);
            }else{
                res.append("null");
            }
            
            if(i != tree.length-1){
                res.append(" ");
            }else{
                res.append(']');
            }
        }
        return ""+res;
    }
}