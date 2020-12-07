package com.ibeetl.code.javadoc;

import java.math.BigDecimal;

/**
 *  价格调用接口
 *  @author 公众号 java系统优化
 */
public interface IPriceService {
  /**
   *  查询商品价格
   * @param code 商品代码
   * @return  商品价格
   */
  BigDecimal queryPrice(String code);
}
