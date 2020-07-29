package com.sqy.kingsforglory.glory.controller;

import com.sqy.kingsforglory.glory.pojo.RespData;
import com.sqy.kingsforglory.glory.pojo.entity.Arrangement;
import com.sqy.kingsforglory.glory.pojo.entity.Introduction;
import com.sqy.kingsforglory.glory.service.ArrangementService;
import com.sqy.kingsforglory.glory.service.IntroductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/arrangement")
public class ArrangementController extends HoshiController{
    @Autowired
    private ArrangementService arrangementService;

    /**
     * 添加比赛信息
     *
     * @param arrangement
     * @return
     */
    @PostMapping("/add")
    public RespData<String> add(@RequestBody Arrangement arrangement) {
        return $(respData -> {
            arrangementService.save(arrangement);
            String id = arrangement.getId();
            if (id == null && id.equals("")) {
                respData.success(false).data(id).msg("添加失败");

            } else {
                respData.success(true).data(id).msg("添加成功");
            }
        });
    }

    /**
     * 管理员根据ID删除比赛的信息
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
                arrangementService.removeById(id);
                booleanRespData.success(true).data(true).msg("已删除");
            }
        });
    }

    /**
     * 更改比赛消息
     *
     * @param arrangement
     * @return
     */
    @PutMapping("/update")
    public RespData<Boolean> update(@RequestBody Arrangement arrangement) {
        return $(booleanRespData -> {
            if (arrangement.getId() == null && arrangement.getId().equals(" ")) {
                booleanRespData.success(false).msg("未找到该ID");
            } else {
                Arrangement byId = arrangementService.getById(arrangement.getId());
                byId.setName(arrangement.getName());
                byId.setTeam(arrangement.getTeam());
                byId.setGameTime(arrangement.getGameTime());
                byId.setTimes(arrangement.getTimes());
                byId.setVideo(arrangement.getVideo());
                arrangementService.updateById(byId);
                booleanRespData.success(true).data(true).msg("修改成功");
            }
        });
    }
    /**
     * 根据时间查询该时间段的视频，即视频回放
     * @param key
     * @return
     */
    @GetMapping("/search/{pageIndex}/{pageSize}")
    public RespData<List<Arrangement>> search(@RequestParam(name = "search", required = false, defaultValue = "") String key
            , @PathVariable int pageIndex, @PathVariable int pageSize) {
        return $(listRespData -> {
            $page().index(pageIndex).size(pageSize);
            List<Arrangement> list = arrangementService.bySearch(key);
            listRespData.success(true).data(list);
        });
    }
}
