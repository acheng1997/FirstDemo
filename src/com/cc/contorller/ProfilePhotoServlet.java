package com.cc.contorller;

import com.cc.pojo.MyFile;
import com.cc.pojo.User;
import com.cc.server.UserServer;
import com.cc.utils.FileSizeUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "profilePhotoServlet", value = "/profilePhoto")
public class ProfilePhotoServlet extends HttpServlet {
    private UserServer userServer;

    public ProfilePhotoServlet() {
        this.userServer = new UserServer();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
//        给个上传的路径
        String uploadPath = "F:/demo/img/";
//        通过工厂类创建一个对象
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
//        设置缓存区的大小
        diskFileItemFactory.setSizeThreshold(10240);
//        设置临时文件，在上传的路径下新建一个文件夹，放临时文件
        diskFileItemFactory.setRepository(new File("F:/demo/temporary"));
//        得到文件上传的工具类，参数为工厂对象
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
//        设置上传文件的最大大小
        servletFileUpload.setFileSizeMax(1024 * 1024 * 50);
//        设置每次最多上传文化的总共最大大小
        servletFileUpload.setSizeMax(1024 * 1024 * 200);
//        设置编码格式
        servletFileUpload.setHeaderEncoding("UTF-8");
        FileOutputStream fos = null;
//        解析request，每一项包装成一个fileItem对象，因为有多个，所以用链表保存
        try {
            List<FileItem> fileItems = servletFileUpload.parseRequest(req);
            if (fileItems == null || fileItems.size() == 0) {
                req.getSession().setAttribute("UPLOAD_ERROR", "true");
                resp.sendRedirect(req.getContextPath() + "/home.jsp");
                return;
            }
            String fileName = null;
            String uploadName = null;
            User user = new User();
            for (FileItem fileItem : fileItems) {
//                判断是文件还是普通表单
//                isFormField是普通表单
                if (fileItem.isFormField()) {

                } else {
//                    是文件的话就要上传
//                    得到上传文件的文件名
                    fileName = fileItem.getName();
                    //                    判断是否有文件
                    if (fileName == null || "".equals(fileName)) {
                        req.getSession().setAttribute("UPLOAD_ERROR", "true");
                        resp.sendRedirect(req.getContextPath() + "/home.jsp");
                        return;
                    }
//                    封装上传后的文件名（防止上传文件同名）
                    fileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
                    uploadName = uploadPath + fileName;
//                    得到输出流
                    fos = new FileOutputStream(uploadName);
                    byte[] buf = new byte[1024];
//                    得到文件上传的输入流
                    InputStream inputStream = fileItem.getInputStream();
                    int len = 0;
                    while ((len = inputStream.read(buf)) > 0) {
                        fos.write(buf, 0, len);
                    }
//                    删除临时文件
                    fileItem.delete();

                }
            }
            user.setId(Integer.parseInt(id));
            user.setProfilePhoto("http://localhost:8080/img/" + fileName);
//            修改数据库
            if (userServer.updateProfilePhotoById(user)) {
//                成功
                User user_info = (User) req.getSession().getAttribute("USER_INFO");
                User user1 = userServer.selectUserByName(user_info.getName());
                req.getSession().setAttribute("USER_INFO", user1);
                resp.sendRedirect(req.getContextPath() + "/home.jsp");
            } else {
//                失败
                resp.sendRedirect(req.getContextPath() + "/home.jsp");
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }
}
