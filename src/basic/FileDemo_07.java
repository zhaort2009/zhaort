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
	
    	
    	
        LinkedList fileList = new LinkedList();			// 创建一个连接列表fileList
		
		File baseDir = new File(baseDirName);		// 创建一个File对象
		
		if (!baseDir.exists() || !baseDir.isDirectory()) {	// 判断目录是否存在
			System.out.println("文件查找失败：" + baseDirName + "不是一个目录！");
			return fileList;
		}
		// 根目录加入链，取出并删除	
		fileList.addLast(baseDir);
		
		File firstFile = null;
		firstFile = (File) fileList.removeFirst();
    	
		//取出根目录中的所有文件，并循环检查。是文件加入链，是目录调用directorylist。
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
		//取出目录中的所有文件，并循环检查，是文件加入链，是目录循环调用自己。
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
	
	// 查找符合条件的文件，并返回一个文件名列表
	/*public static List findFiles(String baseDirName, String targetFileName) {
		
		LinkedList fileList = new LinkedList();			// 创建一个连接列表fileList
		
		File baseDir = new File(baseDirName);		// 创建一个File对象
		
		if (!baseDir.exists() || !baseDir.isDirectory()) {	// 判断目录是否存在
			System.out.println("文件查找失败：" + baseDirName + "不是一个目录！");
			return fileList;
		}
		
		String tempName = null;
		
		fileList.addLast(baseDir);// 将File对象添加到fileList列表中
		
		File tempFile = null;
		
		while (!fileList.isEmpty()) {
			// 从队列中取目录
			tempFile = (File) fileList.removeFirst();// 将列表中的第一个元素取出并删除
			

			if (tempFile.exists() && tempFile.isDirectory()) {
				
				File[] files = tempFile.listFiles();
				
				for (int i = 0; i < files.length; i++) {
					// 如果是目录则放进队列
					if (files[i].isDirectory()) {
						fileList(sf[i],fs);
					} else {
						// 如果是文件则根据文件名与目标文件名进行匹配
						tempName = files[i].getName();

						if (FileDemo_07.matchWord(targetFileName, tempName)) {
							// 匹配成功，将文件名添加到结果集


							fileList.add(files[i]);
						
						}
					}
				}return fileList;
			}
		}
		return fileList;
	}*/
	// 文件名的通配符匹配，如果匹配成功则返回true，否则返回false
	private static boolean matchWord(String pattern, String str) {
		int patternLength = pattern.length();
		int strLength = str.length();
		int strIndex = 0;
		char ch;
		for (int patternIndex = 0; patternIndex < patternLength; patternIndex++) {
			ch = pattern.charAt(patternIndex);
			if (ch == '*') {
				// 通配符星号*表示可以匹配任意多个字符
				while (strIndex < strLength) {
					if (matchWord(pattern.substring(patternIndex + 1), str
							.substring(strIndex))) {
						return true;
					}
					strIndex++;
				}
			} else if (ch == '?') {
				// 通配符问号?表示匹配任意一个字符
				strIndex++;
				if (strIndex > strLength) {
					// 表示str中已经没有字符匹配?了。
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
		String baseDIR = "D:\\temp"; // 在此目录中找文件
		a.setvector();
		for(int i = 0; i <18;i++ ){
			String tempName = type.elementAt(i); 
			list.put(i,a.findFiles(baseDIR, tempName));
		  
		}
		return list;
		}
	
	/*public static void main(String[] agvs){
		FileDemo_07 a=new FileDemo_07();
		String baseDIR = "D:\\temp"; // 在此目录中找文件
		a.setvector();
		String txtName = type.elementAt(0); // 找扩展名为txt的文件
		LinkedList txtList = a.findFiles(baseDIR, txtName);
		if (txtList.size() == 0) {
			System.out.println("No File Fount.");
		} else {
			for (int i = 0; i < txtList.size(); i++) {
				File tem=(File)txtList.get(i);
           System.out.println(tem.getName());// 显示查找结果。
			}
		}
	}*/
	/*public static void main(String[] paramert) {
		String baseDIR = "D:/temp"; // 在此目录中找文件
		String txtName = "*.txt"; // 找扩展名为txt的文件
		int countNumber = 3; // 最多返回3个文件
		System.out.println("在D盘的temp目录下查找扩展名为" + txtName + "的文件如下：");
		List txtList = FileDemo_07.findFiles(baseDIR, txtName, countNumber);
		if (txtList.size() == 0) {
			System.out.println("No File Fount.");
		} else {
			for (int i = 0; i < txtList.size(); i++) {
				System.out.println(txtList.get(i));// 显示查找结果。
			}
		}
//		String docName = "*.doc"; // 找扩展名为doc的文件
//		System.out.println("在D盘的temp目录下查找扩展名为" + docName + "的文件如下：");
//		List docList = FileDemo_07.findFiles(baseDIR, docName, 2);
//		if (docList.size() == 0) {
//			System.out.println("No File Fount.");
//		} else {
//			for (int i = 0; i < docList.size(); i++) {
//				System.out.println(docList.get(i));// 显示查找结果。
//			}
//		}
//		String jpgName = "*.jpg"; // 找扩展名为jpg的文件
//		System.out.println("在D盘的temp目录下查找扩展名为" + jpgName + "的文件如下：");
//		List jpgList = FileDemo_07.findFiles(baseDIR, jpgName, countNumber);
//		if (jpgList.size() == 0) {
//			System.out.println("No File Fount.");
//		} else {
//			for (int i = 0; i < jpgList.size(); i++) {
//				System.out.println(jpgList.get(i));// 显示查找结果。
//			}
//		}
	}*/
}
