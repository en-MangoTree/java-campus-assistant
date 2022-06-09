package com.knockknock.controller;

import com.knockknock.entity.Trade;
import com.knockknock.service.TradeService;
import com.knockknock.utils.QueryInfo;
import com.knockknock.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @CreateTime: 2022-06-09 22:06
 * @Description:
 */
@RestController
@RequestMapping("/trade")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @PostMapping("/insert")
    public Result insert(@RequestBody Trade trade){
        return tradeService.insert(trade);
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        return tradeService.delete(id);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Trade trade){
        return tradeService.update(trade);
    }

    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryInfo queryInfo){
        return tradeService.findPage(queryInfo);
    }

}
