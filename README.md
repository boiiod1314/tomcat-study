### Maven仓库依赖查询
>http://mvnrepository.com/

### RFC文档下载地址
>http://www.rfc-editor.org/

### HTTP1.1规范
>http://www.rfc-editor.org/pdfrfc/rfc2068.txt.pdf

### JCP官网
>https://jcp.org/en/home/index

### Servlet3.0规范
>http://download.oracle.com/otndocs/jcp/servlet-3.0-mrel-eval-oth-JSpec/

### Servlet3.0新特性
>https://www.ibm.com/developerworks/cn/java/j-lo-servlet30/

>异步处理

>注解支持 @WebServlet @WebInitParam @WebFilter @WebListener @MultipartConfig

>可插性支持

>ServletContext增强,支持在运行时动态部署 Servlet、过滤器、监听器

>HttpServletRequest对上传文件支持


### Servlet3.1新特性
>强制更改sessionId; 升级协议处理


```java
public interface HttpServletRequest extends ServletRequest {
    /**
     * Change the session id of the current session associated with this
     * request and return the new session id. 
     *
     * @return the new session id
     *
     * @throws IllegalStateException if there is no session associated
     * with the request
     *
     * @since Servlet 3.1
     */
    
    public String changeSessionId();
    
    /**
     * Create an instance of <code>HttpUpgradeHandler</code> for an given
     * class and uses it for the http protocol upgrade processing.
     *
     * @param handlerClass The <code>HttpUpgradeHandler</code> class used for the upgrade.
     *
     * @return an instance of the <code>HttpUpgradeHandler</code>
     *
     * @exception IOException if an I/O error occurred during the upgrade
     * @exception ServletException if the given <code>handlerClass</code> fails to
     * be instantiated
     *
     * @see javax.servlet.http.HttpUpgradeHandler
     * @see javax.servlet.http.WebConnection
     *
     * @since Servlet 3.1
     */
    public <T extends HttpUpgradeHandler> T  upgrade(Class<T> handlerClass) throws IOException, ServletException;

}
```

>支持非阻塞IO
```java
public abstract class ServletInputStream extends InputStream {
    /**
     * Instructs the <code>ServletInputStream</code> to invoke the provided
     * {@link ReadListener} when it is possible to read
     *
     * @param readListener the {@link ReadListener} that should be notified
     *  when it's possible to read.
     *
     * @exception IllegalStateException if one of the following conditions is true
     * <ul>
     * <li>the associated request is neither upgraded nor the async started
     * <li>setReadListener is called more than once within the scope of the same request.
     * </ul>
     *
     * @throws NullPointerException if readListener is null
     *
     * @since Servlet 3.1

     */
    public abstract void setReadListener(ReadListener readListener);
}
```
```java
public abstract class ServletOutputStream extends OutputStream  {
    /**
     * Instructs the <code>ServletOutputStream</code> to invoke the provided
     * {@link WriteListener} when it is possible to write
     *
     *
     * @param writeListener the {@link WriteListener} that should be notified
     *  when it's possible to write
     *
     * @exception IllegalStateException if one of the following conditions is true
     * <ul>
     * <li>the associated request is neither upgraded nor the async started
     * <li>setWriteListener is called more than once within the scope of the same request.
     * </ul>
     *
     * @throws NullPointerException if writeListener is null
     *
     * @since Servlet 3.1
     */
    public abstract void setWriteListener(WriteListener writeListener);
}
```
