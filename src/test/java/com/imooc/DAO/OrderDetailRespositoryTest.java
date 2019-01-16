package com.imooc.DAO;

import com.imooc.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRespositoryTest {

    @Autowired
    private OrderDetailRespository respository;
    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("132");
        orderDetail.setOrderId("1111111112");
        orderDetail.setProductIcon("http:///xxxxxx");
        orderDetail.setProductId("1212121");
        orderDetail.setProductName("皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(3.2));
        orderDetail.setProductQuantity(10);

        OrderDetail result =  respository.save(orderDetail);
        Assert.assertNotNull(result);
    }
    @Test
    public void findByOrderId() {
      List<OrderDetail> orderDetailList =  respository.findByOrderId("1111111112");
      Assert.assertNotEquals(0,orderDetailList.size());
    }
}