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
			//��ʼ��������������
			Vector<String> charsettype = new Vector<String>();
			
			LinkedList tafileList = (LinkedList)thashlist.get(i);//��18���ļ���ѭ����ÿ��ȡһ����
			
			
		    
			if (tafileList != null && tafileList.size()>1) {//���������Ԫ������
				
				File tfirstFile=(File)tafileList.get(0);//ȡ��һ��Ԫ�س�����
				//����һ��Ԫ�صı������ͷ���������
			    FileCharsetDetector fcd=new FileCharsetDetector();
			    
			     charsettype.add(fcd.guestFileEncoding(tfirstFile.getAbsolutePath()));
			 
			     charsettype.setSize(100);
		     //�½�һ���ļ�����Ȼ��tfirstFile����
			 LinkedList filelist1=new LinkedList<File>();
			 filelist1.add(tfirstFile);
		     //Ȼ���½�һ��hashtable�����ҽ���ֵΪ0�ĵط�������ո��½��ĵ�һ������
			 Hashtable<Integer,List<File>> shashtable=new Hashtable<Integer,List<File>>();
			 shashtable.put(0,filelist1);
				
				
				for(int j=1;j< tafileList.size();j++){
				File tfile=(File)tafileList.get(j);//�ӵڶ����ļ�ѭ��ȡ����
				String tfiletype=fcd.guestFileEncoding(tfile.getAbsolutePath());
				int k=0;
				while(charsettype.get(k)!=null){
					if(tfiletype==charsettype.get(k)){
						//����tfile�����ֵΪk���ļ����С�
						shashtable.get(k).add(tfile);
						break;
					}else{
						k++;
					
					}
					
					
				}
			    if(charsettype.get(k)==null){
			    	charsettype.add(k,tfiletype);
			    	//�½�һ���ļ����������ļ���������С�
			    	LinkedList nfilelist=new LinkedList<File>();
			    	nfilelist.add(tfile);
			    	
			       //��hashtable�м�ֵΪk�ĵط������������
			    	shashtable.put(k,nfilelist);
			    }
				}
				//��һ��forѭ������hashtable��ÿһ����ȡ�����������½�һ��simiHash�࣬���ҵ�������findsimi����
				for(int n=0;n<shashtable.size();n++){
					
					LinkedList shahlist = (LinkedList)shashtable.get(n);
					SimiHash sf=new SimiHash();
					sf.simifind(shahlist);
					
				}
			}
		
		    
		
		
		
		
		
		
		
		
		
		
		}
		
		
	}

}
