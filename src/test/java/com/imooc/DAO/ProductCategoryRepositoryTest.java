package com.imooc.DAO;

import com.imooc.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;
    @Test
    public void findOneTest(){
       ProductCategory productCategory =  repository.findById(1).get();
        System.out.println(productCategory.toString());
    }

    @Test
    public void addOneTest(){
      ProductCategory productCategory = new ProductCategory("女生最爱",11);
      ProductCategory result = repository.save(productCategory);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(2,10,11);
        List<ProductCategory> result =  repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,result.size());
    }
}