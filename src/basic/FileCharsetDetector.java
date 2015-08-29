package basic;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import basic.nsDetector;
import basic.nsICharsetDetectionObserver;

/**
 * ����JCharDet��ȡ�ļ��ַ��� JCharDet ��mozilla�Զ��ַ���̽���㷨�����java��ֲ����ٷ���ҳΪ��
 * http://jchardet.sourceforge.net/
 */
public class FileCharsetDetector {

 private boolean found = false;

 /**
  * �����ȫƥ��ĳ���ַ�������㷨, ������Ա�����ַ���������. ����(��������ļ�)��ֵ��ΪĬ��ֵ null, ��ʱӦ����ѯ����
  */
 private String encoding = null;

 /*public static void main(String[] argv) throws Exception {

  System.out.println("�ļ�����:"
    + new FileCharsetDetector().guestFileEncoding("C:\\����1.txt"));
 }
*/
 /**
  * ����һ���ļ�(File)���󣬼���ļ�����
  *
  * @param file
  *            File����ʵ��
  * @return �ļ����룬���ޣ��򷵻�null
  * @throws FileNotFoundException
  * @throws IOException
  */
 public String guestFileEncoding(File file) throws FileNotFoundException,
   IOException {
  return geestFileEncoding(file, new nsDetector());
 }

 /**
  * ��ȡ�ļ��ı���
  *
  * @param file
  *            File����ʵ��
  * @param languageHint
  *            ������ʾ������� eg��1 : Japanese; 2 : Chinese; 3 : Simplified Chinese;
  *            4 : Traditional Chinese; 5 : Korean; 6 : Dont know (default)
  * @return �ļ����룬eg��UTF-8,GBK,GB2312��ʽ�����ޣ��򷵻�null
  * @throws FileNotFoundException
  * @throws IOException
  */
 public String guestFileEncoding(File file, int languageHint)
   throws FileNotFoundException, IOException {
  return geestFileEncoding(file, new nsDetector(languageHint));
 }

 /**
  * ��ȡ�ļ��ı���
  *
  * @param path
  *            �ļ�·��
  * @return �ļ����룬eg��UTF-8,GBK,GB2312��ʽ�����ޣ��򷵻�null
  * @throws FileNotFoundException
  * @throws IOException
  */
 public String guestFileEncoding(String path) throws FileNotFoundException,
   IOException {
  return guestFileEncoding(new File(path));
 }

 /**
  * ��ȡ�ļ��ı���
  *
  * @param path
  *            �ļ�·��
  * @param languageHint
  *            ������ʾ������� eg��1 : Japanese; 2 : Chinese; 3 : Simplified Chinese;
  *            4 : Traditional Chinese; 5 : Korean; 6 : Dont know (default)
  * @return
  * @throws FileNotFoundException
  * @throws IOException
  */
 public String guestFileEncoding(String path, int languageHint)
   throws FileNotFoundException, IOException {
  return guestFileEncoding(new File(path), languageHint);
 }

 /**
  * ��ȡ�ļ��ı���
  *
  * @param file
  * @param det
  * @return
  * @throws FileNotFoundException
  * @throws IOException
  */
 private String geestFileEncoding(File file, nsDetector det)
   throws FileNotFoundException, IOException {
  // Set an observer...
  // The Notify() will be called when a matching charset is found.
  det.Init(new nsICharsetDetectionObserver() {
   public void Notify(String charset) {
    found = true;
    encoding = charset;
   }
  });

  BufferedInputStream imp = new BufferedInputStream(new FileInputStream(
    file));

  byte[] buf = new byte[1024];
  int len;
  boolean done = false;
  boolean isAscii = true;

  while ((len = imp.read(buf, 0, buf.length)) != -1) {
   // Check if the stream is only ascii.
   if (isAscii)
    isAscii = det.isAscii(buf, len);

   // DoIt if non-ascii and not done yet.
   if (!isAscii && !done)
    done = det.DoIt(buf, len, false);
  }
  det.DataEnd();

  if (isAscii) {
   encoding = "ASCII";
   found = true;
  }

  if (!found) {
   String prob[] = det.getProbableCharsets();
   if (prob.length > 0) {
    // ��û�з�������£���ȡ��һ�����ܵı���
    encoding = prob[0];
   } else {
    return null;
   }
  }
  return encoding;
 }
}