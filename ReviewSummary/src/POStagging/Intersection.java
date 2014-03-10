package POStagging;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;



public class Intersection {

	public String[] intersect(Map m1,Map m2)
	{
		String[] set1;
		String[] set2;
		String text1="",text2="";
		Set set=m1.entrySet();
		Iterator it=set.iterator();
		while(it.hasNext())
		{
			Map.Entry me=(Entry) it.next();
			text1=text1+me.getKey()+"::";
			
		}
		//System.out.println(m2);
		Set s=m2.entrySet();
		Iterator it1=s.iterator();
		while(it1.hasNext())
		{
			Map.Entry me=(Entry) it1.next();
			text2=text2+me.getKey()+"::";
			
		}
		
		String temp ="";

		set1=text1.split("::");
		set2=text2.split("::");
		int i=0;
		for(String s1:set1)
		{
			
			for(String s2:set2)
			{
				//System.out.println(">>"+s1);
				//System.out.println("<<"+s2);
				if(s1.equals(s2))
				{
					//System.out.println(s1);
					temp=temp+s1+"::";
					i++;
				}
			}
		}
		String[] result=temp.split("::");
		return result;
	}	
	
}
