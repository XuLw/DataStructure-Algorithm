package dataStructure;

public class Main {

	 // ��������˳��
    private static void shuffle(Object[] arr){

        for(int i = arr.length-1 ; i >= 0 ; i --){
            int pos = (int) (Math.random() * (i+1));
            Object t = arr[pos];
            arr[pos] = arr[i];
            arr[i] = t;
        }
    }

    // ���Զ����������е�remove
    public static void main(String[] args) {

        BST<Integer, Integer> bst = new BST<Integer, Integer>();

        // ȡn��ȡֵ��Χ��[0...n)����������Ž�������������
        int N = 10000;
        for(int i = 0 ; i < N ; i ++){
            Integer key = new Integer((int)(Math.random()*N));
            // Ϊ�˺������Է���,����valueֵȡ��keyֵһ��
            bst.insert(key, key);
        }
        // ע��, ����������ɵ��������ظ�, ����bst�е����������������С��n��

        // order�����д��[0...n)������Ԫ��
        Integer order[] = new Integer[N];
        for( int i = 0 ; i < N ; i ++ )
            order[i] = new Integer(i);
        // ����order�����˳��
        shuffle( order );

        // ����ɾ��[0...n)��Χ�������Ԫ��
        for( int i = 0 ; i < N ; i ++ )
            if( bst.contain( order[i] )){
                bst.remove( order[i] );
                System.out.println("After remove " + order[i] + " size = " + bst.size() );
            }

        // ������������������Ӧ��Ϊ��
        System.out.println( bst.size() );
    }
}
