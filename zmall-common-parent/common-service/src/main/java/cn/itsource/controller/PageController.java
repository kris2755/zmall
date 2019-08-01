package cn.itsource.controller;

import cn.itsource.util.AjaxResult;
import cn.itsource.util.VelocityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PageController {
    //model    templatepath模板路径   targetpath目标文件路径
    @PostMapping("/genPage")
    public AjaxResult genPage(@RequestBody Map<String,Object> map){
        Object model = map.get("model");
        String templatepath = (String) map.get("templatepath");
        String targetpath = (String) map.get("targetpath");
        try {
            VelocityUtils.staticByTemplate(model,templatepath,targetpath);
            return AjaxResult.me().setSuccess(true).setMessage("成功");
        }
        catch (Exception e){
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("失败");
        }
    }
}
