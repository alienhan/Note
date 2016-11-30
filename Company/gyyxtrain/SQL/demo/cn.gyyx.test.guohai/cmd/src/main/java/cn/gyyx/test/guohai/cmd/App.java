package cn.gyyx.test.guohai.cmd;

/**
 * Hello world!
 *
 */
public class App 
{
	private int i;
	public void next(){
		i++;
		i++;
	}
	
	public int getValue(){
		return i;
	}
	
    public static void main( String[] args )
    {
        final App ae = new App();
        new Thread("Watcher"){
        	public void run(){
        		while(true){
        			int val=ae.getValue();
        			if(val%2!=0){
        				System.out.println(val);
        				System.exit(0);
        			}
        		}
        	}
        }.start();
        
        while(true){
        	ae.next();
        }
        		
    }
}
