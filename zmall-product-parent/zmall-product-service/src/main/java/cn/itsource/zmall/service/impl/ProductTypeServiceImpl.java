package cn.itsource.zmall.service.impl;

import cn.itsource.util.AjaxResult;
import cn.itsource.zmall.RedisClient;
import cn.itsource.zmall.domain.Product;
import cn.itsource.zmall.domain.ProductType;
import cn.itsource.zmall.mapper.ProductTypeMapper;
import cn.itsource.zmall.service.IProductTypeService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品目录 服务实现类
 * </p>
 *
 * @author kris
 * @since 2019-07-31
 */
@Service
public class ProductTypeServiceImpl extends ServiceImpl<ProductTypeMapper, ProductType> implements IProductTypeService {
    @Autowired
    private RedisClient redisClient;

    @Autowired
    private ProductTypeMapper productTypeMapper;
    @Override
    public List<ProductType> loadTypeTree() {
        AjaxResult result = redisClient.get("productTypes");
        //获取对象，转换成String
        String productTypesJson = (String) result.getResultObj();
        List<ProductType> productTypes = JSONArray.parseArray(productTypesJson, ProductType.class);
        //判断productTypesJson是否为空，如果为空，就调用loop方法查询数据库，把值存进去
        if (productTypes==null||productTypes.size()<=0){
            productTypes=loop();
            redisClient.set("productTypes", JSON.toJSONString(productTypes));
        }
        return productTypes;
    }


    private List<ProductType> loop() {
        List<ProductType> productTypes = baseMapper.selectList(null);
        //定义一个List存放一级菜单
        List<ProductType> list = new ArrayList<>();
        //定义一个Map存放所有的ProductType，key是id，value是类型对象
        Map<Long,ProductType> map = new HashMap<>();
        for (ProductType pt : productTypes) {
            map.put(pt.getId(),pt);
        }
        //循环
        for (ProductType productType : productTypes) {
            if(productType.getPid()==0){
                list.add(productType);
            }else{
                ProductType parent = map.get(productType.getPid());
                parent.getChildren().add(productType);
            }
        }
        return list;
    }
}
