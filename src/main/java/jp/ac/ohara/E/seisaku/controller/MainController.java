package jp.ac.ohara.E.seisaku.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.micrometer.common.lang.NonNull;
import jp.ac.ohara.E.seisaku.model.GakuseiHyou;
import jp.ac.ohara.E.seisaku.model.SeisekiHyou;
import jp.ac.ohara.E.seisaku.model.SyusekiHyou;
import jp.ac.ohara.E.seisaku.service.GakuseiService;
import jp.ac.ohara.E.seisaku.service.SeisekiService;
import jp.ac.ohara.E.seisaku.service.SyusekiService;

@Controller
public class MainController {
	@Autowired
	private GakuseiService GakuseiService;
	@Autowired
	private SeisekiService seisekiService;
	@Autowired
	private SyusekiService syusekiService;


@GetMapping("/")
  public String index(Model model) {
    model.addAttribute("message", "こんにちは");
    return "top";
  }

  @GetMapping("/gakuseihyou/")
  public ModelAndView add(GakuseiHyou gakuseihyou, ModelAndView model)  {
      model.addObject("gakuseihyou", gakuseihyou);
      model.setViewName("gakuseihyou");
  return model;
    }
  
  @PostMapping("/gakuseihyou/")
  public String gakuseihyou(@Validated @ModelAttribute @NonNull GakuseiHyou gakuseihyou, RedirectAttributes result, ModelAndView model,
          RedirectAttributes redirectAttributes) {
      try {
          this.GakuseiService.save(gakuseihyou);
          redirectAttributes.addFlashAttribute("exception", "");

      } catch (Exception e) {
          redirectAttributes.addFlashAttribute("exception", e.getMessage());
      }
      return "redirect:/";

    }
  
  @GetMapping("/seisekihyou/")
  public ModelAndView add(SeisekiHyou seisekihyou, ModelAndView model)  {
      model.addObject("seisekihyou", seisekihyou);

  model.setViewName("seisekitouroku");
  return model;

    }
  
  @PostMapping("/seisekihyou/")
  public String seisekihyou(@Validated @ModelAttribute @NonNull SeisekiHyou seisekihyou, RedirectAttributes result, ModelAndView model,
          RedirectAttributes redirectAttributes) {
      try {
          this.seisekiService.save(seisekihyou);
          redirectAttributes.addFlashAttribute("exception", "");

      } catch (Exception e) {
          redirectAttributes.addFlashAttribute("exception", e.getMessage());
      }
      return "redirect:/";
      
  }


  @GetMapping("/syusekihyou/")
  public ModelAndView add(SyusekiHyou syusekihyou, ModelAndView model)  {
      model.addObject("syusekihyou", syusekihyou);

  model.setViewName("syusekihyou");
  return model;


    }
 
  @PostMapping("/syusekihyou/")
  public String syusekihyou(@Validated @ModelAttribute @NonNull SyusekiHyou syusekihyou, RedirectAttributes result, ModelAndView model,
          RedirectAttributes redirectAttributes) {
      try {
          this.syusekiService.save(syusekihyou);
          redirectAttributes.addFlashAttribute("exception", "");

      } catch (Exception e) {
          redirectAttributes.addFlashAttribute("exception", e.getMessage());
      }
      return "redirect:/";
      
  }
  @GetMapping("/matome/")
  public String hyouji(Model model) {
      // 学生リストを取得し、空でないかを確認
      List<GakuseiHyou> gakuseiList = this.GakuseiService.getGakuseiList();
      if (!gakuseiList.isEmpty()) {
          model.addAttribute("gakusei_list", gakuseiList);
      }

      // 出席リストを取得し、空でないかを確認
      List<SyusekiHyou> syusekiList = this.syusekiService.getSyusekiList();
      if (!syusekiList.isEmpty()) {
          model.addAttribute("syuseki_list", syusekiList);
      }

      // 成績リストを取得し、空でないかを確認
      List<SeisekiHyou> seisekiList = this.seisekiService.getSeisekiList();
      if (!seisekiList.isEmpty()) {
          model.addAttribute("seiseki_list", seisekiList);
      }

      // TODO: データがない場合の処理はThymeleafのテンプレートで行うので、ここでは何も追加しない

      // ビュー名を返す
      return "hyouji";
  }

 

}