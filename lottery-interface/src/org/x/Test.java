package org.x;

import org.common.util.ThreadPool;

import org.x.LogInsert;

public class Test {
	
	public void process(){
		ThreadPool.mThreadPool.execute(new LogInsert("1", "2", "3"));
	}

}
