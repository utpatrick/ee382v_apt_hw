package LowerInputStream;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class LowerInputStream extends InputStream {
	
	private final InputStream inStream;
	
	public LowerInputStream(InputStream inputStream){
		this.inStream = inputStream;
	}
	
	public LowerInputStream(String fileName) throws FileNotFoundException {
        inStream = new BufferedInputStream(
                         new FileInputStream(fileName)
        );
    }
	
	@Override
    public int read() throws IOException {
        int ch = inStream.read();
        return (ch == -1 ? ch : Character.toLowerCase((char)ch));
    }

}
