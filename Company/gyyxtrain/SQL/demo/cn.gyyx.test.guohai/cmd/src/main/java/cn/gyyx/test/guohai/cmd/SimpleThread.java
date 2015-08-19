/*-------------------------------------------------------------------------
* 版权所有：北京光宇在线科技有限责任公司
* 作者：guohai
* 联系方式：guohai@gyyx.cn
* 创建时间： 2014年9月10日
* 版本号：v1.0
* 本类主要用途描述：
* xxxxxxxxxxxxxxxxxxxxxx
-------------------------------------------------------------------------*/
package cn.gyyx.test.guohai.cmd;

public class SimpleThread extends Thread {
	private int countDown=5;
	private static int threadCount=0;
	
	public SimpleThread(){
		super("" + ++threadCount);
		start();
	}
	
	public String toString(){
		return "#" + getName() +":"+ countDown;
	}
	
	public void run(){
		while(true){
			System.out.println(this);
			if(--countDown ==0)return;
		}
	}
}
