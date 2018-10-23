/*
 * Decompiled with CFR 0_133.
 */
package com.qipeng.blackcat.web;

import com.qipeng.blackcat.entity.MouseData;
import com.qipeng.blackcat.service.TextService;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TrackRecognitionController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    TextService textService;

    @RequestMapping(value={"getTrackRecognitionData"})
    @ResponseBody
    public Object testShow() {
        return this.textService.getList();
    }

    @RequestMapping(value={"getDataById"})
    @ResponseBody
    public Object getDataById(String id) {
        this.logger.info("getDataById" + id);
        return this.textService.getList(id);
    }
}
