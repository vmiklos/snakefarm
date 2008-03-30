import java.io.*;

public class TestCompare
{
	public static void main(String[] args)
	{
		String originalFileName = args[0];
		String testFileName = args[1];

		try
		{
			BufferedReader br1 = new BufferedReader(new FileReader(new File(testFileName)));
			BufferedReader br2 = new BufferedReader(new FileReader(new File(originalFileName)));
			
			String line1;
			String line2 = br2.readLine();
			boolean testpartFound = false;
			boolean failed = false;
			while((line1 = br1.readLine()) != null)
			{
				if(testpartFound)
				{
					if(line1.equals(line2)) 
						line2 = br2.readLine();
					else
						failed = true;
				}
				else
				{
					if(line1.equals(line2))
					{
						testpartFound = true;
						line2 = br2.readLine();
					}
				}
			}
			
			if((line2 == null) && (!failed))
				System.out.println("PASSED");
			else
				System.out.println("FAILED");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
