package basic;



import java.lang.* ;

public interface nsICharsetDetector {

	public void Init(nsICharsetDetectionObserver observer) ;
	public boolean DoIt(byte[] aBuf, int aLen, boolean oDontFeedMe) ;
	public void Done() ;
}

