package cn.itsource.zmall;

import cn.itsource.util.AjaxResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "COMMON-SERVICE",fallbackFactory = RedisClientFallbackFactory.class)
public interface RedisClient {
    @RequestMapping(value = "/redis",method = RequestMethod.POST)
    public AjaxResult set(@RequestParam("key") String key,@RequestParam("value") String value);

    @RequestMapping(value = "/redis",method = RequestMethod.GET)
    public AjaxResult get(@RequestParam("key")String key);

}
