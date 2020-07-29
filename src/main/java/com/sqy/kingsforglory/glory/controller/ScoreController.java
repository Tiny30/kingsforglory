package com.sqy.kingsforglory.glory.controller;


import com.sqy.kingsforglory.glory.pojo.RespData;
import com.sqy.kingsforglory.glory.pojo.entity.Arrangement;
import com.sqy.kingsforglory.glory.pojo.entity.Score;
import com.sqy.kingsforglory.glory.service.ArrangementService;
import com.sqy.kingsforglory.glory.service.ScoreService;
import com.sqy.kingsforglory.glory.service.UserService;
import com.sqy.kingsforglory.stream.HStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/Score")
public class ScoreController extends HoshiController {
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private UserService userService;

    /**
     * 添加战绩信息
     *
     * @param score
     * @return
     */
    @PostMapping("/add")
    public RespData<String> add(@RequestBody Score score) {
        return $(respData -> {
            scoreService.save(score);
            String id = score.getId();
            if (id == null && id.equals("")) {
                respData.success(false).data(id).msg("添加失败");

            } else {
                respData.success(true).data(id).msg("添加成功");
            }
        });
    }

    /**
     * 管理员根据ID删除战绩的信息
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
                boolean b = scoreService.removeById(id);
                booleanRespData.success(true).data(b).msg("已删除");
            }
        });
    }

    /**
     * 更改战绩消息
     *
     * @param score
     * @return
     */
    @PutMapping("/update")
    public RespData<Boolean> update(@RequestBody Score score) {
        return $(booleanRespData -> {
            if (score.getId() == null && score.getId().equals(" ")) {
                booleanRespData.success(false).msg("未找到该ID");
            } else {
                Score byId = scoreService.getById(score.getId());
                byId.setGrade(score.getGrade());
                byId.setHero(score.getHero());
                byId.setTime(new Date());
                scoreService.updateById(byId);
                booleanRespData.success(true).data(true).msg("修改成功");
            }
        });
    }

    /**
     * 根据用户id查询战绩
     *
     * @param userId
     * @return
     */
    @GetMapping("/search/{pageIndex}/{pageSize}")
    public RespData<List<Arrangement>> search(@RequestParam(name = "search", required = false, defaultValue = "") String userId
            , @RequestParam(name = "search", required = false, defaultValue = "") String key
            ,@PathVariable int pageIndex, @PathVariable int pageSize) {
        return $(listRespData -> {
            $page().index(pageIndex).size(pageSize);
            List<Score> scoreList = scoreService.search(key, userId);
            HStream.$(scoreList).forEach(score -> {
                score.setUser(userService.getById(score.getUserId()));
            });

        });
    }
}
