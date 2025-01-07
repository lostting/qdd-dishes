package cn.qdd.foods.controller;

import cn.qdd.foods.domain.base.Result;
import cn.qdd.foods.domain.base.ResultUtil;
import cn.qdd.foods.service.CategoryService;
import cn.qdd.foods.service.DishService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.PushBuilder;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author 东方曜
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/upload")
@Api(tags = "上传")
public class UploadController {

    private final DishService dishService;
    private final CategoryService categoryService;
    // 上传文件的存储路径
    private static final String UPLOAD_DIR = "/Users/admin/img/";

    @PostMapping("/uploadFile")
    @ApiOperation(value = "上传文件")
    public Result uploadFile(@RequestParam("file") MultipartFile file, PushBuilder pushBuilder) {
        // 检查文件是否为空
        if (file.isEmpty()) {
            return ResultUtil.fail("上传文件不能为空");
        }

        // 生成唯一的文件名，使用 UUID
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename != null ? originalFilename.substring(originalFilename.lastIndexOf(".")) : "";
        String uuidFileName = UUID.randomUUID().toString() + fileExtension;

        // 目标文件路径
        File targetFile = new File(UPLOAD_DIR + uuidFileName);

        // 创建目标目录（如果不存在）
        if (!targetFile.getParentFile().exists()) {
            targetFile.getParentFile().mkdirs();
        }

        try {
            // 保存文件到目标路径
            file.transferTo(targetFile);
            // 返回成功结果，文件路径
//            String path = "http://192.168.124.3:91/img/";
            String path = "http://127.0.0.1:91/img/";
//            return ResultUtil.success(UPLOAD_DIR + uuidFileName);
            return ResultUtil.success(new JSONObject() {{
                put("url", uuidFileName);
                put("data", path + uuidFileName);
            }});
        } catch (IOException e) {
            // 如果保存失败，返回错误信息
            return ResultUtil.fail("文件上传失败：" + e.getMessage());
        }
    }

}
