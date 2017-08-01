package com.jk.modules.cms.controller;

import com.jk.common.base.controller.BaseController;
import com.jk.modules.cms.service.ContentCatService;
import com.jk.modules.sys.vo.TreeNode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

/**
 * 内容分类管理
 * @author cuiP
 * Created by JK on 2017/4/21.
 */
@Controller
@RequestMapping("/admin/content/cat")
public class ContentCatController extends BaseController {

    private static final String BASE_PATH = "admin/content/";

    @Resource
    private ContentCatService contentCatService;

    @GetMapping
    public String list(){
        return BASE_PATH + "content-cat-list";
    }

    /**
     * 查询内容分类树
     *
     * @return
     */
    @GetMapping("/tree")
    public ResponseEntity<List<TreeNode>> getTreeList() {
        List<TreeNode> treeNodeList = contentCatService.findTreeList();
        return ResponseEntity.ok(treeNodeList);
    }
}
