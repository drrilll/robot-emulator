package dang.antlr.parser;

/*
 * ANTLRNoCaseFileStream.java
 *
 * Created on January 25, 2008, 2:12 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
  
import java.io.*;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CharStream;
 
/**
 *
 * @author Jim Idle
 */
public class ANTLRNoCaseFileStream  extends ANTLRFileStream {
    public ANTLRNoCaseFileStream(String fileName) throws IOException {
        super(fileName, null);
    }
 
    public ANTLRNoCaseFileStream(String fileName, String encoding)
    throws IOException {
        super(fileName, encoding);
    }
 
    public int LA(int i) {
        if ( i==0 ) {
            return 0; // undefined
        }
        if ( i<0 ) {
            i++; // e.g., translate LA(-1) to use offset 0
        }
 
        if ( (p+i-1) >= n ) {
 
            return CharStream.EOF;
        }
        return Character.toLowerCase(data[p+i-1]);
    }
}
