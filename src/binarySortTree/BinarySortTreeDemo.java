package binarySortTree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {7,3,10,12,5,1,9};
//        BinarySortTree binarySortTree = new BinarySortTree();
//        for (int i = 0; i < arr.length; i++) {
//            binarySortTree.add(new Node(arr[i]));
//        }
//
//        System.out.println("中序遍历二叉排序树");
//        binarySortTree.infixOrder();
//
//        binarySortTree.delNode(12);
//        System.out.println("删除节点后");
//        binarySortTree.infixOrder();

        int[] arr = {4,3,6,5,7,8};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(i));
        }

        System.out.println("中序遍历");
        binarySortTree.infixOrder();


        System.out.println("树的高度"+binarySortTree.getRoot().height());
        System.out.println("树的左子树高度="+binarySortTree.getRoot().leftHeight());
        System.out.println("树的右子树高度="+binarySortTree.getRoot().rightHeight());
    }
}
