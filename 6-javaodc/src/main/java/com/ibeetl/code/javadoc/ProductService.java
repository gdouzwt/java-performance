package com.ibeetl.code.javadoc;

/**
 * 产品服务类，用于获取者{@link Product 产品}的基本信息
 * @see Product
 * @author 公众号 java系统优化
 */
public class ProductService {

  /**
   * @deprecated  需要显示的指定一个超时时间，{@link ProductService 参考}
   *
   * {@linkplain ProductService  参考}
   * @param code 商品代码
   * @throws RuntimeException  任意异常
   * @return 商品信息
   *
   */
  public Product queryProduct(String code){
      return null;
  }

  /**
   * 返回 {@code Product.Result}
    * @param code
   * @param timeout
   * @return
   */
  public Product queryProduct(String code,int timeout){
    new Thread().stop();
    return null;
  }

  static class Result{

  }

}
