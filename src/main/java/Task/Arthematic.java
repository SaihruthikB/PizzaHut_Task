package Task;

public class Arthematic {
	static int a = 10;
	public static void main(String args[])
	{
		try
		{
			int sum= (Arthematic.a/0);
			//int year =Integer.parseInt(sum);
			System.out.println(sum);
			
		}
		catch (ArithmeticException a1)
		{
			a1.printStackTrace();
		}
		
	}

	
}
