package com.ibeetl.code.javadoc;

/**
 * 2&lt;Integer &gt;
 *
 * &#64;Autowird
 * <a>
 * <pre>{@code
 * 	   @Autowired
 *     ProudctService p = ....;
 *
 *     if(p<1){
 *     //not reached
 *     }
 *}
 * </pre>
 * @author 公众号 java系统优化
 */
public class Product {
  /**
   * 商品销售状态,值是{@value }
   */
  public final static int STATUS_ONSALE = 0;
  /**
   * 商品下架,值是{@value }
   */
  public final  static int STATUS_OFF = 1;

  String code;
  String name;

  int status = STATUS_ONSALE;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * 商品状态,默认值是{@value STATUS_ONSALE}
   * 商品状态定义了销售品是否可以销售
   * @see #STATUS_ONSALE
   * @see #STATUS_OFF
   * @return 商品状态
   */
  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }
}
