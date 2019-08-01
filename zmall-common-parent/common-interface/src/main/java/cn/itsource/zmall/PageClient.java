package cn.itsource.zmall;

import cn.itsource.util.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(value = "COMMON-SERVICE",fallbackFactory = PageFallbackFactory.class )//服务提供者的名称
public interface PageClient {

    /**
     * 根据特定模板传入特定数据,生成静态页面到特定位置
     * @param model
     * @param tmeplatePath
     * @param staticPagePath
     * Map<String,Object>
     *      model ==数据
     *      tmeplatePath==xxx
     *      staticPagePath = xxx
     */
    @PostMapping("/genPage")
    public AjaxResult genPage(@RequestBody Map<String,Object> map);
}
