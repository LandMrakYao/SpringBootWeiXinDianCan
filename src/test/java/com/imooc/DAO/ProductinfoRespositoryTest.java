package com.imooc.DAO;

import com.imooc.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductinfoRespositoryTest {

    @Autowired
    private ProductinfoRespository repository;
    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("皮蛋粥");
        productInfo.setProductPrice(new BigDecimal(2));
        productInfo.setProductStock(200);
        productInfo.setProductDescription("很好喝的粥");
        productInfo.setProductIcon("https://baike.baidu.com/pic/%E7%9A%AE%E8%9B%8B%E7%B2%A5/" +
                "2567316/0/9864a231c067de975edf0eec?fr=lemma&ct=single#aid=0&pic=b3ba5d16d454250621a4e960");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);

        ProductInfo result = repository.save(productInfo);
        Assert.assertNotNull(result);
    }
    @Test
    public void findByProductStatus() {
        List<ProductInfo> productInfoList =  repository.findByProductStatus(0);
        Assert.assertNotEquals(0,productInfoList.size());
    }
}