package com.example.sys.controller;

import com.example.sys.entity.*;
import com.example.sys.service.VideoService;
import com.example.sys.util.PageDataResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


/**
 * Created by tyj on 2019/07/03.
 */
@Slf4j
@Controller
@RequestMapping("/video")
public class VideoController extends BaseController{

	@Autowired
	private VideoService videoService;


	@RequestMapping("")
	public String index(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.debug("-------------index------------");
		User user = (User) request.getSession().getAttribute("admin");
		if (user == null) {
			response.sendRedirect(request.getContextPath()+"/admin/login");
			return null;
		}

		model.addAttribute("username", user.getUsername());
		log.debug("用户名："+user.getUsername());
		return "video/index";
	}


	/**
	 * 分页查询产品列表
	 * @return ok/fail
	 */
	@RequestMapping(value = "/getVideos", method = RequestMethod.POST)
	@ResponseBody
	public PageDataResult getVideos(@RequestParam("page") Integer page,
									  @RequestParam("limit") Integer limit, VideoSearch videoSearch, HttpServletRequest request) {
		log.debug("分页查询产品列表！搜索条件：productSearch：" + videoSearch + ",page:" + page
				+ ",每页记录数量limit:" + limit);
		System.out.println(request.toString());

		PageDataResult pdr = new PageDataResult();
		try {
			if (null == page) {
				page = 1;
			}
			if (null == limit) {
				limit = 10;
			}
			// 获取产品列表
			pdr = videoService.getVideos(videoSearch, page, limit);
      			log.info("产品列表查询=pdr:" + pdr);



		} catch (Exception e) {
			e.printStackTrace();
			log.error("产品列表查询异常！", e);
		}
		return pdr;
	}



	@RequestMapping(value = "/download/{file}")
	public void downloadFileFromSysDir(HttpServletResponse response,@PathVariable String file) {
		String fileName = file;
		String terminal = fileName.substring(0,12);
		response.setHeader("content-type","application/octet-stream");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition","attachment;filename=" + fileName);
		byte[] buff = new byte[1024];
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			bis = new BufferedInputStream(new FileInputStream(new File("D:\\CarDep\\"+terminal+"/"+ fileName)));
			int i = bis.read(buff);
			while (i != -1) {
				os.write(buff, 0, buff.length);
				os.flush();
				i = bis.read(buff);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("成功下载");
	}





}





