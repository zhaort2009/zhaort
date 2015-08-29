package basic;
import java.io.File;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;


public class system {
	 
	public static void main(String[] agvs)throws Exception{
		
		Hashtable<Integer,List<File>> thashlist=new Hashtable<Integer,List<File>>();
		
		thashlist=FileDemo_07.hashStore();
		
		
		
		for(int i=0;i<thashlist.size();i++){
			//初始化编码类型容器
			Vector<String> charsettype = new Vector<String>();
			
			LinkedList tafileList = (LinkedList)thashlist.get(i);//对18条文件链循环。每次取一条。
			
			
		    
			if (tafileList != null && tafileList.size()>1) {//如果有两个元素以上
				
				File tfirstFile=(File)tafileList.get(0);//取第一个元素出来，
				//将第一个元素的编码类型放在容器中
			    FileCharsetDetector fcd=new FileCharsetDetector();
			    
			     charsettype.add(fcd.guestFileEncoding(tfirstFile.getAbsolutePath()));
			 
			     charsettype.setSize(100);
		     //新建一条文件链，然后将tfirstFile加入
			 LinkedList filelist1=new LinkedList<File>();
			 filelist1.add(tfirstFile);
		     //然后新建一个hashtable，并且将键值为0的地方，存入刚刚新建的第一条链。
			 Hashtable<Integer,List<File>> shashtable=new Hashtable<Integer,List<File>>();
			 shashtable.put(0,filelist1);
				
				
				for(int j=1;j< tafileList.size();j++){
				File tfile=(File)tafileList.get(j);//从第二个文件循环取出。
				String tfiletype=fcd.guestFileEncoding(tfile.getAbsolutePath());
				int k=0;
				while(charsettype.get(k)!=null){
					if(tfiletype==charsettype.get(k)){
						//将此tfile加入键值为k的文件链中。
						shashtable.get(k).add(tfile);
						break;
					}else{
						k++;
					
					}
					
					
				}
			    if(charsettype.get(k)==null){
			    	charsettype.add(k,tfiletype);
			    	//新建一条文件链，将此文件加入此链中。
			    	LinkedList nfilelist=new LinkedList<File>();
			    	nfilelist.add(tfile);
			    	
			       //在hashtable中键值为k的地方，存入此链。
			    	shashtable.put(k,nfilelist);
			    }
				}
				//用一个for循环，将hashtable的每一条链取出来，并且新建一个simiHash类，并且调用它的findsimi方法
				for(int n=0;n<shashtable.size();n++){
					
					LinkedList shahlist = (LinkedList)shashtable.get(n);
					SimiHash sf=new SimiHash();
					sf.simifind(shahlist);
					
				}
			}
		
		    
		
		
		
		
		
		
		
		
		
		
		}
		
		
	}

}
