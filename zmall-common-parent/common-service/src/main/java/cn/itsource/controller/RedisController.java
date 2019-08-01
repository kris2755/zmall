package cn.itsource.controller;

import cn.itsource.util.AjaxResult;
import cn.itsource.util.RedisUtils;
import cn.itsource.zmall.RedisClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController implements RedisClient{
    @RequestMapping(value = "/redis",method = RequestMethod.POST)
    @Override
    public AjaxResult set(@RequestParam("key") String key, @RequestParam("value") String value) {
        try {
            RedisUtils.INSTANCE.set(key,value);
            return AjaxResult.me().setSuccess(true).setMessage("保存成功");
        }
        catch (Exception e){
            return AjaxResult.me().setSuccess(false).setMessage("保存失败");
        }

    }
    @RequestMapping(value = "/redis",method = RequestMethod.GET)
    @Override
    public AjaxResult get(@RequestParam("key") String key) {
        try {
            String value = RedisUtils.INSTANCE.get(key);
            return AjaxResult.me().setSuccess(true).setMessage("成功").setResultObj(value);
        }
        catch (Exception e){
            return AjaxResult.me().setSuccess(false).setMessage("失败");
        }
    }
}
