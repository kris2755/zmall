package cn.itsource.controller;

import cn.itsource.util.AjaxResult;
import cn.itsource.util.FastDfsApiOpr;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FdfsController {
    @PostMapping("/fastdfs")
    public AjaxResult upload(MultipartFile file){
        //获取源文件名
        String originalFilename = file.getOriginalFilename();
        //获取文件的扩展名
        String substring = originalFilename.substring(originalFilename.lastIndexOf("." + 1));

        //上传
        try {
            String upload = FastDfsApiOpr.upload(file.getBytes(), substring);
            return AjaxResult.me().setSuccess(true).setMessage("上传成功").setResultObj(upload);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("上传失败"+e.getMessage());
        }
    }

    @DeleteMapping("/fastdfs")
    public AjaxResult upload(String fileId){
        //截取第一个“/”
        try {
            fileId = fileId.substring(1);
            String groupName = fileId.substring(0, fileId.indexOf("/"));
            String fileName = fileId.substring(fileId.indexOf("/")+1);
            FastDfsApiOpr.delete(groupName,fileName);
            return AjaxResult.me().setSuccess(true).setMessage("成功");
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("失败"+e.getMessage());
        }

    }

}
