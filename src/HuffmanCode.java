import java.io.*;
import java.util.*;

public class HuffmanCode {

    static Map<Byte, String> huffmanCodes = new HashMap<Byte, String>();
    static StringBuilder stringBuilder = new StringBuilder();


    public static void main(String[] args) {
//        String content = "i like like like java do you like a java";
//        byte[] contentBytes = content.getBytes();
//        System.out.println(new String(contentBytes));
//
//        List<Node> nodes = getNodes(contentBytes);
////        System.out.println("node=" + nodes);
//
////        System.out.println("赫夫曼树");
//        Node huffmanTreeRoot = createHuffmanTree(nodes);
////        System.out.println("前序遍历");
////        huffmanTreeRoot.preOrder();
//
//        getCodes(huffmanTreeRoot, "", stringBuilder);
//        System.out.println("生成的赫夫曼编码表" + huffmanCodes);
//
//        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
//        System.out.println("huffmanCodeBytes=" + Arrays.toString(huffmanCodeBytes));
//
//        byte[] sourceBytes = decode(huffmanCodes, huffmanCodeBytes);
//        System.out.println("原来的字符串=" + new String(sourceBytes));

//        String srcFile = "C:\\Heima\\DataStructure\\src.bmp";
//        String dstFile = "C:\\Heima\\DataStructure\\dst.zip";
//        zipFile(srcFile,dstFile);
//        System.out.println("压缩文件ok");

        String zipFile = "C:\\Heima\\DataStructure\\dst.zip";
        String dstFile = "C:\\Heima\\DataStructure\\src2.bmp";
        unZipFile(zipFile,dstFile);
        System.out.println("解压成功！");


    }

    public static void unZipFile(String zipFile,String dstFile){
        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            byte[] huffmanBytes = (byte[])ois.readObject();
            Map<Byte,String> huffmanCodes = (Map<Byte,String>)ois.readObject();
            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            os = new FileOutputStream(dstFile);
            os.write(bytes);
        }catch (Exception e){
            //TODO:handle exception
            System.out.println(e.getMessage());

        }finally {
            try {
                os.close();
                ois.close();
                is.close();

            }catch (Exception e2){
                //TODO:handle exception
                System.out.println(e2.getMessage());
            }
        }
    }

    public static void zipFile(String srcFile, String dstFile) {
        OutputStream os = null;
        ObjectOutputStream oos = null;

        FileInputStream is = null;
        try {
            is = new FileInputStream(srcFile);
            //这个方法通常返回文件的总大小（以字节为单位）
            byte[] b = new byte[is.available()];
            //is.read(b) 会读取文件中的字节，直到字节数组 b 被填满，或者达到文件末尾。
            //读取的数据将按顺序存储在 b 数组中，这样 b 数组就包含了整个文件的内容。
            is.read(b);
            byte[] huffmanBytes = huffmanZip(b);
            os = new FileOutputStream(dstFile);
            oos = new ObjectOutputStream(os);
            oos.writeObject(huffmanBytes);
            //通过将 huffmanCodes 声明为全局静态变量，你可以在类的其他部分（包括 huffmanZip 方法之外）
            // 访问和使用 huffmanZip 方法内部生成的 huffmanCodes。
            oos.writeObject(huffmanCodes);

        } catch (Exception e) {
            //TODO:handle exception
            System.out.println(e.getMessage());
        } finally {
            try {
                is.close();
                oos.close();
                os.close();
            } catch (Exception e) {
                //TODO:handle exception
                System.out.println(e.getMessage());
            }
        }
    }

    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }

        //把赫夫曼编码表进行调换，因为反向查询
        HashMap<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        ArrayList<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while (flag) {
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            list.add(b);
            i += count;
        }
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    private static String byteToBitString(boolean flag, byte b) {
        int temp = b;
        if (flag) {
            //异或计算 256 在二进制下表示为 100000000（即第9位是1，其他位是0）。
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {

            //str.substring(startIndex)：从 str 中提取从 startIndex 开始到字符串末尾的子字符串。
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {

        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            //huffmanCodes.get(b) 的作用是使用 b 作为键，
            // 从 huffmanCodes 这个 Map 中找到对应的值（一个字符串），并返回该值。
            stringBuilder.append(huffmanCodes.get(b));
        }
//        System.out.println("测试stringBuilder="+stringBuilder.toString());

        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        getCodes(root.left, "0", stringBuilder);
        getCodes(root.right, "1", stringBuilder);
        return huffmanCodes;
    }

    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);

        if (node != null) {
            if (node.data == null) {
                getCodes(node.left, "0", stringBuilder2);
                getCodes(node.right, "1", stringBuilder2);
            } else {
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("赫夫曼树为空");
        }
    }

    private static List<Node> getNodes(byte[] bytes) {
        ArrayList<Node> nodes = new ArrayList<>();
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }

        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }


    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

}

class Node implements Comparable<Node> {
    Byte data;
    int weight;
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {

        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node[ data = " + data + "weight=" + weight + "]";
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
