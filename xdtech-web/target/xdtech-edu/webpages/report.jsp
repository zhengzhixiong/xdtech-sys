<%@ page import="java.io.*" %>
<%
    String imagePath = System.getProperty("***") + "/web/tmp";

    String name = request.getParameter("image");
    imagePath += "/" + name;

    response.reset();

    OutputStream output = response.getOutputStream();// 得到输出流

    response.setContentType("image/png");
//        ServletContext context = getServletContext();// 得到背景对象
    File f = new File(imagePath);

    InputStream imageIn = new FileInputStream(f);
    BufferedInputStream bis = new BufferedInputStream(imageIn);// 输入缓冲流
    BufferedOutputStream bos = new BufferedOutputStream(output);// 输出缓冲流
    byte data[] = new byte[4096];// 缓冲字节数
    int size = 0;
    size = bis.read(data);
    while (size != -1) {
        bos.write(data, 0, size);
        size = bis.read(data);
    }
    bis.close();
    bos.flush();// 清空输出缓冲流
    bos.close();
    output.close();
%>
