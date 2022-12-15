import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ASD2 {

    static class Node {
        String currentOld = "";
        Node left;
        Node right;
        Node parent;
        String value;

        public Node(String value) {
            this.left = null;
            this.right = null;
            this.parent = null;
            this.value = value;
        }

        public Node() {

        }

        public static void addNode(String path, Node root, String val) {
            Node tmp = root;
            if (path.equals("")) {
                tmp.value = val;
            } else {
                char[] chars = path.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    if (chars[i] == 'L') {
                        if (tmp.left != null) {
                            if (i == chars.length - 1) {
                                tmp.left.value = val;
                            }
                        } else {
                            Node current;
                            if (i == chars.length - 1) {
                                current = new Node(val);
                            } else {
                                current = new Node();
                            }
                            current.parent = tmp;
                            tmp.left = current;
                        }
                        tmp = tmp.left;
                    }

                    if (chars[i] == 'R') {
                        if (tmp.right != null) {
                            if (i == chars.length - 1) {
                                tmp.right.value = val;
                            }
                        } else {
                            Node current;
                            if (i == chars.length - 1) {
                                current = new Node(val);
                            } else {
                                current = new Node();
                            }
                            current.parent = tmp;
                            tmp.right = current;
                        }
                        tmp = tmp.right;
                    }
                }
            }


        }
        public void findTheOldest(Node node, String tmp) {
            tmp = node.value+tmp;


            if (node.left != null) {
                findTheOldest(node.left, tmp);
            }
            if (node.right != null) {
                findTheOldest(node.right, tmp);
            }
            if(node.left == null && node.right == null ){

                if(currentOld.compareTo(tmp)<0){
                    currentOld = tmp;
                }
            }
        }
        public void showOld(){
            System.out.println(currentOld);
        }
    }


    public static void main(String[] args) throws FileNotFoundException {

        Node root = new Node();
        File file = new File(String.valueOf(args[0]));
        Scanner scanner = new Scanner(file);
        String val;
        String path;
        while (scanner.hasNext()){

            String line = scanner.nextLine();
            String[] strings = line.split(" ");
            val = strings[0];

            if(strings.length <2){

                path = "";
                Node.addNode(path,root,val);
            }else {

                path = strings[1];
                Node.addNode(path,root,val);

            }
        }

        String tmp = "";
        root.findTheOldest(root,tmp);
        root.showOld();


    }
}