package com.sqy.kingsforglory.glory.controller;


import com.sqy.kingsforglory.glory.pojo.RespData;
import com.sqy.kingsforglory.glory.pojo.entity.Introduction;
import com.sqy.kingsforglory.glory.service.IntroductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/introduction")
public class IntroductController extends HoshiController {
    @Autowired
    private IntroductionService introductionService;

    /**
     * 用于管理员添加推送的信息
     *
     * @param introduction
     * @return
     */
    @PostMapping("/add")
    public RespData<String> add(@RequestBody Introduction introduction) {
        return $(respData -> {
            introductionService.save(introduction);
            String id = introduction.getId();
            if (id == null && id.equals("")) {
                respData.success(false).data(id).msg("添加失败");

            } else {
                respData.success(true).data(id).msg("添加成功");
            }
        });
    }

    /**
     * 管理员根据ID删除推送的信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("/del/{id}")
    public RespData<Boolean> del(@PathVariable String id) {
        return $(booleanRespData -> {
            if (id == null) {
                booleanRespData.success(false).msg("未发现该ID");
            } else {
                introductionService.removeById(id);
                booleanRespData.success(true).data(true).msg("已删除");
            }
        });
    }

    /**
     * 更改推送消息
     *
     * @param introduction
     * @return
     */
    @PutMapping("/update")
    public RespData<Boolean> update(@RequestBody Introduction introduction) {
        return $(booleanRespData -> {
            if (introduction.getId() == null && introduction.getId().equals(" ")) {
                booleanRespData.success(false).msg("未找到该ID");
            } else {
                Introduction byId = introductionService.getById(introduction.getId());
                byId.setTitle(introduction.getTitle());
                byId.setContent(introduction.getContent());
                byId.setImg(introduction.getImg());
                byId.setCreateTime(new Date());
                introductionService.updateById(byId);
                booleanRespData.success(true).data(true).msg("修改成功");
            }
        });
    }
    @GetMapping("/search/{pageIndex}/{pageSize}")
    public RespData<List<Introduction>> search(@RequestParam(name = "search", required = false, defaultValue = "") String key
            , @PathVariable int pageIndex, @PathVariable int pageSize) {
        return $(listRespData -> {
            $page().index(pageIndex).size(pageSize);
            List<Introduction> list = introductionService.bySearch(key);
            listRespData.success(true).data(list);
        });
    }
}
