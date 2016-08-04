package other;

public class Result {
	public String getResult(String input){
		String[] str=input.split(",");//将字符串转换成字符串数组
		int[] temp=new int[str.length];//将字符串数组转换成整型数组
		int[] result=new int[temp.length];//保存结果
		int AllProduct=1;//保存所有数的乘积
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
