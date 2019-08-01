package cn.itsource.zmall.service;

import cn.itsource.util.PageList;
import cn.itsource.zmall.domain.Brand;
import cn.itsource.zmall.query.BrandQuery;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 品牌信息 服务类
 * </p>
 *
 * @author kris
 * @since 2019-07-31
 */
public interface IBrandService extends IService<Brand> {
    PageList<Brand> queryPage(BrandQuery query);
}
