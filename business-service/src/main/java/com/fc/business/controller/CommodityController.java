package com.fc.business.controller;

import com.fc.business.service.CommodityService;
import com.fc.common.convert.CommodityDomainConvert;
import com.fc.common.dto.ResponseDTO;
import com.fc.common.vo.CommodityVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author lzr
 * @date 2021/3/18 15:23
 */
@Api(
        tags = "商品模块",
        value = "商品模块"
)
@RestController("/commodity")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private CommodityDomainConvert commodityDomainConvert;

    @PostMapping("/create")
    @ApiOperation(notes = "创建商品", value = "创建商品")
    public Map<String, Object> create(@RequestBody CommodityVo commodityVo) {
        commodityService.create(commodityDomainConvert.convert(commodityVo));
        return ResponseDTO.successBuilder()
                .addMessage("创建成功")
                .map();
    }

    @DeleteMapping("/deleteById")
    @ApiOperation(notes = "通过id删除商品", value = "通过id删除商品")
    public Map<String, Object> deleteById(@RequestParam("id") Long id){
        commodityService.deleteById(id);
        return ResponseDTO.successBuilder()
                .addMessage("删除成功")
                .map();
    }

    @PutMapping("/update")
    @ApiOperation(notes = "更新商品", value = "更新商品")
    public Map<String, Object> update(CommodityVo commodityVo){
        commodityService.update(commodityDomainConvert.convert(commodityVo));
        return ResponseDTO.successBuilder()
                .addMessage("更新商品成功")
                .map();
    }

    @GetMapping("/selectById")
    @ApiOperation(notes = "通过id查询", value = "通过id查询")
    public Map<String, Object> selectById(@RequestParam("id") Long id){
        return ResponseDTO.successBuilder()
                .addDataValue(commodityService.selectById(id))
                .map();
    }

    @GetMapping("/selectByName")
    @ApiOperation(notes = "根据商品名称查询", value = "根据商品名称查询")
    public Map<String, Object> selectByName(@RequestParam("commodityName") String commodityName){
        return ResponseDTO.successBuilder()
                .addDataValue(commodityService.selectByName(commodityName))
                .map();
    }
}
