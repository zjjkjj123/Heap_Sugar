package other;

public class Result {
	public String getResult(String input){
		String[] str=input.split(",");//���ַ���ת�����ַ�������
		int[] temp=new int[str.length];//���ַ�������ת������������
		int[] result=new int[temp.length];//������
		int AllProduct=1;//�����������ĳ˻�
		String s="";
		for(int i=0;i<str.length;i++){
			temp[i]=Integer.valueOf(str[i]);
			System.out.println(temp[i]);
		}
		System.out.println();
		for(int i=0;i<temp.length;i++){
			AllProduct*=temp[i];
		}
		System.out.println(AllProduct);
		System.out.println();
		for(int i=0;i<temp.length;i++){
			result[i]=AllProduct/temp[i];
			System.out.println(result[i]);
		}
		for(int i=0;i<result.length;i++){
			s+=result[i]+",";
		}
		return s;
	}
}
