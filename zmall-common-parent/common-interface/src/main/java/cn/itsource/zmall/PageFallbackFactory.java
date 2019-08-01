package cn.itsource.zmall;

import cn.itsource.util.AjaxResult;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PageFallbackFactory implements FallbackFactory<PageClient>{
    @Override
    public PageClient create(Throwable throwable) {
        return new PageClient() {
            @Override
            public AjaxResult genPage(Map<String, Object> map) {
                return AjaxResult.me().setSuccess(false).setMessage("系统异常");
            }
        };
    }
}
