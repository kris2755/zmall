package cn.itsource.zmall.service.impl;

import cn.itsource.util.AjaxResult;
import cn.itsource.zmall.PageClient;
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
    private PageClient pageClient;


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

    @Override
    public void genHomePage() {
        Map<String, Object> map = new HashMap<>();
        String templatepath ="C:\\Users\\62685\\Desktop\\zmall\\zmall-parent\\zmall-product-parent\\zmall-product-service\\src\\main\\resources\\template\\product.type.vm";
        String targetpath="C:\\Users\\62685\\Desktop\\zmall\\zmall-parent\\zmall-product-parent\\zmall-product-service\\src\\main\\resources\\template\\product.type.vm.html";

        List<ProductType> productTypes = loadTypeTree();
        map.put("model",productTypes);
        map.put("templatepath",templatepath);
        map.put("targetpath",targetpath);
        pageClient.genPage(map);

        map = new HashMap<>();
        templatepath = "C:\\Users\\62685\\Desktop\\zmall\\zmall-parent\\zmall-product-parent\\zmall-product-service\\src\\main\\resources\\template\\home.vm";
        targetpath = "C:\\Users\\62685\\Desktop\\zmall-web\\zmall-web-plat\\zmall-web-home\\home.html";
        //model 中要有一个数据是staticRoot
        Map<String,String> model = new HashMap<>();
        model.put("staticRoot","C:\\Users\\62685\\Desktop\\zmall\\zmall-parent\\zmall-product-parent\\zmall-product-service\\src\\main\\resources\\");
        map.put("model",model);
        map.put("templatepath",templatepath);
        map.put("targetpath",targetpath);

        pageClient.genPage(map);
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
                List<ProductType> children = parent.getChildren();
                //如果children为空，就为children创建一个新的数组，让他为空
                if (children==null){
                    children = new ArrayList<>();
                }
                children.add(productType);
                parent.setChildren(children);

            }
        }
        return list;
    }
}
