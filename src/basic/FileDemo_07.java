package basic;


import java.io.File;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFileChooser;

public class FileDemo_07 {
	
	private static Vector<String> type = new Vector<String>();
    public void setvector(){
    	type.add("*.txt");
    	type.add("*.html");
    	type.add("*.java");
    	
    	type.add("*.ini");
    	type.add("*.log");
    	type.add("*.dat");
    	type.add("*.bin");
    	type.add("*.hex");
    	type.add("*.c");
    	
    	type.add("*.h");
    	type.add("*.hpp");
    	type.add("*.prj");
    	
    	type.add("*.htm");
    	type.add("*.js");
    	type.add("*.xml");
    	type.add("*.css");
    	type.add("*.rb");
    	type.add("*.php");
    	
    	
    	
    	
    	
    }
    public  LinkedList findFiles(String baseDirName, String targetFileName){
	
    	
    	
        LinkedList fileList = new LinkedList();			// ����һ�������б�fileList
		
		File baseDir = new File(baseDirName);		// ����һ��File����
		
		if (!baseDir.exists() || !baseDir.isDirectory()) {	// �ж�Ŀ¼�Ƿ����
			System.out.println("�ļ�����ʧ�ܣ�" + baseDirName + "����һ��Ŀ¼��");
			return fileList;
		}
		// ��Ŀ¼��������ȡ����ɾ��	
		fileList.addLast(baseDir);
		
		File firstFile = null;
		firstFile = (File) fileList.removeFirst();
    	
		//ȡ����Ŀ¼�е������ļ�����ѭ����顣���ļ�����������Ŀ¼����directorylist��
		String tempName = null;
		
		File[] files = firstFile.listFiles();
    	
		for(int i = 0; i < files.length;i++ ){
			if(!files[i].isDirectory()){
				tempName = files[i].getName();
				if (FileDemo_07.matchWord(targetFileName, tempName)) {
				fileList.add(files[i]);}
			}
			else
				directoryList(files[i],fileList,targetFileName);
		}
		return fileList;
	}
    
    
    
    
	public void directoryList(File file,List ls,String targetFileName) {
		//ȡ��Ŀ¼�е������ļ�����ѭ����飬���ļ�����������Ŀ¼ѭ�������Լ���
		String tN = null;
        File[] files = file.listFiles();
        if (files != null) {
              for (File f : files) {
            	  if(!f.isDirectory()){
            		  tN = f.getName();
            		  if (FileDemo_07.matchWord(targetFileName, tN)){ls.add(f);}
            		  }
                    directoryList(f,ls,targetFileName);
              }
        }
   }
	
	// ���ҷ����������ļ���������һ���ļ����б�
	/*public static List findFiles(String baseDirName, String targetFileName) {
		
		LinkedList fileList = new LinkedList();			// ����һ�������б�fileList
		
		File baseDir = new File(baseDirName);		// ����һ��File����
		
		if (!baseDir.exists() || !baseDir.isDirectory()) {	// �ж�Ŀ¼�Ƿ����
			System.out.println("�ļ�����ʧ�ܣ�" + baseDirName + "����һ��Ŀ¼��");
			return fileList;
		}
		
		String tempName = null;
		
		fileList.addLast(baseDir);// ��File������ӵ�fileList�б���
		
		File tempFile = null;
		
		while (!fileList.isEmpty()) {
			// �Ӷ�����ȡĿ¼
			tempFile = (File) fileList.removeFirst();// ���б��еĵ�һ��Ԫ��ȡ����ɾ��
			

			if (tempFile.exists() && tempFile.isDirectory()) {
				
				File[] files = tempFile.listFiles();
				
				for (int i = 0; i < files.length; i++) {
					// �����Ŀ¼��Ž�����
					if (files[i].isDirectory()) {
						fileList(sf[i],fs);
					} else {
						// ������ļ�������ļ�����Ŀ���ļ�������ƥ��
						tempName = files[i].getName();

						if (FileDemo_07.matchWord(targetFileName, tempName)) {
							// ƥ��ɹ������ļ�����ӵ������


							fileList.add(files[i]);
						
						}
					}
				}return fileList;
			}
		}
		return fileList;
	}*/
	// �ļ�����ͨ���ƥ�䣬���ƥ��ɹ��򷵻�true�����򷵻�false
	private static boolean matchWord(String pattern, String str) {
		int patternLength = pattern.length();
		int strLength = str.length();
		int strIndex = 0;
		char ch;
		for (int patternIndex = 0; patternIndex < patternLength; patternIndex++) {
			ch = pattern.charAt(patternIndex);
			if (ch == '*') {
				// ͨ����Ǻ�*��ʾ����ƥ���������ַ�
				while (strIndex < strLength) {
					if (matchWord(pattern.substring(patternIndex + 1), str
							.substring(strIndex))) {
						return true;
					}
					strIndex++;
				}
			} else if (ch == '?') {
				// ͨ����ʺ�?��ʾƥ������һ���ַ�
				strIndex++;
				if (strIndex > strLength) {
					// ��ʾstr���Ѿ�û���ַ�ƥ��?�ˡ�
					return false;
				}
			} else {
				if ((strIndex >= strLength) || (ch != str.charAt(strIndex))) {
					return false;
				}
				strIndex++;
			}
		}
		return (strIndex == strLength);
	}
	public static Hashtable<Integer,List<File>>  hashStore(){
		Hashtable<Integer,List<File>> list=new Hashtable<Integer,List<File>>();
		FileDemo_07 a=new FileDemo_07();
		String baseDIR = "D:\\temp"; // �ڴ�Ŀ¼�����ļ�
		a.setvector();
		for(int i = 0; i <18;i++ ){
			String tempName = type.elementAt(i); 
			list.put(i,a.findFiles(baseDIR, tempName));
		  
		}
		return list;
		}
	
	/*public static void main(String[] agvs){
		FileDemo_07 a=new FileDemo_07();
		String baseDIR = "D:\\temp"; // �ڴ�Ŀ¼�����ļ�
		a.setvector();
		String txtName = type.elementAt(0); // ����չ��Ϊtxt���ļ�
		LinkedList txtList = a.findFiles(baseDIR, txtName);
		if (txtList.size() == 0) {
			System.out.println("No File Fount.");
		} else {
			for (int i = 0; i < txtList.size(); i++) {
				File tem=(File)txtList.get(i);
           System.out.println(tem.getName());// ��ʾ���ҽ����
			}
		}
	}*/
	/*public static void main(String[] paramert) {
		String baseDIR = "D:/temp"; // �ڴ�Ŀ¼�����ļ�
		String txtName = "*.txt"; // ����չ��Ϊtxt���ļ�
		int countNumber = 3; // ��෵��3���ļ�
		System.out.println("��D�̵�tempĿ¼�²�����չ��Ϊ" + txtName + "���ļ����£�");
		List txtList = FileDemo_07.findFiles(baseDIR, txtName, countNumber);
		if (txtList.size() == 0) {
			System.out.println("No File Fount.");
		} else {
			for (int i = 0; i < txtList.size(); i++) {
				System.out.println(txtList.get(i));// ��ʾ���ҽ����
			}
		}
//		String docName = "*.doc"; // ����չ��Ϊdoc���ļ�
//		System.out.println("��D�̵�tempĿ¼�²�����չ��Ϊ" + docName + "���ļ����£�");
//		List docList = FileDemo_07.findFiles(baseDIR, docName, 2);
//		if (docList.size() == 0) {
//			System.out.println("No File Fount.");
//		} else {
//			for (int i = 0; i < docList.size(); i++) {
//				System.out.println(docList.get(i));// ��ʾ���ҽ����
//			}
//		}
//		String jpgName = "*.jpg"; // ����չ��Ϊjpg���ļ�
//		System.out.println("��D�̵�tempĿ¼�²�����չ��Ϊ" + jpgName + "���ļ����£�");
//		List jpgList = FileDemo_07.findFiles(baseDIR, jpgName, countNumber);
//		if (jpgList.size() == 0) {
//			System.out.println("No File Fount.");
//		} else {
//			for (int i = 0; i < jpgList.size(); i++) {
//				System.out.println(jpgList.get(i));// ��ʾ���ҽ����
//			}
//		}
	}*/
}
