package cn.itsource.zmall.service.impl;

import cn.itsource.zmall.domain.Product;
import cn.itsource.zmall.mapper.ProductMapper;
import cn.itsource.zmall.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品 服务实现类
 * </p>
 *
 * @author kris
 * @since 2019-07-31
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
