package com.imooc.service.impl;


import com.dto.CartDTO;
import com.imooc.DAO.ProductinfoRespository;
import com.imooc.dataobject.ProductInfo;
import com.imooc.enums.ProductStatusEnum;
import com.imooc.enums.ResultEnum;
import com.imooc.execption.SellException;
import com.imooc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductinfoRespository respository;
    @Override
    public ProductInfo findOne(String productId) {
        return respository.findById(productId).get();
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return respository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return respository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return respository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo = respository.findById(cartDTO.getProductId()).get();
            if (productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //增加库存
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            respository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
            for (CartDTO cartDTO: cartDTOList){
               ProductInfo productInfo =  respository.findById(cartDTO.getProductId()).get();
               if (productInfo == null){
                   throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
               }
               Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
               if (result < 0){
                    throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
               }
               productInfo.setProductStock(result);

               respository.save(productInfo);
            }
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = respository.findById(productId).get();
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.UP) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //更新
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        return respository.save(productInfo);

    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = respository.findById(productId).get();
        if (productInfo == null) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN) {
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //更新
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return respository.save(productInfo);

    }
}
