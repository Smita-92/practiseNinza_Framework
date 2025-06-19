package com.ninza.hrm.api.genericUtility;

import java.util.Random;

public class JavaUtility {
	
	public int randomNum() {
		Random random=new Random();
		int ran=random.nextInt(5000);
		return ran;
				
	}

}
