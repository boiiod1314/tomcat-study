package tomcat.study.chapter02;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by huwei on 2017/8/14.
 */
public class DefaultServletOutputStream extends ServletOutputStream {

    private OutputStream out;

    public DefaultServletOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {

    }
}
