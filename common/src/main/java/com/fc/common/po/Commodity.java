package com.fc.common.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fc.common.po.status.CommodityStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * @author 李国鹏
 * @version 1.0.0
 * @date 2021/3/10 14:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("commodity")
public class Commodity {

    private Long id;

    /**
     * 商家id
     */
    private Long sellerId;

    /**
     *商品名称
     */
    private String name;

    /**
     * 商品在售界面显示的商品标题
     */
    private String title;

    /**
     * 商品描述
     */
    private String detail;

    /**
     * 商品主图
     */
    private String mainImage;

    /**
     * 商品图片
     */
    private String subImages;

    /**
     * 商品押金
     */
    private Double guaranteePrice;


    /**
     * 商品租金(默认小时为最低计量单位)
     */
    private Integer rentPrice;

    /**
     * 库存
     */
    private Long number;

    /**
     * 商品状态
     * 在售，下架，待上架
     */
    private CommodityStatus commodityStatus;

    /**
     * 自定义属性
     * 例如：
     * 颜色：红白蓝
     * 大小： 1 2 3
     */
    private List<Map<String,List<String>>> uniform;

    /**
     * 发货地址
     */
    private Long deliverAddressId;

    /**
     * 自定义参数
     * 生产日期，品牌等
     * */
    private List<Map<String,String>> attribute;

    /**
     * 成交量
     */
    private Long tradingVolume;

    /**
     * 平均评分
     */
    private Double mark;
    /**
     * 用户评论单独一个表。
     */

    /**
     * 商品所属类别列表
     * 如生活用品
     */
    private List<String> type;
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 更新时间
     */
    private Timestamp updateTime;
    /**
     * 删除时间
     */
    private Timestamp deleteTime;
}
