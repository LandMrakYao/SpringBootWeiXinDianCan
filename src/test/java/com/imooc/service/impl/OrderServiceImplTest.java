package com.imooc.service.impl;

import com.dto.OrderDTO;
import com.imooc.dataobject.OrderDetail;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYER_OPENID = "110110";
    private final String ORDER_ID="154322354430715006";
    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("郭郭郭");
        orderDTO.setBuyerAddress("郑州");
        orderDTO.setBuyerPhone("12343131201");
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("123333");
        orderDetail.setProductQuantity(5);

        OrderDetail orderDetail12 = new OrderDetail();
        orderDetail12.setProductId("123457");
        orderDetail12.setProductQuantity(10);

        orderDetailList.add(orderDetail);
        orderDetailList.add(orderDetail12);
        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单】 result={}",result);
    }

    @Test
    public void findOne() {
     OrderDTO result = orderService.findOne(ORDER_ID);
     log.info("【查询单个订单】 result={}",result);
        Assert.assertEquals(ORDER_ID,result.getOrderId());
    }

    @Test
    public void findList() {
        PageRequest request = PageRequest.of(0,2);
        Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID,request);
        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() {
    OrderDTO orderDTO = orderService.findOne(ORDER_ID);
    OrderDTO result = orderService.cancel(orderDTO);
    Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() {
    OrderDTO orderDTO = orderService.findOne(ORDER_ID);
    OrderDTO result = orderService.finish(orderDTO);
    Assert.assertEquals(OrderStatusEnum.FINISH.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() {
     OrderDTO orderDTO = orderService.findOne(ORDER_ID);
     OrderDTO result = orderService.paid(orderDTO);
     Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());
    }

    @Test
    public void list(){
        PageRequest request = PageRequest.of(0,2);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
        //Assert.assertTrue();
    }
}