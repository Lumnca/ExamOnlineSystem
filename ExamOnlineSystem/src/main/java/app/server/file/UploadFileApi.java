package app.server.file;


import app.datamodel.common.Response;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UploadFileApi {
    @CrossOrigin
    @PostMapping("uploadimg")
    public Response uploadimg(@RequestParam("upload") MultipartFile file, HttpServletRequest request,
                              HttpServletResponse response){
        try {
            //上传文件
            FastDFSClient dfs = new FastDFSClient();
            //文件名
            String filename = file.getOriginalFilename();
            //后缀名
            String extName = filename.substring(filename.lastIndexOf(".")+1);
            //上传文件
            String url = dfs.uploadFile(file.getBytes(), extName);
            //构建完整的图片地址。
            String imgpath = FileServerAddr.getFileserver()+"/"+url;
           System.out.println("上传地址:"+imgpath);
            return new Response(200,imgpath);

        } catch (Exception e) {
            e.printStackTrace();
            return new Response(400,"上传错误");
        }

    }
    @CrossOrigin
    @PostMapping("uploadimgeditor")
    public JSONObject uploadimg(@RequestParam("upload") MultipartFile file){
        JSONObject object = new JSONObject();
        List<Img> imgs = new ArrayList<>();
        try {
            Img img = new Img();
            //上传文件
            FastDFSClient dfs = new FastDFSClient();
            //文件名
            String filename = file.getOriginalFilename();
            //后缀名
            String extName = filename.substring(filename.lastIndexOf(".")+1);
            //上传文件
            String url = dfs.uploadFile(file.getBytes(), extName);
            //构建完整的图片地址。
            String imgpath = FileServerAddr.getFileserver()+"/"+url;
            object.put("errno",0);
            img.setHref(imgpath);
            img.setUrl(imgpath);
            img.setAlt("这是图片");
            imgs.add(img);

        } catch (Exception e) {
            object.put("error",1);
        }
        object.put("data",imgs);
        return object;
    }

}
